package com.gtnewhorizon.newgunrizons.model.sight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PUreticle extends ModelBase {

    ModelRenderer reticle1;
    ModelRenderer reticle2;
    ModelRenderer reticle3;
    ModelRenderer reticle4;
    ModelRenderer reticle5;

    public PUreticle() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.reticle1 = new ModelRenderer(this, 0, 0);
        this.reticle1.addBox(0.0F, 0.0F, 0.0F, 1, 31, 0);
        this.reticle1.setRotationPoint(7.2F, -10.3F, 0.0F);
        this.reticle1.setTextureSize(256, 128);
        this.reticle1.mirror = true;
        this.setRotation(this.reticle1, 0.0F, 0.0F, 0.0F);
        this.reticle2 = new ModelRenderer(this, 0, 0);
        this.reticle2.addBox(0.0F, 0.0F, 0.0F, 30, 1, 0);
        this.reticle2.setRotationPoint(-24.0F, -11.0F, 0.0F);
        this.reticle2.setTextureSize(256, 128);
        this.reticle2.mirror = true;
        this.setRotation(this.reticle2, 0.0F, 0.0F, 0.0F);
        this.reticle3 = new ModelRenderer(this, 0, 0);
        this.reticle3.addBox(0.0F, 0.0F, 0.0F, 30, 1, 0);
        this.reticle3.setRotationPoint(9.0F, -11.0F, 0.0F);
        this.reticle3.setTextureSize(256, 128);
        this.reticle3.mirror = true;
        this.setRotation(this.reticle3, 0.0F, 0.0F, 0.0F);
        this.reticle4 = new ModelRenderer(this, 0, 0);
        this.reticle4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 0);
        this.reticle4.setRotationPoint(7.5F, -11.0F, 0.0F);
        this.reticle4.setTextureSize(256, 128);
        this.reticle4.mirror = true;
        this.setRotation(this.reticle4, 0.0F, 0.0F, 0.7853982F);
        this.reticle5 = new ModelRenderer(this, 0, 0);
        this.reticle5.addBox(0.0F, 0.0F, 0.0F, 1, 31, 0);
        this.reticle5.setRotationPoint(6.8F, -10.3F, 0.0F);
        this.reticle5.setTextureSize(256, 128);
        this.reticle5.mirror = true;
        this.setRotation(this.reticle5, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.reticle1.render(f5);
        this.reticle2.render(f5);
        this.reticle3.render(f5);
        this.reticle4.render(f5);
        this.reticle5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
