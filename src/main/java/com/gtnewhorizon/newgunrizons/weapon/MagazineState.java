package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

public enum MagazineState implements ManagedState<MagazineState> {

    READY(false),
    LOAD_REQUESTED,
    LOAD(null, LOAD_REQUESTED, null, true);

    private final MagazineState preparingPhase;
    private final MagazineState permitRequestedPhase;
    private final MagazineState commitPhase;
    @Getter
    private final boolean isTransient;
    @Getter
    private final int priority;

    MagazineState() {
        this(null, null, null, true);
    }

    MagazineState(boolean isTransient) {
        this(null, null, null, isTransient);
    }

    MagazineState(MagazineState preparingPhase, MagazineState permitRequestedState, MagazineState transactionFinalState,
        boolean isTransient) {
        this(0, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
    }

    MagazineState(int priority, MagazineState preparingPhase, MagazineState permitRequestedState,
        MagazineState transactionFinalState, boolean isTransient) {
        this.priority = priority;
        this.preparingPhase = preparingPhase;
        this.permitRequestedPhase = permitRequestedState;
        this.commitPhase = transactionFinalState;
        this.isTransient = isTransient;
    }

    public MagazineState preparingPhase() {
        return this.preparingPhase;
    }

    public MagazineState permitRequestedPhase() {
        return this.permitRequestedPhase;
    }

    public MagazineState commitPhase() {
        return this.commitPhase;
    }

    public void init(ByteBuf buf) {}

    public void serialize(ByteBuf buf) {}

    static {
        TypeRegistry.getInstance()
            .register(MagazineState.class);
    }
}
