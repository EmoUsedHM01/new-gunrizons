package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemRuby extends Item {

    public ItemRuby() {
        this.setMaxStackSize(16);
        this.setUnlocalizedName("newgunrizons_Ruby");
        this.setTextureName("newgunrizons:ruby");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
