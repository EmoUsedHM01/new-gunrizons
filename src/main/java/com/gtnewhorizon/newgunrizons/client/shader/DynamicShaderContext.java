package com.gtnewhorizon.newgunrizons.client.shader;

import net.minecraft.client.shader.Framebuffer;

import lombok.Getter;

public class DynamicShaderContext {

    @Getter
    private final float partialTicks;
    @Getter
    private final DynamicShaderPhase phase;
    @Getter
    private final Framebuffer mainFramebuffer;
    @Getter
    private final Object target;

    public DynamicShaderContext(DynamicShaderPhase phase, Object target, Framebuffer mainFramebuffer,
        float partialTicks) {
        this.partialTicks = partialTicks;
        this.phase = phase;
        this.mainFramebuffer = mainFramebuffer;
        this.target = target;
    }
}
