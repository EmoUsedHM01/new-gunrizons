package com.gtnewhorizon.newgunrizons.items;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentContainer;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeAttackAspect;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeState;
import com.gtnewhorizon.newgunrizons.items.instances.ItemGrenadeInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceFactory;
import com.gtnewhorizon.newgunrizons.registry.Sounds;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemGrenade extends Item implements ItemInstanceFactory<ItemGrenadeInstance, GrenadeState>, AttachmentContainer, Updatable {
   public static final int EXPLODE_ON_IMPACT = -1;
   private String bounceHardSound;
   private String bounceSoftSound;
   private String explosionSound;
   private String safetyPinOffSound;
   private String throwSound;
   private final String name;
   private final GrenadeRenderer renderer;
   private final List<String> textureNames;
   private final int explosionTimeout;
   private final float explosionStrength;
   private final Map<ItemAttachment, CompatibleAttachment> compatibleAttachments;
   private final Supplier<Float> velocity;
   private final Supplier<Float> farVelocity;
   private final Supplier<Float> gravityVelocity;
   private final Supplier<Float> rotationSlowdownFactor;
   private final float effectiveRadius;
   private final float fragmentDamage;
   private final int fragmentCount;

   private ItemGrenade(ItemGrenade.Builder builder) {
      this.name = builder.name;
      this.renderer = builder.renderer;
      this.textureNames = new ArrayList<>(builder.textureNames);
      this.explosionTimeout = builder.explosionTimeout;
      this.explosionStrength = builder.explosionStrength;
      this.compatibleAttachments = builder.compatibleAttachments;
      this.velocity = builder.velocity;
      this.farVelocity = builder.farVelocity;
      this.gravityVelocity = builder.gravityVelocity;
      this.rotationSlowdownFactor = builder.rotationSlowdownFactor;
      this.effectiveRadius = builder.effectiveRadius;
      this.fragmentDamage = builder.fragmentDamage;
      this.fragmentCount = builder.fragmentCount;
   }

   public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.func_77622_d(stack, worldIn, playerIn);
      stack.func_77982_d(new NBTTagCompound());
   }

   public String getTextureName() {
      return this.textureNames.get(0);
   }

   public boolean hasSafetyPin() {
      return this.explosionTimeout > 0;
   }

   @Override
   public List<CompatibleAttachment> getActiveAttachments(EntityLivingBase player, ItemStack itemStack) {
      return new ArrayList<>(this.compatibleAttachments.values());
   }

   public ItemGrenadeInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
      ItemGrenadeInstance instance = new ItemGrenadeInstance(slot, player, itemStack);
      instance.setState(GrenadeState.READY);
      return instance;
   }

   public void attack(EntityPlayer player, boolean throwingFar) {
      GrenadeAttackAspect.INSTANCE.onAttackButtonClick(player, throwingFar);
   }

   public void attackUp(EntityPlayer player, boolean throwingFar) {
      GrenadeAttackAspect.INSTANCE.onAttackButtonUp(player, throwingFar);
   }

   @Override
   public void update(EntityPlayer player) {
      GrenadeAttackAspect.INSTANCE.onUpdate(player);
   }

   public long getReequipTimeout() {
      return 800L;
   }

   public long getTotalThrowingDuration() {
      return this.renderer.getTotalThrowingDuration();
   }

   public float getVelocity() {
      return this.velocity.get();
   }

   public float getFarVelocity() {
      return this.farVelocity.get();
   }

   public float getGravityVelocity() {
      return this.gravityVelocity.get();
   }

   public float getRotationSlowdownFactor() {
      return this.rotationSlowdownFactor.get();
   }

   public String getBounceHardSound() {
      return this.bounceHardSound;
   }

   public String getBounceSoftSound() {
      return this.bounceSoftSound;
   }

   public String getExplosionSound() {
      return this.explosionSound;
   }

   public String getSafetyPinOffSound() {
      return this.safetyPinOffSound;
   }

   public String getThrowSound() {
      return this.throwSound;
   }

   public String getName() {
      return this.name;
   }

   public GrenadeRenderer getRenderer() {
      return this.renderer;
   }

   public int getExplosionTimeout() {
      return this.explosionTimeout;
   }

   public float getExplosionStrength() {
      return this.explosionStrength;
   }

   public Map<ItemAttachment, CompatibleAttachment> getCompatibleAttachments() {
      return this.compatibleAttachments;
   }

   public float getEffectiveRadius() {
      return this.effectiveRadius;
   }

   public float getFragmentDamage() {
      return this.fragmentDamage;
   }

   public int getFragmentCount() {
      return this.fragmentCount;
   }

   public static class Builder {
      public String name;
      protected Map<ItemAttachment, CompatibleAttachment> compatibleAttachments = new HashMap<>();
      private Supplier<Float> velocity = () -> 1.0F;
      private Supplier<Float> farVelocity = () -> 1.3F;
      private Supplier<Float> gravityVelocity = () -> 0.06F;
      private int maxStackSize = 1;
      private int explosionTimeout = 3000;
      private float explosionStrength = 2.0F;
      protected CreativeTabs tab;
      private GrenadeRenderer renderer;
      List<String> textureNames = new ArrayList<>();
      private Supplier<Float> rotationSlowdownFactor = () -> 0.99F;
      private String bounceHardSound;
      private String bounceSoftSound;
      private String explosionSound;
      private String safetyPinOffSound;
      private String throwSound;
      private float effectiveRadius = 20.0F;
      private float fragmentDamage = 15.0F;
      private int fragmentCount = 100;

      public ItemGrenade.Builder withName(String name) {
         this.name = name;
         return this;
      }

      public ItemGrenade.Builder withCreativeTab(CreativeTabs tab) {
         this.tab = tab;
         return this;
      }

      public ItemGrenade.Builder withVelocity(Supplier<Float> velocity) {
         this.velocity = velocity;
         return this;
      }

      public ItemGrenade.Builder withFarVelocity(Supplier<Float> farVelocity) {
         this.farVelocity = farVelocity;
         return this;
      }

      public ItemGrenade.Builder withGravityVelocity(Supplier<Float> gravityVelocity) {
         this.gravityVelocity = gravityVelocity;
         return this;
      }

      public ItemGrenade.Builder withRotationSlowdownFactor(Supplier<Float> rotationSlowdownFactor) {
         this.rotationSlowdownFactor = rotationSlowdownFactor;
         return this;
      }

      public ItemGrenade.Builder withExplosionStrength(float explosionStrength) {
         this.explosionStrength = explosionStrength;
         return this;
      }

      public ItemGrenade.Builder withExplosionTimeout(int explosionTimeout) {
         this.explosionTimeout = explosionTimeout;
         return this;
      }

      public ItemGrenade.Builder withExplosionOnImpact() {
         this.explosionTimeout = -1;
         return this;
      }

      public ItemGrenade.Builder withTextureNames(String... textureNames) {
         for (String textureName : textureNames) {
            this.textureNames.add(textureName.toLowerCase() + ".png");
         }

         return this;
      }

      public ItemGrenade.Builder withCompatibleAttachment(ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, null, true));
         return this;
      }

      public ItemGrenade.Builder withMaxStackSize(int maxStackSize) {
         this.maxStackSize = maxStackSize;
         return this;
      }

      public ItemGrenade.Builder withRenderer(GrenadeRenderer renderer) {
         this.renderer = renderer;
         return this;
      }

      public ItemGrenade.Builder withBounceHardSound(String sound) {
         this.bounceHardSound = sound;
         return this;
      }

      public ItemGrenade.Builder withBounceSoftSound(String sound) {
         this.bounceSoftSound = sound;
         return this;
      }

      public ItemGrenade.Builder withExplosionSound(String sound) {
         this.explosionSound = sound;
         return this;
      }

      public ItemGrenade.Builder withSafetyPinOffSound(String sound) {
         this.safetyPinOffSound = sound;
         return this;
      }

      public ItemGrenade.Builder withThrowSound(String sound) {
         this.throwSound = sound;
         return this;
      }

      public ItemGrenade.Builder withEffectiveRadius(float effectiveRadius) {
         this.effectiveRadius = effectiveRadius;
         return this;
      }

      public ItemGrenade.Builder withFragmentDamage(float fragmentDamage) {
         this.fragmentDamage = fragmentDamage;
         return this;
      }

      public ItemGrenade.Builder withFragmentCount(int fragmentCount) {
         this.fragmentCount = fragmentCount;
         return this;
      }

      public ItemGrenade build() {
         ItemGrenade grenade = new ItemGrenade(this);
         grenade.func_77655_b("newgunrizons_" + this.name);
         grenade.func_77637_a(this.tab);
         grenade.field_77777_bU = this.maxStackSize;
         grenade.bounceHardSound = Sounds.resolve(this.bounceHardSound);
         grenade.bounceSoftSound = Sounds.resolve(this.bounceSoftSound);
         grenade.explosionSound = Sounds.resolve(this.explosionSound);
         grenade.safetyPinOffSound = Sounds.resolve(this.safetyPinOffSound);
         grenade.throwSound = Sounds.resolve(this.throwSound);
         NewGunrizonsMod.proxy.registerItem(this.name, grenade, this.renderer);
         return grenade;
      }
   }
}
