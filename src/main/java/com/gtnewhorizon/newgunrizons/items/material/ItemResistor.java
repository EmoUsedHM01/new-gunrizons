package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemResistor extends Item {

    public ItemResistor() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_Resistor");
        this.setTextureName("newgunrizons:resistor");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
