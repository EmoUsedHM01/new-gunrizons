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

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.util.Pair;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;

/**
 * Renders {@link ItemAttachment} items (scopes, grips, magazines, etc.) as standalone items
 * in inventory, first/third person, and as dropped entities.
 */
public class StaticModelRenderer implements IItemRenderer {

    private static final float MODEL_AGE_IN_TICKS = -0.4F;
    private static final float MODEL_SCALE = 0.08F;

    private final Consumer<ItemStack> entityPositioning;
    private final Consumer<ItemStack> inventoryPositioning;
    private final BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning;
    private final BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning;
    private final BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning;
    private final BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning;
    private final BiConsumer<ModelBase, ItemStack> inventoryModelPositioning;
    private final BiConsumer<ModelBase, ItemStack> entityModelPositioning;
    private final Consumer<RenderContext> firstPersonLeftHandPositioning;
    private final Consumer<RenderContext> firstPersonRightHandPositioning;

    private StaticModelRenderer(Builder builder) {
        this.entityPositioning = builder.entityPositioning;
        this.inventoryPositioning = builder.inventoryPositioning;
        this.thirdPersonPositioning = builder.thirdPersonPositioning;
        this.firstPersonPositioning = builder.firstPersonPositioning;
        this.firstPersonModelPositioning = builder.firstPersonModelPositioning;
        this.thirdPersonModelPositioning = builder.thirdPersonModelPositioning;
        this.inventoryModelPositioning = builder.inventoryModelPositioning;
        this.entityModelPositioning = builder.entityModelPositioning;
        this.firstPersonLeftHandPositioning = builder.firstPersonLeftHandPositioning;
        this.firstPersonRightHandPositioning = builder.firstPersonRightHandPositioning;
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
        GL11.glScaled(-1.0, -1.0, 1.0);
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        RenderContext renderContext = new RenderContext(player, itemStack);

        switch (type) {
            case ENTITY:
                this.entityPositioning.accept(itemStack);
                break;
            case INVENTORY:
                this.inventoryPositioning.accept(itemStack);
                break;
            case EQUIPPED:
                this.thirdPersonPositioning.accept(player, itemStack);
                break;
            case EQUIPPED_FIRST_PERSON:
                this.firstPersonPositioning.accept(player, itemStack);
        }

        renderAttachmentModels(renderContext, itemStack, type);
        GL11.glPopMatrix();

        if (currentTextureId != 0) {
            Framebuffers.bindTexture(currentTextureId);
        }
    }

    private void renderAttachmentModels(RenderContext renderContext, ItemStack itemStack, ItemRenderType type) {
        if (!(itemStack.getItem() instanceof ItemAttachment)) {
            throw new IllegalArgumentException(
                "StaticModelRenderer requires an ItemAttachment, got: " + itemStack.getItem());
        }

        ItemAttachment attachment = (ItemAttachment) itemStack.getItem();
        GL11.glPushMatrix();

        for (Pair<ModelBase, String> texturedModel : attachment.getTexturedModels()) {
            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(NewGunrizonsMod.MODID + ":textures/models/" + texturedModel.getV()));
            GL11.glPushMatrix();
            GL11.glPushAttrib(GL11.GL_ENABLE_BIT);

            ModelBase model = texturedModel.getU();
            applyModelPositioning(model, itemStack, type);
            model.render(null, 0.0F, 0.0F, MODEL_AGE_IN_TICKS, 0.0F, 0.0F, MODEL_SCALE);

            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        CustomRenderer postRenderer = attachment.getPostRenderer();
        if (postRenderer != null) {
            renderContext.setAgeInTicks(MODEL_AGE_IN_TICKS);
            renderContext.setScale(MODEL_SCALE);
            renderContext.setTransformType(TransformType.fromItemRenderType(type));
            renderContext
                .setItemInstance(ItemInstanceRegistry.INSTANCE.getItemInstance(renderContext.getPlayer(), itemStack));
            GL11.glPushMatrix();
            GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_CURRENT_BIT);
            postRenderer.render(renderContext);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        GL11.glPopMatrix();
    }

    private void applyModelPositioning(ModelBase model, ItemStack itemStack, ItemRenderType type) {
        switch (type) {
            case ENTITY:
                this.entityModelPositioning.accept(model, itemStack);
                break;
            case INVENTORY:
                this.inventoryModelPositioning.accept(model, itemStack);
                break;
            case EQUIPPED:
                this.thirdPersonModelPositioning.accept(model, itemStack);
                break;
            case EQUIPPED_FIRST_PERSON:
                this.firstPersonModelPositioning.accept(model, itemStack);
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

        public Builder withFirstPersonPositioning(BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning) {
            this.firstPersonPositioning = firstPersonPositioning;
            return this;
        }

        public Builder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioning = leftHand;
            this.firstPersonRightHandPositioning = rightHand;
            return this;
        }

        public Builder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
            this.entityPositioning = entityPositioning;
            return this;
        }

        public Builder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
            this.inventoryPositioning = inventoryPositioning;
            return this;
        }

        public Builder withThirdPersonPositioning(BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning) {
            this.thirdPersonPositioning = thirdPersonPositioning;
            return this;
        }

        public Builder withFirstPersonModelPositioning(BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning) {
            this.firstPersonModelPositioning = firstPersonModelPositioning;
            return this;
        }

        public Builder withEntityModelPositioning(BiConsumer<ModelBase, ItemStack> entityModelPositioning) {
            this.entityModelPositioning = entityModelPositioning;
            return this;
        }

        public Builder withInventoryModelPositioning(BiConsumer<ModelBase, ItemStack> inventoryModelPositioning) {
            this.inventoryModelPositioning = inventoryModelPositioning;
            return this;
        }

        public Builder withThirdPersonModelPositioning(BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning) {
            this.thirdPersonModelPositioning = thirdPersonModelPositioning;
            return this;
        }

        public StaticModelRenderer build() {
            if (this.inventoryPositioning == null) {
                this.inventoryPositioning = (itemStack) -> GL11.glTranslatef(0.0F, 0.12F, 0.0F);
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
            return new StaticModelRenderer(this);
        }
    }
}
