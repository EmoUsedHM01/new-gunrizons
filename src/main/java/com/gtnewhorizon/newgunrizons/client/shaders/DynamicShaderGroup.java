package com.gtnewhorizon.newgunrizons.client.shaders;

import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.ResourceLocation;

public class DynamicShaderGroup extends ShaderGroup {
   private final Map<String, Object> uniforms = new HashMap<>();

   public DynamicShaderGroup(TextureManager textureManager, IResourceManager resourceManager, Framebuffer mainFramebufferIn, ResourceLocation resourceLocation) throws IOException, JsonSyntaxException {
      super(textureManager, resourceManager, mainFramebufferIn, resourceLocation);
   }

   public Shader addShader(String name, Framebuffer framebufferIn, Framebuffer framebufferOut) throws JsonException {
      DynamicShader shader = new DynamicShader(this.resourceManager, name, framebufferIn, framebufferOut, this);
      this.listShaders.add(this.listShaders.size(), shader);
      return shader;
   }

   public void setUniform(String name, Object value) {
      this.uniforms.put(name, value);
   }

   protected Map<String, Object> getUniforms() {
      return this.uniforms;
   }
}
