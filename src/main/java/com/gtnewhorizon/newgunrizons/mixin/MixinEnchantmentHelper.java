package com.gtnewhorizon.newgunrizons.mixin;

import com.gtnewhorizon.newgunrizons.enchantments.GunEnchantment;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.HashMap;
import java.util.Map;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

    @Inject(method = "mapEnchantmentData", at = @At("RETURN"), cancellable = true)
    private static void onMapEnchantmentData(int enchantLevel, ItemStack stack, CallbackInfoReturnable<Map<Integer, EnchantmentData>> cir) {
        if (!(stack.getItem() instanceof ItemWeapon)) return;

        Map<Integer, EnchantmentData> map = cir.getReturnValue();
        if (map == null) {
            map = new HashMap<>();
        }

        for (Enchantment enchantment : Enchantment.enchantmentsList) {
            if (enchantment instanceof GunEnchantment && enchantment.canApplyAtEnchantingTable(stack)) {
                for (int level = enchantment.getMinLevel(); level <= enchantment.getMaxLevel(); level++) {
                    if (enchantLevel >= enchantment.getMinEnchantability(level)
                        && enchantLevel <= enchantment.getMaxEnchantability(level)) {
                        map.put(enchantment.effectId, new EnchantmentData(enchantment, level));
                    }
                }
            }
        }

        if (!map.isEmpty()) {
            cir.setReturnValue(map);
        }
    }
}
