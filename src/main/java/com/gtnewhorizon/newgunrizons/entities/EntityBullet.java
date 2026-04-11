package com.gtnewhorizon.newgunrizons.entities;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.enchantments.ModEnchantments;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.network.BlockHitMessage;
import com.gtnewhorizon.newgunrizons.network.SpawnParticleMessage;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBullet extends EntityProjectile {
   private static final String TAG_ENTITY_ITEM = "entityItem";
   private static final String TAG_DAMAGE = "damage";
   private static final String TAG_EXPLOSION_RADIUS = "explosionRadius";
   private static final float DEFAULT_DAMAGE = 6.0F;
   private static final float BLEEDING_COEFFICIENT = 1.0F;
   private static final double PARTICLE_BROADCAST_RANGE = 100.0;
   private static final Set<Block> PASS_THROUGH_BLOCKS = Collections.unmodifiableSet(
      new HashSet<>(
         Arrays.asList(
            Blocks.grass,
            Blocks.air,
            Blocks.tallgrass,
            Blocks.leaves,
            Blocks.leaves2,
            Blocks.fire,
            Blocks.hay_block,
            Blocks.double_plant,
            Blocks.web,
            Blocks.wheat
         )
      )
   );
   private ItemWeapon weapon;
   private float damage = 6.0F;
   private float explosionRadius;

   // Enchantment levels
   private int armorPiercingLevel;
   private int collateralLevel;
   private int hollowPointLevel;
   private int incendiaryLevel;
   private int knockbackLevel;

   public EntityBullet(World world) {
      super(world);
   }

   public EntityBullet(
      ItemWeapon weapon, World world, EntityLivingBase thrower, float speed, float gravityVelocity, float inaccuracy, float damage, float explosionRadius
   ) {
      super(world, thrower, speed, gravityVelocity, inaccuracy);
      this.weapon = weapon;
      this.damage = damage;
      this.explosionRadius = explosionRadius;
   }

   public void applyEnchantments(ItemStack weaponStack) {
      this.armorPiercingLevel = ModEnchantments.getLevel(ModEnchantments.armorPiercing, weaponStack);
      this.collateralLevel = ModEnchantments.getLevel(ModEnchantments.collateral, weaponStack);
      this.hollowPointLevel = ModEnchantments.getLevel(ModEnchantments.hollowPoint, weaponStack);
      this.incendiaryLevel = ModEnchantments.getLevel(ModEnchantments.incendiary, weaponStack);
      this.knockbackLevel = ModEnchantments.getLevel(ModEnchantments.knockback, weaponStack);
   }

   @Override
   public void onImpact(MovingObjectPosition position) {
      if (!this.worldObj.isRemote && this.weapon != null) {
         if (this.explosionRadius > 0.0F) {
            this.handleExplosiveImpact(position);
            this.setDead();
         } else if (position.entityHit != null) {
            this.handleEntityHit(position.entityHit, position.hitVec);
            if (this.collateralLevel <= 0) {
               this.setDead();
            }
            // Collateral: bullet continues through
         } else {
            this.handleBlockHit(position);
            this.setDead();
         }
      }
   }

   private void handleExplosiveImpact(MovingObjectPosition position) {
      Explosion.createServerSideExplosion(
         this.worldObj,
         this,
         position.hitVec.xCoord,
         position.hitVec.yCoord,
         position.hitVec.zCoord,
         this.explosionRadius,
         false,
         true
      );
   }

   private boolean isHeadshot(Entity target, Vec3 hitVec) {
      if (hitVec == null || !(target instanceof EntityLivingBase)) {
         return false;
      }
      double entityHeight = target.boundingBox.maxY - target.boundingBox.minY;
      double headThreshold = target.boundingBox.maxY - entityHeight * 0.25;
      return hitVec.yCoord >= headThreshold;
   }

   private void handleEntityHit(Entity target, Vec3 hitVec) {
      // Calculate total damage with Hollow Point (same as Sharpness: 0.5 + 0.5 * level)
      float totalDamage = this.damage;
      if (this.hollowPointLevel > 0) {
         totalDamage += 0.5F + 0.5F * this.hollowPointLevel;
      }

      // Headshot: double damage if bullet hits the top 25% of the entity
      if (this.isHeadshot(target, hitVec)) {
         totalDamage *= 2.0F;
      }

      // Armor Piercing: split damage into regular and armor-bypassing portions
      if (this.armorPiercingLevel > 0) {
         float bypassPercent = Math.min(this.armorPiercingLevel * 0.25F, 1.0F);
         float regularDamage = totalDamage * (1.0F - bypassPercent);
         float piercingDamage = totalDamage * bypassPercent;

         // Apply regular damage (affected by armor)
         DamageSource regularSource = this.getThrower() == null
            ? new DamageSource("generic") : DamageSource.causeThrownDamage(this, this.getThrower());
         if (regularDamage > 0) {
            target.attackEntityFrom(regularSource, regularDamage);
            target.hurtResistantTime = 0;
         }

         // Apply armor-piercing damage (bypasses armor)
         DamageSource piercingSource = this.getThrower() == null
            ? new DamageSource("generic") : DamageSource.causeThrownDamage(this, this.getThrower());
         piercingSource.setDamageBypassesArmor();
         target.attackEntityFrom(piercingSource, piercingDamage);
         target.hurtResistantTime = 0;
      } else {
         DamageSource source = this.getThrower() == null
            ? new DamageSource("generic") : DamageSource.causeThrownDamage(this, this.getThrower());
         target.attackEntityFrom(source, totalDamage);
         target.hurtResistantTime = 0;
      }

      // Knockback
      if (this.knockbackLevel > 0 && target instanceof EntityLivingBase) {
         float knockStrength = this.knockbackLevel * 0.4F;
         double dx = this.motionX;
         double dz = this.motionZ;
         double dist = MathHelper.sqrt_double(dx * dx + dz * dz);
         if (dist > 0.0) {
            target.addVelocity(dx / dist * knockStrength, 0.1, dz / dist * knockStrength);
         }
      }

      // Incendiary: set target on fire for 5 seconds (100 ticks)
      if (this.incendiaryLevel > 0) {
         target.setFire(5);
      }

      target.prevRotationYaw -= 0.3F;
      this.spawnBloodParticles(target);
   }

   private void handleBlockHit(MovingObjectPosition position) {
      // Incendiary: set fire on block hit
      if (this.incendiaryLevel > 0) {
         int fireX = position.blockX + net.minecraft.util.Facing.offsetsXForSide[position.sideHit];
         int fireY = position.blockY + net.minecraft.util.Facing.offsetsYForSide[position.sideHit];
         int fireZ = position.blockZ + net.minecraft.util.Facing.offsetsZForSide[position.sideHit];
         if (this.worldObj.isAirBlock(fireX, fireY, fireZ)) {
            this.worldObj.setBlock(fireX, fireY, fireZ, Blocks.fire);
         }
      }
      this.spawnBlockHitParticles(position);
   }

   private void spawnBlockHitParticles(MovingObjectPosition position) {
      TargetPoint broadcastPoint = new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 100.0);
      NewGunrizonsMod.CHANNEL
         .sendToAllAround(new BlockHitMessage(position.blockX, position.blockY, position.blockZ, position.sideHit), broadcastPoint);
   }

   @Override
   protected void onWaterImpact(double x, double y, double z) {
      if (this.weapon != null) {
         TargetPoint broadcastPoint = new TargetPoint(this.dimension, x, y, z, 100.0);
         NewGunrizonsMod.CHANNEL.sendToAllAround(new SpawnParticleMessage(SpawnParticleMessage.ParticleType.WATER_SPLASH, 8, x, y, z), broadcastPoint);
      }
   }

   private void spawnBloodParticles(Entity target) {
      double speed = Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ)
         + 1.0;
      double hitX = target.posX - this.motionX / speed;
      double hitY = target.posY - this.motionY / speed;
      double hitZ = target.posZ - this.motionZ / speed;
      int count = (int)(this.getParticleCount() * 1.0F);
      TargetPoint broadcastPoint = new TargetPoint(target.dimension, this.posX, this.posY, this.posZ, 100.0);
      NewGunrizonsMod.CHANNEL.sendToAllAround(new SpawnParticleMessage(SpawnParticleMessage.ParticleType.BLOOD, count, hitX, hitY, hitZ), broadcastPoint);
   }

   private int getParticleCount() {
      float x = this.damage - 30.0F;
      return (int)(-0.11 * x * x + 100.0);
   }

   @Override
   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeInt(Item.getIdFromItem(this.weapon));
      buffer.writeFloat(this.damage);
      buffer.writeFloat(this.explosionRadius);
      buffer.writeByte(this.armorPiercingLevel);
      buffer.writeByte(this.collateralLevel);
      buffer.writeByte(this.hollowPointLevel);
      buffer.writeByte(this.incendiaryLevel);
      buffer.writeByte(this.knockbackLevel);
   }

   @Override
   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.weapon = (ItemWeapon)Item.getItemById(buffer.readInt());
      this.damage = buffer.readFloat();
      this.explosionRadius = buffer.readFloat();
      this.armorPiercingLevel = buffer.readByte();
      this.collateralLevel = buffer.readByte();
      this.hollowPointLevel = buffer.readByte();
      this.incendiaryLevel = buffer.readByte();
      this.knockbackLevel = buffer.readByte();
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      Item item = Item.getItemById(tagCompound.getInteger("entityItem"));
      if (item instanceof ItemWeapon) {
         this.weapon = (ItemWeapon)item;
      }

      this.damage = tagCompound.getFloat("damage");
      this.explosionRadius = tagCompound.getFloat("explosionRadius");
      this.armorPiercingLevel = tagCompound.getByte("armorPiercing");
      this.collateralLevel = tagCompound.getByte("collateral");
      this.hollowPointLevel = tagCompound.getByte("hollowPoint");
      this.incendiaryLevel = tagCompound.getByte("incendiary");
      this.knockbackLevel = tagCompound.getByte("knockback");
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("entityItem", Item.getIdFromItem(this.weapon));
      tagCompound.setFloat("damage", this.damage);
      tagCompound.setFloat("explosionRadius", this.explosionRadius);
      tagCompound.setByte("armorPiercing", (byte)this.armorPiercingLevel);
      tagCompound.setByte("collateral", (byte)this.collateralLevel);
      tagCompound.setByte("hollowPoint", (byte)this.hollowPointLevel);
      tagCompound.setByte("incendiary", (byte)this.incendiaryLevel);
      tagCompound.setByte("knockback", (byte)this.knockbackLevel);
   }

   @Override
   public boolean canCollideWithBlock(Block block, int metadata) {
      return !PASS_THROUGH_BLOCKS.contains(block) && super.canCollideWithBlock(block, metadata);
   }

   public ItemWeapon getWeapon() {
      return this.weapon;
   }
}
