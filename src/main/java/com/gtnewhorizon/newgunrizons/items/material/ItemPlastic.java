package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemPlastic extends Item {

    public ItemPlastic() {
        this.setMaxStackSize(16);
        this.setUnlocalizedName("newgunrizons_Plastic");
        this.setTextureName("newgunrizons:plastic");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
