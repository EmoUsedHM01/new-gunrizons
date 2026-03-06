package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemWeaponPartKit extends Item {

    public ItemWeaponPartKit() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_WeaponPartKit");
        this.setTextureName("newgunrizons:weapon_part_kit");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
