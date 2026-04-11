package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemBulletCasingSmall extends Item {
   public ItemBulletCasingSmall() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_BulletCasingSmall");
      this.setTextureName("newgunrizons:bullet_casing_small");
      this.setCreativeTab(NewGunrizonsMod.AmmoTab);
   }
}
