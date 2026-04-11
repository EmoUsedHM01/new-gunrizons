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
         this.framebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      }

      this.worldRenderer = manager.getWorldRenderer();
      this.effectRenderer = manager.getEffectRenderer();
      this.shaderManager = new ShaderManager();
      if (this.shaderManager.hasActiveGroups()) {
         System.err.println("!!! Active shader groups found !!!");
      }
   }

   public void deactivate() {
      this.framebuffer.deleteFramebuffer();
      if (this.renderFramebuffer != null) {
         this.renderFramebuffer.deleteFramebuffer();
         this.renderFramebuffer = null;
      }

      this.shaderManager.removeAllShaders(new ShaderContext(null, this.worldRenderer, null, 0.0F));
      Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
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
      return this.framebuffer != null ? this.framebuffer.framebufferTexture : -1;
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
         Minecraft mc = Minecraft.getMinecraft();
         boolean useScreenResFbo = isIrisPresent();
         Framebuffer targetFbo;
         if (useScreenResFbo) {
            this.ensureRenderFramebufferSize(mc.displayWidth, mc.displayHeight);
            targetFbo = this.renderFramebuffer;
         } else {
            targetFbo = this.framebuffer;
         }

         isRenderingScope = true;
         long finishTimeNano = this.renderEndNanoTime + 16666666L;
         int origDisplayWidth = mc.displayWidth;
         int origDisplayHeight = mc.displayHeight;
         EntityRenderer origEntityRenderer = mc.entityRenderer;
         Framebuffer origFramebuffer = mc.framebufferMc;
         boolean origHideGUI = mc.gameSettings.hideGUI;
         mc.framebufferMc = targetFbo;
         mc.entityRenderer = this.worldRenderer;
         mc.gameSettings.hideGUI = true;
         if (!useScreenResFbo) {
            mc.displayWidth = 400;
            mc.displayHeight = 400;
         }

         targetFbo.bindFramebuffer(true);
         this.worldRenderer.updateRenderer();
         this.prepareRenderWorld(event, weaponInstance, targetFbo);
         this.worldRenderer.renderWorld(event.renderTickTime, finishTimeNano);
         this.postRenderWorld(event, targetFbo);
         if (useScreenResFbo) {
            this.blitCenterSquareToDisplayFbo();
         }

         mc.gameSettings.hideGUI = origHideGUI;
         mc.framebufferMc = origFramebuffer;
         mc.entityRenderer = origEntityRenderer;
         mc.displayWidth = origDisplayWidth;
         mc.displayHeight = origDisplayHeight;
         origFramebuffer.bindFramebuffer(true);
         this.renderEndNanoTime = System.nanoTime();
         isRenderingScope = false;
      }
   }

   private void ensureRenderFramebufferSize(int width, int height) {
      if (this.renderFramebuffer == null || this.renderFramebuffer.framebufferWidth != width || this.renderFramebuffer.framebufferHeight != height) {
         if (this.renderFramebuffer != null) {
            this.renderFramebuffer.deleteFramebuffer();
         }

         this.renderFramebuffer = new Framebuffer(width, height, true);
      }
   }

   private void blitCenterSquareToDisplayFbo() {
      int srcW = this.renderFramebuffer.framebufferWidth;
      int srcH = this.renderFramebuffer.framebufferHeight;
      int squareSize = Math.min(srcW, srcH);
      int srcX0 = (srcW - squareSize) / 2;
      int srcY0 = (srcH - squareSize) / 2;
      GL30.glBindFramebuffer(36008, this.renderFramebuffer.framebufferObject);
      GL30.glBindFramebuffer(36009, this.framebuffer.framebufferObject);
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
