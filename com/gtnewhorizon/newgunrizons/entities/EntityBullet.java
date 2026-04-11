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
            Blocks.field_150349_c,
            Blocks.field_150350_a,
            Blocks.field_150329_H,
            Blocks.field_150362_t,
            Blocks.field_150361_u,
            Blocks.field_150480_ab,
            Blocks.field_150407_cf,
            Blocks.field_150398_cm,
            Blocks.field_150321_G,
            Blocks.field_150464_aj
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
      if (!this.field_70170_p.field_72995_K && this.weapon != null) {
         if (this.explosionRadius > 0.0F) {
            this.handleExplosiveImpact(position);
         } else if (position.field_72308_g != null) {
            this.handleEntityHit(position.field_72308_g);
         } else {
            this.spawnBlockHitParticles(position);
         }

         this.func_70106_y();
      }
   }

   private void handleExplosiveImpact(MovingObjectPosition position) {
      Explosion.createServerSideExplosion(
         this.field_70170_p,
         this,
         position.field_72307_f.field_72450_a,
         position.field_72307_f.field_72448_b,
         position.field_72307_f.field_72449_c,
         this.explosionRadius,
         false,
         true
      );
   }

   private void handleEntityHit(Entity target) {
      DamageSource source = this.getThrower() == null ? new DamageSource("generic") : DamageSource.func_76356_a(this, this.getThrower());
      target.func_70097_a(source, this.damage);
      target.field_70172_ad = 0;
      target.field_70126_B -= 0.3F;
      this.spawnBloodParticles(target);
   }

   private void spawnBlockHitParticles(MovingObjectPosition position) {
      TargetPoint broadcastPoint = new TargetPoint(this.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 100.0);
      NewGunrizonsMod.CHANNEL
         .sendToAllAround(new BlockHitMessage(position.field_72311_b, position.field_72312_c, position.field_72309_d, position.field_72310_e), broadcastPoint);
   }

   @Override
   protected void onWaterImpact(double x, double y, double z) {
      if (this.weapon != null) {
         TargetPoint broadcastPoint = new TargetPoint(this.field_71093_bK, x, y, z, 100.0);
         NewGunrizonsMod.CHANNEL.sendToAllAround(new SpawnParticleMessage(SpawnParticleMessage.ParticleType.WATER_SPLASH, 8, x, y, z), broadcastPoint);
      }
   }

   private void spawnBloodParticles(Entity target) {
      double speed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y)
         + 1.0;
      double hitX = target.field_70165_t - this.field_70159_w / speed;
      double hitY = target.field_70163_u - this.field_70181_x / speed;
      double hitZ = target.field_70161_v - this.field_70179_y / speed;
      int count = (int)(this.getParticleCount() * 1.0F);
      TargetPoint broadcastPoint = new TargetPoint(target.field_71093_bK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 100.0);
      NewGunrizonsMod.CHANNEL.sendToAllAround(new SpawnParticleMessage(SpawnParticleMessage.ParticleType.BLOOD, count, hitX, hitY, hitZ), broadcastPoint);
   }

   private int getParticleCount() {
      float x = this.damage - 30.0F;
      return (int)(-0.11 * x * x + 100.0);
   }

   @Override
   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeInt(Item.func_150891_b(this.weapon));
      buffer.writeFloat(this.damage);
      buffer.writeFloat(this.explosionRadius);
   }

   @Override
   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.weapon = (ItemWeapon)Item.func_150899_d(buffer.readInt());
      this.damage = buffer.readFloat();
      this.explosionRadius = buffer.readFloat();
   }

   @Override
   public void func_70037_a(NBTTagCompound tagCompound) {
      super.func_70037_a(tagCompound);
      Item item = Item.func_150899_d(tagCompound.func_74762_e("entityItem"));
      if (item instanceof ItemWeapon) {
         this.weapon = (ItemWeapon)item;
      }

      this.damage = tagCompound.func_74760_g("damage");
      this.explosionRadius = tagCompound.func_74760_g("explosionRadius");
   }

   @Override
   public void func_70014_b(NBTTagCompound tagCompound) {
      super.func_70014_b(tagCompound);
      tagCompound.func_74768_a("entityItem", Item.func_150891_b(this.weapon));
      tagCompound.func_74776_a("damage", this.damage);
      tagCompound.func_74776_a("explosionRadius", this.explosionRadius);
   }

   @Override
   public boolean canCollideWithBlock(Block block, int metadata) {
      return !PASS_THROUGH_BLOCKS.contains(block) && super.canCollideWithBlock(block, metadata);
   }

   public ItemWeapon getWeapon() {
      return this.weapon;
   }
}
