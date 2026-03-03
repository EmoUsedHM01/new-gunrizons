package com.gtnewhorizon.newgunrizons.tab;

import java.util.Comparator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.ItemAttachment;
import com.gtnewhorizon.newgunrizons.registry.Attachments;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AttachmentsTab extends CreativeTabs {

    private static final Comparator<ItemStack> SORT_BY_CATEGORY = (a, b) -> {
        int catA = getCategorySortOrder(a.getItem());
        int catB = getCategorySortOrder(b.getItem());
        if (catA != catB) return Integer.compare(catA, catB);
        return a.getDisplayName().compareToIgnoreCase(b.getDisplayName());
    };

    private static int getCategorySortOrder(Item item) {
        if (item instanceof ItemAttachment att) {
            return switch (att.getCategory()) {
                case SCOPE -> 0;
                case SILENCER -> 1;
                case GRIP -> 2;
                default -> 3;
            };
        }
        return 4;
    }

    public AttachmentsTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void displayAllReleventItems(List items) {
        super.displayAllReleventItems(items);
        items.sort(SORT_BY_CATEGORY);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Attachments.Leupold;
    }
}
