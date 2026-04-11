package com.gtnewhorizon.newgunrizons.client.shaders;

import com.gtnewhorizon.newgunrizons.client.scope.ScopeWorldRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import org.lwjgl.opengl.GL11;

public interface ShaderPhase {
   ShaderPhase WORLD_RENDER = new ShaderPhase.EntityRendererBinding();
   ShaderPhase SCOPE_RENDER = new ShaderPhase.ScopeRendererBinding();
   ShaderPhase ITEM_RENDER = new ShaderPhase.ItemRenderBinding();

   void apply(ShaderContext var1, DynamicShaderGroup var2);

   void remove(ShaderContext var1, DynamicShaderGroup var2);

   public static class EntityRendererBinding implements ShaderPhase {
      @Override
      public void apply(ShaderContext context, DynamicShaderGroup shaderGroup) {
         EntityRenderer renderer = context.getRenderer();
         ShaderGroup currentShaderGroup = renderer.func_147706_e();
         if (currentShaderGroup != shaderGroup) {
            this.remove(context, null);
            renderer.field_147707_d = shaderGroup;
         }
      }

      @Override
      public void remove(ShaderContext context, DynamicShaderGroup shaderGroup) {
         EntityRenderer renderer = context.getRenderer();
         ShaderGroup currentShaderGroup = renderer.func_147706_e();
         if (currentShaderGroup instanceof DynamicShaderGroup) {
            currentShaderGroup.func_148021_a();
            renderer.field_147707_d = null;
         }
      }
   }

   public static class ItemRenderBinding implements ShaderPhase {
      @Override
      public void apply(ShaderContext context, DynamicShaderGroup shaderGroup) {
         if (OpenGlHelper.field_148824_g) {
            int originalMatrixMode = GL11.glGetInteger(2976);
            GL11.glMatrixMode(5890);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glPushAttrib(8193);
            shaderGroup.func_148018_a(context.getPartialTicks());
            GL11.glPopAttrib();
            GL11.glPopMatrix();
            GL11.glMatrixMode(originalMatrixMode);
         }
      }

      @Override
      public void remove(ShaderContext context, DynamicShaderGroup shaderGroup) {
         shaderGroup.func_148021_a();
      }
   }

   public static class ScopeRendererBinding implements ShaderPhase {
      @Override
      public void apply(ShaderContext context, DynamicShaderGroup shaderGroup) {
         EntityRenderer renderer = context.getRenderer();
         if (renderer instanceof ScopeWorldRenderer) {
            ScopeWorldRenderer entityRenderer = (ScopeWorldRenderer)renderer;
            ShaderGroup currentShaderGroup = entityRenderer.func_147706_e();
            if (currentShaderGroup != shaderGroup) {
               entityRenderer.field_147707_d = shaderGroup;
               entityRenderer.useShader(true);
            }
         }
      }

      @Override
      public void remove(ShaderContext context, DynamicShaderGroup shaderGroup) {
         EntityRenderer renderer = context.getRenderer();
         if (renderer instanceof ScopeWorldRenderer) {
            ScopeWorldRenderer entityRenderer = (ScopeWorldRenderer)renderer;
            ShaderGroup currentShaderGroup = entityRenderer.func_147706_e();
            if (currentShaderGroup instanceof DynamicShaderGroup) {
               currentShaderGroup.func_148021_a();
               entityRenderer.field_147707_d = null;
            }
         }
      }
   }
}
