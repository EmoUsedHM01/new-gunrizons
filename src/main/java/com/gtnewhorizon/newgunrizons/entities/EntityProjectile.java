package com.gtnewhorizon.newgunrizons.entities;

import com.gtnewhorizon.newgunrizons.util.RayCast;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public abstract class EntityProjectile extends Entity implements IProjectile, IEntityAdditionalSpawnData {
   private static final float DEG_TO_RAD = (float) (Math.PI / 180.0);
   private static final double RAD_TO_DEG = 180.0 / Math.PI;
   private static final int MAX_TICKS = 200;
   private static final long DEFAULT_MAX_LIFETIME = 5000L;
   private static final float HITBOX_SIZE = 0.25F;
   private static final float SPAWN_LATERAL_OFFSET = 0.16F;
   private static final float SPAWN_VERTICAL_OFFSET = 0.1F;
   private static final double INACCURACY_SPREAD = 0.0075;
   private static final float AIR_DRAG = 0.99F;
   private static final float WATER_DRAG = 0.8F;
   private static final float GROUND_BOUNCE_RANDOMIZATION = 0.2F;
   private static final float ROTATION_SMOOTHING = 0.2F;
   private static final int BUBBLE_COUNT = 4;
   private static final float BUBBLE_OFFSET_FRACTION = 0.25F;
   private static final double ENTITY_SEARCH_EXPANSION = 1.0;
   private static final float ENTITY_HITBOX_EXPANSION = 0.3F;
   private static final int THROWER_IMMUNITY_TICKS = 5;
   private static final double RENDER_DISTANCE_BBOX_SCALE = 4.0;
   private static final double RENDER_DISTANCE_MULTIPLIER = 64.0;
   private static final String TAG_TIMESTAMP = "timestamp";
   private static final String TAG_X_TILE = "xTile";
   private static final String TAG_Y_TILE = "yTile";
   private static final String TAG_Z_TILE = "zTile";
   private static final String TAG_SHAKE = "shake";
   private static final String TAG_IN_GROUND = "inGround";
   private static final String TAG_OWNER_NAME = "ownerName";
   private static final String TAG_GRAVITY_VELOCITY = "gravityVelocity";
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   protected boolean inGround;
   public int throwableShake;
   protected EntityLivingBase thrower;
   private String throwerName;
   private int ticksInAir;
   protected float gravityVelocity;
   protected float velocity;
   protected float inaccuracy;
   private long timestamp;
   protected long maxLifetime = 5000L;

   public EntityProjectile(World world) {
      super(world);
      this.setSize(0.25F, 0.25F);
      this.timestamp = System.currentTimeMillis();
   }

   public EntityProjectile(World world, EntityLivingBase thrower, float velocity, float gravityVelocity, float inaccuracy) {
      this(world);
      this.thrower = thrower;
      this.velocity = velocity;
      this.gravityVelocity = gravityVelocity;
      this.inaccuracy = inaccuracy;
   }

   public EntityProjectile(World world, double posX, double posY, double posZ) {
      super(world);
      this.setSize(0.25F, 0.25F);
      this.setPosition(posX, posY, posZ);
   }

   public void setPositionAndDirection() {
      float yaw = this.thrower instanceof EntityPlayer ? this.thrower.rotationYaw : this.thrower.renderYawOffset;
      this.setLocationAndAngles(
         this.thrower.posX, this.thrower.posY + this.thrower.getEyeHeight(), this.thrower.posZ, yaw, this.thrower.rotationPitch
      );
      this.posX = this.posX - MathHelper.cos(this.rotationYaw * (float) (Math.PI / 180.0)) * 0.16F;
      this.posY -= 0.1F;
      this.posZ = this.posZ - MathHelper.sin(this.rotationYaw * (float) (Math.PI / 180.0)) * 0.16F;
      this.setPosition(this.posX, this.posY, this.posZ);
      float speed = this.velocity;
      this.motionX = -MathHelper.sin(this.rotationYaw * (float) (Math.PI / 180.0))
         * MathHelper.cos(this.rotationPitch * (float) (Math.PI / 180.0))
         * speed;
      this.motionZ = MathHelper.cos(this.rotationYaw * (float) (Math.PI / 180.0))
         * MathHelper.cos(this.rotationPitch * (float) (Math.PI / 180.0))
         * speed;
      this.motionY = -MathHelper.sin((this.rotationPitch + this.getPitchOffset()) * (float) (Math.PI / 180.0)) * speed;
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.velocity, this.inaccuracy);
   }

   protected float getPitchOffset() {
      return 0.0F;
   }

   public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
      double length = MathHelper.sqrt_double(x * x + y * y + z * z);
      x /= length;
      y /= length;
      z /= length;
      x += this.rand.nextGaussian() * 0.0075 * inaccuracy;
      y += this.rand.nextGaussian() * 0.0075 * inaccuracy;
      z += this.rand.nextGaussian() * 0.0075 * inaccuracy;
      x *= velocity;
      y *= velocity;
      z *= velocity;
      this.motionX = x;
      this.motionY = y;
      this.motionZ = z;
      float horizontalSpeed = MathHelper.sqrt_double(x * x + z * z);
      this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * (180.0 / Math.PI));
      this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, horizontalSpeed) * (180.0 / Math.PI));
   }

   public void setVelocity(double mX, double mY, double mZ) {
      this.motionX = mX;
      this.motionY = mY;
      this.motionZ = mZ;
      if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
         float horizontalSpeed = MathHelper.sqrt_double(mX * mX + mZ * mZ);
         this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(mX, mZ) * (180.0 / Math.PI));
         this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(mY, horizontalSpeed) * (180.0 / Math.PI));
      }
   }

   public void func_70071_h_() {
      if (this.ticksExisted > 200) {
         this.setDead();
      } else {
         this.lastTickPosX = this.posX;
         this.lastTickPosY = this.posY;
         this.lastTickPosZ = this.posZ;
         super.onUpdate();
         if (this.throwableShake > 0) {
            this.throwableShake--;
         }

         if (this.inGround) {
            this.inGround = false;
            this.motionX = this.motionX * (this.rand.nextFloat() * 0.2F);
            this.motionY = this.motionY * (this.rand.nextFloat() * 0.2F);
            this.motionZ = this.motionZ * (this.rand.nextFloat() * 0.2F);
            this.ticksInAir = 0;
         } else {
            this.ticksInAir++;
         }

         Vec3 startPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
         Vec3 endPos = Vec3.createVectorHelper(
            this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ
         );
         if (!this.worldObj.isRemote) {
            this.checkWaterEntry(startPos, endPos);
         }

         startPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
         endPos = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         MovingObjectPosition blockHit = RayCast.rayCastBlocks(this.worldObj, startPos, endPos, this::canCollideWithBlock);
         startPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
         endPos = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
         if (blockHit != null) {
            endPos = Vec3.createVectorHelper(blockHit.hitVec.xCoord, blockHit.hitVec.yCoord, blockHit.hitVec.zCoord);
         }

         if (!this.worldObj.isRemote) {
            Entity hitEntity = this.findClosestEntityOnPath(startPos, endPos);
            if (hitEntity != null) {
               blockHit = new MovingObjectPosition(hitEntity);
            }
         }

         if (blockHit != null) {
            this.onImpact(blockHit);
         }

         this.posX = this.posX + this.motionX;
         this.posY = this.posY + this.motionY;
         this.posZ = this.posZ + this.motionZ;
         float horizontalSpeed = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * (180.0 / Math.PI));
         this.rotationPitch = (float)(Math.atan2(this.motionY, horizontalSpeed) * (180.0 / Math.PI));

         while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
            this.prevRotationPitch -= 360.0F;
         }

         while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
         }

         while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
         }

         while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
         }

         this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
         this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
         float drag = 0.99F;
         if (this.isInWater()) {
            for (int i = 0; i < 4; i++) {
               this.worldObj
                  .spawnParticle(
                     "bubble",
                     this.posX - this.motionX * 0.25,
                     this.posY - this.motionY * 0.25,
                     this.posZ - this.motionZ * 0.25,
                     this.motionX,
                     this.motionY,
                     this.motionZ
                  );
            }

            drag = 0.8F;
         }

         this.motionX *= drag;
         this.motionY *= drag;
         this.motionZ *= drag;
         this.motionY = this.motionY - this.gravityVelocity;
         this.setPosition(this.posX, this.posY, this.posZ);
      }
   }

   private Entity findClosestEntityOnPath(Vec3 startPos, Vec3 endPos) {
      Entity closestEntity = null;
      List<Entity> nearbyEntities = this.worldObj
         .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0, 1.0, 1.0));
      double closestDistance = 0.0;
      EntityLivingBase thrower = this.getThrower();

      for (Entity candidate : nearbyEntities) {
         if (candidate.canBeCollidedWith() && (candidate != thrower || this.ticksInAir >= 5)) {
            AxisAlignedBB expandedBbox = candidate.boundingBox.expand(0.3F, 0.3F, 0.3F);
            MovingObjectPosition intercept = expandedBbox.calculateIntercept(startPos, endPos);
            if (intercept != null) {
               double hitDistance = startPos.distanceTo(intercept.hitVec);
               if (hitDistance < closestDistance || closestDistance == 0.0) {
                  closestEntity = candidate;
                  closestDistance = hitDistance;
               }
            }
         }
      }

      return closestEntity;
   }

   private void checkWaterEntry(Vec3 start, Vec3 end) {
      MovingObjectPosition waterHit = this.worldObj
         .rayTraceBlocks(
            Vec3.createVectorHelper(start.xCoord, start.yCoord, start.zCoord),
            Vec3.createVectorHelper(end.xCoord, end.yCoord, end.zCoord),
            true
         );
      if (waterHit != null && waterHit.typeOfHit == MovingObjectType.BLOCK) {
         Block hitBlock = this.worldObj.getBlock(waterHit.blockX, waterHit.blockY, waterHit.blockZ);
         if (hitBlock.getMaterial() == Material.water) {
            double splashX = waterHit.hitVec.xCoord;
            double splashY = waterHit.blockY + 1.0;
            double splashZ = waterHit.hitVec.zCoord;
            this.onWaterImpact(splashX, splashY, splashZ);
         }
      }
   }

   protected void onWaterImpact(double x, double y, double z) {
   }

   protected abstract void onImpact(MovingObjectPosition var1);

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      tagCompound.setLong("timestamp", this.timestamp);
      tagCompound.setShort("xTile", (short)this.xTile);
      tagCompound.setShort("yTile", (short)this.yTile);
      tagCompound.setShort("zTile", (short)this.zTile);
      tagCompound.setByte("shake", (byte)this.throwableShake);
      tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
      if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof EntityPlayer) {
         this.throwerName = this.thrower.getCommandSenderName();
      }

      tagCompound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
      tagCompound.setFloat("gravityVelocity", this.gravityVelocity);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      this.xTile = tagCompound.getShort("xTile");
      this.yTile = tagCompound.getShort("yTile");
      this.zTile = tagCompound.getShort("zTile");
      this.throwableShake = tagCompound.getByte("shake") & 255;
      this.inGround = tagCompound.getByte("inGround") == 1;
      this.throwerName = tagCompound.getString("ownerName");
      if (this.throwerName != null && this.throwerName.isEmpty()) {
         this.throwerName = null;
      }

      this.gravityVelocity = tagCompound.getFloat("gravityVelocity");
      this.timestamp = tagCompound.getLong("timestamp");
      if (System.currentTimeMillis() > this.timestamp + this.maxLifetime) {
         this.setDead();
      }
   }

   public void writeSpawnData(ByteBuf buffer) {
      buffer.writeFloat(this.gravityVelocity);
   }

   public void readSpawnData(ByteBuf buffer) {
      this.gravityVelocity = buffer.readFloat();
   }

   public float getShadowSize() {
      return 0.0F;
   }

   public EntityLivingBase getThrower() {
      if (this.thrower == null && this.throwerName != null && !this.throwerName.isEmpty()) {
         this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
      }

      return this.thrower;
   }

   protected void entityInit() {
   }

   public boolean isInRangeToRenderDist(double distanceSq) {
      double renderDistance = this.boundingBox.getAverageEdgeLength() * 4.0;
      renderDistance *= 64.0;
      return distanceSq < renderDistance * renderDistance;
   }

   public boolean canCollideWithBlock(Block block, int metadata) {
      return block.canCollideCheck(metadata, false);
   }
}
