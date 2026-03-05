package com.gtnewhorizon.newgunrizons.grenade;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

public enum GrenadeState implements ManagedState<GrenadeState> {

    READY(false),
    SAFETY_PING_OFF(9),
    STRIKER_LEVER_RELEASED(9),
    THROWING(9),
    THROWN(9),
    EXPLODED_IN_HANDS(9);

    private final GrenadeState preparingPhase;
    private final GrenadeState commitPhase;
    @Getter
    private final boolean isTransient;
    @Getter
    private final int priority;

    GrenadeState() {
        this(0, null, null, true);
    }

    GrenadeState(int priority) {
        this(priority, null, null, true);
    }

    GrenadeState(boolean isTransient) {
        this(0, null, null, isTransient);
    }

    GrenadeState(int priority, GrenadeState preparingPhase, GrenadeState commitPhase, boolean isTransient) {
        this.priority = priority;
        this.preparingPhase = preparingPhase;
        this.commitPhase = commitPhase;
        this.isTransient = false;
    }

    public GrenadeState preparingPhase() {
        return this.preparingPhase;
    }

    public GrenadeState commitPhase() {
        return this.commitPhase;
    }

    public void init(ByteBuf buf) {}

    public void serialize(ByteBuf buf) {}

    static {
        TypeRegistry.getInstance()
            .register(GrenadeState.class);
    }
}
