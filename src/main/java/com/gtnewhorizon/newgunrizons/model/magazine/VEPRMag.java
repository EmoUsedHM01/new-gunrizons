package com.gtnewhorizon.newgunrizons.model.magazine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VEPRMag extends ModelBase {

    ModelRenderer mag1;
    ModelRenderer mag2;
    ModelRenderer mag3;
    ModelRenderer mag4;

    public VEPRMag() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.mag1 = new ModelRenderer(this, 100, 0);
        this.mag1.addBox(0.0F, 0.0F, 0.0F, 3, 8, 11);
        this.mag1.setRotationPoint(-3.4F, -7.2F, -31.8F);
        this.mag1.setTextureSize(64, 32);
        this.mag1.mirror = true;
        this.setRotation(this.mag1, -0.2602503F, 0.0F, 0.0F);
        this.mag2 = new ModelRenderer(this, 100, 0);
        this.mag2.addBox(0.0F, 0.0F, 0.0F, 1, 8, 11);
        this.mag2.setRotationPoint(-0.6F, -7.2F, -31.8F);
        this.mag2.setTextureSize(64, 32);
        this.mag2.mirror = true;
        this.setRotation(this.mag2, -0.2602503F, 0.0F, 0.0F);
        this.mag3 = new ModelRenderer(this, 100, 0);
        this.mag3.addBox(0.0F, 0.0F, 0.0F, 3, 11, 12);
        this.mag3.setRotationPoint(-3.4F, 3.4F, -23.2F);
        this.mag3.setTextureSize(64, 32);
        this.mag3.mirror = true;
        this.setRotation(this.mag3, -2.044824F, 0.0F, 0.0F);
        this.mag4 = new ModelRenderer(this, 100, 0);
        this.mag4.addBox(0.0F, 0.0F, 0.0F, 1, 11, 12);
        this.mag4.setRotationPoint(-0.6F, 3.4F, -23.2F);
        this.mag4.setTextureSize(64, 32);
        this.mag4.mirror = true;
        this.setRotation(this.mag4, -2.044824F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.mag1.render(f5);
        this.mag2.render(f5);
        this.mag3.render(f5);
        this.mag4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
