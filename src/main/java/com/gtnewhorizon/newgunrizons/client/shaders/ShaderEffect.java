package com.gtnewhorizon.newgunrizons.client.shaders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.util.ResourceLocation;

public class ShaderEffect {
   private final ResourceLocation shaderLocation;
   private final List<ShaderEffect.UniformBinding> uniforms;
   private final UUID sourceId;

   public ShaderEffect(UUID sourceId, ResourceLocation location) {
      this.sourceId = sourceId;
      this.shaderLocation = location;
      this.uniforms = new ArrayList<>();
   }

   public ShaderEffect withUniform(String name, Function<ShaderContext, Object> supplier) {
      this.uniforms.add(new ShaderEffect.UniformBinding(name, supplier));
      return this;
   }

   public List<ShaderEffect.UniformBinding> getUniforms() {
      return Collections.unmodifiableList(this.uniforms);
   }

   public ResourceLocation getShaderLocation() {
      return this.shaderLocation;
   }

   public UUID getSourceId() {
      return this.sourceId;
   }

   public static final class UniformBinding {
      private final String name;
      private final Function<ShaderContext, Object> supplier;

      public UniformBinding(String name, Function<ShaderContext, Object> supplier) {
         this.name = name;
         this.supplier = supplier;
      }

      public String getName() {
         return this.name;
      }

      public Function<ShaderContext, Object> getSupplier() {
         return this.supplier;
      }
   }
}
