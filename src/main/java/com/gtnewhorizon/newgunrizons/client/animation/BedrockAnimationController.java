package com.gtnewhorizon.newgunrizons.client.animation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gtnewhorizon.newgunrizons.model.BedrockModel;
import lombok.Getter;
import net.minecraft.client.model.ModelRenderer;

import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.AnimationClip;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.BoneAnimation;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

/**
 * Maps {@link RenderableState}s to Bedrock animation clips and manages
 * playback lifecycle with smooth blending between states.
 */
public class BedrockAnimationController {

    public static final String BONE_LEFT_HAND = "left_hand";
    public static final String BONE_RIGHT_HAND = "right_hand";

    private static final long BLEND_DURATION_MS = 250L;
    private static final float DEG_TO_RAD = (float) (Math.PI / 180.0);

    /**
     * -- GETTER --
     *  Returns the underlying animation data.
     */
    @Getter
    private final BedrockAnimation animation;
    private final Map<RenderableState, String> stateClipMap = new HashMap<>();
    @Getter
    private BedrockAnimationPlayer currentPlayer;
    private RenderableState currentAnimState;

    // Blend state: lerp from captured "from" values to current animation target.
    // Values are stored in MC model-space (radians for rotation, model units for position).
    private final Map<String, float[]> blendFromRotations = new HashMap<>();
    private final Map<String, float[]> blendFromPositions = new HashMap<>();
    private final Map<String, float[]> lastAppliedRotations = new HashMap<>();
    private final Map<String, float[]> lastAppliedPositions = new HashMap<>();
    private long blendStartTime;
    private boolean blending;

    // Tracks the last fire timestamp to distinguish genuine new shots from state flicker
    private long lastFireTimestamp;

    // Debug offset: additive position/rotation applied to a named bone
    private String debugBoneName;
    @Getter
    private float[] debugPosOffset;
    @Getter
    private float[] debugRotOffset;

    public BedrockAnimationController(BedrockAnimation animation) {
        this.animation = animation;
    }

    /**
     * Directly plays a named clip, bypassing the state machine.
     * Used by the debug system to preview specific clips.
     */
    public void playClipDirect(String clipName) {
        AnimationClip clip = animation.getClip(clipName);
        if (clip == null) return;
        captureBlendFrom();
        currentPlayer = new BedrockAnimationPlayer(clip);
        currentPlayer.start();
    }

    /**
     * Sets a debug offset (position and rotation) to be applied to a named bone
     * after animation evaluation. Offsets are additive on top of whatever the animation produced.
     *
     * @param boneName  target bone name
     * @param posOffset position offset [x, y, z] in Bedrock coordinates, or null
     * @param rotOffset rotation offset [rx, ry, rz] in degrees, or null
     */
    public void setDebugOffset(String boneName, float[] posOffset, float[] rotOffset) {
        this.debugBoneName = boneName;
        this.debugPosOffset = posOffset;
        this.debugRotOffset = rotOffset;
    }

    /**
     * Clears any debug offset.
     */
    public void clearDebugOffset() {
        this.debugBoneName = null;
        this.debugPosOffset = null;
        this.debugRotOffset = null;
    }

    /**
     * Maps a renderable state to a named animation clip.
     */
    public void mapState(RenderableState state, String clipName) {
        stateClipMap.put(state, clipName);
    }

    /**
     * Called each frame with the current renderable state and fire timestamp.
     * For fire-cycle states (SHOOTING/ZOOMING_SHOOTING), the fire timestamp is
     * used to detect genuine new shots vs. stale weapon state history flicker.
     *
     * @param newState      current renderable state
     * @param fireTimestamp weapon's last fire timestamp (0 if not in a fire state)
     */
    public void onStateChanged(RenderableState newState, long fireTimestamp) {
        boolean isFireState = newState == RenderableState.SHOOTING
            || newState == RenderableState.ZOOMING_SHOOTING;

        if (isFireState) {
            if (fireTimestamp == lastFireTimestamp) {
                // Same fire cycle — don't restart animation (just update state enum
                // in case of SHOOTING <-> ZOOMING_SHOOTING switch while aimed)
                currentAnimState = newState;
                return;
            }
            // Genuine new shot — restart animation even if state name is the same
            lastFireTimestamp = fireTimestamp;
            captureBlendFrom();
            currentAnimState = newState;
            startAnimation(newState);
            return;
        }

        if (newState == currentAnimState) return;

        captureBlendFrom();
        currentAnimState = newState;
        startAnimation(newState);
    }

