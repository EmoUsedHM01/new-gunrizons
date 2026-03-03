package com.gtnewhorizon.newgunrizons.model.magazine;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.gtnewhorizon.newgunrizons.attachment.ModelWithAttachments;

public class M1A1mag extends ModelWithAttachments {

    ModelRenderer mag1;
    ModelRenderer mag2;
    ModelRenderer mag3;
    ModelRenderer mag4;
    ModelRenderer mag5;
    ModelRenderer mag6;

    public M1A1mag() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.mag1 = new ModelRenderer(this, 0, 0);
        this.mag1.addBox(0.0F, 0.0F, 0.0F, 2, 25, 4);
        this.mag1.setRotationPoint(-2.3F, -7.5F, -11.5F);
        this.mag1.setTextureSize(64, 32);
        this.mag1.mirror = true;
        this.setRotation(this.mag1, 0.0F, 0.0F, 0.0F);
        this.mag2 = new ModelRenderer(this, 0, 0);
        this.mag2.addBox(0.0F, 0.0F, 0.0F, 1, 25, 4);
        this.mag2.setRotationPoint(-0.7F, -7.5F, -11.5F);
        this.mag2.setTextureSize(64, 32);
        this.mag2.mirror = true;
        this.setRotation(this.mag2, 0.0F, 0.0F, 0.0F);
        this.mag3 = new ModelRenderer(this, 0, 0);
        this.mag3.addBox(0.0F, 0.0F, 0.0F, 2, 25, 1);
        this.mag3.setRotationPoint(-2.0F, -7.5F, -12.5F);
        this.mag3.setTextureSize(64, 32);
        this.mag3.mirror = true;
        this.setRotation(this.mag3, 0.0F, 0.0F, 0.0F);
        this.mag4 = new ModelRenderer(this, 0, 0);
        this.mag4.addBox(0.0F, 0.0F, 0.0F, 1, 25, 1);
        this.mag4.setRotationPoint(0.3F, -7.5F, -11.5F);
        this.mag4.setTextureSize(64, 32);
        this.mag4.mirror = true;
        this.setRotation(this.mag4, 0.0F, -2.876695F, 0.0F);
        this.mag5 = new ModelRenderer(this, 0, 0);
        this.mag5.addBox(0.0F, 0.0F, 0.0F, 1, 25, 1);
        this.mag5.setRotationPoint(-2.3F, -7.5F, -11.5F);
        this.mag5.setTextureSize(64, 32);
        this.mag5.mirror = true;
        this.setRotation(this.mag5, 0.0F, 1.301251F, 0.0F);
        this.mag6 = new ModelRenderer(this, 0, 0);
        this.mag6.addBox(0.0F, 0.0F, 0.0F, 2, 25, 1);
        this.mag6.setRotationPoint(-2.0F, -7.5F, -8.0F);
        this.mag6.setTextureSize(64, 32);
        this.mag6.mirror = true;
        this.setRotation(this.mag6, 0.0F, 0.0F, 0.0F);
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
    }
}
