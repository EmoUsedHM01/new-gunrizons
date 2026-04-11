package com.gtnewhorizon.newgunrizons.client.animation;

import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import java.util.Queue;

public interface MultipartPositioning {
   <T> T getFromState(Class<T> var1);

   <T> T getToState(Class<T> var1);

   boolean isExpired(Queue<MultipartPositioning> var1);

   MultipartPositioning.Positioner getPositioner();

   float getProgress();

   public interface Positioner {
      void position(Part var1, RenderContext var2);

      default void applySway(float rate, float amplitude, float verticalBias) {
      }
   }
}