    /** Overload for non-fire states (backward compat). */
    public void onStateChanged(RenderableState newState) {
        onStateChanged(newState, 0);
    }

    private void captureBlendFrom() {
        blendFromRotations.clear();
        for (Map.Entry<String, float[]> e : lastAppliedRotations.entrySet()) {
            blendFromRotations.put(e.getKey(), e.getValue().clone());
        }
        blendFromPositions.clear();
        for (Map.Entry<String, float[]> e : lastAppliedPositions.entrySet()) {
            blendFromPositions.put(e.getKey(), e.getValue().clone());
        }
        blending = !lastAppliedRotations.isEmpty() || !lastAppliedPositions.isEmpty();
        blendStartTime = System.currentTimeMillis();
    }

    private void startAnimation(RenderableState state) {
        String clipName = stateClipMap.get(state);
        if (clipName != null) {
            AnimationClip clip = animation.getClip(clipName);
            if (clip != null) {
                currentPlayer = new BedrockAnimationPlayer(clip);
                currentPlayer.start();
                return;
            }
        }
        currentPlayer = null;
    }

    /**
     * Applies the current animation's bone transforms to the model,
     * blending from the previous pose if a state transition just occurred.
     */
    public void applyToModel(BedrockModel model) {
        float blend = 1.0f;
        if (blending) {
            blend = Math.min((System.currentTimeMillis() - blendStartTime) / (float) BLEND_DURATION_MS, 1.0f);
            if (blend >= 1.0f) {
                blending = false;
            }
        }

        if (currentPlayer != null && (currentPlayer.isPlaying() || currentPlayer.isFinished())) {
            // Apply to model bones (including hand bones which are now real model bones)
            currentPlayer.applyToModel(model);

            if (blend < 1.0f) {
                blendRealBones(model, blend);
            }

            applyDebugOffsetToBone(model);
            storeAppliedValues(model);
        } else if (blend < 1.0f) {
            // No current animation but still blending toward rest/zero
            blendTowardRest(model, blend);
            applyDebugOffsetToBone(model);
            storeAppliedValues(model);
        } else {
            lastAppliedRotations.clear();
            lastAppliedPositions.clear();
        }
    }

    private void applyDebugOffsetToBone(BedrockModel model) {
        if (debugBoneName == null || (debugPosOffset == null && debugRotOffset == null)) return;
        ModelRenderer bone = model.getBone(debugBoneName);
        if (bone == null) return;
        if (debugRotOffset != null) {
            bone.rotateAngleX += debugRotOffset[0] * DEG_TO_RAD;
            bone.rotateAngleY += debugRotOffset[1] * DEG_TO_RAD;
            bone.rotateAngleZ += debugRotOffset[2] * DEG_TO_RAD;
        }
        if (debugPosOffset != null) {
            bone.rotationPointX += (debugPosOffset[0]);
            bone.rotationPointY += (-debugPosOffset[1]);
            bone.rotationPointZ += debugPosOffset[2];
        }
    }

