package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemAluminumIngot extends Item {

    public ItemAluminumIngot() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_AluminumIngot");
        this.setTextureName("newgunrizons:aluminumingot");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
