package com.gtnewhorizon.newgunrizons.client.shaders;

import com.google.gson.JsonSyntaxException;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.render.Framebuffers;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.UnaryOperator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderManager {
   private static final Logger logger = LogManager.getLogger(ShaderManager.class);
   private static final UUID BLUR_SOURCE_UUID = UUID.randomUUID();
   private static final UUID NIGHT_VISION_SOURCE_UUID = UUID.randomUUID();
   private static final UUID VIGNETTE_SOURCE_UUID = UUID.randomUUID();
   private final Map<UUID, ShaderManager.LoadedShaderGroup> loaded = new LinkedHashMap<>();
   private ShaderEffect blurSource;
   private ShaderEffect nightVisionSource;
   private ShaderEffect vignetteSource;
   private ItemWeaponInstance currentInstance;

   public boolean hasActiveGroups() {
      return !this.loaded.isEmpty();
   }

   public void applyShader(ShaderContext shaderContext, ItemWeaponInstance instance) {
      if (instance != null) {
         ShaderEffect source = this.resolveShaderEffect(instance, shaderContext.getPhase());
         if (source != null) {
            this.loadFromSource(shaderContext, source);
         }
      }
   }

   private void ensureEffectsInitialized() {
      if (this.blurSource == null) {
         this.blurSource = new ShaderEffect(BLUR_SOURCE_UUID, new ResourceLocation("newgunrizons", "shaders/post/blur.json"))
            .withUniform("Radius", context -> {
               ItemAttachment scope = this.currentInstance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
               return scope instanceof ItemScope && ((ItemScope)scope).isOptical() ? 10.0F : 5.0F;
            })
            .withUniform("Progress", context -> this.currentInstance.getAimChangeProgress());
         this.nightVisionSource = new ShaderEffect(NIGHT_VISION_SOURCE_UUID, new ResourceLocation("newgunrizons", "shaders/post/night-vision.json"))
            .withUniform("IntensityAdjust", context -> 40.0F - Minecraft.getMinecraft().gameSettings.gammaSetting * 38.0F)
            .withUniform("NoiseAmplification", context -> 2.0F + 3.0F * Minecraft.getMinecraft().gameSettings.gammaSetting);
         this.vignetteSource = new ShaderEffect(VIGNETTE_SOURCE_UUID, new ResourceLocation("newgunrizons", "shaders/post/vignette.json"))
            .withUniform("Radius", context -> {
               EntityPlayer player = Minecraft.getMinecraft().thePlayer;
               float f2 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * context.getPartialTicks();
               return -6.5F * f2 + 0.55F;
            });
      }
   }

   private ShaderEffect resolveShaderEffect(ItemWeaponInstance instance, ShaderPhase phase) {
      this.currentInstance = instance;
      this.ensureEffectsInitialized();
      if (instance.isAimed() && phase == ShaderPhase.SCOPE_RENDER) {
         ItemAttachment scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
         if (scopeItem instanceof ItemScope) {
            ItemScope scope = (ItemScope)scopeItem;
            if (scope.isOptical()) {
               return scope.hasNightVision() && instance.isNightVisionOn() ? this.nightVisionSource : this.vignetteSource;
            }
         }
      }

      float progress = instance.getAimChangeProgress();
      return phase != ShaderPhase.ITEM_RENDER || !instance.isAimed() && (!(progress > 0.0F) || !(progress < 1.0F)) ? null : this.blurSource;
   }

   public void loadFromSource(ShaderContext context, ShaderEffect source) {
      int originalFramebufferId = Framebuffers.getCurrentFramebuffer();
      Iterator<Entry<UUID, ShaderManager.LoadedShaderGroup>> it = this.loaded.entrySet().iterator();

      while (it.hasNext()) {
         Entry<UUID, ShaderManager.LoadedShaderGroup> entry = it.next();
         ShaderManager.LoadedShaderGroup existing = entry.getValue();
         if (existing.phase == context.getPhase() && (!source.getSourceId().equals(entry.getKey()) || context.getFramebuffer() != existing.framebuffer)) {
            existing.phase.remove(context, existing.group);
            it.remove();
         }
      }

      ShaderManager.LoadedShaderGroup loadedGroup = this.loaded.compute(source.getSourceId(), (id, current) -> {
         if (current != null) {
            current.confirmed = true;
            return (ShaderManager.LoadedShaderGroup)current;
         } else {
            ShaderManager.LoadedShaderGroup newGroup = new ShaderManager.LoadedShaderGroup();
            newGroup.confirmed = true;
            newGroup.group = this.createShaderGroup(context, source, source.getShaderLocation());
            newGroup.phase = context.getPhase();
            newGroup.framebuffer = context.getFramebuffer();
            return newGroup;
         }
      });
      if (loadedGroup != null && loadedGroup.group != null) {
         source.getUniforms().forEach(u -> loadedGroup.group.setUniform(u.getName(), u.getSupplier().apply(context)));
         context.getPhase().apply(context, loadedGroup.group);
      }

      Minecraft mc = Minecraft.getMinecraft();
      Framebuffers.bindFramebuffer(originalFramebufferId, true, mc.getFramebuffer().framebufferWidth, mc.getFramebuffer().framebufferHeight);
   }

   private DynamicShaderGroup createShaderGroup(ShaderContext context, ShaderEffect source, ResourceLocation resourceLocation) {
      Minecraft mc = Minecraft.getMinecraft();
      IResourceManager resourceManager = new ShaderManager.FixedDomainResourceManager(mc.getResourceManager(), ShaderManager::fixMangledDomain);
      TextureManager textureManager = new ShaderManager.FixedDomainTextureManager(mc.getTextureManager(), ShaderManager::fixMangledDomain);

      try {
         DynamicShaderGroup group = new DynamicShaderGroup(textureManager, resourceManager, context.getFramebuffer(), resourceLocation);
         group.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
         return group;
      } catch (JsonSyntaxException | IOException var8) {
         logger.error("Failed to create shader due to {}", new Object[]{var8, var8});
         return null;
      }
   }

   public void removeStaleShaders(ShaderContext context) {
      Iterator<Entry<UUID, ShaderManager.LoadedShaderGroup>> it = this.loaded.entrySet().iterator();

      while (it.hasNext()) {
         Entry<UUID, ShaderManager.LoadedShaderGroup> entry = it.next();
         ShaderManager.LoadedShaderGroup group = entry.getValue();
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

   public void removeAllShaders(ShaderContext context) {
      for (Iterator<Entry<UUID, ShaderManager.LoadedShaderGroup>> it = this.loaded.entrySet().iterator(); it.hasNext(); it.remove()) {
         Entry<UUID, ShaderManager.LoadedShaderGroup> entry = it.next();
         ShaderManager.LoadedShaderGroup group = entry.getValue();
         if (group.group != null) {
            group.phase.remove(context, group.group);
         }
      }
   }

   private static ResourceLocation fixMangledDomain(ResourceLocation rl) {
      String domain = rl.getResourceDomain();
      if ("shaders/program/newgunrizons".equals(domain)) {
         return new ResourceLocation("newgunrizons", "shaders/program/" + rl.getResourcePath());
      } else {
         return "textures/effect/newgunrizons".equals(domain) ? new ResourceLocation("newgunrizons", "textures/effect/" + rl.getResourcePath()) : rl;
      }
   }

   private static class FixedDomainResourceManager implements IResourceManager {
      private final IResourceManager delegate;
      private final UnaryOperator<ResourceLocation> fixer;

      FixedDomainResourceManager(IResourceManager delegate, UnaryOperator<ResourceLocation> fixer) {
         this.delegate = delegate;
         this.fixer = fixer;
      }

      public Set<String> getResourceDomains() {
         return this.delegate.getResourceDomains();
      }

      public IResource getResource(ResourceLocation location) throws IOException {
         return this.delegate.getResource(this.fixer.apply(location));
      }

      public List<IResource> getAllResources(ResourceLocation location) throws IOException {
         return this.delegate.getAllResources(this.fixer.apply(location));
      }
   }

   private static class FixedDomainTextureManager extends TextureManager {
      private final TextureManager delegate;
      private final UnaryOperator<ResourceLocation> fixer;

      FixedDomainTextureManager(TextureManager delegate, UnaryOperator<ResourceLocation> fixer) {
         super(null);
         this.delegate = delegate;
         this.fixer = fixer;
      }

      public void bindTexture(ResourceLocation resource) {
         this.delegate.bindTexture(this.fixer.apply(resource));
      }

      public boolean loadTickableTexture(ResourceLocation textureLocation, ITickableTextureObject textureObj) {
         return this.delegate.loadTickableTexture(this.fixer.apply(textureLocation), textureObj);
      }

      public boolean loadTexture(ResourceLocation textureLocation, ITextureObject textureObj) {
         ResourceLocation fixed = this.fixer.apply(textureLocation);
         if (!fixed.equals(textureLocation) && textureObj instanceof SimpleTexture) {
            textureObj = new SimpleTexture(fixed);
         }

         return this.delegate.loadTexture(fixed, textureObj);
      }

      public ITextureObject getTexture(ResourceLocation textureLocation) {
         return this.delegate.getTexture(this.fixer.apply(textureLocation));
      }

      public ResourceLocation getDynamicTextureLocation(String name, DynamicTexture texture) {
         return this.delegate.getDynamicTextureLocation(name, texture);
      }

      public void tick() {
         this.delegate.tick();
      }

      public void deleteTexture(ResourceLocation textureLocation) {
         this.delegate.deleteTexture(this.fixer.apply(textureLocation));
      }

      public void onResourceManagerReload(IResourceManager resourceManager) {
         this.delegate.onResourceManagerReload(resourceManager);
      }
   }

   private static class LoadedShaderGroup {
      DynamicShaderGroup group;
      ShaderPhase phase;
      Framebuffer framebuffer;
      boolean confirmed;

      private LoadedShaderGroup() {
      }
   }
}
