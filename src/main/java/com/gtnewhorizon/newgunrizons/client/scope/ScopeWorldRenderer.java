package com.gtnewhorizon.newgunrizons.client.scope;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ScopeWorldRenderer extends EntityRenderer {
   private boolean useShader;

   public ScopeWorldRenderer(Minecraft minecraft, IResourceManager resourceManager) {
      super(minecraft, resourceManager);
   }

   public void renderWorld(float partialTicks, long finishTimeNano) {
      super.renderWorld(partialTicks, finishTimeNano);
      this.applyShaderIfNeeded(partialTicks);
   }

   private void applyShaderIfNeeded(float partialTicks) {
      if (OpenGlHelper.shadersSupported) {
         if (this.theShaderGroup != null && this.useShader) {
            GL11.glMatrixMode(5890);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            this.theShaderGroup.loadShaderGroup(partialTicks);
            GL11.glPopMatrix();
         }

         Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
      }
   }

   public void useShader(boolean useShader) {
      this.useShader = useShader;
   }
}
