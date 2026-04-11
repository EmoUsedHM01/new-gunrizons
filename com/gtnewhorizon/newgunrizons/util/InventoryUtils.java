package com.gtnewhorizon.newgunrizons.util;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtils {
   private static int itemSlotIndex(Item item, Predicate<ItemStack> condition, EntityPlayer player) {
      for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
         if (player.field_71071_by.field_70462_a[i] != null
            && player.field_71071_by.field_70462_a[i].func_77973_b() == item
            && condition.test(player.field_71071_by.field_70462_a[i])) {
            return i;
         }
      }

      return -1;
   }

   public static ItemStack consumeInventoryItem(Item item, Predicate<ItemStack> condition, EntityPlayer player, int maxSize) {
      if (maxSize <= 0) {
         return null;
      } else {
         int i = itemSlotIndex(item, condition, player);
         if (i < 0) {
            return null;
         } else {
            ItemStack stackInSlot = player.field_71071_by.field_70462_a[i];
            int consumedStackSize = Math.min(maxSize, stackInSlot.field_77994_a);
            ItemStack result = stackInSlot.func_77979_a(consumedStackSize);
            if (stackInSlot.field_77994_a <= 0) {
               player.field_71071_by.field_70462_a[i] = null;
            }

            return result;
         }
      }
   }

   @SafeVarargs
   public static ItemStack tryConsumingCompatibleItem(
      List<? extends Item> compatibleParts, int maxSize, EntityPlayer player, Predicate<ItemStack>... conditions
   ) {
      ItemStack resultStack = null;

      for (Predicate<ItemStack> condition : conditions) {
         for (Item item : compatibleParts) {
            if ((resultStack = consumeInventoryItem(item, condition, player, maxSize)) != null) {
               break;
            }
         }

         if (resultStack != null) {
            break;
         }
      }

      return resultStack;
   }

   public static void consumeInventoryItemFromSlot(EntityPlayer player, int slot) {
      if (player.field_71071_by.field_70462_a[slot] != null) {
         if (--player.field_71071_by.field_70462_a[slot].field_77994_a <= 0) {
            player.field_71071_by.field_70462_a[slot] = null;
         }
      }
   }

   public static void addItemToPlayerInventory(EntityPlayer player, Item item, int slot) {
      if (slot == -1) {
         player.field_71071_by.func_70441_a(new ItemStack(item));
      } else if (player.field_71071_by.field_70462_a[slot] == null) {
         player.field_71071_by.field_70462_a[slot] = new ItemStack(item);
      }
   }

   public static boolean inventoryHasFreeSlots(EntityPlayer player) {
      for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
         if (player.field_71071_by.field_70462_a[i] == null) {
            return true;
         }
      }

      return false;
   }

   @SafeVarargs
   public static boolean hasCompatibleItem(List<? extends Item> compatibleParts, EntityPlayer player, Predicate<ItemStack>... conditions) {
      for (Predicate<ItemStack> condition : conditions) {
         for (Item item : compatibleParts) {
            if (itemSlotIndex(item, condition, player) >= 0) {
               return true;
            }
         }
      }

      return false;
   }

   public static int getInventorySlot(EntityPlayer player, ItemStack itemStack) {
      for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
         if (player.field_71071_by.field_70462_a[i] == itemStack) {
            return i;
         }
      }

      return -1;
   }
}
