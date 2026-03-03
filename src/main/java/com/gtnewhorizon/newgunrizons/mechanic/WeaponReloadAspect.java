package com.gtnewhorizon.newgunrizons.mechanic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.ItemAttachment;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.Permit;
import com.gtnewhorizon.newgunrizons.state.PermitManager;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import com.gtnewhorizon.newgunrizons.weapon.ItemBullet;
import com.gtnewhorizon.newgunrizons.weapon.ItemMagazine;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

public class WeaponReloadAspect implements Aspect<WeaponState, PlayerWeaponInstance> {

    private static final Logger logger = LogManager.getLogger(WeaponReloadAspect.class);
    private static final long ALERT_TIMEOUT = 500L;
    private static final Set<WeaponState> allowedUpdateFromStates;

    private static final Predicate<PlayerWeaponInstance> sprinting;
    private static final Predicate<PlayerWeaponInstance> hasNextLoadIteration;
    private static final Predicate<PlayerWeaponInstance> supportsDirectBulletLoad;
    private static final Predicate<PlayerWeaponInstance> magazineAttached;
    private static final Predicate<PlayerWeaponInstance> loadIterationCompleted;
    private static final Predicate<PlayerWeaponInstance> allLoadIterationsCompleted;
    private static final Predicate<PlayerWeaponInstance> reloadAnimationCompleted;
    private static final Predicate<PlayerWeaponInstance> unloadAnimationCompleted;
    private static final Predicate<PlayerWeaponInstance> prepareFirstLoadIterationAnimationCompleted;
    private final Predicate<PlayerWeaponInstance> inventoryHasFreeSlots = (
        weaponInstance) -> weaponInstance.getPlayer() instanceof EntityPlayer
            && InventoryUtils.inventoryHasFreeSlots((EntityPlayer) weaponInstance.getPlayer());
    private static final Predicate<PlayerWeaponInstance> alertTimeoutExpired;
    private final Predicate<ItemStack> magazineNotEmpty = (magazineStack) -> Tags.getAmmo(magazineStack) > 0;
    private final ModContext modContext;
    private PermitManager permitManager;
    private StateManager<WeaponState, ? super PlayerWeaponInstance> stateManager;

    public WeaponReloadAspect(ModContext modContext) {
        this.modContext = modContext;
    }

    public void setStateManager(StateManager<WeaponState, ? super PlayerWeaponInstance> stateManager) {
        if (this.permitManager == null) {
            throw new IllegalStateException("Permit manager not initialized");
        } else {
            this.stateManager = stateManager.in(this)
                .change(WeaponState.READY)
                .to(WeaponState.LOAD)
                .when(supportsDirectBulletLoad.or(magazineAttached.negate()))
                .withPermit(
                    (s, es) -> { return new WeaponReloadAspect.LoadPermit(s); },
                    this.modContext.getPlayerItemInstanceRegistry()::update,
                    this.permitManager)
                .withAction((c, f, t, p) -> { this.completeClientLoad(c, (WeaponReloadAspect.LoadPermit) p); })
                .manual()
                .in(this)
                .change(WeaponState.LOAD)
                .to(WeaponState.READY)
                .when(reloadAnimationCompleted.and(hasNextLoadIteration.negate()))
                .automatic()
                .in(this)
                .change(WeaponState.LOAD)
                .to(WeaponState.LOAD_ITERATION)
                .when(hasNextLoadIteration.and(prepareFirstLoadIterationAnimationCompleted))
                .withAction(this::startLoadIteration)
                .automatic()
                .in(this)
                .change(WeaponState.LOAD_ITERATION)
                .to(WeaponState.LOAD_ITERATION_COMPLETED)
                .when(loadIterationCompleted)
                .withAction(this::completeLoadIteration)
                .automatic()
                .in(this)
                .change(WeaponState.LOAD_ITERATION_COMPLETED)
                .to(WeaponState.LOAD_ITERATION)
                .when(hasNextLoadIteration)
                .withAction(this::startLoadIteration)
                .automatic()
                .in(this)
                .change(WeaponState.LOAD_ITERATION_COMPLETED)
                .to(WeaponState.ALL_LOAD_ITERATIONS_COMPLETED)
                .when(hasNextLoadIteration.negate())
                .automatic()
                .in(this)
                .change(WeaponState.ALL_LOAD_ITERATIONS_COMPLETED)
                .to(WeaponState.READY)
                .when(allLoadIterationsCompleted)
                .withAction(this::completeAllLoadIterations)
                .automatic()
                .in(this)
                .prepare((c, f, t) -> { this.prepareUnload(c); }, unloadAnimationCompleted)
                .change(WeaponState.READY)
                .to(WeaponState.UNLOAD)
                .when(magazineAttached.and(this.inventoryHasFreeSlots))
                .withPermit(
                    (s, c) -> new UnloadPermit(s),
                    this.modContext.getPlayerItemInstanceRegistry()::update,
                    this.permitManager)
                .withAction((c, f, t, p) -> this.completeClientUnload(c, (UnloadPermit) p))
                .manual()
                .in(this)
                .change(WeaponState.UNLOAD)
                .to(WeaponState.READY)
                .automatic()
                .in(this)
                .change(WeaponState.READY)
                .to(WeaponState.ALERT)
                .when(this.inventoryHasFreeSlots.negate())
                .withAction(this::inventoryFullAlert)
                .manual()
                .in(this)
                .change(WeaponState.ALERT)
                .to(WeaponState.READY)
                .when(alertTimeoutExpired)
                .automatic();
        }
    }

