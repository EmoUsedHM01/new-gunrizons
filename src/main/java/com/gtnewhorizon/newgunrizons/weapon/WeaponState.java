package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

public enum WeaponState implements ManagedState {

    IDLE,
    RELOADING_START,
    RELOADING_ITERATION,
    RELOADING_ITERATION_COMPLETED,
    RELOADING_END,
    SHOOTING,
    MODIFYING,
    NO_AMMO;

    static {
        TypeRegistry.getInstance()
            .register(WeaponState.class);
    }
}
