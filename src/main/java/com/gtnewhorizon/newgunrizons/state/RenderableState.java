package com.gtnewhorizon.newgunrizons.state;

/**
 * Simplified visual states used by the rendering system.
 * <p>
 * Maps complex internal states ({@code WeaponState}, {@code GrenadeState}) to a smaller
 * set of animation states. For weapons the mapping is performed by
 * {@code WeaponRenderer.mapWeaponState()}, for grenades by
 * {@code GrenadeRenderer.getStateDescriptor()}.
 */
public enum RenderableState {

    // --- Base states ---
    NORMAL,
    RUNNING,

    // --- Aiming ---
    ZOOMING,

    // --- Firing ---
    SHOOTING,
    AUTO_SHOOTING,
    ZOOMING_SHOOTING,

    // --- Reload ---
    RELOADING,
    UNLOADING,
    LOAD_ITERATION,
    LOAD_ITERATION_COMPLETED,
    ALL_LOAD_ITERATIONS_COMPLETED,

    // --- Mechanical ---
    EJECT_SPENT_ROUND,

    // --- Attachment mode ---
    MODIFYING,

    // --- Grenade ---
    SAFETY_PIN_OFF,
    STRIKER_LEVER_OFF,
    THROWING,
    THROWN
}
