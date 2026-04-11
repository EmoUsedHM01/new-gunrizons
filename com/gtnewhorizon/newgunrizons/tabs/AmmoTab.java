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
      int typeA = getTypeSortOrder(a.func_77973_b());
      int typeB = getTypeSortOrder(b.func_77973_b());
      return typeA != typeB ? Integer.compare(typeA, typeB) : a.func_82833_r().compareToIgnoreCase(b.func_82833_r());
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
   public void func_78018_a(List items) {
      super.func_78018_a(items);
      items.sort(SORT_BY_TYPE);
   }

   @SideOnly(Side.CLIENT)
   public Item func_78016_d() {
      return Magazines.NATOMag1;
   }
}
