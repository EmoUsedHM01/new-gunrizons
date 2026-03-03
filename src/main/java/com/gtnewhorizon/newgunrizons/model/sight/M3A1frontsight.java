package com.gtnewhorizon.newgunrizons.model.sight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M3A1frontsight extends ModelBase {

    ModelRenderer sight1;
    ModelRenderer sight2;

    public M3A1frontsight() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.sight1 = new ModelRenderer(this, 0, 0);
        this.sight1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
        this.sight1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.sight1.setTextureSize(64, 32);
        this.sight1.mirror = true;
        this.setRotation(this.sight1, 0.0743572F, 0.0F, 0.0F);
        this.sight2 = new ModelRenderer(this, 0, 0);
        this.sight2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
        this.sight2.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.sight2.setTextureSize(64, 32);
        this.sight2.mirror = true;
        this.setRotation(this.sight2, -0.669215F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.sight1.render(f5);
        this.sight2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
