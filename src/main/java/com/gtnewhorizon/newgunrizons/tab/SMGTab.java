package com.gtnewhorizon.newgunrizons.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.registry.Guns;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SMGTab extends CreativeTabs {

    public SMGTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Guns.MP7;
    }
}
