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
            .withUniform("IntensityAdjust", context -> 40.0F - Minecraft.func_71410_x().field_71474_y.field_74333_Y * 38.0F)
            .withUniform("NoiseAmplification", context -> 2.0F + 3.0F * Minecraft.func_71410_x().field_71474_y.field_74333_Y);
         this.vignetteSource = new ShaderEffect(VIGNETTE_SOURCE_UUID, new ResourceLocation("newgunrizons", "shaders/post/vignette.json"))
            .withUniform("Radius", context -> {
               EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
               float f2 = player.field_71107_bF + (player.field_71109_bG - player.field_71107_bF) * context.getPartialTicks();
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

      Minecraft mc = Minecraft.func_71410_x();
      Framebuffers.bindFramebuffer(originalFramebufferId, true, mc.func_147110_a().field_147621_c, mc.func_147110_a().field_147618_d);
   }

   private DynamicShaderGroup createShaderGroup(ShaderContext context, ShaderEffect source, ResourceLocation resourceLocation) {
      Minecraft mc = Minecraft.func_71410_x();
      IResourceManager resourceManager = new ShaderManager.FixedDomainResourceManager(mc.func_110442_L(), ShaderManager::fixMangledDomain);
      TextureManager textureManager = new ShaderManager.FixedDomainTextureManager(mc.func_110434_K(), ShaderManager::fixMangledDomain);

      try {
         DynamicShaderGroup group = new DynamicShaderGroup(textureManager, resourceManager, context.getFramebuffer(), resourceLocation);
         group.func_148026_a(mc.field_71443_c, mc.field_71440_d);
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
      String domain = rl.func_110624_b();
      if ("shaders/program/newgunrizons".equals(domain)) {
         return new ResourceLocation("newgunrizons", "shaders/program/" + rl.func_110623_a());
      } else {
         return "textures/effect/newgunrizons".equals(domain) ? new ResourceLocation("newgunrizons", "textures/effect/" + rl.func_110623_a()) : rl;
      }
   }

   private static class FixedDomainResourceManager implements IResourceManager {
      private final IResourceManager delegate;
      private final UnaryOperator<ResourceLocation> fixer;

      FixedDomainResourceManager(IResourceManager delegate, UnaryOperator<ResourceLocation> fixer) {
         this.delegate = delegate;
         this.fixer = fixer;
      }

      public Set<String> func_135055_a() {
         return this.delegate.func_135055_a();
      }

      public IResource func_110536_a(ResourceLocation location) throws IOException {
         return this.delegate.func_110536_a(this.fixer.apply(location));
      }

      public List<IResource> func_135056_b(ResourceLocation location) throws IOException {
         return this.delegate.func_135056_b(this.fixer.apply(location));
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

      public void func_110577_a(ResourceLocation resource) {
         this.delegate.func_110577_a(this.fixer.apply(resource));
      }

      public boolean func_110580_a(ResourceLocation textureLocation, ITickableTextureObject textureObj) {
         return this.delegate.func_110580_a(this.fixer.apply(textureLocation), textureObj);
      }

      public boolean func_110579_a(ResourceLocation textureLocation, ITextureObject textureObj) {
         ResourceLocation fixed = this.fixer.apply(textureLocation);
         if (!fixed.equals(textureLocation) && textureObj instanceof SimpleTexture) {
            textureObj = new SimpleTexture(fixed);
         }

         return this.delegate.func_110579_a(fixed, textureObj);
      }

      public ITextureObject func_110581_b(ResourceLocation textureLocation) {
         return this.delegate.func_110581_b(this.fixer.apply(textureLocation));
      }

      public ResourceLocation func_110578_a(String name, DynamicTexture texture) {
         return this.delegate.func_110578_a(name, texture);
      }

      public void func_110550_d() {
         this.delegate.func_110550_d();
      }

      public void func_147645_c(ResourceLocation textureLocation) {
         this.delegate.func_147645_c(this.fixer.apply(textureLocation));
      }

      public void func_110549_a(IResourceManager resourceManager) {
         this.delegate.func_110549_a(resourceManager);
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
