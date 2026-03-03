package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemTanCloth extends Item {

    public ItemTanCloth() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_TanCloth");
        this.setTextureName("newgunrizons:tancloth");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
