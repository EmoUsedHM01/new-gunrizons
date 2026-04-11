package com.gtnewhorizon.newgunrizons.entities;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
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

   @Override
   public void onImpact(MovingObjectPosition position) {
      if (!this.worldObj.isRemote && this.weapon != null) {
         if (this.explosionRadius > 0.0F) {
            this.handleExplosiveImpact(position);
         } else if (position.entityHit != null) {
            this.handleEntityHit(position.entityHit);
         } else {
            this.spawnBlockHitParticles(position);
         }

         this.setDead();
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

   private void handleEntityHit(Entity target) {
      DamageSource source = this.getThrower() == null ? new DamageSource("generic") : DamageSource.causeThrownDamage(this, this.getThrower());
      target.attackEntityFrom(source, this.damage);
      target.hurtResistantTime = 0;
      target.prevRotationYaw -= 0.3F;
      this.spawnBloodParticles(target);
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
   }

   @Override
   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.weapon = (ItemWeapon)Item.getItemById(buffer.readInt());
      this.damage = buffer.readFloat();
      this.explosionRadius = buffer.readFloat();
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
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("entityItem", Item.getIdFromItem(this.weapon));
      tagCompound.setFloat("damage", this.damage);
      tagCompound.setFloat("explosionRadius", this.explosionRadius);
   }

   @Override
   public boolean canCollideWithBlock(Block block, int metadata) {
      return !PASS_THROUGH_BLOCKS.contains(block) && super.canCollideWithBlock(block, metadata);
   }

   public ItemWeapon getWeapon() {
      return this.weapon;
   }
}
