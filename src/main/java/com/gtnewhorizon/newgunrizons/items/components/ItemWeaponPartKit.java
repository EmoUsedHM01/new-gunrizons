package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemWeaponPartKit extends Item {
   public ItemWeaponPartKit() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_WeaponPartKit");
      this.setTextureName("newgunrizons:weapon_part_kit");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
