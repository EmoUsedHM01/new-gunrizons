package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.animation.MatrixHelper;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.util.vector.Matrix4f;

public class RenderContext {
   private EntityLivingBase player;
   private ItemStack itemStack;
   private float limbSwing;
   private float limbSwingAmount;
   private float ageInTicks;
   private float netHeadYaw;
   private float headPitch;
   private float scale;
   private float transitionProgress;
   private TransformType compatibleTransformType;
   private RenderableState fromState;
   private RenderableState toState;
   private ItemInstance<?> itemInstance;
   private final Map<Part, Matrix4f> attachablePartPositions;

   public RenderContext(EntityLivingBase player, ItemStack itemStack) {
      this.player = player;
      this.itemStack = itemStack;
      this.attachablePartPositions = new HashMap<>();
   }

   public void setPlayer(EntityPlayer player) {
      this.player = player;
   }

   public void setWeapon(ItemStack weapon) {
      this.itemStack = weapon;
   }

   public ItemStack getWeapon() {
      return this.itemStack;
   }

   public TransformType getTransformType() {
      return this.compatibleTransformType;
   }

   public void setTransformType(TransformType compatibleTransformType) {
      this.compatibleTransformType = compatibleTransformType;
   }

   public ItemWeaponInstance getWeaponInstance() {
      if (this.itemInstance instanceof ItemWeaponInstance) {
         return (ItemWeaponInstance)this.itemInstance;
      } else {
         ItemInstance<?> itemInstance = ItemInstanceRegistry.INSTANCE.getItemInstance(this.player, this.itemStack);
         return itemInstance instanceof ItemWeaponInstance ? (ItemWeaponInstance)itemInstance : null;
      }
   }

   public void capturePartPosition(Part part) {
      this.attachablePartPositions.put(part, MatrixHelper.captureMatrix());
   }

   public Matrix4f getPartPosition(Part part) {
      if (part == null) {
         part = Part.MAIN_ITEM;
      }

      return this.attachablePartPositions.get(part);
   }

   public EntityLivingBase getPlayer() {
      return this.player;
   }

   public void setLimbSwing(float limbSwing) {
      this.limbSwing = limbSwing;
   }

   public float getLimbSwing() {
      return this.limbSwing;
   }

   public void setLimbSwingAmount(float limbSwingAmount) {
      this.limbSwingAmount = limbSwingAmount;
   }

   public float getLimbSwingAmount() {
      return this.limbSwingAmount;
   }

   public void setAgeInTicks(float ageInTicks) {
      this.ageInTicks = ageInTicks;
   }

   public float getAgeInTicks() {
      return this.ageInTicks;
   }

   public void setNetHeadYaw(float netHeadYaw) {
      this.netHeadYaw = netHeadYaw;
   }

   public float getNetHeadYaw() {
      return this.netHeadYaw;
   }

   public void setHeadPitch(float headPitch) {
      this.headPitch = headPitch;
   }

   public float getHeadPitch() {
      return this.headPitch;
   }

   public void setScale(float scale) {
      this.scale = scale;
   }

   public float getScale() {
      return this.scale;
   }

   public void setTransitionProgress(float transitionProgress) {
      this.transitionProgress = transitionProgress;
   }

   public float getTransitionProgress() {
      return this.transitionProgress;
   }

   public void setFromState(RenderableState fromState) {
      this.fromState = fromState;
   }

   public RenderableState getFromState() {
      return this.fromState;
   }

   public void setToState(RenderableState toState) {
      this.toState = toState;
   }

   public RenderableState getToState() {
      return this.toState;
   }

   public void setItemInstance(ItemInstance<?> itemInstance) {
      this.itemInstance = itemInstance;
   }

   public ItemInstance<?> getItemInstance() {
      return this.itemInstance;
   }
}
