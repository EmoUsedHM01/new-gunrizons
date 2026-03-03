package com.gtnewhorizon.newgunrizons.state;

public interface ExtendedState<T extends ManagedState<T>> {

    boolean setState(T var1);

    T getState();

    long getStateUpdateTimestamp();

    <E extends ExtendedState<T>> void prepareTransaction(E var1);
}
