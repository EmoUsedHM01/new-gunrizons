package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemCapacitor extends Item {

    public ItemCapacitor() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_Capacitor");
        this.setTextureName("newgunrizons:capacitor");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
