package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.entities.EntityGrenade;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;

public class EntityGrenadeRenderer extends Render {

    public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
        EntityGrenade entityGrenade = (EntityGrenade) entity;
        ItemGrenade itemGrenade = entityGrenade.getItemGrenade();
        if (itemGrenade != null) {
            GrenadeRenderer renderer = itemGrenade.getRenderer();
            Minecraft.getMinecraft().renderEngine.bindTexture(
                new ResourceLocation(NewGunrizonsMod.MODID, "textures/models/" + itemGrenade.getTextureName()));
            ModelBase model = renderer.getModel();
            GL11.glPushMatrix();
            GL11.glTranslated(x, y, z);
            float rotationOffsetX = renderer.getXRotationCenterOffset()
                .get();
            float rotationOffsetY = renderer.getYRotationCenterOffset()
                .get();
            float rotationOffsetZ = renderer.getZRotationCenterOffset()
                .get();
            GL11.glTranslatef(rotationOffsetX, rotationOffsetY, rotationOffsetZ);
            GL11.glRotatef(entityGrenade.getXRotation(), 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(entityGrenade.getYRotation(), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(entityGrenade.getZRotation(), 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-rotationOffsetX, -rotationOffsetY, -rotationOffsetZ);
            renderer.getThrownEntityPositioning()
                .run();
            model.render(entity, 0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.08F);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}
