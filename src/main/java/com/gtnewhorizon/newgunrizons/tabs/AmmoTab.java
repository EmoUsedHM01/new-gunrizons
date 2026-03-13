package com.gtnewhorizon.newgunrizons.tabs;

import java.util.Comparator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.registry.Bullets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AmmoTab extends CreativeTabs {

    private static final Comparator<ItemStack> SORT_BY_TYPE = (a, b) -> {
        int typeA = getTypeSortOrder(a.getItem());
        int typeB = getTypeSortOrder(b.getItem());
        if (typeA != typeB) return Integer.compare(typeA, typeB);
        return a.getDisplayName()
            .compareToIgnoreCase(b.getDisplayName());
    };

    private static int getTypeSortOrder(Item item) {
        if (item instanceof ItemBullet) return 0;
        return 1;
    }

    public AmmoTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void displayAllReleventItems(List items) {
        super.displayAllReleventItems(items);
        items.sort(SORT_BY_TYPE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Bullets.ShotgunShell;
    }
}
