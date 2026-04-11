package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import java.util.Random;
import java.util.function.BiConsumer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class EntityLaserBeamRenderer implements CustomRenderer {
   private static final float X_OFFSET = 0.5F;
   private static final float Y_OFFSET = -1.3F;
   private static final float Z_OFFSET = -1.7F;
   private final BiConsumer<EntityLivingBase, ItemStack> positioning;

   public EntityLaserBeamRenderer(BiConsumer<EntityLivingBase, ItemStack> positioning) {
      this.positioning = positioning;
   }

   @Override
   public void render(RenderContext renderContext) {
      ItemInstance<?> instance = renderContext.getItemInstance();
      TransformType type = renderContext.getTransformType();
      if (instance instanceof ItemWeaponInstance
         && ((ItemWeaponInstance)instance).isLaserOn()
         && (
            type == TransformType.THIRD_PERSON_LEFT_HAND
               || type == TransformType.THIRD_PERSON_RIGHT_HAND
               || type == TransformType.FIRST_PERSON_LEFT_HAND
               || type == TransformType.FIRST_PERSON_RIGHT_HAND
               || type == TransformType.GROUND
         )) {
         int savedProgram = GL11.glGetInteger(35725);
         GL20.glUseProgram(0);
         GL11.glPushMatrix();
         GL11.glPushAttrib(1048575);
         GL11.glDisable(2884);
         GL11.glDisable(2896);
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 1);
         GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.7F);
         GL11.glLineWidth(2.5F);
         GL11.glDepthMask(false);
         if (this.positioning != null) {
            this.positioning.accept(renderContext.getPlayer(), renderContext.getWeapon());
         }

         long time = System.currentTimeMillis();
         Random random = new Random(time - time % 300L);
         float start = -1.7F;
         float length = 100.0F;
         float end = 0.0F;
         GL11.glBegin(1);

         for (int i = 0; i < 100 && start < length && end < length; i++) {
            GL11.glVertex3f(0.5F, -1.3F, start);
            end = start - (1.0F + random.nextFloat() * 2.0F);
            if (end > length) {
               end = length;
            }

            GL11.glVertex3f(0.5F, -1.3F, end);
            start = end + random.nextFloat() * 0.5F;
         }

         GL11.glEnd();
         GL11.glDepthMask(true);
         GL11.glPopAttrib();
         GL11.glPopMatrix();
         GL20.glUseProgram(savedProgram);
      }
   }
}
