package com.gtnewhorizon.newgunrizons.client.shader;

import java.io.IOException;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderUniform;

class DynamicShader extends Shader {

    private final DynamicShaderGroup shaderGroup;

    public DynamicShader(IResourceManager resourceManager, String programName, Framebuffer framebufferInIn,
        Framebuffer framebufferOutIn, DynamicShaderGroup shaderGroup) throws IOException {
        super(resourceManager, programName, framebufferInIn, framebufferOutIn);
        this.shaderGroup = shaderGroup;
    }

    public void loadShader(float partialTicks) {
        this.shaderGroup.getUniforms()
            .forEach((name, value) -> {
                ShaderUniform uniform = this.getShaderManager()
                    .func_147991_a(name);
                if (uniform != null) {
                    if (value instanceof Float) {
                        uniform.func_148090_a((Float) value);
                    } else if (value instanceof float[]values) {
                        if (values.length == 1) {
                            uniform.func_148090_a(values[0]);
                        } else if (values.length == 2) {
                            uniform.func_148087_a(values[0], values[1]);
                        } else if (values.length == 3) {
                            uniform.func_148095_a(values[0], values[1], values[2]);
                        } else if (values.length == 4) {
                            uniform.func_148092_b(values[0], values[1], values[2], values[3]);
                        }
                    } else if (value instanceof Float[]valuesx) {
                        if (valuesx.length == 1) {
                            uniform.func_148090_a(valuesx[0]);
                        } else if (valuesx.length == 2) {
                            uniform.func_148087_a(valuesx[0], valuesx[1]);
                        } else if (valuesx.length == 3) {
                            uniform.func_148095_a(valuesx[0], valuesx[1], valuesx[2]);
                        } else if (valuesx.length == 4) {
                            uniform.func_148092_b(valuesx[0], valuesx[1], valuesx[2], valuesx[3]);
                        }
                    }
                }

            });
        super.loadShader(partialTicks);
    }
}
