package com.gtnewhorizon.newgunrizons.client.scope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Custom EntityRenderer subclass for scope perspective rendering.
 * <p>
 * Behavioural differences from vanilla EntityRenderer:
 * <ul>
 * <li>Hand rendering is suppressed via {@code MixinEntityRenderer}</li>
 * <li>Chunk frustum culling is skipped via {@code MixinRenderGlobal}</li>
 * <li>Iris hand rendering is suppressed via {@code hideGUI} flag in
 * {@link ScopePerspective}</li>
 * <li>Post-processing shaders can be conditionally applied via
 * {@link #useShader}</li>
 * </ul>
 */
@SideOnly(Side.CLIENT)
public class ScopeWorldRenderer extends EntityRenderer {

    private boolean useShader;

    public ScopeWorldRenderer(Minecraft minecraft, IResourceManager resourceManager) {
        super(minecraft, resourceManager);
    }

    @Override
    public void renderWorld(float partialTicks, long finishTimeNano) {
        super.renderWorld(partialTicks, finishTimeNano);
        applyShaderIfNeeded(partialTicks);
    }

    private void applyShaderIfNeeded(float partialTicks) {
        if (OpenGlHelper.shadersSupported) {
            if (this.theShaderGroup != null && this.useShader) {
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                this.theShaderGroup.loadShaderGroup(partialTicks);
                GL11.glPopMatrix();
            }
            Minecraft.getMinecraft()
                .getFramebuffer()
                .bindFramebuffer(true);
        }
    }

    public void useShader(boolean useShader) {
        this.useShader = useShader;
    }
}
