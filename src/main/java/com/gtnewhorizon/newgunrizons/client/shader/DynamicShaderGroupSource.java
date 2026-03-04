package com.gtnewhorizon.newgunrizons.client.shader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.newgunrizons.util.Pair;

import lombok.Getter;

public class DynamicShaderGroupSource {

    @Getter
    private final ResourceLocation shaderLocation;
    private final List<Pair<String, Function<DynamicShaderContext, Object>>> uniforms;
    @Getter
    private final UUID sourceId;

    public DynamicShaderGroupSource(UUID sourceId, ResourceLocation location) {
        this.sourceId = sourceId;
        this.shaderLocation = location;
        this.uniforms = new ArrayList<>();
    }

    public DynamicShaderGroupSource withUniform(String name, Function<DynamicShaderContext, Object> value) {
        this.uniforms.add(new Pair<>(name, value));
        return this;
    }

    public List<Pair<String, Function<DynamicShaderContext, Object>>> getUniforms(DynamicShaderContext context) {
        return Collections.unmodifiableList(this.uniforms);
    }
}
