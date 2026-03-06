package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemGunStockWood extends Item {

    public ItemGunStockWood() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_GunStockWood");
        this.setTextureName("newgunrizons:gun_stock_wood");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
