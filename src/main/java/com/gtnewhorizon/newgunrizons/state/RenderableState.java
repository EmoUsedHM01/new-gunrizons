package com.gtnewhorizon.newgunrizons.state;

/**
 * Simplified visual states used by the rendering system.
 * <p>
 * Maps complex internal states ({@code WeaponState}, {@code GrenadeState}) to a smaller
 * set of animation states. For weapons the mapping is performed by
 * {@code WeaponRenderer.mapWeaponState()}, for grenades by
 * {@code GrenadeRenderer.mapGrenadeState()}.
 */
public enum RenderableState {

    // --- Base states ---
    IDLE,
    RUNNING,

    // --- Aiming ---
    ZOOMING,

    // --- Firing ---
    SHOOTING,
    ZOOMING_SHOOTING,

    // --- Reload ---
    RELOADING_START,
    RELOADING_ITERATION,
    RELOADING_END,

    // --- Attachment mode ---
    MODIFYING,

    // --- Grenade ---
    SAFETY_PIN_OFF,
    STRIKER_LEVER_OFF,
    THROWING,
    THROWN
}
