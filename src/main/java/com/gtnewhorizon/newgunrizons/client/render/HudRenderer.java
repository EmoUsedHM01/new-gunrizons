package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.StatusMessageCenter;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

public class HudRenderer {

    public enum StatusBarPosition {
        TOP_RIGHT,
        BOTTOM_RIGHT,
        TOP_LEFT,
        BOTTOM_LEFT;
    }

    private static final int STATUS_BAR_BOTTOM_OFFSET = 15;
    private static final int STATUS_BAR_TOP_OFFSET = 10;

    private static final int COLOR_WHITE = 0xFFFFFF;
    private static final int COLOR_YELLOW = 0xFFFF00;
    private static final int COLOR_RED = 0xFF0000;

    private final Minecraft mc;
    private final ModContext modContext;
    private final StatusBarPosition statusBarPosition;

    public HudRenderer(Minecraft mc, ModContext modContext) {
        this.mc = mc;
        this.modContext = modContext;
        this.statusBarPosition = StatusBarPosition.TOP_RIGHT;
    }

    public boolean renderWeaponHud(ScaledResolution resolution, ItemStack itemStack,
        ItemWeaponInstance weaponInstance) {
        ItemWeapon weaponItem = (ItemWeapon) itemStack.getItem();
        String crosshair = weaponItem != null ? weaponItem.getCrosshair(weaponInstance) : null;
        if (crosshair == null) {
            return false;
        }

        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        FontRenderer fontRender = mc.fontRenderer;
        mc.entityRenderer.setupOverlayRendering();

        mc.renderEngine.bindTexture(new ResourceLocation(crosshair));
        GL11.glPushAttrib(GL11.GL_ALL_CLIENT_ATTRIB_BITS);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);

        if (isInModifyingState(weaponInstance)) {
            renderAttachmentModeHints(fontRender, width, height);
        } else {
            renderStatusMessage(fontRender, width, height, getDefaultWeaponMessage(weaponInstance), COLOR_YELLOW);
        }

        GL11.glPopAttrib();
        return true;
    }

    public void renderMagazineHud(ScaledResolution resolution, ItemStack itemStack) {
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        FontRenderer fontRender = mc.fontRenderer;
        mc.entityRenderer.setupOverlayRendering();

        renderStatusMessage(fontRender, width, height, getDefaultMagazineMessage(itemStack), COLOR_RED);
    }

    public boolean renderGrenadeHud(ScaledResolution resolution) {
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        FontRenderer fontRender = mc.fontRenderer;
        mc.entityRenderer.setupOverlayRendering();

        StatusMessageCenter.Message message = modContext.getStatusMessageCenter()
            .nextMessage();
        if (message == null) {
            return false;
        }

        int color = COLOR_WHITE;
        String messageText = message.getMessage();
        if (message.isAlert()) {
            color = COLOR_YELLOW;
        }

        int x = getStatusBarXPosition(width, messageText, fontRender);
        int y = getStatusBarYPosition(height);
        int stringWidth = fontRender.getStringWidth(messageText);
        if (stringWidth > 80) {
            x = width - stringWidth - 5;
        }

        fontRender.drawStringWithShadow(messageText, x, y, color);
        return true;
    }

    private void renderAttachmentModeHints(FontRenderer fontRender, int width, int height) {
        String changeScopeMessage = StatCollector.translateToLocalFormatted(
            "gui.attachmentMode.changeScope",
            Keyboard.getKeyName(KeyBindings.upArrowKey.getKeyCode()));
        fontRender.drawStringWithShadow(changeScopeMessage, width / 2 - 40, 60, COLOR_WHITE);

        String changeBarrelRig = StatCollector.translateToLocalFormatted(
            "gui.attachmentMode.changeBarrelRig",
            Keyboard.getKeyName(KeyBindings.leftArrowKey.getKeyCode()));
        fontRender.drawStringWithShadow(changeBarrelRig, 10, height / 2 - 10, COLOR_WHITE);

        String changeUnderBarrelRig = StatCollector.translateToLocalFormatted(
            "gui.attachmentMode.changeUnderBarrelRig",
            Keyboard.getKeyName(KeyBindings.downArrowKey.getKeyCode()));
        fontRender.drawStringWithShadow(changeUnderBarrelRig, 10, height - 40, COLOR_WHITE);
    }

    private void renderStatusMessage(FontRenderer fontRender, int width, int height, String defaultMessage,
        int alertColor) {
        int color = COLOR_WHITE;
        String messageText;

        StatusMessageCenter.Message message = modContext.getStatusMessageCenter()
            .nextMessage();
        if (message != null) {
            messageText = message.getMessage();
            if (message.isAlert()) {
                color = alertColor;
            }
        } else {
            messageText = defaultMessage;
        }

        int x = getStatusBarXPosition(width, messageText, fontRender);
        int y = getStatusBarYPosition(height);
        fontRender.drawStringWithShadow(messageText, x, y, color);
    }

    private String getDefaultMagazineMessage(ItemStack itemStack) {
        ItemMagazine magazine = (ItemMagazine) itemStack.getItem();
        return StatCollector
            .translateToLocalFormatted("gui.ammoCounter", Tags.getAmmo(itemStack) + "/" + magazine.getAmmo());
    }

    private String getDefaultWeaponMessage(ItemWeaponInstance weaponInstance) {
        ItemMagazine magazine = (ItemMagazine) WeaponAttachmentAspect
            .getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
        int totalCapacity;
        if (magazine != null) {
            totalCapacity = magazine.getAmmo();
        } else {
            totalCapacity = weaponInstance.getWeapon()
                .getAmmoCapacity();
        }

        if (weaponInstance.getWeapon()
            .getAmmoCapacity() == 0 && totalCapacity == 0) {
            return StatCollector.translateToLocalFormatted("gui.noMagazine");
        }
        return StatCollector.translateToLocalFormatted(
            "gui.ammoCounter",
            weaponInstance.getWeapon()
                .getCurrentAmmo() + "/"
                + totalCapacity);
    }

    private boolean isInModifyingState(ItemWeaponInstance weaponInstance) {
        return weaponInstance.getState() == WeaponState.MODIFYING
            || weaponInstance.getState() == WeaponState.NEXT_ATTACHMENT;
    }

    private int getStatusBarXPosition(int width, String text, FontRenderer fontRender) {
        if (statusBarPosition == StatusBarPosition.BOTTOM_RIGHT || statusBarPosition == StatusBarPosition.TOP_RIGHT) {
            int x = width - 80;
            int stringWidth = fontRender.getStringWidth(text);
            if (stringWidth > 80) {
                x = width - stringWidth - 5;
            }
            return x;
        }
        return 10;
    }

    private int getStatusBarYPosition(int height) {
        switch (statusBarPosition) {
            case BOTTOM_RIGHT:
            case BOTTOM_LEFT:
                return height - STATUS_BAR_BOTTOM_OFFSET;
            default:
                return STATUS_BAR_TOP_OFFSET;
        }
    }
}
