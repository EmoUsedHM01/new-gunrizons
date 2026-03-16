package com.gtnewhorizon.newgunrizons.client.render;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL20;

/**
 * Renders bullet tracers as a procedural cylinder with a GLSL glow shader.
 * <p>
 * The cylinder geometry naturally produces the correct silhouette from every
 * viewing angle: an elongated streak from the side and a circular dot from
 * behind. The fragment shader adds a hot-core-to-color falloff based on the
 * surface normal vs. view direction.
 * <p>
 * Rendering uses additive blending with back-face culling disabled so both
 * the near and far sides of the cylinder contribute, creating a brighter
 * center where they overlap — a cheap volumetric glow.
 * <p>
 * Angelica/Iris compatibility: any active shader program is saved and
 * restored around the draw call, and draw buffer output is restricted to
 * {@code GL_COLOR_ATTACHMENT0} to avoid writing into G-buffer auxiliaries.
 */
public class TracerRenderer {

    private static final int SEGMENTS = 8;

    private static int displayList = -1;
    private static int whiteTexture = -1;
    private static boolean initialized;

    /** Must be called from the render thread on first use. */
    private static void ensureInitialized() {
        if (initialized) return;
        initialized = true;
        buildCylinder();
        buildWhiteTexture();
    }

    // ---- Geometry (unit cylinder: length 1 along +X, radius 1) ----

    private static void buildCylinder() {
        displayList = GL11.glGenLists(1);
        GL11.glNewList(displayList, GL11.GL_COMPILE);

        // --- Cylinder body ---
        GL11.glBegin(GL11.GL_QUADS);
        for (int i = 0; i < SEGMENTS; i++) {
            double a0 = 2.0 * Math.PI * i / SEGMENTS;
            double a1 = 2.0 * Math.PI * (i + 1) / SEGMENTS;

            float y0 = (float) Math.cos(a0);
            float z0 = (float) Math.sin(a0);
            float y1 = (float) Math.cos(a1);
            float z1 = (float) Math.sin(a1);
            float v0 = (float) i / SEGMENTS;
            float v1 = (float) (i + 1) / SEGMENTS;

            GL11.glTexCoord2f(0, v0);
            GL11.glNormal3f(0, y0, z0);
            GL11.glVertex3f(0, y0, z0);

            GL11.glTexCoord2f(0, v1);
            GL11.glNormal3f(0, y1, z1);
            GL11.glVertex3f(0, y1, z1);

            GL11.glTexCoord2f(1, v1);
            GL11.glNormal3f(0, y1, z1);
            GL11.glVertex3f(1, y1, z1);

            GL11.glTexCoord2f(1, v0);
            GL11.glNormal3f(0, y0, z0);
            GL11.glVertex3f(1, y0, z0);
        }
        GL11.glEnd();

        // --- Front cap ---
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glTexCoord2f(0.5f, 0.5f);
        GL11.glNormal3f(1, 0, 0);
        GL11.glVertex3f(1, 0, 0);
        for (int i = 0; i <= SEGMENTS; i++) {
            double a = 2.0 * Math.PI * i / SEGMENTS;
            GL11.glTexCoord2f(0.5f + 0.5f * (float) Math.cos(a), 0.5f + 0.5f * (float) Math.sin(a));
            GL11.glNormal3f(1, 0, 0);
            GL11.glVertex3f(1, (float) Math.cos(a), (float) Math.sin(a));
        }
        GL11.glEnd();

        // --- Back cap ---
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glTexCoord2f(0.5f, 0.5f);
        GL11.glNormal3f(-1, 0, 0);
        GL11.glVertex3f(0, 0, 0);
        for (int i = SEGMENTS; i >= 0; i--) {
            double a = 2.0 * Math.PI * i / SEGMENTS;
            GL11.glTexCoord2f(0.5f + 0.5f * (float) Math.cos(a), 0.5f + 0.5f * (float) Math.sin(a));
            GL11.glNormal3f(-1, 0, 0);
            GL11.glVertex3f(0, (float) Math.cos(a), (float) Math.sin(a));
        }
        GL11.glEnd();

        GL11.glEndList();
    }

    private static void buildWhiteTexture() {
        whiteTexture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, whiteTexture);
        ByteBuffer pixel = BufferUtils.createByteBuffer(4);
        pixel.put((byte) 255).put((byte) 255).put((byte) 255).put((byte) 255).flip();
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, 1, 1, 0,
            GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixel);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    // ---- Public API ----

    public static void render(float length, float width, float r, float g, float b, float intensity) {
        ensureInitialized();

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_TEXTURE_BIT | GL11.GL_ENABLE_BIT | GL11.GL_DEPTH_BUFFER_BIT
            | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_CURRENT_BIT);

        // Render like a normal entity: bind a texture, set vertex color,
        // let Iris's gbuffers shader handle everything (lighting, G-buffer, bloom).
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, whiteTexture);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // additive

        GL11.glColor4f(r * intensity, g * intensity, b * intensity, intensity);

        GL11.glScalef(length, width, width);
        GL11.glCallList(displayList);

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
