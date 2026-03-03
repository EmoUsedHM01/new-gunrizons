package com.gtnewhorizon.newgunrizons.state;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.network.UniversalObject;

import io.netty.buffer.ByteBuf;

public class Permit<S extends ManagedState<S>> extends UniversalObject {

    protected S state;
    protected Permit.Status status;
    protected long timestamp;

    public Permit() {
        this.status = Permit.Status.UNKNOWN;
    }

    public Permit(S state) {
        this.state = state;
        this.status = Permit.Status.REQUESTED;
        this.timestamp = System.currentTimeMillis();
    }

    public S getState() {
        return this.state;
    }

    public void setStatus(Permit.Status status) {
        this.status = status;
    }

    public Permit.Status getStatus() {
        return this.status;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void init(ByteBuf buf) {
        super.init(buf);
        this.timestamp = buf.readLong();
        this.status = Permit.Status.values()[buf.readInt()];
        this.state = TypeRegistry.getInstance()
            .fromBytes(buf);
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeLong(this.timestamp);
        buf.writeInt(this.status.ordinal());
        TypeRegistry.getInstance()
            .toBytes(this.state, buf);
    }

    static {
        TypeRegistry.getInstance()
            .register(Permit.class);
    }

    public enum Status {
        REQUESTED,
        GRANTED,
        DENIED,
        UNKNOWN;
    }
}
