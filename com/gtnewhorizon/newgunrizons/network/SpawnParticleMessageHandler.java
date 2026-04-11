package com.gtnewhorizon.newgunrizons.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SpawnParticleMessageHandler implements IMessageHandler<SpawnParticleMessage, IMessage> {
   private static final double Y_OFFSET = 1.0;

   public IMessage onMessage(SpawnParticleMessage message, MessageContext ctx) {
      if (ctx.side != Side.CLIENT) {
         return null;
      } else {
         switch (message.getParticleType()) {
            case BLOOD:
               this.spawnBloodParticles(message);
               break;
            case WATER_SPLASH:
               this.spawnWaterSplash(message);
         }

         return null;
      }
   }

   private void spawnBloodParticles(SpawnParticleMessage message) {
      for (int i = 0; i < message.getCount(); i++) {
         EntityBreakingFX particle = new EntityBreakingFX(
            Minecraft.func_71410_x().field_71439_g.field_70170_p, message.getPosX(), message.getPosY() + 1.0, message.getPosZ(), Items.field_151137_ax
         );
         TextureAtlasSprite sprite = Minecraft.func_71410_x().func_147117_R().func_110572_b(new ResourceLocation("newgunrizons", "particle/blood").toString());
         particle.func_110125_a(sprite);
         Minecraft.func_71410_x().field_71452_i.func_78873_a(particle);
      }
   }

   private void spawnWaterSplash(SpawnParticleMessage message) {
      World world = Minecraft.func_71410_x().field_71439_g.field_70170_p;
      Random rand = world.field_73012_v;
      double x = message.getPosX();
      double y = message.getPosY();
      double z = message.getPosZ();

      for (int i = 0; i < message.getCount(); i++) {
         world.func_72869_a(
            "splash",
            x + (rand.nextDouble() - 0.5) * 0.3,
            y + 0.1,
            z + (rand.nextDouble() - 0.5) * 0.3,
            (rand.nextDouble() - 0.5) * 0.2,
            0.15 + rand.nextDouble() * 0.25,
            (rand.nextDouble() - 0.5) * 0.2
         );
      }

      world.func_72908_a(x, y, z, "random.splash", 0.3F, 1.5F + rand.nextFloat() * 0.4F);
   }
}
