package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import lombok.Getter;

public enum WeaponState implements ManagedState {

    IDLE,
    RELOADING_START,
    RELOADING_ITERATION,
    RELOADING_ITERATION_COMPLETED,
    RELOADING_END,
    SHOOTING,
    RECOILED,
    MODIFYING,
    NEXT_ATTACHMENT,
    NO_AMMO;

    static {
        TypeRegistry.getInstance()
            .register(WeaponState.class);
    }
}
