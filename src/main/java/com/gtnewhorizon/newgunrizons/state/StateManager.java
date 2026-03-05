package com.gtnewhorizon.newgunrizons.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Finite state machine engine that manages state transitions for weapons, magazines, and grenades.
 * <p>
 * Transition rules are configured per-{@link Aspect} via the fluent {@link RuleBuilder} API.
 * Each rule specifies: source state → target state, an optional guard predicate, an optional
 * action callback, and whether the transition is automatic or manual.
 *
 * @param <S> the state enum type (e.g. WeaponState)
 * @param <E> the extended state type (e.g. PlayerWeaponInstance)
 */
public class StateManager<S extends ManagedState<S>, E extends ExtendedState<S>> {

    private static final Logger logger = LogManager.getLogger(StateManager.class);

    // ==================== Core ====================

    /** Strategy for comparing two states (typically enum identity). */
    private final StateComparator<S> stateComparator;

    /** Transition rules indexed by the aspect that owns them. */
    private final Map<Aspect<S, ? extends E>, LinkedHashSet<TransitionRule<S, E>>> transitionRules = new HashMap<>();

    // ==================== Constructor ====================

    /**
     * @param stateComparator strategy for comparing states (typically {@code (s1, s2) -> s1 == s2})
     */
    public StateManager(StateComparator<S> stateComparator) {
        this.stateComparator = stateComparator;
    }

    // ==================== Public API ====================

    /**
     * Begins building a transition rule for the given aspect.
     *
     * @param aspect the behavior module this rule belongs to
     * @return a fluent rule builder
     */
    public <EE extends E> RuleBuilder<EE> in(Aspect<S, EE> aspect) {
        return new RuleBuilder<>(aspect);
    }

    /**
     * Attempts to transition the extended state to one of the target states.
     * Uses the current state as the source.
     */
    @SafeVarargs
    public final void changeState(Aspect<S, ? extends E> aspect, E extendedState, S... targetStates) {
        S currentState = extendedState.getState();
        this.executeTransition(aspect, extendedState, currentState, targetStates);
    }

    /**
     * Attempts to transition only if the current state is one of {@code fromStates}.
     */
    @SafeVarargs
    public final void changeStateFromAnyOf(Aspect<S, ? extends E> aspect, E extendedState, Collection<S> fromStates,
        S... targetStates) {
        S currentState = extendedState.getState();
        if (!fromStates.contains(currentState)) {
            return;
        }
        this.executeTransition(aspect, extendedState, currentState, targetStates);
    }

    // ==================== Transition execution ====================

    /**
     * Core transition logic. Iteratively finds and applies matching rules until no more
     * transitions are possible. After the first rule fires, subsequent iterations look
     * for automatic rules (those that fire without explicit targets).
     */
    @SafeVarargs
    protected final void executeTransition(Aspect<S, ? extends E> aspect, E extendedState, S currentState,
        S... targetStates) {
        if (extendedState == null) {
            return;
        }

        // If already at the sole target state, no transition needed
        if (targetStates.length == 1 && this.stateComparator.compare(currentState, targetStates[0])) {
            return;
        }

        S activeState = currentState;
        S[] remainingTargets = targetStates;

        TransitionRule<S, E> matchingRule;
        while ((matchingRule = this.findMatchingRule(aspect, extendedState, activeState, remainingTargets)) != null) {
            extendedState.setState(matchingRule.toState);
            logger.debug("Changed state of {} to {}", extendedState, matchingRule.toState);

            if (matchingRule.action != null) {
                matchingRule.action.execute(extendedState, activeState, matchingRule.toState);
            }

            activeState = matchingRule.toState;
            // After the first explicit transition, only follow automatic rules
            remainingTargets = emptyStateArray();
        }
    }

    // ==================== Rule matching ====================

    @SafeVarargs
    private TransitionRule<S, E> findMatchingRule(Aspect<S, ? extends E> aspect, E extendedState, S currentState,
        S... targetStates) {
        return this.transitionRules.entrySet()
            .stream()
            .filter(e -> e.getKey() == aspect)
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .filter(rule -> rule.matches(this.stateComparator, extendedState, currentState, targetStates))
            .findFirst()
            .orElse(null);
    }

    // ==================== Utilities ====================

    /**
     * Creates an empty state array. Required because Java forbids generic array creation
     * ({@code new S[0]}), so we create {@code new ManagedState[0]} and cast.
     */
    @SuppressWarnings("unchecked")
    private static <S> S[] emptyStateArray() {
        return (S[]) new ManagedState[0];
    }

    // ==================== Inner: Transition rule ====================

    /**
     * A single transition rule: from-state → to-state, with optional guard and action.
     */
    private static class TransitionRule<S extends ManagedState<S>, E extends ExtendedState<S>> {

        final S fromState;
        final S toState;
        final BiPredicate<S, E> guard;
        final TransitionAction<S, E> action;
        final boolean automatic;

        TransitionRule(S fromState, S toState, BiPredicate<S, E> guard, TransitionAction<S, E> action,
            boolean automatic) {
            if (fromState == null) {
                throw new IllegalArgumentException("From-state cannot be null");
            }
            if (toState == null) {
                throw new IllegalArgumentException("To-state cannot be null");
            }
            this.fromState = fromState;
            this.toState = toState;
            this.guard = guard;
            this.action = action;
            this.automatic = automatic;
        }

