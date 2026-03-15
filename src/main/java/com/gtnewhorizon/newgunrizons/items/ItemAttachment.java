package com.gtnewhorizon.newgunrizons.items;

import java.util.function.Function;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.model.BedrockModel;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all weapon attachments (scopes, grips, silencers, magazines, bullets).
 * <p>
 * Each attachment belongs to an {@link AttachmentCategory} and has a single {@link BedrockModel}
 * for rendering. Subclasses add specialized behavior:
 * {@link ItemScope} (zoom), {@link ItemBullet} (type marker).
 */
public class ItemAttachment extends Item {

	@Getter
	private final AttachmentCategory category;

	@Getter
	@Setter
	private String name;

	/** Bedrock model for this attachment. */
	@Getter
	@Setter
	private BedrockModel model;

	/** Texture for the model. */
	@Getter
	private String modelTextureName;

	public void setModelTextureName(String modelTextureName) {
		this.modelTextureName = modelTextureName;
	}

	/** Optional renderer executed after the main render pass (e.g. scope viewfinder). */
	@Getter
	@Setter
	private CustomRenderer postRenderer;

	/** Called when this attachment is equipped onto a weapon. */
	@Getter
	@Setter
	private AttachmentHandler applyHandler;

	/** Called when this attachment is removed from a weapon. */
	@Getter
	@Setter
	private AttachmentHandler removeHandler;

	/** Optional HUD crosshair texture path (fully qualified). */
	@Getter
	private final String crosshair;

	/** Optional tooltip provider shown when hovering over this item in inventory. */
	@Getter
	@Setter
	private Function<ItemStack, String> informationProvider;

	public ItemAttachment(AttachmentCategory category, String crosshair) {
		this.category = category;
		this.crosshair = crosshair != null ? NewGunrizonsMod.MODID + ":textures/crosshairs/" + crosshair + ".png"
			: null;
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		stack.setTagCompound(new NBTTagCompound());
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List tooltip, boolean flag) {
		if (tooltip != null && this.informationProvider != null) {
			tooltip.add(this.informationProvider.apply(itemStack));
		}
	}

	@Override
	public String toString() {
		return this.name != null ? "Attachment [" + this.name + "]" : super.toString();
	}

	/** Callback invoked when an attachment is equipped or removed from a weapon. */
	@FunctionalInterface
	public interface AttachmentHandler {

		void apply(ItemAttachment attachment, ItemWeaponInstance weaponInstance);
	}
}
