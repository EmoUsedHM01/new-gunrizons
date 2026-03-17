package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface ItemInstanceFactory<T extends ItemInstance> {

    T createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot);
}
