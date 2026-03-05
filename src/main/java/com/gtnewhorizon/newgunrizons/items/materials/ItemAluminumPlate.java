package com.gtnewhorizon.newgunrizons.items.materials;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemAluminumPlate extends Item {

    public ItemAluminumPlate() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_AluminumPlate");
        this.setTextureName("newgunrizons:aluminumplate");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
