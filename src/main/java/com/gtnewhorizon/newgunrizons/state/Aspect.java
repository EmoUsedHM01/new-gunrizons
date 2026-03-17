package com.gtnewhorizon.newgunrizons.state;

/**
 * A behavior module that defines state transition rules for a particular
 * concern (firing, reloading, attachments, grenade attacks).
 *
 * @param <S> the state enum type
 * @param <E> the stateful context type
 */
public interface Aspect<S, E extends Stateful<S>> {

    void setStateManager(StateManager<S, ? super E> stateManager);
}
