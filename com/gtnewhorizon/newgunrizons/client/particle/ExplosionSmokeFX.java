package com.gtnewhorizon.newgunrizons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ExplosionSmokeFX extends EntityFX {
   private static final int BASE_MAX_AGE = 50;
   private static final int MAX_AGE_VARIANCE = 30;
   private static final double ALPHA_PHASE_OFFSET = Math.PI / 4;
   private static final float PEAK_ALPHA = 0.3F;
   private static final float MIN_MOTION_X = 1.0F;
   private static final double BUOYANCY = 1.0E-5;
   private static final double HORIZONTAL_DRAG = 0.8;
   private static final double VERTICAL_DRAG = 1.0;
   private static final double GROUND_FRICTION = 0.7;
   private static final int COLUMN_COUNT = 4;
   private static final int ROW_COUNT = 4;
   private static final int TOTAL_IMAGES = 16;
   private static final float COLUMN_WIDTH = 0.25F;
   private static final float ROW_HEIGHT = 0.25F;
   private static final float RENDER_SCALE = 0.1F;
   private static final float MIN_ALPHA_THRESHOLD = 0.003921569F;
   private static final int FULL_BRIGHTNESS = 200;
   private static final ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> EXPLOSION_SCALE_FUNCTION = (currentScale, ticks, maxTicks) -> {
      if (currentScale > 25.0F) {
         return currentScale * 1.0008F;
      } else if (currentScale > 20.0F) {
         return currentScale * 1.002F;
      } else if (currentScale > 15.0F) {
         return currentScale * 1.004F;
      } else {
         return currentScale > 10.0F ? currentScale * 1.05F : currentScale * 3.0F;
      }
   };
   private static final ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> SMOKE_GRENADE_SCALE_FUNCTION = (currentScale, ticks, maxTicks) -> {
      if (currentScale > 25.0F) {
         return currentScale * 1.0008F;
      } else if (currentScale > 20.0F) {
         return currentScale * 1.002F;
      } else if (currentScale > 15.0F) {
         return currentScale * 1.004F;
      } else {
         return currentScale > 5.0F ? currentScale * 1.05F : currentScale * 2.0F;
      }
   };
   private static final ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> EXPLOSION_ALPHA_FUNCTION = (currentAlpha, ticks, maxTicks) -> {
      double alphaRadians = (Math.PI / 4) + Math.PI * ticks.intValue() / maxTicks.intValue();
      return 0.3F * (float)Math.sin(Math.min(alphaRadians, Math.PI));
   };
   private static final ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> SMOKE_GRENADE_ALPHA_FUNCTION = (currentAlpha, ticks, maxTicks) -> {
      double alphaRadians = (Math.PI / 4) + Math.PI * ticks.intValue() / maxTicks.intValue();
      return 0.3F * (float)Math.sin(Math.min(alphaRadians, Math.PI));
   };
   private final int imageIndex;
   private final ExplosionSmokeFX.Behavior behavior;
   private final ResourceLocation smokeTexture;

   public ExplosionSmokeFX(
      World world,
      double positionX,
      double positionY,
      double positionZ,
      float scale,
      float motionX,
      float motionY,
      float motionZ,
      int particleMaxAge,
      ExplosionSmokeFX.Behavior behavior,
      ResourceLocation smokeTexture
   ) {
      super(world, positionX, positionY, positionZ, 0.0, 0.0, 0.0);
      this.smokeTexture = smokeTexture;
      if (motionX == 0.0F) {
         motionX = 1.0F;
      }

      this.field_70159_w = motionX;
      this.field_70181_x = motionY;
      this.field_70179_y = motionZ;
      this.behavior = behavior;
      this.field_70552_h = 1.0F;
      this.field_70553_i = 1.0F;
      this.field_70551_j = 1.0F;
      this.field_82339_as = 0.0F;
      this.field_70544_f *= scale;
      this.field_70547_e = particleMaxAge == 0 ? 50 + (int)(this.field_70146_Z.nextFloat() * 30.0F) : particleMaxAge;
      this.imageIndex = this.field_70146_Z.nextInt(16);
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

      this.field_70181_x += 1.0E-5;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.8;
      this.field_70181_x *= 1.0;
      this.field_70179_y *= 0.8;
      this.field_82339_as = this.behavior.alphaUpdateFunction.apply(this.field_82339_as, this.field_70546_d, this.field_70547_e);
      this.field_70544_f = this.behavior.scaleUpdateFunction.apply(this.field_70544_f, this.field_70546_d, this.field_70547_e);
      if (this.field_70122_E) {
         this.field_70159_w *= 0.7;
         this.field_70179_y *= 0.7;
      }
   }

   public void func_70539_a(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
      int prevProgram = GL11.glGetInteger(35725);
      if (prevProgram != 0) {
         GL20.glUseProgram(0);
      }

      Minecraft.func_71410_x().func_110434_K().func_110577_a(this.smokeTexture);
      GL11.glPushMatrix();
      GL11.glPushAttrib(286977);
      if (prevProgram != 0) {
         GL11.glDrawBuffer(36064);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glAlphaFunc(516, 0.003921569F);
      RenderHelper.func_74518_a();
      tessellator.func_78371_b(7);
      tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
      tessellator.func_78380_c(200);
      float size = 0.1F * this.field_70544_f;
      float x = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * partialTicks - field_70556_an);
      float y = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * partialTicks - field_70554_ao);
      float z = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * partialTicks - field_70555_ap);
      int row = Math.floorDiv(this.imageIndex, 4);
      int col = this.imageIndex % 4;
      float u0 = col * 0.25F;
      float u1 = (col + 1) * 0.25F;
      float v0 = row * 0.25F;
      float v1 = (row + 1) * 0.25F;
      tessellator.func_78374_a(x - par3 * size - par6 * size, y - par4 * size, z - par5 * size - par7 * size, u1, v1);
      tessellator.func_78374_a(x - par3 * size + par6 * size, y + par4 * size, z - par5 * size + par7 * size, u1, v0);
      tessellator.func_78374_a(x + par3 * size + par6 * size, y + par4 * size, z + par5 * size + par7 * size, u0, v0);
      tessellator.func_78374_a(x + par3 * size - par6 * size, y - par4 * size, z + par5 * size - par7 * size, u0, v1);
      tessellator.func_78381_a();
      RenderHelper.func_74519_b();
      GL11.glPopAttrib();
      GL11.glPopMatrix();
      if (prevProgram != 0) {
         GL20.glUseProgram(prevProgram);
      }
   }

   public int func_70537_b() {
      return 3;
   }

   public static enum Behavior {
      EXPLOSION(ExplosionSmokeFX.EXPLOSION_SCALE_FUNCTION, ExplosionSmokeFX.EXPLOSION_ALPHA_FUNCTION),
      SMOKE_GRENADE(ExplosionSmokeFX.SMOKE_GRENADE_SCALE_FUNCTION, ExplosionSmokeFX.SMOKE_GRENADE_ALPHA_FUNCTION);

      private final ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> scaleUpdateFunction;
      private final ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> alphaUpdateFunction;

      private Behavior(
         ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> scaleUpdateFunction,
         ExplosionSmokeFX.TriFunction<Float, Integer, Integer, Float> alphaUpdateFunction
      ) {
         this.scaleUpdateFunction = scaleUpdateFunction;
         this.alphaUpdateFunction = alphaUpdateFunction;
      }
   }

   @FunctionalInterface
   public interface TriFunction<T, U, V, R> {
      R apply(T var1, U var2, V var3);
   }
}
