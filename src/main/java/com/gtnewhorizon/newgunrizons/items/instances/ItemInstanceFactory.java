package com.gtnewhorizon.newgunrizons.items.instances;

import com.gtnewhorizon.newgunrizons.state.ManagedState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface ItemInstanceFactory<T extends ItemInstance<S>, S extends ManagedState<S>> {
   T createItemInstance(EntityLivingBase var1, ItemStack var2, int var3);
}
