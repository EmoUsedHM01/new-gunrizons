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
      this.func_70105_a(0.25F, 0.25F);
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
      this.func_70105_a(0.25F, 0.25F);
      this.func_70107_b(posX, posY, posZ);
   }

   public void setPositionAndDirection() {
      float yaw = this.thrower instanceof EntityPlayer ? this.thrower.field_70177_z : this.thrower.field_70761_aq;
      this.func_70012_b(
         this.thrower.field_70165_t, this.thrower.field_70163_u + this.thrower.func_70047_e(), this.thrower.field_70161_v, yaw, this.thrower.field_70125_A
      );
      this.field_70165_t = this.field_70165_t - MathHelper.func_76134_b(this.field_70177_z * (float) (Math.PI / 180.0)) * 0.16F;
      this.field_70163_u -= 0.1F;
      this.field_70161_v = this.field_70161_v - MathHelper.func_76126_a(this.field_70177_z * (float) (Math.PI / 180.0)) * 0.16F;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      float speed = this.velocity;
      this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z * (float) (Math.PI / 180.0))
         * MathHelper.func_76134_b(this.field_70125_A * (float) (Math.PI / 180.0))
         * speed;
      this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z * (float) (Math.PI / 180.0))
         * MathHelper.func_76134_b(this.field_70125_A * (float) (Math.PI / 180.0))
         * speed;
      this.field_70181_x = -MathHelper.func_76126_a((this.field_70125_A + this.getPitchOffset()) * (float) (Math.PI / 180.0)) * speed;
      this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.velocity, this.inaccuracy);
   }

   protected float getPitchOffset() {
      return 0.0F;
   }

   public void func_70186_c(double x, double y, double z, float velocity, float inaccuracy) {
      double length = MathHelper.func_76133_a(x * x + y * y + z * z);
      x /= length;
      y /= length;
      z /= length;
      x += this.field_70146_Z.nextGaussian() * 0.0075 * inaccuracy;
      y += this.field_70146_Z.nextGaussian() * 0.0075 * inaccuracy;
      z += this.field_70146_Z.nextGaussian() * 0.0075 * inaccuracy;
      x *= velocity;
      y *= velocity;
      z *= velocity;
      this.field_70159_w = x;
      this.field_70181_x = y;
      this.field_70179_y = z;
      float horizontalSpeed = MathHelper.func_76133_a(x * x + z * z);
      this.field_70126_B = this.field_70177_z = (float)(Math.atan2(x, z) * (180.0 / Math.PI));
      this.field_70127_C = this.field_70125_A = (float)(Math.atan2(y, horizontalSpeed) * (180.0 / Math.PI));
   }

   public void func_70016_h(double mX, double mY, double mZ) {
      this.field_70159_w = mX;
      this.field_70181_x = mY;
      this.field_70179_y = mZ;
      if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float horizontalSpeed = MathHelper.func_76133_a(mX * mX + mZ * mZ);
         this.field_70126_B = this.field_70177_z = (float)(Math.atan2(mX, mZ) * (180.0 / Math.PI));
         this.field_70127_C = this.field_70125_A = (float)(Math.atan2(mY, horizontalSpeed) * (180.0 / Math.PI));
      }
   }

   public void func_70071_h_() {
      if (this.field_70173_aa > 200) {
         this.func_70106_y();
      } else {
         this.field_70142_S = this.field_70165_t;
         this.field_70137_T = this.field_70163_u;
         this.field_70136_U = this.field_70161_v;
         super.func_70071_h_();
         if (this.throwableShake > 0) {
            this.throwableShake--;
         }

         if (this.inGround) {
            this.inGround = false;
            this.field_70159_w = this.field_70159_w * (this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70181_x = this.field_70181_x * (this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70179_y = this.field_70179_y * (this.field_70146_Z.nextFloat() * 0.2F);
            this.ticksInAir = 0;
         } else {
            this.ticksInAir++;
         }

         Vec3 startPos = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         Vec3 endPos = Vec3.func_72443_a(
            this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y
         );
         if (!this.field_70170_p.field_72995_K) {
            this.checkWaterEntry(startPos, endPos);
         }

         startPos = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         endPos = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         MovingObjectPosition blockHit = RayCast.rayCastBlocks(this.field_70170_p, startPos, endPos, this::canCollideWithBlock);
         startPos = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         endPos = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         if (blockHit != null) {
            endPos = Vec3.func_72443_a(blockHit.field_72307_f.field_72450_a, blockHit.field_72307_f.field_72448_b, blockHit.field_72307_f.field_72449_c);
         }

         if (!this.field_70170_p.field_72995_K) {
            Entity hitEntity = this.findClosestEntityOnPath(startPos, endPos);
            if (hitEntity != null) {
               blockHit = new MovingObjectPosition(hitEntity);
            }
         }

         if (blockHit != null) {
            this.onImpact(blockHit);
         }

         this.field_70165_t = this.field_70165_t + this.field_70159_w;
         this.field_70163_u = this.field_70163_u + this.field_70181_x;
         this.field_70161_v = this.field_70161_v + this.field_70179_y;
         float horizontalSpeed = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * (180.0 / Math.PI));
         this.field_70125_A = (float)(Math.atan2(this.field_70181_x, horizontalSpeed) * (180.0 / Math.PI));

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
         float drag = 0.99F;
         if (this.func_70090_H()) {
            for (int i = 0; i < 4; i++) {
               this.field_70170_p
                  .func_72869_a(
                     "bubble",
                     this.field_70165_t - this.field_70159_w * 0.25,
                     this.field_70163_u - this.field_70181_x * 0.25,
                     this.field_70161_v - this.field_70179_y * 0.25,
                     this.field_70159_w,
                     this.field_70181_x,
                     this.field_70179_y
                  );
            }

            drag = 0.8F;
         }

         this.field_70159_w *= drag;
         this.field_70181_x *= drag;
         this.field_70179_y *= drag;
         this.field_70181_x = this.field_70181_x - this.gravityVelocity;
         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      }
   }

   private Entity findClosestEntityOnPath(Vec3 startPos, Vec3 endPos) {
      Entity closestEntity = null;
      List<Entity> nearbyEntities = this.field_70170_p
         .func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
      double closestDistance = 0.0;
      EntityLivingBase thrower = this.getThrower();

      for (Entity candidate : nearbyEntities) {
         if (candidate.func_70067_L() && (candidate != thrower || this.ticksInAir >= 5)) {
            AxisAlignedBB expandedBbox = candidate.field_70121_D.func_72314_b(0.3F, 0.3F, 0.3F);
            MovingObjectPosition intercept = expandedBbox.func_72327_a(startPos, endPos);
            if (intercept != null) {
               double hitDistance = startPos.func_72438_d(intercept.field_72307_f);
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
      MovingObjectPosition waterHit = this.field_70170_p
         .func_72901_a(
            Vec3.func_72443_a(start.field_72450_a, start.field_72448_b, start.field_72449_c),
            Vec3.func_72443_a(end.field_72450_a, end.field_72448_b, end.field_72449_c),
            true
         );
      if (waterHit != null && waterHit.field_72313_a == MovingObjectType.BLOCK) {
         Block hitBlock = this.field_70170_p.func_147439_a(waterHit.field_72311_b, waterHit.field_72312_c, waterHit.field_72309_d);
         if (hitBlock.func_149688_o() == Material.field_151586_h) {
            double splashX = waterHit.field_72307_f.field_72450_a;
            double splashY = waterHit.field_72312_c + 1.0;
            double splashZ = waterHit.field_72307_f.field_72449_c;
            this.onWaterImpact(splashX, splashY, splashZ);
         }
      }
   }

   protected void onWaterImpact(double x, double y, double z) {
   }

   protected abstract void onImpact(MovingObjectPosition var1);

   public void func_70014_b(NBTTagCompound tagCompound) {
      tagCompound.func_74772_a("timestamp", this.timestamp);
      tagCompound.func_74777_a("xTile", (short)this.xTile);
      tagCompound.func_74777_a("yTile", (short)this.yTile);
      tagCompound.func_74777_a("zTile", (short)this.zTile);
      tagCompound.func_74774_a("shake", (byte)this.throwableShake);
      tagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
      if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof EntityPlayer) {
         this.throwerName = this.thrower.func_70005_c_();
      }

      tagCompound.func_74778_a("ownerName", this.throwerName == null ? "" : this.throwerName);
      tagCompound.func_74776_a("gravityVelocity", this.gravityVelocity);
   }

   public void func_70037_a(NBTTagCompound tagCompound) {
      this.xTile = tagCompound.func_74765_d("xTile");
      this.yTile = tagCompound.func_74765_d("yTile");
      this.zTile = tagCompound.func_74765_d("zTile");
      this.throwableShake = tagCompound.func_74771_c("shake") & 255;
      this.inGround = tagCompound.func_74771_c("inGround") == 1;
      this.throwerName = tagCompound.func_74779_i("ownerName");
      if (this.throwerName != null && this.throwerName.isEmpty()) {
         this.throwerName = null;
      }

      this.gravityVelocity = tagCompound.func_74760_g("gravityVelocity");
      this.timestamp = tagCompound.func_74763_f("timestamp");
      if (System.currentTimeMillis() > this.timestamp + this.maxLifetime) {
         this.func_70106_y();
      }
   }

   public void writeSpawnData(ByteBuf buffer) {
      buffer.writeFloat(this.gravityVelocity);
   }

   public void readSpawnData(ByteBuf buffer) {
      this.gravityVelocity = buffer.readFloat();
   }

   public float func_70053_R() {
      return 0.0F;
   }

   public EntityLivingBase getThrower() {
      if (this.thrower == null && this.throwerName != null && !this.throwerName.isEmpty()) {
         this.thrower = this.field_70170_p.func_72924_a(this.throwerName);
      }

      return this.thrower;
   }

   protected void func_70088_a() {
   }

   public boolean func_70112_a(double distanceSq) {
      double renderDistance = this.field_70121_D.func_72320_b() * 4.0;
      renderDistance *= 64.0;
      return distanceSq < renderDistance * renderDistance;
   }

   public boolean canCollideWithBlock(Block block, int metadata) {
      return block.func_149678_a(metadata, false);
   }
}
