package com.gtnewhorizon.newgunrizons.grenade;

public class GrenadeStateTimed {
   private final GrenadeState state;
   private final long timestamp;
   private final long duration;

   public GrenadeStateTimed(GrenadeState state, long timestamp) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = 2147483647L;
   }

   public GrenadeStateTimed(GrenadeState state, long timestamp, long duration) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = duration;
   }

   public GrenadeState getState() {
      return this.state;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public long getDuration() {
      return this.duration;
   }
}
