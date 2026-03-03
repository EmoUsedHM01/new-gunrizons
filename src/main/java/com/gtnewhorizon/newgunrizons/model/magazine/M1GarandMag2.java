package com.gtnewhorizon.newgunrizons.model.magazine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M1GarandMag2 extends ModelBase {

    ModelRenderer mag1;
    ModelRenderer mag2;
    ModelRenderer mag3;
    ModelRenderer mag4;
    ModelRenderer mag5;
    ModelRenderer mag6;
    ModelRenderer mag7;
    ModelRenderer mag8;
    ModelRenderer mag9;

    public M1GarandMag2() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.mag1 = new ModelRenderer(this, 100, 0);
        this.mag1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3);
        this.mag1.setRotationPoint(0.5F, -6.85F, 1.0F);
        this.mag1.setTextureSize(64, 32);
        this.mag1.mirror = true;
        this.setRotation(this.mag1, 0.0F, 0.0F, 0.0F);
        this.mag2 = new ModelRenderer(this, 100, 0);
        this.mag2.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3);
        this.mag2.setRotationPoint(-2.25F, -6.85F, 1.0F);
        this.mag2.setTextureSize(64, 32);
        this.mag2.mirror = true;
        this.setRotation(this.mag2, 0.0F, 0.0F, 0.0F);
        this.mag3 = new ModelRenderer(this, 100, 0);
        this.mag3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.mag3.setRotationPoint(1.5F, -6.85F, 1.0F);
        this.mag3.setTextureSize(64, 32);
        this.mag3.mirror = true;
        this.setRotation(this.mag3, 0.0F, 0.0F, 2.119181F);
        this.mag4 = new ModelRenderer(this, 100, 0);
        this.mag4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.mag4.setRotationPoint(-2.25F, -6.85F, 1.0F);
        this.mag4.setTextureSize(64, 32);
        this.mag4.mirror = true;
        this.setRotation(this.mag4, 0.0F, 0.0F, -0.5205006F);
        this.mag5 = new ModelRenderer(this, 100, 0);
        this.mag5.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1);
        this.mag5.setRotationPoint(-1.4F, -6.4F, 3.0F);
        this.mag5.setTextureSize(64, 32);
        this.mag5.mirror = true;
        this.setRotation(this.mag5, 0.0F, 0.0F, 0.0F);
        this.mag6 = new ModelRenderer(this, 100, 0);
        this.mag6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.mag6.setRotationPoint(-1.75F, -6.8F, 3.0F);
        this.mag6.setTextureSize(64, 32);
        this.mag6.mirror = true;
        this.setRotation(this.mag6, 0.0F, 0.0F, 0.0F);
        this.mag7 = new ModelRenderer(this, 100, 0);
        this.mag7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.mag7.setRotationPoint(0.0F, -6.8F, 3.0F);
        this.mag7.setTextureSize(64, 32);
        this.mag7.mirror = true;
        this.setRotation(this.mag7, 0.0F, 0.0F, 0.0F);
        this.mag8 = new ModelRenderer(this, 100, 0);
        this.mag8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3);
        this.mag8.setRotationPoint(1.5F, -0.85F, 1.0F);
        this.mag8.setTextureSize(64, 32);
        this.mag8.mirror = true;
        this.setRotation(this.mag8, 0.0F, 0.0F, 2.602503F);
        this.mag9 = new ModelRenderer(this, 100, 0);
        this.mag9.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3);
        this.mag9.setRotationPoint(-2.25F, -0.85F, 1.0F);
        this.mag9.setTextureSize(64, 32);
        this.mag9.mirror = true;
        this.setRotation(this.mag9, 0.0F, 0.0F, -0.9666439F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.mag1.render(f5);
        this.mag2.render(f5);
        this.mag3.render(f5);
        this.mag4.render(f5);
        this.mag5.render(f5);
        this.mag6.render(f5);
        this.mag7.render(f5);
        this.mag8.render(f5);
        this.mag9.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
