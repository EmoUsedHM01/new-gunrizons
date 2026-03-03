package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M107action extends ModelBase {

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
    ModelRenderer action14;

    public M107action() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action1 = new ModelRenderer(this, 0, 0);
        this.action1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 32);
        this.action1.setRotationPoint(-4.8F, -14.5F, -33.0F);
        this.action1.setTextureSize(64, 32);
        this.action1.mirror = true;
        this.setRotation(this.action1, 0.0F, 0.0F, 0.0F);
        this.action2 = new ModelRenderer(this, 0, 0);
        this.action2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 15);
        this.action2.setRotationPoint(-4.8F, -14.5F, -33.0F);
        this.action2.setTextureSize(64, 32);
        this.action2.mirror = true;
        this.setRotation(this.action2, 0.0F, 0.0F, -1.041001F);
        this.action3 = new ModelRenderer(this, 0, 0);
        this.action3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action3.setRotationPoint(-4.9F, -14.5F, -8.0F);
        this.action3.setTextureSize(64, 32);
        this.action3.mirror = true;
        this.setRotation(this.action3, 0.0F, 0.0F, 0.0F);
        this.action4 = new ModelRenderer(this, 0, 0);
        this.action4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action4.setRotationPoint(-4.9F, -14.5F, -6.0F);
        this.action4.setTextureSize(64, 32);
        this.action4.mirror = true;
        this.setRotation(this.action4, 0.0F, 0.0F, 0.0F);
        this.action5 = new ModelRenderer(this, 0, 0);
        this.action5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action5.setRotationPoint(-4.9F, -14.5F, -4.0F);
        this.action5.setTextureSize(64, 32);
        this.action5.mirror = true;
        this.setRotation(this.action5, 0.0F, 0.0F, 0.0F);
        this.action6 = new ModelRenderer(this, 0, 0);
        this.action6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action6.setRotationPoint(-4.9F, -14.5F, -2.0F);
        this.action6.setTextureSize(64, 32);
        this.action6.mirror = true;
        this.setRotation(this.action6, 0.0F, 0.0F, 0.0F);
        this.action7 = new ModelRenderer(this, 0, 0);
        this.action7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 9);
        this.action7.setRotationPoint(-4.8F, -14.5F, -18.0F);
        this.action7.setTextureSize(64, 32);
        this.action7.mirror = true;
        this.setRotation(this.action7, 0.0F, 0.0F, -1.041001F);
        this.action8 = new ModelRenderer(this, 0, 0);
        this.action8.addBox(0.0F, 0.0F, 0.0F, 2, 1, 6);
        this.action8.setRotationPoint(-4.6F, -14.5F, -18.0F);
        this.action8.setTextureSize(64, 32);
        this.action8.mirror = true;
        this.setRotation(this.action8, 0.0F, 0.0F, -1.041001F);
        this.action9 = new ModelRenderer(this, 0, 0);
        this.action9.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action9.setRotationPoint(-6.3F, -14.5F, -33.0F);
        this.action9.setTextureSize(64, 32);
        this.action9.mirror = true;
        this.setRotation(this.action9, 0.0F, 0.0F, 0.0F);
        this.action10 = new ModelRenderer(this, 0, 0);
        this.action10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.action10.setRotationPoint(-6.3F, -13.5F, -33.0F);
        this.action10.setTextureSize(64, 32);
        this.action10.mirror = true;
        this.setRotation(this.action10, 1.933288F, 0.0F, 0.0F);
        this.action14 = new ModelRenderer(this, 0, 0);
        this.action14.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.action14.setRotationPoint(-8.3F, -15.0F, -33.55F);
        this.action14.setTextureSize(64, 32);
        this.action14.mirror = true;
        this.setRotation(this.action14, 1.933288F, 0.0F, 0.0F);
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
        this.action14.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
