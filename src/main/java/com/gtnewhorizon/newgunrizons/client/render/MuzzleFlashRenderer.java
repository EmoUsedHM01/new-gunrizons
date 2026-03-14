package com.gtnewhorizon.newgunrizons.client.render;

import java.nio.FloatBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.model.JsonModel;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Renders muzzle flash at the "muzzle_flash" bone position (child of barrel),
 * oriented to face the camera (billboard). Falls back to hardcoded offsets
 * if the bone doesn't exist in the model.
 */
@SideOnly(Side.CLIENT)
public class MuzzleFlashRenderer {

    public static final String BONE_MUZZLE_FLASH = "muzzle_flash";

    private static final ResourceLocation FLASH_TEXTURE = new ResourceLocation(
        NewGunrizonsMod.MODID,
        "textures/effect/flashes.png");

    private static final int IMAGES_PER_ROW = 8;
    private static final float UV_WIDTH = 1.0F / IMAGES_PER_ROW;
    private static final long FLASH_DURATION_MS = 60L;
    private static final float RAD_TO_DEG = 180F / (float) Math.PI;

    /** Flash quad half-size in model space. */
    private static final float FLASH_SIZE = 3.0F;

    private static final java.util.Random rand = new java.util.Random();
    private static final FloatBuffer modelviewBuf = BufferUtils.createFloatBuffer(16);

    /**
     * Renders a muzzle flash if the weapon just fired.
     * If the weapon model has a "muzzle_flash" bone, renders at that bone's position.
     * Otherwise falls back to the weapon's configured flash offsets.
     */
    public static void renderIfFiring(RenderContext renderContext, JsonModel weaponModel, float renderScale) {
        ItemWeaponInstance weaponInstance = renderContext.getWeaponInstance();
        if (weaponInstance == null) return;

        ItemWeapon weapon = weaponInstance.getWeapon();
        if (weapon.getFlashIntensity() <= 0.0F) return;
        if (WeaponAttachmentAspect.INSTANCE.isSilencerOn(weaponInstance)) return;

        long elapsed = System.currentTimeMillis() - weaponInstance.getLastFireTimestamp();
        if (elapsed < 0 || elapsed > FLASH_DURATION_MS) return;

        float alpha = weapon.getFlashIntensity() * (1.0F - (float) elapsed / FLASH_DURATION_MS);
        if (alpha <= 0.0F) return;

        float scale = weapon.getFlashScale().get();
        int imageIndex = Math.abs(rand.nextInt()) % IMAGES_PER_ROW;

        if (weaponModel != null && weaponModel.getBone(BONE_MUZZLE_FLASH) != null) {
            renderFlashAtBone(weaponModel, renderScale, alpha, scale, imageIndex);
        } else {
            // Fallback: hardcoded offsets
            float ox = weapon.getFlashOffsetX().get();
            float oy = weapon.getFlashOffsetY().get();
            float oz = weapon.getFlashOffsetZ().get();
            renderFlashAtOffset(alpha, scale, imageIndex, ox, oy, oz);
        }
    }

    /**
     * Renders the flash quad at the muzzle_flash bone position.
     * Walks the bone hierarchy: receiver -> barrel -> muzzle_flash.
     */
    private static void renderFlashAtBone(JsonModel model, float renderScale,
        float alpha, float flashScale, int imageIndex) {

        int prevProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        if (prevProgram != 0) GL20.glUseProgram(0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(FLASH_TEXTURE);

        GL11.glPushMatrix();
        GL11.glPushAttrib(
            GL11.GL_TEXTURE_BIT | GL11.GL_DEPTH_BUFFER_BIT
                | GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT
                | GL11.GL_CURRENT_BIT | GL11.GL_POLYGON_BIT);

        if (prevProgram != 0) GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);

        // Walk bone hierarchy: receiver -> barrel -> muzzle_flash
        applyBoneChain(model, renderScale, "receiver", "barrel", BONE_MUZZLE_FLASH);

        // Billboard: cancel rotation, keep position and scale
        modelviewBuf.clear();
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelviewBuf);
        float tx = modelviewBuf.get(12);
        float ty = modelviewBuf.get(13);
        float tz = modelviewBuf.get(14);
        float sx = columnLength(modelviewBuf, 0);
        float sy = columnLength(modelviewBuf, 4);
        GL11.glLoadIdentity();
        GL11.glTranslatef(tx, ty, tz);
        GL11.glScalef(sx, sy, 1.0F);

        drawFlashQuad(alpha, flashScale, imageIndex);

        GL11.glPopAttrib();
        GL11.glPopMatrix();

        if (prevProgram != 0) GL20.glUseProgram(prevProgram);
    }

    /**
     * Fallback: renders flash at hardcoded offset position.
     */
    private static void renderFlashAtOffset(float alpha, float flashScale, int imageIndex,
        float offsetX, float offsetY, float offsetZ) {

        int prevProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        if (prevProgram != 0) GL20.glUseProgram(0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(FLASH_TEXTURE);

        GL11.glPushMatrix();
        GL11.glPushAttrib(
            GL11.GL_TEXTURE_BIT | GL11.GL_DEPTH_BUFFER_BIT
                | GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT
                | GL11.GL_CURRENT_BIT | GL11.GL_POLYGON_BIT);

        if (prevProgram != 0) GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);

        GL11.glTranslatef(0.1F + offsetX, -0.9F + offsetY, -4.7F + offsetZ);

        modelviewBuf.clear();
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelviewBuf);
        float tx = modelviewBuf.get(12);
        float ty = modelviewBuf.get(13);
        float tz = modelviewBuf.get(14);
        float sx = columnLength(modelviewBuf, 0);
        float sy = columnLength(modelviewBuf, 4);
        GL11.glLoadIdentity();
        GL11.glTranslatef(tx, ty, tz);
        GL11.glScalef(sx, sy, 1.0F);

        drawFlashQuad(alpha, flashScale, imageIndex);

        GL11.glPopAttrib();
        GL11.glPopMatrix();

        if (prevProgram != 0) GL20.glUseProgram(prevProgram);
    }

    private static void applyBoneChain(JsonModel model, float renderScale, String... boneNames) {
        for (String boneName : boneNames) {
            ModelRenderer bone = model.getBone(boneName);
            if (bone == null) continue;
            GL11.glTranslatef(
                bone.rotationPointX * renderScale,
                bone.rotationPointY * renderScale,
                bone.rotationPointZ * renderScale);
            if (bone.rotateAngleZ != 0) GL11.glRotatef(bone.rotateAngleZ * RAD_TO_DEG, 0, 0, 1);
            if (bone.rotateAngleY != 0) GL11.glRotatef(bone.rotateAngleY * RAD_TO_DEG, 0, 1, 0);
            if (bone.rotateAngleX != 0) GL11.glRotatef(bone.rotateAngleX * RAD_TO_DEG, 1, 0, 0);
        }
    }

    private static void drawFlashQuad(float alpha, float flashScale, int imageIndex) {
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        float size = FLASH_SIZE * flashScale;
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
    }

    private static float columnLength(FloatBuffer buf, int col) {
        return (float) Math.sqrt(
            buf.get(col) * buf.get(col)
                + buf.get(col + 1) * buf.get(col + 1)
                + buf.get(col + 2) * buf.get(col + 2));
    }

    /** No-op placeholder. */
    public static void renderDebugFlash() {}
}
