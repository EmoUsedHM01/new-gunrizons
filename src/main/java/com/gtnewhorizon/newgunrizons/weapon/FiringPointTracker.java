package com.gtnewhorizon.newgunrizons.weapon;

import java.lang.reflect.Method;

import lombok.Getter;
import net.minecraft.entity.EntityLivingBase;

/**
 * Tracks the firing point (muzzle) position of the currently held weapon.
 * <p>
 * The position is captured in eye-space during weapon rendering by
 * {@link com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer} and
 * consumed by {@link com.gtnewhorizon.newgunrizons.client.render.EntityBulletRenderer}
 * for tracer origin correction and by the smoke particle system for muzzle
 * smoke positioning.
 * <p>
 * Eye-space values are Iris/Angelica-safe — unaffected by coordinate shifting
 * that shader mods apply to the modelview matrix. World-space conversion is
 * performed on demand via {@link #getWorldPosition(EntityLivingBase)}.
 */
public class FiringPointTracker {

    @Getter
    private static float eyeX;
    @Getter
    private static float eyeY;
    @Getter
    private static float eyeZ;

    private static boolean captured;

    /** Cached reflection handle for Iris's shadow pass check. */
    private static final Method SHADOW_CHECK;
    static {
        Method m = null;
        try {
            Class<?> clazz = Class.forName("net.coderbot.iris.shadows.ShadowRenderingState");
            m = clazz.getMethod("areShadowsCurrentlyBeingRendered");
        } catch (Exception ignored) {}
        SHADOW_CHECK = m;
    }

    private static boolean isShadowPass() {
        if (SHADOW_CHECK == null) return false;
        try {
            return (Boolean) SHADOW_CHECK.invoke(null);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasFiringPoint() {
        return captured;
    }

    /**
     * Stores the eye-space position of the firing point bone.
     * Skips capture during Iris shadow passes (detected via
     * {@code ShadowRenderingState.areShadowsCurrentlyBeingRendered()})
     * where the modelview has a relocated coordinate origin.
     */
    public static void captureEyePosition(float ex, float ey, float ez) {
        if (isShadowPass()) return;

        eyeX = ex;
        eyeY = ey - 0.1f; // shift down slightly (positive Y = down in eye-space)
        eyeZ = ez;
        captured = true;
    }

    /**
     * Converts the stored eye-space firing point to world-space via inverse
     * camera rotation + player eye position.
     *
     * @return world-space {x, y, z}, or null if no firing point has been captured
     */
    public static double[] getWorldPosition(EntityLivingBase player) {
        if (!captured) return null;

        float yaw = player.rotationYaw;
        float pitch = player.rotationPitch;

        double yawRad = Math.toRadians(-(yaw + 180.0));
        double pitchRad = Math.toRadians(-pitch);
        double cosY = Math.cos(yawRad);
        double sinY = Math.sin(yawRad);
        double cosP = Math.cos(pitchRad);
        double sinP = Math.sin(pitchRad);

        double p1x = eyeX;
        double p1y = eyeY * cosP - eyeZ * sinP;
        double p1z = eyeY * sinP + eyeZ * cosP;

        double playerEyeX = player.posX;
        double playerEyeY = player.posY + player.getEyeHeight();
        double playerEyeZ = player.posZ;

        return new double[] {
            playerEyeX + p1x * cosY + p1z * sinY,
            playerEyeY + p1y,
            playerEyeZ - p1x * sinY + p1z * cosY
        };
    }
}
