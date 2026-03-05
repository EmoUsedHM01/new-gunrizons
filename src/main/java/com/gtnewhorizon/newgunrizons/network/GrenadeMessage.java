package com.gtnewhorizon.newgunrizons.network;

import com.gtnewhorizon.newgunrizons.items.instances.ItemGrenadeInstance;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class GrenadeMessage implements IMessage {

    private ItemGrenadeInstance instance;
    private long activationTimestamp;

    public GrenadeMessage() {}

    public GrenadeMessage(ItemGrenadeInstance instance, long activationTimestamp) {
        this.instance = instance;
        this.activationTimestamp = activationTimestamp;
    }

    public void fromBytes(ByteBuf buf) {
        this.instance = TypeRegistry.getInstance()
            .fromBytes(buf);
        this.activationTimestamp = buf.readLong();
    }

    public void toBytes(ByteBuf buf) {
        TypeRegistry.getInstance()
            .toBytes(this.instance, buf);
        buf.writeLong(this.activationTimestamp);
    }

    public ItemGrenadeInstance getInstance() {
        return this.instance;
    }

    public long getActivationTimestamp() {
        return this.activationTimestamp;
    }
}
