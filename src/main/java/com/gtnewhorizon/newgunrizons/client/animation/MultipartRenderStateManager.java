package com.gtnewhorizon.newgunrizons.client.animation;

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

import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

/**
 * Manages the animation state machine for a multipart weapon/grenade model.
 * <p>
 * Maintains a queue of {@link MultipartPositioning} entries — either static holds
 * or animated transitions between states. Each frame, {@link #nextPositioning()}
 * returns the current active positioning (expiring completed ones), and its
 * {@link MultipartPositioning.Positioner} applies per-part GL transforms by
 * interpolating between captured modelview matrices.
 */
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
        }
        if (newState.equals(this.currentState)) {
            return;
        }

        if (immediate) {
            this.positioningQueue.clear();
        }

        if (animated) {
            this.positioningQueue.add(new TransitionedPositioning(this.currentState, newState));
        }

        this.positioningQueue.add(new StaticPositioning(newState));
        this.currentState = newState;
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

    /**
     * Captures the current modelview matrix after executing a transition's
     * positioning function for a specific part. Used to build the interpolation
     * keyframe matrices.
     */
    private static Matrix4f captureMatrixForPositioning(MultipartTransition transition, Part part,
        RenderContext context) {
        GL11.glPushMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        FloatBuffer buf = BufferUtils.createFloatBuffer(16);
        transition.position(part, context);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, buf);
        buf.rewind();
        Matrix4f matrix = new Matrix4f();
        matrix.load(buf);
        GL11.glPopMatrix();
        return matrix;
    }

    // -------------------------------------------------------------------------
    // Animated transition between two states
    // -------------------------------------------------------------------------

    private class TransitionedPositioning implements MultipartPositioning {

        private final Map<Part, PartData> partDataMap = new HashMap<>();
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
            this.fromPositioning = transitionProvider.getPositioning(fromState);
            this.toPositioning = transitionProvider.getPositioning(toState);
            this.segmentCount = this.toPositioning.size();

            for (MultipartTransition t : this.toPositioning) {
                this.totalDuration += t.getDuration() + t.getPause();
            }
        }

        @Override
        public float getProgress() {
            if (this.startTime == null) {
                return 0.0F;
            }
            return (float) (System.currentTimeMillis() - this.startTime) / (float) this.totalDuration;
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

        /**
         * Adjusts a matrix when transitioning between different attachment parents.
         * Compensates for the coordinate space difference so the part doesn't jump.
         */
        private Matrix4f adjustToAttached(Matrix4f matrix, Part fromAttached, Part toAttached, RenderContext context) {
            if (fromAttached == toAttached) {
                return matrix;
            }
            Matrix4f fromMatrix = context.getPartPosition(fromAttached);
            if (fromMatrix == null) {
                return matrix;
            }
            Matrix4f toMatrix = context.getPartPosition(toAttached);
            if (toMatrix == null) {
                return matrix;
            }
            Matrix4f invertedToMatrix = Matrix4f.invert(toMatrix, null);
            if (invertedToMatrix == null) {
                return matrix;
            }
            Matrix4f correctionMatrix = Matrix4f.mul(invertedToMatrix, fromMatrix, null);
            return Matrix4f.mul(correctionMatrix, matrix, null);
        }

        /**
         * Lazily computes and caches the interpolation keyframe matrices for a part.
         * The first matrix comes from the "from" state's last keyframe, followed by
         * one matrix per "to" state keyframe.
         */
        private PartData getPartData(Part part, RenderContext context) {
            try {
                return this.partDataMap.computeIfAbsent(part, (p) -> {
                    PartData pd = new PartData();
                    MultipartTransition fromMultipart = this.fromPositioning.get(this.fromPositioning.size() - 1);

                    // Determine the starting matrix for this part
                    Matrix4f fromMatrix;
                    if (MultipartTransition.isAnchored(fromMultipart.getPositioning(part))) {
                        fromMatrix = lastApplied.get(p);
                        if (fromMatrix == null) {
                            fromMatrix = new Matrix4f();
                            fromMatrix.setIdentity();
                        }
                    } else {
                        fromMatrix = captureMatrixForPositioning(fromMultipart, p, context);
                    }

                    fromMatrix = this.adjustToAttached(
                        fromMatrix,
                        fromMultipart.getAttachedTo(p),
                        this.toPositioning.get(0)
                            .getAttachedTo(p),
                        context);
                    pd.matrices.add(fromMatrix);
                    pd.attachedTo = this.toPositioning.get(0)
                        .getAttachedTo(p);

                    // Build keyframe matrices for each segment of the target positioning
                    Matrix4f previous = fromMatrix;
                    for (MultipartTransition t : this.toPositioning) {
                        Matrix4f current;
                        if (MultipartTransition.isAnchored(t.getPositioning(part))) {
                            current = previous;
                        } else {
                            current = captureMatrixForPositioning(t, p, context);
                        }
                        pd.matrices.add(current);
                        previous = current;
                    }

                    return pd;
                });
            } catch (Exception e) {
                System.err.println(
                    "Failed to get data for part " + part
                        + " for transition from ["
                        + this.fromState
                        + "] to ["
                        + this.toState
                        + "]");
                throw e;
            }
        }

        @Override
        public Positioner getPositioner() {
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
            float finalProgress = Math.min((float) currentOffset / (float) currentDuration, 1.0F);

            if (this.currentIndex >= this.segmentCount) {
                this.expired = true;
                return new Positioner() {

                    @Override
                    public void position(Part part, RenderContext context) {
                        PartData partData = getPartData(part, context);
                        applyInterpolated(
                            part,
                            context,
                            partData.matrices.get(currentIndex - 1),
                            partData.matrices.get(currentIndex),
                            partData.attachedTo,
                            1.0F);
                    }

                    @Override
                    public void applySway(float rate, float amplitude) {
                        idleSway.apply(0.0F, 0.0F);
                    }
                };
            }

            return new Positioner() {

                @Override
                public void position(Part part, RenderContext context) {
                    PartData partData = getPartData(part, context);
                    applyInterpolated(
                        part,
                        context,
                        partData.matrices.get(currentIndex),
                        partData.matrices.get(currentIndex + 1),
                        partData.attachedTo,
                        finalProgress);
                }

                @Override
                public void applySway(float rate, float amplitude) {
                    idleSway.apply(0.0F, 0.0F);
                }
            };
        }

        /**
         * Linearly interpolates between two keyframe matrices and applies the result
         * to the GL modelview matrix, optionally relative to an attachment parent.
         */
        private void applyInterpolated(Part part, RenderContext context, Matrix4f beforeMatrix, Matrix4f afterMatrix,
            Part attachedTo, float progress) {
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
            lastApplied.put(part, deltaMatrix);
            Matrix4f composite = Matrix4f.mul(currentMatrix, deltaMatrix, null);
            MatrixHelper.loadMatrix(composite);
        }

        private static class PartData {

            final List<Matrix4f> matrices = new ArrayList<>();
            Part attachedTo;
        }
    }

    // -------------------------------------------------------------------------
    // Static hold at a single state (no animation)
    // -------------------------------------------------------------------------

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
        public Positioner getPositioner() {
            final List<MultipartTransition> transitions = transitionProvider.getPositioning(this.state);
            return new Positioner() {

                @Override
                public void position(Part part, RenderContext context) {
                    try {
                        MultipartTransition multipartTransition = transitions.get(transitions.size() - 1);
                        Part attachedTo = multipartTransition.getAttachedTo(part);
                        if (attachedTo != null) {
                            MatrixHelper.loadMatrix(context.getPartPosition(attachedTo));
                        }

                        if (MultipartTransition.isAnchored(multipartTransition.getPositioning(part))) {
                            Matrix4f m = lastApplied.get(part);
                            MatrixHelper.applyMatrix(m);
                        } else {
                            multipartTransition.position(part, context);
                        }
                    } catch (Exception e) {
                        System.err.println("Failed to find static position for " + part + " in " + state);
                        throw e;
                    }
                }

                @Override
                public void applySway(float rate, float amplitude) {
                    idleSway.apply(rate, amplitude);
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
}
