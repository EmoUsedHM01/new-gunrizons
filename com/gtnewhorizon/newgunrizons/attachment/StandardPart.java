package com.gtnewhorizon.newgunrizons.attachment;

public final class StandardPart implements Part {
   private final String name;

   StandardPart(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
