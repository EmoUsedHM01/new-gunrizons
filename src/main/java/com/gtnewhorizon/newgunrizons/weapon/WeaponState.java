package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import io.netty.buffer.ByteBuf;

public enum WeaponState implements ManagedState<WeaponState> {
   READY(false),
   LOAD(true),
   LOAD_ITERATION,
   LOAD_ITERATION_COMPLETED,
   ALL_LOAD_ITERATIONS_COMPLETED,
   UNLOAD_PREPARING,
   UNLOAD(UNLOAD_PREPARING, READY, true),
   FIRING(9),
   RECOILED(10),
   PAUSED(10),
   EJECT_REQUIRED,
   EJECTING,
   MODIFYING(2, false),
   NEXT_ATTACHMENT(2, false),
   ALERT;

   private final WeaponState preparingPhase;
   private final WeaponState commitPhase;
   private final boolean isTransient;
   private final int priority;

   private WeaponState() {
      this(0, null, null, true);
   }

   private WeaponState(int priority) {
      this(priority, null, null, true);
   }

   private WeaponState(boolean isTransient) {
      this(0, null, null, isTransient);
   }

   private WeaponState(int priority, boolean isTransient) {
      this(priority, null, null, isTransient);
   }

   private WeaponState(WeaponState preparingPhase, WeaponState commitPhase, boolean isTransient) {
      this(0, preparingPhase, commitPhase, isTransient);
   }

   private WeaponState(int priority, WeaponState preparingPhase, WeaponState commitPhase, boolean isTransient) {
      this.priority = priority;
      this.preparingPhase = preparingPhase;
      this.commitPhase = commitPhase;
      this.isTransient = false;
   }

   public WeaponState getPreparingPhase() {
      return this.preparingPhase;
   }

   public WeaponState getCommitPhase() {
      return this.commitPhase;
   }

   @Override
   public void init(ByteBuf buf) {
   }

   @Override
   public void serialize(ByteBuf buf) {
   }

   public boolean isTransient() {
      return this.isTransient;
   }

   public int getPriority() {
      return this.priority;
   }

   static {
      TypeRegistry.getInstance().register(WeaponState.class);
   }
}
