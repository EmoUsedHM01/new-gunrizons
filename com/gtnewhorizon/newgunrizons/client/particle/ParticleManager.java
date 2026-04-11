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
         if (p.field_70128_L) {
            it.remove();
         }
      }
   }

   public static void renderSmoke(float partialTicks) {
      if (!smokeParticles.isEmpty()) {
         Entity view = Minecraft.func_71410_x().field_71451_h;
         if (view != null) {
            EntityFX.field_70556_an = view.field_70142_S + (view.field_70165_t - view.field_70142_S) * partialTicks;
            EntityFX.field_70554_ao = view.field_70137_T + (view.field_70163_u - view.field_70137_T) * partialTicks;
            EntityFX.field_70555_ap = view.field_70136_U + (view.field_70161_v - view.field_70136_U) * partialTicks;
            float rotX = ActiveRenderInfo.field_74588_d;
            float rotZ = ActiveRenderInfo.field_74586_f;
            float rotYZ = ActiveRenderInfo.field_74587_g;
            float rotXY = ActiveRenderInfo.field_74596_h;
            float rotXZ = ActiveRenderInfo.field_74589_e;
            Tessellator tessellator = Tessellator.field_78398_a;

            for (SmokeFX particle : smokeParticles) {
               particle.func_70539_a(tessellator, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);
            }
         }
      }
   }

   private static double[] computeMuzzlePosition(EntityLivingBase player, float distance, float xOffset, float yOffset, float rand) {
      Vec3 look = player.func_70040_Z();
      double lx = look.field_72450_a;
      double ly = look.field_72448_b;
      double lz = look.field_72449_c;
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
      double eyeX = player.field_70165_t;
      double eyeY = player.field_70163_u + player.func_70047_e();
      double eyeZ = player.field_70161_v;
      Random r = player.field_70170_p.field_73012_v;
      double posX = eyeX + lx * distance + rx * xOffset - ux * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
      double posY = eyeY + ly * distance - horizLen * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
      double posZ = eyeZ + lz * distance + rz * xOffset - uz * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
      return new double[]{posX, posY, posZ};
   }

   public static void spawnSmokeParticle(EntityLivingBase player, float xOffset, float yOffset) {
      double motionX = player.field_70170_p.field_73012_v.nextGaussian() * 0.003;
      double motionY = player.field_70170_p.field_73012_v.nextGaussian() * 0.003;
      double motionZ = player.field_70170_p.field_73012_v.nextGaussian() * 0.003;
      float distance = 0.42F;
      float scale = 2.3F;
      xOffset += -0.02F;
      yOffset += 0.1F;
      double[] pos = computeMuzzlePosition(player, distance, xOffset, yOffset, 0.01F);
      SmokeFX particle = new SmokeFX(player.field_70170_p, pos[0], pos[1], pos[2], scale, (float)motionX, (float)motionY, (float)motionZ);
      smokeParticles.add(particle);
   }

   public static void spawnExplosionSmoke(
      double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float scale, int maxAge, ResourceLocation textureResource
   ) {
      World world = Minecraft.func_71410_x().field_71439_g.field_70170_p;
      ExplosionSmokeFX particle = new ExplosionSmokeFX(
         world, posX, posY, posZ, scale, (float)motionX, (float)motionY, (float)motionZ, maxAge, ExplosionSmokeFX.Behavior.SMOKE_GRENADE, textureResource
      );
      Minecraft.func_71410_x().field_71452_i.func_78873_a(particle);
   }

   public static void spawnExplosionParticle(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float scale, int maxAge) {
      World world = Minecraft.func_71410_x().field_71439_g.field_70170_p;
      ExplosionParticleFX particle = new ExplosionParticleFX(world, posX, posY, posZ, scale, motionX, motionY, motionZ, maxAge);
      Minecraft.func_71410_x().field_71452_i.func_78873_a(particle);
   }
}
