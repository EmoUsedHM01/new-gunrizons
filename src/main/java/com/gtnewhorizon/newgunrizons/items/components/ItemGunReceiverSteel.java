package com.gtnewhorizon.newgunrizons.items.components;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.item.Item;

public class ItemGunReceiverSteel extends Item {
   public ItemGunReceiverSteel() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("newgunrizons_GunReceiverSteel");
      this.setTextureName("newgunrizons:gun_receiver_steel");
      this.setCreativeTab(NewGunrizonsMod.gunsTab);
   }
}
