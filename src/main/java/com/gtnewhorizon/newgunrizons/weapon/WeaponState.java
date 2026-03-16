package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import lombok.Getter;

@Getter
public enum WeaponState implements ManagedState<WeaponState> {

    IDLE,
    RELOADING_START,
    RELOADING_ITERATION,
    RELOADING_ITERATION_COMPLETED,
    RELOADING_END,
    SHOOTING(9),
    RECOILED(10),
    MODIFYING(2),
    NEXT_ATTACHMENT(2),
    NO_AMMO;

    private final int priority;

    WeaponState() {
        this(0);
    }

    WeaponState(int priority) {
        this.priority = priority;
    }

    static {
        TypeRegistry.getInstance()
            .register(WeaponState.class);
    }
}
