package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.gtnewhorizon.newgunrizons.attachment.ModelWithAttachments;

public class PPSH41action extends ModelWithAttachments {

    ModelRenderer action1;
    ModelRenderer action2;
    ModelRenderer action3;
    ModelRenderer action4;
    ModelRenderer action5;
    ModelRenderer action6;

    public PPSH41action() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action1 = new ModelRenderer(this, 0, 0);
        this.action1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 15);
        this.action1.setRotationPoint(-2.4F, -8.0F, -15.0F);
        this.action1.setTextureSize(64, 32);
        this.action1.mirror = true;
        this.setRotation(this.action1, 0.0F, 0.0F, 0.0F);
        this.action2 = new ModelRenderer(this, 0, 0);
        this.action2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action2.setRotationPoint(-4.3F, -7.9F, -14.0F);
        this.action2.setTextureSize(64, 32);
        this.action2.mirror = true;
        this.setRotation(this.action2, 0.0F, 0.0F, 0.0F);
        this.action3 = new ModelRenderer(this, 0, 0);
        this.action3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action3.setRotationPoint(-4.3F, -7.9F, -14.2F);
        this.action3.setTextureSize(64, 32);
        this.action3.mirror = true;
        this.setRotation(this.action3, 0.0F, 0.0F, 0.0F);
        this.action4 = new ModelRenderer(this, 0, 0);
        this.action4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action4.setRotationPoint(-4.2F, -8.1F, -14.1F);
        this.action4.setTextureSize(64, 32);
        this.action4.mirror = true;
        this.setRotation(this.action4, 0.0F, 0.0F, 0.0F);
        this.action5 = new ModelRenderer(this, 0, 0);
        this.action5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action5.setRotationPoint(-3.7F, -8.0F, -14.1F);
        this.action5.setTextureSize(64, 32);
        this.action5.mirror = true;
        this.setRotation(this.action5, 0.0F, 0.0F, 0.0F);
        this.action6 = new ModelRenderer(this, 0, 0);
        this.action6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
        this.action6.setRotationPoint(-2.0F, -8.5F, -20.0F);
        this.action6.setTextureSize(64, 32);
        this.action6.mirror = true;
        this.setRotation(this.action6, 0.0F, 0.0F, 0.0F);
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
    }
}
