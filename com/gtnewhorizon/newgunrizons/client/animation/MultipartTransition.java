package com.gtnewhorizon.newgunrizons.client.animation;

import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MultipartTransition {
   private static final Consumer<RenderContext> ANCHORED_POSITION = c -> {};
   private final Map<Part, Consumer<RenderContext>> multipartPositionFunctions = new HashMap<>();
   private final Map<Part, Part> attachedTo = new HashMap<>();
   private final long duration;
   private final long pause;

   public static Consumer<RenderContext> anchoredPosition() {
      return ANCHORED_POSITION;
   }

   public static boolean isAnchored(Consumer<?> consumer) {
      return consumer == ANCHORED_POSITION;
   }

   public MultipartTransition(Part part, Consumer<RenderContext> positionFunction, long duration, long pause) {
      this.duration = duration;
      this.pause = pause;
      this.multipartPositionFunctions.put(part, positionFunction);
   }

   public MultipartTransition(Part part, Consumer<RenderContext> positionFunction, long duration) {
      this(part, positionFunction, duration, 0L);
   }

   public MultipartTransition(long duration, long pause) {
      this.duration = duration;
      this.pause = pause;
   }

   public MultipartTransition(long duration) {
      this(duration, 0L);
   }

   public MultipartTransition withPartPositionFunction(Part part, Consumer<RenderContext> positionFunction) {
      return this.withPartPositionFunction(part, null, positionFunction);
   }

   public MultipartTransition withPartPositionFunction(Part part, Part attachedTo, Consumer<RenderContext> positionFunction) {
      this.multipartPositionFunctions.put(part, positionFunction);
      this.attachedTo.put(part, attachedTo);
      return this;
   }

   public void position(Part part, RenderContext context) {
      Consumer<RenderContext> positionFunction = this.multipartPositionFunctions.get(part);
      if (positionFunction != null) {
         positionFunction.accept(context);
      }
   }

   public Consumer<RenderContext> getPositioning(Part part) {
      return this.multipartPositionFunctions.get(part);
   }

   public Part getAttachedTo(Part part) {
      return this.attachedTo.get(part);
   }

   public long getDuration() {
      return this.duration;
   }

   public long getPause() {
      return this.pause;
   }
}
