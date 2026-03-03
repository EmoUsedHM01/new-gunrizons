package com.gtnewhorizon.newgunrizons.model.magazine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LugerMag extends ModelBase {

    ModelRenderer mag1;
    ModelRenderer mag2;
    ModelRenderer mag3;
    ModelRenderer mag4;
    ModelRenderer mag5;
    ModelRenderer mag6;
    ModelRenderer mag7;
    ModelRenderer mag8;
    ModelRenderer mag9;
    ModelRenderer mag10;
    ModelRenderer mag11;

    public LugerMag() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.mag1 = new ModelRenderer(this, 0, 0);
        this.mag1.addBox(0.0F, 0.0F, 0.0F, 3, 16, 6);
        this.mag1.setRotationPoint(-3.3F, 5.4F, 6.8F);
        this.mag1.setTextureSize(64, 32);
        this.mag1.mirror = true;
        this.setRotation(this.mag1, -2.67686F, 0.0F, 0.0F);
        this.mag2 = new ModelRenderer(this, 0, 0);
        this.mag2.addBox(0.0F, 0.0F, 0.0F, 1, 16, 6);
        this.mag2.setRotationPoint(-0.7F, 5.4F, 6.8F);
        this.mag2.setTextureSize(64, 32);
        this.mag2.mirror = true;
        this.setRotation(this.mag2, -2.67686F, 0.0F, 0.0F);
        this.mag3 = new ModelRenderer(this, 0, 0);
        this.mag3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3);
        this.mag3.setRotationPoint(-3.3F, 5.4F, 6.8F);
        this.mag3.setTextureSize(64, 32);
        this.mag3.mirror = true;
        this.setRotation(this.mag3, -2.007645F, 0.0F, 0.0F);
        this.mag4 = new ModelRenderer(this, 0, 0);
        this.mag4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3);
        this.mag4.setRotationPoint(-0.7F, 5.4F, 6.8F);
        this.mag4.setTextureSize(64, 32);
        this.mag4.mirror = true;
        this.setRotation(this.mag4, -2.007645F, 0.0F, 0.0F);
        this.mag5 = new ModelRenderer(this, 0, 0);
        this.mag5.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
        this.mag5.setRotationPoint(-3.3F, 8.2F, 1.5F);
        this.mag5.setTextureSize(64, 32);
        this.mag5.mirror = true;
        this.setRotation(this.mag5, 1.07818F, 0.0F, 0.0F);
        this.mag6 = new ModelRenderer(this, 0, 0);
        this.mag6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
        this.mag6.setRotationPoint(-0.7F, 8.2F, 1.5F);
        this.mag6.setTextureSize(64, 32);
        this.mag6.mirror = true;
        this.setRotation(this.mag6, 1.07818F, 0.0F, 0.0F);
        this.mag7 = new ModelRenderer(this, 0, 0);
        this.mag7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
        this.mag7.setRotationPoint(-0.7F, 7.4F, 2.5F);
        this.mag7.setTextureSize(64, 32);
        this.mag7.mirror = true;
        this.setRotation(this.mag7, 0.4461433F, 0.0F, 0.0F);
        this.mag8 = new ModelRenderer(this, 0, 0);
        this.mag8.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
        this.mag8.setRotationPoint(-3.3F, 7.4F, 2.5F);
        this.mag8.setTextureSize(64, 32);
        this.mag8.mirror = true;
        this.setRotation(this.mag8, 0.4461433F, 0.0F, 0.0F);
        this.mag9 = new ModelRenderer(this, 0, 0);
        this.mag9.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
        this.mag9.setRotationPoint(-3.3F, 6.75F, 3.8F);
        this.mag9.setTextureSize(64, 32);
        this.mag9.mirror = true;
        this.setRotation(this.mag9, 0.4461433F, 0.0F, 0.0F);
        this.mag10 = new ModelRenderer(this, 0, 0);
        this.mag10.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.mag10.setRotationPoint(-0.7F, 6.75F, 3.8F);
        this.mag10.setTextureSize(64, 32);
        this.mag10.mirror = true;
        this.setRotation(this.mag10, 0.4461433F, 0.0F, 0.0F);
        this.mag11 = new ModelRenderer(this, 0, 0);
        this.mag11.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2);
        this.mag11.setRotationPoint(-3.5F, 7.8F, 3.0F);
        this.mag11.setTextureSize(64, 32);
        this.mag11.mirror = true;
        this.setRotation(this.mag11, 0.4461433F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.mag1.render(f5);
        this.mag2.render(f5);
        this.mag3.render(f5);
        this.mag4.render(f5);
        this.mag5.render(f5);
        this.mag6.render(f5);
        this.mag7.render(f5);
        this.mag8.render(f5);
        this.mag9.render(f5);
        this.mag10.render(f5);
        this.mag11.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
