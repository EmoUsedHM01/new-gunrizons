package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

public enum MagazineState implements ManagedState<MagazineState> {
   READY(false),
   LOAD(true);

   private final MagazineState preparingPhase;
   private final MagazineState commitPhase;
   private final boolean isTransient;
   private final int priority;

   private MagazineState() {
      this(null, null, true);
   }

   private MagazineState(boolean isTransient) {
      this(null, null, isTransient);
   }

   private MagazineState(MagazineState preparingPhase, MagazineState commitPhase, boolean isTransient) {
      this(0, preparingPhase, commitPhase, isTransient);
   }

   private MagazineState(int priority, MagazineState preparingPhase, MagazineState commitPhase, boolean isTransient) {
      this.priority = priority;
      this.preparingPhase = preparingPhase;
      this.commitPhase = commitPhase;
      this.isTransient = isTransient;
   }

   public MagazineState getPreparingPhase() {
      return this.preparingPhase;
   }

   public MagazineState getCommitPhase() {
      return this.commitPhase;
   }

   public boolean isTransient() {
      return this.isTransient;
   }

   public int getPriority() {
      return this.priority;
   }

   static {
      TypeRegistry.getInstance().register(MagazineState.class);
   }
}
