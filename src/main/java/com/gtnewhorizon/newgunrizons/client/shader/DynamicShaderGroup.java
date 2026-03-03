package com.gtnewhorizon.newgunrizons.client.shader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;

import com.google.gson.JsonSyntaxException;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class DynamicShaderGroup extends ShaderGroup {

    private final Map<String, Object> uniforms = new HashMap<>();

    public DynamicShaderGroup(TextureManager textureManager, IResourceManager resourceManager,
        Framebuffer mainFramebufferIn, ResourceLocation resourceLocation) throws IOException, JsonSyntaxException {
        super(textureManager, resourceManager, mainFramebufferIn, resourceLocation);
    }

    public Shader addShader(String name, Framebuffer framebufferIn, Framebuffer framebufferOut) {
        IResourceManager resourceManager = getResourceManager(this);

        try {
            DynamicShader shader = new DynamicShader(resourceManager, name, framebufferIn, framebufferOut, this);
            List<Shader> listShaders = getShaders(this);
            listShaders.add(listShaders.size(), shader);
            return shader;
        } catch (IOException var7) {
            throw new RuntimeException(var7);
        }
    }

    public void render(float partialTicks) {
        this.loadShaderGroup(partialTicks);
    }

    public void setUniform(String name, Object value) {
        this.uniforms.put(name, value);
    }

    protected Map<String, Object> getUniforms() {
        return this.uniforms;
    }

    private static IResourceManager getResourceManager(ShaderGroup shaderGroup) {
        return ObfuscationReflectionHelper
            .getPrivateValue(ShaderGroup.class, shaderGroup, "resourceManager", "resourceManager");
    }

    private static List<Shader> getShaders(ShaderGroup shaderGroup) {
        return ObfuscationReflectionHelper
            .getPrivateValue(ShaderGroup.class, shaderGroup, "listShaders", "listShaders");
    }
}
