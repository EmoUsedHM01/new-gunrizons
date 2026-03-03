package com.gtnewhorizon.newgunrizons.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.state.ManagedState;

public interface PlayerItemInstanceFactory<T extends PlayerItemInstance<S>, S extends ManagedState<S>> {

    T createItemInstance(EntityLivingBase var1, ItemStack var2, int var3);
}
