package com.gtnewhorizon.newgunrizons.weapon;

public class WeaponStateTimed {
   private final WeaponState state;
   private final long timestamp;
   private final long duration;
   private boolean isInfinite;

   public WeaponStateTimed(WeaponState state, long timestamp) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = 2147483647L;
      this.isInfinite = true;
   }

   public WeaponStateTimed(WeaponState state, long timestamp, long duration) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = duration;
   }

   public WeaponState getState() {
      return this.state;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public long getDuration() {
      return this.duration;
   }

   public boolean isInfinite() {
      return this.isInfinite;
   }
}
