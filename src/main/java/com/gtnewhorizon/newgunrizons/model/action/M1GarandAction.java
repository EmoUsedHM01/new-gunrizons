package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M1GarandAction extends ModelBase {

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
    ModelRenderer action11;
    ModelRenderer action12;
    ModelRenderer action13;
    ModelRenderer action14;

    public M1GarandAction() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action1 = new ModelRenderer(this, 150, 0);
        this.action1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action1.setRotationPoint(-5.0F, -13.5F, -21.0F);
        this.action1.setTextureSize(64, 32);
        this.action1.mirror = true;
        this.setRotation(this.action1, 0.0F, 0.0F, 0.0F);
        this.action2 = new ModelRenderer(this, 150, 0);
        this.action2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action2.setRotationPoint(-5.5F, -13.5F, -21.0F);
        this.action2.setTextureSize(64, 32);
        this.action2.mirror = true;
        this.setRotation(this.action2, 0.0F, 0.0F, 0.0F);
        this.action3 = new ModelRenderer(this, 150, 0);
        this.action3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action3.setRotationPoint(-5.5F, -13.5F, -21.0F);
        this.action3.setTextureSize(64, 32);
        this.action3.mirror = true;
        this.setRotation(this.action3, 0.0F, 0.2602503F, 0.0F);
        this.action4 = new ModelRenderer(this, 150, 0);
        this.action4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action4.setRotationPoint(-1.5F, -14.5F, -22.5F);
        this.action4.setTextureSize(64, 32);
        this.action4.mirror = true;
        this.setRotation(this.action4, 0.0F, 0.0F, 1.375609F);
        this.action5 = new ModelRenderer(this, 150, 0);
        this.action5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action5.setRotationPoint(-1.5F, -14.5F, -22.5F);
        this.action5.setTextureSize(64, 32);
        this.action5.mirror = true;
        this.setRotation(this.action5, 0.0F, 0.0F, 0.2602503F);
        this.action6 = new ModelRenderer(this, 150, 0);
        this.action6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action6.setRotationPoint(-1.5F, -14.0F, -21.5F);
        this.action6.setTextureSize(64, 32);
        this.action6.mirror = true;
        this.setRotation(this.action6, 0.0F, 0.0F, 0.2602503F);
        this.action7 = new ModelRenderer(this, 150, 0);
        this.action7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action7.setRotationPoint(-1.5F, -14.0F, -21.5F);
        this.action7.setTextureSize(64, 32);
        this.action7.mirror = true;
        this.setRotation(this.action7, 0.0F, 0.0F, 1.375609F);
        this.action8 = new ModelRenderer(this, 150, 0);
        this.action8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action8.setRotationPoint(-2.0F, -14.4F, -22.5F);
        this.action8.setTextureSize(64, 32);
        this.action8.mirror = true;
        this.setRotation(this.action8, 0.0F, 0.0F, 1.375609F);
        this.action9 = new ModelRenderer(this, 150, 0);
        this.action9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action9.setRotationPoint(-2.0F, -13.9F, -21.5F);
        this.action9.setTextureSize(64, 32);
        this.action9.mirror = true;
        this.setRotation(this.action9, 0.0F, 0.0F, 1.375609F);
        this.action10 = new ModelRenderer(this, 150, 0);
        this.action10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action10.setRotationPoint(-3.0F, -14.2F, -22.5F);
        this.action10.setTextureSize(64, 32);
        this.action10.mirror = true;
        this.setRotation(this.action10, 0.0F, 0.0F, 0.4461433F);
        this.action11 = new ModelRenderer(this, 150, 0);
        this.action11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action11.setRotationPoint(-3.0F, -13.7F, -21.5F);
        this.action11.setTextureSize(64, 32);
        this.action11.mirror = true;
        this.setRotation(this.action11, 0.0F, 0.0F, 0.4461433F);
        this.action12 = new ModelRenderer(this, 150, 0);
        this.action12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action12.setRotationPoint(-4.0F, -14.5F, -23.5F);
        this.action12.setTextureSize(64, 32);
        this.action12.mirror = true;
        this.setRotation(this.action12, 0.0F, 0.0F, 0.0F);
        this.action13 = new ModelRenderer(this, 150, 0);
        this.action13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.action13.setRotationPoint(-4.0F, -14.5F, -22.5F);
        this.action13.setTextureSize(64, 32);
        this.action13.mirror = true;
        this.setRotation(this.action13, -1.041001F, 0.0F, 0.0F);
        this.action14 = new ModelRenderer(this, 150, 0);
        this.action14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.action14.setRotationPoint(-4.0F, -13.5F, -23.5F);
        this.action14.setTextureSize(64, 32);
        this.action14.mirror = true;
        this.setRotation(this.action14, 0.0F, 0.0F, 0.0F);
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
        this.action11.render(f5);
        this.action12.render(f5);
        this.action13.render(f5);
        this.action14.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
