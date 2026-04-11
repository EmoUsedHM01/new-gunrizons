package com.gtnewhorizon.newgunrizons.client.animation;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Random;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public final class IdleSway {
   private static final float MAX_ROTATION_ANGLE = 5.0F;
   private static final float Z_ROTATION_SCALE = 3.0F;
   private static final float Z_TRANSLATION_SCALE = 0.33333334F;
   private final Random random = new Random();
   private Matrix4f fromMatrix;
   private Matrix4f toMatrix;
   private Matrix4f currentMatrix = captureIdentity();
   private long startTime;
   private float rate = 0.25F;
   private float amplitude = 0.04F;
   private float verticalBias = 1.0F;

   public IdleSway() {
      this.pickNewTarget();
   }

   public void apply(float rate, float amplitude, float verticalBias) {
      if (rate != this.rate || amplitude != this.amplitude || verticalBias != this.verticalBias) {
         if (rate == 0.0F && amplitude == 0.0F) {
            this.toMatrix = this.fromMatrix = this.currentMatrix = captureIdentity();
         } else {
            this.pickNewTarget();
         }

         this.rate = rate;
         this.amplitude = amplitude;
         this.verticalBias = verticalBias;
      }

      if (rate != 0.0F || amplitude != 0.0F) {
         long currentTime = System.currentTimeMillis();
         float progress = (float)(currentTime - this.startTime) * rate / 1000.0F;
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
   }

   private void pickNewTarget() {
      this.fromMatrix = this.currentMatrix;
      this.toMatrix = this.createRandomMatrix();
      this.startTime = System.currentTimeMillis();
   }

   private Matrix4f createRandomMatrix() {
      return captureTransform(() -> {
         float hScale = 1.0F / this.verticalBias;
         float xRotation = 5.0F * this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F * hScale;
         float yRotation = 5.0F * this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F;
         float zRotation = 5.0F * this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F * 3.0F * hScale;
         GL11.glRotatef(xRotation, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(yRotation, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(zRotation, 0.0F, 0.0F, 1.0F);
         float xOffset = this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F * hScale;
         float yOffset = this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F * this.verticalBias;
         float zOffset = this.amplitude * (this.random.nextFloat() - 0.5F) * 2.0F * 0.33333334F * hScale;
         GL11.glTranslatef(xOffset, yOffset, zOffset);
      });
   }

   private static Matrix4f captureIdentity() {
      return captureTransform(() -> {});
   }

   private static Matrix4f captureTransform(Runnable glOperations) {
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      FloatBuffer buf = BufferUtils.createFloatBuffer(16);
      glOperations.run();
      GL11.glGetFloat(2982, buf);
      ((Buffer)buf).rewind();
      Matrix4f matrix = new Matrix4f();
      matrix.load(buf);
      GL11.glPopMatrix();
      return matrix;
   }
}
