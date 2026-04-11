package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.StatusMessageManager;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class HudRenderer {
   private static final int STATUS_BAR_BOTTOM_OFFSET = 15;
   private static final int STATUS_BAR_TOP_OFFSET = 10;
   private static final float AMMO_COUNTER_SCALE = 1.5F;
   private static final int COLOR_WHITE = 16777215;
   private static final int COLOR_YELLOW = 16776960;
   private static final int COLOR_RED = 16711680;
   private final HudRenderer.StatusBarPosition statusBarPosition = HudRenderer.StatusBarPosition.BOTTOM_RIGHT;

   public boolean renderWeaponHud(ScaledResolution resolution, ItemStack itemStack, ItemWeaponInstance weaponInstance) {
      ItemWeapon weaponItem = (ItemWeapon)itemStack.getItem();
      String crosshair = weaponItem != null ? weaponItem.getCrosshair(weaponInstance) : null;
      if (crosshair == null) {
         return false;
      } else {
         Minecraft mc = Minecraft.getMinecraft();
         int width = resolution.getScaledWidth();
         int height = resolution.getScaledHeight();
         FontRenderer fontRender = mc.fontRenderer;
         mc.entityRenderer.setupOverlayRendering();
         mc.renderEngine.bindTexture(new ResourceLocation(crosshair));
         GL11.glPushAttrib(-1);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 771);
         GL11.glEnable(3042);
         if (this.isInModifyingState(weaponInstance)) {
            this.renderAttachmentModeHints(fontRender, width, height);
         } else {
            this.renderStatusMessage(fontRender, width, height, this.getDefaultWeaponMessage(weaponInstance), 16776960);
         }

         GL11.glPopAttrib();
         return true;
      }
   }

   public void renderMagazineHud(ScaledResolution resolution, ItemStack itemStack) {
      int width = resolution.getScaledWidth();
      int height = resolution.getScaledHeight();
      Minecraft mc = Minecraft.getMinecraft();
      FontRenderer fontRender = mc.fontRenderer;
      mc.entityRenderer.setupOverlayRendering();
      this.renderStatusMessage(fontRender, width, height, this.getDefaultMagazineMessage(itemStack), 16711680);
   }

   public boolean renderGrenadeHud(ScaledResolution resolution) {
      int width = resolution.getScaledWidth();
      int height = resolution.getScaledHeight();
      Minecraft mc = Minecraft.getMinecraft();
      FontRenderer fontRender = mc.fontRenderer;
      mc.entityRenderer.setupOverlayRendering();
      StatusMessageManager.Message message = StatusMessageManager.INSTANCE.nextMessage();
      if (message == null) {
         return false;
      } else {
         int color = 16777215;
         String messageText = message.getMessage();
         if (message.isAlert()) {
            color = 16776960;
         }

         int stringWidth = fontRender.getStringWidth(messageText);
         int x = width - stringWidth - 5;
         int y = this.getStatusBarYPosition(height, fontRender.FONT_HEIGHT);
         fontRender.drawStringWithShadow(messageText, x, y, color);
         return true;
      }
   }

   private void renderAttachmentModeHints(FontRenderer fontRender, int width, int height) {
      String changeScopeMessage = StatCollector.translateToLocalFormatted(
         "gui.attachmentMode.changeScope", new Object[]{Keyboard.getKeyName(KeyBindings.upArrowKey.getKeyCode())}
      );
      fontRender.drawStringWithShadow(changeScopeMessage, width / 2 - 40, 60, 16777215);
      String changeBarrelRig = StatCollector.translateToLocalFormatted(
         "gui.attachmentMode.changeBarrelRig", new Object[]{Keyboard.getKeyName(KeyBindings.leftArrowKey.getKeyCode())}
      );
      fontRender.drawStringWithShadow(changeBarrelRig, 10, height / 2 - 10, 16777215);
      String changeUnderBarrelRig = StatCollector.translateToLocalFormatted(
         "gui.attachmentMode.changeUnderBarrelRig", new Object[]{Keyboard.getKeyName(KeyBindings.downArrowKey.getKeyCode())}
      );
      fontRender.drawStringWithShadow(changeUnderBarrelRig, 10, height - 40, 16777215);
   }

   private void renderStatusMessage(FontRenderer fontRender, int width, int height, String defaultMessage, int alertColor) {
      int color = 16777215;
      StatusMessageManager.Message message = StatusMessageManager.INSTANCE.nextMessage();
      String messageText;
      if (message != null) {
         messageText = message.getMessage();
         if (message.isAlert()) {
            color = alertColor;
         }
      } else {
         messageText = defaultMessage;
      }

      int stringWidth = fontRender.getStringWidth(messageText);
      int scaledStringWidth = (int)(stringWidth * 1.5F);
      int scaledHeight = (int)(fontRender.FONT_HEIGHT * 1.5F);
      int x = width - scaledStringWidth - 5;
      int y = this.getStatusBarYPosition(height, scaledHeight);
      GL11.glPushMatrix();
      GL11.glTranslatef(x, y, 0.0F);
      GL11.glScalef(1.5F, 1.5F, 1.0F);
      fontRender.drawStringWithShadow(messageText, 0, 0, color);
      GL11.glPopMatrix();
   }

   private String getDefaultMagazineMessage(ItemStack itemStack) {
      ItemMagazine magazine = (ItemMagazine)itemStack.getItem();
      return StatCollector.translateToLocalFormatted("gui.ammoCounter", new Object[]{ItemInstance.getAmmo(itemStack) + "/" + magazine.getAmmo()});
   }

   private String getDefaultWeaponMessage(ItemWeaponInstance weaponInstance) {
      ItemMagazine magazine = (ItemMagazine)WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
      int totalCapacity;
      if (magazine != null) {
         totalCapacity = magazine.getAmmo();
      } else {
         totalCapacity = weaponInstance.getWeapon().getAmmoCapacity();
      }

      return weaponInstance.getWeapon().getAmmoCapacity() == 0 && totalCapacity == 0
         ? StatCollector.translateToLocalFormatted("gui.noMagazine", new Object[0])
         : StatCollector.translateToLocalFormatted("gui.ammoCounter", new Object[]{weaponInstance.getWeapon().getCurrentAmmo() + "/" + totalCapacity});
   }

   private boolean isInModifyingState(ItemWeaponInstance weaponInstance) {
      return weaponInstance.getState() == WeaponState.MODIFYING || weaponInstance.getState() == WeaponState.NEXT_ATTACHMENT;
   }

   private int getStatusBarYPosition(int height, int textHeight) {
      switch (this.statusBarPosition) {
         case BOTTOM_RIGHT:
         case BOTTOM_LEFT:
            return height - 15 - textHeight;
         default:
            return 10;
      }
   }

   public static enum StatusBarPosition {
      TOP_RIGHT,
      BOTTOM_RIGHT,
      TOP_LEFT,
      BOTTOM_LEFT;
   }
}
