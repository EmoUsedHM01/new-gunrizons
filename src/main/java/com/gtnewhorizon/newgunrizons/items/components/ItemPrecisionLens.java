package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemPrecisionLens extends Item {
   public ItemPrecisionLens() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_PrecisionLens");
      this.setTextureName("newgunrizons:precision_lens");
      this.setCreativeTab(NewGunrizonsMod.AttachmentsTab);
   }
}
