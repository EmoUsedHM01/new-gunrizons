package com.gtnewhorizon.newgunrizons.client.handlers;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.ADSConfig;
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
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;

public class WeaponInputHandler {
   private boolean leftMouseButtonPressed;
   private int lastItemIndex = -1;

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onMouse(MouseEvent event) {
      if (event.button == 0 || event.button == 1) {
         EntityPlayer player = Minecraft.getMinecraft().thePlayer;
         if (player != null) {
            Item item = getHeldItem(player);
            if (item instanceof ItemWeapon || item instanceof ItemGrenade) {
               // Allow right-click block interaction (chests, crafting tables, etc.) when looking at an interactable block
               if (event.button == 1 && event.buttonstate && item instanceof ItemWeapon) {
                  MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
                  if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                     World world = player.worldObj;
                     Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
                     if (isInteractableBlock(world, block, mop.blockX, mop.blockY, mop.blockZ)) {
                        return;
                     }
                  }
               }
               event.setCanceled(true);
               if (event.button == 0) {
                  if (event.buttonstate) {
                     this.leftMouseButtonPressed = true;
                     this.lastItemIndex = player.inventory.currentItem;
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
               } else if (item instanceof ItemWeapon && ADSConfig.isHoldMode()) {
                  // Hold mode: press = aim, release = stop aiming
                  ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
                  if (instance != null) {
                     instance.setAimed(event.buttonstate);
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
      EntityPlayer player = Minecraft.getMinecraft().thePlayer;
      if (player != null) {
         ItemStack itemStack = player.getHeldItem();
         if (KeyBindings.reloadKey.isPressed()) {
            if (itemStack != null) {
               Item item = itemStack.getItem();
               if (item instanceof Reloadable) {
                  ((Reloadable)item).reloadHeldItem(player);
               }
            }
         } else if (KeyBindings.laserSwitchKey.isPressed()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && (instance.getState() == WeaponState.READY || instance.getState() == WeaponState.MODIFYING)) {
               instance.setLaserOn(!instance.isLaserOn());
               NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)5, player.inventory.currentItem));
            }
         } else if (KeyBindings.nightVisionSwitchKey.isPressed()) {
            ItemWeaponInstance nvInstance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (nvInstance != null
               && (
                  nvInstance.getState() == WeaponState.READY
                     || nvInstance.getState() == WeaponState.MODIFYING
                     || nvInstance.getState() == WeaponState.EJECT_REQUIRED
               )) {
               nvInstance.setNightVisionOn(!nvInstance.isNightVisionOn());
            }
         } else if (KeyBindings.attachmentKey.isPressed()) {
            if (itemStack != null && itemStack.getItem() instanceof ItemWeapon) {
               ((ItemWeapon)itemStack.getItem()).toggleClientAttachmentSelectionMode(player);
            }
         } else if (KeyBindings.upArrowKey.isPressed()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.MODIFYING) {
               WeaponAttachmentAspect.INSTANCE.changeAttachment(AttachmentCategory.SCOPE, instance);
            }
         } else if (KeyBindings.downArrowKey.isPressed()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.MODIFYING) {
               WeaponAttachmentAspect.INSTANCE.changeAttachment(AttachmentCategory.GRIP, instance);
            }
         } else if (KeyBindings.leftArrowKey.isPressed()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.MODIFYING) {
               WeaponAttachmentAspect.INSTANCE.changeAttachment(AttachmentCategory.SILENCER, instance);
            }
         } else if (KeyBindings.fireModeKey.isPressed()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && instance.getState() == WeaponState.READY) {
               instance.getWeapon().changeFireMode(instance);
            }
         } else if (KeyBindings.addKey.isPressed()) {
            ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
            if (instance != null && (instance.getState() == WeaponState.READY || instance.getState() == WeaponState.EJECT_REQUIRED)) {
               instance.getWeapon().incrementZoom(instance);
            }
         } else if (KeyBindings.subtractKey.isPressed()) {
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
         EntityPlayer player = Minecraft.getMinecraft().thePlayer;
         if (player != null && Minecraft.getMinecraft().currentScreen == null) {
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
         EntityPlayer player = Minecraft.getMinecraft().thePlayer;
         if (player != null) {
            int currentItemIndex = player.inventory.currentItem;
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
      ItemStack itemStack = player.getHeldItem();
      return itemStack != null ? itemStack.getItem() : null;
   }

   private static boolean isInteractableBlock(World world, Block block, int x, int y, int z) {
      // Tile entity blocks: chests, furnaces, dispensers, hoppers, brewing stands,
      // enchanting tables, beacons, ender chests, and all mod machines (GregTech, etc.)
      if (world.getTileEntity(x, y, z) != null) {
         return true;
      }
      // Vanilla interactable blocks without tile entities
      return block instanceof BlockWorkbench
         || block instanceof BlockDoor
         || block instanceof BlockTrapDoor
         || block instanceof BlockFenceGate
         || block instanceof BlockLever
         || block instanceof BlockButton
         || block instanceof BlockBed
         || block instanceof BlockAnvil
         || block instanceof BlockCake
         || block instanceof BlockCauldron;
   }
}
