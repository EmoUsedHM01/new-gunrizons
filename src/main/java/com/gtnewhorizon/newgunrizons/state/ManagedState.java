package com.gtnewhorizon.newgunrizons.state;

import com.gtnewhorizon.newgunrizons.network.UniversallySerializable;

public interface ManagedState<T extends ManagedState<T>> extends UniversallySerializable {

    default T preparingPhase() {
        return null;
    }

    default T permitRequestedPhase() {
        return null;
    }

    default T commitPhase() {
        return null;
    }
}
