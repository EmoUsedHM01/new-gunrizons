package com.gtnewhorizon.newgunrizons.client.shaders;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Framebuffer;

public class ShaderContext {
   private final float partialTicks;
   private final ShaderPhase phase;
   private final Framebuffer framebuffer;
   private final EntityRenderer renderer;

   public ShaderContext(ShaderPhase phase, EntityRenderer renderer, Framebuffer framebuffer, float partialTicks) {
      this.partialTicks = partialTicks;
      this.phase = phase;
      this.framebuffer = framebuffer;
      this.renderer = renderer;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public ShaderPhase getPhase() {
      return this.phase;
   }

   public Framebuffer getFramebuffer() {
      return this.framebuffer;
   }

   public EntityRenderer getRenderer() {
      return this.renderer;
   }
}
