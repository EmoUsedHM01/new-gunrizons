package com.gtnewhorizon.newgunrizons.client.render;

import java.util.List;
import java.util.function.Consumer;

import com.gtnewhorizon.newgunrizons.model.JsonModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartPositioning;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.model.ModelWithAttachments;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.util.Pair;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;

public class WeaponRenderer implements IItemRenderer {

    private static final int INVENTORY_TEXTURE_WIDTH = 256;
    private static final int INVENTORY_TEXTURE_HEIGHT = 256;
    private static final float MIN_ALPHA_THRESHOLD = 1.0F / 255.0F;

    /** GL_ENABLE_BIT | GL_CURRENT_BIT -- saves enable flags and current color/normal/texcoord. */
    private static final int ATTRIB_ENABLE_CURRENT = GL11.GL_ENABLE_BIT | GL11.GL_CURRENT_BIT;

    private final ModelBase model;
    private final String textureName;
    private final Consumer<ItemStack> entityPositioning;
    private final Consumer<ItemStack> inventoryPositioning;
    private final Consumer<RenderContext> thirdPersonPositioning;
    private final Consumer<RenderContext> firstPersonPositioning;
    @Getter
    private final long totalReloadingDuration;
    @Getter
    private final long totalUnloadingDuration;
    @Getter
    private final long totalLoadIterationDuration;
    @Getter
    private final long prepareFirstLoadIterationAnimationDuration;
    @Getter
    private final long allLoadIterationAnimationsCompletedDuration;
    private final com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController bedrockAnimController;
    private Integer cachedInventoryTexture;
    /** Set to true when the EQUIPPED_FIRST_PERSON path has already applied bedrock animation. */
    private boolean bedrockAnimAppliedThisFrame;

    private WeaponRenderer(Builder builder) {
        this.model = builder.getModel();
        this.textureName = builder.getTextureName();
        this.entityPositioning = builder.getEntityPositioning();
        this.inventoryPositioning = builder.getInventoryPositioning();
        this.thirdPersonPositioning = builder.getThirdPersonPositioning();
        this.firstPersonPositioning = builder.firstPersonPositioning;
        this.totalReloadingDuration = builder.totalReloadingDuration;
        this.totalUnloadingDuration = builder.totalUnloadingDuration;
        this.totalLoadIterationDuration = builder.totalLoadIterationDuration;
        this.prepareFirstLoadIterationAnimationDuration = builder.prepareFirstLoadIterationAnimationDuration;
        this.allLoadIterationAnimationsCompletedDuration = builder.allLoadIterationAnimationsCompletedDuration;
        this.bedrockAnimController = builder.bedrockAnimController;
    }

    /**
     * Maps the weapon state machine state to a renderable animation state.
     * The bedrock animation controller handles all blending, hold, and transitions.
     */
    private static RenderableState mapWeaponState(ItemWeaponInstance instance, EntityLivingBase player) {
        if (instance == null) return RenderableState.NORMAL;
        switch (instance.getState()) {
            case FIRING:
            case RECOILED:
            case PAUSED:
            case EJECT_REQUIRED:
                return instance.isAimed() ? RenderableState.ZOOMING_SHOOTING : RenderableState.SHOOTING;
            case EJECTING:
                return RenderableState.EJECT_SPENT_ROUND;
            case LOAD:
                return RenderableState.RELOADING;
            case UNLOAD_PREPARING:
            case UNLOAD:
                return RenderableState.UNLOADING;
            case LOAD_ITERATION:
                return RenderableState.LOAD_ITERATION;
            case LOAD_ITERATION_COMPLETED:
                return RenderableState.LOAD_ITERATION_COMPLETED;
            case ALL_LOAD_ITERATIONS_COMPLETED:
                return RenderableState.ALL_LOAD_ITERATIONS_COMPLETED;
            case MODIFYING:
            case NEXT_ATTACHMENT:
                return RenderableState.MODIFYING;
            default: // READY, ALERT, etc.
                if (player.isSprinting()) return RenderableState.RUNNING;
                if (instance.isAimed()) return RenderableState.ZOOMING;
                return RenderableState.NORMAL;
        }
    }

