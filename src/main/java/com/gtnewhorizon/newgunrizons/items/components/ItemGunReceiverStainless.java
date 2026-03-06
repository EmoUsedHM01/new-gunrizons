package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemGunReceiverStainless extends Item {

    public ItemGunReceiverStainless() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_GunReceiverStainless");
        this.setTextureName("newgunrizons:gun_receiver_stainless");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
