package com.gtnewhorizon.newgunrizons.client.handlers;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.weapon.Reloadable;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;

public class WeaponInputHandler {
   private boolean leftMouseButtonPressed;
   private int lastItemIndex = -1;

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onMouse(MouseEvent event) {
      if (event.button == 0 || event.button == 1) {
         EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
         if (player != null) {
            Item item = getHeldItem(player);
            if (item instanceof ItemWeapon || item instanceof ItemGrenade) {
               event.setCanceled(true);
               if (event.button == 0) {
                  if (event.buttonstate) {
                     this.leftMouseButtonPressed = true;
                     this.lastItemIndex = player.field_71071_by.field_70461_c;
                     if (item instanceof ItemWeapon) {
                        ((ItemWeapon)item).tryFire(player);
                     } else {
                        ((ItemGrenade)item).attack(player, true);
                     }
                  } else {
                     this.leftMouseButtonPressed = false;
                     if (item instanceof ItemWeapon) {
                        ((ItemWeapon)item).tryStopFire(player);
                     } else {
                        ((ItemGrenade)item).attackUp(player, true);
                     }
                  }
               } else if (event.buttonstate) {
                  if (item instanceof ItemWeapon) {
                     ((ItemWeapon)item).toggleAiming();
                  } else {
                     ((ItemGrenade)item).attack(player, false);
                  }
               } else if (item instanceof ItemGrenade) {
                  ((ItemGrenade)item).attackUp(player, false);
               }
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onKeyInput(KeyInputEvent event) {
      EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
      if (player != null) {
         ItemStack itemStack = player.func_70694_bm();
         if (KeyBindings.reloadKey.func_151468_f()) {
            if (itemStack != null) {
               Item item = itemStack.func_77973_b();
               if (item instanceof Reloadable) {
                  ((Reloadable)item).reloadHeldItem(player);
               }
            }
         } else if (KeyBindings.laserSwitchKey.func_151468_f()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && (instance.getState() == WeaponState.READY || instance.getState() == WeaponState.MODIFYING)) {
               instance.setLaserOn(!instance.isLaserOn());
               NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)5, player.field_71071_by.field_70461_c));
            }
         } else if (KeyBindings.nightVisionSwitchKey.func_151468_f()) {
            ItemWeaponInstance nvInstance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (nvInstance != null
               && (
                  nvInstance.getState() == WeaponState.READY
                     || nvInstance.getState() == WeaponState.MODIFYING
                     || nvInstance.getState() == WeaponState.EJECT_REQUIRED
               )) {
               nvInstance.setNightVisionOn(!nvInstance.isNightVisionOn());
            }
         } else if (KeyBindings.attachmentKey.func_151468_f()) {
            if (itemStack != null && itemStack.func_77973_b() instanceof ItemWeapon) {
               ((ItemWeapon)itemStack.func_77973_b()).toggleClientAttachmentSelectionMode(player);
            }
         } else if (KeyBindings.upArrowKey.func_151468_f()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.MODIFYING) {
               WeaponAttachmentAspect.INSTANCE.changeAttachment(AttachmentCategory.SCOPE, instance);
            }
         } else if (KeyBindings.downArrowKey.func_151468_f()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.MODIFYING) {
               WeaponAttachmentAspect.INSTANCE.changeAttachment(AttachmentCategory.GRIP, instance);
            }
         } else if (KeyBindings.leftArrowKey.func_151468_f()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.MODIFYING) {
               WeaponAttachmentAspect.INSTANCE.changeAttachment(AttachmentCategory.SILENCER, instance);
            }
         } else if (KeyBindings.fireModeKey.func_151468_f()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.READY) {
               instance.getWeapon().changeFireMode(instance);
            }
         } else if (KeyBindings.addKey.func_151468_f()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && (instance.getState() == WeaponState.READY || instance.getState() == WeaponState.EJECT_REQUIRED)) {
               instance.getWeapon().incrementZoom(instance);
            }
         } else if (KeyBindings.subtractKey.func_151468_f()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && (instance.getState() == WeaponState.READY || instance.getState() == WeaponState.EJECT_REQUIRED)) {
               instance.getWeapon().decrementZoom(instance);
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderTick(RenderTickEvent event) {
      if (event.phase == Phase.START && this.leftMouseButtonPressed) {
         EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
         if (player != null && Minecraft.func_71410_x().field_71462_r == null) {
            Item item = getHeldItem(player);
            if (item instanceof ItemWeapon) {
               ((ItemWeapon)item).tryFire(player);
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onClientTick(ClientTickEvent event) {
      if (event.phase == Phase.START && this.leftMouseButtonPressed) {
         EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
         if (player != null) {
            int currentItemIndex = player.field_71071_by.field_70461_c;
            if (this.lastItemIndex != currentItemIndex) {
               Item item = getHeldItem(player);
               if (item instanceof ItemWeapon) {
                  ((ItemWeapon)item).tryStopFire(player);
               } else if (item instanceof ItemGrenade) {
                  ((ItemGrenade)item).attackUp(player, true);
               }

               this.leftMouseButtonPressed = false;
               this.lastItemIndex = currentItemIndex;
            }
         }
      }
   }

   private static Item getHeldItem(EntityPlayer player) {
      ItemStack itemStack = player.func_70694_bm();
      return itemStack != null ? itemStack.func_77973_b() : null;
   }
}
