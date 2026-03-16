package com.gtnewhorizon.newgunrizons.state;

import com.gtnewhorizon.newgunrizons.network.UniversallySerializable;

/**
 * Interface for state enums used by the finite state machine framework.
 * Implementations: {@code WeaponState}, {@code MagazineState}, {@code GrenadeState}.
 *
 * @param <T> self-referencing type bound for the state enum
 */
public interface ManagedState extends UniversallySerializable {
}
