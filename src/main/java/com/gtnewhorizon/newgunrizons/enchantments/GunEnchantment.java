package com.gtnewhorizon.newgunrizons.enchantments;

import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class GunEnchantment extends Enchantment {
    private final int maxLevel;
    private final int baseMinEnchantability;
    private final int perLevelEnchantability;
    private final int enchantabilityRange;

    public GunEnchantment(int id, int weight, int maxLevel, int baseMinEnchantability, int perLevelEnchantability, int enchantabilityRange) {
        super(id, weight, EnumEnchantmentType.bow);
        this.maxLevel = maxLevel;
        this.baseMinEnchantability = baseMinEnchantability;
        this.perLevelEnchantability = perLevelEnchantability;
        this.enchantabilityRange = enchantabilityRange;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }

    @Override
    public int getMinEnchantability(int level) {
        return this.baseMinEnchantability + (level - 1) * this.perLevelEnchantability;
    }

    @Override
    public int getMaxEnchantability(int level) {
        return this.getMinEnchantability(level) + this.enchantabilityRange;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof ItemWeapon;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.canApply(stack);
    }
}
