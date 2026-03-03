package com.gtnewhorizon.newgunrizons.client.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.client.particle.ExplosionParticleFX;
import com.gtnewhorizon.newgunrizons.client.particle.ExplosionSmokeFX;
import com.gtnewhorizon.newgunrizons.client.particle.FlashFX;
import com.gtnewhorizon.newgunrizons.client.particle.SmokeFX;

public class EffectManager {

    public void spawnSmokeParticle(EntityLivingBase player, float xOffset, float yOffset) {
        double motionX = player.worldObj.rand.nextGaussian() * 0.003D;
        double motionY = player.worldObj.rand.nextGaussian() * 0.003D;
        double motionZ = player.worldObj.rand.nextGaussian() * 0.003D;
        Vec3 look = player.getLookVec();
        float distance = 0.3F;
        float scale = 2.3F;
        float positionRandomizationFactor = 0.01F;
        double posX = player.posX + look.xCoord * (double) distance
            + (double) ((player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor)
            + -look.zCoord * (double) xOffset;
        double posY = player.posY + look.yCoord * (double) distance
            + (double) ((player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor)
            - (double) yOffset;
        double posZ = player.posZ + look.zCoord * (double) distance
            + (double) ((player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor)
            + look.xCoord * (double) xOffset;
        SmokeFX smokeParticle = new SmokeFX(
            player.worldObj,
            posX,
            posY,
            posZ,
            scale,
            (float) motionX,
            (float) motionY,
            (float) motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect(smokeParticle);
    }

    public void spawnFlashParticle(EntityLivingBase player, float flashIntensity, float flashScale, float xOffset,
        float yOffset) {
        float distance = 0.5F;
        float scale = 0.8F * 2.3F * flashScale;
        float positionRandomizationFactor = 0.003F;
        Vec3 look = player.getLookVec();
        float motionX = (float) player.worldObj.rand.nextGaussian() * 0.003F;
        float motionY = (float) player.worldObj.rand.nextGaussian() * 0.003F;
        float motionZ = (float) player.worldObj.rand.nextGaussian() * 0.003F;
        double posX = player.posX + look.xCoord * (double) distance
            + (double) ((player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor)
            + -look.zCoord * (double) xOffset;
        double posY = player.posY + look.yCoord * (double) distance
            + (double) ((player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor)
            - (double) yOffset;
        double posZ = player.posZ + look.zCoord * (double) distance
            + (double) ((player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor)
            + look.xCoord * (double) xOffset;
        FlashFX flashParticle = new FlashFX(
            player.worldObj,
            posX,
            posY,
            posZ,
            scale,
            flashIntensity,
            motionX,
            motionY,
            motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect(flashParticle);
    }

    public void spawnExplosionSmoke(double posX, double posY, double posZ, double motionX, double motionY,
        double motionZ, float scale, int maxAge, ExplosionSmokeFX.Behavior behavior, ResourceLocation textureResource) {
        World world = Minecraft.getMinecraft().thePlayer.worldObj;
        ExplosionSmokeFX smokeParticle = new ExplosionSmokeFX(
            world,
            posX,
            posY,
            posZ,
            scale,
            (float) motionX,
            (float) motionY,
            (float) motionZ,
            maxAge,
            ExplosionSmokeFX.Behavior.SMOKE_GRENADE,
            textureResource);
        Minecraft.getMinecraft().effectRenderer.addEffect(smokeParticle);
    }

    public void spawnExplosionParticle(double posX, double posY, double posZ, double motionX, double motionY,
        double motionZ, float scale, int maxAge) {
        World world = Minecraft.getMinecraft().thePlayer.worldObj;
        ExplosionParticleFX explosionParticle = new ExplosionParticleFX(
            world,
            posX,
            posY,
            posZ,
            scale,
            motionX,
            motionY,
            motionZ,
            maxAge);
        Minecraft.getMinecraft().effectRenderer.addEffect(explosionParticle);
    }
}
