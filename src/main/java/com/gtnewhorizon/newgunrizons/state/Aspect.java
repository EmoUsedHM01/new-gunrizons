package com.gtnewhorizon.newgunrizons.state;

public interface Aspect<T extends ManagedState<T>, E extends ExtendedState<T>> {

    void setStateManager(StateManager<T, ? super E> var1);

    void setPermitManager(PermitManager var1);
}
