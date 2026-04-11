package com.gtnewhorizon.newgunrizons.items.instances;

import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.weapon.MagazineState;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ItemMagazineInstance extends ItemInstance<MagazineState> {
   public ItemMagazineInstance() {
   }

   public ItemMagazineInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
      super(itemInventoryIndex, player, itemStack);
   }

   public ItemMagazineInstance(int itemInventoryIndex, EntityLivingBase player) {
      super(itemInventoryIndex, player);
   }

   @Override
   public void init(ByteBuf buf) {
      super.init(buf);
   }

   @Override
   public void serialize(ByteBuf buf) {
      super.serialize(buf);
   }

   @Override
   protected void updateWith(ItemInstance<MagazineState> otherItemInstance, boolean updateManagedState) {
      super.updateWith(otherItemInstance, updateManagedState);
   }

   public ItemMagazine getMagazine() {
      return (ItemMagazine)this.item;
   }

   static {
      TypeRegistry.getInstance().register(ItemMagazineInstance.class);
   }
}
