package com.gtnewhorizon.newgunrizons.client.render;

import java.util.Random;
import java.util.function.BiConsumer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;

public class EntityLaserBeamRenderer implements CustomRenderer {

    private static final float X_OFFSET = 0.5F;
    private static final float Y_OFFSET = -1.3F;
    private static final float Z_OFFSET = -1.7F;

    private final BiConsumer<EntityLivingBase, ItemStack> positioning;

    public EntityLaserBeamRenderer(BiConsumer<EntityLivingBase, ItemStack> positioning) {
        this.positioning = positioning;
    }

    public void render(RenderContext renderContext) {
        ItemInstance<?> instance = renderContext.getItemInstance();
        TransformType type = renderContext.getTransformType();
        if (instance instanceof ItemWeaponInstance && ((ItemWeaponInstance) instance).isLaserOn()
            && (type == TransformType.THIRD_PERSON_LEFT_HAND || type == TransformType.THIRD_PERSON_RIGHT_HAND
                || type == TransformType.FIRST_PERSON_LEFT_HAND
                || type == TransformType.FIRST_PERSON_RIGHT_HAND
                || type == TransformType.GROUND)) {
            // Save and unbind the current GLSL shader program. glPushAttrib/glPopAttrib
            // does NOT save shader program bindings, so Iris's GBuffer shader would remain
            // partially bound during our fixed-function drawing, corrupting subsequent
            // rendering and causing white textures on weapon model parts.
            int savedProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
            GL20.glUseProgram(0);

            GL11.glPushMatrix();
            GL11.glPushAttrib(1048575);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            // Additive blending for a glowing laser effect
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.7F);
            GL11.glLineWidth(2.5F);
            GL11.glDepthMask(false);
            if (this.positioning != null) {
                this.positioning.accept(renderContext.getPlayer(), renderContext.getWeapon());
            }

            // Draw the laser beam with raw GL_LINES instead of Tessellator.
            // The Tessellator manipulates vertex arrays, lightmap texture unit
            // state, and VAO bindings that corrupt Iris's shader pipeline state,
            // causing subsequent weapon model parts to render white.
            long time = System.currentTimeMillis();
            Random random = new Random(time - time % 300L);
            float start = Z_OFFSET;
            float length = 100.0F;
            float end = 0.0F;

            GL11.glBegin(GL11.GL_LINES);
            for (int i = 0; i < 100 && start < length && end < length; ++i) {
                GL11.glVertex3f(X_OFFSET, Y_OFFSET, start);
                end = start - (1.0F + random.nextFloat() * 2.0F);
                if (end > length) {
                    end = length;
                }
                GL11.glVertex3f(X_OFFSET, Y_OFFSET, end);
                start = end + random.nextFloat() * 0.5F;
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glPopAttrib();
            GL11.glPopMatrix();

            // Restore the GLSL shader program that was active before laser rendering.
            GL20.glUseProgram(savedProgram);
        }

    }
}
