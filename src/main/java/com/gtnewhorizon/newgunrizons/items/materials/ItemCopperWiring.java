package com.gtnewhorizon.newgunrizons.items.materials;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemCopperWiring extends Item {

    public ItemCopperWiring() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_CopperWiring");
        this.setTextureName("newgunrizons:copperwiring");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
