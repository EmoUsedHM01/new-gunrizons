package com.gtnewhorizon.newgunrizons.client.handlers;

import com.gtnewhorizon.newgunrizons.client.particle.ParticleManager;
import com.gtnewhorizon.newgunrizons.items.Updatable;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ClientTickHandler {
   private static final AttributeModifier SLOW_DOWN_WHILE_ZOOMING = new AttributeModifier(UUID.randomUUID(), "Slow Down While Zooming", -0.5, 2)
      .func_111168_a(false);

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onClientTick(ClientTickEvent event) {
      if (event.phase == Phase.START) {
         ParticleManager.tickSmoke();
         EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
         if (player != null) {
            Item item = getHeldItem(player);
            if (item instanceof Updatable) {
               ((Updatable)item).update(player);
            }
         }
      } else if (event.phase == Phase.END) {
         EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
         ItemInstanceRegistry.INSTANCE.update(player);
         ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
         if (weaponInstance != null) {
            if (player.func_70051_ag()) {
               weaponInstance.setAimed(false);
            }

            if (weaponInstance.isAimed()) {
               this.slowPlayerDown(player);
            } else {
               this.restorePlayerSpeed(player);
            }
         } else if (player != null) {
            this.restorePlayerSpeed(player);
         }
      }
   }

   private void restorePlayerSpeed(EntityPlayer player) {
      if (player.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111127_a(SLOW_DOWN_WHILE_ZOOMING.func_111167_a()) != null) {
         player.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111124_b(SLOW_DOWN_WHILE_ZOOMING);
      }
   }

   private void slowPlayerDown(EntityPlayer player) {
      if (player.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111127_a(SLOW_DOWN_WHILE_ZOOMING.func_111167_a()) == null) {
         player.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111121_a(SLOW_DOWN_WHILE_ZOOMING);
      }
   }

   private static Item getHeldItem(EntityPlayer player) {
      ItemStack itemStack = player.func_70694_bm();
      return itemStack != null ? itemStack.func_77973_b() : null;
   }
}
