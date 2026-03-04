package com.gtnewhorizon.newgunrizons.weapon;

import lombok.Getter;

@Getter
public class WeaponStateTimed {

    private final WeaponState state;
    private final long timestamp;
    private final long duration;
    private boolean isInfinite;

    public WeaponStateTimed(WeaponState state, long timestamp) {
        this.state = state;
        this.timestamp = timestamp;
        this.duration = Integer.MAX_VALUE;
        this.isInfinite = true;
    }

    public WeaponStateTimed(WeaponState state, long timestamp, long duration) {
        this.state = state;
        this.timestamp = timestamp;
        this.duration = duration;
    }

}
