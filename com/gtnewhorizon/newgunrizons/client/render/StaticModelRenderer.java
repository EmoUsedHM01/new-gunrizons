package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.util.Pair;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class StaticModelRenderer implements IItemRenderer {
   private static final float MODEL_AGE_IN_TICKS = -0.4F;
   private static final float MODEL_SCALE = 0.08F;
   private final Consumer<ItemStack> entityPositioning;
   private final Consumer<ItemStack> inventoryPositioning;
   private final BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning;
   private final BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning;
   private final BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning;
   private final BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning;
   private final BiConsumer<ModelBase, ItemStack> inventoryModelPositioning;
   private final BiConsumer<ModelBase, ItemStack> entityModelPositioning;
   private final Consumer<RenderContext> firstPersonLeftHandPositioning;
   private final Consumer<RenderContext> firstPersonRightHandPositioning;

   private StaticModelRenderer(StaticModelRenderer.Builder builder) {
      this.entityPositioning = builder.entityPositioning;
      this.inventoryPositioning = builder.inventoryPositioning;
      this.thirdPersonPositioning = builder.thirdPersonPositioning;
      this.firstPersonPositioning = builder.firstPersonPositioning;
      this.firstPersonModelPositioning = builder.firstPersonModelPositioning;
      this.thirdPersonModelPositioning = builder.thirdPersonModelPositioning;
      this.inventoryModelPositioning = builder.inventoryModelPositioning;
      this.entityModelPositioning = builder.entityModelPositioning;
      this.firstPersonLeftHandPositioning = builder.firstPersonLeftHandPositioning;
      this.firstPersonRightHandPositioning = builder.firstPersonRightHandPositioning;
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
      int currentTextureId = 0;
      if (type == ItemRenderType.INVENTORY) {
         currentTextureId = Framebuffers.getCurrentTexture();
      }

      GL11.glPushMatrix();
      GL11.glScaled(-1.0, -1.0, 1.0);
      EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
      RenderContext renderContext = new RenderContext(player, itemStack);
      switch (type) {
         case ENTITY:
            this.entityPositioning.accept(itemStack);
            break;
         case INVENTORY:
            this.inventoryPositioning.accept(itemStack);
            break;
         case EQUIPPED:
            this.thirdPersonPositioning.accept(player, itemStack);
            break;
         case EQUIPPED_FIRST_PERSON:
            this.firstPersonPositioning.accept(player, itemStack);
            WeaponRenderer.renderLeftArm(player, renderContext, (p, c) -> this.firstPersonLeftHandPositioning.accept(c));
            WeaponRenderer.renderRightArm(player, renderContext, (p, c) -> this.firstPersonRightHandPositioning.accept(c));
      }

      this.renderAttachmentModels(renderContext, itemStack, type);
      GL11.glPopMatrix();
      if (currentTextureId != 0) {
         Framebuffers.bindTexture(currentTextureId);
      }
   }

   private void renderAttachmentModels(RenderContext renderContext, ItemStack itemStack, ItemRenderType type) {
      if (!(itemStack.func_77973_b() instanceof ItemAttachment)) {
         throw new IllegalArgumentException("StaticModelRenderer requires an ItemAttachment, got: " + itemStack.func_77973_b());
      } else {
         ItemAttachment attachment = (ItemAttachment)itemStack.func_77973_b();
         GL11.glPushMatrix();

         for (Pair<ModelBase, String> texturedModel : attachment.getTexturedModels()) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(new ResourceLocation("newgunrizons:textures/models/" + texturedModel.getV()));
            GL11.glPushMatrix();
            GL11.glPushAttrib(8192);
            ModelBase model = texturedModel.getU();
            this.applyModelPositioning(model, itemStack, type);
            model.func_78088_a(null, 0.0F, 0.0F, -0.4F, 0.0F, 0.0F, 0.08F);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         }

         CustomRenderer postRenderer = attachment.getPostRenderer();
         if (postRenderer != null) {
            renderContext.setAgeInTicks(-0.4F);
            renderContext.setScale(0.08F);
            renderContext.setTransformType(TransformType.fromItemRenderType(type));
            renderContext.setItemInstance(ItemInstanceRegistry.INSTANCE.getItemInstance(renderContext.getPlayer(), itemStack));
            GL11.glPushMatrix();
            GL11.glPushAttrib(8193);
            postRenderer.render(renderContext);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
      }
   }

   private void applyModelPositioning(ModelBase model, ItemStack itemStack, ItemRenderType type) {
      switch (type) {
         case ENTITY:
            this.entityModelPositioning.accept(model, itemStack);
            break;
         case INVENTORY:
            this.inventoryModelPositioning.accept(model, itemStack);
            break;
         case EQUIPPED:
            this.thirdPersonModelPositioning.accept(model, itemStack);
            break;
         case EQUIPPED_FIRST_PERSON:
            this.firstPersonModelPositioning.accept(model, itemStack);
      }
   }

   public static class Builder {
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

      public StaticModelRenderer.Builder withFirstPersonPositioning(BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning) {
         this.firstPersonPositioning = firstPersonPositioning;
         return this;
      }

      public StaticModelRenderer.Builder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public StaticModelRenderer.Builder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
         this.entityPositioning = entityPositioning;
         return this;
      }

      public StaticModelRenderer.Builder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
         this.inventoryPositioning = inventoryPositioning;
         return this;
      }

      public StaticModelRenderer.Builder withThirdPersonPositioning(BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning) {
         this.thirdPersonPositioning = thirdPersonPositioning;
         return this;
      }

      public StaticModelRenderer.Builder withFirstPersonModelPositioning(BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning) {
         this.firstPersonModelPositioning = firstPersonModelPositioning;
         return this;
      }

      public StaticModelRenderer.Builder withEntityModelPositioning(BiConsumer<ModelBase, ItemStack> entityModelPositioning) {
         this.entityModelPositioning = entityModelPositioning;
         return this;
      }

      public StaticModelRenderer.Builder withInventoryModelPositioning(BiConsumer<ModelBase, ItemStack> inventoryModelPositioning) {
         this.inventoryModelPositioning = inventoryModelPositioning;
         return this;
      }

      public StaticModelRenderer.Builder withThirdPersonModelPositioning(BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning) {
         this.thirdPersonModelPositioning = thirdPersonModelPositioning;
         return this;
      }

      public StaticModelRenderer build() {
         if (this.inventoryPositioning == null) {
            this.inventoryPositioning = itemStack -> GL11.glTranslatef(0.0F, 0.12F, 0.0F);
         }

         if (this.entityPositioning == null) {
            this.entityPositioning = itemStack -> {};
         }

         if (this.firstPersonPositioning == null) {
            this.firstPersonPositioning = (player, itemStack) -> {};
         }

         if (this.thirdPersonPositioning == null) {
            this.thirdPersonPositioning = (player, itemStack) -> {};
         }

         if (this.inventoryModelPositioning == null) {
            this.inventoryModelPositioning = (m, i) -> {};
         }

         if (this.entityModelPositioning == null) {
            this.entityModelPositioning = (m, i) -> {};
         }

         if (this.firstPersonModelPositioning == null) {
            this.firstPersonModelPositioning = (m, i) -> {};
         }

         if (this.thirdPersonModelPositioning == null) {
            this.thirdPersonModelPositioning = (m, i) -> {};
         }

         if (this.firstPersonLeftHandPositioning == null) {
            this.firstPersonLeftHandPositioning = c -> GL11.glScalef(0.0F, 0.0F, 0.0F);
         }

         if (this.firstPersonRightHandPositioning == null) {
            this.firstPersonRightHandPositioning = c -> GL11.glScalef(0.0F, 0.0F, 0.0F);
         }

         return new StaticModelRenderer(this);
      }

      public Consumer<ItemStack> getEntityPositioning() {
         return this.entityPositioning;
      }

      public Consumer<ItemStack> getInventoryPositioning() {
         return this.inventoryPositioning;
      }

      public BiConsumer<EntityPlayer, ItemStack> getThirdPersonPositioning() {
         return this.thirdPersonPositioning;
      }

      public BiConsumer<EntityPlayer, ItemStack> getFirstPersonPositioning() {
         return this.firstPersonPositioning;
      }

      public BiConsumer<ModelBase, ItemStack> getFirstPersonModelPositioning() {
         return this.firstPersonModelPositioning;
      }

      public BiConsumer<ModelBase, ItemStack> getThirdPersonModelPositioning() {
         return this.thirdPersonModelPositioning;
      }

      public BiConsumer<ModelBase, ItemStack> getInventoryModelPositioning() {
         return this.inventoryModelPositioning;
      }

      public BiConsumer<ModelBase, ItemStack> getEntityModelPositioning() {
         return this.entityModelPositioning;
      }

      public Consumer<RenderContext> getFirstPersonLeftHandPositioning() {
         return this.firstPersonLeftHandPositioning;
      }

      public Consumer<RenderContext> getFirstPersonRightHandPositioning() {
         return this.firstPersonRightHandPositioning;
      }
   }
}
