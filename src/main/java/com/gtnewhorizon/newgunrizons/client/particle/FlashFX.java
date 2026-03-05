package com.gtnewhorizon.newgunrizons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class FlashFX extends EntityFX {

    private static final float ALPHA_FADE_PER_TICK = 0.8F;
    private static final float SCALE_GROWTH_PER_TICK = 1.1F;
    private static final float BASE_SCALE_MULTIPLIER = 1.4F;
    private static final float RENDER_SCALE = 0.1F;
    private static final int MAX_AGE_TICKS = 1;
    private static final float MIN_MOTION = 0.01F;
    private static final float GROUND_FRICTION = 0.7F;

    private static final ResourceLocation FLASH_TEXTURE = new ResourceLocation(
        "newgunrizons",
        "textures/effect/flashes.png");
    private static final int IMAGES_PER_ROW = 8;
    private static final float UV_WIDTH = 1.0F / IMAGES_PER_ROW;

    private static final float MIN_ALPHA_THRESHOLD = 1.0F / 255.0F;
    private static final int FULL_BRIGHTNESS = 200;

    private final int imageIndex;

    public FlashFX(World world, double positionX, double positionY, double positionZ, float scale, float alpha,
        float motionX, float motionY, float motionZ) {
        super(world, positionX, positionY, positionZ, 0.0D, 0.0D, 0.0D);

        if (motionX == 0.0F) motionX = MIN_MOTION;
        if (motionZ == 0.0F) motionZ = MIN_MOTION;
        if (motionY == 0.0F) motionY = MIN_MOTION;

        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;

        this.particleTextureIndexX = 0;
        this.particleTextureIndexY = 0;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.particleAlpha = alpha;
        this.particleScale *= BASE_SCALE_MULTIPLIER;
        this.particleScale *= scale;
        this.particleMaxAge = MAX_AGE_TICKS;
        this.imageIndex = this.rand.nextInt() % IMAGES_PER_ROW;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.particleAlpha *= ALPHA_FADE_PER_TICK;
        this.particleScale *= SCALE_GROWTH_PER_TICK;
        if (this.onGround) {
            this.motionX *= GROUND_FRICTION;
            this.motionZ *= GROUND_FRICTION;
        }
    }

    public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5,
        float par6, float par7) {
        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(FLASH_TEXTURE);
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_TEXTURE_BIT);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        // Additive blending so the flash actually brightens the scene
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glAlphaFunc(GL11.GL_GREATER, MIN_ALPHA_THRESHOLD);
        tessellator.startDrawing(GL11.GL_QUADS);
        tessellator.setBrightness(FULL_BRIGHTNESS);
        RenderHelper.disableStandardItemLighting();
        float size = RENDER_SCALE * this.particleScale;
        float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
        float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
        float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        float u0 = this.imageIndex * UV_WIDTH;
        float u1 = (this.imageIndex + 1) * UV_WIDTH;
        tessellator
            .addVertexWithUV(x - par3 * size - par6 * size, y - par4 * size, z - par5 * size - par7 * size, u1, 1.0F);
        tessellator
            .addVertexWithUV(x - par3 * size + par6 * size, y + par4 * size, z - par5 * size + par7 * size, u1, 0.0F);
        tessellator
            .addVertexWithUV(x + par3 * size + par6 * size, y + par4 * size, z + par5 * size + par7 * size, u0, 0.0F);
        tessellator
            .addVertexWithUV(x + par3 * size - par6 * size, y - par4 * size, z + par5 * size - par7 * size, u0, 1.0F);
        tessellator.draw();
        RenderHelper.enableStandardItemLighting();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public int getFXLayer() {
        return 3;
    }
}
