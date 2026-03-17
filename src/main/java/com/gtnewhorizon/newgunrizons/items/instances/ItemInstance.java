package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;

public abstract class ItemInstance {

    private static final String AMMO_TAG = "Ammo";
    private static final String INSTANCE_TAG = "Instance";

    private static final byte TYPE_WEAPON = 0;
    private static final byte TYPE_GRENADE = 1;

    @Setter
    @Getter
    protected EntityLivingBase player;
    @Getter
    protected Item item;
    @Getter
    protected int itemInventoryIndex;

    public ItemInstance() {}

    public ItemInstance(int itemInventoryIndex, EntityLivingBase player) {
        this.itemInventoryIndex = itemInventoryIndex;
        this.player = player;
        ItemStack itemStack = player.getHeldItem();
        if (itemStack != null) {
            this.item = itemStack.getItem();
        }
    }

    public ItemInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        this.itemInventoryIndex = itemInventoryIndex;
        this.player = player;
        if (itemStack != null) {
            this.item = itemStack.getItem();
        }
    }

    public ItemStack getItemStack() {
        return this.player instanceof EntityPlayer
            ? ((EntityPlayer) this.player).inventory.getStackInSlot(this.itemInventoryIndex)
            : null;
    }

    protected void setItemInventoryIndex(int itemInventoryIndex) {
        this.itemInventoryIndex = itemInventoryIndex;
    }

    public void readFromBuf(ByteBuf buf) {
        this.item = Item.getItemById(buf.readInt());
        this.itemInventoryIndex = buf.readInt();
    }

    public void writeToBuf(ByteBuf buf) {
        buf.writeInt(Item.getIdFromItem(this.item));
        buf.writeInt(this.itemInventoryIndex);
    }

    public boolean needsOpticalScopePerspective() {
        return false;
    }

    // ==================== Static NBT helpers ====================

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

    @SuppressWarnings("unchecked")
    public static <T extends ItemInstance> T fromStack(ItemStack itemStack) {
        return (T) deserializeInstance(itemStack);
    }

    public static void toStack(ItemStack itemStack, ItemInstance instance) {
        if (itemStack == null) return;
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        if (instance == null) {
            itemStack.stackTagCompound.removeTag(INSTANCE_TAG);
            return;
        }
        ByteBuf buf = Unpooled.buffer();
        try {
            if (instance instanceof ItemWeaponInstance) {
                buf.writeByte(TYPE_WEAPON);
            } else if (instance instanceof ItemGrenadeInstance) {
                buf.writeByte(TYPE_GRENADE);
            } else {
                return;
            }
            instance.writeToBuf(buf);
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            itemStack.stackTagCompound.setByteArray(INSTANCE_TAG, bytes);
        } finally {
            buf.release();
        }
    }

    private static ItemInstance deserializeInstance(ItemStack itemStack) {
        if (itemStack == null || itemStack.stackTagCompound == null) {
            return null;
        }
        byte[] bytes = itemStack.stackTagCompound.getByteArray(INSTANCE_TAG);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteBuf buf = Unpooled.copiedBuffer(bytes);
        try {
            byte typeId = buf.readByte();
            ItemInstance instance;
            switch (typeId) {
                case TYPE_WEAPON:
                    instance = new ItemWeaponInstance();
                    break;
                case TYPE_GRENADE:
                    instance = new ItemGrenadeInstance();
                    break;
                default:
                    return null;
            }
            instance.readFromBuf(buf);
            return instance;
        } finally {
            buf.release();
        }
    }
}
