package com.gtnewhorizon.newgunrizons.items.materials;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemSulfurDust extends Item {

    public ItemSulfurDust() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_SulfurDust");
        this.setTextureName("newgunrizons:sulfurdust");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
