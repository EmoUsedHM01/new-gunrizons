package com.gtnewhorizon.newgunrizons.attachment;

import java.util.function.Function;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.model.BedrockModel;

/**
 * Builder for constructing {@link ItemAttachment} instances.
 * <p>
 * Subclassed by {@code ItemScope.Builder} and {@code ItemBullet.Builder}
 * to create specialized attachment types.
 */
public class AttachmentBuilder {

	private String name;
	private BedrockModel model;
	private String textureName;
	private CreativeTabs tab;
	private AttachmentCategory category;
	private int maxStackSize = 1;
	private Function<ItemStack, String> informationProvider;

	private CustomRenderer postRenderer;

	protected ItemAttachment.AttachmentHandler applyHandler;
	protected ItemAttachment.AttachmentHandler removeHandler;

	public AttachmentBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public AttachmentBuilder withCategory(AttachmentCategory category) {
		this.category = category;
		return this;
	}

	public AttachmentBuilder withCreativeTab(CreativeTabs tab) {
		this.tab = tab;
		return this;
	}

	public AttachmentBuilder withModel(BedrockModel model) {
		this.model = model;
		return this;
	}

	public AttachmentBuilder withTextureName(String textureName) {
		this.textureName = textureName.toLowerCase();
		return this;
	}

	public AttachmentBuilder withMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
		return this;
	}

	public AttachmentBuilder withInformationProvider(Function<ItemStack, String> informationProvider) {
		this.informationProvider = informationProvider;
		return this;
	}

	public AttachmentBuilder withPostRender(CustomRenderer postRenderer) {
		this.postRenderer = postRenderer;
		return this;
	}

	public AttachmentBuilder withApply(ItemAttachment.AttachmentHandler applyHandler) {
		this.applyHandler = applyHandler;
		return this;
	}

	public AttachmentBuilder withRemove(ItemAttachment.AttachmentHandler removeHandler) {
		this.removeHandler = removeHandler;
		return this;
	}

	public ItemAttachment build() {
		ItemAttachment attachment = createAttachment();
		attachment.setUnlocalizedName(NewGunrizonsMod.MODID + "_" + this.name);
		attachment.setCreativeTab(this.tab);
		attachment.setMaxStackSize(this.maxStackSize);
		attachment.setName(this.name);
		attachment.setPostRenderer(this.postRenderer);
		attachment.setApplyHandler(this.applyHandler);
		attachment.setRemoveHandler(this.removeHandler);
		attachment.setInformationProvider(this.informationProvider);

		if (this.model != null) {
			attachment.setModel(this.model);
			attachment.setModelTextureName(ensurePngExtension(this.textureName));
		}

		NewGunrizonsMod.proxy.registerItem(this.name, attachment, null);

		return attachment;
	}

	public <V extends ItemAttachment> V build(Class<V> target) {
		return target.cast(this.build());
	}

	protected ItemAttachment createAttachment() {
		return new ItemAttachment(this.category, null);
	}

	static String ensurePngExtension(String filename) {
		if (filename == null || filename.endsWith(".png")) {
			return filename;
		}
		return filename + ".png";
	}
}
