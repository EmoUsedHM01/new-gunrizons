package com.gtnewhorizon.newgunrizons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ExplosionParticleFX extends EntityFX {
   private static final int BASE_MAX_AGE = 50;
   private static final int MAX_AGE_VARIANCE = 30;
   private static final int FULL_OPACITY_TICKS = 9;
   private static final float MIN_MOTION_X = 1.0F;
   private static final double BUOYANCY = 0.004;
   private static final double AIR_DRAG = 0.99;
   private static final double GRAVITY = 0.07;
   private static final double GROUND_FRICTION = 0.7;
   private static final ResourceLocation TEXTURE = new ResourceLocation("newgunrizons", "textures/effect/explosion-particles.png");
   private static final int COLUMN_COUNT = 5;
   private static final int ROW_COUNT = 5;
   private static final int TOTAL_IMAGES = 25;
   private static final float COLUMN_WIDTH = 0.2F;
   private static final float ROW_HEIGHT = 0.2F;
   private static final float RENDER_SCALE = 0.1F;
   private static final float MIN_ALPHA_THRESHOLD = 0.003921569F;
   private static final int FULL_BRIGHTNESS = 200;
   private final int imageIndex;

   public ExplosionParticleFX(
      World world, double positionX, double positionY, double positionZ, float scale, double motionX, double motionY, double motionZ, int particleMaxAge
   ) {
      super(world, positionX, positionY, positionZ, 0.0, 0.0, 0.0);
      if (motionX == 0.0) {
         motionX = 1.0;
      }

      this.motionX = motionX;
      this.motionY = motionY;
      this.motionZ = motionZ;
      this.particleTextureIndexX = 0;
      this.particleTextureIndexY = 0;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.particleAlpha = 1.0F;
      this.particleScale = scale;
      this.particleMaxAge = particleMaxAge == 0 ? 50 + (int)(this.rand.nextFloat() * 30.0F) : particleMaxAge;
      this.imageIndex = this.rand.nextInt(25);
   }

   public void func_70071_h_() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      this.motionY += 0.004;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.99;
      this.motionY *= 0.99;
      this.motionY -= 0.07;
      this.motionZ *= 0.99;
      if (this.onGround) {
         this.motionX *= 0.7;
         this.motionZ *= 0.7;
      }

      this.particleAlpha = this.particleAge < 9 ? 1.0F : 1 + 9 / this.particleMaxAge - this.particleAge / this.particleMaxAge;
   }

   public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
      int prevProgram = GL11.glGetInteger(35725);
      if (prevProgram != 0) {
         GL20.glUseProgram(0);
      }

      Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
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
      RenderHelper.disableStandardItemLighting();
      tessellator.startDrawing(7);
      tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
      tessellator.setBrightness(200);
      float size = 0.1F * this.particleScale;
      float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
      float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
      float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
      int row = Math.floorDiv(this.imageIndex, 5);
      int col = this.imageIndex % 5;
      float u0 = col * 0.2F;
      float u1 = (col + 1) * 0.2F;
      float v0 = row * 0.2F;
      float v1 = (row + 1) * 0.2F;
      tessellator.addVertexWithUV(x - par3 * size - par6 * size, y - par4 * size, z - par5 * size - par7 * size, u1, v1);
      tessellator.addVertexWithUV(x - par3 * size + par6 * size, y + par4 * size, z - par5 * size + par7 * size, u1, v0);
      tessellator.addVertexWithUV(x + par3 * size + par6 * size, y + par4 * size, z + par5 * size + par7 * size, u0, v0);
      tessellator.addVertexWithUV(x + par3 * size - par6 * size, y - par4 * size, z + par5 * size - par7 * size, u0, v1);
      tessellator.draw();
      RenderHelper.enableStandardItemLighting();
      GL11.glPopAttrib();
      GL11.glPopMatrix();
      if (prevProgram != 0) {
         GL20.glUseProgram(prevProgram);
      }
   }

   public int getFXLayer() {
      return 3;
   }
}
