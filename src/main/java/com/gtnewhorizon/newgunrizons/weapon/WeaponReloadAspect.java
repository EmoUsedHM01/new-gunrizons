package com.gtnewhorizon.newgunrizons.weapon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.StatusMessageManager;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;

public class WeaponReloadAspect implements Aspect<WeaponState, ItemWeaponInstance> {

    private static final long ALERT_TIMEOUT = 500L;
    private static final Set<WeaponState> allowedUpdateFromStates;

    private static final Predicate<ItemWeaponInstance> hasNextLoadIteration;
    private static final Predicate<ItemWeaponInstance> supportsDirectBulletLoad;
    private static final Predicate<ItemWeaponInstance> loadIterationCompleted;
    private static final Predicate<ItemWeaponInstance> allLoadIterationsCompleted;
    private static final Predicate<ItemWeaponInstance> reloadAnimationCompleted;
    private static final Predicate<ItemWeaponInstance> prepareFirstLoadIterationAnimationCompleted;
    private static final Predicate<ItemWeaponInstance> alertTimeoutExpired;

    public static final WeaponReloadAspect INSTANCE = new WeaponReloadAspect();

    private StateManager<WeaponState, ? super ItemWeaponInstance> stateManager;

    private final Predicate<ItemWeaponInstance> notFullyLoaded = (weaponInstance) -> weaponInstance.getAmmo()
        < weaponInstance.getWeapon().getAmmoCapacity();

    /** Client-side guard: checks if player has compatible ammo in inventory. */
    private final Predicate<ItemWeaponInstance> hasCompatibleAmmo = (weaponInstance) -> {
        if (!(weaponInstance.getPlayer() instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer) weaponInstance.getPlayer();
        ItemWeapon weapon = weaponInstance.getWeapon();
        List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);
        if (!compatibleBullets.isEmpty()) {
            return InventoryUtils.hasCompatibleItem(compatibleBullets, player, (s) -> true);
        }
        return weapon.getAmmo() != null && player.inventory.hasItem(weapon.getAmmo());
    };

    public WeaponReloadAspect() {}

    public void setStateManager(StateManager<WeaponState, ? super ItemWeaponInstance> stateManager) {
        this.stateManager = stateManager.in(this)
            .change(WeaponState.IDLE)
            .to(WeaponState.RELOADING_START)
            .when(supportsDirectBulletLoad.and(this.notFullyLoaded).and(this.hasCompatibleAmmo))
            .withAction(this::performLoad)
            .manual()
            .in(this)
            .change(WeaponState.RELOADING_START)
            .to(WeaponState.IDLE)
            .when(reloadAnimationCompleted.and(hasNextLoadIteration.negate()))
            .automatic()
            .in(this)
            .change(WeaponState.RELOADING_START)
            .to(WeaponState.RELOADING_ITERATION)
            .when(hasNextLoadIteration.and(prepareFirstLoadIterationAnimationCompleted))
            .withAction(this::startLoadIteration)
            .automatic()
            .in(this)
            .change(WeaponState.RELOADING_ITERATION)
            .to(WeaponState.RELOADING_ITERATION_COMPLETED)
            .when(loadIterationCompleted)
            .withAction(this::completeLoadIteration)
            .automatic()
            .in(this)
            .change(WeaponState.RELOADING_ITERATION_COMPLETED)
            .to(WeaponState.RELOADING_ITERATION)
            .when(hasNextLoadIteration)
            .withAction(this::startLoadIteration)
            .automatic()
            .in(this)
            .change(WeaponState.RELOADING_ITERATION_COMPLETED)
            .to(WeaponState.RELOADING_END)
            .when(hasNextLoadIteration.negate())
            .automatic()
            .in(this)
            .change(WeaponState.RELOADING_END)
            .to(WeaponState.IDLE)
            .when(allLoadIterationsCompleted)
            .withAction(this::completeAllLoadIterations)
            .automatic()
            .in(this)
            .change(WeaponState.IDLE)
            .to(WeaponState.NO_AMMO)
            .when(this.hasCompatibleAmmo.negate())
            .withAction(this::noAmmoAlert)
            .manual()
            .in(this)
            .change(WeaponState.NO_AMMO)
            .to(WeaponState.IDLE)
            .when(alertTimeoutExpired)
            .automatic();
    }

