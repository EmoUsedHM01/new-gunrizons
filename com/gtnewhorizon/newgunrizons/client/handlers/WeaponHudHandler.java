package com.gtnewhorizon.newgunrizons.client.handlers;

import com.gtnewhorizon.newgunrizons.client.render.HudRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class WeaponHudHandler {
   private final HudRenderer hudRenderer = new HudRenderer();

   @SubscribeEvent
   public void onRenderCrosshair(Pre event) {
      if (event.type == ElementType.CROSSHAIRS) {
         ItemStack itemStack = Minecraft.func_71410_x().field_71439_g.func_70694_bm();
         if (itemStack != null) {
            ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
            if (weaponInstance != null) {
               if (this.hudRenderer.renderWeaponHud(event.resolution, itemStack, weaponInstance)) {
                  event.setCanceled(true);
               }
            } else if (itemStack.func_77973_b() instanceof ItemMagazine) {
               this.hudRenderer.renderMagazineHud(event.resolution, itemStack);
               event.setCanceled(true);
            } else if (itemStack.func_77973_b() instanceof ItemGrenade && this.hudRenderer.renderGrenadeHud(event.resolution)) {
               event.setCanceled(true);
            }
         }
      }
   }
}
