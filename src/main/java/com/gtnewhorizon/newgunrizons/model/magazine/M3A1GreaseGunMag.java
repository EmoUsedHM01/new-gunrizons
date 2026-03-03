package com.gtnewhorizon.newgunrizons.model.magazine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M3A1GreaseGunMag extends ModelBase {

    ModelRenderer mag1;

    public M3A1GreaseGunMag() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.mag1 = new ModelRenderer(this, 0, 0);
        this.mag1.addBox(0.0F, 0.0F, 0.0F, 3, 30, 7);
        this.mag1.setRotationPoint(-3.0F, -9.2F, -37.0F);
        this.mag1.setTextureSize(64, 32);
        this.mag1.mirror = true;
        this.setRotation(this.mag1, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.mag1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
