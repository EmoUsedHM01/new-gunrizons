package com.gtnewhorizon.newgunrizons.client.animation;

import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import java.util.function.Consumer;

public class Transition {
   private static final Consumer<RenderContext> ANCHORED_POSITION = c -> {};
   private final Consumer<RenderContext> itemPositioning;
   private final long duration;
   private final long pause;
   private final Part attachedTo;
   private boolean animated;

   public static boolean isAnchored(Consumer<?> consumer) {
      return consumer == ANCHORED_POSITION;
   }

   public Transition(Consumer<RenderContext> itemPositioning, boolean animated) {
      this(itemPositioning, 0L, 0L);
      this.animated = animated;
   }

   public Transition(Consumer<RenderContext> itemPositioning, long duration) {
      this(itemPositioning, duration, 0L);
   }

   public Transition(Consumer<RenderContext> itemPositioning, long duration, long pause) {
      this(itemPositioning, duration, pause, null);
   }

   public Transition(Consumer<RenderContext> itemPositioning, long duration, long pause, Part attachedTo) {
      this.itemPositioning = itemPositioning;
      this.duration = duration;
      this.pause = pause;
      this.attachedTo = attachedTo;
   }

   public Consumer<RenderContext> getItemPositioning() {
      return this.itemPositioning;
   }

   public long getDuration() {
      return this.duration;
   }

   public long getPause() {
      return this.pause;
   }

   public Part getAttachedTo() {
      return this.attachedTo;
   }

   public boolean isAnimated() {
      return this.animated;
   }
}
