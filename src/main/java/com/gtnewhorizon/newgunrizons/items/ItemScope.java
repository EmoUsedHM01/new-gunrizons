package com.gtnewhorizon.newgunrizons.items;

import java.util.function.BiConsumer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.scope.ScopeRenderer;
import com.gtnewhorizon.newgunrizons.config.ModContext;

/**
 * A scope attachment that provides zoom capabilities.
 * <p>
 * Scopes can be optical (with a viewfinder overlay) and optionally provide night vision.
 * Zoom level is configured via the {@link Builder}.
 */
public class ItemScope extends ItemAttachment {

    private final float minZoom;
    private final float maxZoom;
    private final boolean isOpticalZoom;
    private final boolean hasNightVision;

    private ItemScope(ItemScope.Builder builder) {
        super(builder.getModId(), AttachmentCategory.SCOPE, null);
        this.minZoom = builder.minZoom;
        this.maxZoom = builder.maxZoom;
        this.isOpticalZoom = builder.isOpticalZoom;
        this.hasNightVision = builder.hasNightVision;
    }

    public float getMinZoom() {
        return this.minZoom;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public boolean isOptical() {
        return this.isOpticalZoom;
    }

    public boolean hasNightVision() {
        return this.hasNightVision;
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
            this.applyHandler = (a, instance) -> {
                float zoom = this.minZoom + (this.maxZoom - this.minZoom) / 2.0F;
                instance.setZoom(zoom);
            };
            this.removeHandler = (a, instance) -> instance.setZoom(1.0F);
            return super.build(modContext);
        }
    }
}
