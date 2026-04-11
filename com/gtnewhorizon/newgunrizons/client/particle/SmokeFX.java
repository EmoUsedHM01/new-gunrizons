package com.gtnewhorizon.newgunrizons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class SmokeFX extends EntityFX {
   private static final float PEAK_ALPHA = 0.2F;
   private static final double ALPHA_PHASE_OFFSET = Math.PI / 4;
   private static final float SCALE_GROWTH_PER_TICK = 1.0006F;
   private static final int BASE_MAX_AGE = 50;
   private static final int MAX_AGE_VARIANCE = 30;
   private static final float MIN_MOTION_X = 1.0F;
   private static final double BUOYANCY = 5.0E-4;
   private static final double HORIZONTAL_DRAG = 0.6;
   private static final double VERTICAL_DRAG = 1.0;
   private static final double GROUND_FRICTION = 0.7;
   private static final ResourceLocation SMOKE_TEXTURE = new ResourceLocation("newgunrizons", "textures/effect/smokes.png");
   private static final int IMAGES_PER_ROW = 4;
   private static final float UV_WIDTH = 0.25F;
   private static final float RENDER_SCALE = 0.1F;
   private static final float MIN_ALPHA_THRESHOLD = 0.003921569F;
   private static final float MIN_LIGHT = 0.05F;
   private final int imageIndex;

   public SmokeFX(World world, double positionX, double positionY, double positionZ, float scale, float motionX, float motionY, float motionZ) {
      super(world, positionX, positionY, positionZ, 0.0, 0.0, 0.0);
      if (motionX == 0.0F) {
         motionX = 1.0F;
      }

      this.field_70159_w = motionX;
      this.field_70181_x = motionY;
      this.field_70179_y = motionZ;
      this.field_94054_b = 0;
      this.field_94055_c = 0;
      this.field_70552_h = 1.0F;
      this.field_70553_i = 1.0F;
      this.field_70551_j = 1.0F;
      this.field_82339_as = 0.0F;
      this.field_70544_f *= scale;
      this.field_70547_e = 50 + (int)(this.field_70146_Z.nextFloat() * 30.0F);
      this.imageIndex = this.field_70146_Z.nextInt() % 4;
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if (this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

      this.field_70181_x += 5.0E-4;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.6;
      this.field_70181_x *= 1.0;
      this.field_70179_y *= 0.6;
      double alphaRadians = (Math.PI / 4) + Math.PI * this.field_70546_d / this.field_70547_e;
      this.field_82339_as = 0.2F * (float)Math.sin(Math.min(alphaRadians, Math.PI));
      this.field_70544_f *= 1.0006F;
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

      Minecraft.func_71410_x().func_110434_K().func_110577_a(SMOKE_TEXTURE);
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
      int bx = MathHelper.func_76128_c(this.field_70165_t);
      int by = MathHelper.func_76128_c(this.field_70163_u);
      int bz = MathHelper.func_76128_c(this.field_70161_v);
      float skyContrib = this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, bx, by, bz) / 15.0F * this.field_70170_p.func_72971_b(1.0F);
      float blockContrib = this.field_70170_p.func_72972_b(EnumSkyBlock.Block, bx, by, bz) / 15.0F;
      float light = Math.max(Math.max(skyContrib, blockContrib), 0.05F);
      tessellator.func_78371_b(7);
      tessellator.func_78369_a(this.field_70552_h * light, this.field_70553_i * light, this.field_70551_j * light, this.field_82339_as);
      float size = 0.1F * this.field_70544_f;
      float x = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * partialTicks - field_70556_an);
      float y = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * partialTicks - field_70554_ao);
      float z = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * partialTicks - field_70555_ap);
      float u0 = this.imageIndex * 0.25F;
      float u1 = (this.imageIndex + 1) * 0.25F;
      tessellator.func_78374_a(x - par3 * size - par6 * size, y - par4 * size, z - par5 * size - par7 * size, u1, 1.0);
      tessellator.func_78374_a(x - par3 * size + par6 * size, y + par4 * size, z - par5 * size + par7 * size, u1, 0.0);
      tessellator.func_78374_a(x + par3 * size + par6 * size, y + par4 * size, z + par5 * size + par7 * size, u0, 0.0);
      tessellator.func_78374_a(x + par3 * size - par6 * size, y - par4 * size, z + par5 * size - par7 * size, u0, 1.0);
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
}
