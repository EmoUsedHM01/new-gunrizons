package com.gtnewhorizon.newgunrizons.items.instances;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemInstanceRegistry {
   public static final ItemInstanceRegistry INSTANCE = new ItemInstanceRegistry();
   private final Map<UUID, Map<Integer, ItemInstance<?>>> registry = new HashMap<>();
   private final WeakHashMap<ItemStack, ItemInstance<?>> itemStackCache = new WeakHashMap<>();

   @SideOnly(Side.CLIENT)
   public static ItemWeaponInstance getMainHeldWeapon() {
      EntityPlayer player = Minecraft.getMinecraft().thePlayer;
      return INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
   }

   public <T extends ItemInstance<S>, S extends ManagedState<S>> T getMainHandItemInstance(EntityPlayer player, Class<T> targetClass) {
      if (player == null) {
         return null;
      } else {
         ItemInstance<?> instance = this.getItemInstance(player, player.inventory.currentItem);
         return targetClass.isInstance(instance) ? targetClass.cast(instance) : null;
      }
   }

   public ItemInstance<?> getItemInstance(EntityPlayer player, int slot) {
      Map<Integer, ItemInstance<?>> slotInstances = this.registry.computeIfAbsent(player.getPersistentID(), p -> new HashMap<>());
      ItemInstance<?> result = slotInstances.get(slot);
      if (result == null) {
         result = this.createItemInstance(player, slot);
         if (result != null) {
            slotInstances.put(slot, result);
         }

         return result;
      } else {
         ItemStack slotItemStack = player.inventory.getStackInSlot(slot);
         if (slotItemStack != null && slotItemStack.getItem() != result.getItem()) {
            result = this.createItemInstance(player, slot);
            if (result != null) {
               slotInstances.put(slot, result);
            }
         }

         if (result != null && result.getItemInventoryIndex() != slot) {
            result.setItemInventoryIndex(slot);
         }

         if (result != null && result.getPlayer() != player) {
            result.setPlayer(player);
         }

         return result;
      }
   }

   private ItemInstance<?> createItemInstance(EntityPlayer player, int slot) {
      ItemStack itemStack = player.inventory.getStackInSlot(slot);
      if (itemStack == null) {
         return null;
      } else {
         Item item = itemStack.getItem();
         if (!(item instanceof ItemInstanceFactory)) {
            return null;
         } else {
            ItemInstance<?> result = null;

            try {
               result = ItemInstance.fromStack(itemStack);
            } catch (RuntimeException var7) {
            }

            if (result == null) {
               result = ((ItemInstanceFactory)item).createItemInstance(player, itemStack, slot);
            }

            result.setItemInventoryIndex(slot);
            result.setPlayer(player);
            return result;
         }
      }
   }

   public ItemInstance<?> getItemInstance(EntityLivingBase player, ItemStack itemStack) {
      ItemInstance<?> cached = this.itemStackCache.get(itemStack);
      if (cached != null && cached.getItem() == itemStack.getItem()) {
         return cached;
      } else {
         ItemInstance<?> instance = null;
         if (player.worldObj != null
            && player.worldObj.isRemote
            && player instanceof EntityPlayer
            && NewGunrizonsMod.proxy.isLocalPlayer(player)) {
            int slot = InventoryUtils.getInventorySlot((EntityPlayer)player, itemStack);
            if (slot >= 0) {
               instance = this.getItemInstance((EntityPlayer)player, slot);
            }
         }

         if (instance == null || instance.getItem() != itemStack.getItem()) {
            try {
               instance = ItemInstance.fromStack(itemStack);
            } catch (RuntimeException var6) {
            }
         }

         if ((instance == null || instance.getItem() != itemStack.getItem()) && itemStack.getItem() instanceof ItemInstanceFactory) {
            instance = ((ItemInstanceFactory)itemStack.getItem()).createItemInstance(player, itemStack, -1);
            instance.setPlayer(player);
         }

         if (instance != null) {
            this.itemStackCache.put(itemStack, instance);
         }

         return instance;
      }
   }

   public void update(EntityPlayer player) {
      if (player != null) {
         Map<Integer, ItemInstance<?>> slotContexts = this.registry.get(player.getPersistentID());
         if (slotContexts != null) {
            Iterator<Entry<Integer, ItemInstance<?>>> it = slotContexts.entrySet().iterator();

            while (it.hasNext()) {
               Entry<Integer, ItemInstance<?>> entry = it.next();
               ItemStack slotStack = player.inventory.getStackInSlot(entry.getKey());
               if (slotStack == null || slotStack.getItem() != entry.getValue().getItem()) {
                  it.remove();
               }
            }
         }
      }
   }
}
