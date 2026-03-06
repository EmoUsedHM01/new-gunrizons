package com.gtnewhorizon.newgunrizons.items.components;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemFiringMechanismAdvanced extends Item {

    public ItemFiringMechanismAdvanced() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_FiringMechanismAdvanced");
        this.setTextureName("newgunrizons:firing_mechanism_advanced");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
