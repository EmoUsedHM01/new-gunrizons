package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.grenade.GrenadeState;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;

import lombok.Getter;
import lombok.Setter;

public class ItemGrenadeInstance extends ItemInstance<GrenadeState> {

	private static final int SERIAL_VERSION = 13;
	@Getter
	private int ammo;
	@Setter
	@Getter
	private long activationTimestamp;
	@Setter
	@Getter
	private long lastSafetyPinAlertTimestamp;
	@Getter
	@Setter
	private boolean throwingFar;

	public ItemGrenadeInstance() {}

	public ItemGrenadeInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
		super(itemInventoryIndex, player, itemStack);
	}

	protected int getSerialVersion() {
		return SERIAL_VERSION;
	}

	public boolean setState(GrenadeState state) {
        return super.setState(state);
	}

	protected void setAmmo(int ammo) {
		if (ammo != this.ammo) {
			this.ammo = ammo;
		}
	}

	protected void updateWith(ItemInstance<GrenadeState> otherItemInstance, boolean updateManagedState) {
		super.updateWith(otherItemInstance, updateManagedState);
		ItemGrenadeInstance other = (ItemGrenadeInstance) otherItemInstance;
		this.setAmmo(other.ammo);
	}

	public ItemGrenade getWeapon() {
		return (ItemGrenade) this.item;
	}

	public String toString() {
		return this.getWeapon()
			.getName() + "["
			+ this.getUuid()
			+ "]";
	}

	static {
		TypeRegistry.getInstance()
			.register(ItemGrenadeInstance.class);
	}
}
