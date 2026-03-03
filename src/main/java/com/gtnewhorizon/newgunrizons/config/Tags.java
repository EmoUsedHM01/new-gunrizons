package com.gtnewhorizon.newgunrizons.config;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public final class Tags {

    private static final String AMMO_TAG = "Ammo";
    private static final String INSTANCE_TAG = "Instance";

    public static int getAmmo(ItemStack itemStack) {
        return itemStack != null && itemStack.stackTagCompound != null ? itemStack.stackTagCompound.getInteger(AMMO_TAG)
            : 0;
    }

    public static void setAmmo(ItemStack itemStack, int ammo) {
        if (itemStack != null) {
            if (itemStack.stackTagCompound == null) {
                itemStack.stackTagCompound = new NBTTagCompound();
            }
            itemStack.stackTagCompound.setInteger(AMMO_TAG, ammo);
        }
    }

    public static PlayerItemInstance<?> getInstance(ItemStack itemStack) {
        if (itemStack != null && itemStack.stackTagCompound != null) {
            byte[] bytes = itemStack.stackTagCompound.getByteArray(INSTANCE_TAG);
            return bytes != null && bytes.length > 0 ? (PlayerItemInstance) TypeRegistry.getInstance()
                .fromBytes(Unpooled.wrappedBuffer(bytes)) : null;
        } else {
            return null;
        }
    }

    public static <T extends PlayerItemInstance<?>> T getInstance(ItemStack itemStack, Class<T> targetClass) {
        if (itemStack != null && itemStack.stackTagCompound != null) {
            byte[] bytes = itemStack.stackTagCompound.getByteArray(INSTANCE_TAG);
            if (bytes != null && bytes.length > 0) {
                try {
                    return (T) targetClass.cast(
                        TypeRegistry.getInstance()
                            .fromBytes(Unpooled.wrappedBuffer(bytes)));
                } catch (RuntimeException var4) {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static void setInstance(ItemStack itemStack, PlayerItemInstance<?> instance) {
        if (itemStack != null) {
            if (itemStack.stackTagCompound == null) {
                itemStack.stackTagCompound = new NBTTagCompound();
            }
            ByteBuf buf = Unpooled.buffer();
            if (instance != null) {
                TypeRegistry.getInstance()
                    .toBytes(instance, buf);
                itemStack.stackTagCompound.setByteArray("Instance", buf.array());
            } else {
                itemStack.stackTagCompound.removeTag("Instance");
            }

        }
    }
}
