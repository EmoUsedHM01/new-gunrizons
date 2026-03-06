package com.gtnewhorizon.newgunrizons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.client.debug.MuzzleDebug;

/**
 * Factory for spawning weapon and explosion particle effects.
 * <p>
 * All particles use FXLayer 3 (fully custom rendering) and bypass active shader
 * programs during rendering. See individual particle classes for details on
 * Angelica/Iris compatibility.
 */
public class ParticleManager {

    /**
     * Computes a world-space muzzle position relative to the player's eye using
     * a camera-relative coordinate system (forward / right / up).
     *
     * @param player   the shooter
     * @param distance forward distance along the look vector
     * @param xOffset  lateral offset (positive = right from the player's view)
     * @param yOffset  vertical offset (positive = downward from the player's view)
     * @param rand     randomization factor applied to each axis
     * @return world-space {x, y, z}
     */
    private static double[] computeMuzzlePosition(EntityLivingBase player, float distance, float xOffset,
        float yOffset, float rand) {
        Vec3 look = player.getLookVec();
        double lx = look.xCoord, ly = look.yCoord, lz = look.zCoord;

        // Right vector: horizontal perpendicular to look, normalized
        double horizLen = Math.sqrt(lx * lx + lz * lz);
        double rx, rz;
        if (horizLen > 0.001) {
            rx = -lz / horizLen;
            rz = lx / horizLen;
        } else {
            rx = 1.0;
            rz = 0.0;
        }

        // Camera up = cross(right, forward), already unit length
        double ux = -rz * ly;
        double uy = horizLen;
        double uz = rx * ly;

        double eyeX = player.posX;
        double eyeY = player.posY + player.getEyeHeight();
        double eyeZ = player.posZ;

        java.util.Random r = player.worldObj.rand;
        double posX = eyeX + lx * distance + rx * xOffset - ux * yOffset
            + (r.nextFloat() * 2.0F - 1.0F) * rand;
        double posY = eyeY + ly * distance - uy * yOffset
            + (r.nextFloat() * 2.0F - 1.0F) * rand;
        double posZ = eyeZ + lz * distance + rz * xOffset - uz * yOffset
            + (r.nextFloat() * 2.0F - 1.0F) * rand;

        return new double[] { posX, posY, posZ };
    }

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

        float distance = 0.42F;
        float scale = 2.3F;
        xOffset += -0.02F;
        yOffset += 0.10F;

        if (MuzzleDebug.isEnabled()) {
            distance += MuzzleDebug.getDistanceOffset();
            xOffset += MuzzleDebug.getXOffset();
            yOffset += MuzzleDebug.getYOffset();
        }

        double[] pos = computeMuzzlePosition(player, distance, xOffset, yOffset, 0.01F);

        SmokeFX particle = new SmokeFX(
            player.worldObj,
            pos[0],
            pos[1],
            pos[2],
            scale,
            (float) motionX,
            (float) motionY,
            (float) motionZ);
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
        float distance = 0.82F;
        float scale = 0.8F * 2.3F * flashScale;
        xOffset += -0.02F;
        yOffset += 0.10F;

        if (MuzzleDebug.isEnabled()) {
            distance += MuzzleDebug.getDistanceOffset();
            xOffset += MuzzleDebug.getXOffset();
            yOffset += MuzzleDebug.getYOffset();
        }

        float motionX = (float) player.worldObj.rand.nextGaussian() * 0.003F;
        float motionY = (float) player.worldObj.rand.nextGaussian() * 0.003F;
        float motionZ = (float) player.worldObj.rand.nextGaussian() * 0.003F;

        double[] pos = computeMuzzlePosition(player, distance, xOffset, yOffset, 0.003F);

        FlashFX particle = new FlashFX(
            player.worldObj,
            pos[0],
            pos[1],
            pos[2],
            scale,
            flashIntensity,
            motionX,
            motionY,
            motionZ);
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
            world,
            posX,
            posY,
            posZ,
            scale,
            motionX,
            motionY,
            motionZ,
            maxAge);
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }
}
