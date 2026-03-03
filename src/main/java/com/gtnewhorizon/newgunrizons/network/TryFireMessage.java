package com.gtnewhorizon.newgunrizons.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class TryFireMessage implements IMessage {

    private boolean on;

    public TryFireMessage() {}

    public TryFireMessage(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return this.on;
    }

    public void fromBytes(ByteBuf buf) {
        this.on = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.on);
    }
}
