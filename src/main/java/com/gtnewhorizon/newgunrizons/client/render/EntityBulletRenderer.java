package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.gtnewhorizon.newgunrizons.client.particle.ParticleManager;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;

public class EntityBulletRenderer extends Render {

    private static final ResourceLocation DUMMY_TEXTURE = new ResourceLocation("textures/misc/unknown_pack.png");

    private static final int LERP_TICKS = 2;

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

        // Origin correction: lerp from firing point to entity position.
        Minecraft mc = Minecraft.getMinecraft();
        if (bullet.getThrower() == mc.thePlayer
            && mc.gameSettings.thirdPersonView == 0
            && ParticleManager.hasFiringPointPosition()
            && bullet.ticksExisted <= LERP_TICKS) {

            float t = (bullet.ticksExisted + partialTicks) / (LERP_TICKS + 1.0F);
            t = Math.min(t, 1.0F);

            Entity view = mc.renderViewEntity;
            double camX = view.lastTickPosX + (view.posX - view.lastTickPosX) * partialTicks;
            double camY = view.lastTickPosY + (view.posY - view.lastTickPosY) * partialTicks;
            double camZ = view.lastTickPosZ + (view.posZ - view.lastTickPosZ) * partialTicks;

            double fpX = ParticleManager.getLastFiringPointX() - camX;
            double fpY = ParticleManager.getLastFiringPointY() - camY;
            double fpZ = ParticleManager.getLastFiringPointZ() - camZ;

            renderX = fpX + (x - fpX) * t;
            renderY = fpY + (y - fpY) * t;
            renderZ = fpZ + (z - fpZ) * t;
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
