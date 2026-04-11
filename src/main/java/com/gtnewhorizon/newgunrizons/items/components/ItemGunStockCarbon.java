package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemGunStockCarbon extends Item {
   public ItemGunStockCarbon() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_GunStockCarbon");
      this.setTextureName("newgunrizons:gun_stock_carbon");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
