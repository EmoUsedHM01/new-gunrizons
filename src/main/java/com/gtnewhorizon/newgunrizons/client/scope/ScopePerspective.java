package com.gtnewhorizon.newgunrizons.client.scope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Framebuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderContext;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderManager;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderPhase;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class ScopePerspective {

    private static final int SCOPE_TEXTURE_SIZE = 400;

    /** Scope render is capped at ~90 FPS; the previous frame's texture stays valid. */
    private static final long SCOPE_RENDER_INTERVAL_NS = 10_666_666L;
    private long lastScopeRenderNano;

    protected Framebuffer framebuffer;
    private Framebuffer renderFramebuffer;
    protected ScopeWorldRenderer worldRenderer;
    protected EffectRenderer effectRenderer;
    protected ShaderManager shaderManager;

    private long renderEndNanoTime = System.nanoTime();

    /** Set to {@code true} while the scope is rendering, read by MixinRenderGlobal. */
    public static boolean isRenderingScope;

    /** Whether Iris/Angelica shader pipeline is present (checked once, cached). */
    private static boolean irisDetectionDone;
    private static boolean irisPresent;

    public ScopePerspective() {}

    public void activate(ScopeManager manager) {
        if (this.framebuffer == null) {
            this.framebuffer = new Framebuffer(SCOPE_TEXTURE_SIZE, SCOPE_TEXTURE_SIZE, true);
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
        Minecraft.getMinecraft()
            .getFramebuffer()
            .bindFramebuffer(true);
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
            || renderableState == RenderableState.ZOOMING_SHOOTING;
    }

    public void update(RenderTickEvent event, ItemWeaponInstance weaponInstance) {
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

        isRenderingScope = true;
        long finishTimeNano = this.renderEndNanoTime + 16666666L;

        // Save Minecraft state
        int origDisplayWidth = mc.displayWidth;
        int origDisplayHeight = mc.displayHeight;
        EntityRenderer origEntityRenderer = mc.entityRenderer;
        Framebuffer origFramebuffer = mc.framebufferMc;
        boolean origHideGUI = mc.gameSettings.hideGUI;

        mc.framebufferMc = targetFbo;
        mc.entityRenderer = this.worldRenderer;
        mc.gameSettings.hideGUI = true;

        // Only change displayWidth/Height on vanilla — safe there, and needed for
        // correct 1:1 projection. With Iris this triggers the resize cascade.
        if (!useScreenResFbo) {
            mc.displayWidth = SCOPE_TEXTURE_SIZE;
            mc.displayHeight = SCOPE_TEXTURE_SIZE;
        }

        targetFbo.bindFramebuffer(true);
        this.worldRenderer.updateRenderer();
        this.prepareRenderWorld(event, weaponInstance, targetFbo);

        this.worldRenderer.renderWorld(event.renderTickTime, finishTimeNano);
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
        isRenderingScope = false;
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

    private void prepareRenderWorld(RenderTickEvent event, ItemWeaponInstance weaponInstance, Framebuffer targetFbo) {
        ShaderContext shaderContext = new ShaderContext(
            ShaderPhase.SCOPE_RENDER,
            this.worldRenderer,
            targetFbo,
            event.renderTickTime);
        this.shaderManager.applyShader(shaderContext, weaponInstance);
    }

    private void postRenderWorld(RenderTickEvent event, Framebuffer targetFbo) {
        ShaderContext shaderContext = new ShaderContext(
            ShaderPhase.SCOPE_RENDER,
            this.worldRenderer,
            targetFbo,
            event.renderTickTime);
        this.shaderManager.removeStaleShaders(shaderContext);
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
