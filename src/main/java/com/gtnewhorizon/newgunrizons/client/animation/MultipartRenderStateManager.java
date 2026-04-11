package com.gtnewhorizon.newgunrizons.client.animation;

import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.WeakHashMap;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class MultipartRenderStateManager {
   private final IdleSway idleSway;
   private final WeakHashMap<Part, Matrix4f> lastApplied = new WeakHashMap<>();
   private RenderableState currentState;
   private final MultipartTransitionProvider transitionProvider;
   private final Deque<MultipartPositioning> positioningQueue;

   public MultipartRenderStateManager(RenderableState initialState, MultipartTransitionProvider transitionProvider) {
      this.transitionProvider = transitionProvider;
      this.positioningQueue = new LinkedList<>();
      this.idleSway = new IdleSway();
      this.setState(initialState, false, true);
   }

   public void setState(RenderableState newState, boolean animated, boolean immediate) {
      if (newState == null) {
         throw new IllegalArgumentException("State cannot be null");
      } else if (!newState.equals(this.currentState)) {
         if (immediate) {
            this.positioningQueue.clear();
         }

         if (animated) {
            this.positioningQueue.add(new MultipartRenderStateManager.TransitionedPositioning(this.currentState, newState));
         }

         this.positioningQueue.add(new MultipartRenderStateManager.StaticPositioning(newState));
         this.currentState = newState;
      }
   }

   public MultipartPositioning nextPositioning() {
      while (!this.positioningQueue.isEmpty()) {
         MultipartPositioning p = this.positioningQueue.poll();
         if (!p.isExpired(this.positioningQueue)) {
            this.positioningQueue.addFirst(p);
            return p;
         }
      }

      throw new IllegalStateException("Position cannot be null");
   }

   private static Matrix4f captureMatrixForPositioning(MultipartTransition transition, Part part, RenderContext context) {
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      FloatBuffer buf = BufferUtils.createFloatBuffer(16);
      transition.position(part, context);
      GL11.glGetFloat(2982, buf);
      ((Buffer)buf).rewind();
      Matrix4f matrix = new Matrix4f();
      matrix.load(buf);
      GL11.glPopMatrix();
      return matrix;
   }

   private class StaticPositioning implements MultipartPositioning {
      private final RenderableState state;

      StaticPositioning(RenderableState state) {
         this.state = state;
      }

      @Override
      public float getProgress() {
         return 1.0F;
      }

      @Override
      public boolean isExpired(Queue<MultipartPositioning> queue) {
         return !queue.isEmpty();
      }

      @Override
      public MultipartPositioning.Positioner getPositioner() {
         final List<MultipartTransition> transitions = MultipartRenderStateManager.this.transitionProvider.getPositioning(this.state);
         return new MultipartPositioning.Positioner() {
            @Override
            public void position(Part part, RenderContext context) {
               try {
                  MultipartTransition multipartTransition = transitions.get(transitions.size() - 1);
                  Part attachedTo = multipartTransition.getAttachedTo(part);
                  if (attachedTo != null) {
                     MatrixHelper.loadMatrix(context.getPartPosition(attachedTo));
                  }

                  if (MultipartTransition.isAnchored(multipartTransition.getPositioning(part))) {
                     Matrix4f m = MultipartRenderStateManager.this.lastApplied.get(part);
                     MatrixHelper.applyMatrix(m);
                  } else {
                     multipartTransition.position(part, context);
                  }
               } catch (Exception var6) {
                  System.err.println("Failed to find static position for " + part + " in " + StaticPositioning.this.state);
                  throw var6;
               }
            }

            @Override
            public void applySway(float rate, float amplitude) {
               MultipartRenderStateManager.this.idleSway.apply(rate, amplitude);
            }
         };
      }

      @Override
      public <T> T getFromState(Class<T> stateClass) {
         return stateClass.cast(this.state);
      }

      @Override
      public <T> T getToState(Class<T> stateClass) {
         return stateClass.cast(this.state);
      }
   }

   private class TransitionedPositioning implements MultipartPositioning {
      private final Map<Part, MultipartRenderStateManager.TransitionedPositioning.PartData> partDataMap = new HashMap<>();
      private Long startTime;
      private long totalDuration;
      private int currentIndex;
      private long currentStartTime;
      private boolean expired;
      private final int segmentCount;
      private final List<MultipartTransition> fromPositioning;
      private final List<MultipartTransition> toPositioning;
      private final RenderableState fromState;
      private final RenderableState toState;

      TransitionedPositioning(RenderableState fromState, RenderableState toState) {
         this.fromState = fromState;
         this.toState = toState;
         this.fromPositioning = MultipartRenderStateManager.this.transitionProvider.getPositioning(fromState);
         this.toPositioning = MultipartRenderStateManager.this.transitionProvider.getPositioning(toState);
         this.segmentCount = this.toPositioning.size();

         for (MultipartTransition t : this.toPositioning) {
            this.totalDuration = this.totalDuration + t.getDuration() + t.getPause();
         }
      }

      @Override
      public float getProgress() {
         return this.startTime == null ? 0.0F : (float)(System.currentTimeMillis() - this.startTime) / (float)this.totalDuration;
      }

      @Override
      public boolean isExpired(Queue<MultipartPositioning> queue) {
         return this.expired;
      }

      @Override
      public <T> T getFromState(Class<T> stateClass) {
         return stateClass.cast(this.fromState);
      }

      @Override
      public <T> T getToState(Class<T> stateClass) {
         return stateClass.cast(this.toState);
      }

      private Matrix4f adjustToAttached(Matrix4f matrix, Part fromAttached, Part toAttached, RenderContext context) {
         if (fromAttached == toAttached) {
            return matrix;
         } else {
            Matrix4f fromMatrix = context.getPartPosition(fromAttached);
            if (fromMatrix == null) {
               return matrix;
            } else {
               Matrix4f toMatrix = context.getPartPosition(toAttached);
               if (toMatrix == null) {
                  return matrix;
               } else {
                  Matrix4f invertedToMatrix = Matrix4f.invert(toMatrix, null);
                  if (invertedToMatrix == null) {
                     return matrix;
                  } else {
                     Matrix4f correctionMatrix = Matrix4f.mul(invertedToMatrix, fromMatrix, null);
                     return Matrix4f.mul(correctionMatrix, matrix, null);
                  }
               }
            }
         }
      }

      private MultipartRenderStateManager.TransitionedPositioning.PartData getPartData(Part part, RenderContext context) {
         try {
            return this.partDataMap.computeIfAbsent(part, p -> {
               MultipartRenderStateManager.TransitionedPositioning.PartData pd = new MultipartRenderStateManager.TransitionedPositioning.PartData();
               MultipartTransition fromMultipart = this.fromPositioning.get(this.fromPositioning.size() - 1);
               Matrix4f fromMatrix;
               if (MultipartTransition.isAnchored(fromMultipart.getPositioning(part))) {
                  fromMatrix = MultipartRenderStateManager.this.lastApplied.get(p);
                  if (fromMatrix == null) {
                     fromMatrix = new Matrix4f();
                     fromMatrix.setIdentity();
                  }
               } else {
                  fromMatrix = MultipartRenderStateManager.captureMatrixForPositioning(fromMultipart, p, context);
               }

               fromMatrix = this.adjustToAttached(fromMatrix, fromMultipart.getAttachedTo(p), this.toPositioning.get(0).getAttachedTo(p), context);
               pd.matrices.add(fromMatrix);
               pd.attachedTo = this.toPositioning.get(0).getAttachedTo(p);
               Matrix4f previous = fromMatrix;

               for (MultipartTransition t : this.toPositioning) {
                  Matrix4f current;
                  if (MultipartTransition.isAnchored(t.getPositioning(part))) {
                     current = previous;
                  } else {
                     current = MultipartRenderStateManager.captureMatrixForPositioning(t, p, context);
                  }

                  pd.matrices.add(current);
                  previous = current;
               }

               return pd;
            });
         } catch (Exception var4) {
            System.err.println("Failed to get data for part " + part + " for transition from [" + this.fromState + "] to [" + this.toState + "]");
            throw var4;
         }
      }

      @Override
      public MultipartPositioning.Positioner getPositioner() {
         long currentTime = System.currentTimeMillis();
         MultipartTransition targetState = this.toPositioning.get(this.currentIndex);
         long currentDuration = targetState.getDuration();
         long currentPause = targetState.getPause();
         if (this.currentIndex == 0 && this.startTime == null) {
            this.startTime = currentTime;
         }

         if (this.currentStartTime == 0L) {
            this.currentStartTime = currentTime;
         } else if (currentTime > this.currentStartTime + currentDuration + currentPause) {
            this.currentIndex++;
            this.currentStartTime = currentTime;
         }

         long currentOffset = currentTime - this.currentStartTime;
         final float finalProgress = Math.min((float)currentOffset / (float)currentDuration, 1.0F);
         if (this.currentIndex >= this.segmentCount) {
            this.expired = true;
            return new MultipartPositioning.Positioner() {
               @Override
               public void position(Part part, RenderContext context) {
                  MultipartRenderStateManager.TransitionedPositioning.PartData partData = TransitionedPositioning.this.getPartData(part, context);
                  TransitionedPositioning.this.applyInterpolated(
                     part,
                     context,
                     partData.matrices.get(TransitionedPositioning.this.currentIndex - 1),
                     partData.matrices.get(TransitionedPositioning.this.currentIndex),
                     partData.attachedTo,
                     1.0F
                  );
               }

               @Override
               public void applySway(float rate, float amplitude) {
                  MultipartRenderStateManager.this.idleSway.apply(0.0F, 0.0F);
               }
            };
         } else {
            return new MultipartPositioning.Positioner() {
               @Override
               public void position(Part part, RenderContext context) {
                  MultipartRenderStateManager.TransitionedPositioning.PartData partData = TransitionedPositioning.this.getPartData(part, context);
                  TransitionedPositioning.this.applyInterpolated(
                     part,
                     context,
                     partData.matrices.get(TransitionedPositioning.this.currentIndex),
                     partData.matrices.get(TransitionedPositioning.this.currentIndex + 1),
                     partData.attachedTo,
                     finalProgress
                  );
               }

               @Override
               public void applySway(float rate, float amplitude) {
                  MultipartRenderStateManager.this.idleSway.apply(0.0F, 0.0F);
               }
            };
         }
      }

      private void applyInterpolated(Part part, RenderContext context, Matrix4f beforeMatrix, Matrix4f afterMatrix, Part attachedTo, float progress) {
         Matrix4f currentMatrix = null;
         if (attachedTo != null) {
            currentMatrix = context.getPartPosition(attachedTo);
         }

         if (currentMatrix == null) {
            currentMatrix = MatrixHelper.captureMatrix();
         }

         Matrix4f m1 = MatrixHelper.interpolateMatrix(beforeMatrix, 1.0F - progress);
         Matrix4f m2 = MatrixHelper.interpolateMatrix(afterMatrix, progress);
         Matrix4f deltaMatrix = Matrix4f.add(m1, m2, null);
         MultipartRenderStateManager.this.lastApplied.put(part, deltaMatrix);
         Matrix4f composite = Matrix4f.mul(currentMatrix, deltaMatrix, null);
         MatrixHelper.loadMatrix(composite);
      }

      private static class PartData {
         final List<Matrix4f> matrices = new ArrayList<>();
         Part attachedTo;

         private PartData() {
         }
      }
   }
}
