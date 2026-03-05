package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.newgunrizons.model.ModelBullet;

public class EntityBulletRenderer extends Render {

    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(
        NewGunrizonsMod.MODID,
        "textures/effect/bullet44.png");

    private static final ModelBullet DEFAULT_MODEL = new ModelBullet();

    public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
        /*
         * EntityBullet entityBullet = (EntityBullet) entity;
         * ItemWeapon weapon = entityBullet.getWeapon();
         * if (weapon != null) {
         * GL11.glPushMatrix();
         * this.bindTexture(DEFAULT_TEXTURE);
         * GL11.glTranslated(x, y, z);
         * DEFAULT_MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
         * GL11.glPopMatrix();
         * }
         */
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return DEFAULT_TEXTURE;
    }

}
