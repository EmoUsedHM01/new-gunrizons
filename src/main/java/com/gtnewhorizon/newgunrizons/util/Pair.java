package com.gtnewhorizon.newgunrizons.util;

import lombok.Getter;

@Getter
public final class Pair<U, V> {

    private final U u;
    private final V v;

    public Pair(U u, V v) {
        this.u = u;
        this.v = v;
    }

}
