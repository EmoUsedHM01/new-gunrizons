package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MP18action extends ModelBase {

    ModelRenderer action1;
    ModelRenderer action2;
    ModelRenderer action3;

    public MP18action() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action1 = new ModelRenderer(this, 0, 0);
        this.action1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
        this.action1.setRotationPoint(-2.5F, -7.5F, -18.0F);
        this.action1.setTextureSize(64, 32);
        this.action1.mirror = true;
        this.setRotation(this.action1, 0.0F, 0.0F, 0.0F);
        this.action2 = new ModelRenderer(this, 0, 0);
        this.action2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.action2.setRotationPoint(-2.5F, -7.5F, -9.0F);
        this.action2.setTextureSize(64, 32);
        this.action2.mirror = true;
        this.setRotation(this.action2, 0.0F, 0.0F, -1.152537F);
        this.action3 = new ModelRenderer(this, 0, 0);
        this.action3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.action3.setRotationPoint(-4.8F, -7.5F, -18.1F);
        this.action3.setTextureSize(64, 32);
        this.action3.mirror = true;
        this.setRotation(this.action3, 0.0F, -0.2230717F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.action1.render(f5);
        this.action2.render(f5);
        this.action3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
