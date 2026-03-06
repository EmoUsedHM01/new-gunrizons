package com.gtnewhorizon.newgunrizons.client.scope;

import java.util.function.BiConsumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.TransformType;

public class ScopeRenderer implements CustomRenderer {

    private final BiConsumer<EntityLivingBase, ItemStack> positioning;

    public ScopeRenderer(BiConsumer<EntityLivingBase, ItemStack> positioning) {
        this.positioning = positioning;
    }

    public void render(RenderContext renderContext) {

        if (renderContext.getTransformType() != TransformType.FIRST_PERSON_RIGHT_HAND
            && renderContext.getTransformType() != TransformType.FIRST_PERSON_LEFT_HAND) {
            return;
        }
        ScopePerspective perspective = ScopeManager.INSTANCE.getPerspective(renderContext.getItemInstance(), false);

        if (perspective == null) {
            return;
        }

        float brightness = perspective.getBrightness(renderContext);
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_CURRENT_BIT);
        this.positioning.accept(renderContext.getPlayer(), renderContext.getWeapon());
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, perspective.getTexture());
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0.0D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(brightness, brightness, brightness, 1.0F);
        float s = renderContext.getScale();
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -10.0F * s, 0.0F);
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        tess.addVertexWithUV(3.0F * s, 3.0F * s, 0.0F, 0.0F, 0.0F);
        tess.addVertexWithUV(0.0F, 3.0F * s, 0.0F, 1.0F, 0.0F);
        tess.addVertexWithUV(0.0F, 0.0F, 0.0F, 1.0F, 1.0F);
        tess.addVertexWithUV(3.0F * s, 0.0F, 0.0F, 0.0F, 1.0F);
        tess.draw();
        GL11.glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0.0D);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
