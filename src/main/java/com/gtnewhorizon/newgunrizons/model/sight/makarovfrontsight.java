package com.gtnewhorizon.newgunrizons.model.sight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class makarovfrontsight extends ModelBase {

    ModelRenderer sight2;
    ModelRenderer sight3;
    ModelRenderer sight4;
    ModelRenderer sight5;

    public makarovfrontsight() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.sight2 = new ModelRenderer(this, 0, 0);
        this.sight2.addBox(0.0F, 0.0F, 0.0F, 3, 5, 4);
        this.sight2.setRotationPoint(0.0F, -2.0F, -3.0F);
        this.sight2.setTextureSize(64, 32);
        this.sight2.mirror = true;
        this.setRotation(this.sight2, 0.0F, 0.0F, 0.0F);
        this.sight3 = new ModelRenderer(this, 0, 0);
        this.sight3.addBox(0.0F, 0.0F, 0.0F, 3, 5, 4);
        this.sight3.setRotationPoint(0.0F, -2.0F, -3.0F);
        this.sight3.setTextureSize(64, 32);
        this.sight3.mirror = true;
        this.setRotation(this.sight3, -1.264073F, 0.0F, 0.0F);
        this.sight4 = new ModelRenderer(this, 0, 0);
        this.sight4.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
        this.sight4.setRotationPoint(0.0F, -0.4F, -7.75F);
        this.sight4.setTextureSize(64, 32);
        this.sight4.mirror = true;
        this.setRotation(this.sight4, -0.669215F, 0.0F, 0.0F);
        this.sight5 = new ModelRenderer(this, 0, 0);
        this.sight5.addBox(0.0F, 0.0F, 0.0F, 3, 1, 7);
        this.sight5.setRotationPoint(0.0F, 2.0F, -9.6F);
        this.sight5.setTextureSize(64, 32);
        this.sight5.mirror = true;
        this.setRotation(this.sight5, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.sight2.render(f5);
        this.sight3.render(f5);
        this.sight4.render(f5);
        this.sight5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
