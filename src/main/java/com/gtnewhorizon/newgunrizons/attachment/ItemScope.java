package com.gtnewhorizon.newgunrizons.attachment;

import java.util.function.BiConsumer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.client.scope.ScopeRenderer;
import com.gtnewhorizon.newgunrizons.config.ModContext;

public class ItemScope extends ItemAttachment {

    private final ItemScope.Builder builder;

    private ItemScope(ItemScope.Builder builder) {
        super(
            builder.getModId(),
            AttachmentCategory.SCOPE,
            builder.getModel(),
            builder.getTextureName(),
            null,
            null,
            null);
        this.builder = builder;
        this.setMaxStackSize(1);
    }

    public float getMinZoom() {
        return this.builder.minZoom;
    }

    public float getMaxZoom() {
        return this.builder.maxZoom;
    }

    public boolean isOptical() {
        return this.builder.isOpticalZoom;
    }

    public boolean hasNightVision() {
        return this.builder.hasNightVision;
    }

    public static final class Builder extends AttachmentBuilder {

        private float minZoom;
        private float maxZoom;
        private boolean isOpticalZoom;
        private boolean hasNightVision;
        private BiConsumer<EntityLivingBase, ItemStack> viewfinderPositioning;

        public ItemScope.Builder withZoomRange(float minZoom, float maxZoom) {
            this.minZoom = minZoom;
            this.maxZoom = maxZoom;
            return this;
        }

        public ItemScope.Builder withOpticalZoom() {
            this.isOpticalZoom = true;
            return this;
        }

        public ItemScope.Builder withNightVision() {
            this.hasNightVision = true;
            return this;
        }

        public ItemScope.Builder withViewfinderPositioning(
            BiConsumer<EntityLivingBase, ItemStack> viewfinderPositioning) {
            this.viewfinderPositioning = viewfinderPositioning;
            return this;
        }

        public ItemAttachment createAttachment(ModContext modContext) {
            if (this.isOpticalZoom) {
                if (this.viewfinderPositioning == null) {
                    this.viewfinderPositioning = (p, s) -> {
                        GL11.glScalef(1.1F, 1.1F, 1.1F);
                        GL11.glTranslatef(0.1F, 0.4F, 0.6F);
                    };
                }

                withPostRender(new ScopeRenderer(this.viewfinderPositioning));
            }

            return new ItemScope(this);
        }

        public ItemAttachment build(ModContext modContext) {
            this.apply2 = (a, instance) -> {
                float zoom = this.minZoom + (this.maxZoom - this.minZoom) / 2.0F;
                instance.setZoom(zoom);
            };
            this.remove2 = (a, instance) -> instance.setZoom(1.0F);
            return super.build(modContext);
        }
    }
}
