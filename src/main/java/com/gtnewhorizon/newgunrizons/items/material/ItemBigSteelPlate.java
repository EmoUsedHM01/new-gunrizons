package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemBigSteelPlate extends Item {

    public ItemBigSteelPlate() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_BigSteelPlate");
        this.setTextureName("newgunrizons:bigsteelplate");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
