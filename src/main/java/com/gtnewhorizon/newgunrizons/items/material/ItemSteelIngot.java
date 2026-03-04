package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemSteelIngot extends Item {

    public ItemSteelIngot() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_SteelIngot");
        this.setTextureName("newgunrizons:steelingot");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
