package com.gtnewhorizon.newgunrizons.client.particle;

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
 * Large smoke particle used for explosions and smoke grenades.
 * <p>
 * Unlike {@link SmokeFX} (which uses a fixed alpha curve), this particle's scale
 * and alpha evolution are controlled by a {@link Behavior} enum that selects
 * different growth/fade functions for explosions vs. smoke grenades.
 * The texture is a 4x4 sprite sheet (16 variants) with a per-instance random selection.
 * <p>
 * Uses FXLayer 3 (fully custom rendering) and <b>bypasses any active shader program</b>
 * (e.g. Angelica/Iris) to render via the fixed-function pipeline. This prevents the
 * shader from altering alpha handling and avoids writing particle data into auxiliary
 * G-buffer MRT attachments (normals, specular) that would corrupt screen-space
 * post-processing effects like rain puddles.
 *
 * @see ParticleManager#spawnExplosionSmoke
 */
public class ExplosionSmokeFX extends EntityFX {

    // --- Behavior ---

    private static final int BASE_MAX_AGE = 50;
    private static final int MAX_AGE_VARIANCE = 30;
    private static final double ALPHA_PHASE_OFFSET = Math.PI / 4.0;
    private static final float PEAK_ALPHA = 0.3F;

    // --- Physics ---

    private static final float MIN_MOTION_X = 1.0F;
    private static final double BUOYANCY = 1.0E-5;
    private static final double HORIZONTAL_DRAG = 0.8;
    private static final double VERTICAL_DRAG = 1.0;
    private static final double GROUND_FRICTION = 0.7;

    // --- Rendering ---

    /** Sprite sheet layout: 4 columns x 4 rows = 16 variants. */
    private static final int COLUMN_COUNT = 4;
    private static final int ROW_COUNT = 4;
    private static final int TOTAL_IMAGES = COLUMN_COUNT * ROW_COUNT;
    private static final float COLUMN_WIDTH = 1.0F / COLUMN_COUNT;
    private static final float ROW_HEIGHT = 1.0F / ROW_COUNT;
    private static final float RENDER_SCALE = 0.1F;
    private static final float MIN_ALPHA_THRESHOLD = 1.0F / 255.0F;
    private static final int FULL_BRIGHTNESS = 200;

    // --- Scale functions ---

    /** Explosion smoke: rapid initial expansion that slows as the cloud grows. */
    private static final TriFunction<Float, Integer, Integer, Float> EXPLOSION_SCALE_FUNCTION = (currentScale, ticks,
        maxTicks) -> {
        if (currentScale > 25.0F) return currentScale * 1.0008F;
        if (currentScale > 20.0F) return currentScale * 1.002F;
        if (currentScale > 15.0F) return currentScale * 1.004F;
        if (currentScale > 10.0F) return currentScale * 1.05F;
        return currentScale * 3.0F;
    };

    /** Smoke grenade: similar tiered expansion but slower initial growth. */
    private static final TriFunction<Float, Integer, Integer, Float> SMOKE_GRENADE_SCALE_FUNCTION = (currentScale,
        ticks, maxTicks) -> {
        if (currentScale > 25.0F) return currentScale * 1.0008F;
        if (currentScale > 20.0F) return currentScale * 1.002F;
        if (currentScale > 15.0F) return currentScale * 1.004F;
        if (currentScale > 5.0F) return currentScale * 1.05F;
        return currentScale * 2.0F;
    };

    // --- Alpha functions (identical for both behaviors — sine curve with PI/4 offset) ---

    private static final TriFunction<Float, Integer, Integer, Float> EXPLOSION_ALPHA_FUNCTION = (currentAlpha, ticks,
        maxTicks) -> {
        double alphaRadians = ALPHA_PHASE_OFFSET + Math.PI * (double) ticks / (double) maxTicks;
        return PEAK_ALPHA * (float) Math.sin(Math.min(alphaRadians, Math.PI));
    };

    private static final TriFunction<Float, Integer, Integer, Float> SMOKE_GRENADE_ALPHA_FUNCTION = (currentAlpha,
        ticks, maxTicks) -> {
        double alphaRadians = ALPHA_PHASE_OFFSET + Math.PI * (double) ticks / (double) maxTicks;
        return PEAK_ALPHA * (float) Math.sin(Math.min(alphaRadians, Math.PI));
    };

    /** Randomly chosen sprite variant (0..15) from the 4x4 texture atlas. */
    private final int imageIndex;
    private final Behavior behavior;
    private final ResourceLocation smokeTexture;

    public ExplosionSmokeFX(World world, double positionX, double positionY, double positionZ, float scale,
        float motionX, float motionY, float motionZ, int particleMaxAge, Behavior behavior,
        ResourceLocation smokeTexture) {
        super(world, positionX, positionY, positionZ, 0.0D, 0.0D, 0.0D);

        this.smokeTexture = smokeTexture;

        if (motionX == 0.0F) {
            motionX = MIN_MOTION_X;
        }
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;

        this.behavior = behavior;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.particleAlpha = 0.0F;
        this.particleScale *= scale;
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
        this.motionX *= HORIZONTAL_DRAG;
        this.motionY *= VERTICAL_DRAG;
        this.motionZ *= HORIZONTAL_DRAG;

        this.particleAlpha = this.behavior.alphaUpdateFunction
            .apply(this.particleAlpha, this.particleAge, this.particleMaxAge);
        this.particleScale = this.behavior.scaleUpdateFunction
            .apply(this.particleScale, this.particleAge, this.particleMaxAge);

        if (this.onGround) {
            this.motionX *= GROUND_FRICTION;
            this.motionZ *= GROUND_FRICTION;
        }
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
            .bindTexture(this.smokeTexture);
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

        // Build camera-facing quad from 4x4 sprite sheet
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

    /** Selects scale/alpha evolution functions for different smoke effects. */
    public enum Behavior {

        EXPLOSION(EXPLOSION_SCALE_FUNCTION, EXPLOSION_ALPHA_FUNCTION),
        SMOKE_GRENADE(SMOKE_GRENADE_SCALE_FUNCTION, SMOKE_GRENADE_ALPHA_FUNCTION);

        private final TriFunction<Float, Integer, Integer, Float> scaleUpdateFunction;
        private final TriFunction<Float, Integer, Integer, Float> alphaUpdateFunction;

        Behavior(TriFunction<Float, Integer, Integer, Float> scaleUpdateFunction,
            TriFunction<Float, Integer, Integer, Float> alphaUpdateFunction) {
            this.scaleUpdateFunction = scaleUpdateFunction;
            this.alphaUpdateFunction = alphaUpdateFunction;
        }
    }

    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {

        R apply(T t, U u, V v);
    }
}
