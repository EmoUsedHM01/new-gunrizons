package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemTinIngot extends Item {

    public ItemTinIngot() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_TinIngot");
        this.setTextureName("newgunrizons:tiningot");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
