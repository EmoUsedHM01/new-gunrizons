package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemGunBarrelTungstenSteel extends Item {

    public ItemGunBarrelTungstenSteel() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_GunBarrelTungstenSteel");
        this.setTextureName("newgunrizons:gun_barrel_tungstensteel");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
