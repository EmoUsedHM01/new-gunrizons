package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemElectronics extends Item {

    public ItemElectronics() {
        this.setMaxStackSize(32);
        this.setUnlocalizedName("newgunrizons_Electronics");
        this.setTextureName("newgunrizons:electronics");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
