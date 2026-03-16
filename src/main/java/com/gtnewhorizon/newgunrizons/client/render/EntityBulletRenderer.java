package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.gtnewhorizon.newgunrizons.client.particle.ParticleManager;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;

public class EntityBulletRenderer extends Render {

    private static final ResourceLocation DUMMY_TEXTURE = new ResourceLocation("textures/misc/unknown_pack.png");

    private static final int LERP_TICKS = 4;

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        EntityBullet bullet = (EntityBullet) entity;
        ItemWeapon weapon = bullet.getWeapon();
        if (weapon == null) return;

        float tracerWidth = weapon.getTracerWidth();
        float tracerLength = weapon.getTracerLength();

        double renderX = x;
        double renderY = y;
        double renderZ = z;

        // Origin correction: lerp from firing point (or thrower position) to
        // entity position over the first few ticks, so the tracer visually
        // starts at the barrel rather than appearing mid-flight.
        if (bullet.ticksExisted <= LERP_TICKS) {
            Minecraft mc = Minecraft.getMinecraft();
            Entity view = mc.renderViewEntity;
            double camX = view.lastTickPosX + (view.posX - view.lastTickPosX) * partialTicks;
            double camY = view.lastTickPosY + (view.posY - view.lastTickPosY) * partialTicks;
            double camZ = view.lastTickPosZ + (view.posZ - view.lastTickPosZ) * partialTicks;

            double originX, originY, originZ;

            if (bullet.getThrower() == mc.thePlayer
                && mc.gameSettings.thirdPersonView == 0
                && ParticleManager.hasFiringPointPosition()) {
                // First person: use the captured muzzle bone position
                originX = ParticleManager.getLastFiringPointX() - camX;
                originY = ParticleManager.getLastFiringPointY() - camY;
                originZ = ParticleManager.getLastFiringPointZ() - camZ;
            } else {
                // Fallback: use the thrower's eye position
                EntityLivingBase thrower = bullet.getThrower();
                if (thrower != null) {
                    double eyeX = thrower.lastTickPosX + (thrower.posX - thrower.lastTickPosX) * partialTicks;
                    double eyeY = thrower.lastTickPosY + (thrower.posY - thrower.lastTickPosY) * partialTicks
                        + thrower.getEyeHeight();
                    double eyeZ = thrower.lastTickPosZ + (thrower.posZ - thrower.lastTickPosZ) * partialTicks;
                    originX = eyeX - camX;
                    originY = eyeY - camY;
                    originZ = eyeZ - camZ;
                } else {
                    originX = renderX;
                    originY = renderY;
                    originZ = renderZ;
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

        // Vanilla arrow rotation convention
        GL11.glRotatef(
            bullet.prevRotationYaw + (bullet.rotationYaw - bullet.prevRotationYaw) * partialTicks - 90.0F,
            0.0F, 1.0F, 0.0F);
        GL11.glRotatef(
            bullet.prevRotationPitch + (bullet.rotationPitch - bullet.prevRotationPitch) * partialTicks,
            0.0F, 0.0F, 1.0F);

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        TracerRenderer.render(tracerLength, tracerWidth,
            weapon.getTracerColorR(), weapon.getTracerColorG(), weapon.getTracerColorB(),
            weapon.getTracerIntensity());

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return DUMMY_TEXTURE;
    }
}
