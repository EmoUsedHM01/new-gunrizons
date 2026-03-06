package com.gtnewhorizon.newgunrizons.weapon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.entity.player.EntityPlayer;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemMagazineInstance;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;

public class MagazineReloadAspect implements Aspect<MagazineState, ItemMagazineInstance> {

    private static final Set<MagazineState> allowedUpdateFromStates;
    private static final long reloadAnimationDuration;
    private static final Predicate<ItemMagazineInstance> reloadAnimationCompleted;
    public static final MagazineReloadAspect INSTANCE = new MagazineReloadAspect();

    private StateManager<MagazineState, ? super ItemMagazineInstance> stateManager;
    private final Predicate<ItemMagazineInstance> notFull = (instance) -> ItemInstance.getAmmo(instance.getItemStack())
        < instance.getMagazine()
            .getAmmo();

    /** Client-side guard: checks if player has compatible bullets in inventory. */
    private final Predicate<ItemMagazineInstance> hasCompatibleBullets = (instance) -> {
        if (!(instance.getPlayer() instanceof EntityPlayer player)) {
            return false;
        }
        if (!(instance.getItemStack()
            .getItem() instanceof ItemMagazine magazine)) {
            return false;
        }
        List<ItemBullet> compatibleBullets = magazine.getCompatibleBullets();
        return InventoryUtils.hasCompatibleItem(compatibleBullets, player, (s) -> true);
    };

    public MagazineReloadAspect() {}

    public void setStateManager(StateManager<MagazineState, ? super ItemMagazineInstance> stateManager) {
        this.stateManager = stateManager.in(this)
            .change(MagazineState.READY)
            .to(MagazineState.LOAD)
            .when(this.notFull.and(this.hasCompatibleBullets))
            .withAction(this::performLoad)
            .manual()
            .in(this)
            .change(MagazineState.LOAD)
            .to(MagazineState.READY)
            .when(reloadAnimationCompleted)
            .automatic();
    }

    public void reloadHeldItem(EntityPlayer player) {
        ItemMagazineInstance instance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemMagazineInstance.class);
        this.stateManager.changeState(this, instance, MagazineState.LOAD);
    }

    public void updateHeldItem(EntityPlayer player) {
        ItemMagazineInstance instance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemMagazineInstance.class);
        if (instance != null) {
            this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates);
        }

    }

    private void performLoad(ItemMagazineInstance magazineInstance) {
        // Send action to server for authoritative inventory operations (bullet consumption)
        NewGunrizonsMod.CHANNEL.sendToServer(
            new WeaponActionMessage(WeaponActionMessage.MAGAZINE_LOAD, magazineInstance.getItemInventoryIndex()));
    }

    static {
        allowedUpdateFromStates = new HashSet<>(Arrays.asList(MagazineState.LOAD));
        reloadAnimationDuration = 1000L;
        reloadAnimationCompleted = (es) -> System.currentTimeMillis()
            >= es.getStateUpdateTimestamp() + reloadAnimationDuration;
    }
}
