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
            Minecraft.getMinecraft().thePlayer.worldObj, message.getPosX(), message.getPosY() + 1.0, message.getPosZ(), Items.redstone
         );
         TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation("newgunrizons", "particle/blood").toString());
         particle.setParticleIcon(sprite);
         Minecraft.getMinecraft().effectRenderer.addEffect(particle);
      }
   }

   private void spawnWaterSplash(SpawnParticleMessage message) {
      World world = Minecraft.getMinecraft().thePlayer.worldObj;
      Random rand = world.rand;
      double x = message.getPosX();
      double y = message.getPosY();
      double z = message.getPosZ();

      for (int i = 0; i < message.getCount(); i++) {
         world.spawnParticle(
            "splash",
            x + (rand.nextDouble() - 0.5) * 0.3,
            y + 0.1,
            z + (rand.nextDouble() - 0.5) * 0.3,
            (rand.nextDouble() - 0.5) * 0.2,
            0.15 + rand.nextDouble() * 0.25,
            (rand.nextDouble() - 0.5) * 0.2
         );
      }

      world.playSoundEffect(x, y, z, "random.splash", 0.3F, 1.5F + rand.nextFloat() * 0.4F);
   }
}
