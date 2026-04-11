package com.gtnewhorizon.newgunrizons.items;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.registry.AttachmentCompatibilityRegistry;
import com.gtnewhorizon.newgunrizons.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemAttachment extends Item {
   private final AttachmentCategory category;
   private String name;
   private final List<Pair<ModelBase, String>> texturedModels = new ArrayList<>();
   private CustomRenderer postRenderer;
   private Part renderablePart;
   private ItemAttachment.AttachmentHandler applyHandler;
   private ItemAttachment.AttachmentHandler removeHandler;
   private final String crosshair;
   private Function<ItemStack, String> informationProvider;
   private final List<CompatibleAttachment> attachments = new ArrayList<>();

   public ItemAttachment(AttachmentCategory category, String crosshair) {
      this.category = category;
      this.crosshair = crosshair != null ? "newgunrizons:textures/crosshairs/" + crosshair + ".png" : null;
   }

   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.onCreated(stack, worldIn, playerIn);
      stack.setTagCompound(new NBTTagCompound());
   }

   public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean flag) {
      if (tooltip != null && this.informationProvider != null) {
         tooltip.add(this.informationProvider.apply(itemStack));
      }

      if (tooltip != null) {
         List<String> gunNames = AttachmentCompatibilityRegistry.getCompatibleGunNames(this);
         if (!gunNames.isEmpty()) {
            StringBuilder line = new StringBuilder("Can be attached to: ");
            boolean first = true;
            for (String name : gunNames) {
               if (!first) {
                  line.append(", ");
               }
               if (!first && line.length() + name.length() > 40) {
                  tooltip.add(line.toString());
                  line = new StringBuilder("  ");
               }
               line.append(name);
               first = false;
            }
            tooltip.add(line.toString());
         }
      }
   }

   public String toString() {
      return this.name != null ? "Attachment [" + this.name + "]" : super.toString();
   }

   public void addModel(ModelBase model, String textureName) {
      this.texturedModels.add(new Pair<>(model, textureName));
   }

   public void addCompatibleAttachment(CompatibleAttachment attachment) {
      this.attachments.add(attachment);
   }

   public List<CompatibleAttachment> getAttachments() {
      return Collections.unmodifiableList(this.attachments);
   }

   public AttachmentCategory getCategory() {
      return this.category;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Pair<ModelBase, String>> getTexturedModels() {
      return this.texturedModels;
   }

   public CustomRenderer getPostRenderer() {
      return this.postRenderer;
   }

   public void setPostRenderer(CustomRenderer postRenderer) {
      this.postRenderer = postRenderer;
   }

   public Part getRenderablePart() {
      return this.renderablePart;
   }

   public void setRenderablePart(Part renderablePart) {
      this.renderablePart = renderablePart;
   }

   public ItemAttachment.AttachmentHandler getApplyHandler() {
      return this.applyHandler;
   }

   public void setApplyHandler(ItemAttachment.AttachmentHandler applyHandler) {
      this.applyHandler = applyHandler;
   }

   public ItemAttachment.AttachmentHandler getRemoveHandler() {
      return this.removeHandler;
   }

   public void setRemoveHandler(ItemAttachment.AttachmentHandler removeHandler) {
      this.removeHandler = removeHandler;
   }

   public String getCrosshair() {
      return this.crosshair;
   }

   public Function<ItemStack, String> getInformationProvider() {
      return this.informationProvider;
   }

   public void setInformationProvider(Function<ItemStack, String> informationProvider) {
      this.informationProvider = informationProvider;
   }

   @FunctionalInterface
   public interface AttachmentHandler {
      void apply(ItemAttachment var1, ItemWeaponInstance var2);
   }
}
