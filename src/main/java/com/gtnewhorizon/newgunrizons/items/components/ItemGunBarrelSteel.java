package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemGunBarrelSteel extends Item {

    public ItemGunBarrelSteel() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_GunBarrelSteel");
        this.setTextureName("newgunrizons:gun_barrel_steel");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
