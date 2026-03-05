package com.gtnewhorizon.newgunrizons.client.particle;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * Fiery debris particle used for grenade and weapon explosions.
 * <p>
 * Unlike the smoke particles, these are fully opaque for the first few ticks
 * ({@link #FULL_OPACITY_TICKS}), then linearly fade out. They are affected by
 * gravity (fall back down after initial upward blast) and air drag.
 * The texture is a 5x5 sprite sheet (25 variants) with a per-instance random selection.
 * <p>
 * Uses FXLayer 3 (fully custom rendering) and <b>bypasses any active shader program</b>
 * (e.g. Angelica/Iris) to render via the fixed-function pipeline. This prevents the
 * shader from altering alpha handling and avoids writing particle data into auxiliary
 * G-buffer MRT attachments (normals, specular) that would corrupt screen-space
 * post-processing effects like rain puddles.
 *
 * @see ParticleManager#spawnExplosionParticle
 */
public class ExplosionParticleFX extends EntityFX {

    // --- Behavior ---

    private static final int BASE_MAX_AGE = 50;
    private static final int MAX_AGE_VARIANCE = 30;
    /** Number of ticks the particle stays fully opaque before fading. */
    private static final int FULL_OPACITY_TICKS = 9;

    // --- Physics ---

    private static final float MIN_MOTION_X = 1.0F;
    private static final double BUOYANCY = 0.004;
    private static final double AIR_DRAG = 0.99;
    private static final double GRAVITY = 0.07;
    private static final double GROUND_FRICTION = 0.7;

    // --- Rendering ---

    private static final ResourceLocation TEXTURE = new ResourceLocation(
        NewGunrizonsMod.MODID,
        "textures/effect/explosion-particles.png");
    /** Sprite sheet layout: 5 columns x 5 rows = 25 variants. */
    private static final int COLUMN_COUNT = 5;
    private static final int ROW_COUNT = 5;
    private static final int TOTAL_IMAGES = COLUMN_COUNT * ROW_COUNT;
    private static final float COLUMN_WIDTH = 1.0F / COLUMN_COUNT;
    private static final float ROW_HEIGHT = 1.0F / ROW_COUNT;
    private static final float RENDER_SCALE = 0.1F;
    private static final float MIN_ALPHA_THRESHOLD = 1.0F / 255.0F;
    private static final int FULL_BRIGHTNESS = 200;

    /** Randomly chosen sprite variant (0..24) from the 5x5 texture atlas. */
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

    @Override
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

        // Fully opaque for the first few ticks, then linear fade-out.
        this.particleAlpha = this.particleAge < FULL_OPACITY_TICKS ? 1.0F
            : (float) (1 + FULL_OPACITY_TICKS / this.particleMaxAge - this.particleAge / this.particleMaxAge);
    }

    /**
     * Renders this particle as a camera-facing textured quad.
     * <p>
     * <b>Shader bypass:</b> If a GLSL shader program is active (e.g. Angelica), it is
     * temporarily unbound so the particle renders via OpenGL fixed-function. Draw buffer
     * output is restricted to {@code GL_COLOR_ATTACHMENT0} to avoid writing into the
     * shader's auxiliary G-buffer attachments. All modified GL state is saved via
     * {@code glPushAttrib} and restored via {@code glPopAttrib}.
     */
    @Override
    public void renderParticle(Tessellator tessellator, float partialTicks, float par3, float par4, float par5,
        float par6, float par7) {

        // --- Bypass active shader program (Angelica/Iris compatibility) ---
        int prevProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        if (prevProgram != 0) {
            GL20.glUseProgram(0);
        }

        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(TEXTURE);
        GL11.glPushMatrix();
        GL11.glPushAttrib(
            GL11.GL_TEXTURE_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT
                | GL11.GL_CURRENT_BIT);

        // Restrict to main color attachment only — see SmokeFX for full explanation.
        if (prevProgram != 0) {
            GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, MIN_ALPHA_THRESHOLD);
        RenderHelper.disableStandardItemLighting();

        // Build camera-facing quad from 5x5 sprite sheet
        tessellator.startDrawing(GL11.GL_QUADS);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        tessellator.setBrightness(FULL_BRIGHTNESS);

        float size = RENDER_SCALE * this.particleScale;
        float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
        float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
        float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);

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

        // --- Restore shader program ---
        if (prevProgram != 0) {
            GL20.glUseProgram(prevProgram);
        }
    }

    @Override
    public int getFXLayer() {
        return 3;
    }
}
