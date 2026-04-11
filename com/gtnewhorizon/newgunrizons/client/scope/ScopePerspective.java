package com.gtnewhorizon.newgunrizons.client.scope;

import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderContext;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderManager;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderPhase;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL30;

public class ScopePerspective {
   private static final int SCOPE_TEXTURE_SIZE = 400;
   private static final long SCOPE_RENDER_INTERVAL_NS = 10666666L;
   private long lastScopeRenderNano;
   protected Framebuffer framebuffer;
   private Framebuffer renderFramebuffer;
   protected ScopeWorldRenderer worldRenderer;
   protected EffectRenderer effectRenderer;
   protected ShaderManager shaderManager;
   private long renderEndNanoTime = System.nanoTime();
   public static boolean isRenderingScope;
   private static boolean irisDetectionDone;
   private static boolean irisPresent;

   public void activate(ScopeManager manager) {
      if (this.framebuffer == null) {
         this.framebuffer = new Framebuffer(400, 400, true);
         this.framebuffer.func_147604_a(0.0F, 0.0F, 0.0F, 0.0F);
      }

      this.worldRenderer = manager.getWorldRenderer();
      this.effectRenderer = manager.getEffectRenderer();
      this.shaderManager = new ShaderManager();
      if (this.shaderManager.hasActiveGroups()) {
         System.err.println("!!! Active shader groups found !!!");
      }
   }

   public void deactivate() {
      this.framebuffer.func_147608_a();
      if (this.renderFramebuffer != null) {
         this.renderFramebuffer.func_147608_a();
         this.renderFramebuffer = null;
      }

      this.shaderManager.removeAllShaders(new ShaderContext(null, this.worldRenderer, null, 0.0F));
      Minecraft.func_71410_x().func_147110_a().func_147610_a(true);
   }

   public float getBrightness(RenderContext renderContext) {
      float brightness = 0.0F;
      ItemWeaponInstance instance = renderContext.getWeaponInstance();
      if (instance == null) {
         return 0.0F;
      } else {
         boolean aimed = instance.isAimed();
         float progress = Math.min(1.0F, renderContext.getTransitionProgress());
         if (isAimingState(renderContext.getFromState()) && isAimingState(renderContext.getToState())) {
            brightness = 1.0F;
         } else if (progress > 0.0F && aimed && isAimingState(renderContext.getToState())) {
            brightness = progress;
         } else if (isAimingState(renderContext.getFromState()) && progress > 0.0F && !aimed) {
            brightness = Math.max(1.0F - progress, 0.0F);
         }

         return brightness;
      }
   }

   public int getTexture() {
      return this.framebuffer != null ? this.framebuffer.field_147617_g : -1;
   }

   private static boolean isAimingState(RenderableState renderableState) {
      return renderableState == RenderableState.ZOOMING
         || renderableState == RenderableState.ZOOMING_RECOILED
         || renderableState == RenderableState.ZOOMING_SHOOTING;
   }

   public void update(RenderTickEvent event, ItemWeaponInstance weaponInstance) {
      long now = System.nanoTime();
      if (now - this.lastScopeRenderNano >= 10666666L) {
         this.lastScopeRenderNano = now;
         Minecraft mc = Minecraft.func_71410_x();
         boolean useScreenResFbo = isIrisPresent();
         Framebuffer targetFbo;
         if (useScreenResFbo) {
            this.ensureRenderFramebufferSize(mc.field_71443_c, mc.field_71440_d);
            targetFbo = this.renderFramebuffer;
         } else {
            targetFbo = this.framebuffer;
         }

         isRenderingScope = true;
         long finishTimeNano = this.renderEndNanoTime + 16666666L;
         int origDisplayWidth = mc.field_71443_c;
         int origDisplayHeight = mc.field_71440_d;
         EntityRenderer origEntityRenderer = mc.field_71460_t;
         Framebuffer origFramebuffer = mc.field_147124_at;
         boolean origHideGUI = mc.field_71474_y.field_74319_N;
         mc.field_147124_at = targetFbo;
         mc.field_71460_t = this.worldRenderer;
         mc.field_71474_y.field_74319_N = true;
         if (!useScreenResFbo) {
            mc.field_71443_c = 400;
            mc.field_71440_d = 400;
         }

         targetFbo.func_147610_a(true);
         this.worldRenderer.func_78464_a();
         this.prepareRenderWorld(event, weaponInstance, targetFbo);
         this.worldRenderer.func_78471_a(event.renderTickTime, finishTimeNano);
         this.postRenderWorld(event, targetFbo);
         if (useScreenResFbo) {
            this.blitCenterSquareToDisplayFbo();
         }

         mc.field_71474_y.field_74319_N = origHideGUI;
         mc.field_147124_at = origFramebuffer;
         mc.field_71460_t = origEntityRenderer;
         mc.field_71443_c = origDisplayWidth;
         mc.field_71440_d = origDisplayHeight;
         origFramebuffer.func_147610_a(true);
         this.renderEndNanoTime = System.nanoTime();
         isRenderingScope = false;
      }
   }

   private void ensureRenderFramebufferSize(int width, int height) {
      if (this.renderFramebuffer == null || this.renderFramebuffer.field_147621_c != width || this.renderFramebuffer.field_147618_d != height) {
         if (this.renderFramebuffer != null) {
            this.renderFramebuffer.func_147608_a();
         }

         this.renderFramebuffer = new Framebuffer(width, height, true);
      }
   }

   private void blitCenterSquareToDisplayFbo() {
      int srcW = this.renderFramebuffer.field_147621_c;
      int srcH = this.renderFramebuffer.field_147618_d;
      int squareSize = Math.min(srcW, srcH);
      int srcX0 = (srcW - squareSize) / 2;
      int srcY0 = (srcH - squareSize) / 2;
      GL30.glBindFramebuffer(36008, this.renderFramebuffer.field_147616_f);
      GL30.glBindFramebuffer(36009, this.framebuffer.field_147616_f);
      GL30.glBlitFramebuffer(srcX0, srcY0, srcX0 + squareSize, srcY0 + squareSize, 0, 0, 400, 400, 16384, 9729);
   }

   private void prepareRenderWorld(RenderTickEvent event, ItemWeaponInstance weaponInstance, Framebuffer targetFbo) {
      ShaderContext shaderContext = new ShaderContext(ShaderPhase.SCOPE_RENDER, this.worldRenderer, targetFbo, event.renderTickTime);
      this.shaderManager.applyShader(shaderContext, weaponInstance);
   }

   private void postRenderWorld(RenderTickEvent event, Framebuffer targetFbo) {
      ShaderContext shaderContext = new ShaderContext(ShaderPhase.SCOPE_RENDER, this.worldRenderer, targetFbo, event.renderTickTime);
      this.shaderManager.removeStaleShaders(shaderContext);
   }

   private static boolean isIrisPresent() {
      if (!irisDetectionDone) {
         irisDetectionDone = true;

         try {
            Class.forName("net.coderbot.iris.Iris");
            irisPresent = true;
         } catch (ClassNotFoundException var1) {
            irisPresent = false;
         }
      }

      return irisPresent;
   }
}
