package com.gtnewhorizon.newgunrizons.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public abstract class UniversalObject implements UniversallySerializable {

    private UUID uuid = UUID.randomUUID();

    protected int getSerialVersion() {
        return 0;
    }

    protected UniversalObject() {}

    public void init(ByteBuf buf) {
        if (this.getSerialVersion() != buf.readInt()) {
            throw new IndexOutOfBoundsException("Serial version mismatch");
        } else {
            this.uuid = new UUID(buf.readLong(), buf.readLong());
        }
    }

    public void serialize(ByteBuf buf) {
        buf.writeInt(this.getSerialVersion());
        buf.writeLong(this.uuid.getMostSignificantBits());
        buf.writeLong(this.uuid.getLeastSignificantBits());
    }
}
