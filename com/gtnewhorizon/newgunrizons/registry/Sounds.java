package com.gtnewhorizon.newgunrizons.registry;

public final class Sounds {
   public static final String ZOOM = resolve("opticzoom");
   public static final String FIRE_MODE_SWITCH = resolve("gunfiremodeswitch");
   public static final String DRY_FIRE = resolve("dryfire");
   public static final String EXPLOSION = resolve("grenadeexplosion");

   private Sounds() {
   }

   public static String resolve(String sound) {
      return sound == null ? null : "newgunrizons:" + sound.toLowerCase();
   }
}
