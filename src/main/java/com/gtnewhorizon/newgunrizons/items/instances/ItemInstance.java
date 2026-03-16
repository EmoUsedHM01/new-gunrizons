package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.network.UniversalObject;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;

public class ItemInstance<S extends ManagedState> extends UniversalObject {

    private static final String AMMO_TAG = "Ammo";
    private static final String INSTANCE_TAG = "Instance";
    @Getter
    protected S state;
    @Getter
    protected long stateUpdateTimestamp = System.currentTimeMillis();
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

    public void init(ByteBuf buf) {
        super.init(buf);
        this.item = Item.getItemById(buf.readInt());
        this.itemInventoryIndex = buf.readInt();
        this.state = TypeRegistry.getInstance()
            .fromBytes(buf);
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(Item.getIdFromItem(this.item));
        buf.writeInt(this.itemInventoryIndex);
        TypeRegistry.getInstance()
            .toBytes(this.state, buf);
    }

    public boolean setState(S state) {
        this.state = state;
        this.stateUpdateTimestamp = System.currentTimeMillis();
        return false;
    }

    protected void updateWith(ItemInstance<S> otherState, boolean updateManagedState) {
        if (updateManagedState) {
            this.setState(otherState.getState());
        }

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

    public static ItemInstance<?> fromStack(ItemStack itemStack) {
        return deserializeInstance(itemStack);
    }

    public static <T extends ItemInstance<?>> T fromStack(ItemStack itemStack, Class<T> targetClass) {
        try {
            return targetClass.cast(deserializeInstance(itemStack));
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static void toStack(ItemStack itemStack, ItemInstance<?> instance) {
        if (itemStack != null) {
            if (itemStack.stackTagCompound == null) {
                itemStack.stackTagCompound = new NBTTagCompound();
            }
            if (instance != null) {
                ByteBuf buf = Unpooled.buffer();
                try {
                    TypeRegistry.getInstance()
                        .toBytes(instance, buf);
                    byte[] bytes = new byte[buf.readableBytes()];
                    buf.readBytes(bytes);
                    itemStack.stackTagCompound.setByteArray(INSTANCE_TAG, bytes);
                } finally {
                    buf.release();
                }
            } else {
                itemStack.stackTagCompound.removeTag(INSTANCE_TAG);
            }
        }
    }

    private static ItemInstance<?> deserializeInstance(ItemStack itemStack) {
        if (itemStack == null || itemStack.stackTagCompound == null) {
            return null;
        }
        byte[] bytes = itemStack.stackTagCompound.getByteArray(INSTANCE_TAG);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteBuf buf = Unpooled.copiedBuffer(bytes);
        try {
            return TypeRegistry.getInstance()
                .fromBytes(buf);
        } finally {
            buf.release();
        }
    }

    static {
        TypeRegistry.getInstance()
            .register(ItemInstance.class);
        TypeRegistry.getInstance()
            .register(ItemWeaponInstance.class);
    }
}
