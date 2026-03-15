package com.gtnewhorizon.newgunrizons.client.animation;

import java.util.List;
import java.util.Map;

import com.gtnewhorizon.newgunrizons.model.BedrockModel;
import net.minecraft.client.model.ModelRenderer;

import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.AnimationClip;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.BoneAnimation;
import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimation.Keyframe;

/**
 * Evaluates a single Bedrock animation clip at the current time and applies
 * bone transforms as additive deltas to a {@link BedrockModel}'s ModelRenderers.
 * <p>
 * Coordinate conversion: Bedrock animations use Y-up with rotation in degrees.
 * ModelRenderer uses Y-down with rotation in radians. Animation rotation values
 * use the opposite sign convention from geometry rotations (X and Y are not
 * negated), so all axes are simply converted deg-to-rad without negation.
 * Position Y is negated to convert from Y-up to Y-down: posY = -posY.
 */
public class BedrockAnimationPlayer {

    private static final float DEG_TO_RAD = (float) (Math.PI / 180.0);

    private final AnimationClip clip;
    private long startTime;
    private boolean playing;
    private boolean finished;

    public BedrockAnimationPlayer(AnimationClip clip) {
        this.clip = clip;
    }

    public AnimationClip getClip() {
        return clip;
    }

    /**
     * Returns the current elapsed time clamped to the clip length.
     */
    public float getElapsedClamped() {
        if (!playing && !finished) return 0f;
        if (finished) return clip.length;
        float elapsed = (System.currentTimeMillis() - startTime) / 1000f;
        if (elapsed >= clip.length) {
            return clip.loop ? elapsed % clip.length : clip.length;
        }
        return elapsed;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.playing = true;
        this.finished = false;
    }

    public void stop() {
        this.playing = false;
        this.finished = true;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isFinished() {
        return finished;
    }

    /**
     * Returns the animation length in milliseconds.
     */
    public long getDurationMs() {
        return (long) (clip.length * 1000f);
    }

    /**
     * Returns the current progress (0.0 to 1.0). May exceed 1.0 if not looping and past the end.
     */
    public float getProgress() {
        if (!playing) return finished ? 1f : 0f;
        float elapsed = (System.currentTimeMillis() - startTime) / 1000f;
        return clip.length > 0 ? elapsed / clip.length : 1f;
    }

    /**
     * Evaluates the animation at the current time and applies bone transforms
     * as additive deltas on top of the model's rest pose.
     * Must be called BEFORE model.render() each frame.
     */
    public void applyToModel(BedrockModel model) {
        if (!playing && !finished) return;

        float elapsed;
        if (finished) {
            // Hold the last frame (clamp-to-end)
            elapsed = clip.length;
        } else {
            elapsed = (System.currentTimeMillis() - startTime) / 1000f;
            if (elapsed >= clip.length) {
                if (clip.loop) {
                    elapsed = elapsed % clip.length;
                } else {
                    elapsed = clip.length;
                    playing = false;
                    finished = true;
                }
            }
        }

        for (Map.Entry<String, BoneAnimation> entry : clip.bones.entrySet()) {
            String boneName = entry.getKey();
            BoneAnimation boneAnim = entry.getValue();
            ModelRenderer renderer = model.getBone(boneName);
            if (renderer == null) continue;

            float[] restRot = model.getRestRotation(boneName);
            float[] restPos = model.getRestPosition(boneName);

            if (boneAnim.rotation != null && !boneAnim.rotation.isEmpty() && restRot != null) {
                float[] rot = evaluate(boneAnim.rotation, elapsed);
                // Animation rotation values use the opposite sign convention from
                // geometry rotations for X and Y — apply without negation.
                renderer.rotateAngleX = restRot[0] + (rot[0] * DEG_TO_RAD);
                renderer.rotateAngleY = restRot[1] + (rot[1] * DEG_TO_RAD);
                renderer.rotateAngleZ = restRot[2] + (rot[2] * DEG_TO_RAD);
            }

            if (boneAnim.position != null && !boneAnim.position.isEmpty() && restPos != null) {
                float[] pos = evaluate(boneAnim.position, elapsed);
                renderer.rotationPointX = restPos[0] + (pos[0]);
                renderer.rotationPointY = restPos[1] + (-pos[1]);
                renderer.rotationPointZ = restPos[2] + pos[2];
            }

            // Scale is not natively supported by MC 1.7.10 ModelRenderer — skipped
        }
    }

    /**
     * Evaluates a keyframe channel at the given time using linear interpolation.
     */
    static float[] evaluate(List<Keyframe> keyframes, float time) {
        if (keyframes.size() == 1) {
            return keyframes.get(0).value;
        }

        // Before the first keyframe
        Keyframe first = keyframes.get(0);
        if (time <= first.time) {
            return first.value;
        }

        // After the last keyframe
        Keyframe last = keyframes.get(keyframes.size() - 1);
        if (time >= last.time) {
            return last.value;
        }

        // Find bracketing keyframes
        for (int i = 0; i < keyframes.size() - 1; i++) {
            Keyframe kf1 = keyframes.get(i);
            Keyframe kf2 = keyframes.get(i + 1);
            if (time >= kf1.time && time <= kf2.time) {
                float duration = kf2.time - kf1.time;
                float progress = duration > 0 ? (time - kf1.time) / duration : 0f;
                return new float[] {
                    lerp(kf1.value[0], kf2.value[0], progress),
                    lerp(kf1.value[1], kf2.value[1], progress),
                    lerp(kf1.value[2], kf2.value[2], progress)
                };
            }
        }

        return last.value;
    }

    private static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }
}
