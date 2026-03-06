package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemGunStockCarbon extends Item {

    public ItemGunStockCarbon() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_GunStockCarbon");
        this.setTextureName("newgunrizons:gun_stock_carbon");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
