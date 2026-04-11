package com.gtnewhorizon.newgunrizons.client.handlers;

import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class WeaponTooltipHandler {

   @SubscribeEvent
   public void onItemTooltip(ItemTooltipEvent event) {
      ItemStack stack = event.itemStack;
      if (stack == null || !(stack.getItem() instanceof ItemWeapon)) {
         return;
      }

      // Collect the enchantment display names that we already added in addInformation
      NBTTagList enchTags = stack.getEnchantmentTagList();
      if (enchTags == null || enchTags.tagCount() == 0) {
         return;
      }

      Set<String> enchNames = new HashSet<>();
      for (int i = 0; i < enchTags.tagCount(); i++) {
         short id = enchTags.getCompoundTagAt(i).getShort("id");
         short lvl = enchTags.getCompoundTagAt(i).getShort("lvl");
         if (Enchantment.enchantmentsList[id] != null) {
            enchNames.add(Enchantment.enchantmentsList[id].getTranslatedName(lvl));
         }
      }

      // Remove the duplicate enchantment lines that vanilla added after our gun stats.
      // Our addInformation adds them first (indices 1..N), then gun stats, then vanilla adds them again.
      // We need to remove only the second occurrence of each enchantment line.
      Set<String> seen = new HashSet<>();
      Iterator<String> it = event.toolTip.iterator();
      while (it.hasNext()) {
         String line = it.next();
         if (enchNames.contains(line)) {
            if (seen.contains(line)) {
               it.remove();
            } else {
               seen.add(line);
            }
         }
      }
   }
}
