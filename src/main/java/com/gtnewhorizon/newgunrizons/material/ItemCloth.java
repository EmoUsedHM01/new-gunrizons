package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemCloth extends Item {

    public ItemCloth() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_Cloth");
        this.setTextureName("newgunrizons:cloth");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
