package com.gtnewhorizon.newgunrizons.entities;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.util.RayCast;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import io.netty.buffer.ByteBuf;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.BiPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityGrenade extends Entity implements IEntityAdditionalSpawnData, IThrowableEntity {
   private static final Logger logger = LogManager.getLogger(EntityGrenade.class);
   private static final int VELOCITY_HISTORY_SIZE = 10;
   private static final double STOP_THRESHOLD = 0.001;
   private static final int MAX_TICKS = 2000;
   private static final String TAG_ENTITY_ITEM = "entity_item";
   private float gravityVelocity;
   private float slowdownFactor = 0.5F;
   private int ticksInAir;
   private EntityLivingBase thrower;
   protected int bounceCount;
   private float initialYaw;
   private float initialPitch;
   private float xRotation;
   private float yRotation;
   private float zRotation;
   private float xRotationChange;
   private float yRotationChange;
   private float zRotationChange;
   private float rotationSlowdownFactor = 0.99F;
   private final float maxRotationChange = 20.0F;
   protected boolean stopped;
   private final Queue<Double> velocityHistory = new ArrayDeque<>(10);
   protected ItemGrenade itemGrenade;
   private long explosionTimeout;
   private float explosionStrength;
   private long activationTimestamp;

   private EntityGrenade(ItemGrenade itemGrenade, EntityLivingBase thrower, float velocity, float gravityVelocity, float rotationSlowdownFactor) {
      super(thrower.field_70170_p);
      this.thrower = thrower;
      this.gravityVelocity = gravityVelocity;
      this.rotationSlowdownFactor = rotationSlowdownFactor;
      this.itemGrenade = itemGrenade;
      this.func_70105_a(0.3F, 0.3F);
      this.func_70012_b(
         thrower.field_70165_t,
         thrower.field_70163_u + thrower.func_70047_e(),
         thrower.field_70161_v,
         thrower instanceof EntityPlayer ? thrower.field_70177_z : thrower.field_70761_aq,
         thrower.field_70125_A
      );
      this.field_70165_t = this.field_70165_t - MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float) Math.PI) * 0.16F;
      this.field_70163_u -= 0.1F;
      this.field_70161_v = this.field_70161_v - MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float) Math.PI) * 0.16F;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      float f = 0.4F;
      this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float) Math.PI)
         * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI)
         * f;
      this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float) Math.PI)
         * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI)
         * f;
      this.field_70181_x = -MathHelper.func_76126_a((this.field_70125_A + 0.0F) / 180.0F * (float) Math.PI) * f;
      this.initialYaw = this.field_70177_z;
      this.initialPitch = this.field_70125_A;
      this.setThrowableHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, velocity, 10.0F);
      logger.debug(
         "Throwing with position {}{}{}, rotation pitch {}, velocity {}, {}, {}",
         new Object[]{
            this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70125_A, this.field_70159_w, this.field_70181_x, this.field_70179_y
         }
      );
   }

   public EntityGrenade(World world) {
      super(world);
      this.xRotationChange = 20.0F * (float)this.field_70146_Z.nextGaussian();
      this.yRotationChange = 20.0F * (float)this.field_70146_Z.nextGaussian();
      this.zRotationChange = 20.0F * (float)this.field_70146_Z.nextGaussian();
   }

   public void setThrowableHeading(double motionX, double motionY, double motionZ, float velocity, float inaccuracy) {
      float f2 = MathHelper.func_76133_a(motionX * motionX + motionY * motionY + motionZ * motionZ);
      motionX /= f2;
      motionY /= f2;
      motionZ /= f2;
      motionX += this.field_70146_Z.nextGaussian() * 0.0075F * inaccuracy;
      motionY += this.field_70146_Z.nextGaussian() * 0.0075F * inaccuracy;
      motionZ += this.field_70146_Z.nextGaussian() * 0.0075F * inaccuracy;
      motionX *= velocity;
      motionY *= velocity;
      motionZ *= velocity;
      this.field_70159_w = motionX;
      this.field_70181_x = motionY;
      this.field_70179_y = motionZ;
      float f3 = MathHelper.func_76133_a(motionX * motionX + motionZ * motionZ);
      this.field_70126_B = this.field_70177_z = (float)(Math.atan2(motionX, motionZ) * 180.0 / Math.PI);
      this.field_70127_C = this.field_70125_A = (float)(Math.atan2(motionY, f3) * 180.0 / Math.PI);
   }

   public void setThrower(Entity thrower) {
      this.thrower = (EntityLivingBase)thrower;
   }

   public void func_70071_h_() {
      if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 2000) {
         this.func_70106_y();
      } else {
         this.xRotation = this.xRotation + this.xRotationChange;
         this.yRotation = this.yRotation + this.yRotationChange;
         this.zRotation = this.zRotation + this.zRotationChange;
         this.xRotationChange = this.xRotationChange * this.rotationSlowdownFactor;
         this.yRotationChange = this.yRotationChange * this.rotationSlowdownFactor;
         this.zRotationChange = this.zRotationChange * this.rotationSlowdownFactor;
         this.field_70142_S = this.field_70165_t;
         this.field_70137_T = this.field_70163_u;
         this.field_70136_U = this.field_70161_v;
         super.func_70071_h_();
         this.ticksInAir++;
         if (!this.stopped) {
            Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3 vec31 = Vec3.func_72443_a(
               this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y
            );
            MovingObjectPosition movingobjectposition = RayCast.rayCastBlocks(this.field_70170_p, vec3, vec31, this::canCollideWithBlock);
            vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            if (movingobjectposition != null) {
               vec31 = Vec3.func_72443_a(
                  movingobjectposition.field_72307_f.field_72450_a,
                  movingobjectposition.field_72307_f.field_72448_b,
                  movingobjectposition.field_72307_f.field_72449_c
               );
            }

            if (this.thrower != null) {
               Entity entity = null;
               List<Entity> list = this.field_70170_p
                  .func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
               double d0 = 0.0;
               EntityLivingBase entitylivingbase = this.getThrower();
               MovingObjectPosition entityMovingObjectPosition = null;

               for (Entity entity1 : list) {
                  if (entity1.func_70067_L() && (entity1 != entitylivingbase || this.ticksInAir >= 5)) {
                     float f = 0.3F;
                     AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
                     MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
                     if (movingobjectposition1 != null) {
                        double d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
                        if (d1 < d0 || d0 == 0.0) {
                           entity = entity1;
                           entityMovingObjectPosition = movingobjectposition1;
                           d0 = d1;
                        }
                     }
                  }
               }

               if (entity != null) {
                  movingobjectposition = new MovingObjectPosition(entity);
                  movingobjectposition.field_72310_e = entityMovingObjectPosition.field_72310_e;
                  movingobjectposition.field_72307_f = entityMovingObjectPosition.field_72307_f;
               }
            }

            logger.trace(
               "Ori position to {}, {}, {}, motion {} {} {} ",
               new Object[]{this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y}
            );
            if (movingobjectposition != null
               && (movingobjectposition.field_72313_a == MovingObjectType.BLOCK || movingobjectposition.field_72313_a == MovingObjectType.ENTITY)) {
               logger.trace(
                  "Hit {}, vec set to {}, {}, {}",
                  new Object[]{
                     movingobjectposition.field_72313_a,
                     movingobjectposition.field_72307_f.field_72450_a,
                     movingobjectposition.field_72307_f.field_72448_b,
                     movingobjectposition.field_72307_f.field_72449_c
                  }
               );
               logger.trace(
                  "Before bouncing {}, side {}, motion set to {}, {}, {}",
                  new Object[]{this.bounceCount, movingobjectposition.field_72310_e, this.field_70159_w, this.field_70181_x, this.field_70179_y}
               );
               this.field_70165_t = movingobjectposition.field_72307_f.field_72450_a;
               this.field_70163_u = movingobjectposition.field_72307_f.field_72448_b;
               this.field_70161_v = movingobjectposition.field_72307_f.field_72449_c;
               switch (movingobjectposition.field_72310_e) {
                  case 0:
                     this.field_70181_x = -this.field_70181_x;
                     this.field_70163_u = this.field_70163_u + this.field_70181_x;
                     break;
                  case 1:
                     this.field_70181_x = -this.field_70181_x;
                     break;
                  case 2:
                     this.field_70179_y = -this.field_70179_y;
                     this.field_70161_v = this.field_70161_v + this.field_70179_y;
                     break;
                  case 3:
                     this.field_70179_y = -this.field_70179_y;
                     break;
                  case 4:
                     this.field_70159_w = -this.field_70159_w;
                     this.field_70165_t = this.field_70165_t + this.field_70159_w;
                     break;
                  case 5:
                     this.field_70159_w = -this.field_70159_w;
               }

               this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
               if (movingobjectposition.field_72313_a == MovingObjectType.ENTITY) {
                  this.avoidEntityCollisionAfterBounce(movingobjectposition);
               } else if (movingobjectposition.field_72313_a == MovingObjectType.BLOCK) {
                  this.avoidBlockCollisionAfterBounce(movingobjectposition);
               }

               logger.trace(
                  "After bouncing {}  motion set to {}, {}, {}", new Object[]{this.bounceCount, this.field_70159_w, this.field_70181_x, this.field_70179_y}
               );
               this.onBounce(movingobjectposition);
               this.bounceCount++;
               if (this.field_70128_L) {
                  return;
               }
            } else {
               this.field_70165_t = this.field_70165_t + this.field_70159_w;
               this.field_70163_u = this.field_70163_u + this.field_70181_x;
               this.field_70161_v = this.field_70161_v + this.field_70179_y;
            }

            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            float motionSquared = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70125_A = (float)(Math.atan2(this.field_70181_x, motionSquared) * 180.0 / Math.PI);

            while (this.field_70125_A - this.field_70127_C < -180.0F) {
               this.field_70127_C -= 360.0F;
            }

            while (this.field_70125_A - this.field_70127_C >= 180.0F) {
               this.field_70127_C += 360.0F;
            }

            while (this.field_70177_z - this.field_70126_B < -180.0F) {
               this.field_70126_B -= 360.0F;
            }

            while (this.field_70177_z - this.field_70126_B >= 180.0F) {
               this.field_70126_B += 360.0F;
            }

            this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
            this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
            float f2 = 0.99F;
            float currentGravityVelocity = this.gravityVelocity;
            if (this.func_70090_H()) {
               for (int i = 0; i < 4; i++) {
                  float f4 = 0.25F;
                  this.field_70170_p
                     .func_72869_a(
                        "bubble",
                        this.field_70165_t - this.field_70159_w * f4,
                        this.field_70163_u - this.field_70181_x * f4,
                        this.field_70161_v - this.field_70179_y * f4,
                        this.field_70159_w,
                        this.field_70181_x,
                        this.field_70179_y
                     );
               }

               f2 = 0.8F;
            }

            if (movingobjectposition != null
               && (movingobjectposition.field_72313_a == MovingObjectType.BLOCK || movingobjectposition.field_72313_a == MovingObjectType.ENTITY)) {
               f2 = this.slowdownFactor;
               this.rotationSlowdownFactor = this.rotationSlowdownFactor * (this.slowdownFactor * 1.5F);
            }

            this.field_70159_w *= f2;
            this.field_70181_x *= f2;
            this.field_70179_y *= f2;
            this.recordVelocityHistory();
            if (this.velocityHistory.stream().noneMatch(v -> v > 0.001)) {
               this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0;
               this.stopped = true;
               logger.trace("Stopping {}", new Object[]{this});
            } else {
               this.field_70181_x -= currentGravityVelocity;
            }

            logger.trace(
               "Set position to {}, {}, {}, motion {} {} {} ",
               new Object[]{this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y}
            );
         }
      }

      if (!this.field_70170_p.field_72995_K && this.explosionTimeout > 0L && System.currentTimeMillis() > this.activationTimestamp + this.explosionTimeout) {
         this.explode();
      }
   }

   public void onBounce(MovingObjectPosition movingobjectposition) {
      if (this.explosionTimeout == -1L && !this.field_70170_p.field_72995_K) {
         this.explode();
      } else if (movingobjectposition.field_72313_a == MovingObjectType.BLOCK && this.itemGrenade != null) {
         String bounceHardSound = this.itemGrenade.getBounceHardSound();
         if (bounceHardSound != null) {
            Block hitBlock = this.field_70170_p
               .func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d);
            if (madeFromHardMaterial(hitBlock)) {
               this.field_70170_p.func_72956_a(this, bounceHardSound, 2.0F / (this.bounceCount + 1.0F), 1.0F);
            }
         }

         String bounceSoftSound = this.itemGrenade.getBounceSoftSound();
         if (bounceSoftSound != null) {
            Block hitBlock = this.field_70170_p
               .func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d);
            if (!madeFromHardMaterial(hitBlock)) {
               this.field_70170_p.func_72956_a(this, bounceSoftSound, 1.0F / (this.bounceCount + 1.0F), 1.0F);
            }
         }
      }
   }

   private void avoidBlockCollisionAfterBounce(MovingObjectPosition movingobjectposition) {
      if (movingobjectposition.field_72313_a == MovingObjectType.BLOCK) {
         double dX = Math.signum(this.field_70159_w) * 0.05;
         double dY = Math.signum(this.field_70181_x) * 0.05;
         double dZ = Math.signum(this.field_70179_y) * 0.05;

         for (int i = 0; i < 10; i++) {
            double projectedXPos = this.field_70165_t + dX * i;
            double projectedYPos = this.field_70163_u + dY * i;
            double projectedZPos = this.field_70161_v + dZ * i;
            BlockPos blockPos = new BlockPos((int)projectedXPos, (int)projectedYPos, (int)projectedZPos);
            AxisAlignedBB projectedEntityBoundingBox = this.field_70121_D.func_72325_c(dX * i, dY * i, dZ * i);
            if (this.field_70170_p.func_147439_a(blockPos.getX(), blockPos.getY(), blockPos.getZ()).func_149688_o() == Material.field_151579_a
               || !AxisAlignedBB.func_72330_a(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos.getX() + 1, blockPos.getY() + 1, blockPos.getZ() + 1)
                  .func_72326_a(projectedEntityBoundingBox)) {
               this.field_70165_t = projectedXPos;
               this.field_70163_u = projectedYPos;
               this.field_70161_v = projectedZPos;
               logger.trace("Found non-intercepting post-bounce position on iteration {}", new Object[]{i});
               break;
            }
         }
      }
   }

   private void avoidEntityCollisionAfterBounce(MovingObjectPosition movingobjectposition) {
      if (movingobjectposition.field_72308_g != null) {
         this.slowdownFactor = 0.3F;
         double dX = Math.signum(this.field_70159_w) * 0.05;
         double dY = Math.signum(this.field_70181_x) * 0.05;
         double dZ = Math.signum(this.field_70179_y) * 0.05;
         float f = 0.3F;
         AxisAlignedBB axisalignedbb = movingobjectposition.field_72308_g.field_70121_D.func_72314_b(f, f, f);
         MovingObjectPosition intercept = movingobjectposition;

         for (int i = 0; i < 10; i++) {
            Vec3 currentPos = Vec3.func_72443_a(this.field_70165_t + dX * i, this.field_70163_u + dY * i, this.field_70161_v + dY * i);
            Vec3 projectedPos = Vec3.func_72443_a(this.field_70165_t + dX * (i + 1), this.field_70163_u + dY * (i + 1), this.field_70161_v + dZ * (i + 1));
            intercept = axisalignedbb.func_72327_a(currentPos, projectedPos);
            if (intercept == null) {
               BlockPos blockPos = new BlockPos((int)projectedPos.field_72450_a, (int)projectedPos.field_72448_b, (int)projectedPos.field_72449_c);
               Block collidedBlock = this.field_70170_p.func_147439_a(blockPos.getX(), blockPos.getY(), blockPos.getZ());
               if (collidedBlock.func_149688_o() != Material.field_151579_a) {
                  logger.debug("Found non-intercept position colliding with block {}", new Object[]{collidedBlock});
                  intercept = movingobjectposition;
               } else {
                  this.field_70165_t = projectedPos.field_72450_a;
                  this.field_70163_u = projectedPos.field_72448_b;
                  this.field_70161_v = projectedPos.field_72449_c;
               }
               break;
            }
         }

         if (intercept != null) {
            logger.debug("Could not find non-intercept position after bounce");
         }
      }
   }

   private void explode() {
      logger.debug("Exploding {}", new Object[]{this});
      Explosion.createServerSideExplosion(
         this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.explosionStrength, false, true
      );
      List<?> nearbyEntities = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(5.0, 5.0, 5.0));
      float damageCoefficient = 1.0F;
      float effectiveRadius = this.itemGrenade.getEffectiveRadius() * damageCoefficient;
      float fragmentDamage = this.itemGrenade.getFragmentDamage();
      float configuredFragmentCount = this.itemGrenade.getFragmentCount() * damageCoefficient;

      for (int i = 0; i < configuredFragmentCount; i++) {
         double x = (this.field_70146_Z.nextDouble() - 0.5) * 2.0;
         double y = (this.field_70146_Z.nextDouble() - 0.5) * 2.0;
         double z = (this.field_70146_Z.nextDouble() - 0.5) * 2.0;
         double d2 = x * x + y * y + z * z;
         if (d2 == 0.0) {
            logger.debug("Ignoring zero distance index {}", new Object[]{i});
         } else {
            double k = Math.sqrt(effectiveRadius * effectiveRadius / d2);
            double k2 = 0.1;
            Vec3 cvec1 = Vec3.func_72443_a(this.field_70165_t + x * k2, this.field_70163_u + y * k2, this.field_70161_v + z * k2);
            Vec3 cvec10 = Vec3.func_72443_a(this.field_70165_t + x * k2, this.field_70163_u + y * k2, this.field_70161_v + z * k2);
            Vec3 cvec2 = Vec3.func_72443_a(this.field_70165_t + x * k, this.field_70163_u + y * k, this.field_70161_v + z * k);
            BiPredicate<Block, Integer> isCollidable = (block, blockMetadata) -> block.func_149678_a(blockMetadata, false);
            MovingObjectPosition rayTraceResult = RayCast.rayCastBlocks(this.field_70170_p, cvec1, cvec2, isCollidable);
            if (rayTraceResult != null) {
               cvec2 = Vec3.func_72443_a(
                  rayTraceResult.field_72307_f.field_72450_a, rayTraceResult.field_72307_f.field_72448_b, rayTraceResult.field_72307_f.field_72449_c
               );
            }

            for (Object nearbyEntityObject : nearbyEntities) {
               Entity nearbyEntity = (Entity)nearbyEntityObject;
               if (nearbyEntity.func_70067_L()) {
                  float f = 0.5F;
                  AxisAlignedBB axisalignedbb = nearbyEntity.field_70121_D.func_72314_b(f, f, f);
                  MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(cvec10, cvec2);
                  if (movingobjectposition1 != null) {
                     double distanceToEntity = cvec10.func_72438_d(movingobjectposition1.field_72307_f);
                     float damageDistanceReductionFactor = (float)Math.abs(1.0 - distanceToEntity / effectiveRadius);
                     logger.trace(
                        "Hit entity {} at distance {}, damage reduction {}", new Object[]{nearbyEntity, distanceToEntity, damageDistanceReductionFactor}
                     );
                     nearbyEntity.func_70097_a(
                        DamageSource.func_76356_a(this, this.getThrower()),
                        Math.max(0.1F, this.field_70146_Z.nextFloat()) * fragmentDamage * damageDistanceReductionFactor
                     );
                  }
               }
            }
         }
      }

      this.func_70106_y();
   }

   private static boolean madeFromHardMaterial(Block block) {
      Material material = block.func_149688_o();
      return material == Material.field_151576_e
         || material == Material.field_151573_f
         || material == Material.field_151588_w
         || material == Material.field_151575_d;
   }

   protected void func_70088_a() {
   }

   public void func_70037_a(NBTTagCompound tagCompound) {
      Item item = Item.func_150899_d(tagCompound.func_74762_e("entity_item"));
      if (item instanceof ItemGrenade) {
         this.itemGrenade = (ItemGrenade)item;
      }
   }

   public void func_70014_b(NBTTagCompound tagCompound) {
      tagCompound.func_74768_a("entity_item", Item.func_150891_b(this.itemGrenade));
   }

   public void writeSpawnData(ByteBuf buffer) {
      buffer.writeInt(this.thrower != null ? this.thrower.func_145782_y() : -1);
      buffer.writeDouble(this.field_70165_t);
      buffer.writeDouble(this.field_70163_u);
      buffer.writeDouble(this.field_70161_v);
      buffer.writeDouble(this.field_70159_w);
      buffer.writeDouble(this.field_70181_x);
      buffer.writeDouble(this.field_70179_y);
      buffer.writeFloat(this.gravityVelocity);
      buffer.writeFloat(this.rotationSlowdownFactor);
      buffer.writeFloat(this.initialYaw);
      buffer.writeFloat(this.initialPitch);
      buffer.writeInt(Item.func_150891_b(this.itemGrenade));
      buffer.writeLong(this.activationTimestamp);
      buffer.writeLong(this.explosionTimeout);
      buffer.writeFloat(this.explosionStrength);
   }

   public void readSpawnData(ByteBuf buffer) {
      int entityId = buffer.readInt();
      if (this.thrower == null && entityId >= 0) {
         Entity entity = this.field_70170_p.func_73045_a(entityId);
         if (entity instanceof EntityLivingBase) {
            this.thrower = (EntityPlayer)entity;
         }
      }

      this.field_70165_t = buffer.readDouble();
      this.field_70163_u = buffer.readDouble();
      this.field_70161_v = buffer.readDouble();
      this.field_70159_w = buffer.readDouble();
      this.field_70181_x = buffer.readDouble();
      this.field_70179_y = buffer.readDouble();
      this.gravityVelocity = buffer.readFloat();
      this.rotationSlowdownFactor = buffer.readFloat();
      this.initialYaw = buffer.readFloat();
      this.initialPitch = buffer.readFloat();
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      logger.debug(
         "Restoring with position {}{}{}, rotation pitch {}, velocity {}, {}, {}",
         new Object[]{
            this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70125_A, this.field_70159_w, this.field_70181_x, this.field_70179_y
         }
      );
      Item item = Item.func_150899_d(buffer.readInt());
      if (item instanceof ItemGrenade) {
         this.itemGrenade = (ItemGrenade)item;
      }

      this.activationTimestamp = buffer.readLong();
      this.explosionTimeout = buffer.readLong();
      this.explosionStrength = buffer.readFloat();
   }

   public float getYRotation() {
      return this.yRotation - this.initialYaw - 90.0F;
   }

   public boolean canCollideWithBlock(Block block, int metadata) {
      return block != Blocks.field_150350_a
         && block != Blocks.field_150329_H
         && block != Blocks.field_150362_t
         && block != Blocks.field_150361_u
         && block != Blocks.field_150480_ab
         && block != Blocks.field_150407_cf
         && block != Blocks.field_150398_cm
         && block != Blocks.field_150321_G
         && block != Blocks.field_150464_aj
         && block.func_149678_a(metadata, false);
   }

   private void recordVelocityHistory() {
      double velocity = this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y;
      this.velocityHistory.add(velocity);
      if (this.velocityHistory.size() > 10) {
         this.velocityHistory.poll();
      }
   }

   public EntityLivingBase getThrower() {
      return this.thrower;
   }

   public float getXRotation() {
      return this.xRotation;
   }

   public float getZRotation() {
      return this.zRotation;
   }

   public ItemGrenade getItemGrenade() {
      return this.itemGrenade;
   }

   public static class Builder {
      private long explosionTimeout;
      private float explosionStrength;
      private long activationTimestamp;
      private EntityLivingBase thrower;
      private ItemGrenade itemGrenade;
      private float velocity = 1.0F;
      private float gravityVelocity = 0.06F;
      private float rotationSlowdownFactor = 0.99F;

      public EntityGrenade.Builder withActivationTimestamp(long activationTimestamp) {
         this.activationTimestamp = activationTimestamp;
         return this;
      }

      public EntityGrenade.Builder withExplosionTimeout(long explosionTimeout) {
         this.explosionTimeout = explosionTimeout;
         return this;
      }

      public EntityGrenade.Builder withThrower(EntityLivingBase thrower) {
         this.thrower = thrower;
         return this;
      }

      public EntityGrenade.Builder withExplosionStrength(float explosionStrength) {
         this.explosionStrength = explosionStrength;
         return this;
      }

      public EntityGrenade.Builder withGrenade(ItemGrenade itemGrenade) {
         this.itemGrenade = itemGrenade;
         return this;
      }

      public EntityGrenade.Builder withVelocity(float velocity) {
         this.velocity = velocity;
         return this;
      }

      public EntityGrenade.Builder withGravityVelocity(float gravityVelocity) {
         this.gravityVelocity = gravityVelocity;
         return this;
      }

      public EntityGrenade.Builder withRotationSlowdownFactor(float rotationSlowdownFactor) {
         this.rotationSlowdownFactor = rotationSlowdownFactor;
         return this;
      }

      public EntityGrenade build() {
         EntityGrenade entityGrenade = new EntityGrenade(this.itemGrenade, this.thrower, this.velocity, this.gravityVelocity, this.rotationSlowdownFactor);
         entityGrenade.activationTimestamp = this.activationTimestamp;
         entityGrenade.explosionTimeout = this.explosionTimeout;
         entityGrenade.explosionStrength = this.explosionStrength;
         entityGrenade.itemGrenade = this.itemGrenade;
         return entityGrenade;
      }
   }
}
