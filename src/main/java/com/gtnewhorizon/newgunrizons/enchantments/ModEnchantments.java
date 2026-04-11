package com.gtnewhorizon.newgunrizons.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class ModEnchantments {
    public static GunEnchantment armorPiercing;
    public static GunEnchantment collateral;
    public static GunEnchantment etherealRounds;
    public static GunEnchantment fastHands;
    public static GunEnchantment hollowPoint;
    public static GunEnchantment incendiary;
    public static GunEnchantment knockback;
    public static GunEnchantment stability;

    private static int nextFreeId() {
        for (int i = 128; i < 256; i++) {
            if (Enchantment.enchantmentsList[i] == null) {
                return i;
            }
        }
        for (int i = 64; i < 128; i++) {
            if (Enchantment.enchantmentsList[i] == null) {
                return i;
            }
        }
        throw new RuntimeException("No free enchantment IDs available for NewGunrizons");
    }

    public static void init() {
        // GunEnchantment(id, weight, maxLevel, baseMinEnchantability, perLevelEnchantability, enchantabilityRange)
        // weight: higher = more common (10=common, 5=uncommon, 2=rare, 1=very rare)

        armorPiercing = new GunEnchantment(nextFreeId(), 2, 3, 15, 10, 20);
        armorPiercing.setName("armorPiercing");

        collateral = new GunEnchantment(nextFreeId(), 1, 1, 25, 0, 25);
        collateral.setName("collateral");

        etherealRounds = new GunEnchantment(nextFreeId(), 2, 4, 15, 8, 20);
        etherealRounds.setName("etherealRounds");

        fastHands = new GunEnchantment(nextFreeId(), 5, 3, 10, 8, 20);
        fastHands.setName("fastHands");

        hollowPoint = new GunEnchantment(nextFreeId(), 10, 5, 1, 11, 20);
        hollowPoint.setName("hollowPoint");

        incendiary = new GunEnchantment(nextFreeId(), 2, 1, 20, 0, 20);
        incendiary.setName("incendiary");

        knockback = new GunEnchantment(nextFreeId(), 5, 2, 5, 20, 20);
        knockback.setName("gunKnockback");

        stability = new GunEnchantment(nextFreeId(), 5, 4, 5, 8, 20);
        stability.setName("stability");

        System.out.println("[NewGunrizons] Registered enchantments:");
        System.out.println("[NewGunrizons]   Armor Piercing: id=" + armorPiercing.effectId);
        System.out.println("[NewGunrizons]   Collateral: id=" + collateral.effectId);
        System.out.println("[NewGunrizons]   Ethereal Rounds: id=" + etherealRounds.effectId);
        System.out.println("[NewGunrizons]   Fast Hands: id=" + fastHands.effectId);
        System.out.println("[NewGunrizons]   Hollow Point: id=" + hollowPoint.effectId);
        System.out.println("[NewGunrizons]   Incendiary: id=" + incendiary.effectId);
        System.out.println("[NewGunrizons]   Knockback: id=" + knockback.effectId);
        System.out.println("[NewGunrizons]   Stability: id=" + stability.effectId);
        System.out.println("[NewGunrizons]   Enchantment.enchantmentsList[" + hollowPoint.effectId + "] = " + Enchantment.enchantmentsList[hollowPoint.effectId]);
    }

    public static int getLevel(Enchantment enchantment, ItemStack stack) {
        if (stack == null) return 0;
        return EnchantmentHelper.getEnchantmentLevel(enchantment.effectId, stack);
    }
}
