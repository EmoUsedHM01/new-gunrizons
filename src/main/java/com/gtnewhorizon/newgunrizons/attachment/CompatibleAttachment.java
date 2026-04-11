package com.gtnewhorizon.newgunrizons.attachment;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class CompatibleAttachment {
   private final ItemAttachment attachment;
   private final Consumer<ModelBase> modelPositioning;
   private final BiConsumer<EntityLivingBase, ItemStack> positioning;
   private final boolean isDefault;
   private final boolean isPermanent;
   private final ItemAttachment.AttachmentHandler applyHandler;
   private final ItemAttachment.AttachmentHandler removeHandler;

   public CompatibleAttachment(
      ItemAttachment attachment,
      BiConsumer<EntityLivingBase, ItemStack> positioning,
      Consumer<ModelBase> modelPositioning,
      boolean isDefault,
      boolean isPermanent
   ) {
      this.attachment = attachment;
      this.positioning = positioning;
      this.modelPositioning = modelPositioning;
      this.isDefault = isDefault;
      this.isPermanent = isPermanent;
      this.applyHandler = null;
      this.removeHandler = null;
   }

   public CompatibleAttachment(
      ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning, Consumer<ModelBase> modelPositioning, boolean isDefault
   ) {
      this(attachment, positioning, modelPositioning, isDefault, false);
   }

   public CompatibleAttachment(ItemAttachment attachment, ItemAttachment.AttachmentHandler applyHandler, ItemAttachment.AttachmentHandler removeHandler) {
      this.attachment = attachment;
      this.positioning = null;
      this.modelPositioning = null;
      this.isDefault = false;
      this.isPermanent = false;
      this.applyHandler = applyHandler;
      this.removeHandler = removeHandler;
   }

   public CompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> modelPositioning) {
      this(attachment, null, modelPositioning, false);
   }

   public CompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> modelPositioning, boolean isDefault) {
      this(attachment, null, modelPositioning, isDefault);
   }

   public ItemAttachment getAttachment() {
      return this.attachment;
   }

   public Consumer<ModelBase> getModelPositioning() {
      return this.modelPositioning;
   }

   public BiConsumer<EntityLivingBase, ItemStack> getPositioning() {
      return this.positioning;
   }

   public boolean isDefault() {
      return this.isDefault;
   }

   public boolean isPermanent() {
      return this.isPermanent;
   }

   public ItemAttachment.AttachmentHandler getApplyHandler() {
      return this.applyHandler;
   }

   public ItemAttachment.AttachmentHandler getRemoveHandler() {
      return this.removeHandler;
   }
}
