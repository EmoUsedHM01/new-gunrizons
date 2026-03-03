package com.gtnewhorizon.newgunrizons.util;

import lombok.Getter;

@Getter
public final class Tuple<U, V> {

    private final U u;
    private final V v;

    public Tuple(U u, V v) {
        this.u = u;
        this.v = v;
    }

}
