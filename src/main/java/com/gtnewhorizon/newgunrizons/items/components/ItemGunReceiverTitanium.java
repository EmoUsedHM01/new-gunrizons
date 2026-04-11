package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemGunReceiverTitanium extends Item {
   public ItemGunReceiverTitanium() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_GunReceiverTitanium");
      this.setTextureName("newgunrizons:gun_receiver_titanium");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
