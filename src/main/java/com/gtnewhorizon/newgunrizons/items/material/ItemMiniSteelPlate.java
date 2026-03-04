package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemMiniSteelPlate extends Item {

    public ItemMiniSteelPlate() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_MiniSteelPlate");
        this.setTextureName("newgunrizons:ministeelplate");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
