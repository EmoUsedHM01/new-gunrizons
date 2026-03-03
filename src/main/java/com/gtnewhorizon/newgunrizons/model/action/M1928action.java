package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M1928action extends ModelBase {

    ModelRenderer action5;
    ModelRenderer action6;
    ModelRenderer action7;
    ModelRenderer action8;
    ModelRenderer action9;
    ModelRenderer action10;
    ModelRenderer action11;
    ModelRenderer action12;

    public M1928action() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action5 = new ModelRenderer(this, 0, 0);
        this.action5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 7);
        this.action5.setRotationPoint(-2.8F, -8.8F, -14.5F);
        this.action5.setTextureSize(64, 32);
        this.action5.mirror = true;
        this.setRotation(this.action5, 0.0F, 0.0F, 0.0F);
        this.action6 = new ModelRenderer(this, 0, 0);
        this.action6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action6.setRotationPoint(-1.8F, -9.8F, -14.5F);
        this.action6.setTextureSize(64, 32);
        this.action6.mirror = true;
        this.setRotation(this.action6, 0.0F, 0.0F, 0.0F);
        this.action7 = new ModelRenderer(this, 0, 0);
        this.action7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action7.setRotationPoint(-1.8F, -9.8F, -14.5F);
        this.action7.setTextureSize(64, 32);
        this.action7.mirror = true;
        this.setRotation(this.action7, 0.0F, 0.0F, 0.7853982F);
        this.action8 = new ModelRenderer(this, 0, 0);
        this.action8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action8.setRotationPoint(-2.8F, -8.8F, -14.5F);
        this.action8.setTextureSize(64, 32);
        this.action8.mirror = true;
        this.setRotation(this.action8, 0.0F, 0.0F, -0.7853982F);
        this.action9 = new ModelRenderer(this, 0, 0);
        this.action9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 18);
        this.action9.setRotationPoint(-1.5F, -10.0F, -6.5F);
        this.action9.setTextureSize(64, 32);
        this.action9.mirror = true;
        this.setRotation(this.action9, 0.0F, 0.0F, 0.0F);
        this.action10 = new ModelRenderer(this, 0, 0);
        this.action10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action10.setRotationPoint(-1.4F, -11.3F, -6.5F);
        this.action10.setTextureSize(64, 32);
        this.action10.mirror = true;
        this.setRotation(this.action10, 0.0F, 0.0F, 0.7853982F);
        this.action11 = new ModelRenderer(this, 0, 0);
        this.action11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action11.setRotationPoint(-0.6F, -11.3F, -6.5F);
        this.action11.setTextureSize(64, 32);
        this.action11.mirror = true;
        this.setRotation(this.action11, 0.0F, 0.0F, 0.7853982F);
        this.action12 = new ModelRenderer(this, 0, 0);
        this.action12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action12.setRotationPoint(-1.5F, -10.5F, -6.5F);
        this.action12.setTextureSize(64, 32);
        this.action12.mirror = true;
        this.setRotation(this.action12, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.action5.render(f5);
        this.action6.render(f5);
        this.action7.render(f5);
        this.action8.render(f5);
        this.action9.render(f5);
        this.action10.render(f5);
        this.action11.render(f5);
        this.action12.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
