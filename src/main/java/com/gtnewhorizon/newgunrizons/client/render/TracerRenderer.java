package com.gtnewhorizon.newgunrizons.client.render;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

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
    private static int shaderProgram = -1;
    private static int uTracerColor = -1;
    private static int uTracerLength = -1;
    private static int uIntensity = -1;
    private static boolean initialized;
    private static boolean shaderAvailable;

    /** Must be called from the render thread on first use. */
    private static void ensureInitialized() {
        if (initialized) return;
        initialized = true;
        buildCylinder();
        loadShader();
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

            // CCW winding when viewed from outside (normal points outward)
            GL11.glNormal3f(0, y0, z0);
            GL11.glVertex3f(0, y0, z0); // back, seg i

            GL11.glNormal3f(0, y1, z1);
            GL11.glVertex3f(0, y1, z1); // back, seg i+1

            GL11.glNormal3f(0, y1, z1);
            GL11.glVertex3f(1, y1, z1); // front, seg i+1

            GL11.glNormal3f(0, y0, z0);
            GL11.glVertex3f(1, y0, z0); // front, seg i
        }
        GL11.glEnd();

        // --- Front cap (disc at x = 1, normal +X) ---
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glNormal3f(1, 0, 0);
        GL11.glVertex3f(1, 0, 0); // center
        for (int i = 0; i <= SEGMENTS; i++) {
            double a = 2.0 * Math.PI * i / SEGMENTS;
            GL11.glNormal3f(1, 0, 0);
            GL11.glVertex3f(1, (float) Math.cos(a), (float) Math.sin(a));
        }
        GL11.glEnd();

        // --- Back cap (disc at x = 0, normal -X, reverse winding) ---
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glNormal3f(-1, 0, 0);
        GL11.glVertex3f(0, 0, 0); // center
        for (int i = SEGMENTS; i >= 0; i--) {
            double a = 2.0 * Math.PI * i / SEGMENTS;
            GL11.glNormal3f(-1, 0, 0);
            GL11.glVertex3f(0, (float) Math.cos(a), (float) Math.sin(a));
        }
        GL11.glEnd();

        GL11.glEndList();
    }

    // ---- Shader compilation ----

    private static void loadShader() {
        String vshSource = readResource("tracer.vsh");
        String fshSource = readResource("tracer.fsh");
        if (vshSource == null || fshSource == null) return;

        int vsh = compileShader(GL20.GL_VERTEX_SHADER, vshSource);
        int fsh = compileShader(GL20.GL_FRAGMENT_SHADER, fshSource);
        if (vsh == 0 || fsh == 0) return;

        int program = GL20.glCreateProgram();
        GL20.glAttachShader(program, vsh);
        GL20.glAttachShader(program, fsh);
        GL20.glLinkProgram(program);

        if (GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            GL20.glDeleteProgram(program);
            return;
        }

        // Shaders are linked into the program; flag them for deletion
        // (they will be freed when the program is deleted).
        GL20.glDeleteShader(vsh);
        GL20.glDeleteShader(fsh);

        shaderProgram = program;
        uTracerColor = GL20.glGetUniformLocation(program, "u_TracerColor");
        uTracerLength = GL20.glGetUniformLocation(program, "u_TracerLength");
        uIntensity = GL20.glGetUniformLocation(program, "u_Intensity");
        shaderAvailable = true;
    }

    private static int compileShader(int type, String source) {
        int shader = GL20.glCreateShader(type);
        GL20.glShaderSource(shader, source);
        GL20.glCompileShader(shader);
        if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            GL20.glDeleteShader(shader);
            return 0;
        }
        return shader;
    }

    private static String readResource(String name) {
        try (InputStream is = TracerRenderer.class
            .getResourceAsStream("/assets/newgunrizons/shaders/program/" + name)) {
            if (is == null) return null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    // ---- Public API ----

    /**
     * Draws a tracer cylinder scaled to the given dimensions and tinted
     * with the specified color. Manages all GL state internally.
     *
     * @param length tracer length (along the flight axis)
     * @param width  tracer radius (cross-section)
     * @param r         red component of the tracer color (0–1)
     * @param g         green component (0–1)
     * @param b         blue component (0–1)
     * @param intensity brightness multiplier (1.0 = normal tracer, 3.0+ = solid laser beam)
     */
    public static void render(float length, float width, float r, float g, float b, float intensity) {
        ensureInitialized();

        // --- Save active shader program (Angelica/Iris compatibility) ---
        int prevProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);

        GL11.glPushMatrix();
        GL11.glPushAttrib(
            GL11.GL_ENABLE_BIT | GL11.GL_DEPTH_BUFFER_BIT
                | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_CURRENT_BIT);

        // When inside a shader FBO, restrict output to the main color attachment
        // to prevent writing tracer data into G-buffer auxiliaries (normals, specular).
        if (prevProgram != 0) {
            GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
        }

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // additive
        GL11.glDepthMask(false);

        // Bind our shader (or fall back to fixed-function with vertex color)
        if (shaderAvailable) {
            GL20.glUseProgram(shaderProgram);
            GL20.glUniform3f(uTracerColor, r, g, b);
            GL20.glUniform1f(uTracerLength, length);
            GL20.glUniform1f(uIntensity, intensity);
        } else {
            GL11.glColor4f(r, g, b, 0.6f);
        }

        GL11.glScalef(length, width, width);
        GL11.glCallList(displayList);

        // --- Depth-only pass: write depth so translucent geometry (water) ---
        // --- doesn't render over the tracer.                              ---
        GL11.glColorMask(false, false, false, false);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE); // front faces only for a single depth layer
        if (shaderAvailable) {
            GL20.glUseProgram(0);
        }
        GL11.glCallList(displayList);

        // --- Restore state ---
        GL20.glUseProgram(prevProgram);

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
