package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.gtnewhorizon.newgunrizons.attachment.ModelWithAttachments;

public class M1CarbineAction extends ModelWithAttachments {

    ModelRenderer action81;
    ModelRenderer action90;
    ModelRenderer action91;
    ModelRenderer action92;
    ModelRenderer action93;
    ModelRenderer action94;
    ModelRenderer action149;
    ModelRenderer action200;
    ModelRenderer action201;
    ModelRenderer action202;
    ModelRenderer action203;

    public M1CarbineAction() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action81 = new ModelRenderer(this, 150, 0);
        this.action81.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.action81.setRotationPoint(-5.5F, -12.5F, -21.5F);
        this.action81.setTextureSize(64, 32);
        this.action81.mirror = true;
        this.setRotation(this.action81, 0.0F, 0.0F, 0.0F);
        this.action90 = new ModelRenderer(this, 150, 0);
        this.action90.addBox(0.0F, 0.0F, 0.0F, 2, 1, 8);
        this.action90.setRotationPoint(-2.5F, -12.8F, -22.5F);
        this.action90.setTextureSize(64, 32);
        this.action90.mirror = true;
        this.setRotation(this.action90, 0.0F, 0.0F, 0.0F);
        this.action91 = new ModelRenderer(this, 150, 0);
        this.action91.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.action91.setRotationPoint(-2.5F, -13.0F, -23.0F);
        this.action91.setTextureSize(64, 32);
        this.action91.mirror = true;
        this.setRotation(this.action91, 0.0F, 0.0F, 0.0F);
        this.action92 = new ModelRenderer(this, 150, 0);
        this.action92.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.action92.setRotationPoint(-2.5F, -13.0F, -23.0F);
        this.action92.setTextureSize(64, 32);
        this.action92.mirror = true;
        this.setRotation(this.action92, 0.0F, 0.0F, 0.5948578F);
        this.action93 = new ModelRenderer(this, 150, 0);
        this.action93.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.action93.setRotationPoint(-2.0F, -13.2F, -23.0F);
        this.action93.setTextureSize(64, 32);
        this.action93.mirror = true;
        this.setRotation(this.action93, 0.0F, 0.0F, 0.0F);
        this.action94 = new ModelRenderer(this, 150, 0);
        this.action94.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
        this.action94.setRotationPoint(-2.0F, -13.0F, -22.5F);
        this.action94.setTextureSize(64, 32);
        this.action94.mirror = true;
        this.setRotation(this.action94, 0.0F, 0.0F, 0.0F);
        this.action149 = new ModelRenderer(this, 150, 0);
        this.action149.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.action149.setRotationPoint(-5.5F, -13.0F, -21.5F);
        this.action149.setTextureSize(64, 32);
        this.action149.mirror = true;
        this.setRotation(this.action149, 0.0F, 0.0F, 0.0F);
        this.action200 = new ModelRenderer(this, 150, 0);
        this.action200.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action200.setRotationPoint(-3.5F, -13.0F, -22.0F);
        this.action200.setTextureSize(64, 32);
        this.action200.mirror = true;
        this.setRotation(this.action200, 0.0F, 0.0F, 0.0F);
        this.action201 = new ModelRenderer(this, 150, 0);
        this.action201.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action201.setRotationPoint(-3.5F, -12.5F, -22.0F);
        this.action201.setTextureSize(64, 32);
        this.action201.mirror = true;
        this.setRotation(this.action201, 0.0F, 0.0F, 0.0F);
        this.action202 = new ModelRenderer(this, 150, 0);
        this.action202.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action202.setRotationPoint(-3.5F, -12.5F, -22.0F);
        this.action202.setTextureSize(64, 32);
        this.action202.mirror = true;
        this.setRotation(this.action202, 0.0F, -1.041001F, 0.0F);
        this.action203 = new ModelRenderer(this, 150, 0);
        this.action203.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action203.setRotationPoint(-3.5F, -13.0F, -22.0F);
        this.action203.setTextureSize(64, 32);
        this.action203.mirror = true;
        this.setRotation(this.action203, 0.0F, -1.041001F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.action81.render(f5);
        this.action90.render(f5);
        this.action91.render(f5);
        this.action92.render(f5);
        this.action93.render(f5);
        this.action94.render(f5);
        this.action149.render(f5);
        this.action200.render(f5);
        this.action201.render(f5);
        this.action202.render(f5);
        this.action203.render(f5);
    }
}
