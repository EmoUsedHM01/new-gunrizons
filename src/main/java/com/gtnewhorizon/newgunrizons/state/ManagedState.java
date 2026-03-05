package com.gtnewhorizon.newgunrizons.state;

import com.gtnewhorizon.newgunrizons.network.UniversallySerializable;

/**
 * Interface for state enums used by the finite state machine framework.
 * <p>
 * Each state can define optional intermediate phases for multistep transitions:
 * <ul>
 * <li>{@link #preparingPhase()} — entered before the main transition (e.g. playing an animation)</li>
 * <li>{@link #commitPhase()} — entered after the transition is fully committed</li>
 * </ul>
 * Implementations: {@code WeaponState}, {@code MagazineState}, {@code GrenadeState}.
 *
 * @param <T> self-referencing type bound for the state enum
 */
public interface ManagedState<T extends ManagedState<T>> extends UniversallySerializable {

    /** Returns the intermediate state entered during preparation, or null if none. */
    default T preparingPhase() {
        return null;
    }

    /** Returns the state entered after a transition is fully committed, or null if none. */
    default T commitPhase() {
        return null;
    }
}
