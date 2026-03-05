package com.gtnewhorizon.newgunrizons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class ExplosionParticleFX extends EntityFX {

    private static final float RENDER_SCALE = 0.1F;
    private static final int BASE_MAX_AGE = 50;
    private static final int MAX_AGE_VARIANCE = 30;
    private static final float MIN_MOTION_X = 1.0F;
    private static final double BUOYANCY = 0.004;
    private static final double AIR_DRAG = 0.99;
    private static final double GRAVITY = 0.07;
    private static final double GROUND_FRICTION = 0.7;
    private static final int FULL_OPACITY_TICKS = 9;

    private static final ResourceLocation TEXTURE = new ResourceLocation(
        "newgunrizons",
        "textures/effect/explosion-particles.png");
    private static final int COLUMN_COUNT = 5;
    private static final int ROW_COUNT = 5;
    private static final int TOTAL_IMAGES = COLUMN_COUNT * ROW_COUNT;
    private static final float COLUMN_WIDTH = 1.0F / COLUMN_COUNT;
    private static final float ROW_HEIGHT = 1.0F / ROW_COUNT;

    private static final float MIN_ALPHA_THRESHOLD = 1.0F / 255.0F;
    private static final int FULL_BRIGHTNESS = 200;

    private final int imageIndex;

    public ExplosionParticleFX(World world, double positionX, double positionY, double positionZ, float scale,
        double motionX, double motionY, double motionZ, int particleMaxAge) {
        super(world, positionX, positionY, positionZ, 0.0D, 0.0D, 0.0D);

        if (motionX == 0.0D) {
            motionX = MIN_MOTION_X;
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
        this.particleMaxAge = particleMaxAge == 0 ? BASE_MAX_AGE + (int) (this.rand.nextFloat() * MAX_AGE_VARIANCE)
            : particleMaxAge;
        this.imageIndex = this.rand.nextInt(TOTAL_IMAGES);
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }

        this.motionY += BUOYANCY;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= AIR_DRAG;
        this.motionY *= AIR_DRAG;
        this.motionY -= GRAVITY;
        this.motionZ *= AIR_DRAG;

        if (this.onGround) {
            this.motionX *= GROUND_FRICTION;
            this.motionZ *= GROUND_FRICTION;
        }

        this.particleAlpha = this.particleAge < FULL_OPACITY_TICKS ? 1.0F
            : (float) (1 + FULL_OPACITY_TICKS / this.particleMaxAge - this.particleAge / this.particleMaxAge);
    }

    public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5,
        float par6, float par7) {
        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(TEXTURE);
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_TEXTURE_BIT);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, MIN_ALPHA_THRESHOLD);
        RenderHelper.disableStandardItemLighting();
        tessellator.startDrawing(GL11.GL_QUADS);
        float size = RENDER_SCALE * this.particleScale;
        float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
        float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
        float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        tessellator.setBrightness(FULL_BRIGHTNESS);
        int row = Math.floorDiv(this.imageIndex, ROW_COUNT);
        int col = this.imageIndex % COLUMN_COUNT;
        float u0 = col * COLUMN_WIDTH;
        float u1 = (col + 1) * COLUMN_WIDTH;
        float v0 = row * ROW_HEIGHT;
        float v1 = (row + 1) * ROW_HEIGHT;
        tessellator
            .addVertexWithUV(x - par3 * size - par6 * size, y - par4 * size, z - par5 * size - par7 * size, u1, v1);
        tessellator
            .addVertexWithUV(x - par3 * size + par6 * size, y + par4 * size, z - par5 * size + par7 * size, u1, v0);
        tessellator
            .addVertexWithUV(x + par3 * size + par6 * size, y + par4 * size, z + par5 * size + par7 * size, u0, v0);
        tessellator
            .addVertexWithUV(x + par3 * size - par6 * size, y - par4 * size, z + par5 * size - par7 * size, u0, v1);
        tessellator.draw();
        RenderHelper.enableStandardItemLighting();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public int getFXLayer() {
        return 3;
    }
}
