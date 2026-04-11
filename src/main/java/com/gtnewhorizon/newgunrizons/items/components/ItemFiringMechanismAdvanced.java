package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemFiringMechanismAdvanced extends Item {
   public ItemFiringMechanismAdvanced() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_FiringMechanismAdvanced");
      this.setTextureName("newgunrizons:firing_mechanism_advanced");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
