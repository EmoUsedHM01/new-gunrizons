package com.gtnewhorizon.newgunrizons.util;

public final class Pair<U, V> {
   private final U u;
   private final V v;

   public Pair(U u, V v) {
      this.u = u;
      this.v = v;
   }

   public U getU() {
      return this.u;
   }

   public V getV() {
      return this.v;
   }
}
