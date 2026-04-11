package com.gtnewhorizon.newgunrizons.network;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponFireAspect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class WeaponActionMessageHandler implements IMessageHandler<WeaponActionMessage, IMessage> {
   private final WeaponFireAspect weaponFireAspect;

   public WeaponActionMessageHandler(WeaponFireAspect weaponFireAspect) {
      this.weaponFireAspect = weaponFireAspect;
   }

   public IMessage onMessage(WeaponActionMessage message, MessageContext ctx) {
      if (ctx.side != Side.SERVER) {
         return null;
      } else {
         EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
         int slotIndex = message.getSlotIndex();
         if (slotIndex >= 0 && slotIndex < player.field_71071_by.field_70462_a.length) {
            ItemStack itemStack = player.field_71071_by.func_70301_a(slotIndex);
            if (itemStack == null) {
               return null;
            } else {
               switch (message.getActionType()) {
                  case 0:
                     this.processWeaponLoad(player, itemStack, slotIndex);
                     break;
                  case 1:
                     this.processWeaponUnload(player, itemStack, slotIndex);
                     break;
                  case 2:
                     this.processMagazineLoad(player, itemStack);
                     break;
                  case 3:
                     this.processChangeAttachment(player, itemStack, message.getAttachmentCategory(), slotIndex);
                     break;
                  case 4:
                     this.processChangeFireMode(player, itemStack, slotIndex);
                     break;
                  case 5:
                     this.processToggleLaser(player, itemStack, slotIndex);
                     break;
                  case 6:
                     this.processZoom(player, itemStack, true, slotIndex);
                     break;
                  case 7:
                     this.processZoom(player, itemStack, false, slotIndex);
                     break;
                  case 8:
                     this.weaponFireAspect.serverFire(player, itemStack, slotIndex);
               }

               return null;
            }
         } else {
            return null;
         }
      }
   }

   private ItemWeaponInstance getOrCreateInstance(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
      if (!(weaponStack.func_77973_b() instanceof ItemWeapon)) {
         return null;
      } else {
         ItemWeapon weapon = (ItemWeapon)weaponStack.func_77973_b();
         if (weaponStack.field_77990_d == null) {
            weaponStack.field_77990_d = new NBTTagCompound();
         }

         ItemWeaponInstance instance = ItemInstance.fromStack(weaponStack, ItemWeaponInstance.class);
         if (instance == null) {
            instance = weapon.createItemInstance(player, weaponStack, slotIndex);
            ItemInstance.toStack(weaponStack, instance);
         }

         instance.setPlayer(player);
         return instance;
      }
   }

   private void processWeaponLoad(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
      if (weaponStack.func_77973_b() instanceof ItemWeapon) {
         ItemWeapon weapon = (ItemWeapon)weaponStack.func_77973_b();
         if (weaponStack.field_77990_d == null) {
            weaponStack.field_77990_d = new NBTTagCompound();
         }

         ItemWeaponInstance instance = this.getOrCreateInstance(player, weaponStack, slotIndex);
         List<ItemMagazine> compatibleMagazines = weapon.getCompatibleMagazines();
         List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);
         if (!compatibleMagazines.isEmpty()) {
            this.loadWithMagazine(player, weaponStack, weapon, instance, compatibleMagazines);
         } else if (!compatibleBullets.isEmpty()) {
            this.loadWithBullets(player, weaponStack, weapon, instance, compatibleBullets);
         } else if (weapon.getAmmo() != null) {
            this.loadWithGenericAmmo(player, weaponStack, weapon, instance);
         }
      }
   }

   private void loadWithMagazine(
      EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon, ItemWeaponInstance instance, List<ItemMagazine> compatibleMagazines
   ) {
      ItemAttachment existingMagazine = WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, instance);
      if (existingMagazine != null) {
         int ammo = ItemInstance.getAmmo(weaponStack);
         instance.setAmmo(ammo);
         instance.setLoadIterationCount(0);
         ItemInstance.toStack(weaponStack, instance);
      } else {
         ItemStack magazineStack = InventoryUtils.tryConsumingCompatibleItem(
            compatibleMagazines, 1, player, stack -> ItemInstance.getAmmo(stack) > 0, stack -> true
         );
         if (magazineStack != null) {
            int ammo = ItemInstance.getAmmo(magazineStack);
            ItemInstance.setAmmo(weaponStack, ammo);
            WeaponAttachmentAspect.addAttachment((ItemAttachment)magazineStack.func_77973_b(), instance);
            instance.setAmmo(ammo);
            instance.setLoadIterationCount(0);
            ItemInstance.toStack(weaponStack, instance);
            player.field_70170_p.func_85173_a(player, weapon.getReloadSound(), 1.0F, 1.0F);
         }
      }
   }

   private void loadWithBullets(
      EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon, ItemWeaponInstance instance, List<ItemAttachment> compatibleBullets
   ) {
      int currentAmmo = instance.getAmmo();
      int maxToLoad = Math.min(weapon.getMaxBulletsPerReload(), weapon.getAmmoCapacity() - currentAmmo);
      ItemStack consumedStack = InventoryUtils.tryConsumingCompatibleItem(compatibleBullets, maxToLoad, player, stack -> true);
      if (consumedStack != null) {
         int ammo = currentAmmo + consumedStack.field_77994_a;
         ItemInstance.setAmmo(weaponStack, ammo);
         instance.setAmmo(ammo);
         if (weapon.hasIteratedLoad()) {
            instance.setLoadIterationCount(consumedStack.field_77994_a);
         }

         ItemInstance.toStack(weaponStack, instance);
         player.field_70170_p.func_85173_a(player, weapon.getReloadSound(), 1.0F, 1.0F);
      }
   }

   private void loadWithGenericAmmo(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon, ItemWeaponInstance instance) {
      if (player.field_71071_by.func_146026_a(weapon.getAmmo())) {
         ItemInstance.setAmmo(weaponStack, weapon.getAmmoCapacity());
         instance.setAmmo(weapon.getAmmoCapacity());
         ItemInstance.toStack(weaponStack, instance);
         player.field_70170_p.func_85173_a(player, weapon.getReloadSound(), 1.0F, 1.0F);
      }
   }

   private void processWeaponUnload(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
      if (weaponStack.func_77973_b() instanceof ItemWeapon) {
         ItemWeapon weapon = (ItemWeapon)weaponStack.func_77973_b();
         ItemWeaponInstance instance = this.getOrCreateInstance(player, weaponStack, slotIndex);
         if (instance != null) {
            int[] activeAttachmentIds = instance.getActiveAttachmentIds();
            int magazineAttachmentId = activeAttachmentIds[AttachmentCategory.MAGAZINE.ordinal()];
            ItemAttachment currentMagazine = null;
            if (magazineAttachmentId > 0) {
               currentMagazine = (ItemAttachment)Item.func_150899_d(magazineAttachmentId);
            }

            if (currentMagazine instanceof ItemMagazine) {
               ItemMagazine magazine = (ItemMagazine)currentMagazine;
               ItemStack magazineItemStack = magazine.createItemStack();
               ItemInstance.setAmmo(magazineItemStack, instance.getAmmo());
               player.field_71071_by.func_70441_a(magazineItemStack);
               activeAttachmentIds[AttachmentCategory.MAGAZINE.ordinal()] = -1;
               instance.setActiveAttachmentIds(activeAttachmentIds);
            }

            ItemInstance.setAmmo(weaponStack, 0);
            instance.setAmmo(0);
            ItemInstance.toStack(weaponStack, instance);
            player.field_70170_p.func_85173_a(player, weapon.getUnloadSound(), 1.0F, 1.0F);
         }
      }
   }

   private void processMagazineLoad(EntityPlayerMP player, ItemStack magazineStack) {
      if (magazineStack.func_77973_b() instanceof ItemMagazine) {
         ItemMagazine magazine = (ItemMagazine)magazineStack.func_77973_b();
         List<ItemBullet> compatibleBullets = magazine.getCompatibleBullets();
         int currentAmmo = ItemInstance.getAmmo(magazineStack);
         int maxToLoad = magazine.getAmmo() - currentAmmo;
         ItemStack consumedStack = InventoryUtils.tryConsumingCompatibleItem(compatibleBullets, maxToLoad, player, stack -> true);
         if (consumedStack != null) {
            ItemInstance.setAmmo(magazineStack, currentAmmo + consumedStack.field_77994_a);
            if (magazine.getReloadSound() != null) {
               player.func_85030_a(magazine.getReloadSound(), 1.0F, 1.0F);
            }
         }
      }
   }

   private void processChangeAttachment(EntityPlayerMP player, ItemStack weaponStack, byte categoryOrdinal, int slotIndex) {
      if (weaponStack.func_77973_b() instanceof ItemWeapon) {
         ItemWeapon weapon = (ItemWeapon)weaponStack.func_77973_b();
         if (categoryOrdinal >= 0 && categoryOrdinal < AttachmentCategory.VALUES.length) {
            AttachmentCategory attachmentCategory = AttachmentCategory.VALUES[categoryOrdinal];
            ItemWeaponInstance instance = this.getOrCreateInstance(player, weaponStack, slotIndex);
            if (instance != null) {
               int[] originalActiveAttachmentIds = instance.getActiveAttachmentIds();
               int[] activeAttachmentIds = Arrays.copyOf(originalActiveAttachmentIds, originalActiveAttachmentIds.length);
               int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];
               ItemAttachment currentAttachment = null;
               if (activeAttachmentIdForThisCategory > 0) {
                  currentAttachment = (ItemAttachment)Item.func_150899_d(activeAttachmentIdForThisCategory);
               }

               if (currentAttachment != null) {
                  CompatibleAttachment currentCompatibleAttachment = weapon.getCompatibleAttachments().get(currentAttachment);
                  if (currentCompatibleAttachment != null && currentCompatibleAttachment.isPermanent()) {
                     return;
                  }
               }

               int foundSlot = -1;
               CompatibleAttachment foundCompatibleAttachment = null;

               for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
                  ItemStack slotItemStack = player.field_71071_by.func_70301_a(i);
                  if (slotItemStack != null && slotItemStack.func_77973_b() instanceof ItemAttachment) {
                     ItemAttachment attachmentFromInventory = (ItemAttachment)slotItemStack.func_77973_b();
                     if (attachmentFromInventory.getCategory() == attachmentCategory) {
                        CompatibleAttachment compatibleAttachment = weapon.getCompatibleAttachments().get(attachmentFromInventory);
                        if (compatibleAttachment != null && attachmentFromInventory != currentAttachment) {
                           foundSlot = i;
                           foundCompatibleAttachment = compatibleAttachment;
                           break;
                        }
                     }
                  }
               }

               if (currentAttachment != null && currentAttachment.getRemoveHandler() != null) {
                  currentAttachment.getRemoveHandler().apply(currentAttachment, instance);
               }

               if (foundSlot >= 0) {
                  ItemStack slotItemStack = player.field_71071_by.func_70301_a(foundSlot);
                  ItemAttachment nextAttachment = (ItemAttachment)slotItemStack.func_77973_b();
                  if (nextAttachment != null && nextAttachment.getApplyHandler() != null) {
                     nextAttachment.getApplyHandler().apply(nextAttachment, instance);
                  } else if (foundCompatibleAttachment.getApplyHandler() != null) {
                     foundCompatibleAttachment.getApplyHandler().apply(nextAttachment, instance);
                  } else {
                     ItemAttachment.AttachmentHandler handler = weapon.getEquivalentHandler(attachmentCategory);
                     if (handler != null) {
                        handler.apply(null, instance);
                     }
                  }

                  InventoryUtils.consumeInventoryItemFromSlot(player, foundSlot);
                  activeAttachmentIds[attachmentCategory.ordinal()] = Item.func_150891_b(nextAttachment);
               } else {
                  activeAttachmentIds[attachmentCategory.ordinal()] = -1;
                  ItemAttachment.AttachmentHandler handler = weapon.getEquivalentHandler(attachmentCategory);
                  if (handler != null) {
                     handler.apply(null, instance);
                  }
               }

               if (currentAttachment != null) {
                  InventoryUtils.addItemToPlayerInventory(player, currentAttachment, foundSlot);
               }

               instance.setActiveAttachmentIds(activeAttachmentIds);
               ItemInstance.toStack(weaponStack, instance);
            }
         }
      }
   }

   private void processChangeFireMode(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
      if (weaponStack.func_77973_b() instanceof ItemWeapon) {
         ItemWeapon weapon = (ItemWeapon)weaponStack.func_77973_b();
         ItemWeaponInstance instance = this.getOrCreateInstance(player, weaponStack, slotIndex);
         if (instance != null) {
            List<Integer> modes = weapon.getMaxShots();
            if (modes.size() > 1) {
               int currentIndex = modes.indexOf(instance.getMaxShots());
               int nextIndex = (currentIndex + 1) % modes.size();
               instance.setMaxShots(modes.get(nextIndex));
               ItemInstance.toStack(weaponStack, instance);
            }
         }
      }
   }

   private void processToggleLaser(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
      ItemWeaponInstance instance = this.getOrCreateInstance(player, weaponStack, slotIndex);
      if (instance != null) {
         instance.setLaserOn(!instance.isLaserOn());
         ItemInstance.toStack(weaponStack, instance);
      }
   }

   private void processZoom(EntityPlayerMP player, ItemStack weaponStack, boolean zoomIn, int slotIndex) {
      if (weaponStack.func_77973_b() instanceof ItemWeapon) {
         ItemWeaponInstance instance = this.getOrCreateInstance(player, weaponStack, slotIndex);
         if (instance != null) {
            Item scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
            if (scopeItem instanceof ItemScope && ((ItemScope)scopeItem).isOptical()) {
               float minZoom = ((ItemScope)scopeItem).getMinZoom();
               float maxZoom = ((ItemScope)scopeItem).getMaxZoom();
               float increment = (minZoom - maxZoom) / 20.0F;
               float zoom = instance.getZoom();
               if (zoomIn) {
                  if (zoom > maxZoom) {
                     zoom = Math.max(zoom - increment, maxZoom);
                  }
               } else if (zoom < minZoom) {
                  zoom = Math.min(zoom + increment, minZoom);
               }

               instance.setZoom(zoom);
               ItemInstance.toStack(weaponStack, instance);
            }
         }
      }
   }
}
