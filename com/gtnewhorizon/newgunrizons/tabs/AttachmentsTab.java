package com.gtnewhorizon.newgunrizons.tabs;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Comparator;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AttachmentsTab extends CreativeTabs {
   private static final Comparator<ItemStack> SORT_BY_CATEGORY = (a, b) -> {
      int catA = getCategorySortOrder(a.func_77973_b());
      int catB = getCategorySortOrder(b.func_77973_b());
      return catA != catB ? Integer.compare(catA, catB) : a.func_82833_r().compareToIgnoreCase(b.func_82833_r());
   };

   private static int getCategorySortOrder(Item item) {
      if (item instanceof ItemAttachment) {
         ItemAttachment att = (ItemAttachment)item;
         switch (att.getCategory()) {
            case SCOPE:
               return 0;
            case SILENCER:
               return 1;
            case GRIP:
               return 2;
            default:
               return 3;
         }
      } else {
         return 4;
      }
   }

   public AttachmentsTab(int par1, String par2Str) {
      super(par1, par2Str);
   }

   @SideOnly(Side.CLIENT)
   public void func_78018_a(List items) {
      super.func_78018_a(items);
      items.sort(SORT_BY_CATEGORY);
   }

   @SideOnly(Side.CLIENT)
   public Item func_78016_d() {
      return Attachments.Leupold;
   }
}
