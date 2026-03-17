package com.gtnewhorizon.newgunrizons.state;

/**
 * Interface for objects that hold a finite state machine state.
 *
 * @param <S> the state enum type
 */
public interface Stateful<S> {

    S getState();

    void setState(S state);

    long getStateUpdateTimestamp();
}
