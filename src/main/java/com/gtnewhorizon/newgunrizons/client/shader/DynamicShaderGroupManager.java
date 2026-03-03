package com.gtnewhorizon.newgunrizons.client.shader;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonSyntaxException;
import com.gtnewhorizon.newgunrizons.client.resource.TransformingResourceManager;
import com.gtnewhorizon.newgunrizons.client.resource.TransformingTextureManager;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;

public class DynamicShaderGroupManager {

    private static final Logger logger = LogManager.getLogger(DynamicShaderGroupManager.class);
    private static final String PATH_RESOURCES = "/com/gtnewhorizon/newgunrizons/resources/";
    private static final String PATH_SHADER_PROGRAMS = "/com/gtnewhorizon/newgunrizons/resources/shaders/programs/";
    private static final String DOMAIN = "newgunrizons";
    private static final String DOMAIN_SHADER_PROGRAM = "shaders/program/newgunrizons";
    private static final String DOMAIN_TEXTURES_EFFECT = "textures/effect/newgunrizons";
    private static final String SHADER_PROGRAM_PREFIX = "shaders/program/";

    private final Map<UUID, LoadedShaderGroup> loaded = new LinkedHashMap<>();

    public boolean hasActiveGroups() {
        return !this.loaded.isEmpty();
    }

    public void applyShader(DynamicShaderContext shaderContext, PlayerWeaponInstance instance) {
        if (instance != null) {
            DynamicShaderGroupSource source = instance.getShaderSource(shaderContext.getPhase());
            if (source != null) {
                loadFromSource(shaderContext, source);
            }
        }
    }

    /**
     * Loads or confirms a shader group from the given source. Any existing shader
     * group with the same phase but a different source or framebuffer is removed
     * first (it's being replaced).
     */
    public void loadFromSource(DynamicShaderContext context, DynamicShaderGroupSource source) {
        int originalFramebufferId = Framebuffers.getCurrentFramebuffer();

        // Remove conflicting shader groups: same phase but different source or framebuffer.
        Iterator<Map.Entry<UUID, LoadedShaderGroup>> it = this.loaded.entrySet()
            .iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, LoadedShaderGroup> entry = it.next();
            LoadedShaderGroup existing = entry.getValue();
            if (existing.phase != context.getPhase()) {
                continue;
            }
            if (source.getSourceId()
                .equals(entry.getKey()) && context.getMainFramebuffer() == existing.mainFramebuffer) {
                continue;
            }
            existing.phase.remove(context, existing.group);
            it.remove();
        }

        // Load a new shader group, or confirm the existing one for this source.
        LoadedShaderGroup loadedGroup = this.loaded.compute(source.getSourceId(), (id, current) -> {
            if (current != null) {
                current.confirmed = true;
                return current;
            }
            LoadedShaderGroup newGroup = new LoadedShaderGroup();
            newGroup.confirmed = true;
            newGroup.group = this.createShaderGroup(context, source, source.getShaderLocation());
            newGroup.phase = context.getPhase();
            newGroup.mainFramebuffer = context.getMainFramebuffer();
            return newGroup;
        });

        if (loadedGroup != null && loadedGroup.group != null) {
            source.getUniforms(context)
                .forEach(
                    u -> loadedGroup.group.setUniform(
                        u.getU(),
                        u.getV()
                            .apply(context)));
            context.getPhase()
                .apply(context, loadedGroup.group);
        }

        // Restore the original framebuffer (shader loading may have changed it).
        Minecraft mc = Minecraft.getMinecraft();
        Framebuffers.bindFramebuffer(
            originalFramebufferId,
            true,
            mc.getFramebuffer().framebufferWidth,
            mc.getFramebuffer().framebufferHeight);
    }

    private DynamicShaderGroup createShaderGroup(DynamicShaderContext context, DynamicShaderGroupSource source,
        ResourceLocation resourceLocation) {
        Minecraft mc = Minecraft.getMinecraft();
        IResourceManager resourceManager = new TransformingResourceManager(
            mc.getResourceManager(),
            DynamicShaderGroupManager::modifyResourceLocation);
        TransformingTextureManager textureManager = new TransformingTextureManager(
            mc.getTextureManager(),
            DynamicShaderGroupManager::modifyResourceLocation);

        try {
            DynamicShaderGroup group = new DynamicShaderGroup(
                textureManager,
                resourceManager,
                context.getMainFramebuffer(),
                resourceLocation);
            group.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
            return group;
        } catch (IOException | JsonSyntaxException e) {
            logger.error("Failed to create shader due to {}", e, e);
            return null;
        }
    }

    public void removeStaleShaders(DynamicShaderContext context) {
        Iterator<Map.Entry<UUID, LoadedShaderGroup>> it = this.loaded.entrySet()
            .iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, LoadedShaderGroup> entry = it.next();
            LoadedShaderGroup group = entry.getValue();
            if (!group.confirmed) {
                it.remove();
                if (group.group != null) {
                    group.phase.remove(context, group.group);
                }
            } else {
                group.confirmed = false;
            }
        }
    }

    public void removeAllShaders(DynamicShaderContext context) {
        Iterator<Map.Entry<UUID, LoadedShaderGroup>> it = this.loaded.entrySet()
            .iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, LoadedShaderGroup> entry = it.next();
            LoadedShaderGroup group = entry.getValue();
            if (group.group != null) {
                group.phase.remove(context, group.group);
            }
            it.remove();
        }
    }

    /**
     * Rewrites resource locations to load shader and texture resources
     * from the mod's classpath (under /com/gtnewhorizon/newgunrizons/resources/).
     */
    private static ResourceLocation modifyResourceLocation(ResourceLocation resourceLocation) {
        String domain = resourceLocation.getResourceDomain();
        String path = resourceLocation.getResourcePath();

        if (DOMAIN.equals(domain)) {
            if (path.startsWith(SHADER_PROGRAM_PREFIX)) {
                return new ResourceLocation(
                    DOMAIN,
                    PATH_SHADER_PROGRAMS + path.substring(SHADER_PROGRAM_PREFIX.length()));
            }
            return resourceLocation;
        }

        if (DOMAIN_SHADER_PROGRAM.equals(domain)) {
            return new ResourceLocation(DOMAIN, PATH_SHADER_PROGRAMS + path);
        }

        if (DOMAIN_TEXTURES_EFFECT.equals(domain)) {
            return new ResourceLocation(DOMAIN, PATH_RESOURCES + path);
        }

        return resourceLocation;
    }

    private static class LoadedShaderGroup {

        DynamicShaderGroup group;
        DynamicShaderPhase phase;
        Framebuffer mainFramebuffer;
        boolean confirmed;
    }
}
