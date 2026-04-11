package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemFiringMechanism extends Item {
   public ItemFiringMechanism() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_FiringMechanism");
      this.setTextureName("newgunrizons:firing_mechanism");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
