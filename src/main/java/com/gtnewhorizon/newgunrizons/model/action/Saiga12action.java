package com.gtnewhorizon.newgunrizons.model.action;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.gtnewhorizon.newgunrizons.attachment.ModelWithAttachments;

public class Saiga12action extends ModelWithAttachments {

    ModelRenderer action1;
    ModelRenderer action2;
    ModelRenderer action3;
    ModelRenderer action4;
    ModelRenderer action5;
    ModelRenderer action6;
    ModelRenderer action7;
    ModelRenderer action8;
    ModelRenderer action9;

    public Saiga12action() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.action1 = new ModelRenderer(this, 100, 0);
        this.action1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 13);
        this.action1.setRotationPoint(-2.6F, -15.2F, -33.5F);
        this.action1.setTextureSize(64, 32);
        this.action1.mirror = true;
        this.setRotation(this.action1, 0.0F, 0.0F, 0.3346075F);
        this.action2 = new ModelRenderer(this, 100, 0);
        this.action2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 23);
        this.action2.setRotationPoint(-3.5F, -13.0F, -33.5F);
        this.action2.setTextureSize(64, 32);
        this.action2.mirror = true;
        this.setRotation(this.action2, 0.0F, 0.0F, 0.0F);
        this.action3 = new ModelRenderer(this, 100, 0);
        this.action3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 15);
        this.action3.setRotationPoint(-3.5F, -13.0F, -33.5F);
        this.action3.setTextureSize(64, 32);
        this.action3.mirror = true;
        this.setRotation(this.action3, 0.0F, 0.0F, -1.07818F);
        this.action4 = new ModelRenderer(this, 100, 0);
        this.action4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
        this.action4.setRotationPoint(-3.6F, -13.0F, -26.5F);
        this.action4.setTextureSize(64, 32);
        this.action4.mirror = true;
        this.setRotation(this.action4, 0.0F, 0.0F, -1.07818F);
        this.action5 = new ModelRenderer(this, 100, 0);
        this.action5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6);
        this.action5.setRotationPoint(-2.7F, -15.2F, -26.5F);
        this.action5.setTextureSize(64, 32);
        this.action5.mirror = true;
        this.setRotation(this.action5, 0.0F, 0.0F, 0.3346075F);
        this.action6 = new ModelRenderer(this, 100, 0);
        this.action6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
        this.action6.setRotationPoint(-3.7F, -13.0F, -33.5F);
        this.action6.setTextureSize(64, 32);
        this.action6.mirror = true;
        this.setRotation(this.action6, 0.0F, 0.0F, 0.0F);
        this.action7 = new ModelRenderer(this, 100, 0);
        this.action7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.action7.setRotationPoint(-3.7F, -13.0F, -29.5F);
        this.action7.setTextureSize(64, 32);
        this.action7.mirror = true;
        this.setRotation(this.action7, 0.0F, 0.2648976F, 0.0F);
        this.action8 = new ModelRenderer(this, 100, 0);
        this.action8.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
        this.action8.setRotationPoint(-6.0F, -13.0F, -33.0F);
        this.action8.setTextureSize(64, 32);
        this.action8.mirror = true;
        this.setRotation(this.action8, 0.0F, 0.0F, 0.0F);
        this.action9 = new ModelRenderer(this, 100, 0);
        this.action9.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.action9.setRotationPoint(-4.7F, -13.0F, -33.0F);
        this.action9.setTextureSize(64, 32);
        this.action9.mirror = true;
        this.setRotation(this.action9, 0.0F, 0.3346075F, 0.0F);
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
    }
}
