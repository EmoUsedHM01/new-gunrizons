package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.state.ManagedState;

public interface ItemInstanceFactory<T extends ItemInstance<S>, S extends ManagedState<S>> {

    T createItemInstance(EntityLivingBase var1, ItemStack var2, int var3);
}
