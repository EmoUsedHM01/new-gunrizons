package com.gtnewhorizon.newgunrizons.client.scope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Framebuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.RenderingPhase;
import com.gtnewhorizon.newgunrizons.client.shader.DynamicShaderContext;
import com.gtnewhorizon.newgunrizons.client.shader.DynamicShaderGroupManager;
import com.gtnewhorizon.newgunrizons.client.shader.DynamicShaderPhase;
import com.gtnewhorizon.newgunrizons.config.ClientModContext;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class ScopePerspective {

    private static final int SCOPE_TEXTURE_SIZE = 400;

    /** Scope render is capped at ~60 FPS; the previous frame's texture stays valid. */
    private static final long SCOPE_RENDER_INTERVAL_NS = 10_666_666L;
    private long lastScopeRenderNano;

    protected ClientModContext modContext;
    protected Framebuffer framebuffer;
    private Framebuffer renderFramebuffer;
    protected ScopeWorldRenderer entityRenderer;
    protected EffectRenderer effectRenderer;
    protected DynamicShaderGroupManager shaderGroupManager;

    private long renderEndNanoTime = System.nanoTime();

    /** Set to {@code true} while the scope is rendering, read by MixinRenderGlobal. */
    public static boolean isRenderingScope;

    /** Whether Iris/Angelica shader pipeline is present (checked once, cached). */
    private static boolean irisDetectionDone;
    private static boolean irisPresent;

    public ScopePerspective() {}

    public void activate(ClientModContext modContext, ScopeManager manager) {
        this.modContext = modContext;

        if (this.framebuffer == null) {
            this.framebuffer = new Framebuffer(SCOPE_TEXTURE_SIZE, SCOPE_TEXTURE_SIZE, true);
            this.framebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
        }

        this.entityRenderer = manager.getEntityRenderer();
        this.effectRenderer = manager.getEffectRenderer();
        this.shaderGroupManager = new DynamicShaderGroupManager();

        if (this.shaderGroupManager.hasActiveGroups()) {
            System.err.println("!!! Active shader groups found !!!");
        }
    }

    public void deactivate() {
        this.framebuffer.deleteFramebuffer();
        if (this.renderFramebuffer != null) {
            this.renderFramebuffer.deleteFramebuffer();
            this.renderFramebuffer = null;
        }
        this.shaderGroupManager.removeAllShaders(new DynamicShaderContext(null, this.entityRenderer, null, 0.0F));
        Minecraft.getMinecraft()
            .getFramebuffer()
            .bindFramebuffer(true);
    }

    public float getBrightness(RenderContext renderContext) {
        float brightness = 0.0F;
        PlayerWeaponInstance instance = renderContext.getWeaponInstance();
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
        return renderableState == RenderableState.ZOOMING || renderableState == RenderableState.ZOOMING_RECOILED
            || renderableState == RenderableState.ZOOMING_SHOOTING;
    }

    public void update(RenderTickEvent event, PlayerWeaponInstance weaponInstance) {
        long now = System.nanoTime();
        if (now - this.lastScopeRenderNano < SCOPE_RENDER_INTERVAL_NS) {
            return;
        }
        this.lastScopeRenderNano = now;

        Minecraft mc = Minecraft.getMinecraft();
        boolean useScreenResFbo = isIrisPresent();

        // When Iris/Angelica is loaded, render to a screen-resolution FBO to avoid
        // triggering the expensive framebuffer resize cascade in prepareRenderTargets().
        // When vanilla (no Iris), render directly to the 400x400 display FBO.
        Framebuffer targetFbo;
        if (useScreenResFbo) {
            ensureRenderFramebufferSize(mc.displayWidth, mc.displayHeight);
            targetFbo = this.renderFramebuffer;
        } else {
            targetFbo = this.framebuffer;
        }

        this.modContext.getSafeGlobals().renderingPhase.set(RenderingPhase.RENDER_PERSPECTIVE);
        long finishTimeNano = this.renderEndNanoTime + 16666666L;

        // Save Minecraft state
        int origDisplayWidth = mc.displayWidth;
        int origDisplayHeight = mc.displayHeight;
        EntityRenderer origEntityRenderer = mc.entityRenderer;
        Framebuffer origFramebuffer = mc.framebufferMc;
        boolean origHideGUI = mc.gameSettings.hideGUI;

        mc.framebufferMc = targetFbo;
        mc.entityRenderer = this.entityRenderer;
        mc.gameSettings.hideGUI = true;

        // Only change displayWidth/Height on vanilla — safe there, and needed for
        // correct 1:1 projection. With Iris this triggers the resize cascade.
        if (!useScreenResFbo) {
            mc.displayWidth = SCOPE_TEXTURE_SIZE;
            mc.displayHeight = SCOPE_TEXTURE_SIZE;
        }

        targetFbo.bindFramebuffer(true);
        this.entityRenderer.updateRenderer();
        this.prepareRenderWorld(event, weaponInstance, targetFbo);

        isRenderingScope = true;
        try {
            this.entityRenderer.renderWorld(event.renderTickTime, finishTimeNano);
        } finally {
            isRenderingScope = false;
        }
        this.postRenderWorld(event, targetFbo);

        // With Iris, blit center square from screen-res FBO to 400x400 display FBO.
        if (useScreenResFbo) {
            blitCenterSquareToDisplayFbo();
        }

        // Restore Minecraft state
        mc.gameSettings.hideGUI = origHideGUI;
        mc.framebufferMc = origFramebuffer;
        mc.entityRenderer = origEntityRenderer;
        mc.displayWidth = origDisplayWidth;
        mc.displayHeight = origDisplayHeight;
        origFramebuffer.bindFramebuffer(true);

        this.renderEndNanoTime = System.nanoTime();
        this.modContext.getSafeGlobals().renderingPhase.set(RenderingPhase.NORMAL);
    }

    private void ensureRenderFramebufferSize(int width, int height) {
        if (this.renderFramebuffer != null && this.renderFramebuffer.framebufferWidth == width
            && this.renderFramebuffer.framebufferHeight == height) {
            return;
        }
        if (this.renderFramebuffer != null) {
            this.renderFramebuffer.deleteFramebuffer();
        }
        this.renderFramebuffer = new Framebuffer(width, height, true);
    }

    private void blitCenterSquareToDisplayFbo() {
        int srcW = this.renderFramebuffer.framebufferWidth;
        int srcH = this.renderFramebuffer.framebufferHeight;
        int squareSize = Math.min(srcW, srcH);
        int srcX0 = (srcW - squareSize) / 2;
        int srcY0 = (srcH - squareSize) / 2;

        GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, this.renderFramebuffer.framebufferObject);
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, this.framebuffer.framebufferObject);
        GL30.glBlitFramebuffer(
            srcX0,
            srcY0,
            srcX0 + squareSize,
            srcY0 + squareSize,
            0,
            0,
            SCOPE_TEXTURE_SIZE,
            SCOPE_TEXTURE_SIZE,
            GL11.GL_COLOR_BUFFER_BIT,
            GL11.GL_LINEAR);
    }

    private void prepareRenderWorld(RenderTickEvent event, PlayerWeaponInstance weaponInstance, Framebuffer targetFbo) {
        DynamicShaderContext shaderContext = new DynamicShaderContext(
            DynamicShaderPhase.POST_WORLD_OPTICAL_SCOPE_RENDER,
            this.entityRenderer,
            targetFbo,
            event.renderTickTime);
        this.shaderGroupManager.applyShader(shaderContext, weaponInstance);
    }

    private void postRenderWorld(RenderTickEvent event, Framebuffer targetFbo) {
        DynamicShaderContext shaderContext = new DynamicShaderContext(
            DynamicShaderPhase.POST_WORLD_OPTICAL_SCOPE_RENDER,
            this.entityRenderer,
            targetFbo,
            event.renderTickTime);
        this.shaderGroupManager.removeStaleShaders(shaderContext);
    }

    private static boolean isIrisPresent() {
        if (!irisDetectionDone) {
            irisDetectionDone = true;
            try {
                Class.forName("net.coderbot.iris.Iris");
                irisPresent = true;
            } catch (ClassNotFoundException e) {
                irisPresent = false;
            }
        }
        return irisPresent;
    }
}