    public void renderItem(ItemStack weaponItemStack, RenderContext renderContext,
        MultipartPositioning.Positioner positioner) {
        List<CompatibleAttachment> attachments = null;
        if (this.model instanceof ModelWithAttachments) {
            attachments = ((ItemWeapon) weaponItemStack.getItem())
                .getActiveAttachments(renderContext.getPlayer(), weaponItemStack);
        }

        if (this.textureName != null) {
            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(NewGunrizonsMod.MODID + ":textures/models/" + this.textureName));
        } else {
            ItemWeapon weapon = (ItemWeapon) weaponItemStack.getItem();
            String textureName = weapon.getTextureName();

            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(NewGunrizonsMod.MODID + ":textures/models/" + textureName));
        }

        // Apply bedrock bone animations if active.
        // Skip if already applied by the EQUIPPED_FIRST_PERSON path (which needs
        // animation applied before arm rendering).
        if (this.bedrockAnimController != null && this.model instanceof com.gtnewhorizon.newgunrizons.model.JsonModel
            && !this.bedrockAnimAppliedThisFrame) {
            com.gtnewhorizon.newgunrizons.model.JsonModel jsonModel = (com.gtnewhorizon.newgunrizons.model.JsonModel) this.model;
            // Reset bones to rest pose before applying new animation frame,
            // so transitions between states (or to states with no animation) are clean.
            jsonModel.resetBonesToRestPose();
            RenderableState toState = renderContext.getToState();
            if (toState != null) {
                long fireTimestamp = 0;
                ItemWeaponInstance wi = renderContext.getWeaponInstance();
                if (wi != null) {
                    fireTimestamp = wi.getLastFireTimestamp();
                }
                this.bedrockAnimController.onStateChanged(toState, fireTimestamp);
            }
            this.bedrockAnimController.applyToModel(jsonModel);
        }
        this.bedrockAnimAppliedThisFrame = false;

        this.model.render(
            null,
            renderContext.getLimbSwing(),
            renderContext.getLimbSwingAmount(),
            renderContext.getAgeInTicks(),
            renderContext.getNetHeadYaw(),
            renderContext.getHeadPitch(),
            renderContext.getScale());

        // Note: bone reset moved to after muzzle flash rendering in the
        // EQUIPPED_FIRST_PERSON path so flash can read animated bone positions.

        if (attachments != null) {
            this.renderAttachments(positioner, renderContext, attachments);
        }
    }

    public void renderAttachments(MultipartPositioning.Positioner positioner, RenderContext renderContext,
        List<CompatibleAttachment> attachments) {
        for (CompatibleAttachment attachment : attachments) {
            if (attachment != null) {
                this.renderCompatibleAttachment(attachment, positioner, renderContext);
            }
        }
    }

    private void renderCompatibleAttachment(CompatibleAttachment compatibleAttachment,
        MultipartPositioning.Positioner positioner, RenderContext renderContext) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(ATTRIB_ENABLE_CURRENT);
        if (compatibleAttachment.getPositioning() != null) {
            compatibleAttachment.getPositioning()
                .accept(renderContext.getPlayer(), renderContext.getWeapon());
        }

        ItemAttachment itemAttachment = compatibleAttachment.getAttachment();
        if (positioner != null) {
            if (itemAttachment instanceof com.gtnewhorizon.newgunrizons.attachment.Part) {
                positioner.position((com.gtnewhorizon.newgunrizons.attachment.Part) itemAttachment, renderContext);
            } else if (itemAttachment.getRenderablePart() != null) {
                positioner.position(itemAttachment.getRenderablePart(), renderContext);
            }
        }

        for (Pair<ModelBase, String> texturedModel : compatibleAttachment.getAttachment()
            .getTexturedModels()) {
            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(NewGunrizonsMod.MODID + ":textures/models/" + texturedModel.getV()));
            GL11.glPushMatrix();
            GL11.glPushAttrib(ATTRIB_ENABLE_CURRENT);
            if (compatibleAttachment.getModelPositioning() != null) {
                compatibleAttachment.getModelPositioning()
                    .accept(texturedModel.getU());
            }

            texturedModel.getU()
                .render(
                    renderContext.getPlayer(),
                    renderContext.getLimbSwing(),
                    renderContext.getLimbSwingAmount(),
                    renderContext.getAgeInTicks(),
                    renderContext.getNetHeadYaw(),
                    renderContext.getHeadPitch(),
                    renderContext.getScale());
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        CustomRenderer postRenderer = compatibleAttachment.getAttachment()
            .getPostRenderer();
        if (postRenderer != null) {
            GL11.glPushMatrix();
            GL11.glPushAttrib(ATTRIB_ENABLE_CURRENT);
            postRenderer.render(renderContext);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        for (CompatibleAttachment attachment : itemAttachment.getAttachments()) {
            renderCompatibleAttachment(attachment, positioner, renderContext);
        }

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController getBedrockAnimController() {
        return bedrockAnimController;
    }

    public static class Builder {

        @Getter
        private ModelBase model;
        @Getter
        private String textureName;

        @Getter
        private Consumer<ItemStack> entityPositioning;
        @Getter
        private Consumer<ItemStack> inventoryPositioning;
        @Getter
        private Consumer<RenderContext> thirdPersonPositioning;
        private Consumer<RenderContext> firstPersonPositioning;

        private long totalReloadingDuration = 250;
        private long totalUnloadingDuration = 250;
        private long totalLoadIterationDuration = 250;
        private int prepareFirstLoadIterationAnimationDuration = 100;
        private int allLoadIterationAnimationsCompletedDuration = 100;

        private com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController bedrockAnimController;

        public Builder withModel(ModelBase model) {
            this.model = model;
            return this;
        }

        /**
         * Loads a Bedrock animation file containing animation clips.
         *
         * @param animationPath path relative to assets/newgunrizons/animations/, without .animation.json extension
         */
        public Builder withBedrockAnimation(String animationPath) {
            this.bedrockAnimController = new com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController(
                new com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation(animationPath));
            return this;
        }

        /**
         * Maps a weapon renderable state to a named Bedrock animation clip.
         * Requires {@link #withBedrockAnimation(String)} to be called first.
         */
        public Builder withBedrockAnimationForState(RenderableState state, String clipName) {
            if (this.bedrockAnimController == null) {
                throw new IllegalStateException("Call withBedrockAnimation() before withBedrockAnimationForState()");
            }
            this.bedrockAnimController.mapState(state, clipName);
            return this;
        }

        public Builder withTotalReloadingDuration(long totalReloadingDuration) {
            this.totalReloadingDuration = totalReloadingDuration;
            return this;
        }

        public Builder withTotalUnloadingDuration(long totalUnloadingDuration) {
            this.totalUnloadingDuration = totalUnloadingDuration;
            return this;
        }

        public Builder withTotalLoadIterationDuration(long totalLoadIterationDuration) {
            this.totalLoadIterationDuration = totalLoadIterationDuration;
            return this;
        }

        public Builder withPrepareFirstLoadIterationAnimationDuration(int prepareFirstLoadIterationAnimationDuration) {
            this.prepareFirstLoadIterationAnimationDuration = prepareFirstLoadIterationAnimationDuration;
            return this;
        }

        public Builder withAllLoadIterationAnimationsCompletedDuration(
            int allLoadIterationAnimationsCompletedDuration) {
            this.allLoadIterationAnimationsCompletedDuration = allLoadIterationAnimationsCompletedDuration;
            return this;
        }

        public Builder withTextureName(String textureName) {
            this.textureName = textureName + ".png";
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

        public Builder withThirdPersonPositioning(Consumer<RenderContext> thirdPersonPositioning) {
            this.thirdPersonPositioning = thirdPersonPositioning;
            return this;
        }

        public Builder withFirstPersonPositioning(Consumer<RenderContext> firstPersonPositioning) {
            this.firstPersonPositioning = firstPersonPositioning;
            return this;
        }

        public WeaponRenderer build() {
            if (FMLCommonHandler.instance()
                .getSide() != Side.CLIENT) {
                return null;
            }

            if (this.inventoryPositioning == null) {
                this.inventoryPositioning = (itemStack) -> GL11.glTranslatef(0.0F, 0.12F, 0.0F);
            }

            if (this.entityPositioning == null) {
                this.entityPositioning = (itemStack) -> {};
            }

            if (this.thirdPersonPositioning == null) {
                this.thirdPersonPositioning = (context) -> {
                    GL11.glTranslatef(-0.4F, 0.2F, 0.4F);
                    GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                };
            }

            if (this.firstPersonPositioning == null) {
                this.firstPersonPositioning = (context) -> {
                    // Counter Forge's block-centering offset (EQUIPPED_BLOCK path)
                    GL11.glTranslatef(0.5F, -1.0F, 0.5F);
                    // Counter vanilla ItemRenderer's 45° Y coordinate rotation
                    // so Blockbench axes map 1:1 to in-game axes.
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                };
            }

            return new WeaponRenderer(this);
        }

    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderItem(ItemRenderType type, ItemStack weaponItemStack, Object... data) {
        GL11.glPushMatrix();
        int originalFramebufferId = -1;
        Framebuffer framebuffer = null;
        Integer inventoryTexture = null;
        boolean inventoryTextureInitializationPhaseOn = false;
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int currentTextureId = 0;
        if (type == ItemRenderType.INVENTORY) {
            currentTextureId = Framebuffers.getCurrentTexture();
            inventoryTexture = this.cachedInventoryTexture;
            if (inventoryTexture == null) {
                inventoryTextureInitializationPhaseOn = true;
                originalFramebufferId = Framebuffers.getCurrentFramebuffer();
                Framebuffers.unbindFramebuffer();
                framebuffer = new Framebuffer(INVENTORY_TEXTURE_WIDTH, INVENTORY_TEXTURE_HEIGHT, true);
                inventoryTexture = framebuffer.framebufferTexture;
                this.cachedInventoryTexture = inventoryTexture;
                framebuffer.bindFramebuffer(true);
                this.setupInventoryRendering();
                GL11.glScalef(130.0F, 130.0F, 130.0F);
                GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(1.45F, 1.4F, 0.0F);
            }
        }

        GL11.glScaled(-1.0D, -1.0D, 1.0D);
        Object player;
        if (data.length > 1 && data[1] instanceof EntityLivingBase) {
            player = data[1];
        } else {
            player = Minecraft.getMinecraft().thePlayer;
        }

        RenderContext renderContext = new RenderContext((EntityLivingBase) player, weaponItemStack);
        renderContext.setAgeInTicks(-0.4F);
        renderContext.setScale(0.08F);
        renderContext.setTransformType(TransformType.fromItemRenderType(type));
        MultipartPositioning.Positioner positioner = null;
        switch (type) {
            case ENTITY:
                this.entityPositioning.accept(weaponItemStack);
                break;
            case INVENTORY:
                this.inventoryPositioning.accept(weaponItemStack);
                break;
            case EQUIPPED:
                this.thirdPersonPositioning.accept(renderContext);
                break;
            case EQUIPPED_FIRST_PERSON:
                this.firstPersonPositioning.accept(renderContext);

                JsonModel weaponModel = this.model instanceof JsonModel ? (JsonModel) this.model : null;

                // Reset bones before any animation
                if (weaponModel != null) {
                    weaponModel.resetBonesToRestPose();
                }

                // Map weapon state to renderable state
                ItemWeaponInstance weaponInstance = null;
                ItemInstance<?> itemInst = ItemInstanceRegistry.INSTANCE.getItemInstance((EntityLivingBase) player, weaponItemStack);
                if (itemInst instanceof ItemWeaponInstance && itemInst.getItem() == weaponItemStack.getItem()) {
                    weaponInstance = (ItemWeaponInstance) itemInst;
                }
                renderContext.setItemInstance(weaponInstance);
                RenderableState currentRenderState = mapWeaponState(weaponInstance, (EntityLivingBase) player);

                // Fire cycle hold: keep SHOOTING while animation plays
                if (this.bedrockAnimController != null) {
                    RenderableState held = this.bedrockAnimController.getActiveFireCycleState();
                    if (held != null && currentRenderState != RenderableState.SHOOTING
                        && currentRenderState != RenderableState.ZOOMING_SHOOTING) {
                        currentRenderState = held;
                    }
                }

                renderContext.setToState(currentRenderState);

                if (this.bedrockAnimController != null && weaponModel != null) {
                    long fireTimestamp = weaponInstance != null ? weaponInstance.getLastFireTimestamp() : 0;
                    this.bedrockAnimController.onStateChanged(currentRenderState, fireTimestamp);
                    this.bedrockAnimController.applyToModel(weaponModel);
                }

                this.bedrockAnimAppliedThisFrame = (weaponModel != null);

                // Render arms at hand bone positions
                renderLeftArm((EntityPlayer) player, renderContext, null, this.bedrockAnimController, weaponModel, renderContext.getScale());
                renderRightArm((EntityPlayer) player, renderContext, null, this.bedrockAnimController, weaponModel, renderContext.getScale());
                break;
        }

        if (type != ItemRenderType.INVENTORY || inventoryTextureInitializationPhaseOn) {
            this.renderItem(weaponItemStack, renderContext, positioner);
        }

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            JsonModel flashModel = this.model instanceof JsonModel ? (JsonModel) this.model : null;
            MuzzleFlashRenderer.renderIfFiring(renderContext, flashModel, renderContext.getScale());
        }

        // Reset bones to rest pose after all rendering (including muzzle flash)
        if (this.bedrockAnimController != null && this.model instanceof com.gtnewhorizon.newgunrizons.model.JsonModel) {
            this.bedrockAnimController
                .resetModel((com.gtnewhorizon.newgunrizons.model.JsonModel) this.model);
        }

        if (type == ItemRenderType.INVENTORY && inventoryTextureInitializationPhaseOn) {
            framebuffer.unbindFramebuffer();
            framebuffer.framebufferTexture = -1;
            framebuffer.deleteFramebuffer();
            this.restoreInventoryRendering(scaledresolution);
        }

        GL11.glPopMatrix();
        if (originalFramebufferId >= 0) {
            Framebuffers.bindFramebuffer(
                originalFramebufferId,
                true,
                mc.getFramebuffer().framebufferWidth,
                mc.getFramebuffer().framebufferHeight);
        }

        if (type == ItemRenderType.INVENTORY) {
            this.renderCachedInventoryTexture(inventoryTexture);
            if (currentTextureId != 0) {
                Framebuffers.bindTexture(currentTextureId);
            }
        }
    }

    private void setupInventoryRendering() {
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, INVENTORY_TEXTURE_WIDTH, INVENTORY_TEXTURE_HEIGHT, 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
    }

    private void restoreInventoryRendering(ScaledResolution scaledresolution) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(
            0.0D,
            scaledresolution.getScaledWidth_double(),
            scaledresolution.getScaledHeight_double(),
            0.0D,
            1000.0D,
            3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    private void renderCachedInventoryTexture(Integer inventoryTexture) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-210.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(1.0F, 1.0F, -1.0F);
        GL11.glTranslatef(-0.8F, -0.8F, -1.0F);
        GL11.glScalef(0.006F, 0.006F, 0.006F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, MIN_ALPHA_THRESHOLD);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, inventoryTexture);
        drawTexturedQuadFit();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private static void drawTexturedQuadFit() {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, 256.0, 0.0, 0.0F, 1.0F);
        tessellator.addVertexWithUV(256.0, 256.0, 0.0, 1.0F, 1.0F);
        tessellator.addVertexWithUV(256.0, 0.0D, 0.0, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0, 0.0F, 0.0F);
        tessellator.draw();
    }

    static void renderRightArm(EntityPlayer player, RenderContext renderContext,
        MultipartPositioning.Positioner positioner,
        com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController animController,
        com.gtnewhorizon.newgunrizons.model.JsonModel weaponModel, float renderScale) {

        if (weaponModel != null && weaponModel.getBone(
                com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController.BONE_RIGHT_HAND) != null) {
            renderArmAtBone(player, weaponModel,
                com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController.BONE_RIGHT_HAND,
                true, renderScale);
        } else if (positioner != null) {
            // Fallback to positioner-based arm rendering for models without hand bones
            RenderPlayer render = (RenderPlayer) RenderManager.instance.getEntityRenderObject(player);
            Minecraft.getMinecraft()
                .getTextureManager()
                .bindTexture(((AbstractClientPlayer) player).getLocationSkin());
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.25F, 0.0F, 0.2F);
            GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
            positioner.position(com.gtnewhorizon.newgunrizons.attachment.Part.RIGHT_HAND, renderContext);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            render.modelBipedMain.onGround = 0.0F;
            render.modelBipedMain.setRotationAngles(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
            render.modelBipedMain.bipedRightArm.render(0.0625F);
            GL11.glPopMatrix();
        }
        // If no hand bone and no positioner, skip arm rendering
    }

    static void renderLeftArm(EntityPlayer player, RenderContext renderContext,
        MultipartPositioning.Positioner positioner,
        com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController animController,
        com.gtnewhorizon.newgunrizons.model.JsonModel weaponModel, float renderScale) {

        if (weaponModel != null && weaponModel.getBone(
                com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController.BONE_LEFT_HAND) != null) {
            renderArmAtBone(player, weaponModel,
                com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController.BONE_LEFT_HAND,
                false, renderScale);
        } else if (positioner != null) {
            // Fallback to positioner-based arm rendering for models without hand bones
            RenderPlayer render = (RenderPlayer) RenderManager.instance.getEntityRenderObject(player);
            Minecraft.getMinecraft()
                .getTextureManager()
                .bindTexture(((AbstractClientPlayer) player).getLocationSkin());
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            positioner.position(com.gtnewhorizon.newgunrizons.attachment.Part.LEFT_HAND, renderContext);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            render.modelBipedMain.onGround = 0.0F;
            render.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
            render.modelBipedMain.bipedLeftArm.render(0.0625F);
            GL11.glPopMatrix();
        }
        // If no hand bone and no positioner, skip arm rendering
    }

    /**
     * Renders a player arm (left or right) positioned at the given hand bone's transform.
     * The hand bone is a child of the receiver bone, so we walk the parent chain
     * to compose the full transform: receiver -> hand bone.
     * <p>
     * ModelRenderer applies transforms as: translate(rotationPoint * scale), rotateZ, rotateY, rotateX.
     */
    static void renderArmAtBone(EntityPlayer player, com.gtnewhorizon.newgunrizons.model.JsonModel model,
        String handBoneName, boolean rightArm, float renderScale) {
        ModelRenderer handBone = model.getBone(handBoneName);
        if (handBone == null) return;

        RenderPlayer render = (RenderPlayer) RenderManager.instance.getEntityRenderObject(player);
        Minecraft.getMinecraft().getTextureManager()
            .bindTexture(((AbstractClientPlayer) player).getLocationSkin());

        GL11.glPushMatrix();

        // Apply parent bone (receiver) transform first
        ModelRenderer receiver = model.getBone("receiver");
        if (receiver != null) {
            GL11.glTranslatef(
                receiver.rotationPointX * renderScale,
                receiver.rotationPointY * renderScale,
                receiver.rotationPointZ * renderScale);
            if (receiver.rotateAngleZ != 0) GL11.glRotatef(receiver.rotateAngleZ * (180F / (float) Math.PI), 0, 0, 1);
            if (receiver.rotateAngleY != 0) GL11.glRotatef(receiver.rotateAngleY * (180F / (float) Math.PI), 0, 1, 0);
            if (receiver.rotateAngleX != 0) GL11.glRotatef(receiver.rotateAngleX * (180F / (float) Math.PI), 1, 0, 0);
        }

        // Apply hand bone transform (child of receiver)
        GL11.glTranslatef(
            handBone.rotationPointX * renderScale,
            handBone.rotationPointY * renderScale,
            handBone.rotationPointZ * renderScale);
        if (handBone.rotateAngleZ != 0) GL11.glRotatef(handBone.rotateAngleZ * (180F / (float) Math.PI), 0, 0, 1);
        if (handBone.rotateAngleY != 0) GL11.glRotatef(handBone.rotateAngleY * (180F / (float) Math.PI), 0, 1, 0);
        if (handBone.rotateAngleX != 0) GL11.glRotatef(handBone.rotateAngleX * (180F / (float) Math.PI), 1, 0, 0);

        // Normalize scale so arm renders at consistent size regardless of weapon scale
        GL11.glScalef(1.3f, 1.3f, 1.3f);

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        render.modelBipedMain.onGround = 0.0F;
        if (rightArm) {
            render.modelBipedMain.setRotationAngles(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
            render.modelBipedMain.bipedRightArm.render(0.0625F);
        } else {
            render.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
            render.modelBipedMain.bipedLeftArm.render(0.0625F);
        }

        GL11.glPopMatrix();
    }
}
