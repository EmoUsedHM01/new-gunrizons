package com.gtnewhorizon.newgunrizons.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBullet extends ModelBase {

    private final ModelRenderer ammunition;

    public ModelBullet() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.ammunition = new ModelRenderer(this, 0, 0);
        this.ammunition.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.ammunition.setRotationPoint(-0.5F, 22.0F, -1.5F);
        this.ammunition.setTextureSize(32, 32);
        this.ammunition.mirror = true;
        this.setRotation(this.ammunition, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw,
        float headPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale, entity);
        this.ammunition.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
