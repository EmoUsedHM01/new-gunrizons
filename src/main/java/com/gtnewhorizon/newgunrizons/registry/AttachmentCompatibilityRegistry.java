package com.gtnewhorizon.newgunrizons.registry;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

public class AttachmentCompatibilityRegistry {

   private static final Map<ItemAttachment, List<String>> attachmentToGunNames = new HashMap<>();

   public static void buildReverseMap() {
      attachmentToGunNames.clear();

      for (java.lang.reflect.Field field : Guns.class.getDeclaredFields()) {
         try {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) && field.getType() == Item.class) {
               Item item = (Item) field.get(null);
               if (item instanceof ItemWeapon) {
                  ItemWeapon weapon = (ItemWeapon) item;
                  String displayName = StatCollector.translateToLocal("item." + weapon.getName() + ".name");
                  for (ItemAttachment attachment : weapon.getCompatibleAttachments().keySet()) {
                     if (attachment.getCreativeTab() != null) {
                        attachmentToGunNames.computeIfAbsent(attachment, k -> new ArrayList<>()).add(displayName);
                     }
                  }
               }
            }
         } catch (IllegalAccessException e) {
            // skip
         }
      }
   }

   public static List<String> getCompatibleGunNames(ItemAttachment attachment) {
      return attachmentToGunNames.getOrDefault(attachment, Collections.emptyList());
   }
}
