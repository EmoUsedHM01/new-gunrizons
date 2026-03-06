package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemGunReceiverTitanium extends Item {

    public ItemGunReceiverTitanium() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_GunReceiverTitanium");
        this.setTextureName("newgunrizons:gun_receiver_titanium");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
