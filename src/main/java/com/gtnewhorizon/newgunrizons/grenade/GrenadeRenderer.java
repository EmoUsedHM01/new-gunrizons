package com.gtnewhorizon.newgunrizons.grenade;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation;
import com.gtnewhorizon.newgunrizons.client.render.Framebuffers;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.TransformType;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.items.instances.ItemGrenadeInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.model.BedrockModel;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;

public class GrenadeRenderer implements IItemRenderer {

	@Getter
	private final ModelBase model;
	@Getter
	private final String textureName;
	@Getter
	private final Runnable thrownEntityPositioning;
	@Getter
	private final long totalThrowingDuration;
	@Getter
	private final Supplier<Float> xRotationCenterOffset;
	@Getter
	private final Supplier<Float> yRotationCenterOffset;
	@Getter
	private final Supplier<Float> zRotationCenterOffset;
	private final BedrockAnimationController bedrockAnimController;
	/** Set to true when the EQUIPPED_FIRST_PERSON path has already applied bedrock animation. */
	private boolean bedrockAnimAppliedThisFrame;

	private GrenadeRenderer(Builder builder) {
		this.model = builder.model;
		this.textureName = builder.textureName;
		this.thrownEntityPositioning = builder.thrownEntityPositioning;
		this.totalThrowingDuration = builder.totalThrowingDuration;
		this.xRotationCenterOffset = builder.xCenterOffset;
		this.yRotationCenterOffset = builder.yCenterOffset;
		this.zRotationCenterOffset = builder.zCenterOffset;
		this.bedrockAnimController = builder.bedrockAnimController;
	}

	/**
	 * Maps the grenade state machine state to a renderable animation state.
	 */
	private static RenderableState mapGrenadeState(ItemGrenadeInstance instance, EntityLivingBase player) {
		if (instance == null) return RenderableState.IDLE;
		switch (instance.getState()) {
			case SAFETY_PIN_OFF:
				return RenderableState.SAFETY_PIN_OFF;
			case STRIKER_LEVER_RELEASED:
				return RenderableState.STRIKER_LEVER_OFF;
			case THROWING:
				return RenderableState.THROWING;
			case THROWN:
				return RenderableState.THROWN;
			default:
				if (player.isSprinting()) return RenderableState.RUNNING;
				return RenderableState.IDLE;
		}
	}

	public void renderItem(ItemStack grenadeItemStack, RenderContext renderContext) {
		String resolvedTexture = this.textureName;
		if (resolvedTexture == null) {
			resolvedTexture = ((ItemGrenade) grenadeItemStack.getItem()).getTextureName();
		}
		Minecraft.getMinecraft().renderEngine
			.bindTexture(new ResourceLocation(NewGunrizonsMod.MODID + ":textures/models/" + resolvedTexture));

		// Apply bedrock bone animations if active.
		// Skip if already applied by the EQUIPPED_FIRST_PERSON path (which needs
		// animation applied before arm rendering).
		if (this.bedrockAnimController != null && this.model instanceof BedrockModel
			&& !this.bedrockAnimAppliedThisFrame) {
			BedrockModel bedrockModel = (BedrockModel) this.model;
			bedrockModel.resetBonesToRestPose();
			RenderableState toState = renderContext.getToState();
			if (toState != null) {
				this.bedrockAnimController.onStateChanged(toState);
			}
			this.bedrockAnimController.applyToModel(bedrockModel);
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
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderItem(ItemRenderType type, ItemStack grenadeItemStack, Object... data) {
		int currentTextureId = 0;
		if (type == ItemRenderType.INVENTORY) {
			currentTextureId = Framebuffers.getCurrentTexture();
		}

		GL11.glPushMatrix();
		GL11.glScaled(-1.0D, -1.0D, 1.0D);
		EntityLivingBase player;
		if (data.length > 1 && data[1] instanceof EntityPlayer) {
			player = (EntityLivingBase) data[1];
		} else {
			player = Minecraft.getMinecraft().thePlayer;
		}

		RenderContext renderContext = new RenderContext(player, grenadeItemStack);
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
				// Counter Forge's block-centering offset
				GL11.glTranslatef(0.5F, -1.0F, 0.5F);
				// Counter vanilla ItemRenderer's 45° Y rotation
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);

				BedrockModel grenadeModel = this.model instanceof BedrockModel ? (BedrockModel) this.model : null;

				// Reset bones before any animation
				if (grenadeModel != null) {
					grenadeModel.resetBonesToRestPose();
				}

				// Map grenade state to renderable state
				ItemGrenadeInstance grenadeInstance = null;
				ItemInstance itemInst = ItemInstanceRegistry.INSTANCE.getItemInstance(player, grenadeItemStack);
				if (itemInst instanceof ItemGrenadeInstance && itemInst.getItem() == grenadeItemStack.getItem()) {
					grenadeInstance = (ItemGrenadeInstance) itemInst;
				}
				renderContext.setItemInstance(grenadeInstance);
				RenderableState currentRenderState = mapGrenadeState(grenadeInstance, player);

				renderContext.setToState(currentRenderState);

				if (this.bedrockAnimController != null && grenadeModel != null) {
					this.bedrockAnimController.onStateChanged(currentRenderState);
					this.bedrockAnimController.applyToModel(grenadeModel);
				}

				this.bedrockAnimAppliedThisFrame = (grenadeModel != null);

				// Render arms at hand bone positions
				WeaponRenderer.renderLeftArm((EntityPlayer) player, grenadeModel, renderContext.getScale());
				WeaponRenderer.renderRightArm((EntityPlayer) player, grenadeModel, renderContext.getScale());
				break;
		}

		this.renderItem(grenadeItemStack, renderContext);

		// Reset bones to rest pose after all rendering
		if (this.bedrockAnimController != null && this.model instanceof BedrockModel) {
			this.bedrockAnimController.resetModel((BedrockModel) this.model);
		}

		GL11.glPopMatrix();
		if (currentTextureId != 0) {
			Framebuffers.bindTexture(currentTextureId);
		}
	}

