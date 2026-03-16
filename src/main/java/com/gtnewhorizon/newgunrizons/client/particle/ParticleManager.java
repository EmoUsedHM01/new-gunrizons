package com.gtnewhorizon.newgunrizons.client.particle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
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

    private static final List<SmokeFX> smokeParticles = new ArrayList<>();

    public static void tickSmoke() {
        Iterator<SmokeFX> it = smokeParticles.iterator();
        while (it.hasNext()) {
            SmokeFX p = it.next();
            p.onUpdate();
            if (p.isDead) {
                it.remove();
            }
        }
    }

    public static void renderSmoke(float partialTicks) {
        if (smokeParticles.isEmpty()) return;

        Entity view = Minecraft.getMinecraft().renderViewEntity;
        if (view == null) return;

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
    private static double[] computeMuzzlePosition(EntityLivingBase player, float distance, float xOffset, float yOffset,
        float rand) {
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
        double posX = eyeX + lx * distance + rx * xOffset - ux * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
        double posY = eyeY + ly * distance - uy * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;
        double posZ = eyeZ + lz * distance + rz * xOffset - uz * yOffset + (r.nextFloat() * 2.0F - 1.0F) * rand;

        return new double[] { posX, posY, posZ };
    }

    // Cached firing point world position, updated each frame during weapon rendering
    @Getter
    private static double lastFiringPointX;
    @Getter
    private static double lastFiringPointY;
    @Getter
    private static double lastFiringPointZ;

    @Getter
    private static float lastFiringPointEyeX;
    @Getter
    private static float lastFiringPointEyeY;
    @Getter
    private static float lastFiringPointEyeZ;

    private static boolean hasFiringPoint;

    public static boolean hasFiringPointPosition() {
        return hasFiringPoint;
    }

    /**
     * Stores the world-space position of the firing point bone, captured during weapon rendering.
     */
    public static void setFiringPointWorldPosition(double x, double y, double z) {
        lastFiringPointX = x;
        lastFiringPointY = y;
        lastFiringPointZ = z;
        hasFiringPoint = true;
    }

    private static long lastEyeCaptureTime = -1;

    /**
     * Stores the eye-space position of the firing point bone.
     * Only captures once per frame — prevents Iris's secondary render passes
     * (shadow, translucent) from overwriting the correct values from the main pass.
     * Uses a 5ms cooldown as a frame guard (at 60fps, frames are ~16ms apart).
     */
    public static void setFiringPointEyePosition(float ex, float ey, float ez) {
        long now = System.nanoTime();
        if (now - lastEyeCaptureTime < 5_000_000L) return;
        lastEyeCaptureTime = now;
        lastFiringPointEyeX = ex;
        lastFiringPointEyeY = ey - 0.1f; // shift down slightly (positive Y = down in eye-space)
        lastFiringPointEyeZ = ez;
    }

    /**
     * Computes and stores the firing point world position from model-space camera-relative offsets.
     */
    public static void setMuzzleFromModelSpace(EntityLivingBase player, float forward, float right, float up) {
        double[] pos = computeMuzzlePosition(player, forward, right, -up, 0f);
        lastFiringPointX = pos[0];
        lastFiringPointY = pos[1];
        lastFiringPointZ = pos[2];
        hasFiringPoint = true;
    }

    /**
     * Spawns a {@link SmokeFX} wisp at the firing point bone position if available,
     * otherwise falls back to the offset-based calculation.
     */
    public static void spawnSmokeParticle(EntityLivingBase player, float xOffset, float yOffset) {
        double motionX = player.worldObj.rand.nextGaussian() * 0.003D;
        double motionY = player.worldObj.rand.nextGaussian() * 0.003D;
        double motionZ = player.worldObj.rand.nextGaussian() * 0.003D;

        float scale = 2.3F;
        double[] pos;

        if (hasFiringPoint) {
            pos = new double[] { lastFiringPointX, lastFiringPointY, lastFiringPointZ };
            java.util.Random r = player.worldObj.rand;
            pos[0] += (r.nextFloat() * 2.0F - 1.0F) * 0.01F;
            pos[1] += (r.nextFloat() * 2.0F - 1.0F) * 0.01F;
            pos[2] += (r.nextFloat() * 2.0F - 1.0F) * 0.01F;
        } else {
            float distance = 0.42F;
            xOffset += -0.02F;
            yOffset += 0.10F;
            pos = computeMuzzlePosition(player, distance, xOffset, yOffset, 0.01F);
        }

        SmokeFX particle = new SmokeFX(
            player.worldObj,
            pos[0],
            pos[1],
            pos[2],
            scale,
            (float) motionX,
            (float) motionY,
            (float) motionZ);
        smokeParticles.add(particle);
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
