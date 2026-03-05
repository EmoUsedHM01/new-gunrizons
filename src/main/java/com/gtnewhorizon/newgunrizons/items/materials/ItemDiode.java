package com.gtnewhorizon.newgunrizons.items.materials;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemDiode extends Item {

    public ItemDiode() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_Diode");
        this.setTextureName("newgunrizons:diode");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
