package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.grenade.ItemGrenade;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.network.StatusMessageCenter;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CustomGui extends Gui {

    public enum StatusBarPosition {
        TOP_RIGHT,
        BOTTOM_RIGHT,
        TOP_LEFT,
        BOTTOM_LEFT;
    }

    private static final int STATUS_BAR_BOTTOM_OFFSET = 15;
    private static final int STATUS_BAR_TOP_OFFSET = 10;

    private final Minecraft mc;
    private final ModContext modContext;
    private final StatusBarPosition statusBarPosition;

    public CustomGui(Minecraft mc, ModContext modContext) {
        this.mc = mc;
        this.modContext = modContext;
        this.statusBarPosition = StatusBarPosition.TOP_RIGHT;
    }

    @SubscribeEvent
    public void onRenderHud(Pre event) {}

    @SubscribeEvent
    public void onRenderCrosshair(Pre event) {
        if (event.type == ElementType.CROSSHAIRS) {
            ItemStack itemStack = Minecraft.getMinecraft().thePlayer.getHeldItem();
            if (itemStack != null) {
                PlayerWeaponInstance weaponInstance = this.modContext.getMainHeldWeapon();
                int color;
                int stringWidth;
                if (weaponInstance != null) {
                    ItemWeapon weaponItem = (ItemWeapon) itemStack.getItem();
                    String crosshair = weaponItem != null ? weaponItem.getCrosshair(weaponInstance) : null;
                    if (crosshair != null) {
                        ScaledResolution scaledResolution = event.resolution;
                        int width = scaledResolution.getScaledWidth();
                        int height = scaledResolution.getScaledHeight();
                        FontRenderer fontRender = Minecraft.getMinecraft().fontRenderer;
                        this.mc.entityRenderer.setupOverlayRendering();
                        color = 16777215;
                        this.mc.renderEngine.bindTexture(new ResourceLocation(crosshair));
                        GL11.glPushAttrib(8192);
                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glDisable(2896);
                        GL11.glBlendFunc(770, 771);
                        GL11.glEnable(3042);
                        String messageText;
                        if (this.isInModifyingState(weaponInstance)) {
                            String changeScopeMessage = StatCollector.translateToLocalFormatted(
                                "gui.attachmentMode.changeScope",
                                Keyboard.getKeyName(KeyBindings.upArrowKey.getKeyCode()));
                            fontRender.drawStringWithShadow(changeScopeMessage, width / 2 - 40, 60, color);
                            messageText = StatCollector.translateToLocalFormatted(
                                "gui.attachmentMode.changeBarrelRig",
                                Keyboard.getKeyName(KeyBindings.leftArrowKey.getKeyCode()));
                            fontRender.drawStringWithShadow(messageText, 10, height / 2 - 10, color);
                            String changeUnderBarrelRig = StatCollector.translateToLocalFormatted(
                                "gui.attachmentMode.changeUnderBarrelRig",
                                Keyboard.getKeyName(KeyBindings.downArrowKey.getKeyCode()));
                            fontRender.drawStringWithShadow(changeUnderBarrelRig, 10, height - 40, color);
                        } else {
                            StatusMessageCenter.Message message = this.modContext.getStatusMessageCenter()
                                .nextMessage();
                            if (message != null) {
                                messageText = message.getMessage();
                                if (message.isAlert()) {
                                    color = 16776960;
                                }
                            } else {
                                messageText = this.getDefaultWeaponMessage(weaponInstance);
                            }

                            stringWidth = this.getStatusBarXPosition(width, messageText, fontRender);
                            int y = this.getStatusBarYPosition(color);
                            fontRender.drawStringWithShadow(messageText, stringWidth, y, color);
                        }

                        GL11.glPopAttrib();
                        event.setCanceled(true);
                    }
                } else {
                    ScaledResolution scaledResolution;
                    int width;
                    int height;
                    FontRenderer fontRender;
                    StatusMessageCenter.Message message;
                    String messageText;
                    int x;
                    int y;
                    if (itemStack.getItem() instanceof ItemMagazine) {
                        scaledResolution = event.resolution;
                        width = scaledResolution.getScaledWidth();
                        height = scaledResolution.getScaledHeight();
                        fontRender = Minecraft.getMinecraft().fontRenderer;
                        this.mc.entityRenderer.setupOverlayRendering();
                        color = 16777215;
                        message = this.modContext.getStatusMessageCenter()
                            .nextMessage();
                        if (message != null) {
                            messageText = message.getMessage();
                            if (message.isAlert()) {
                                color = 16711680;
                            }
                        } else {
                            messageText = this.getDefaultMagazineMessage(itemStack);
                        }

                        x = this.getStatusBarXPosition(width, messageText, fontRender);
                        y = this.getStatusBarYPosition(height);
                        fontRender.drawStringWithShadow(messageText, x, y, color);
                        event.setCanceled(true);
                    } else if (itemStack.getItem() instanceof ItemGrenade) {
                        scaledResolution = event.resolution;
                        width = scaledResolution.getScaledWidth();
                        height = scaledResolution.getScaledHeight();
                        fontRender = Minecraft.getMinecraft().fontRenderer;
                        this.mc.entityRenderer.setupOverlayRendering();
                        color = 16777215;
                        message = this.modContext.getStatusMessageCenter()
                            .nextMessage();
                        if (message != null) {
                            messageText = message.getMessage();
                            if (message.isAlert()) {
                                color = 16776960;
                            }

                            x = this.getStatusBarXPosition(width, messageText, fontRender);
                            y = this.getStatusBarYPosition(height);
                            stringWidth = fontRender.getStringWidth(messageText);
                            if (stringWidth > 80) {
                                x = width - stringWidth - 5;
                            }

                            fontRender.drawStringWithShadow(messageText, x, y, color);
                            event.setCanceled(true);
                        }
                    }
                }

            }
        }
    }

    private int getStatusBarXPosition(int width, String text, FontRenderer fontRender) {
        int x;
        if (this.statusBarPosition != StatusBarPosition.BOTTOM_RIGHT
            && this.statusBarPosition != StatusBarPosition.TOP_RIGHT) {
            x = 10;
        } else {
            x = width - 80;
            int stringWidth = fontRender.getStringWidth(text);
            if (stringWidth > 80) {
                x = width - stringWidth - 5;
            }
        }

        return x;
    }

    private int getStatusBarYPosition(int height) {
        return switch (this.statusBarPosition) {
            case BOTTOM_RIGHT, BOTTOM_LEFT -> height - STATUS_BAR_BOTTOM_OFFSET;
            default -> STATUS_BAR_TOP_OFFSET;
        };
    }

    private String getDefaultMagazineMessage(ItemStack itemStack) {
        ItemMagazine magazine = (ItemMagazine) itemStack.getItem();
        return StatCollector
            .translateToLocalFormatted("gui.ammoCounter", Tags.getAmmo(itemStack) + "/" + magazine.getAmmo());
    }

    private String getDefaultWeaponMessage(PlayerWeaponInstance weaponInstance) {
        ItemMagazine magazine = (ItemMagazine) WeaponAttachmentAspect
            .getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
        int totalCapacity;
        if (magazine != null) {
            totalCapacity = magazine.getAmmo();
        } else {
            totalCapacity = weaponInstance.getWeapon()
                .getAmmoCapacity();
        }

        String text;
        if (weaponInstance.getWeapon()
            .getAmmoCapacity() == 0 && totalCapacity == 0) {
            text = StatCollector.translateToLocalFormatted("gui.noMagazine");
        } else {
            text = StatCollector.translateToLocalFormatted(
                "gui.ammoCounter",
                weaponInstance.getWeapon()
                    .getCurrentAmmo() + "/"
                    + totalCapacity);
        }

        return text;
    }

    private boolean isInModifyingState(PlayerWeaponInstance weaponInstance) {
        return weaponInstance.getState() == WeaponState.MODIFYING
            || weaponInstance.getState() == WeaponState.MODIFYING_REQUESTED
            || weaponInstance.getState() == WeaponState.NEXT_ATTACHMENT
            || weaponInstance.getState() == WeaponState.NEXT_ATTACHMENT_REQUESTED;
    }
}
