package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import lombok.Getter;

public enum MagazineState implements ManagedState<MagazineState> {

    READY(false),
    LOAD(true);

    @Getter
    private final MagazineState preparingPhase;
    @Getter
    private final MagazineState commitPhase;
    @Getter
    private final boolean isTransient;
    @Getter
    private final int priority;

    MagazineState() {
        this(null, null, true);
    }

    MagazineState(boolean isTransient) {
        this(null, null, isTransient);
    }

    MagazineState(MagazineState preparingPhase, MagazineState commitPhase, boolean isTransient) {
        this(0, preparingPhase, commitPhase, isTransient);
    }

    MagazineState(int priority, MagazineState preparingPhase, MagazineState commitPhase, boolean isTransient) {
        this.priority = priority;
        this.preparingPhase = preparingPhase;
        this.commitPhase = commitPhase;
        this.isTransient = isTransient;
    }

    static {
        TypeRegistry.getInstance()
            .register(MagazineState.class);
    }
}
