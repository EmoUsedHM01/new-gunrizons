package com.gtnewhorizon.newgunrizons.model.magazine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HK417Mag extends ModelBase {

    ModelRenderer mag1;
    ModelRenderer mag2;
    ModelRenderer mag3;

    public HK417Mag() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.mag1 = new ModelRenderer(this, 0, 0);
        this.mag1.addBox(0.0F, 0.0F, 0.0F, 3, 9, 10);
        this.mag1.setRotationPoint(-3.3F, 5.0F, -22.8F);
        this.mag1.setTextureSize(64, 32);
        this.mag1.mirror = true;
        this.setRotation(this.mag1, 1.449966F, 0.0F, 0.0F);
        this.mag2 = new ModelRenderer(this, 0, 0);
        this.mag2.addBox(0.0F, 0.0F, 0.0F, 1, 9, 10);
        this.mag2.setRotationPoint(-0.7F, 5.0F, -22.8F);
        this.mag2.setTextureSize(64, 32);
        this.mag2.mirror = true;
        this.setRotation(this.mag2, 1.449966F, 0.0F, 0.0F);
        this.mag3 = new ModelRenderer(this, 40, 0);
        this.mag3.addBox(0.0F, 0.0F, 0.0F, 4, 10, 2);
        this.mag3.setRotationPoint(-3.5F, 5.8F, -23.5F);
        this.mag3.setTextureSize(64, 32);
        this.mag3.mirror = true;
        this.setRotation(this.mag3, 1.449966F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.mag1.render(f5);
        this.mag2.render(f5);
        this.mag3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
