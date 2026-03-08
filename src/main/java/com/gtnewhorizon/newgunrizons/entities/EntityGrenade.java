package com.gtnewhorizon.newgunrizons.entities;

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
import net.minecraft.world.World;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.util.RayCast;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

public class EntityGrenade extends Entity implements IEntityAdditionalSpawnData, IThrowableEntity {

    private static final Logger logger = LogManager.getLogger(EntityGrenade.class);
    private static final int VELOCITY_HISTORY_SIZE = 10;
    private static final double STOP_THRESHOLD = 0.001D;
    private static final int MAX_TICKS = 2000;
    private static final String TAG_ENTITY_ITEM = "entity_item";

    private float gravityVelocity;
    private float slowdownFactor = 0.5F;
    private int ticksInAir;
    @Getter
    private EntityLivingBase thrower;
    protected int bounceCount;
    private float initialYaw;
    private float initialPitch;
    @Getter
    private float xRotation;
    private float yRotation;
    @Getter
    private float zRotation;
    private float xRotationChange;
    private float yRotationChange;
    private float zRotationChange;
    private float rotationSlowdownFactor = 0.99F;
    private final float maxRotationChange = 20.0F;
    protected boolean stopped;
    private final Queue<Double> velocityHistory = new ArrayDeque<>(10);

    @Getter
    protected ItemGrenade itemGrenade;
    private long explosionTimeout;
    private float explosionStrength;
    private long activationTimestamp;

