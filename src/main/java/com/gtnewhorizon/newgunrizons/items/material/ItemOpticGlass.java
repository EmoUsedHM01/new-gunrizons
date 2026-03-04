package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemOpticGlass extends Item {

    public ItemOpticGlass() {
        this.setMaxStackSize(16);
        this.setUnlocalizedName("newgunrizons_OpticGlass");
        this.setTextureName("newgunrizons:opticglass");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