    public void reloadMainHeldItem(EntityPlayer player) {
        ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemWeaponInstance.class);
        if (instance != null) {
            this.stateManager.changeState(this, instance, WeaponState.RELOADING_START, WeaponState.NO_AMMO);
        }
    }

    public void updateMainHeldItem(EntityPlayer player) {
        ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemWeaponInstance.class);
        if (instance != null) {
            this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates);
        }
    }

    private void performLoad(ItemWeaponInstance weaponInstance) {
        if (!(weaponInstance.getPlayer() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) weaponInstance.getPlayer();
        ItemWeapon weapon = weaponInstance.getWeapon();

        weaponInstance.setLoadIterationCount(0);
        List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);

        if (!compatibleBullets.isEmpty()) {
            int maxToLoad = Math
                .min(weapon.getMaxBulletsPerReload(), weapon.getAmmoCapacity() - weaponInstance.getAmmo());
            int loaded = this.countAvailableBullets(compatibleBullets, player, maxToLoad);
            if (loaded > 0) {
                weaponInstance.setAmmo(weaponInstance.getAmmo() + loaded);
                weaponInstance.setLoadIterationCount(weapon.hasIteratedLoad() ? loaded : 1);
            }
        } else if (weapon.getAmmo() != null && player.inventory.hasItem(weapon.getAmmo())) {
            weaponInstance.setAmmo(weapon.getAmmoCapacity());
            weaponInstance.setLoadIterationCount(1);
        }

        if (weapon.getReloadSound() != null) {
            player.playSound(weapon.getReloadSound(), 1.0F, 1.0F);
        }

        NewGunrizonsMod.CHANNEL.sendToServer(
            new WeaponActionMessage(WeaponActionMessage.WEAPON_LOAD, weaponInstance.getItemInventoryIndex()));
    }

    private int countAvailableBullets(List<ItemAttachment> compatibleBullets, EntityPlayer player, int maxToLoad) {
        int total = 0;
        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack == null) continue;
            for (ItemAttachment bullet : compatibleBullets) {
                if (stack.getItem() == bullet) {
                    total += stack.stackSize;
                    if (total >= maxToLoad) {
                        return maxToLoad;
                    }
                    break;
                }
            }
        }
        return total;
    }

    public void noAmmoAlert(ItemWeaponInstance weaponInstance) {
        StatusMessageManager.INSTANCE
            .addAlertMessage(StatCollector.translateToLocalFormatted("gui.noAmmo"), 3, 250L, 200L);
    }

    public void startLoadIteration(ItemWeaponInstance weaponInstance) {
        if (weaponInstance.getWeapon()
            .getReloadIterationSound() != null) {
            weaponInstance.getPlayer()
                .playSound(
                    weaponInstance.getWeapon()
                        .getReloadIterationSound(),
                    1.0F,
                    1.0F);
        }
    }

    public void completeLoadIteration(ItemWeaponInstance weaponInstance) {
        weaponInstance.setLoadIterationCount(weaponInstance.getLoadIterationCount() - 1);
    }

    public void completeAllLoadIterations(ItemWeaponInstance weaponInstance) {
        if (weaponInstance.getWeapon()
            .getAllReloadIterationsCompletedSound() != null) {
            weaponInstance.getPlayer()
                .playSound(
                    weaponInstance.getWeapon()
                        .getAllReloadIterationsCompletedSound(),
                    1.0F,
                    1.0F);
        }
    }

    static {
        allowedUpdateFromStates = new HashSet<>(
            Arrays.asList(
                WeaponState.RELOADING_START,
                WeaponState.RELOADING_ITERATION,
                WeaponState.RELOADING_ITERATION_COMPLETED,
                WeaponState.RELOADING_END,
                WeaponState.NO_AMMO));

        hasNextLoadIteration = (weaponInstance) -> weaponInstance.getLoadIterationCount() > 0;
        supportsDirectBulletLoad = (weaponInstance) -> weaponInstance.getWeapon()
            .getAmmoCapacity() > 0;
        loadIterationCompleted = (weaponInstance) -> System.currentTimeMillis()
            >= weaponInstance.getStateUpdateTimestamp()
                + weaponInstance.getWeapon().getAnimationDurationMs(RenderableState.RELOADING_ITERATION);
        allLoadIterationsCompleted = (weaponInstance) -> System.currentTimeMillis()
            >= weaponInstance.getStateUpdateTimestamp()
                + weaponInstance.getWeapon().getAnimationDurationMs(RenderableState.RELOADING_END);
        reloadAnimationCompleted = (weaponInstance) -> System.currentTimeMillis()
            >= weaponInstance.getStateUpdateTimestamp()
                + weaponInstance.getWeapon().getAnimationDurationMs(RenderableState.RELOADING_START);
        prepareFirstLoadIterationAnimationCompleted = (weaponInstance) -> System.currentTimeMillis()
            >= weaponInstance.getStateUpdateTimestamp()
                + weaponInstance.getWeapon().getAnimationDurationMs(RenderableState.RELOADING_START);
        alertTimeoutExpired = (instance) -> System.currentTimeMillis()
            >= ALERT_TIMEOUT + instance.getStateUpdateTimestamp();
    }
}
