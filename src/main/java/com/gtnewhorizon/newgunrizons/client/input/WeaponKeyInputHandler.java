package com.gtnewhorizon.newgunrizons.client.input;

import java.util.function.Function;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.Reloadable;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class WeaponKeyInputHandler {

    private final Function<Object, EntityPlayer> entityPlayerSupplier;
    private final ModContext modContext;

    public WeaponKeyInputHandler(ModContext modContext, Function<Object, EntityPlayer> entityPlayerSupplier) {
        this.modContext = modContext;
        this.entityPlayerSupplier = entityPlayerSupplier;
    }

    @SubscribeEvent
    public final void onKeyInput(KeyInputEvent event) {
        this.onCompatibleKeyInput();
    }

    private void onCompatibleKeyInput() {
        EntityPlayer player = this.entityPlayerSupplier.apply(null);
        ItemStack itemStack = player.getHeldItem();
        if (KeyBindings.reloadKey.isPressed()) {
            if (itemStack != null) {
                Item item = itemStack.getItem();
                if (item instanceof Reloadable) {
                    ((Reloadable) item).reloadMainHeldItemForPlayer(player);
                }
            }
        } else {
            PlayerWeaponInstance instance;
            if (KeyBindings.laserSwitchKey.isPressed()) {
                instance = this.modContext.getPlayerItemInstanceRegistry()
                    .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                if (instance != null
                    && (instance.getState() == WeaponState.READY || instance.getState() == WeaponState.MODIFYING)) {
                    instance.setLaserOn(!instance.isLaserOn());
                }
            } else if (KeyBindings.nightVisionSwitchKey.isPressed()) {
                PlayerWeaponInstance nvInstance = this.modContext.getPlayerItemInstanceRegistry()
                    .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                if (nvInstance != null
                    && (nvInstance.getState() == WeaponState.READY || nvInstance.getState() == WeaponState.MODIFYING
                        || nvInstance.getState() == WeaponState.EJECT_REQUIRED)) {
                    nvInstance.setNightVisionOn(!nvInstance.isNightVisionOn());
                }
            } else if (KeyBindings.attachmentKey.isPressed()) {
                if (itemStack != null && itemStack.getItem() instanceof ItemWeapon) {
                    ((ItemWeapon) itemStack.getItem()).toggleClientAttachmentSelectionMode(player);
                }
            } else if (KeyBindings.upArrowKey.isPressed()) {
                instance = this.modContext.getPlayerItemInstanceRegistry()
                    .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                if (instance != null && instance.getState() == WeaponState.MODIFYING) {
                    this.modContext.getAttachmentAspect()
                        .changeAttachment(AttachmentCategory.SCOPE, instance);
                }
            } else {
                if (KeyBindings.downArrowKey.isPressed()) {
                    instance = this.modContext.getPlayerItemInstanceRegistry()
                        .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                    if (instance != null && instance.getState() == WeaponState.MODIFYING) {
                        this.modContext.getAttachmentAspect()
                            .changeAttachment(AttachmentCategory.GRIP, instance);
                    }
                } else if (KeyBindings.leftArrowKey.isPressed()) {
                    instance = this.modContext.getPlayerItemInstanceRegistry()
                        .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                    if (instance != null && instance.getState() == WeaponState.MODIFYING) {
                        this.modContext.getAttachmentAspect()
                            .changeAttachment(AttachmentCategory.SILENCER, instance);
                    }
                } else if (KeyBindings.fireModeKey.isPressed()) {
                    instance = this.modContext.getPlayerItemInstanceRegistry()
                        .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                    if (instance != null && instance.getState() == WeaponState.READY) {
                        instance.getWeapon()
                            .changeFireMode(instance);
                    }
                } else if (KeyBindings.addKey.isPressed()) {
                    instance = this.modContext.getPlayerItemInstanceRegistry()
                        .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                    if (instance != null && (instance.getState() == WeaponState.READY
                        || instance.getState() == WeaponState.EJECT_REQUIRED)) {
                        instance.getWeapon()
                            .incrementZoom(instance);
                    }
                } else if (KeyBindings.subtractKey.isPressed()) {
                    instance = this.modContext.getPlayerItemInstanceRegistry()
                        .getMainHandItemInstance(player, PlayerWeaponInstance.class);
                    if (instance != null && (instance.getState() == WeaponState.READY
                        || instance.getState() == WeaponState.EJECT_REQUIRED)) {
                        instance.getWeapon()
                            .decrementZoom(instance);
                    }
                }
            }
        }

    }
}
