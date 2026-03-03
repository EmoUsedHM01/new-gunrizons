package com.gtnewhorizon.newgunrizons.grenade;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class GrenadeMessage implements IMessage {

    private PlayerGrenadeInstance instance;
    private long activationTimestamp;

    public GrenadeMessage() {}

    public GrenadeMessage(PlayerGrenadeInstance instance, long activationTimestamp) {
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

    public PlayerGrenadeInstance getInstance() {
        return this.instance;
    }

    public long getActivationTimestamp() {
        return this.activationTimestamp;
    }
}
