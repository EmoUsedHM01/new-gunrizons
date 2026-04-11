package com.gtnewhorizon.newgunrizons.client.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ParticleManager {
   private static final List<SmokeFX> smokeParticles = new ArrayList<>();

   public static void tickSmoke() {
      Iterator<SmokeFX> it = smokeParticles.iterator();

      while (it.hasNext()) {
         SmokeFX p = it.next();
         p.func_70071_h_();
         if (p.isDead) {
            it.remove();
         }
      }
   }

   public static void renderSmoke(float partialTicks) {
      if (!smokeParticles.isEmpty()) {
         Entity view = Minecraft.getMinecraft().renderViewEntity;
         if (view != null) {
            EntityFX.interpPosX = view.lastTickPosX + (view.posX - view.lastTickPosX) * partialTicks;
            EntityFX.interpPosY = view.lastTickPosY + (view.posY - view.lastTickPosY) * partialTicks;
            EntityFX.interpPosZ = view.lastTickPosZ + (view.posZ - view.lastTickPosZ) * partialTicks;
            float rotX = ActiveRenderInfo.rotationX;
            float rotZ = ActiveRenderInfo.rotationZ;
            float rotYZ = ActiveRenderInfo.rotationYZ;
            float rotXY = ActiveRenderInfo.rotationXY;
            float rotXZ = ActiveRenderInfo.rotationXZ;
            Tessellator tessellator = Tessellator.instance;

            for (SmokeFX particle : smokeParticles) {
               particle.renderParticle(tessellator, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);
            }
         }
      }
   }

   private static double[] computeMuzzlePosition(EntityLivingBase player, float distance, float xOffset, float yOffset, float rand) {
      Vec3 look = player.getLookVec();
      double lx = look.xCoord;
      double ly = look.yCoord;
      double lz = look.zCoord;
      double horizLen = Math.sqrt(lx * lx + lz * lz);
      double rx;
      double rz;
      if (horizLen > 0.001) {
         rx = -lz / horizLen;
         rz = lx / horizLen;
      } else {
         rx = 1.0;
         rz = 0.0;
      }

      double ux = -rz * ly;
      double uz = rx * ly;
      double eyeX = player.posX;
      double eyeY = player.posY + player.getEyeHeight();
      double eyeZ = player.posZ;
      Random r = player.worldObj.rand;
      double posX = eyeX + lx * distance + rx * xOffset - ux * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
      double posY = eyeY + ly * distance - horizLen * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
      double posZ = eyeZ + lz * distance + rz * xOffset - uz * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
      return new double[]{posX, posY, posZ};
   }

   public static void spawnSmokeParticle(EntityLivingBase player, float xOffset, float yOffset) {
      double motionX = player.worldObj.rand.nextGaussian() * 0.003;
      double motionY = player.worldObj.rand.nextGaussian() * 0.003;
      double motionZ = player.worldObj.rand.nextGaussian() * 0.003;
      float distance = 0.42F;
      float scale = 2.3F;
      xOffset += -0.02F;
      yOffset += 0.1F;
      double[] pos = computeMuzzlePosition(player, distance, xOffset, yOffset, 0.01F);
      SmokeFX particle = new SmokeFX(player.worldObj, pos[0], pos[1], pos[2], scale, (float)motionX, (float)motionY, (float)motionZ);
      smokeParticles.add(particle);
   }

   public static void spawnExplosionSmoke(
      double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float scale, int maxAge, ResourceLocation textureResource
   ) {
      World world = Minecraft.getMinecraft().thePlayer.worldObj;
      ExplosionSmokeFX particle = new ExplosionSmokeFX(
         world, posX, posY, posZ, scale, (float)motionX, (float)motionY, (float)motionZ, maxAge, ExplosionSmokeFX.Behavior.SMOKE_GRENADE, textureResource
      );
      Minecraft.getMinecraft().effectRenderer.addEffect(particle);
   }

   public static void spawnExplosionParticle(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float scale, int maxAge) {
      World world = Minecraft.getMinecraft().thePlayer.worldObj;
      ExplosionParticleFX particle = new ExplosionParticleFX(world, posX, posY, posZ, scale, motionX, motionY, motionZ, maxAge);
      Minecraft.getMinecraft().effectRenderer.addEffect(particle);
   }
}
