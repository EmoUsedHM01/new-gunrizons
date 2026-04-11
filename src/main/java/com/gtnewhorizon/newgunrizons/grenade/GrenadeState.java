package com.gtnewhorizon.newgunrizons.grenade;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import io.netty.buffer.ByteBuf;

public enum GrenadeState implements ManagedState<GrenadeState> {
   READY(false),
   SAFETY_PIN_OFF(9),
   STRIKER_LEVER_RELEASED(9),
   THROWING(9),
   THROWN(9),
   EXPLODED_IN_HANDS(9);

   private final GrenadeState preparingPhase;
   private final GrenadeState commitPhase;
   private final boolean isTransient;
   private final int priority;

   private GrenadeState() {
      this(0, null, null, true);
   }

   private GrenadeState(int priority) {
      this(priority, null, null, true);
   }

   private GrenadeState(boolean isTransient) {
      this(0, null, null, isTransient);
   }

   private GrenadeState(int priority, GrenadeState preparingPhase, GrenadeState commitPhase, boolean isTransient) {
      this.priority = priority;
      this.preparingPhase = preparingPhase;
      this.commitPhase = commitPhase;
      this.isTransient = false;
   }

   public GrenadeState getPreparingPhase() {
      return this.preparingPhase;
   }

   public GrenadeState getCommitPhase() {
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
      TypeRegistry.getInstance().register(GrenadeState.class);
   }
}
