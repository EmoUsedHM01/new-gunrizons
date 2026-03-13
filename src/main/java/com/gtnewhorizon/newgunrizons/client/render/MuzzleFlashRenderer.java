package com.gtnewhorizon.newgunrizons.client.render;

import java.nio.FloatBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.debug.PositionDebugger;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Renders muzzle flash in the weapon's model space during the first-person render pass.
 * <p>
 * The flash quad is positioned at the barrel tip using the weapon's GL transform stack,
 * then oriented to face the camera. This keeps it locked to the barrel regardless of
 * pitch, recoil animation, or camera angle.
 * <p>
 * Bypasses any active shader program (Angelica/Iris) to render via fixed-function additive
 * blending, and restricts draw output to {@code GL_COLOR_ATTACHMENT0} to avoid writing
 * into auxiliary G-buffer MRT attachments.
 */
@SideOnly(Side.CLIENT)
public class MuzzleFlashRenderer {

    private static final ResourceLocation FLASH_TEXTURE = new ResourceLocation(
        NewGunrizonsMod.MODID,
        "textures/effect/flashes.png");

    private static final int IMAGES_PER_ROW = 8;
    private static final float UV_WIDTH = 1.0F / IMAGES_PER_ROW;

    /** How long the flash stays visible after firing (milliseconds). */
    private static final long FLASH_DURATION_MS = 60L;

    /** Default barrel tip offset in model space (X, Y, Z). */
    private static final float BARREL_X = 0.1F;
    private static final float BARREL_Y = -0.9F;
    private static final float BARREL_Z = -4.7F;

    /** Flash quad half-size in model space. */
    private static final float FLASH_SIZE = 3.0F;

    private static final java.util.Random rand = new java.util.Random();
    private static final FloatBuffer modelviewBuf = BufferUtils.createFloatBuffer(16);

    /**
     * Renders a muzzle flash if the weapon just fired.
     * Must be called while the weapon's model-space GL matrix is active.
     */
    public static void renderIfFiring(RenderContext renderContext) {
        ItemWeaponInstance weaponInstance = renderContext.getWeaponInstance();
        if (weaponInstance == null) {
            return;
        }

        ItemWeapon weapon = weaponInstance.getWeapon();
        if (weapon.getFlashIntensity() <= 0.0F) {
            return;
        }

        if (WeaponAttachmentAspect.INSTANCE.isSilencerOn(weaponInstance)) {
            return;
        }

        long elapsed = System.currentTimeMillis() - weaponInstance.getLastFireTimestamp();
        if (elapsed < 0 || elapsed > FLASH_DURATION_MS) {
            return;
        }

        float alpha = weapon.getFlashIntensity() * (1.0F - (float) elapsed / FLASH_DURATION_MS);
        if (alpha <= 0.0F) {
            return;
        }

        float scale = weapon.getFlashScale()
            .get();
        float weaponOffsetX = weapon.getFlashOffsetX()
            .get();
        float weaponOffsetY = weapon.getFlashOffsetY()
            .get();
        float weaponOffsetZ = weapon.getFlashOffsetZ()
            .get();
        int imageIndex = Math.abs(rand.nextInt()) % IMAGES_PER_ROW;
        renderFlashQuad(alpha, scale, imageIndex, weaponOffsetX, weaponOffsetY, weaponOffsetZ);
    }

    /**
     * Renders a static muzzle flash for position debugging.
     * Uses the MUZZLE_FLASH debug state's translate as flash offset and scale as flash scale.
     */
    public static void renderDebugFlash() {
        if (!PositionDebugger.isActive()
            || PositionDebugger.getCurrentMode() != PositionDebugger.Mode.MUZZLE_FLASH) {
            return;
        }
        PositionDebugger.TransformState state = PositionDebugger.getState(PositionDebugger.Mode.MUZZLE_FLASH);
        renderFlashQuad(1.0F, state.scale, 0, state.translateX, state.translateY, state.translateZ);
    }

    private static void renderFlashQuad(float alpha, float scale, int imageIndex, float weaponOffsetX,
        float weaponOffsetY, float weaponOffsetZ) {
        // Bypass active shader program (Angelica/Iris compatibility)
        int prevProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        if (prevProgram != 0) {
            GL20.glUseProgram(0);
        }

        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(FLASH_TEXTURE);

        GL11.glPushMatrix();
        GL11.glPushAttrib(
            GL11.GL_TEXTURE_BIT | GL11.GL_DEPTH_BUFFER_BIT
                | GL11.GL_ENABLE_BIT
                | GL11.GL_COLOR_BUFFER_BIT
                | GL11.GL_CURRENT_BIT
                | GL11.GL_POLYGON_BIT);

        // Restrict to main color attachment only — prevents writing particle RGBA
        // into shader G-buffer auxiliary MRT attachments (normals, specular).
        if (prevProgram != 0) {
            GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
        }

        // Translate to barrel tip in model space (default + per-weapon correction)
        float bx = BARREL_X + weaponOffsetX;
        float by = BARREL_Y + weaponOffsetY;
        float bz = BARREL_Z + weaponOffsetZ;
        GL11.glTranslatef(bx, by, bz);

        // Extract the current modelview matrix and cancel its rotation so the quad
        // always faces the camera (billboard). We keep the translation (position)
        // but replace the upper-left 3x3 with identity.
        modelviewBuf.clear();
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelviewBuf);
        float tx = modelviewBuf.get(12);
        float ty = modelviewBuf.get(13);
        float tz = modelviewBuf.get(14);
        // Extract scale from each column to preserve it
        float sx = (float) Math.sqrt(
            modelviewBuf.get(0) * modelviewBuf.get(0) + modelviewBuf.get(1) * modelviewBuf.get(1)
                + modelviewBuf.get(2) * modelviewBuf.get(2));
        float sy = (float) Math.sqrt(
            modelviewBuf.get(4) * modelviewBuf.get(4) + modelviewBuf.get(5) * modelviewBuf.get(5)
                + modelviewBuf.get(6) * modelviewBuf.get(6));
        GL11.glLoadIdentity();
        GL11.glTranslatef(tx, ty, tz);
        GL11.glScalef(sx, sy, 1.0F);

        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        float size = FLASH_SIZE * scale;
        float u0 = imageIndex * UV_WIDTH;
        float u1 = (imageIndex + 1) * UV_WIDTH;

        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.setColorRGBA_F(1.0F, 1.0F, 1.0F, alpha);
        tess.setBrightness(240);
        tess.addVertexWithUV(-size, -size, 0.0, u0, 1.0);
        tess.addVertexWithUV(-size, size, 0.0, u0, 0.0);
        tess.addVertexWithUV(size, size, 0.0, u1, 0.0);
        tess.addVertexWithUV(size, -size, 0.0, u1, 1.0);
        tess.draw();

        GL11.glPopAttrib();
        GL11.glPopMatrix();

        // Restore shader program
        if (prevProgram != 0) {
            GL20.glUseProgram(prevProgram);
        }
    }
}