    public void setPermitManager(PermitManager permitManager) {
        this.permitManager = permitManager;
        permitManager.registerEvaluator(
            WeaponReloadAspect.LoadPermit.class,
            PlayerWeaponInstance.class,
            this::processLoadPermit);
        permitManager.registerEvaluator(
            WeaponReloadAspect.UnloadPermit.class,
            PlayerWeaponInstance.class,
            this::processUnloadPermit);
    }

    public void reloadMainHeldItem(EntityPlayer player) {
        PlayerWeaponInstance instance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerWeaponInstance.class);
        if (instance != null) {
            this.stateManager.changeState(this, instance, WeaponState.LOAD, WeaponState.UNLOAD, WeaponState.ALERT);
        }

    }

    public void updateMainHeldItem(EntityPlayer player) {
        PlayerWeaponInstance instance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerWeaponInstance.class);
        if (instance != null) {
            this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates);
        }

    }

    private void processLoadPermit(WeaponReloadAspect.LoadPermit p, PlayerWeaponInstance weaponInstance) {
        logger.debug("Processing load permit on server for {}", weaponInstance);
        ItemStack weaponItemStack = weaponInstance.getItemStack();
        if (weaponItemStack != null && weaponInstance.getPlayer() instanceof EntityPlayer player) {
            Permit.Status status = Permit.Status.GRANTED;
            weaponInstance.setLoadIterationCount(0);
            Weapon weapon = (Weapon) weaponInstance.getItem();
            if (weaponItemStack.stackTagCompound == null) {
                weaponItemStack.stackTagCompound = new NBTTagCompound();
            }

            List<ItemMagazine> compatibleMagazines = weapon.getCompatibleMagazines();
            List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);
            if (!compatibleMagazines.isEmpty()) {
                ItemAttachment existingMagazine = WeaponAttachmentAspect
                    .getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
                int ammo = Tags.getAmmo(weaponItemStack);
                if (existingMagazine == null) {
                    ammo = 0;
                    ItemStack magazineItemStack = InventoryUtils.tryConsumingCompatibleItem(
                        compatibleMagazines,
                        1,
                        player,
                        this.magazineNotEmpty,
                        (magazineStack) -> true);
                    if (magazineItemStack != null) {
                        ammo = Tags.getAmmo(magazineItemStack);
                        Tags.setAmmo(weaponItemStack, ammo);
                        logger.debug("Setting server side ammo for {} to {}", weaponInstance, ammo);
                        WeaponAttachmentAspect
                            .addAttachment((ItemAttachment) magazineItemStack.getItem(), weaponInstance);
                        player.worldObj.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
                    } else {
                        status = Permit.Status.DENIED;
                    }
                }

                weaponInstance.setAmmo(ammo);
            } else {
                ItemStack consumedStack;
                if (!compatibleBullets.isEmpty() && (consumedStack = InventoryUtils.tryConsumingCompatibleItem(
                    compatibleBullets,
                    Math.min(weapon.getMaxBulletsPerReload(), weapon.getAmmoCapacity() - weaponInstance.getAmmo()),
                    player,
                    (i) -> true)) != null) {
                    int ammo = weaponInstance.getAmmo() + consumedStack.stackSize;
                    Tags.setAmmo(weaponItemStack, ammo);
                    weaponInstance.setAmmo(ammo);
                    if (weapon.hasIteratedLoad()) {
                        weaponInstance.setLoadIterationCount(consumedStack.stackSize);
                    }

                    player.worldObj.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
                } else if (player.inventory.consumeInventoryItem(weapon.builder.ammo)) {
                    Tags.setAmmo(weaponItemStack, weapon.builder.ammoCapacity);
                    weaponInstance.setAmmo(weapon.builder.ammoCapacity);
                    player.worldObj.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
                } else {
                    logger.debug("No suitable ammo found for {}. Permit denied.", weaponInstance);
                    status = Permit.Status.DENIED;
                }
            }

            p.setStatus(status);
        }
    }

    private void prepareUnload(PlayerWeaponInstance weaponInstance) {
        if (weaponInstance.getWeapon()
            .getUnloadSound() != null) {
            weaponInstance.getPlayer()
                .playSound(
                    weaponInstance.getWeapon()
                        .getUnloadSound(),
                    1.0F,
                    1.0F);
        }
    }

    private void processUnloadPermit(WeaponReloadAspect.UnloadPermit p, PlayerWeaponInstance weaponInstance) {
        logger.debug("Processing unload permit on server for {}", weaponInstance);
        if (!(weaponInstance.getPlayer() instanceof EntityPlayer player)) {
            logger.warn("Not a player");
        } else {
            ItemStack weaponItemStack = weaponInstance.getItemStack();
            Weapon weapon = (Weapon) weaponItemStack.getItem();
            if (weaponItemStack.stackTagCompound != null) {
                ItemAttachment attachment = this.modContext.getAttachmentAspect()
                    .removeAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
                if (attachment instanceof ItemMagazine) {
                    ItemStack attachmentItemStack = ((ItemMagazine) attachment).createItemStack();
                    Tags.setAmmo(attachmentItemStack, weaponInstance.getAmmo());
                    if (!player.inventory.addItemStackToInventory(attachmentItemStack)) {
                        logger.error(
                            "Cannot add attachment " + attachment + " for " + weaponInstance + "back to the inventory");
                    }
                }

                Tags.setAmmo(weaponItemStack, 0);
                weaponInstance.setAmmo(0);
                player.worldObj.playSoundToNearExcept(player, weapon.getUnloadSound(), 1.0F, 1.0F);
                p.setStatus(Permit.Status.GRANTED);
            } else {
                p.setStatus(Permit.Status.DENIED);
            }

            p.setStatus(Permit.Status.GRANTED);
        }
    }

    private void completeClientLoad(PlayerWeaponInstance weaponInstance, WeaponReloadAspect.LoadPermit permit) {
        if (permit == null) {
            logger.error("Permit is null, something went wrong");
        } else {
            if (permit.getStatus() == Permit.Status.GRANTED) {
                if (weaponInstance.getWeapon()
                    .getReloadSound() != null) {
                    weaponInstance.getPlayer()
                        .playSound(
                            weaponInstance.getWeapon()
                                .getReloadSound(),
                            1.0F,
                            1.0F);
                }
            }

        }
    }

    private void completeClientUnload(PlayerWeaponInstance weaponInstance, WeaponReloadAspect.UnloadPermit p) {}

    public void inventoryFullAlert(PlayerWeaponInstance weaponInstance) {
        this.modContext.getStatusMessageCenter()
            .addAlertMessage(StatCollector.translateToLocalFormatted("gui.inventoryFull"), 3, 250L, 200L);
    }

    public void startLoadIteration(PlayerWeaponInstance weaponInstance) {
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

    public void completeLoadIteration(PlayerWeaponInstance weaponInstance) {
        weaponInstance.setLoadIterationCount(weaponInstance.getLoadIterationCount() - 1);
    }

    public void completeAllLoadIterations(PlayerWeaponInstance weaponInstance) {
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
        TypeRegistry.getInstance()
            .register(WeaponReloadAspect.UnloadPermit.class);
        TypeRegistry.getInstance()
            .register(WeaponReloadAspect.LoadPermit.class);
        TypeRegistry.getInstance()
            .register(PlayerWeaponInstance.class);
        allowedUpdateFromStates = new HashSet<>(
            Arrays.asList(
                WeaponState.LOAD_REQUESTED,
                WeaponState.LOAD,
                WeaponState.LOAD_ITERATION,
                WeaponState.LOAD_ITERATION_COMPLETED,
                WeaponState.ALL_LOAD_ITERATIONS_COMPLETED,
                WeaponState.UNLOAD_PREPARING,
                WeaponState.UNLOAD_REQUESTED,
                WeaponState.UNLOAD,
                WeaponState.ALERT));
        sprinting = (instance) -> instance.getPlayer()
            .isSprinting();
        hasNextLoadIteration = (weaponInstance) -> weaponInstance.getWeapon()
            .hasIteratedLoad() && weaponInstance.getLoadIterationCount() > 0;
        supportsDirectBulletLoad = (weaponInstance) -> weaponInstance.getWeapon()
            .getAmmoCapacity() > 0;
        magazineAttached = (
            weaponInstance) -> WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance)
                != null;
        loadIterationCompleted = (weaponInstance) -> System.currentTimeMillis()
            >= weaponInstance.getStateUpdateTimestamp() + Math.max(
                weaponInstance.getWeapon().builder.loadIterationTimeout,
                weaponInstance.getWeapon()
                    .getTotalLoadIterationDuration() + 250L);
        allLoadIterationsCompleted = (weaponInstance) -> System.currentTimeMillis()
            >= weaponInstance.getStateUpdateTimestamp() + weaponInstance.getWeapon()
                .getAllLoadIterationAnimationsCompletedDuration();
        reloadAnimationCompleted = (weaponInstance) -> (double) System.currentTimeMillis()
            >= (double) weaponInstance.getStateUpdateTimestamp() + Math.max(
                (double) weaponInstance.getWeapon().builder.reloadingTimeout,
                (double) weaponInstance.getWeapon()
                    .getTotalReloadingDuration() * 1.1D);
        unloadAnimationCompleted = (weaponInstance) -> (double) System.currentTimeMillis()
            >= (double) weaponInstance.getStateUpdateTimestamp() + (double) weaponInstance.getWeapon()
                .getTotalUnloadingDuration() * 1.1D;
        prepareFirstLoadIterationAnimationCompleted = (weaponInstance) -> (double) System.currentTimeMillis()
            >= (double) weaponInstance.getStateUpdateTimestamp() + (double) weaponInstance.getWeapon()
                .getPrepareFirstLoadIterationAnimationDuration() * 1.1D;
        alertTimeoutExpired = (instance) -> System.currentTimeMillis()
            >= ALERT_TIMEOUT + instance.getStateUpdateTimestamp();
    }

    public static class LoadPermit extends Permit<WeaponState> {

        public LoadPermit() {}

        public LoadPermit(WeaponState state) {
            super(state);
        }
    }

    public static class UnloadPermit extends Permit<WeaponState> {

        public UnloadPermit() {}

        public UnloadPermit(WeaponState state) {
            super(state);
        }
    }
}
