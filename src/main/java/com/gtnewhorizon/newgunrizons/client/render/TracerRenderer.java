package com.gtnewhorizon.newgunrizons.client.render;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 * Renders bullet tracers as a textured cylinder with two render paths:
 * <ul>
 *   <li><b>Iris ON</b>: plain white-textured cylinder with vertex color tinting.
 *       Iris's gbuffers shader handles lighting and bloom.</li>
 *   <li><b>Iris OFF</b>: custom GLSL glow shader with additive blending for
 *       a procedural hot-core-to-color falloff effect.</li>
 * </ul>
 */
public class TracerRenderer {

    private static final int SEGMENTS = 8;

    private static int displayList = -1;
    private static int whiteTexture = -1;
    private static int shaderProgram = -1;
    private static int uTracerColor = -1;
    private static int uTracerLength = -1;
    private static int uIntensity = -1;
    private static boolean initialized;
    private static boolean shaderAvailable;

    private static void ensureInitialized() {
        // Detect GL context recreation (game restart): if the display list
        // handle is stale, reinitialize everything.
        if (initialized && displayList >= 0 && !GL11.glIsList(displayList)) {
            initialized = false;
            shaderAvailable = false;
        }
        if (initialized) return;
        initialized = true;
        buildCylinder();
        buildWhiteTexture();
        loadShader();
    }

    private static void buildCylinder() {
        displayList = GL11.glGenLists(1);
        GL11.glNewList(displayList, GL11.GL_COMPILE);

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

    public static void render(float length, float width, float r, float g, float b, float intensity) {
        ensureInitialized();

        int prevProgram = GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        boolean irisActive = prevProgram != 0;

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_TEXTURE_BIT | GL11.GL_ENABLE_BIT | GL11.GL_DEPTH_BUFFER_BIT
            | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_CURRENT_BIT);

        GL11.glDisable(GL11.GL_CULL_FACE);

        if (irisActive) {
            // Iris path: white textured cylinder, vertex color tint.
            // Iris handles lighting, G-buffer writes, and bloom.
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, whiteTexture);
            GL11.glColor4f(r * intensity, g * intensity, b * intensity, intensity);
        } else {
            // Vanilla path: custom glow shader with additive blending.
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glDepthMask(false);

            if (shaderAvailable) {
                GL20.glUseProgram(shaderProgram);
                GL20.glUniform3f(uTracerColor, r, g, b);
                GL20.glUniform1f(uTracerLength, length);
                GL20.glUniform1f(uIntensity, intensity);
            } else {
                GL11.glColor4f(r * intensity, g * intensity, b * intensity, 0.6f);
            }
        }

        GL11.glScalef(length, width, width);
        GL11.glCallList(displayList);

        // Restore shader program (only changed in vanilla path)
        if (!irisActive && shaderAvailable) {
            GL20.glUseProgram(0);
        }

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
