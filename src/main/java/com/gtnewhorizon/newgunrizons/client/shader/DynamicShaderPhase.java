package com.gtnewhorizon.newgunrizons.client.shader;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.client.scope.ScopeWorldRenderer;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

public interface DynamicShaderPhase {

    DynamicShaderPhase POST_WORLD_RENDER = new DynamicShaderPhase.EntityRendererTarget();
    DynamicShaderPhase POST_WORLD_OPTICAL_SCOPE_RENDER = new DynamicShaderPhase.WeaponWorldRendererTarget();
    DynamicShaderPhase PRE_ITEM_RENDER = new DynamicShaderPhase() {

        public void apply(DynamicShaderContext context, DynamicShaderGroup shaderGroup) {
            if (OpenGlHelper.shadersSupported) {
                int originalMatrixMode = GL11.glGetInteger(2976);
                GL11.glMatrixMode(5890);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                GL11.glPushAttrib(8193);
                shaderGroup.render(context.getPartialTicks());
                GL11.glPopAttrib();
                GL11.glPopMatrix();
                GL11.glMatrixMode(originalMatrixMode);
            }
        }

        public void remove(DynamicShaderContext context, DynamicShaderGroup shaderGroup) {
            shaderGroup.deleteShaderGroup();
        }
    };

    void apply(DynamicShaderContext var1, DynamicShaderGroup var2);

    void remove(DynamicShaderContext var1, DynamicShaderGroup var2);

    class WeaponWorldRendererTarget implements DynamicShaderPhase {

        public void apply(DynamicShaderContext context, DynamicShaderGroup shaderGroup) {
            Object target = context.getTarget();
            if (target instanceof ScopeWorldRenderer entityRenderer) {
                ShaderGroup currentShaderGroup = entityRenderer.getShaderGroup();
                if (currentShaderGroup != shaderGroup) {
                    entityRenderer.theShaderGroup = shaderGroup;
                    entityRenderer.useShader(true);
                }
            }

        }

        public void remove(DynamicShaderContext context, DynamicShaderGroup shaderGroup) {
            Object target = context.getTarget();
            if (target instanceof ScopeWorldRenderer entityRenderer) {
                ShaderGroup currentShaderGroup = entityRenderer.getShaderGroup();
                if (currentShaderGroup instanceof DynamicShaderGroup) {
                    currentShaderGroup.deleteShaderGroup();
                    entityRenderer.theShaderGroup = null;
                }
            }

        }
    }

    class EntityRendererTarget implements DynamicShaderPhase {

        public void apply(DynamicShaderContext context, DynamicShaderGroup shaderGroup) {
            Object target = context.getTarget();
            if (target instanceof EntityRenderer entityRenderer) {
                ShaderGroup currentShaderGroup = ObfuscationReflectionHelper
                    .getPrivateValue(EntityRenderer.class, entityRenderer, "theShaderGroup", "theShaderGroup");
                if (currentShaderGroup != shaderGroup) {
                    this.remove(context, null);
                    ObfuscationReflectionHelper.setPrivateValue(
                        EntityRenderer.class,
                        entityRenderer,
                        shaderGroup,
                        "theShaderGroup",
                        "theShaderGroup");
                }
            }

        }

        public void remove(DynamicShaderContext context, DynamicShaderGroup shaderGroup) {
            Object target = context.getTarget();
            if (target instanceof EntityRenderer entityRenderer) {
                ShaderGroup currentShaderGroup = ObfuscationReflectionHelper
                    .getPrivateValue(EntityRenderer.class, entityRenderer, "theShaderGroup", "theShaderGroup");
                if (currentShaderGroup instanceof DynamicShaderGroup) {
                    currentShaderGroup.deleteShaderGroup();
                    ObfuscationReflectionHelper.setPrivateValue(
                        EntityRenderer.class,
                        entityRenderer,
                        (DynamicShaderGroup) null,
                        "theShaderGroup",
                        "theShaderGroup");
                }
            }

        }
    }
}
