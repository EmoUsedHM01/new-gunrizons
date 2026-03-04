package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemSteelDust extends Item {

    public ItemSteelDust() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_SteelDust");
        this.setTextureName("newgunrizons:steeldust");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
