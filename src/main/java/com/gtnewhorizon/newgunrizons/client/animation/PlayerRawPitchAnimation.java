package com.gtnewhorizon.newgunrizons.client.animation;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerRawPitchAnimation {

    private static final float ATTENUATION_COEFFICIENT = 0.5F;
    private float lastYaw;
    private float lastPitch;
    private float anchoredYaw;
    private float anchoredPitch;
    private float startYaw;
    private float startPitch;
    private float targetYaw;
    private float targetPitch;
    private float maxYaw = 2.0F;
    private float maxPitch = 2.0F;
    private float attenuation = 0.5F;
    private final Random rand = new Random();
    private long transitionDuration = 2000L;
    private long startTime;
    private EntityPlayer clientPlayer;
    private boolean forceResetYawPitch;

    PlayerRawPitchAnimation setMaxYaw(float maxYaw) {
        this.maxYaw = maxYaw;
        return this;
    }

    PlayerRawPitchAnimation setMaxPitch(float maxPitch) {
        this.maxPitch = maxPitch;
        return this;
    }

    PlayerRawPitchAnimation setTransitionDuration(long transitionDuration) {
        this.transitionDuration = transitionDuration;
        return this;
    }

    PlayerRawPitchAnimation setPlayer(EntityPlayer clientPlayer) {
        this.clientPlayer = clientPlayer;
        return this;
    }

    public void update() {
        float progress = (float) (System.currentTimeMillis() - this.startTime) / (float) this.transitionDuration;
        if (this.forceResetYawPitch || this.rotationPitchChanged(this.clientPlayer)) {
            this.anchoredYaw = this.clientPlayer.rotationYaw;
            this.anchoredPitch = this.clientPlayer.rotationPitch;
            this.forceResetYawPitch = true;
            this.attenuation = 1.0F;
        }

        if (this.forceResetYawPitch || progress > 1.0F) {
            progress = 0.0F;
            this.startTime = System.currentTimeMillis();
            this.startYaw = this.clientPlayer.rotationYaw;
            this.startPitch = this.clientPlayer.rotationPitch;
            this.targetYaw = this.anchoredYaw + (this.rand.nextFloat() - 0.5F) * 2.0F * this.maxYaw * this.attenuation;
            this.targetPitch = this.anchoredPitch
                + (this.rand.nextFloat() - 0.5F) * 2.0F * this.maxPitch * this.attenuation;
            this.attenuation *= ATTENUATION_COEFFICIENT;
            if (this.attenuation < 0.1F) {
                this.attenuation = 0.1F;
            }
        }

        if (this.forceResetYawPitch) {
            this.forceResetYawPitch = false;
        }

        this.clientPlayer.rotationYaw = this.startYaw + (this.targetYaw - this.startYaw) * progress;
        this.clientPlayer.rotationPitch = this.startPitch + (this.targetPitch - this.startPitch) * progress;
        this.lastYaw = this.clientPlayer.rotationYaw;
        this.lastPitch = this.clientPlayer.rotationPitch;
    }

    public void reset() {
        this.forceResetYawPitch = true;
    }

    private boolean rotationPitchChanged(EntityPlayer clientPlayer) {
        return this.lastYaw != clientPlayer.rotationYaw || this.lastPitch != clientPlayer.rotationPitch;
    }
}
