package com.gtnewhorizon.newgunrizons.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.util.BlockState;
import com.gtnewhorizon.newgunrizons.util.RayTracing;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;

public abstract class EntityProjectile extends Entity implements IProjectile, IEntityAdditionalSpawnData {

    private static final float DEG_TO_RAD = (float) Math.PI / 180.0F;
    private static final double RAD_TO_DEG = 180.0D / Math.PI;

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

    private int xTile;
    private int yTile;
    private int zTile;
    protected boolean inGround;
    public int throwableShake;
    protected EntityLivingBase thrower;
    private String throwerName;
    private int ticksInAir;
    protected float gravityVelocity;
    protected float velocity;
    protected float inaccuracy;
    private long timestamp;
    protected long maxLifetime;

    public EntityProjectile(World world) {
        super(world);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.maxLifetime = DEFAULT_MAX_LIFETIME;
        this.setSize(HITBOX_SIZE, HITBOX_SIZE);
        this.timestamp = System.currentTimeMillis();
    }

    public EntityProjectile(World world, EntityLivingBase thrower, float velocity, float gravityVelocity,
        float inaccuracy) {
        this(world);
        this.thrower = thrower;
        this.velocity = velocity;
        this.gravityVelocity = gravityVelocity;
        this.inaccuracy = inaccuracy;
    }

    public EntityProjectile(World world, double posX, double posY, double posZ) {
        super(world);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.maxLifetime = DEFAULT_MAX_LIFETIME;
        this.setSize(HITBOX_SIZE, HITBOX_SIZE);
        this.setPosition(posX, posY, posZ);
    }

