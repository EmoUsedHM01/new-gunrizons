package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemTransistor extends Item {

    public ItemTransistor() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_Transistor");
        this.setTextureName("newgunrizons:transistor");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
