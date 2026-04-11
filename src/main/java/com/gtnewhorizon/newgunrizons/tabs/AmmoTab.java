package com.gtnewhorizon.newgunrizons.tabs;

import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Comparator;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AmmoTab extends CreativeTabs {
   private static final Comparator<ItemStack> SORT_BY_TYPE = (a, b) -> {
      int typeA = getTypeSortOrder(a.getItem());
      int typeB = getTypeSortOrder(b.getItem());
      return typeA != typeB ? Integer.compare(typeA, typeB) : a.getDisplayName().compareToIgnoreCase(b.getDisplayName());
   };

   private static int getTypeSortOrder(Item item) {
      if (item instanceof ItemBullet) {
         return 0;
      } else {
         return item instanceof ItemMagazine ? 1 : 2;
      }
   }

   public AmmoTab(int par1, String par2Str) {
      super(par1, par2Str);
   }

   @SideOnly(Side.CLIENT)
   public void displayAllReleventItems(List items) {
      super.displayAllReleventItems(items);
      items.sort(SORT_BY_TYPE);
   }

   @SideOnly(Side.CLIENT)
   public Item getTabIconItem() {
      return Magazines.NATOMag1;
   }
}
