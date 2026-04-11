package com.gtnewhorizon.newgunrizons.client.handlers;

import com.gtnewhorizon.newgunrizons.client.particle.ParticleManager;
import com.gtnewhorizon.newgunrizons.client.scope.ScopeManager;
import com.gtnewhorizon.newgunrizons.client.scope.ScopePerspective;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderContext;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderManager;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderPhase;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;

public class WeaponRenderHandler {
   private final ShaderManager shaderManager = new ShaderManager();

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderTick(RenderTickEvent event) {
      Minecraft minecraft = Minecraft.func_71410_x();
      ShaderContext shaderContext = new ShaderContext(ShaderPhase.WORLD_RENDER, minecraft.field_71460_t, minecraft.func_147110_a(), event.renderTickTime);
      EntityPlayer player = minecraft.field_71439_g;
      if (event.phase == Phase.START) {
         if (player != null) {
            ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
            if (weaponInstance != null) {
               if (weaponInstance.isAimed()) {
                  ScopePerspective view = ScopeManager.INSTANCE.getPerspective(weaponInstance, true);
                  if (view != null) {
                     view.update(event, weaponInstance);
                  }
               } else {
                  ScopeManager.INSTANCE.getPerspective(weaponInstance, true);
               }
            }
         }
      } else if (event.phase == Phase.END) {
         ScopePerspective.isRenderingScope = false;
         this.shaderManager.removeStaleShaders(shaderContext);
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderHand(RenderHandEvent event) {
      if (ScopePerspective.isRenderingScope) {
         event.setCanceled(true);
      } else {
         Minecraft minecraft = Minecraft.func_71410_x();
         if (minecraft.field_71474_y.field_74320_O == 0) {
            ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
            ShaderContext shaderContext = new ShaderContext(ShaderPhase.ITEM_RENDER, null, minecraft.func_147110_a(), event.partialTicks);
            this.shaderManager.applyShader(shaderContext, weaponInstance);
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onFovUpdate(FOVUpdateEvent event) {
      ItemWeaponInstance instance = ItemInstanceRegistry.getMainHeldWeapon();
      EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
      if (instance != null) {
         float fov;
         if (instance.isAttachmentZoomEnabled()) {
            if (ScopePerspective.isRenderingScope) {
               fov = instance.getZoom();
            } else {
               fov = player.field_71075_bZ.field_75100_b ? 1.1F : 1.0F;
            }
         } else {
            fov = player.field_71075_bZ.field_75100_b ? 1.1F : 1.0F;
         }

         event.newfov = fov;
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderLiving(Pre event) {
      if (!event.isCanceled() && event.entity instanceof EntityPlayer) {
         ItemStack itemStack = event.entity.func_70694_bm();
         if (itemStack != null && itemStack.func_77973_b() instanceof ItemWeapon) {
            RenderPlayer rp = (RenderPlayer)event.renderer;
            ItemInstance<?> instance = ItemInstanceRegistry.INSTANCE.getItemInstance(event.entity, itemStack);
            if (instance instanceof ItemWeaponInstance) {
               ItemWeaponInstance weaponInstance = (ItemWeaponInstance)instance;
               rp.field_77109_a.field_78118_o = weaponInstance.isAimed()
                  || weaponInstance.getState() == WeaponState.FIRING
                  || weaponInstance.getState() == WeaponState.RECOILED
                  || weaponInstance.getState() == WeaponState.PAUSED;
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onRenderWorldLast(RenderWorldLastEvent event) {
      ParticleManager.renderSmoke(event.partialTicks);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onTextureStitch(net.minecraftforge.client.event.TextureStitchEvent.Pre event) {
      event.map.func_94245_a(new ResourceLocation("newgunrizons", "particle/blood").toString());
   }
}
