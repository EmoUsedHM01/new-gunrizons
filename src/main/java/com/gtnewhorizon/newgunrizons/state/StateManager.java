package com.gtnewhorizon.newgunrizons.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;

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
public class StateManager<S extends ManagedState, E extends ItemInstance<S>> {

    /** Strategy for comparing two states (typically enum identity). */
    private final StateComparator<S> stateComparator;

    /** Transition rules indexed by the aspect that owns them. */
    private final Map<Aspect<S, ? extends E>, LinkedHashSet<TransitionRule<S, E>>> transitionRules = new HashMap<>();

    /**
     * @param stateComparator strategy for comparing states (typically {@code (s1, s2) -> s1 == s2})
     */
    public StateManager(StateComparator<S> stateComparator) {
        this.stateComparator = stateComparator;
    }

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

    @SafeVarargs
    protected final void executeTransition(Aspect<S, ? extends E> aspect, E extendedState, S currentState,
        S... targetStates) {
        if (extendedState == null) {
            return;
        }

        if (targetStates.length == 1 && this.stateComparator.compare(currentState, targetStates[0])) {
            return;
        }

        S activeState = currentState;
        S[] remainingTargets = targetStates;

        TransitionRule<S, E> matchingRule;
        while ((matchingRule = this.findMatchingRule(aspect, extendedState, activeState, remainingTargets)) != null) {
            extendedState.setState(matchingRule.toState);

            if (matchingRule.action != null) {
                matchingRule.action.execute(extendedState, activeState, matchingRule.toState);
            }

            activeState = matchingRule.toState;
            remainingTargets = emptyStateArray();
        }
    }

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

    @SuppressWarnings("unchecked")
    private static <S> S[] emptyStateArray() {
        return (S[]) new ManagedState[0];
    }

    private static class TransitionRule<S extends ManagedState, E extends ItemInstance<S>> {

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
                .anyMatch(target -> comparator.compare(this.toState, target));
            boolean guardPasses = this.guard.test(this.toState, context);
            return fromMatches && toMatches && guardPasses;
        }
    }

    @FunctionalInterface
    public interface StateComparator<S extends ManagedState> {

        boolean compare(S a, S b);
    }

    @FunctionalInterface
    public interface TransitionAction<S extends ManagedState, EE> {

        void execute(EE context, S fromState, S toState);
    }

    /**
     * Fluent API for defining state transition rules.
     *
     * @param <EE> the specific extended state type for this rule
     */
    public class RuleBuilder<EE extends E> {

        private final Aspect<S, EE> aspect;
        private S fromState;
        private S toState;
        private BiPredicate<S, EE> guard;
        private TransitionAction<S, EE> action;

        RuleBuilder(Aspect<S, EE> aspect) {
            this.aspect = aspect;
        }

        public StateManager<S, E>.RuleBuilder<EE> change(S fromState) {
            this.fromState = fromState;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> to(S toState) {
            this.toState = toState;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> when(Predicate<EE> guard) {
            this.guard = (s, e) -> guard.test(e);
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> when(BiPredicate<S, EE> guard) {
            this.guard = guard;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> withAction(Consumer<EE> action) {
            this.action = (context, from, to) -> action.accept(context);
            return this;
        }

        public StateManager<S, E> automatic() {
            return this.addRule(true);
        }

        public StateManager<S, E> manual() {
            return this.addRule(false);
        }

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

            TransitionRule<S, E> rule = new TransitionRule<>(
                this.fromState,
                this.toState,
                (S s, E e) -> this.guard.test(s, (EE) e),
                (E c, S f, S t) -> this.action.execute((EE) c, f, t),
                automatic);
            aspectRules.add(rule);

            return StateManager.this;
        }
    }
}