    private EntityGrenade(ItemGrenade itemGrenade, EntityLivingBase thrower, float velocity, float gravityVelocity,
        float rotationSlowdownFactor) {
        super(thrower.worldObj);
        this.thrower = thrower;
        this.gravityVelocity = gravityVelocity;
        this.rotationSlowdownFactor = rotationSlowdownFactor;
        this.itemGrenade = itemGrenade;
        this.setSize(0.3F, 0.3F);
        this.setLocationAndAngles(
            thrower.posX,
            thrower.posY + (double) thrower.getEyeHeight(),
            thrower.posZ,
            (thrower instanceof EntityPlayer ? thrower.rotationYaw : thrower.renderYawOffset),
            thrower.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        float f = 0.4F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F)
            * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F)
            * f;
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F)
            * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F)
            * f;
        this.motionY = -MathHelper.sin((this.rotationPitch + 0.0F) / 180.0F * 3.1415927F) * f;
        this.initialYaw = this.rotationYaw;
        this.initialPitch = this.rotationPitch;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity, 10.0F);
        logger.debug(
            "Throwing with position {}{}{}, rotation pitch {}, velocity {}, {}, {}",
            this.posX,
            this.posY,
            this.posZ,
            this.rotationPitch,
            this.motionX,
            this.motionY,
            this.motionZ);
    }

    public EntityGrenade(World world) {
        super(world);
        this.xRotationChange = this.maxRotationChange * (float) this.rand.nextGaussian();
        this.yRotationChange = this.maxRotationChange * (float) this.rand.nextGaussian();
        this.zRotationChange = this.maxRotationChange * (float) this.rand.nextGaussian();
    }

    public void setThrowableHeading(double motionX, double motionY, double motionZ, float velocity, float inaccuracy) {
        float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
        motionX /= f2;
        motionY /= f2;
        motionZ /= f2;
        motionX += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        motionY += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        motionZ += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        motionX *= velocity;
        motionY *= velocity;
        motionZ *= velocity;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(motionY, f3) * 180.0D / Math.PI);
    }

    public void setThrower(Entity thrower) {
        this.thrower = (EntityLivingBase) thrower;
    }

    public void onUpdate() {
        if (!this.worldObj.isRemote && this.ticksExisted > MAX_TICKS) {
            this.setDead();
        } else {
            this.xRotation += this.xRotationChange;
            this.yRotation += this.yRotationChange;
            this.zRotation += this.zRotationChange;
            this.xRotationChange *= this.rotationSlowdownFactor;
            this.yRotationChange *= this.rotationSlowdownFactor;
            this.zRotationChange *= this.rotationSlowdownFactor;
            this.lastTickPosX = this.posX;
            this.lastTickPosY = this.posY;
            this.lastTickPosZ = this.posZ;
            super.onUpdate();
            ++this.ticksInAir;
            if (!this.stopped) {
                Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
                Vec3 vec31 = Vec3
                    .createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
                MovingObjectPosition movingobjectposition = RayCast
                    .rayCastBlocks(this.worldObj, vec3, vec31, this::canCollideWithBlock);
                vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
                vec31 = Vec3
                    .createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
                if (movingobjectposition != null) {
                    vec31 = Vec3.createVectorHelper(
                        movingobjectposition.hitVec.xCoord,
                        movingobjectposition.hitVec.yCoord,
                        movingobjectposition.hitVec.zCoord);
                }

                if (this.thrower != null) {
                    Entity entity = null;
                    List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(
                        this,
                        this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ)
                            .expand(1.0D, 1.0D, 1.0D));
                    double d0 = 0.0D;
                    EntityLivingBase entitylivingbase = this.getThrower();
                    MovingObjectPosition entityMovingObjectPosition = null;

                    for (Entity entity1 : list) {
                        if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.ticksInAir >= 5)) {
                            float f = 0.3F;
                            AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
                            MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
                            if (movingobjectposition1 != null) {
                                double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
                                if (d1 < d0 || d0 == 0.0D) {
                                    entity = entity1;
                                    entityMovingObjectPosition = movingobjectposition1;
                                    d0 = d1;
                                }
                            }
                        }
                    }

                    if (entity != null) {
                        movingobjectposition = new MovingObjectPosition(entity);
                        movingobjectposition.sideHit = entityMovingObjectPosition.sideHit;
                        movingobjectposition.hitVec = entityMovingObjectPosition.hitVec;
                    }
                }

                logger.trace(
                    "Ori position to {}, {}, {}, motion {} {} {} ",
                    this.posX,
                    this.posY,
                    this.posZ,
                    this.motionX,
                    this.motionY,
                    this.motionZ);
                if (movingobjectposition != null
                    && (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                        || movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)) {
                    logger.trace(
                        "Hit {}, vec set to {}, {}, {}",
                        movingobjectposition.typeOfHit,
                        movingobjectposition.hitVec.xCoord,
                        movingobjectposition.hitVec.yCoord,
                        movingobjectposition.hitVec.zCoord);
                    logger.trace(
                        "Before bouncing {}, side {}, motion set to {}, {}, {}",
                        this.bounceCount,
                        movingobjectposition.sideHit,
                        this.motionX,
                        this.motionY,
                        this.motionZ);
                    this.posX = movingobjectposition.hitVec.xCoord;
                    this.posY = movingobjectposition.hitVec.yCoord;
                    this.posZ = movingobjectposition.hitVec.zCoord;
                    switch (movingobjectposition.sideHit) {
                        case 0: // DOWN
                            this.motionY = -this.motionY;
                            this.posY += this.motionY;
                            break;
                        case 1: // UP
                            this.motionY = -this.motionY;
                            break;
                        case 2: // NORTH
                            this.motionZ = -this.motionZ;
                            this.posZ += this.motionZ;
                            break;
                        case 3: // SOUTH
                            this.motionZ = -this.motionZ;
                            break;
                        case 4: // WEST
                            this.motionX = -this.motionX;
                            this.posX += this.motionX;
                            break;
                        case 5: // EAST
                            this.motionX = -this.motionX;
                    }

                    this.setPosition(this.posX, this.posY, this.posZ);
                    if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                        this.avoidEntityCollisionAfterBounce(movingobjectposition);
                    } else if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                        this.avoidBlockCollisionAfterBounce(movingobjectposition);
                    }

                    logger.trace(
                        "After bouncing {}  motion set to {}, {}, {}",
                        this.bounceCount,
                        this.motionX,
                        this.motionY,
                        this.motionZ);
                    this.onBounce(movingobjectposition);
                    ++this.bounceCount;
                    if (this.isDead) {
                        return;
                    }
                } else {
                    this.posX += this.motionX;
                    this.posY += this.motionY;
                    this.posZ += this.motionZ;
                }

                this.setPosition(this.posX, this.posY, this.posZ);
                float motionSquared = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

                for (this.rotationPitch = (float) (Math.atan2(this.motionY, motionSquared) * 180.0D
                    / Math.PI); this.rotationPitch - this.prevRotationPitch
                        < -180.0F; this.prevRotationPitch -= 360.0F) {}

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
                float f2 = 0.99F;
                float currentGravityVelocity = this.gravityVelocity;
                if (this.isInWater()) {
                    for (int i = 0; i < 4; ++i) {
                        float f4 = 0.25F;
                        this.worldObj.spawnParticle(
                            "bubble",
                            this.posX - this.motionX * (double) f4,
                            this.posY - this.motionY * (double) f4,
                            this.posZ - this.motionZ * (double) f4,
                            this.motionX,
                            this.motionY,
                            this.motionZ);
                    }

                    f2 = 0.8F;
                }

                if (movingobjectposition != null
                    && (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                        || movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)) {
                    f2 = this.slowdownFactor;
                    this.rotationSlowdownFactor *= this.slowdownFactor * 1.5F;
                }

                this.motionX *= f2;
                this.motionY *= f2;
                this.motionZ *= f2;
                this.recordVelocityHistory();
                if (this.velocityHistory.stream()
                    .noneMatch((v) -> v > STOP_THRESHOLD)) {
                    this.motionX = this.motionY = this.motionZ = 0.0D;
                    this.stopped = true;
                    logger.trace("Stopping {}", this);
                } else {
                    this.motionY -= currentGravityVelocity;
                }

                logger.trace(
                    "Set position to {}, {}, {}, motion {} {} {} ",
                    this.posX,
                    this.posY,
                    this.posZ,
                    this.motionX,
                    this.motionY,
                    this.motionZ);
            }
        }

        if (!this.worldObj.isRemote && this.explosionTimeout > 0L
            && System.currentTimeMillis() > this.activationTimestamp + this.explosionTimeout) {
            this.explode();
        }
    }

    public void onBounce(MovingObjectPosition movingobjectposition) {
        if (this.explosionTimeout == -1L && !this.worldObj.isRemote) {
            this.explode();
        } else {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                && this.itemGrenade != null) {
                String bounceHardSound = this.itemGrenade.getBounceHardSound();
                if (bounceHardSound != null) {
                    Block hitBlock = this.worldObj.getBlock(
                        movingobjectposition.blockX,
                        movingobjectposition.blockY,
                        movingobjectposition.blockZ);
                    if (madeFromHardMaterial(hitBlock)) {
                        this.worldObj
                            .playSoundAtEntity(this, bounceHardSound, 2.0F / ((float) this.bounceCount + 1.0F), 1.0F);
                    }
                }

                String bounceSoftSound = this.itemGrenade.getBounceSoftSound();
                if (bounceSoftSound != null) {
                    Block hitBlock = this.worldObj.getBlock(
                        movingobjectposition.blockX,
                        movingobjectposition.blockY,
                        movingobjectposition.blockZ);
                    if (!madeFromHardMaterial(hitBlock)) {
                        this.worldObj
                            .playSoundAtEntity(this, bounceSoftSound, 1.0F / ((float) this.bounceCount + 1.0F), 1.0F);
                    }
                }
            }
        }
    }

    private void avoidBlockCollisionAfterBounce(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            double dX = Math.signum(this.motionX) * 0.05D;
            double dY = Math.signum(this.motionY) * 0.05D;
            double dZ = Math.signum(this.motionZ) * 0.05D;

            for (int i = 0; i < 10; ++i) {
                double projectedXPos = this.posX + dX * (double) i;
                double projectedYPos = this.posY + dY * (double) i;
                double projectedZPos = this.posZ + dZ * (double) i;
                BlockPos blockPos = new BlockPos((int) projectedXPos, (int) projectedYPos, (int) projectedZPos);
                AxisAlignedBB projectedEntityBoundingBox = this.boundingBox
                    .getOffsetBoundingBox(dX * (double) i, dY * (double) i, dZ * (double) i);
                if (this.worldObj.getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ())
                    .getMaterial() == Material.air
                    || !AxisAlignedBB
                        .getBoundingBox(
                            blockPos.getX(),
                            blockPos.getY(),
                            blockPos.getZ(),
                            blockPos.getX() + 1,
                            blockPos.getY() + 1,
                            blockPos.getZ() + 1)
                        .intersectsWith(projectedEntityBoundingBox)) {
                    this.posX = projectedXPos;
                    this.posY = projectedYPos;
                    this.posZ = projectedZPos;
                    logger.trace("Found non-intercepting post-bounce position on iteration {}", i);
                    break;
                }
            }
        }
    }

    private void avoidEntityCollisionAfterBounce(MovingObjectPosition movingobjectposition) {
        if (movingobjectposition.entityHit != null) {
            this.slowdownFactor = 0.3F;
            double dX = Math.signum(this.motionX) * 0.05D;
            double dY = Math.signum(this.motionY) * 0.05D;
            double dZ = Math.signum(this.motionZ) * 0.05D;
            float f = 0.3F;
            AxisAlignedBB axisalignedbb = movingobjectposition.entityHit.boundingBox.expand(f, f, f);
            MovingObjectPosition intercept = movingobjectposition;

            for (int i = 0; i < 10; ++i) {
                Vec3 currentPos = Vec3.createVectorHelper(
                    this.posX + dX * (double) i,
                    this.posY + dY * (double) i,
                    this.posZ + dY * (double) i);
                Vec3 projectedPos = Vec3.createVectorHelper(
                    this.posX + dX * (double) (i + 1),
                    this.posY + dY * (double) (i + 1),
                    this.posZ + dZ * (double) (i + 1));
                intercept = axisalignedbb.calculateIntercept(currentPos, projectedPos);
                if (intercept == null) {
                    BlockPos blockPos = new BlockPos(
                        (int) projectedPos.xCoord,
                        (int) projectedPos.yCoord,
                        (int) projectedPos.zCoord);
                    Block collidedBlock = this.worldObj.getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    if (collidedBlock.getMaterial() != Material.air) {
                        logger.debug("Found non-intercept position colliding with block {}", collidedBlock);
                        intercept = movingobjectposition;
                    } else {
                        this.posX = projectedPos.xCoord;
                        this.posY = projectedPos.yCoord;
                        this.posZ = projectedPos.zCoord;
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
        logger.debug("Exploding {}", this);
        Explosion.createServerSideExplosion(
            this.worldObj,
            this,
            this.posX,
            this.posY,
            this.posZ,
            this.explosionStrength,
            false,
            true);
        List<?> nearbyEntities = this.worldObj
            .getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(5.0D, 5.0D, 5.0D));

        float damageCoefficient = 1.0F;
        float effectiveRadius = this.itemGrenade.getEffectiveRadius() * damageCoefficient;
        float fragmentDamage = this.itemGrenade.getFragmentDamage();
        float configuredFragmentCount = (float) this.itemGrenade.getFragmentCount() * damageCoefficient;

        for (int i = 0; (float) i < configuredFragmentCount; ++i) {
            double x = (this.rand.nextDouble() - 0.5D) * 2.0D;
            double y = (this.rand.nextDouble() - 0.5D) * 2.0D;
            double z = (this.rand.nextDouble() - 0.5D) * 2.0D;
            double d2 = x * x + y * y + z * z;
            if (d2 == 0.0D) {
                logger.debug("Ignoring zero distance index {}", i);
            } else {
                double k = Math.sqrt((double) (effectiveRadius * effectiveRadius) / d2);
                double k2 = 0.1D;
                Vec3 cvec1 = Vec3.createVectorHelper(this.posX + x * k2, this.posY + y * k2, this.posZ + z * k2);
                Vec3 cvec10 = Vec3.createVectorHelper(this.posX + x * k2, this.posY + y * k2, this.posZ + z * k2);
                Vec3 cvec2 = Vec3.createVectorHelper(this.posX + x * k, this.posY + y * k, this.posZ + z * k);
                BiPredicate<Block, Integer> isCollidable = (block, blockMetadata) -> block
                    .canCollideCheck(blockMetadata, false);
                MovingObjectPosition rayTraceResult = RayCast.rayCastBlocks(this.worldObj, cvec1, cvec2, isCollidable);
                if (rayTraceResult != null) {
                    cvec2 = Vec3.createVectorHelper(
                        rayTraceResult.hitVec.xCoord,
                        rayTraceResult.hitVec.yCoord,
                        rayTraceResult.hitVec.zCoord);
                }

                for (Object nearbyEntityObject : nearbyEntities) {
                    Entity nearbyEntity = (Entity) nearbyEntityObject;
                    if (nearbyEntity.canBeCollidedWith()) {
                        float f = 0.5F;
                        AxisAlignedBB axisalignedbb = nearbyEntity.boundingBox.expand(f, f, f);
                        MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(cvec10, cvec2);
                        if (movingobjectposition1 != null) {
                            double distanceToEntity = cvec10.distanceTo(movingobjectposition1.hitVec);
                            float damageDistanceReductionFactor = (float) Math
                                .abs(1.0D - distanceToEntity / (double) effectiveRadius);
                            logger.trace(
                                "Hit entity {} at distance {}, damage reduction {}",
                                nearbyEntity,
                                distanceToEntity,
                                damageDistanceReductionFactor);
                            nearbyEntity.attackEntityFrom(
                                DamageSource.causeThrownDamage(this, this.getThrower()),
                                Math.max(0.1F, this.rand.nextFloat()) * fragmentDamage * damageDistanceReductionFactor);
                        }
                    }
                }
            }
        }

        this.setDead();
    }

    private static boolean madeFromHardMaterial(Block block) {
        Material material = block.getMaterial();
        return material == Material.rock || material == Material.iron
            || material == Material.ice
            || material == Material.wood;
    }

    protected void entityInit() {}

    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        Item item = Item.getItemById(tagCompound.getInteger(TAG_ENTITY_ITEM));
        if (item instanceof ItemGrenade) {
            this.itemGrenade = (ItemGrenade) item;
        }
    }

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger(TAG_ENTITY_ITEM, Item.getIdFromItem(this.itemGrenade));
    }

    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.thrower != null ? this.thrower.getEntityId() : -1);
        buffer.writeDouble(this.posX);
        buffer.writeDouble(this.posY);
        buffer.writeDouble(this.posZ);
        buffer.writeDouble(this.motionX);
        buffer.writeDouble(this.motionY);
        buffer.writeDouble(this.motionZ);
        buffer.writeFloat(this.gravityVelocity);
        buffer.writeFloat(this.rotationSlowdownFactor);
        buffer.writeFloat(this.initialYaw);
        buffer.writeFloat(this.initialPitch);
        buffer.writeInt(Item.getIdFromItem(this.itemGrenade));
        buffer.writeLong(this.activationTimestamp);
        buffer.writeLong(this.explosionTimeout);
        buffer.writeFloat(this.explosionStrength);
    }

    public void readSpawnData(ByteBuf buffer) {
        int entityId = buffer.readInt();
        if (this.thrower == null && entityId >= 0) {
            Entity entity = this.worldObj.getEntityByID(entityId);
            if (entity instanceof EntityLivingBase) {
                this.thrower = (EntityPlayer) entity;
            }
        }

        this.posX = buffer.readDouble();
        this.posY = buffer.readDouble();
        this.posZ = buffer.readDouble();
        this.motionX = buffer.readDouble();
        this.motionY = buffer.readDouble();
        this.motionZ = buffer.readDouble();
        this.gravityVelocity = buffer.readFloat();
        this.rotationSlowdownFactor = buffer.readFloat();
        this.initialYaw = buffer.readFloat();
        this.initialPitch = buffer.readFloat();
        this.setPosition(this.posX, this.posY, this.posZ);
        logger.debug(
            "Restoring with position {}{}{}, rotation pitch {}, velocity {}, {}, {}",
            this.posX,
            this.posY,
            this.posZ,
            this.rotationPitch,
            this.motionX,
            this.motionY,
            this.motionZ);

        Item item = Item.getItemById(buffer.readInt());
        if (item instanceof ItemGrenade) {
            this.itemGrenade = (ItemGrenade) item;
        }

        this.activationTimestamp = buffer.readLong();
        this.explosionTimeout = buffer.readLong();
        this.explosionStrength = buffer.readFloat();
    }

    public float getYRotation() {
        return this.yRotation - this.initialYaw - 90.0F;
    }

    public boolean canCollideWithBlock(Block block, int metadata) {
        return !(block == Blocks.air || block == Blocks.tallgrass
            || block == Blocks.leaves
            || block == Blocks.leaves2
            || block == Blocks.fire
            || block == Blocks.hay_block
            || block == Blocks.double_plant
            || block == Blocks.web
            || block == Blocks.wheat) && block.canCollideCheck(metadata, false);
    }

    private void recordVelocityHistory() {
        double velocity = this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ;
        this.velocityHistory.add(velocity);
        if (this.velocityHistory.size() > VELOCITY_HISTORY_SIZE) {
            this.velocityHistory.poll();
        }
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
            EntityGrenade entityGrenade = new EntityGrenade(
                this.itemGrenade,
                this.thrower,
                this.velocity,
                this.gravityVelocity,
                this.rotationSlowdownFactor);
            entityGrenade.activationTimestamp = this.activationTimestamp;
            entityGrenade.explosionTimeout = this.explosionTimeout;
            entityGrenade.explosionStrength = this.explosionStrength;
            entityGrenade.itemGrenade = this.itemGrenade;
            return entityGrenade;
        }
    }
}
