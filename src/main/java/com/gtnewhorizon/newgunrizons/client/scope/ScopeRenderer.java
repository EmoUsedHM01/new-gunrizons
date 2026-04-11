package com.gtnewhorizon.newgunrizons.client.scope;

import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.TransformType;
import java.util.function.BiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ScopeRenderer implements CustomRenderer {
   private final BiConsumer<EntityLivingBase, ItemStack> positioning;

   public ScopeRenderer(BiConsumer<EntityLivingBase, ItemStack> positioning) {
      this.positioning = positioning;
   }

   @Override
   public void render(RenderContext renderContext) {
      if (renderContext.getTransformType() == TransformType.FIRST_PERSON_RIGHT_HAND || renderContext.getTransformType() == TransformType.FIRST_PERSON_LEFT_HAND
         )
       {
         ScopePerspective perspective = ScopeManager.INSTANCE.getPerspective(renderContext.getItemInstance(), false);
         if (perspective != null) {
            float brightness = perspective.getBrightness(renderContext);
            GL11.glPushMatrix();
            GL11.glPushAttrib(8193);
            this.positioning.accept(renderContext.getPlayer(), renderContext.getWeapon());
            GL11.glBindTexture(3553, perspective.getTexture());
            Minecraft.getMinecraft().entityRenderer.disableLightmap(0.0);
            GL11.glEnable(2929);
            GL11.glDisable(2896);
            GL11.glDisable(3008);
            GL11.glDisable(3042);
            GL11.glColor4f(brightness, brightness, brightness, 1.0F);
            float s = renderContext.getScale();
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, -10.0F * s, 0.0F);
            Tessellator tess = Tessellator.instance;
            tess.startDrawingQuads();
            tess.setNormal(0.0F, 0.0F, 1.0F);
            tess.addVertexWithUV(3.0F * s, 3.0F * s, 0.0, 0.0, 0.0);
            tess.addVertexWithUV(0.0, 3.0F * s, 0.0, 1.0, 0.0);
            tess.addVertexWithUV(0.0, 0.0, 0.0, 1.0, 1.0);
            tess.addVertexWithUV(3.0F * s, 0.0, 0.0, 0.0, 1.0);
            tess.draw();
            GL11.glPopMatrix();
            Minecraft.getMinecraft().entityRenderer.enableLightmap(0.0);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         }
      }
   }
}
