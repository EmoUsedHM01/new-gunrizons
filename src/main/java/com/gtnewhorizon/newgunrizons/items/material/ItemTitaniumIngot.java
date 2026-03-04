package com.gtnewhorizon.newgunrizons.items.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemTitaniumIngot extends Item {

    public ItemTitaniumIngot() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_TitaniumIngot");
        this.setTextureName("newgunrizons:titaniumingot");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
