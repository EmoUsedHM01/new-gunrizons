package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LugerAction2 extends ModelBase {

    ModelRenderer ACTION1;
    ModelRenderer ACTION2;
    ModelRenderer ACTION3;
    ModelRenderer ACTION4;
    ModelRenderer ACTION5;

    public LugerAction2() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.ACTION1 = new ModelRenderer(this, 0, 0);
        this.ACTION1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
        this.ACTION1.setRotationPoint(-2.5F, -11.8F, -5.0F);
        this.ACTION1.setTextureSize(64, 32);
        this.ACTION1.mirror = true;
        this.setRotation(this.ACTION1, 0.0F, 0.0F, 0.0F);
        this.ACTION2 = new ModelRenderer(this, 0, 0);
        this.ACTION2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
        this.ACTION2.setRotationPoint(-2.0F, -11.5F, -10.0F);
        this.ACTION2.setTextureSize(64, 32);
        this.ACTION2.mirror = true;
        this.setRotation(this.ACTION2, 0.0F, 0.0F, 0.0F);
        this.ACTION3 = new ModelRenderer(this, 0, 0);
        this.ACTION3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
        this.ACTION3.setRotationPoint(-1.0F, -11.5F, -10.0F);
        this.ACTION3.setTextureSize(64, 32);
        this.ACTION3.mirror = true;
        this.setRotation(this.ACTION3, 0.0F, 0.0F, 0.2602503F);
        this.ACTION4 = new ModelRenderer(this, 0, 0);
        this.ACTION4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
        this.ACTION4.setRotationPoint(-2.0F, -11.5F, -10.0F);
        this.ACTION4.setTextureSize(64, 32);
        this.ACTION4.mirror = true;
        this.setRotation(this.ACTION4, 0.0F, 0.0F, 1.375609F);
        this.ACTION5 = new ModelRenderer(this, 0, 0);
        this.ACTION5.addBox(0.0F, 0.0F, 0.0F, 3, 1, 5);
        this.ACTION5.setRotationPoint(-3.0F, -11.6F, -5.0F);
        this.ACTION5.setTextureSize(64, 32);
        this.ACTION5.mirror = true;
        this.setRotation(this.ACTION5, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.ACTION1.render(f5);
        this.ACTION2.render(f5);
        this.ACTION3.render(f5);
        this.ACTION4.render(f5);
        this.ACTION5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
