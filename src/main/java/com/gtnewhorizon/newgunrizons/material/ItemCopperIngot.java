package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemCopperIngot extends Item {

    public ItemCopperIngot() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_CopperIngot");
        this.setTextureName("newgunrizons:copperingot");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
