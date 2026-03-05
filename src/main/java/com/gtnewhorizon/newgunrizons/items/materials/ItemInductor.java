package com.gtnewhorizon.newgunrizons.items.materials;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemInductor extends Item {

    public ItemInductor() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_Inductor");
        this.setTextureName("newgunrizons:inductor");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
