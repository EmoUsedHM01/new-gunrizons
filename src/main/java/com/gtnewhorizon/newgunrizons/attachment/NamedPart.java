package com.gtnewhorizon.newgunrizons.attachment;

public final class NamedPart implements Part {
   private final String name;

   public NamedPart(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return "Part [" + this.name + "]";
   }
}
