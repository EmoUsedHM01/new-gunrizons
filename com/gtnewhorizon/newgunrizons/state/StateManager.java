package com.gtnewhorizon.newgunrizons.state;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateManager<S extends ManagedState<S>, E extends ItemInstance<S>> {
   private final StateManager.StateComparator<S> stateComparator;
   private final Map<Aspect<S, ? extends E>, LinkedHashSet<StateManager.TransitionRule<S, E>>> transitionRules = new HashMap<>();

   public StateManager(StateManager.StateComparator<S> stateComparator) {
      this.stateComparator = stateComparator;
   }

   public <EE extends E> StateManager<S, E>.RuleBuilder<EE> in(Aspect<S, EE> aspect) {
      return new StateManager.RuleBuilder<>(aspect);
   }

   @SafeVarargs
   public final void changeState(Aspect<S, ? extends E> aspect, E extendedState, S... targetStates) {
      S currentState = extendedState.getState();
      this.executeTransition(aspect, extendedState, currentState, targetStates);
   }

   @SafeVarargs
   public final void changeStateFromAnyOf(Aspect<S, ? extends E> aspect, E extendedState, Collection<S> fromStates, S... targetStates) {
      S currentState = extendedState.getState();
      if (fromStates.contains(currentState)) {
         this.executeTransition(aspect, extendedState, currentState, targetStates);
      }
   }

   @SafeVarargs
   protected final void executeTransition(Aspect<S, ? extends E> aspect, E extendedState, S currentState, S... targetStates) {
      if (extendedState != null) {
         if (targetStates.length != 1 || !this.stateComparator.compare(currentState, targetStates[0])) {
            S activeState = currentState;

            StateManager.TransitionRule<S, E> matchingRule;
            for (S[] remainingTargets = targetStates;
               (matchingRule = this.findMatchingRule(aspect, extendedState, activeState, remainingTargets)) != null;
               remainingTargets = (S[])emptyStateArray()
            ) {
               extendedState.setState(matchingRule.toState);
               if (matchingRule.action != null) {
                  matchingRule.action.execute(extendedState, activeState, matchingRule.toState);
               }

               activeState = matchingRule.toState;
            }
         }
      }
   }

   @SafeVarargs
   private StateManager.TransitionRule<S, E> findMatchingRule(Aspect<S, ? extends E> aspect, E extendedState, S currentState, S... targetStates) {
      return this.transitionRules
         .entrySet()
         .stream()
         .filter(e -> e.getKey() == aspect)
         .map(Entry::getValue)
         .flatMap(Collection::stream)
         .filter(rule -> rule.matches(this.stateComparator, extendedState, currentState, targetStates))
         .findFirst()
         .orElse(null);
   }

   private static <S> S[] emptyStateArray() {
      return (S[])(new ManagedState[0]);
   }

   @FunctionalInterface
   public interface PrepareCallback<S extends ManagedState<S>, EE> {
      void execute(EE var1, S var2, S var3);
   }

   public class RuleBuilder<EE extends E> {
      private final Aspect<S, EE> aspect;
      private S fromState;
      private S toState;
      private BiPredicate<S, EE> guard;
      private StateManager.TransitionAction<S, EE> action;
      private StateManager.PrepareCallback<S, EE> prepareCallback;
      private Predicate<EE> prepareGuard;

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

      public StateManager<S, E>.RuleBuilder<EE> prepare(StateManager.PrepareCallback<S, EE> prepareCallback, Predicate<EE> prepareGuard) {
         this.prepareCallback = prepareCallback;
         this.prepareGuard = prepareGuard;
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

      private StateManager<S, E> addRule(boolean automatic) {
         LinkedHashSet<StateManager.TransitionRule<S, E>> aspectRules = StateManager.this.transitionRules
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
         if (this.prepareCallback == null && this.prepareGuard == null) {
            effectiveFromState = this.fromState;
            effectiveGuard = (s, e) -> this.guard.test(s, (EE)e);
            isAutoAfterPrepare = false;
         } else {
            if (automatic) {
               throw new IllegalStateException("Prepared transition cannot be automatic");
            }

            StateManager.TransitionRule<S, E> prepareRule = new StateManager.TransitionRule<>(
               this.fromState, this.toState.getPreparingPhase(), (s, e) -> this.guard.test(s, (EE)e), (c, f, t) -> {
                  if (this.prepareCallback != null) {
                     this.prepareCallback.execute((EE)c, f, t);
                  }
               }, false
            );
            aspectRules.add(prepareRule);
            effectiveFromState = this.toState.getPreparingPhase();
            effectiveGuard = (s, e) -> this.prepareGuard == null || this.prepareGuard.test((EE)e);
            isAutoAfterPrepare = true;
         }

         StateManager.TransitionRule<S, E> directRule = new StateManager.TransitionRule<>(
            effectiveFromState, this.toState, effectiveGuard, (c, f, t) -> this.action.execute((EE)c, f, t), isAutoAfterPrepare || automatic
         );
         aspectRules.add(directRule);
         return StateManager.this;
      }
   }

   @FunctionalInterface
   public interface StateComparator<S extends ManagedState<S>> {
      boolean compare(S var1, S var2);
   }

   @FunctionalInterface
   public interface TransitionAction<S extends ManagedState<S>, EE> {
      void execute(EE var1, S var2, S var3);
   }

   private static class TransitionRule<S extends ManagedState<S>, E extends ItemInstance<S>> {
      final S fromState;
      final S toState;
      final BiPredicate<S, E> guard;
      final StateManager.TransitionAction<S, E> action;
      final boolean automatic;

      TransitionRule(S fromState, S toState, BiPredicate<S, E> guard, StateManager.TransitionAction<S, E> action, boolean automatic) {
         if (fromState == null) {
            throw new IllegalArgumentException("From-state cannot be null");
         } else if (toState == null) {
            throw new IllegalArgumentException("To-state cannot be null");
         } else {
            this.fromState = fromState;
            this.toState = toState;
            this.guard = guard;
            this.action = action;
            this.automatic = automatic;
         }
      }

      @SafeVarargs
      final boolean matches(StateManager.StateComparator<S> comparator, E context, S fromState, S... targetStates) {
         boolean fromMatches = fromState == null || comparator.compare(this.fromState, fromState);
         boolean toMatches = this.automatic && targetStates.length == 0
            || Arrays.stream(targetStates)
               .anyMatch(target -> comparator.compare(this.toState, (S)target) || comparator.compare(this.toState, target.getPreparingPhase()));
         boolean guardPasses = this.guard.test(this.toState, context);
         return fromMatches && toMatches && guardPasses;
      }
   }
}
