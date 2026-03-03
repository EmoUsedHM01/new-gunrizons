package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemGreenCloth extends Item {

    public ItemGreenCloth() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_GreenCloth");
        this.setTextureName("newgunrizons:greencloth");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
