package com.gtnewhorizon.newgunrizons.model.ammo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WebleyBullets extends ModelBase {

    ModelRenderer bullet1;
    ModelRenderer bullet2;
    ModelRenderer bullet3;
    ModelRenderer bullet4;
    ModelRenderer bullet5;
    ModelRenderer bullet6;

    public WebleyBullets() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.bullet1 = new ModelRenderer(this, 0, 100);
        this.bullet1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.bullet1.setRotationPoint(-2.0F, -19.5F, -15.2F);
        this.bullet1.setTextureSize(64, 32);
        this.bullet1.mirror = true;
        this.setRotation(this.bullet1, 0.0F, 0.0F, 0.0F);
        this.bullet2 = new ModelRenderer(this, 0, 100);
        this.bullet2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.bullet2.setRotationPoint(-2.0F, -12.5F, -15.2F);
        this.bullet2.setTextureSize(64, 32);
        this.bullet2.mirror = true;
        this.setRotation(this.bullet2, 0.0F, 0.0F, 0.0F);
        this.bullet3 = new ModelRenderer(this, 0, 100);
        this.bullet3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.bullet3.setRotationPoint(1.0F, -14.0F, -15.2F);
        this.bullet3.setTextureSize(64, 32);
        this.bullet3.mirror = true;
        this.setRotation(this.bullet3, 0.0F, 0.0F, 0.0F);
        this.bullet4 = new ModelRenderer(this, 0, 100);
        this.bullet4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.bullet4.setRotationPoint(1.0F, -18.0F, -15.2F);
        this.bullet4.setTextureSize(64, 32);
        this.bullet4.mirror = true;
        this.setRotation(this.bullet4, 0.0F, 0.0F, 0.0F);
        this.bullet5 = new ModelRenderer(this, 0, 100);
        this.bullet5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.bullet5.setRotationPoint(-5.0F, -18.0F, -15.2F);
        this.bullet5.setTextureSize(64, 32);
        this.bullet5.mirror = true;
        this.setRotation(this.bullet5, 0.0F, 0.0F, 0.0F);
        this.bullet6 = new ModelRenderer(this, 0, 100);
        this.bullet6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.bullet6.setRotationPoint(-5.0F, -14.0F, -15.2F);
        this.bullet6.setTextureSize(64, 32);
        this.bullet6.mirror = true;
        this.setRotation(this.bullet6, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bullet1.render(f5);
        this.bullet2.render(f5);
        this.bullet3.render(f5);
        this.bullet4.render(f5);
        this.bullet5.render(f5);
        this.bullet6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
