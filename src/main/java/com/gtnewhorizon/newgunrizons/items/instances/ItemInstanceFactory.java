package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.state.ManagedState;

public interface ItemInstanceFactory<T extends ItemInstance<S>, S extends ManagedState> {

    T createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot);
}