        @SafeVarargs
        final boolean matches(StateComparator<S> comparator, E context, S fromState, S... targetStates) {
            boolean fromMatches = fromState == null || comparator.compare(this.fromState, fromState);
            boolean toMatches = (this.automatic && targetStates.length == 0) || Arrays.stream(targetStates)
                .anyMatch(
                    target -> comparator.compare(this.toState, target)
                        || comparator.compare(this.toState, target.preparingPhase()));
            boolean guardPasses = this.guard.test(this.toState, context);
            return fromMatches && toMatches && guardPasses;
        }
    }

    // ==================== Inner: Callback interfaces ====================

    /** Compares two managed states for equality. */
    @FunctionalInterface
    public interface StateComparator<S extends ManagedState<S>> {

        boolean compare(S a, S b);
    }

    /** Callback executed when a transition fires. */
    @FunctionalInterface
    public interface TransitionAction<S extends ManagedState<S>, EE> {

        void execute(EE context, S fromState, S toState);
    }

    /** Callback executed during the prepare phase of a transition. */
    @FunctionalInterface
    public interface PrepareCallback<S extends ManagedState<S>, EE> {

        void execute(EE context, S fromState, S toState);
    }

    // ==================== Inner: Rule builder ====================

    /**
     * Fluent API for defining state transition rules.
     * <p>
     * Usage:
     * 
     * <pre>
     * stateManager.in(aspect)
     *     .change(WeaponState.READY)
     *     .to(WeaponState.FIRING)
     *     .when(instance -&gt; instance.hasAmmo())
     *     .withAction(instance -&gt; instance.fire())
     *     .manual();
     * </pre>
     *
     * @param <EE> the specific extended state type for this rule
     */
    public class RuleBuilder<EE extends E> {

        private final Aspect<S, EE> aspect;

        // --- Transition endpoints ---
        private S fromState;
        private S toState;

        // --- Guard predicate ---
        private BiPredicate<S, EE> guard;

        // --- Transition action ---
        private TransitionAction<S, EE> action;

        // --- Prepare phase ---
        private PrepareCallback<S, EE> prepareCallback;
        private Predicate<EE> prepareGuard;

        RuleBuilder(Aspect<S, EE> aspect) {
            this.aspect = aspect;
        }

        // --- Builder methods ---

        /** Sets the source state for this transition. */
        public StateManager<S, E>.RuleBuilder<EE> change(S fromState) {
            this.fromState = fromState;
            return this;
        }

        /** Sets the target state for this transition. */
        public StateManager<S, E>.RuleBuilder<EE> to(S toState) {
            this.toState = toState;
            return this;
        }

        /** Sets a guard predicate (context only). */
        public StateManager<S, E>.RuleBuilder<EE> when(Predicate<EE> guard) {
            this.guard = (s, e) -> guard.test(e);
            return this;
        }

        /** Sets a guard predicate (target state + context). */
        public StateManager<S, E>.RuleBuilder<EE> when(BiPredicate<S, EE> guard) {
            this.guard = guard;
            return this;
        }

        /** Configures a prepare phase with a callback and readiness predicate. */
        public StateManager<S, E>.RuleBuilder<EE> prepare(PrepareCallback<S, EE> prepareCallback,
            Predicate<EE> prepareGuard) {
            this.prepareCallback = prepareCallback;
            this.prepareGuard = prepareGuard;
            return this;
        }

        /** Sets the action executed when this transition fires (context only). */
        public StateManager<S, E>.RuleBuilder<EE> withAction(Consumer<EE> action) {
            this.action = (context, from, to) -> action.accept(context);
            return this;
        }

        /** Finalizes this rule as automatic (fires without explicit request when conditions are met). */
        public StateManager<S, E> automatic() {
            return this.addRule(true);
        }

        /** Finalizes this rule as manual (fires only when explicitly requested via changeState). */
        public StateManager<S, E> manual() {
            return this.addRule(false);
        }

        // --- Rule construction ---

        @SuppressWarnings("unchecked")
        private StateManager<S, E> addRule(boolean automatic) {
            LinkedHashSet<TransitionRule<S, E>> aspectRules = StateManager.this.transitionRules
                .computeIfAbsent(this.aspect, c -> new LinkedHashSet<>());

            if (this.guard == null) {
                this.guard = (s, c) -> true;
            }

            if (this.action == null) {
                this.action = (c, f, t) -> {};
            }

            S effectiveFromState;
            BiPredicate<S, E> effectiveGuard;
            boolean isAutoAfterPrepare;

            // --- Prepare phase ---
            if (this.prepareCallback != null || this.prepareGuard != null) {
                if (automatic) {
                    throw new IllegalStateException("Prepared transition cannot be automatic");
                }

                TransitionRule<S, E> prepareRule = new TransitionRule<>(
                    this.fromState,
                    this.toState.preparingPhase(),
                    (S s, E e) -> this.guard.test(s, (EE) e),
                    (E c, S f, S t) -> {
                        if (this.prepareCallback != null) {
                            this.prepareCallback.execute((EE) c, f, t);
                        }
                    },
                    false);
                aspectRules.add(prepareRule);

                effectiveFromState = this.toState.preparingPhase();
                effectiveGuard = (s, e) -> this.prepareGuard == null || this.prepareGuard.test((EE) e);
                isAutoAfterPrepare = true;
            } else {
                effectiveFromState = this.fromState;
                effectiveGuard = (s, e) -> this.guard.test(s, (EE) e);
                isAutoAfterPrepare = false;
            }

            // Direct transition rule
            TransitionRule<S, E> directRule = new TransitionRule<>(
                effectiveFromState,
                this.toState,
                effectiveGuard,
                (E c, S f, S t) -> this.action.execute((EE) c, f, t),
                isAutoAfterPrepare || automatic);
            aspectRules.add(directRule);

            return StateManager.this;
        }
    }
}
