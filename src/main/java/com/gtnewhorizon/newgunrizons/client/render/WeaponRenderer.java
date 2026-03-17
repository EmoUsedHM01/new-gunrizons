package com.gtnewhorizon.newgunrizons.client.render;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;

import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController;
import com.gtnewhorizon.newgunrizons.weapon.FiringPointTracker;

import com.gtnewhorizon.newgunrizons.client.animation.IdleSway;
import com.gtnewhorizon.newgunrizons.model.BedrockModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBase;
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
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

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
    private static final String FIRING_POINT_BONE = "firing_point";
    private static final FloatBuffer MATRIX_BUF = BufferUtils.createFloatBuffer(16);

    private final BedrockModel model;
    private final String textureName;
    @Getter
    private final BedrockAnimationController bedrockAnimController;
    private final IdleSway idleSway = new IdleSway();

    private Integer cachedInventoryTexture;
    /** Set to true when the EQUIPPED_FIRST_PERSON path has already applied bedrock animation. */
    private boolean bedrockAnimAppliedThisFrame = false;

    private WeaponRenderer(Builder builder) {
        this.model = builder.model;
        this.textureName = builder.textureName;
        this.bedrockAnimController = builder.bedrockAnimController;
    }

    /**
     * Returns the animation duration in milliseconds for the given renderable state,
     * as defined in the Bedrock animation file. Returns 250ms as fallback if no
     * animation is mapped.
     */
    public long getAnimationDurationMs(RenderableState state) {
        if (bedrockAnimController != null) {
            long duration = bedrockAnimController.getAnimationDurationMs(state);
            if (duration > 0) return duration;
        }
        return 250L;
    }

    /**
     * Maps the weapon state machine state to a renderable animation state.
     * The bedrock animation controller handles all blending, hold, and transitions.
     */
    private static RenderableState mapWeaponState(ItemWeaponInstance instance, EntityLivingBase player) {
        if (instance == null) return RenderableState.IDLE;
        switch (instance.getState()) {
            case SHOOTING:
                return instance.isAimed() ? RenderableState.ZOOMING_SHOOTING : RenderableState.SHOOTING;
            case RELOADING_START:
                return RenderableState.RELOADING_START;
            case RELOADING_ITERATION:
            case RELOADING_ITERATION_COMPLETED:
                return RenderableState.RELOADING_ITERATION;
            case RELOADING_END:
                return RenderableState.RELOADING_END;
            case MODIFYING:
                return RenderableState.MODIFYING;
            default: // IDLE, NO_AMMO, etc.
                if (player.isSprinting()) return RenderableState.RUNNING;
                if (instance.isAimed()) return RenderableState.ZOOMING;
                return RenderableState.IDLE;
        }
    }

    public void renderItem(ItemStack weaponItemStack, RenderContext renderContext) {

        ItemWeapon weapon = (ItemWeapon) weaponItemStack.getItem();

        String texture;
        if (textureName != null) {
            texture = textureName;
        } else {
            texture = weapon.getTextureName();
        }

        Minecraft.getMinecraft().renderEngine
            .bindTexture(new ResourceLocation(NewGunrizonsMod.MODID + ":textures/models/" + texture));


        // Apply bedrock bone animations if active.
        // Skip if already applied by the EQUIPPED_FIRST_PERSON path (which needs
        // animation applied before arm rendering).
        if (bedrockAnimController != null && !bedrockAnimAppliedThisFrame) {

            model.resetBonesToRestPose();
            RenderableState toState = renderContext.getToState();
            if (toState != null) {
                long fireTimestamp = 0;
                ItemWeaponInstance wi = renderContext.getWeaponInstance();
                if (wi != null) {
                    fireTimestamp = wi.getLastFireTimestamp();
                }
                bedrockAnimController.onStateChanged(toState, fireTimestamp);
            }
            bedrockAnimController.applyToModel(model);
        }
        bedrockAnimAppliedThisFrame = false;

        model.render(
            null,
            renderContext.getLimbSwing(),
            renderContext.getLimbSwingAmount(),
            renderContext.getAgeInTicks(),
            renderContext.getNetHeadYaw(),
            renderContext.getHeadPitch(),
            renderContext.getScale());

        captureFiringPointWorldPosition(model, renderContext);

        if (weapon == null)
            return;

        List<CompatibleAttachment> attachments = weapon
            .getActiveAttachments(renderContext.getPlayer(), weaponItemStack);

        if (attachments != null) {
            renderAttachments(model, renderContext, attachments);
        }

    }

    private void captureFiringPointWorldPosition(BedrockModel model, RenderContext renderContext) {
        if (model.getBone(FIRING_POINT_BONE) == null) return;
        EntityLivingBase player = renderContext.getPlayer();
        if (player == null) return;

        GL11.glPushMatrix();
        model.applyBoneTransform(FIRING_POINT_BONE, renderContext.getScale());
        MATRIX_BUF.clear();
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, MATRIX_BUF);
        float ex = MATRIX_BUF.get(12);
        float ey = MATRIX_BUF.get(13);
        float ez = MATRIX_BUF.get(14);
        GL11.glPopMatrix();

        FiringPointTracker.captureEyePosition(ex, ey, ez);
    }

    public void renderAttachments(BedrockModel weaponModel, RenderContext renderContext,
        List<CompatibleAttachment> attachments) {
        for (CompatibleAttachment attachment : attachments) {
            if (attachment != null) {
                this.renderCompatibleAttachment(attachment, weaponModel, renderContext);
            }
        }
    }

    private void renderCompatibleAttachment(CompatibleAttachment compatibleAttachment,
        BedrockModel weaponModel, RenderContext renderContext) {
        ItemAttachment itemAttachment = compatibleAttachment.getAttachment();
        String boneName = compatibleAttachment.getBoneName();
        BedrockModel attachModel = itemAttachment.getModel();

        // Render attachment model at its bone position
        if (boneName != null && attachModel != null && weaponModel.getBone(boneName) != null) {
            GL11.glPushMatrix();
            GL11.glPushAttrib(ATTRIB_ENABLE_CURRENT);
            weaponModel.applyBoneTransform(boneName, renderContext.getScale());

            if (itemAttachment.getModelTextureName() != null) {
                Minecraft.getMinecraft().renderEngine.bindTexture(
                    new ResourceLocation(NewGunrizonsMod.MODID + ":textures/models/" + itemAttachment.getModelTextureName()));
            }
            attachModel.render(
                null,
                renderContext.getLimbSwing(),
                renderContext.getLimbSwingAmount(),
                renderContext.getAgeInTicks(),
                renderContext.getNetHeadYaw(),
                renderContext.getHeadPitch(),
                renderContext.getScale());

            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        // Post-renderer (e.g. scope viewfinder overlay)
        CustomRenderer postRenderer = itemAttachment.getPostRenderer();
        if (postRenderer != null) {
            GL11.glPushMatrix();
            GL11.glPushAttrib(ATTRIB_ENABLE_CURRENT);
            postRenderer.render(renderContext);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    public static class Builder {

        private BedrockModel model;
        private String textureName;

        private BedrockAnimationController bedrockAnimController;

        public Builder withModel(BedrockModel model) {
            this.model = model;
            return this;
        }

        /**
         * Loads a Bedrock animation file containing animation clips.
         *
         * @param animationPath path relative to assets/newgunrizons/animations/, without .animation.json extension
         */
        public Builder withBedrockAnimation(String animationPath) {
            this.bedrockAnimController = new BedrockAnimationController(
                new BedrockAnimation(animationPath));
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

        public Builder withTextureName(String textureName) {
            this.textureName = textureName + ".png";
            return this;
        }

        public WeaponRenderer build() {
            if (FMLCommonHandler.instance()
                .getSide() != Side.CLIENT) {
                return null;
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

        switch (type) {
            case ENTITY:
                break;
            case INVENTORY:
                GL11.glTranslatef(0.0F, 0.12F, 0.0F);
                break;
            case EQUIPPED:
                GL11.glTranslatef(-0.4F, 0.2F, 0.4F);
                GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(0.5F, -1.0F, 0.5F);           // Counter Forge's block-centering offset
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);  // Counter vanilla ItemRenderer's 45° Y rotation

                if (!com.gtnewhorizon.newgunrizons.client.debug.PositionDebugger.isActive()) {
                    this.idleSway.apply(0.33F, 0.06F);
                }

                BedrockModel weaponModel = this.model instanceof BedrockModel ? (BedrockModel) this.model : null;

                // Reset bones before any animation
                if (weaponModel != null) {
                    weaponModel.resetBonesToRestPose();
                }

                // Map weapon state to renderable state
                ItemWeaponInstance weaponInstance = null;
                ItemInstance itemInst = ItemInstanceRegistry.INSTANCE.getItemInstance((EntityLivingBase) player, weaponItemStack);
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
                renderLeftArm((EntityPlayer) player, weaponModel, renderContext.getScale());
                renderRightArm((EntityPlayer) player, weaponModel, renderContext.getScale());
                break;
        }

        if (type != ItemRenderType.INVENTORY || inventoryTextureInitializationPhaseOn) {
            this.renderItem(weaponItemStack, renderContext);
        }

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            BedrockModel flashModel = this.model instanceof BedrockModel ? (BedrockModel) this.model : null;
            MuzzleFlashRenderer.renderIfFiring(renderContext, flashModel, renderContext.getScale());
        }

        // Reset bones to rest pose after all rendering (including muzzle flash)
        if (this.bedrockAnimController != null && this.model instanceof BedrockModel) {
            this.bedrockAnimController
                .resetModel((BedrockModel) this.model);
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

    public static void renderRightArm(EntityPlayer player, BedrockModel weaponModel, float renderScale) {
        if (weaponModel != null && weaponModel.getBone(
                BedrockAnimationController.BONE_RIGHT_HAND) != null) {
            renderArmAtBone(player, weaponModel,
                BedrockAnimationController.BONE_RIGHT_HAND,
                true, renderScale);
        }
    }

    public static void renderLeftArm(EntityPlayer player, BedrockModel weaponModel, float renderScale) {
        if (weaponModel != null && weaponModel.getBone(
                BedrockAnimationController.BONE_LEFT_HAND) != null) {
            renderArmAtBone(player, weaponModel,
                BedrockAnimationController.BONE_LEFT_HAND,
                false, renderScale);
        }
    }

    /**
     * Renders a player arm (left or right) positioned at the given hand bone's transform.
     * Uses {@link BedrockModel#applyBoneTransform} to walk the full parent chain.
     */
    public static void renderArmAtBone(EntityPlayer player, BedrockModel model,
        String handBoneName, boolean rightArm, float renderScale) {
        if (model.getBone(handBoneName) == null) return;

        RenderPlayer render = (RenderPlayer) RenderManager.instance.getEntityRenderObject(player);
        Minecraft.getMinecraft().getTextureManager()
            .bindTexture(((AbstractClientPlayer) player).getLocationSkin());

        GL11.glPushMatrix();
        model.applyBoneTransform(handBoneName, renderScale);

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
