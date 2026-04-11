package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class MuzzleFlashRenderer {
   private static final ResourceLocation FLASH_TEXTURE = new ResourceLocation("newgunrizons", "textures/effect/flashes.png");
   private static final int IMAGES_PER_ROW = 8;
   private static final float UV_WIDTH = 0.125F;
   private static final long FLASH_DURATION_MS = 60L;
   private static final float BARREL_X = 0.1F;
   private static final float BARREL_Y = -0.9F;
   private static final float BARREL_Z = -4.7F;
   private static final float FLASH_SIZE = 3.0F;
   private static final Random rand = new Random();
   private static final FloatBuffer modelviewBuf = BufferUtils.createFloatBuffer(16);

   public static void renderIfFiring(RenderContext renderContext) {
      ItemWeaponInstance weaponInstance = renderContext.getWeaponInstance();
      if (weaponInstance != null) {
         ItemWeapon weapon = weaponInstance.getWeapon();
         if (!(weapon.getFlashIntensity() <= 0.0F)) {
            if (!WeaponAttachmentAspect.INSTANCE.isSilencerOn(weaponInstance)) {
               long elapsed = System.currentTimeMillis() - weaponInstance.getLastFireTimestamp();
               if (elapsed >= 0L && elapsed <= 60L) {
                  float alpha = weapon.getFlashIntensity() * (1.0F - (float)elapsed / 60.0F);
                  if (!(alpha <= 0.0F)) {
                     float scale = weapon.getFlashScale().get();
                     float weaponOffsetX = weapon.getFlashOffsetX().get();
                     float weaponOffsetY = weapon.getFlashOffsetY().get();
                     float weaponOffsetZ = weapon.getFlashOffsetZ().get();
                     int imageIndex = Math.abs(rand.nextInt()) % 8;
                     renderFlashQuad(alpha, scale, imageIndex, weaponOffsetX, weaponOffsetY, weaponOffsetZ);
                  }
               }
            }
         }
      }
   }

   private static void renderFlashQuad(float alpha, float scale, int imageIndex, float weaponOffsetX, float weaponOffsetY, float weaponOffsetZ) {
      int prevProgram = GL11.glGetInteger(35725);
      if (prevProgram != 0) {
         GL20.glUseProgram(0);
      }

      Minecraft.func_71410_x().func_110434_K().func_110577_a(FLASH_TEXTURE);
      GL11.glPushMatrix();
      GL11.glPushAttrib(286985);
      if (prevProgram != 0) {
         GL11.glDrawBuffer(36064);
      }

      float bx = 0.1F + weaponOffsetX;
      float by = -0.9F + weaponOffsetY;
      float bz = -4.7F + weaponOffsetZ;
      GL11.glTranslatef(bx, by, bz);
      ((Buffer)modelviewBuf).clear();
      GL11.glGetFloat(2982, modelviewBuf);
      float tx = modelviewBuf.get(12);
      float ty = modelviewBuf.get(13);
      float tz = modelviewBuf.get(14);
      float sx = (float)Math.sqrt(
         modelviewBuf.get(0) * modelviewBuf.get(0) + modelviewBuf.get(1) * modelviewBuf.get(1) + modelviewBuf.get(2) * modelviewBuf.get(2)
      );
      float sy = (float)Math.sqrt(
         modelviewBuf.get(4) * modelviewBuf.get(4) + modelviewBuf.get(5) * modelviewBuf.get(5) + modelviewBuf.get(6) * modelviewBuf.get(6)
      );
      GL11.glLoadIdentity();
      GL11.glTranslatef(tx, ty, tz);
      GL11.glScalef(sx, sy, 1.0F);
      GL11.glDepthMask(false);
      GL11.glEnable(2929);
      GL11.glDisable(2884);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
      GL11.glDisable(2896);
      GL11.glEnable(3553);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float size = 3.0F * scale;
      float u0 = imageIndex * 0.125F;
      float u1 = (imageIndex + 1) * 0.125F;
      Tessellator tess = Tessellator.field_78398_a;
      tess.func_78382_b();
      tess.func_78369_a(1.0F, 1.0F, 1.0F, alpha);
      tess.func_78380_c(240);
      tess.func_78374_a(-size, -size, 0.0, u0, 1.0);
      tess.func_78374_a(-size, size, 0.0, u0, 0.0);
      tess.func_78374_a(size, size, 0.0, u1, 0.0);
      tess.func_78374_a(size, -size, 0.0, u1, 1.0);
      tess.func_78381_a();
      GL11.glPopAttrib();
      GL11.glPopMatrix();
      if (prevProgram != 0) {
         GL20.glUseProgram(prevProgram);
      }
   }
}
