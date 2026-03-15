package com.gtnewhorizon.newgunrizons.client.animation;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.EntityLivingBase;

/**
 * Applies camera recoil (pitch/yaw kick) gradually over a configurable duration
 * instead of instantly in a single tick.
 */
public final class CameraRecoilController {

    public static final CameraRecoilController INSTANCE = new CameraRecoilController();

    private static final int DEFAULT_DURATION_MS = 50;

    private float pendingPitch;
    private float pendingYaw;
    private float totalPitch;
    private float totalYaw;
    private float appliedPitch;
    private float appliedYaw;
    private long startTime;
    @Getter
    @Setter
    private int durationMs = DEFAULT_DURATION_MS;

    private CameraRecoilController() {}

    /**
     * Adds recoil to be applied gradually. If called while previous recoil is still
     * being applied, the remaining amount is applied instantly and the new recoil starts fresh.
     */
    public void addRecoil(float pitchRecoil, float yawRecoil) {
        // Apply debug override if active
        int debugOverride = com.gtnewhorizon.newgunrizons.client.debug.PositionDebugger
            .getCameraRecoilDurationOverride();
        if (com.gtnewhorizon.newgunrizons.client.debug.PositionDebugger.isActive() && debugOverride >= 0) {
            durationMs = debugOverride;
        }
        // Flush any remaining recoil from previous shot
        pendingPitch += pitchRecoil;
        pendingYaw += yawRecoil;
        totalPitch = pendingPitch;
        totalYaw = pendingYaw;
        appliedPitch = 0f;
        appliedYaw = 0f;
        startTime = System.currentTimeMillis();
    }

    /**
     * Called every render tick to apply a portion of the pending recoil.
     * Returns true if recoil was applied this tick.
     */
    public boolean update(EntityLivingBase player) {
        if (player == null || (pendingPitch == 0f && pendingYaw == 0f)) {
            return false;
        }

        long elapsed = System.currentTimeMillis() - startTime;
        float progress = durationMs > 0 ? Math.min((float) elapsed / durationMs, 1.0f) : 1.0f;

        float targetPitch = totalPitch * progress;
        float targetYaw = totalYaw * progress;

        float deltaPitch = targetPitch - appliedPitch;
        float deltaYaw = targetYaw - appliedYaw;

        player.rotationPitch += deltaPitch;
        player.rotationYaw += deltaYaw;

        appliedPitch = targetPitch;
        appliedYaw = targetYaw;

        if (progress >= 1.0f) {
            pendingPitch = 0f;
            pendingYaw = 0f;
            totalPitch = 0f;
            totalYaw = 0f;
            appliedPitch = 0f;
            appliedYaw = 0f;
        }

        return true;
    }

}
