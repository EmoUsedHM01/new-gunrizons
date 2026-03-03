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

    private static final int DEFAULT_PRIORITY = 0;
    private final GrenadeState preparingPhase;
    private final GrenadeState permitRequestedPhase;
    private final GrenadeState commitPhase;
    @Getter
    private final boolean isTransient;
    @Getter
    private final int priority;

    GrenadeState() {
        this(null, null, null, true);
    }

    GrenadeState(int priority) {
        this(priority, null, null, null, true);
    }

    GrenadeState(boolean isTransient) {
        this(null, null, null, isTransient);
    }

    GrenadeState(GrenadeState preparingPhase, GrenadeState permitRequestedState, GrenadeState transactionFinalState,
        boolean isTransient) {
        this(DEFAULT_PRIORITY, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
    }

    GrenadeState(int priority, GrenadeState preparingPhase, GrenadeState permitRequestedState,
        GrenadeState transactionFinalState, boolean isTransient) {
        this.priority = priority;
        this.preparingPhase = preparingPhase;
        this.permitRequestedPhase = permitRequestedState;
        this.commitPhase = transactionFinalState;
        this.isTransient = false;
    }

    public GrenadeState preparingPhase() {
        return this.preparingPhase;
    }

    public GrenadeState permitRequestedPhase() {
        return this.permitRequestedPhase;
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
