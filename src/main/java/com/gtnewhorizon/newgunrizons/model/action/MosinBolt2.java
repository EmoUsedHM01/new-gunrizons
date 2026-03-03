package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MosinBolt2 extends ModelBase {

    ModelRenderer mosinbolt13;
    ModelRenderer mosinbolt14;
    ModelRenderer mosinbolt15;

    public MosinBolt2() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.mosinbolt13 = new ModelRenderer(this, 0, 0);
        this.mosinbolt13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
        this.mosinbolt13.setRotationPoint(-2.5F, -15.8F, -17.0F);
        this.mosinbolt13.setTextureSize(64, 32);
        this.mosinbolt13.mirror = true;
        this.setRotation(this.mosinbolt13, 0.0F, 0.0F, 0.0F);
        this.mosinbolt14 = new ModelRenderer(this, 0, 0);
        this.mosinbolt14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.mosinbolt14.setRotationPoint(-2.5F, -15.8F, -13.0F);
        this.mosinbolt14.setTextureSize(64, 32);
        this.mosinbolt14.mirror = true;
        this.setRotation(this.mosinbolt14, -0.669215F, 0.0F, 0.0F);
        this.mosinbolt15 = new ModelRenderer(this, 0, 0);
        this.mosinbolt15.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.mosinbolt15.setRotationPoint(-2.5F, -15.0F, -12.0F);
        this.mosinbolt15.setTextureSize(64, 32);
        this.mosinbolt15.mirror = true;
        this.setRotation(this.mosinbolt15, -2.267895F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.mosinbolt13.render(f5);
        this.mosinbolt14.render(f5);
        this.mosinbolt15.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
