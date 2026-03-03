package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SKSaction extends ModelBase {

    ModelRenderer action1;
    ModelRenderer action2;
    ModelRenderer action3;
    ModelRenderer action4;
    ModelRenderer action5;
    ModelRenderer action6;
    ModelRenderer action7;
    ModelRenderer action8;
    ModelRenderer action9;
    ModelRenderer action10;

    public SKSaction() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action1 = new ModelRenderer(this, 150, 0);
        this.action1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10);
        this.action1.setRotationPoint(-1.5F, -8.4F, -11.0F);
        this.action1.setTextureSize(64, 32);
        this.action1.mirror = true;
        this.setRotation(this.action1, 0.0F, 0.0F, 0.0F);
        this.action2 = new ModelRenderer(this, 150, 0);
        this.action2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10);
        this.action2.setRotationPoint(-0.5F, -8.4F, -11.0F);
        this.action2.setTextureSize(64, 32);
        this.action2.mirror = true;
        this.setRotation(this.action2, 0.0F, 0.0F, 0.5948578F);
        this.action3 = new ModelRenderer(this, 150, 0);
        this.action3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10);
        this.action3.setRotationPoint(-1.5F, -8.4F, -11.0F);
        this.action3.setTextureSize(64, 32);
        this.action3.mirror = true;
        this.setRotation(this.action3, 0.0F, 0.0F, 0.9666439F);
        this.action4 = new ModelRenderer(this, 150, 0);
        this.action4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10);
        this.action4.setRotationPoint(-0.65F, -7.85F, -11.0F);
        this.action4.setTextureSize(64, 32);
        this.action4.mirror = true;
        this.setRotation(this.action4, 0.0F, 0.0F, 0.0F);
        this.action5 = new ModelRenderer(this, 150, 0);
        this.action5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 10);
        this.action5.setRotationPoint(-2.3F, -7.85F, -11.0F);
        this.action5.setTextureSize(64, 32);
        this.action5.mirror = true;
        this.setRotation(this.action5, 0.0F, 0.0F, 0.0F);
        this.action6 = new ModelRenderer(this, 150, 0);
        this.action6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.action6.setRotationPoint(-2.3F, -8.5F, -11.0F);
        this.action6.setTextureSize(64, 32);
        this.action6.mirror = true;
        this.setRotation(this.action6, 0.0F, 0.0F, 0.0F);
        this.action7 = new ModelRenderer(this, 150, 0);
        this.action7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.action7.setRotationPoint(-0.65F, -8.5F, -11.0F);
        this.action7.setTextureSize(64, 32);
        this.action7.mirror = true;
        this.setRotation(this.action7, 0.0F, 0.0F, 0.0F);
        this.action8 = new ModelRenderer(this, 150, 0);
        this.action8.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action8.setRotationPoint(-2.3F, -8.5F, -9.0F);
        this.action8.setTextureSize(64, 32);
        this.action8.mirror = true;
        this.setRotation(this.action8, -0.7853982F, 0.0F, 0.0F);
        this.action9 = new ModelRenderer(this, 150, 0);
        this.action9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action9.setRotationPoint(-0.65F, -8.5F, -9.0F);
        this.action9.setTextureSize(64, 32);
        this.action9.mirror = true;
        this.setRotation(this.action9, -0.7853982F, 0.0F, 0.0F);
        this.action10 = new ModelRenderer(this, 150, 0);
        this.action10.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action10.setRotationPoint(-4.3F, -8.3F, -10.3F);
        this.action10.setTextureSize(64, 32);
        this.action10.mirror = true;
        this.setRotation(this.action10, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.action1.render(f5);
        this.action2.render(f5);
        this.action3.render(f5);
        this.action4.render(f5);
        this.action5.render(f5);
        this.action6.render(f5);
        this.action7.render(f5);
        this.action8.render(f5);
        this.action9.render(f5);
        this.action10.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
