package com.gtnewhorizon.newgunrizons.client.animation;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Produces subtle, continuous random rotation and translation offsets
 * that simulate the player's hands swaying while holding a weapon (idle breathing).
 *
 * Works by interpolating between random target transform matrices over time,
 * creating smooth drift rather than jittery jumps. Rate controls how fast
 * the sway moves between targets, amplitude controls how far it drifts.
 */
public final class IdleSway {

    private static final float MAX_ROTATION_ANGLE = 5.0F;
    private static final float Z_ROTATION_SCALE = 3.0F;
    private static final float Z_TRANSLATION_SCALE = 1.0F / 3.0F;

    private final Random random = new Random();
    private Matrix4f fromMatrix;
    private Matrix4f toMatrix;
    private Matrix4f currentMatrix = captureIdentity();
    private long startTime;
    private float rate = 0.25F;
    private float amplitude = 0.04F;

    public IdleSway() {
        this.pickNewTarget();
    }

    /**
     * Applies the sway effect to the current GL modelview matrix.
     * When rate and amplitude are both zero, resets to identity (no sway).
     */
    public void apply(float rate, float amplitude) {
        if (rate != this.rate || amplitude != this.amplitude) {
            if (rate == 0.0F && amplitude == 0.0F) {
                this.toMatrix = this.fromMatrix = this.currentMatrix = captureIdentity();
            } else {
                // Set amplitude before creating random matrices so they use the new value
                this.amplitude = amplitude;
                this.fromMatrix = this.currentMatrix = captureIdentity();
                this.toMatrix = this.createRandomMatrix();
                this.startTime = System.currentTimeMillis();
            }
            this.rate = rate;
            this.amplitude = amplitude;
        }

        if (rate == 0.0F && amplitude == 0.0F) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        float progress = (float) (currentTime - this.startTime) * rate / 1000.0F;
        if (progress >= 1.0F) {
            this.pickNewTarget();
            progress = 0.0F;
        }

        Matrix4f currentTransformMatrix = MatrixHelper.captureMatrix();
        Matrix4f m1 = MatrixHelper.interpolateMatrix(this.fromMatrix, 1.0F - progress);
        Matrix4f m2 = MatrixHelper.interpolateMatrix(this.toMatrix, progress);
        this.currentMatrix = Matrix4f.add(m1, m2, null);
        Matrix4f composite = Matrix4f.mul(currentTransformMatrix, this.currentMatrix, null);
        MatrixHelper.loadMatrix(composite);
    }

    private void pickNewTarget() {
        this.fromMatrix = this.currentMatrix;
        this.toMatrix = this.createRandomMatrix();
        this.startTime = System.currentTimeMillis();
    }

    private Matrix4f createRandomMatrix() {
        return captureTransform(() -> {
            float xRotation = MAX_ROTATION_ANGLE * this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F;
            float yRotation = MAX_ROTATION_ANGLE * this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F;
            float zRotation = MAX_ROTATION_ANGLE * this.amplitude
                * (this.random.nextFloat() - 0.5F)
                * 2.0F
                * Z_ROTATION_SCALE;
            GL11.glRotatef(xRotation, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(yRotation, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(zRotation, 0.0F, 0.0F, 1.0F);
            float xOffset = this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F;
            float yOffset = this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F;
            float zOffset = this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F * Z_TRANSLATION_SCALE;
            GL11.glTranslatef(xOffset, yOffset, zOffset);
        });
    }

    private static Matrix4f captureIdentity() {
        return captureTransform(() -> {});
    }

    private static Matrix4f captureTransform(Runnable glOperations) {
        GL11.glPushMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        FloatBuffer buf = BufferUtils.createFloatBuffer(16);
        glOperations.run();
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, buf);
        buf.rewind();
        Matrix4f matrix = new Matrix4f();
        matrix.load(buf);
        GL11.glPopMatrix();
        return matrix;
    }
}
