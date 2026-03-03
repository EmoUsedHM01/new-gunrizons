package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

public enum WeaponState implements ManagedState<WeaponState> {

    READY(false),
    LOAD_REQUESTED,
    LOAD(null, LOAD_REQUESTED, null, true),
    LOAD_ITERATION,
    LOAD_ITERATION_COMPLETED,
    ALL_LOAD_ITERATIONS_COMPLETED,
    UNLOAD_PREPARING,
    UNLOAD_REQUESTED,
    UNLOAD(UNLOAD_PREPARING, UNLOAD_REQUESTED, READY, true),
    FIRING(9),
    RECOILED(10),
    PAUSED(10),
    EJECT_REQUIRED,
    EJECTING,
    MODIFYING_REQUESTED(1),
    MODIFYING(2, null, MODIFYING_REQUESTED, null, false),
    NEXT_ATTACHMENT_REQUESTED,
    NEXT_ATTACHMENT(2, null, NEXT_ATTACHMENT_REQUESTED, null, false),
    ALERT;

    private final WeaponState preparingPhase;
    private final WeaponState permitRequestedPhase;
    private final WeaponState commitPhase;
    @Getter
    private final boolean isTransient;
    @Getter
    private final int priority;

    WeaponState() {
        this(null, null, null, true);
    }

    WeaponState(int priority) {
        this(priority, null, null, null, true);
    }

    WeaponState(boolean isTransient) {
        this(null, null, null, isTransient);
    }

    WeaponState(WeaponState preparingPhase, WeaponState permitRequestedState, WeaponState transactionFinalState,
        boolean isTransient) {
        this(0, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
    }

    WeaponState(int priority, WeaponState preparingPhase, WeaponState permitRequestedState,
        WeaponState transactionFinalState, boolean isTransient) {
        this.priority = priority;
        this.preparingPhase = preparingPhase;
        this.permitRequestedPhase = permitRequestedState;
        this.commitPhase = transactionFinalState;
        this.isTransient = false;
    }

    public WeaponState preparingPhase() {
        return this.preparingPhase;
    }

    public WeaponState permitRequestedPhase() {
        return this.permitRequestedPhase;
    }

    public WeaponState commitPhase() {
        return this.commitPhase;
    }

    public void init(ByteBuf buf) {}

    public void serialize(ByteBuf buf) {}

    static {
        TypeRegistry.getInstance()
            .register(WeaponState.class);
    }
}
