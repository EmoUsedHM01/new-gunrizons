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

   public void func_78471_a(float partialTicks, long finishTimeNano) {
      super.func_78471_a(partialTicks, finishTimeNano);
      this.applyShaderIfNeeded(partialTicks);
   }

   private void applyShaderIfNeeded(float partialTicks) {
      if (OpenGlHelper.field_148824_g) {
         if (this.field_147707_d != null && this.useShader) {
            GL11.glMatrixMode(5890);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            this.field_147707_d.func_148018_a(partialTicks);
            GL11.glPopMatrix();
         }

         Minecraft.func_71410_x().func_147110_a().func_147610_a(true);
      }
   }

   public void useShader(boolean useShader) {
      this.useShader = useShader;
   }
}
