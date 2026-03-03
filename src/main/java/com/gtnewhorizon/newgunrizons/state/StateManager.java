package com.gtnewhorizon.newgunrizons.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateManager<S extends ManagedState<S>, E extends ExtendedState<S>> {

    private static final Logger logger = LogManager.getLogger(StateManager.class);
    private final StateManager.StateComparator<S> stateComparator;
    private final Map<Aspect<S, ? extends E>, LinkedHashSet<StateManager.TransitionRule<S, E>>> contextRules = new HashMap<>();

    @SuppressWarnings("unchecked")
    private static <T, U> T safeCast(U u) {
        return (T) u;
    }

    public StateManager(StateManager.StateComparator<S> stateComparator) {
        this.stateComparator = stateComparator;
    }

    public <EE extends E> StateManager<S, E>.RuleBuilder<EE> in(Aspect<S, EE> aspect) {
        return new StateManager.RuleBuilder(aspect);
    }

    public StateManager<S, E>.Result changeState(Aspect<S, ? extends E> aspect, E extendedState, S... targetStates) {
        return this.changeState(aspect, extendedState, null, targetStates);
    }

    public StateManager<S, E>.Result changeState(Aspect<S, ? extends E> aspect, E extendedState, Permit<S> permit,
        S... targetStates) {
        S currentState = extendedState.getState();
        return this.changeStateFromTo(aspect, extendedState, permit, currentState, targetStates);
    }

    public StateManager<S, E>.Result changeStateFromAnyOf(Aspect<S, ? extends E> aspect, E extendedState,
        Collection<S> fromStates, S... targetStates) {
        S currentState = extendedState.getState();
        return !fromStates.contains(currentState) ? new StateManager.Result(false, currentState)
            : this.changeStateFromTo(aspect, extendedState, currentState, targetStates);
    }

    protected StateManager<S, E>.Result changeStateFromTo(Aspect<S, ? extends E> aspect, E extendedState,
        S currentState, S... targetStates) {
        return this.changeStateFromTo(aspect, extendedState, null, currentState, targetStates);
    }

    protected StateManager<S, E>.Result changeStateFromTo(Aspect<S, ? extends E> aspect, E extendedState,
        Permit<S> permit, S currentState, S... targetStates) {
        if (extendedState == null) {
            return null;
        } else if (targetStates.length == 1 && Arrays.stream(targetStates)
            .anyMatch((target) -> { return this.stateComparator.compare(currentState, target); })) {
                return new StateManager.Result(false, currentState);
            } else {
                StateManager<S, E>.Result result = null;
                S s = currentState;

                StateManager.TransitionRule newStateRule;
                for (S[] ts = targetStates; (newStateRule = this.findNextStateRule(aspect, extendedState, s, ts))
                    != null; ts = safeCast(new ManagedState[0])) {
                    extendedState.setState((S) newStateRule.toState);
                    logger.debug("Changed state of {} to {}", extendedState, newStateRule.toState);
                    result = new StateManager.Result(true, newStateRule.toState);
                    if (newStateRule.action != null) {
                        result.actionResult = newStateRule.action
                            .execute(extendedState, s, newStateRule.toState, permit);
                    }

                    s = (S) newStateRule.toState;
                }

                if (result == null) {
                    result = new StateManager.Result(false, s);
                }

                return result;
            }
    }

    private StateManager.TransitionRule<S, E> findNextStateRule(Aspect<S, ? extends E> aspect, E extendedState,
        S currentState, S... targetStates) {
        return this.contextRules.entrySet()
            .stream()
            .filter((e) -> { return e.getKey() == aspect; })
            .map((e) -> { return e.getValue(); })
            .flatMap(Collection::stream)
            .filter((rule) -> { return rule.matches(this.stateComparator, extendedState, currentState, targetStates); })
            .findFirst()
            .orElse(null);
    }

    private static class TransitionRule<S extends ManagedState<S>, E extends ExtendedState<S>> {

        S fromState;
        S toState;
        BiPredicate<S, E> predicate;
        StateManager.PostAction<S, E> action;
        boolean auto;

        TransitionRule(S fromState, S toState, BiPredicate<S, E> predicate, StateManager.PostAction<S, E> action,
            boolean auto) {
            if (fromState == null) {
                throw new IllegalArgumentException("From-state cannot be null");
            } else if (toState == null) {
                throw new IllegalArgumentException("To-state cannot be null");
            } else {
                this.fromState = fromState;
                this.toState = toState;
                this.predicate = predicate;
                this.action = action;
                this.auto = auto;
            }
        }

        boolean matches(StateManager.StateComparator<S> stateComparator, E context, S fromState, S... targetStates) {
            boolean result = fromState == null || stateComparator.compare(this.fromState, fromState);
            result = result && (this.auto && targetStates.length == 0 || Arrays.stream(targetStates)
                .anyMatch((targetState) -> {
                    return stateComparator.compare(this.toState, targetState)
                        || stateComparator.compare(this.toState, targetState.preparingPhase())
                        || stateComparator.compare(this.toState, targetState.permitRequestedPhase());
                }));
            result = result && this.predicate.test(this.toState, context);
            return result;
        }
    }

    public interface VoidAction2<EE> {

        void execute(EE var1);
    }

    public interface VoidAction<S extends ManagedState<S>, EE> {

        void execute(EE var1, S var2, S var3);
    }

    public interface VoidPostAction<S extends ManagedState<S>, EE> {

        void execute(EE var1, S var2, S var3, Permit<S> var4);
    }

    public interface PostAction<S extends ManagedState<S>, EE> {

        Object execute(EE var1, S var2, S var3, Permit<S> var4);
    }

    public class Result {

        private final boolean stateChanged;
        private final S state;
        protected Object actionResult;

        private Result(boolean stateChanged, S targetState) {
            this.stateChanged = stateChanged;
            this.state = targetState;
        }

        public boolean isStateChanged() {
            return this.stateChanged;
        }

        public S getState() {
            return this.state;
        }

        public Object getActionResult() {
            return this.actionResult;
        }

    }

    public interface StateComparator<S extends ManagedState<S>> {

        boolean compare(S var1, S var2);
    }

    public class RuleBuilder<EE extends E> {

        private static final long DEFAULT_REQUEST_TIMEOUT = 10000L;
        private final Aspect<S, EE> aspect;
        private S fromState;
        private S toState;
        private StateManager.VoidAction<S, EE> prepareAction;
        private StateManager.PostAction<S, EE> action;
        private BiPredicate<S, EE> predicate;
        private BiFunction<S, EE, Permit<S>> permitProvider;
        private BiFunction<S, EE, Boolean> stateUpdater;
        private PermitManager permitManager;
        private Predicate<EE> preparePredicate;
        private final long requestTimeout = 10000L;
        private boolean isPermitRequired;

        public RuleBuilder(Aspect<S, EE> aspect) {
            this.aspect = aspect;
        }

        public StateManager<S, E>.RuleBuilder<EE> prepare(StateManager.VoidAction<S, EE> prepareAction,
            Predicate<EE> preparePredicate) {
            this.prepareAction = prepareAction;
            this.preparePredicate = preparePredicate;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> change(S fromState) {
            this.fromState = fromState;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> to(S state) {
            this.toState = state;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> when(Predicate<EE> predicate) {
            this.predicate = (s, e) -> { return predicate.test(e); };
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> when(BiPredicate<S, EE> predicate) {
            this.predicate = predicate;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> withPermit(BiFunction<S, EE, Permit<S>> permitProvider,
            BiFunction<S, EE, Boolean> stateUpdater, PermitManager permitManager) {
            this.isPermitRequired = true;
            this.permitProvider = permitProvider;
            this.stateUpdater = stateUpdater;
            this.permitManager = permitManager;
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> withAction(StateManager.VoidPostAction<S, EE> action) {
            this.action = (context, from, to, permit) -> {
                action.execute(context, from, to, permit);
                return null;
            };
            return this;
        }

        public StateManager<S, E>.RuleBuilder<EE> withAction(StateManager.VoidAction2<EE> action) {
            this.action = (context, from, to, permit) -> {
                action.execute(context);
                return null;
            };
            return this;
        }

        public StateManager<S, E> automatic() {
            return this.addRule(true);
        }

        public StateManager<S, E> manual() {
            return this.addRule(false);
        }

        private StateManager<S, E> addRule(boolean auto) {
            LinkedHashSet<StateManager.TransitionRule<S, E>> aspectRules = StateManager.this.contextRules
                .computeIfAbsent(this.aspect, (c) -> { return new LinkedHashSet(); });
            if (this.predicate == null) {
                this.predicate = (s, c) -> { return true; };
            }

            if (this.action == null) {
                this.action = (c, f, t, p) -> { return null; };
            }

            S effectiveFromState;
            BiPredicate<S, E> effectivePredicate;
            boolean isRequestRuleAutoTransitioned;
            StateManager.TransitionRule requestPermitRule;
            if (this.prepareAction == null && this.preparePredicate == null) {
                effectiveFromState = this.fromState;
                effectivePredicate = (s, e) -> { return this.predicate.test(s, StateManager.safeCast(e)); };
                isRequestRuleAutoTransitioned = false;
            } else {
                if (auto) {
                    throw new IllegalStateException("Prepared transition cannot be automatic");
                }

                requestPermitRule = new StateManager.TransitionRule<>(
                    this.fromState,
                    this.toState.preparingPhase(),
                    (S s, E e) -> { return this.predicate.test(s, StateManager.safeCast(e)); },
                    (E c, S f, S t, Permit<S> p) -> {
                        if (this.prepareAction != null) {
                            this.prepareAction.execute(StateManager.safeCast(c), f, t);
                        }

                        return null;
                    },
                    false);
                aspectRules.add(requestPermitRule);
                effectiveFromState = this.toState.preparingPhase();
                effectivePredicate = (s, e) -> {
                    return this.preparePredicate == null || this.preparePredicate.test(StateManager.safeCast(e));
                };
                isRequestRuleAutoTransitioned = true;
            }

            if (this.isPermitRequired) {
                if (auto) {
                    throw new IllegalStateException("Permitted transitions cannot be automatic");
                }

                requestPermitRule = new StateManager.TransitionRule<>(
                    effectiveFromState,
                    this.toState.permitRequestedPhase(),
                    effectivePredicate,
                    (s, f, t, p) -> {
                        this.permitManager.request(
                            p != null ? p : this.permitProvider.apply(t, StateManager.safeCast(s)),
                            s,
                            this::applyPermit);
                        return null;
                    },
                    isRequestRuleAutoTransitioned);
                aspectRules.add(requestPermitRule);
                StateManager.TransitionRule<S, E> rollbackRule = new StateManager.TransitionRule<>(
                    this.toState.permitRequestedPhase(),
                    this.fromState,
                    (S s, E c) -> {
                        return System.currentTimeMillis() > c.getStateUpdateTimestamp() + this.requestTimeout;
                    },
                    (E c, S f, S t, Permit<S> p) -> { return this.action.execute(StateManager.safeCast(c), f, t, p); },
                    true);
                aspectRules.add(rollbackRule);
            } else {
                requestPermitRule = new StateManager.TransitionRule<>(
                    effectiveFromState,
                    this.toState,
                    effectivePredicate,
                    (c, f, t, p) -> { return this.action.execute(StateManager.safeCast(c), f, t, p); },
                    auto);
                aspectRules.add(requestPermitRule);
            }

            return StateManager.this;
        }

        private void applyPermit(Permit<S> processedPermit, E updatedState) {
            S updateToState = processedPermit.getStatus() == Permit.Status.GRANTED ? this.toState : this.fromState;
            StateManager.logger.debug(
                "Applying permit with status {} to {}, changing state to {}",
                processedPermit.getStatus(),
                updatedState,
                this.toState);
            if (this.stateUpdater.apply(updateToState, StateManager.safeCast(updatedState))) {
                this.action.execute(StateManager.safeCast(updatedState), this.fromState, this.toState, processedPermit);
            }

        }
    }
}
