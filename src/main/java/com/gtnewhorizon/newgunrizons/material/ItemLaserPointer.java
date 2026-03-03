package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemLaserPointer extends Item {

    public ItemLaserPointer() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_LaserPointer");
        this.setTextureName("newgunrizons:laserpointer");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
