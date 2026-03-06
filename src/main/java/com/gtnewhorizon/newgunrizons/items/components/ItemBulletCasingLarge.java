package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemBulletCasingLarge extends Item {

    public ItemBulletCasingLarge() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_BulletCasingLarge");
        this.setTextureName("newgunrizons:bullet_casing_large");
        this.setCreativeTab(NewGunrizonsMod.AmmoTab);
    }
}
