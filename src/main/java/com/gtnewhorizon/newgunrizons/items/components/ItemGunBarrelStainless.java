package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemGunBarrelStainless extends Item {
   public ItemGunBarrelStainless() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_GunBarrelStainless");
      this.setTextureName("newgunrizons:gun_barrel_stainless");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
