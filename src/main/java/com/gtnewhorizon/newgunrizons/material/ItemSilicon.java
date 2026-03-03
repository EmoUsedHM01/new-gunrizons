package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemSilicon extends Item {

    public ItemSilicon() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_Silicon");
        this.setTextureName("newgunrizons:silicon");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
