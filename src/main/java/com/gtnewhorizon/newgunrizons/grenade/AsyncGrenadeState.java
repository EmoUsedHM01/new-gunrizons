package com.gtnewhorizon.newgunrizons.grenade;

import lombok.Getter;

@Getter
public class AsyncGrenadeState {

    private final GrenadeState state;
    private final long timestamp;
    private final long duration;

    public AsyncGrenadeState(GrenadeState state, long timestamp) {
        this.state = state;
        this.timestamp = timestamp;
        this.duration = 2147483647L;
    }

    public AsyncGrenadeState(GrenadeState state, long timestamp, long duration) {
        this.state = state;
        this.timestamp = timestamp;
        this.duration = duration;
    }

}
