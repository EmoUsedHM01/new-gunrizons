package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemMetalComponents extends Item {

    public ItemMetalComponents() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_MetalComponents");
        this.setTextureName("newgunrizons:metalcomponents");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
