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

   public Shader func_148023_a(String name, Framebuffer framebufferIn, Framebuffer framebufferOut) throws JsonException {
      DynamicShader shader = new DynamicShader(this.field_148033_b, name, framebufferIn, framebufferOut, this);
      this.field_148031_d.add(this.field_148031_d.size(), shader);
      return shader;
   }

   public void setUniform(String name, Object value) {
      this.uniforms.put(name, value);
   }

   protected Map<String, Object> getUniforms() {
      return this.uniforms;
   }
}
