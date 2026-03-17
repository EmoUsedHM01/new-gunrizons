package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.weapon.FiringPointTracker;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;

public class EntityBulletRenderer extends Render {

    private static final ResourceLocation DUMMY_TEXTURE = new ResourceLocation("textures/misc/unknown_pack.png");

    private static final int LERP_TICKS = 4;

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        EntityBullet bullet = (EntityBullet) entity;
        ItemWeapon weapon = bullet.getWeapon();
        if (weapon == null) return;
        WeaponRenderer renderer = weapon.getRenderer();
        if (renderer == null) return;

        float tracerWidth = renderer.getTracerWidth();
        float tracerLength = renderer.getTracerLength();

        double renderX = x;
        double renderY = y;
        double renderZ = z;

        if (bullet.ticksExisted <= LERP_TICKS) {
            Minecraft mc = Minecraft.getMinecraft();

            double originX = x;
            double originY = y;
            double originZ = z;

            // Compute thrower eye position in camera-relative space
            EntityLivingBase thrower = bullet.getThrower();
            if (thrower != null) {
                double entityX = bullet.lastTickPosX + (bullet.posX - bullet.lastTickPosX) * partialTicks;
                double entityY = bullet.lastTickPosY + (bullet.posY - bullet.lastTickPosY) * partialTicks;
                double entityZ = bullet.lastTickPosZ + (bullet.posZ - bullet.lastTickPosZ) * partialTicks;
                double camX = entityX - x;
                double camY = entityY - y;
                double camZ = entityZ - z;
                double eyeX = thrower.lastTickPosX + (thrower.posX - thrower.lastTickPosX) * partialTicks;
                double eyeY = thrower.lastTickPosY + (thrower.posY - thrower.lastTickPosY) * partialTicks
                    + thrower.getEyeHeight();
                double eyeZ = thrower.lastTickPosZ + (thrower.posZ - thrower.lastTickPosZ) * partialTicks;
                originX = eyeX - camX;
                originY = eyeY - camY;
                originZ = eyeZ - camZ;

                // If we have the barrel bone delta (eye-space offset from model origin),
                // convert it to world-space and add to the eye position for muzzle accuracy.
                if (thrower == mc.thePlayer
                    && mc.gameSettings.thirdPersonView == 0
                    && FiringPointTracker.hasFiringPoint()) {
                    float dEx = FiringPointTracker.getEyeX();
                    float dEy = FiringPointTracker.getEyeY();
                    float dEz = FiringPointTracker.getEyeZ();

                    float playerYaw = mc.thePlayer.prevRotationYaw
                        + (mc.thePlayer.rotationYaw - mc.thePlayer.prevRotationYaw) * partialTicks;
                    float playerPitch = mc.thePlayer.prevRotationPitch
                        + (mc.thePlayer.rotationPitch - mc.thePlayer.prevRotationPitch) * partialTicks;

                    double yawRad = Math.toRadians(-(playerYaw + 180.0));
                    double pitchRad = Math.toRadians(-playerPitch);
                    double cosY = Math.cos(yawRad);
                    double sinY = Math.sin(yawRad);
                    double cosP = Math.cos(pitchRad);
                    double sinP = Math.sin(pitchRad);

                    double p1x = dEx;
                    double p1y = dEy * cosP - dEz * sinP;
                    double p1z = dEy * sinP + dEz * cosP;
                    originX += p1x * cosY + p1z * sinY;
                    originY += p1y;
                    originZ += -p1x * sinY + p1z * cosY;
                }
            }

            float t = (bullet.ticksExisted + partialTicks) / (LERP_TICKS + 1.0F);
            t = Math.min(t, 1.0F);

            renderX = originX + (x - originX) * t;
            renderY = originY + (y - originY) * t;
            renderZ = originZ + (z - originZ) * t;
        }

        GL11.glPushMatrix();
        GL11.glTranslated(renderX, renderY, renderZ);

        GL11.glRotatef(
            bullet.prevRotationYaw + (bullet.rotationYaw - bullet.prevRotationYaw) * partialTicks - 90.0F,
            0.0F, 1.0F, 0.0F);
        GL11.glRotatef(
            bullet.prevRotationPitch + (bullet.rotationPitch - bullet.prevRotationPitch) * partialTicks,
            0.0F, 0.0F, 1.0F);

        TracerRenderer.render(tracerLength, tracerWidth,
            renderer.getTracerColorR(), renderer.getTracerColorG(), renderer.getTracerColorB(),
            renderer.getTracerIntensity());
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return DUMMY_TEXTURE;
    }
}