	// -------------------------------------------------------------------------
	// Builder
	// -------------------------------------------------------------------------

	public static class Builder {

		private ModelBase model;
		private String textureName;
		private Runnable thrownEntityPositioning = () -> {};
		private long totalThrowingDuration;
		private Supplier<Float> xCenterOffset = () -> 0.0F;
		private Supplier<Float> yCenterOffset = () -> 0.0F;
		private Supplier<Float> zCenterOffset = () -> 0.0F;
		private BedrockAnimationController bedrockAnimController;

		public Builder withModel(ModelBase model) {
			this.model = model;
			return this;
		}

		public Builder withTextureName(String textureName) {
			this.textureName = textureName + ".png";
			return this;
		}

		/**
		 * Loads a Bedrock animation file containing animation clips.
		 *
		 * @param animationPath path relative to assets/newgunrizons/animations/, without .animation.json extension
		 */
		public Builder withBedrockAnimation(String animationPath) {
			this.bedrockAnimController = new BedrockAnimationController(new BedrockAnimation(animationPath));
			return this;
		}

		/**
		 * Maps a grenade renderable state to a named Bedrock animation clip.
		 * Requires {@link #withBedrockAnimation(String)} to be called first.
		 */
		public Builder withBedrockAnimationForState(RenderableState state, String clipName) {
			if (this.bedrockAnimController == null) {
				throw new IllegalStateException("Call withBedrockAnimation() before withBedrockAnimationForState()");
			}
			this.bedrockAnimController.mapState(state, clipName);
			return this;
		}

		public Builder withTotalThrowingDuration(long totalThrowingDuration) {
			this.totalThrowingDuration = totalThrowingDuration;
			return this;
		}

		public Builder withThrownEntityPositioning(Runnable throwEntityPositioning) {
			this.thrownEntityPositioning = throwEntityPositioning;
			return this;
		}

		public Builder withEntityRotationCenterOffsets(Supplier<Float> xCenterOffset, Supplier<Float> yCenterOffset,
			Supplier<Float> zCenterOffset) {
			this.xCenterOffset = xCenterOffset;
			this.yCenterOffset = yCenterOffset;
			this.zCenterOffset = zCenterOffset;
			return this;
		}

		public GrenadeRenderer build() {
			if (FMLCommonHandler.instance()
				.getSide() != Side.CLIENT) {
				return null;
			}
			return new GrenadeRenderer(this);
		}
	}
}
