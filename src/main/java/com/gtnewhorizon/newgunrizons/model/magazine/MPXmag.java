package com.gtnewhorizon.newgunrizons.model.magazine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MPXmag extends ModelBase {

    ModelRenderer Mag1;
    ModelRenderer Mag2;
    ModelRenderer Mag3;
    ModelRenderer Mag4;

    public MPXmag() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Mag1 = new ModelRenderer(this, 0, 0);
        this.Mag1.addBox(0.0F, 0.0F, 0.0F, 4, 11, 5);
        this.Mag1.setRotationPoint(-3.5F, -8.5F, -20.6F);
        this.Mag1.setTextureSize(64, 32);
        this.Mag1.mirror = true;
        this.setRotation(this.Mag1, -0.2230717F, 0.0F, 0.0F);
        this.Mag2 = new ModelRenderer(this, 0, 0);
        this.Mag2.addBox(0.0F, 0.0F, 0.0F, 4, 5, 15);
        this.Mag2.setRotationPoint(-3.5F, 3.3F, -18.1F);
        this.Mag2.setTextureSize(64, 32);
        this.Mag2.mirror = true;
        this.setRotation(this.Mag2, -2.044824F, 0.0F, 0.0F);
        this.Mag3 = new ModelRenderer(this, 0, 0);
        this.Mag3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 15);
        this.Mag3.setRotationPoint(-3.0F, 3.3F, -17.7F);
        this.Mag3.setTextureSize(64, 32);
        this.Mag3.mirror = true;
        this.setRotation(this.Mag3, -2.044824F, 0.0F, 0.0F);
        this.Mag4 = new ModelRenderer(this, 0, 0);
        this.Mag4.addBox(0.0F, 0.0F, 0.0F, 3, 12, 1);
        this.Mag4.setRotationPoint(-3.0F, -8.5F, -16.1F);
        this.Mag4.setTextureSize(64, 32);
        this.Mag4.mirror = true;
        this.setRotation(this.Mag4, -0.2230717F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Mag1.render(f5);
        this.Mag2.render(f5);
        this.Mag3.render(f5);
        this.Mag4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
