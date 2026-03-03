package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemSteelPlate extends Item {

    public ItemSteelPlate() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_SteelPlate");
        this.setTextureName("newgunrizons:steelplate");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
