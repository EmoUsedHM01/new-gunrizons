package com.gtnewhorizon.newgunrizons.items.materials;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemTantalumIngot extends Item {

    public ItemTantalumIngot() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_TantalumIngot");
        this.setTextureName("newgunrizons:tantalumingot");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
