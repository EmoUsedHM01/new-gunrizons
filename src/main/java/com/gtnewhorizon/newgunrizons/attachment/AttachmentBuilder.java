package com.gtnewhorizon.newgunrizons.attachment;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.StaticModelRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.util.Pair;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class AttachmentBuilder {
   private String name;
   private ModelBase model;
   private String textureName;
   private final List<Pair<ModelBase, String>> texturedModels = new ArrayList<>();
   private String crosshair;
   private CustomRenderer postRenderer;
   private boolean isRenderablePart;
   private CreativeTabs tab;
   private AttachmentCategory category;
   private int maxStackSize = 1;
   private Function<ItemStack, String> informationProvider;
   protected ItemAttachment.AttachmentHandler applyHandler;
   protected ItemAttachment.AttachmentHandler removeHandler;
   private final Map<ItemAttachment, CompatibleAttachment> compatibleAttachments = new HashMap<>();
   private Consumer<ItemStack> entityPositioning;
   private Consumer<ItemStack> inventoryPositioning;
   private BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning;
   private BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning;
   private BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning;
   private BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning;
   private BiConsumer<ModelBase, ItemStack> inventoryModelPositioning;
   private BiConsumer<ModelBase, ItemStack> entityModelPositioning;
   private Consumer<RenderContext> firstPersonLeftHandPositioning;
   private Consumer<RenderContext> firstPersonRightHandPositioning;

   public AttachmentBuilder withName(String name) {
      this.name = name;
      return this;
   }

   public AttachmentBuilder withCategory(AttachmentCategory category) {
      this.category = category;
      return this;
   }

   public AttachmentBuilder withCreativeTab(CreativeTabs tab) {
      this.tab = tab;
      return this;
   }

   public AttachmentBuilder withModel(ModelBase model) {
      this.model = model;
      return this;
   }

   public AttachmentBuilder withModel(ModelBase model, String textureName) {
      this.texturedModels.add(new Pair<>(model, textureName.toLowerCase()));
      return this;
   }

   public AttachmentBuilder withTextureName(String textureName) {
      this.textureName = textureName.toLowerCase();
      return this;
   }

   public AttachmentBuilder withCrosshair(String crosshair) {
      this.crosshair = crosshair.toLowerCase();
      return this;
   }

   public AttachmentBuilder withPostRender(CustomRenderer postRenderer) {
      this.postRenderer = postRenderer;
      return this;
   }

   public AttachmentBuilder withRenderablePart() {
      this.isRenderablePart = true;
      return this;
   }

   public AttachmentBuilder withMaxStackSize(int maxStackSize) {
      this.maxStackSize = maxStackSize;
      return this;
   }

   public AttachmentBuilder withInformationProvider(Function<ItemStack, String> informationProvider) {
      this.informationProvider = informationProvider;
      return this;
   }

   public AttachmentBuilder withApply(ItemAttachment.AttachmentHandler applyHandler) {
      this.applyHandler = applyHandler;
      return this;
   }

   public AttachmentBuilder withRemove(ItemAttachment.AttachmentHandler removeHandler) {
      this.removeHandler = removeHandler;
      return this;
   }

   public AttachmentBuilder withCompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioner) {
      this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
      return this;
   }

   public AttachmentBuilder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
      this.entityPositioning = entityPositioning;
      return this;
   }

   public AttachmentBuilder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
      this.inventoryPositioning = inventoryPositioning;
      return this;
   }

   public AttachmentBuilder withThirdPersonPositioning(BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning) {
      this.thirdPersonPositioning = thirdPersonPositioning;
      return this;
   }

   public AttachmentBuilder withFirstPersonPositioning(BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning) {
      this.firstPersonPositioning = firstPersonPositioning;
      return this;
   }

   public AttachmentBuilder withFirstPersonModelPositioning(BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning) {
      this.firstPersonModelPositioning = firstPersonModelPositioning;
      return this;
   }

   public AttachmentBuilder withThirdPersonModelPositioning(BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning) {
      this.thirdPersonModelPositioning = thirdPersonModelPositioning;
      return this;
   }

   public AttachmentBuilder withInventoryModelPositioning(BiConsumer<ModelBase, ItemStack> inventoryModelPositioning) {
      this.inventoryModelPositioning = inventoryModelPositioning;
      return this;
   }

   public AttachmentBuilder withEntityModelPositioning(BiConsumer<ModelBase, ItemStack> entityModelPositioning) {
      this.entityModelPositioning = entityModelPositioning;
      return this;
   }

   public AttachmentBuilder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
      this.firstPersonLeftHandPositioning = leftHand;
      this.firstPersonRightHandPositioning = rightHand;
      return this;
   }

   public ItemAttachment build() {
      ItemAttachment attachment = this.createAttachment();
      this.configureAttachment(attachment);
      this.registerModels(attachment);
      this.registerCompatibleAttachments(attachment);
      this.registerRenderer(attachment);
      return attachment;
   }

   public <V extends ItemAttachment> V build(Class<V> target) {
      return target.cast(this.build());
   }

   protected ItemAttachment createAttachment() {
      return new ItemAttachment(this.category, this.crosshair);
   }

   private void configureAttachment(ItemAttachment attachment) {
      attachment.setUnlocalizedName("newgunrizons_" + this.name);
      attachment.setCreativeTab(this.tab);
      attachment.setMaxStackSize(this.maxStackSize);
      attachment.setName(this.name);
      attachment.setPostRenderer(this.postRenderer);
      attachment.setApplyHandler(this.applyHandler);
      attachment.setRemoveHandler(this.removeHandler);
      if (attachment.getInformationProvider() == null) {
         attachment.setInformationProvider(this.informationProvider);
      }

      if (this.isRenderablePart) {
         attachment.setRenderablePart(new NamedPart(this.name != null ? this.name : "unknown"));
      }
   }

   private void registerModels(ItemAttachment attachment) {
      if (this.model != null) {
         attachment.addModel(this.model, ensurePngExtension(this.textureName));
      }

      for (Pair<ModelBase, String> tm : this.texturedModels) {
         attachment.addModel(tm.getU(), ensurePngExtension(tm.getV()));
      }
   }

   private void registerCompatibleAttachments(ItemAttachment attachment) {
      for (CompatibleAttachment ca : this.compatibleAttachments.values()) {
         attachment.addCompatibleAttachment(ca);
      }
   }

   private void registerRenderer(ItemAttachment attachment) {
      boolean hasModels = this.model != null || !this.texturedModels.isEmpty();
      if (hasModels) {
         IItemRenderer renderer = null;
         if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            renderer = new StaticModelRenderer.Builder()
               .withEntityPositioning(this.entityPositioning)
               .withFirstPersonPositioning(this.firstPersonPositioning)
               .withThirdPersonPositioning(this.thirdPersonPositioning)
               .withInventoryPositioning(this.inventoryPositioning)
               .withEntityModelPositioning(this.entityModelPositioning)
               .withFirstPersonModelPositioning(this.firstPersonModelPositioning)
               .withThirdPersonModelPositioning(this.thirdPersonModelPositioning)
               .withInventoryModelPositioning(this.inventoryModelPositioning)
               .withFirstPersonHandPositioning(this.firstPersonLeftHandPositioning, this.firstPersonRightHandPositioning)
               .build();
         }

         NewGunrizonsMod.proxy.registerItem(this.name, attachment, renderer);
      }
   }

   static String ensurePngExtension(String filename) {
      return filename != null && !filename.endsWith(".png") ? filename + ".png" : filename;
   }

   public ModelBase getModel() {
      return this.model;
   }

   public String getTextureName() {
      return this.textureName;
   }
}
