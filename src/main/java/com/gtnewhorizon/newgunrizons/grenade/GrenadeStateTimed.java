package com.gtnewhorizon.newgunrizons.grenade;

import lombok.Getter;

@Getter
public class GrenadeStateTimed {

    private final GrenadeState state;
    private final long timestamp;
    private final long duration;

    public GrenadeStateTimed(GrenadeState state, long timestamp) {
        this.state = state;
        this.timestamp = timestamp;
        this.duration = Integer.MAX_VALUE;
    }

    public GrenadeStateTimed(GrenadeState state, long timestamp, long duration) {
        this.state = state;
        this.timestamp = timestamp;
        this.duration = duration;
    }

}
