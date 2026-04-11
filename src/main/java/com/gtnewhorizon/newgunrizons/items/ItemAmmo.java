package com.gtnewhorizon.newgunrizons.items;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemAmmo extends Item {
   private final List<ItemWeapon> compatibleWeapons = new ArrayList<>();

   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.onCreated(stack, worldIn, playerIn);
      stack.setTagCompound(new NBTTagCompound());
   }

   public void addCompatibleWeapon(ItemWeapon weapon) {
      this.compatibleWeapons.add(weapon);
   }

   public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean flag) {
      tooltip.add("Compatible guns:");
      this.compatibleWeapons.forEach(weapon -> tooltip.add(weapon.getName()));
   }
}