    public void setPositionAndDirection() {
        float yaw = this.thrower instanceof EntityPlayer ? this.thrower.rotationYaw : this.thrower.renderYawOffset;
        this.setLocationAndAngles(
            this.thrower.posX,
            this.thrower.posY + (double) this.thrower.getEyeHeight(),
            this.thrower.posZ,
            yaw,
            this.thrower.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw * DEG_TO_RAD) * SPAWN_LATERAL_OFFSET;
        this.posY -= SPAWN_VERTICAL_OFFSET;
        this.posZ -= MathHelper.sin(this.rotationYaw * DEG_TO_RAD) * SPAWN_LATERAL_OFFSET;
        this.setPosition(this.posX, this.posY, this.posZ);
        float speed = this.velocity;
        this.motionX = -MathHelper.sin(this.rotationYaw * DEG_TO_RAD) * MathHelper.cos(this.rotationPitch * DEG_TO_RAD)
            * speed;
        this.motionZ = MathHelper.cos(this.rotationYaw * DEG_TO_RAD) * MathHelper.cos(this.rotationPitch * DEG_TO_RAD)
            * speed;
        this.motionY = -MathHelper.sin((this.rotationPitch + this.getPitchOffset()) * DEG_TO_RAD) * speed;
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
        x += this.rand.nextGaussian() * INACCURACY_SPREAD * (double) inaccuracy;
        y += this.rand.nextGaussian() * INACCURACY_SPREAD * (double) inaccuracy;
        z += this.rand.nextGaussian() * INACCURACY_SPREAD * (double) inaccuracy;
        x *= velocity;
        y *= velocity;
        z *= velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float horizontalSpeed = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * RAD_TO_DEG);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, horizontalSpeed) * RAD_TO_DEG);
    }

    public void setVelocity(double mX, double mY, double mZ) {
        this.motionX = mX;
        this.motionY = mY;
        this.motionZ = mZ;
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float horizontalSpeed = MathHelper.sqrt_double(mX * mX + mZ * mZ);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(mX, mZ) * RAD_TO_DEG);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(mY, horizontalSpeed) * RAD_TO_DEG);
        }
    }

    public void onUpdate() {
        if (this.ticksExisted > MAX_TICKS) {
            this.setDead();
            return;
        }

        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();
        if (this.throwableShake > 0) {
            --this.throwableShake;
        }

        if (this.inGround) {
            this.inGround = false;
            this.motionX *= this.rand.nextFloat() * GROUND_BOUNCE_RANDOMIZATION;
            this.motionY *= this.rand.nextFloat() * GROUND_BOUNCE_RANDOMIZATION;
            this.motionZ *= this.rand.nextFloat() * GROUND_BOUNCE_RANDOMIZATION;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }

        Vec3 startPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 endPos = Vec3
            .createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition blockHit = RayTracing
            .rayTraceBlocks(this.worldObj, startPos, endPos, this::canCollideWithBlock);
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

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float horizontalSpeed = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * RAD_TO_DEG);
        this.rotationPitch = (float) (Math.atan2(this.motionY, horizontalSpeed) * RAD_TO_DEG);

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

        this.rotationPitch = this.prevRotationPitch
            + (this.rotationPitch - this.prevRotationPitch) * ROTATION_SMOOTHING;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * ROTATION_SMOOTHING;

        float drag = AIR_DRAG;
        if (this.isInWater()) {
            for (int i = 0; i < BUBBLE_COUNT; ++i) {
                this.worldObj.spawnParticle(
                    "bubble",
                    this.posX - this.motionX * BUBBLE_OFFSET_FRACTION,
                    this.posY - this.motionY * BUBBLE_OFFSET_FRACTION,
                    this.posZ - this.motionZ * BUBBLE_OFFSET_FRACTION,
                    this.motionX,
                    this.motionY,
                    this.motionZ);
            }
            drag = WATER_DRAG;
        }

        this.motionX *= drag;
        this.motionY *= drag;
        this.motionZ *= drag;
        this.motionY -= this.gravityVelocity;
        this.setPosition(this.posX, this.posY, this.posZ);
    }

    private Entity findClosestEntityOnPath(Vec3 startPos, Vec3 endPos) {
        Entity closestEntity = null;
        List<Entity> nearbyEntities = this.worldObj.getEntitiesWithinAABBExcludingEntity(
            this,
            this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ)
                .expand(ENTITY_SEARCH_EXPANSION, ENTITY_SEARCH_EXPANSION, ENTITY_SEARCH_EXPANSION));
        double closestDistance = 0.0;
        EntityLivingBase thrower = this.getThrower();

        for (Entity candidate : nearbyEntities) {
            if (candidate.canBeCollidedWith() && (candidate != thrower || this.ticksInAir >= THROWER_IMMUNITY_TICKS)) {
                AxisAlignedBB expandedBbox = candidate.boundingBox
                    .expand(ENTITY_HITBOX_EXPANSION, ENTITY_HITBOX_EXPANSION, ENTITY_HITBOX_EXPANSION);
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

    protected abstract void onImpact(MovingObjectPosition hit);

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setLong(TAG_TIMESTAMP, this.timestamp);
        tagCompound.setShort(TAG_X_TILE, (short) this.xTile);
        tagCompound.setShort(TAG_Y_TILE, (short) this.yTile);
        tagCompound.setShort(TAG_Z_TILE, (short) this.zTile);
        tagCompound.setByte(TAG_SHAKE, (byte) this.throwableShake);
        tagCompound.setByte(TAG_IN_GROUND, (byte) (this.inGround ? 1 : 0));
        if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof EntityPlayer) {
            this.throwerName = this.thrower.getCommandSenderName();
        }
        tagCompound.setString(TAG_OWNER_NAME, this.throwerName == null ? "" : this.throwerName);
        tagCompound.setFloat(TAG_GRAVITY_VELOCITY, this.gravityVelocity);
    }

    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        this.xTile = tagCompound.getShort(TAG_X_TILE);
        this.yTile = tagCompound.getShort(TAG_Y_TILE);
        this.zTile = tagCompound.getShort(TAG_Z_TILE);
        this.throwableShake = tagCompound.getByte(TAG_SHAKE) & 0xFF;
        this.inGround = tagCompound.getByte(TAG_IN_GROUND) == 1;
        this.throwerName = tagCompound.getString(TAG_OWNER_NAME);
        if (this.throwerName != null && this.throwerName.isEmpty()) {
            this.throwerName = null;
        }
        this.gravityVelocity = tagCompound.getFloat(TAG_GRAVITY_VELOCITY);
        this.timestamp = tagCompound.getLong(TAG_TIMESTAMP);
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

    protected void entityInit() {}

    public boolean isInRangeToRenderDist(double distanceSq) {
        double renderDistance = this.boundingBox.getAverageEdgeLength() * RENDER_DISTANCE_BBOX_SCALE;
        renderDistance *= RENDER_DISTANCE_MULTIPLIER;
        return distanceSq < renderDistance * renderDistance;
    }

    public boolean canCollideWithBlock(Block block, BlockState metadata) {
        return block.canCollideCheck(metadata.getBlockMetadata(), false);
    }
}
