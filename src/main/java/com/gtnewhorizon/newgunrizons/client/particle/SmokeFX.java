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

      this.motionX = motionX;
      this.motionY = motionY;
      this.motionZ = motionZ;
      this.particleTextureIndexX = 0;
      this.particleTextureIndexY = 0;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.particleAlpha = 0.0F;
      this.particleScale *= scale;
      this.particleMaxAge = 50 + (int)(this.rand.nextFloat() * 30.0F);
      this.imageIndex = this.rand.nextInt() % 4;
   }

   public void func_70071_h_() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      this.motionY += 5.0E-4;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.6;
      this.motionY *= 1.0;
      this.motionZ *= 0.6;
      double alphaRadians = (Math.PI / 4) + Math.PI * this.particleAge / this.particleMaxAge;
      this.particleAlpha = 0.2F * (float)Math.sin(Math.min(alphaRadians, Math.PI));
      this.particleScale *= 1.0006F;
      if (this.onGround) {
         this.motionX *= 0.7;
         this.motionZ *= 0.7;
      }
   }

   public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
      int prevProgram = GL11.glGetInteger(35725);
      if (prevProgram != 0) {
         GL20.glUseProgram(0);
      }

      Minecraft.getMinecraft().getTextureManager().bindTexture(SMOKE_TEXTURE);
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
      int bx = MathHelper.floor_double(this.posX);
      int by = MathHelper.floor_double(this.posY);
      int bz = MathHelper.floor_double(this.posZ);
      float skyContrib = this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, bx, by, bz) / 15.0F * this.worldObj.getSunBrightness(1.0F);
      float blockContrib = this.worldObj.getSavedLightValue(EnumSkyBlock.Block, bx, by, bz) / 15.0F;
      float light = Math.max(Math.max(skyContrib, blockContrib), 0.05F);
      tessellator.startDrawing(7);
      tessellator.setColorRGBA_F(this.particleRed * light, this.particleGreen * light, this.particleBlue * light, this.particleAlpha);
      float size = 0.1F * this.particleScale;
      float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
      float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
      float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
      float u0 = this.imageIndex * 0.25F;
      float u1 = (this.imageIndex + 1) * 0.25F;
      tessellator.addVertexWithUV(x - par3 * size - par6 * size, y - par4 * size, z - par5 * size - par7 * size, u1, 1.0);
      tessellator.addVertexWithUV(x - par3 * size + par6 * size, y + par4 * size, z - par5 * size + par7 * size, u1, 0.0);
      tessellator.addVertexWithUV(x + par3 * size + par6 * size, y + par4 * size, z + par5 * size + par7 * size, u0, 0.0);
      tessellator.addVertexWithUV(x + par3 * size - par6 * size, y - par4 * size, z + par5 * size - par7 * size, u0, 1.0);
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
