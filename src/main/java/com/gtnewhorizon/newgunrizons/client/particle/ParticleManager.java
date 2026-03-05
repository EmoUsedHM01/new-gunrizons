package com.gtnewhorizon.newgunrizons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Factory for spawning weapon and explosion particle effects.
 * <p>
 * All particles use FXLayer 3 (fully custom rendering) and bypass active shader
 * programs during rendering. See individual particle classes for details on
 * Angelica/Iris compatibility.
 */
public class ParticleManager {

    /**
     * Spawns a {@link SmokeFX} wisp at the player's muzzle position.
     *
     * @param player  the shooter
     * @param xOffset lateral offset from the look vector (positive = right)
     * @param yOffset vertical offset below eye height
     */
    public static void spawnSmokeParticle(EntityLivingBase player, float xOffset, float yOffset) {
        double motionX = player.worldObj.rand.nextGaussian() * 0.003D;
        double motionY = player.worldObj.rand.nextGaussian() * 0.003D;
        double motionZ = player.worldObj.rand.nextGaussian() * 0.003D;

        Vec3 look = player.getLookVec();
        float distance = 0.3F;
        float scale = 2.3F;
        float positionRandomizationFactor = 0.01F;

        double posX = player.posX + look.xCoord * distance
            + (player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor
            - look.zCoord * xOffset;
        double posY = player.posY + look.yCoord * distance
            + (player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor
            - yOffset;
        double posZ = player.posZ + look.zCoord * distance
            + (player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor
            + look.xCoord * xOffset;

        SmokeFX particle = new SmokeFX(
            player.worldObj, posX, posY, posZ, scale,
            (float) motionX, (float) motionY, (float) motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

    /**
     * Spawns a {@link FlashFX} burst at the player's muzzle position.
     *
     * @param player         the shooter
     * @param flashIntensity initial alpha (0..1)
     * @param flashScale     size multiplier
     * @param xOffset        lateral offset from the look vector
     * @param yOffset        vertical offset below eye height
     */
    public static void spawnFlashParticle(EntityLivingBase player, float flashIntensity, float flashScale,
        float xOffset, float yOffset) {
        float distance = 0.7F;
        float scale = 0.8F * 2.3F * flashScale;
        float positionRandomizationFactor = 0.003F;

        Vec3 look = player.getLookVec();

        float motionX = (float) player.worldObj.rand.nextGaussian() * 0.003F;
        float motionY = (float) player.worldObj.rand.nextGaussian() * 0.003F;
        float motionZ = (float) player.worldObj.rand.nextGaussian() * 0.003F;

        double posX = player.posX + look.xCoord * distance
            + (player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor
            - look.zCoord * xOffset;
        double posY = player.posY + look.yCoord * distance
            + (player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor
            - yOffset;
        double posZ = player.posZ + look.zCoord * distance
            + (player.worldObj.rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor
            + look.xCoord * xOffset;

        FlashFX particle = new FlashFX(
            player.worldObj, posX, posY, posZ, scale, flashIntensity,
            motionX, motionY, motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

    /**
     * Spawns an {@link ExplosionSmokeFX} cloud at the given world position.
     * Used for grenade and weapon explosion aftermath.
     */
    public static void spawnExplosionSmoke(double posX, double posY, double posZ, double motionX, double motionY,
        double motionZ, float scale, int maxAge, ResourceLocation textureResource) {
        World world = Minecraft.getMinecraft().thePlayer.worldObj;
        ExplosionSmokeFX particle = new ExplosionSmokeFX(
            world, posX, posY, posZ, scale,
            (float) motionX, (float) motionY, (float) motionZ,
            maxAge, ExplosionSmokeFX.Behavior.SMOKE_GRENADE, textureResource);
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

    /**
     * Spawns an {@link ExplosionParticleFX} debris chunk at the given world position.
     * Used for the fiery debris thrown by explosions.
     */
    public static void spawnExplosionParticle(double posX, double posY, double posZ, double motionX, double motionY,
        double motionZ, float scale, int maxAge) {
        World world = Minecraft.getMinecraft().thePlayer.worldObj;
        ExplosionParticleFX particle = new ExplosionParticleFX(
            world, posX, posY, posZ, scale,
            motionX, motionY, motionZ, maxAge);
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }
}