    private void blendRealBones(BedrockModel model, float blend) {
        Set<String> boneNames = new HashSet<>(blendFromRotations.keySet());
        boneNames.addAll(blendFromPositions.keySet());
        for (Map.Entry<String, BoneAnimation> e : currentPlayer.getClip().bones.entrySet()) {
            boneNames.add(e.getKey());
        }

        for (String boneName : boneNames) {
            ModelRenderer renderer = model.getBone(boneName);
            if (renderer == null) continue;

            float[] restRot = model.getRestRotation(boneName);
            float[] restPos = model.getRestPosition(boneName);

            float[] fromRot = blendFromRotations.get(boneName);
            if (fromRot == null) fromRot = restRot;
            float[] fromPos = blendFromPositions.get(boneName);
            if (fromPos == null) fromPos = restPos;

            renderer.rotateAngleX = lerp(fromRot[0], renderer.rotateAngleX, blend);
            renderer.rotateAngleY = lerp(fromRot[1], renderer.rotateAngleY, blend);
            renderer.rotateAngleZ = lerp(fromRot[2], renderer.rotateAngleZ, blend);
            renderer.rotationPointX = lerp(fromPos[0], renderer.rotationPointX, blend);
            renderer.rotationPointY = lerp(fromPos[1], renderer.rotationPointY, blend);
            renderer.rotationPointZ = lerp(fromPos[2], renderer.rotationPointZ, blend);
        }
    }

    private void blendTowardRest(BedrockModel model, float blend) {
        for (Map.Entry<String, float[]> entry : blendFromRotations.entrySet()) {
            String boneName = entry.getKey();

            ModelRenderer renderer = model.getBone(boneName);
            if (renderer == null) continue;

            float[] restRot = model.getRestRotation(boneName);
            float[] restPos = model.getRestPosition(boneName);
            float[] fromRot = entry.getValue();
            float[] fromPos = blendFromPositions.getOrDefault(boneName, restPos);

            renderer.rotateAngleX = lerp(fromRot[0], restRot[0], blend);
            renderer.rotateAngleY = lerp(fromRot[1], restRot[1], blend);
            renderer.rotateAngleZ = lerp(fromRot[2], restRot[2], blend);
            renderer.rotationPointX = lerp(fromPos[0], restPos[0], blend);
            renderer.rotationPointY = lerp(fromPos[1], restPos[1], blend);
            renderer.rotationPointZ = lerp(fromPos[2], restPos[2], blend);
        }
    }

    private void storeAppliedValues(BedrockModel model) {
        Set<String> boneNames = new HashSet<>();
        if (currentPlayer != null) {
            boneNames.addAll(currentPlayer.getClip().bones.keySet());
        }
        boneNames.addAll(blendFromRotations.keySet());

        for (String boneName : boneNames) {
            ModelRenderer renderer = model.getBone(boneName);
            if (renderer == null) continue;
            lastAppliedRotations.put(boneName,
                new float[] { renderer.rotateAngleX, renderer.rotateAngleY, renderer.rotateAngleZ });
            lastAppliedPositions.put(boneName,
                new float[] { renderer.rotationPointX, renderer.rotationPointY, renderer.rotationPointZ });
        }
    }

    /**
     * Resets all bones to rest pose after rendering.
     */
    public void resetModel(BedrockModel model) {
        if (currentPlayer != null) {
            model.resetBonesToRestPose();
        }
    }

    public long getAnimationDurationMs(RenderableState state) {
        String clipName = stateClipMap.get(state);
        if (clipName == null) return -1;
        AnimationClip clip = animation.getClip(clipName);
        if (clip == null) return -1;
        return (long) (clip.length * 1000f);
    }

    public boolean hasAnimation(RenderableState state) {
        return stateClipMap.containsKey(state);
    }

    /**
     * If a fire-cycle animation is still playing, returns its renderable state
     * so the renderer can hold that state until the animation finishes.
     */
    public RenderableState getActiveFireCycleState() {
        if (currentPlayer != null && currentPlayer.isPlaying() && currentAnimState != null
            && (currentAnimState == RenderableState.SHOOTING
                || currentAnimState == RenderableState.ZOOMING_SHOOTING)) {
            return currentAnimState;
        }
        return null;
    }

    private static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }
}
