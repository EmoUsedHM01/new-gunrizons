package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemBulletCasingMedium extends Item {
   public ItemBulletCasingMedium() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_BulletCasingMedium");
      this.setTextureName("newgunrizons:bullet_casing_medium");
      this.setCreativeTab(NewGunrizonsMod.AmmoTab);
   }
}
