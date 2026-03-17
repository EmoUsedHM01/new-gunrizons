package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

import lombok.Getter;
import lombok.Setter;

public class RenderContext {

	@Getter
	private EntityLivingBase player;
	private ItemStack itemStack;
	@Setter
	@Getter
	private float limbSwing;
	@Setter
	@Getter
	private float limbSwingAmount;
	@Setter
	@Getter
	private float ageInTicks;
	@Setter
	@Getter
	private float netHeadYaw;
	@Setter
	@Getter
	private float headPitch;
	@Setter
	@Getter
	private float scale;
	@Setter
	@Getter
	private float transitionProgress;
	@Setter
    @Getter
    private TransformType transformType;
	@Setter
	@Getter
	private RenderableState fromState;
	@Setter
	@Getter
	private RenderableState toState;
	@Setter
	@Getter
	private ItemInstance itemInstance;

	public RenderContext(EntityLivingBase player, ItemStack itemStack) {
		this.player = player;
		this.itemStack = itemStack;
	}

	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}

	public void setWeapon(ItemStack weapon) {
		this.itemStack = weapon;
	}

	public ItemStack getWeapon() {
		return this.itemStack;
	}

    public ItemWeaponInstance getWeaponInstance() {
		if (this.itemInstance instanceof ItemWeaponInstance) {
			return (ItemWeaponInstance) this.itemInstance;
		} else {
			ItemInstance itemInstance = ItemInstanceRegistry.INSTANCE.getItemInstance(this.player, this.itemStack);
			return itemInstance instanceof ItemWeaponInstance ? (ItemWeaponInstance) itemInstance : null;
		}
	}
}
