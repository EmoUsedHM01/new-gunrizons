package com.gtnewhorizon.newgunrizons.material;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

public class ItemLeadIngot extends Item {

    public ItemLeadIngot() {
        this.setMaxStackSize(64);
        this.setUnlocalizedName("newgunrizons_LeadIngot");
        this.setTextureName("newgunrizons:leadingot");
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }
}
