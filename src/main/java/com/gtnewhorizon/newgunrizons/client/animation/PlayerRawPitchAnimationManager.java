package com.gtnewhorizon.newgunrizons.client.animation;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerRawPitchAnimationManager {

    private final Map<EntityPlayer, PlayerRawPitchAnimation> animations = new HashMap<>();
    private final float maxYaw = 2.0F;
    private final float maxPitch = 2.0F;
    private final long transitionDuration = 2000L;

    public void update(EntityPlayer player) {
        this.getAnimation(player)
            .update();
    }

    public void reset(EntityPlayer player) {
        this.getAnimation(player)
            .reset();
    }

    private PlayerRawPitchAnimation getAnimation(EntityPlayer player) {
        return this.animations.computeIfAbsent(
            player,
            (p) -> (new PlayerRawPitchAnimation()).setMaxPitch(this.maxPitch)
                .setMaxYaw(this.maxYaw)
                .setPlayer(player)
                .setTransitionDuration(this.transitionDuration));
    }
}
