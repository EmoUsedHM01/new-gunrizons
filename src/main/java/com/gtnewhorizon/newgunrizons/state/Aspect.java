package com.gtnewhorizon.newgunrizons.state;

/**
 * A behavior module that drives state transitions for a specific game mechanic.
 * <p>
 * Each aspect defines transition rules (via {@link StateManager.RuleBuilder}).
 * <p>
 * Implementations:
 * <ul>
 * <li>{@code WeaponFireAspect} — firing, recoil, auto-fire</li>
 * <li>{@code WeaponReloadAspect} — loading, unloading, magazine swap</li>
 * <li>{@code WeaponAttachmentAspect} — attachment mode, selection, application</li>
 * <li>{@code MagazineReloadAspect} — magazine bullet loading</li>
 * <li>{@code GrenadeAttackAspect} — grenade arming, throwing, detonation</li>
 * </ul>
 *
 * @param <T> the state enum type this aspect operates on
 * @param <E> the extended state type (e.g. PlayerWeaponInstance)
 */
public interface Aspect<T extends ManagedState<T>, E extends ExtendedState<T>> {

    /**
     * Injects the state manager and configures transition rules for this aspect.
     * Called once during initialization from {@code CommonModContext.init()}.
     */
    void setStateManager(StateManager<T, ? super E> stateManager);
}
