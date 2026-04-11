package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemGunBarrelTitanium extends Item {
   public ItemGunBarrelTitanium() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_GunBarrelTitanium");
      this.setTextureName("newgunrizons:gun_barrel_titanium");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
