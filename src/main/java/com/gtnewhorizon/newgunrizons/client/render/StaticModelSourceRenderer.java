package com.gtnewhorizon.newgunrizons.client.render;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.client.shader.Framebuffers;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.util.Pair;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;

public class StaticModelSourceRenderer implements IItemRenderer {

    protected StaticModelSourceRenderer.Builder builder;

    private StaticModelSourceRenderer(StaticModelSourceRenderer.Builder builder) {
        this.builder = builder;
    }

    protected ModContext getModContext() {
        return this.builder.modContext;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
        int currentTextureId = 0;
        if (type == ItemRenderType.INVENTORY) {
            currentTextureId = Framebuffers.getCurrentTexture();
        }

        GL11.glPushMatrix();
        GL11.glScaled(-1.0D, -1.0D, 1.0D);
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        RenderContext renderContext = new RenderContext(this.getModContext(), player, itemStack);
        switch (type) {
            case ENTITY:
                this.builder.getEntityPositioning()
                    .accept(itemStack);
                break;
            case INVENTORY:
                this.builder.getInventoryPositioning()
                    .accept(itemStack);
                break;
            case EQUIPPED:
                this.builder.getThirdPersonPositioning()
                    .accept(player, itemStack);
                break;
            case EQUIPPED_FIRST_PERSON:
                this.builder.getFirstPersonPositioning()
                    .accept(player, itemStack);
                WeaponRenderer.renderLeftArm(
                    player,
                    renderContext,
                    (p, c) -> this.builder.getFirstPersonLeftHandPositioning()
                        .accept(c));
                WeaponRenderer.renderRightArm(
                    player,
                    renderContext,
                    (p, c) -> this.builder.getFirstPersonRightHandPositioning()
                        .accept(c));
        }

        renderModelSource(renderContext, itemStack, type, 0.0F, 0.0F, -0.4F, 0.0F, 0.0F, 0.08F);
        GL11.glPopMatrix();
        if (currentTextureId != 0) {
            Framebuffers.bindTexture(currentTextureId);
        }

    }

    private void renderModelSource(RenderContext renderContext, ItemStack itemStack, ItemRenderType type, float f,
        float f1, float f2, float f3, float f4, float f5) {
        if (!(itemStack.getItem() instanceof ItemAttachment modelSource)) {
            throw new IllegalArgumentException();
        } else {
            GL11.glPushMatrix();
            for (Pair<ModelBase, String> texturedModel : modelSource.getTexturedModels()) {
                Minecraft.getMinecraft().renderEngine.bindTexture(
                    new ResourceLocation(this.builder.getModId() + ":textures/models/" + texturedModel.getV()));
                GL11.glPushMatrix();
                GL11.glPushAttrib(8192);
                ModelBase model = texturedModel.getU();
                switch (type) {
                    case ENTITY:
                        this.builder.getEntityModelPositioning()
                            .accept(model, itemStack);
                        break;
                    case INVENTORY:
                        this.builder.getInventoryModelPositioning()
                            .accept(model, itemStack);
                        break;
                    case EQUIPPED:
                        this.builder.getThirdPersonModelPositioning()
                            .accept(model, itemStack);
                        break;
                    case EQUIPPED_FIRST_PERSON:
                        this.builder.getFirstPersonModelPositioning()
                            .accept(model, itemStack);
                }

                model.render(null, f, f1, f2, f3, f4, f5);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }

            CustomRenderer postRenderer = modelSource.getPostRenderer();
            if (postRenderer != null) {
                renderContext.setAgeInTicks(-0.4F);
                renderContext.setScale(0.08F);
                renderContext.setTransformType(TransformType.fromItemRenderType(type));
                renderContext.setPlayerItemInstance(
                    this.getModContext()
                        .getPlayerItemInstanceRegistry()
                        .getItemInstance(renderContext.getPlayer(), itemStack));
                GL11.glPushMatrix();
                GL11.glPushAttrib(8193);
                postRenderer.render(renderContext);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
        }
    }

    public static class Builder {

        @Getter
        private Consumer<ItemStack> entityPositioning;
        @Getter
        private Consumer<ItemStack> inventoryPositioning;
        @Getter
        private BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning;
        @Getter
        private BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning;
        @Getter
        private BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning;
        @Getter
        private BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning;
        @Getter
        private BiConsumer<ModelBase, ItemStack> inventoryModelPositioning;
        @Getter
        private BiConsumer<ModelBase, ItemStack> entityModelPositioning;
        @Getter
        private Consumer<RenderContext> firstPersonLeftHandPositioning;
        @Getter
        private Consumer<RenderContext> firstPersonRightHandPositioning;
        @Getter
        private String modId;
        private ModContext modContext;

        public StaticModelSourceRenderer.Builder withModId(String modId) {
            this.modId = modId;
            return this;
        }

        public StaticModelSourceRenderer.Builder withModContext(ModContext modContext) {
            this.modContext = modContext;
            return this;
        }

        public StaticModelSourceRenderer.Builder withFirstPersonPositioning(
            BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning) {
            this.firstPersonPositioning = firstPersonPositioning;
            return this;
        }

        public StaticModelSourceRenderer.Builder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioning = leftHand;
            this.firstPersonRightHandPositioning = rightHand;
            return this;
        }

        public StaticModelSourceRenderer.Builder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
            this.entityPositioning = entityPositioning;
            return this;
        }

