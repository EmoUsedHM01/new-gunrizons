package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemPiston extends Item {

    public ItemPiston() {
        this.setMaxStackSize(16);
        this.setUnlocalizedName("newgunrizons_Piston");
        this.setTextureName("newgunrizons:piston");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
