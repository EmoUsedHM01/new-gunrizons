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
 * Muzzle flash particle. Spawned at the barrel simultaneously with {@link SmokeFX}.
 * <p>
 * Very short-lived (1 tick). Uses <b>additive blending</b> ({@code SRC_ALPHA, ONE})
 * so the flash brightens the scene rather than obscuring it. Alpha fades by 80%
 * per tick while the quad scales up by 10%, producing a brief expanding glow.
 * <p>
 * Uses FXLayer 3 (fully custom rendering) and <b>bypasses any active shader program</b>
 * (e.g. Angelica/Iris) to render via the fixed-function pipeline. This prevents the
 * shader from altering the additive blend output and avoids writing particle data
 * into auxiliary G-buffer MRT attachments (normals, specular) that would corrupt
 * screen-space post-processing effects.
 *
 * @see ParticleManager#spawnFlashParticle
 */
public class FlashFX extends EntityFX {

    // --- Behavior ---

    private static final float ALPHA_FADE_PER_TICK = 0.8F;
    private static final float SCALE_GROWTH_PER_TICK = 1.1F;
    private static final float BASE_SCALE_MULTIPLIER = 1.4F;
    private static final int MAX_AGE_TICKS = 1;

    // --- Physics ---

    private static final float MIN_MOTION = 0.01F;
    private static final float GROUND_FRICTION = 0.7F;

    // --- Rendering ---

    private static final ResourceLocation FLASH_TEXTURE = new ResourceLocation(
        NewGunrizonsMod.MODID,
        "textures/effect/flashes.png");
    /** Sprite sheet: 8 flash variants in a single row. */
    private static final int IMAGES_PER_ROW = 8;
    private static final float UV_WIDTH = 1.0F / IMAGES_PER_ROW;
    private static final float RENDER_SCALE = 0.1F;
    private static final float MIN_ALPHA_THRESHOLD = 1.0F / 255.0F;
    private static final int FULL_BRIGHTNESS = 200;

    /** Randomly chosen sprite variant (0..7) from the texture atlas. */
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

    @Override
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

    /**
     * Renders this particle as a camera-facing textured quad with additive blending.
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
            .bindTexture(FLASH_TEXTURE);
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
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // Additive: flash brightens the scene
        GL11.glAlphaFunc(GL11.GL_GREATER, MIN_ALPHA_THRESHOLD);
        RenderHelper.disableStandardItemLighting();

        // Build camera-facing quad
        tessellator.startDrawing(GL11.GL_QUADS);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        tessellator.setBrightness(FULL_BRIGHTNESS);

        float size = RENDER_SCALE * this.particleScale;
        float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
        float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
        float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);

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