        public StaticModelSourceRenderer.Builder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
            this.inventoryPositioning = inventoryPositioning;
            return this;
        }

        public StaticModelSourceRenderer.Builder withThirdPersonPositioning(
            BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning) {
            this.thirdPersonPositioning = thirdPersonPositioning;
            return this;
        }

        public StaticModelSourceRenderer.Builder withFirstPersonModelPositioning(
            BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning) {
            this.firstPersonModelPositioning = firstPersonModelPositioning;
            return this;
        }

        public StaticModelSourceRenderer.Builder withEntityModelPositioning(
            BiConsumer<ModelBase, ItemStack> entityModelPositioning) {
            this.entityModelPositioning = entityModelPositioning;
            return this;
        }

        public StaticModelSourceRenderer.Builder withInventoryModelPositioning(
            BiConsumer<ModelBase, ItemStack> inventoryModelPositioning) {
            this.inventoryModelPositioning = inventoryModelPositioning;
            return this;
        }

        public StaticModelSourceRenderer.Builder withThirdPersonModelPositioning(
            BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning) {
            this.thirdPersonModelPositioning = thirdPersonModelPositioning;
            return this;
        }

        public StaticModelSourceRenderer build() {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                if (this.inventoryPositioning == null) {
                    this.inventoryPositioning = (itemStack) -> { GL11.glTranslatef(0.0F, 0.12F, 0.0F); };
                }

                if (this.entityPositioning == null) {
                    this.entityPositioning = (itemStack) -> {};
                }

                if (this.firstPersonPositioning == null) {
                    this.firstPersonPositioning = (player, itemStack) -> {};
                }

                if (this.thirdPersonPositioning == null) {
                    this.thirdPersonPositioning = (player, itemStack) -> {};
                }

                if (this.inventoryModelPositioning == null) {
                    this.inventoryModelPositioning = (m, i) -> {};
                }

                if (this.entityModelPositioning == null) {
                    this.entityModelPositioning = (m, i) -> {};
                }

                if (this.firstPersonModelPositioning == null) {
                    this.firstPersonModelPositioning = (m, i) -> {};
                }

                if (this.thirdPersonModelPositioning == null) {
                    this.thirdPersonModelPositioning = (m, i) -> {};
                }

                if (this.firstPersonLeftHandPositioning == null) {
                    this.firstPersonLeftHandPositioning = (c) -> GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

                if (this.firstPersonRightHandPositioning == null) {
                    this.firstPersonRightHandPositioning = (c) -> GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

                return new StaticModelSourceRenderer(this);
            }
        }

    }
}
