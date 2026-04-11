package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemGunStockPlastic extends Item {
   public ItemGunStockPlastic() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_GunStockPlastic");
      this.setTextureName("newgunrizons:gun_stock_plastic");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
