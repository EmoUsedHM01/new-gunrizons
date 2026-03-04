package com.gtnewhorizon.newgunrizons.entities;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.network.SpawnParticleMessage;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import io.netty.buffer.ByteBuf;

public class EntityBullet extends EntityProjectile {

    private static final String TAG_ENTITY_ITEM = "entityItem";
    private static final String TAG_DAMAGE = "damage";
    private static final String TAG_EXPLOSION_RADIUS = "explosionRadius";

    private float explosionRadius;
    private float damage = 6.0F;
    @Getter
    private ItemWeapon weapon;

    public EntityBullet(World world) {
        super(world);
    }

    public EntityBullet(ItemWeapon weapon, World world, EntityLivingBase player, float speed, float gravityVelocity,
                        float inaccuracy, float damage, float explosionRadius) {
        super(world, player, speed, gravityVelocity, inaccuracy);
        this.weapon = weapon;
        this.damage = damage;
        this.explosionRadius = explosionRadius;
    }

    public void onUpdate() {
        super.onUpdate();
    }

    public void onImpact(MovingObjectPosition position) {

        if (worldObj.isRemote || weapon == null)
            return;

        if (explosionRadius > 0.0F) {
            Explosion.createServerSideExplosion(
                weapon.getModContext(),
                worldObj,
                this,
                position.hitVec.xCoord,
                position.hitVec.yCoord,
                position.hitVec.zCoord,
                explosionRadius,
                false,
                true);
        } else if (position.entityHit != null) {
            if (getThrower() == null) {
                position.entityHit.attackEntityFrom(new DamageSource("generic"), damage);
            } else {
                position.entityHit
                    .attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
            }

            position.entityHit.hurtResistantTime = 0;
            Entity var10000 = position.entityHit;
            var10000.prevRotationYaw = (float) ((double) var10000.prevRotationYaw - 0.3D);
            NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(
                position.entityHit.dimension,
                posX,
                posY,
                posZ,
                100.0D);

            double magnitude = Math.sqrt(
                this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ) + 1.0D;

            float bleedingCoefficient = 1.0F;
            int count = (int) ((float) this.getParticleCount(this.damage) * bleedingCoefficient);
            SimpleNetworkWrapper var8 = this.weapon.getModContext()
                .getChannel();
            SpawnParticleMessage.ParticleType var10003 = SpawnParticleMessage.ParticleType.BLOOD;
            double var10006 = this.motionX / magnitude;
            double var10005 = position.entityHit.posX - var10006;
            double var10007 = this.motionY / magnitude;
            var10006 = position.entityHit.posY - var10007;
            double var10008 = this.motionZ / magnitude;
            var8.sendToAllAround(
                new SpawnParticleMessage(
                    var10003,
                    count,
                    var10005,
                    var10006,
                    position.entityHit.posZ - var10008),
                point);
        }

        this.setDead();
    }

    public void writeSpawnData(ByteBuf buffer) {
        super.writeSpawnData(buffer);
        buffer.writeInt(Item.getIdFromItem(this.weapon));
        buffer.writeFloat(this.damage);
        buffer.writeFloat(this.explosionRadius);
    }

    public void readSpawnData(ByteBuf buffer) {
        super.readSpawnData(buffer);
        this.weapon = (ItemWeapon) Item.getItemById(buffer.readInt());
        this.damage = buffer.readFloat();
        this.explosionRadius = buffer.readFloat();
    }

    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);
        Item item = Item.getItemById(tagCompound.getInteger(TAG_ENTITY_ITEM));
        if (item instanceof ItemWeapon) {
            this.weapon = (ItemWeapon) item;
        }

        this.damage = tagCompound.getFloat(TAG_DAMAGE);
        this.explosionRadius = tagCompound.getFloat(TAG_EXPLOSION_RADIUS);
    }

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger(TAG_ENTITY_ITEM, Item.getIdFromItem(this.weapon));
        tagCompound.setFloat(TAG_DAMAGE, this.damage);
        tagCompound.setFloat(TAG_EXPLOSION_RADIUS, this.explosionRadius);
    }

    int getParticleCount(float damage) {
        return (int) (-0.11D * (double) (damage - 30.0F) * (double) (damage - 30.0F) + 100.0D);
    }

    public boolean canCollideWithBlock(Block block, int metadata) {
        return !(block == Blocks.air || block == Blocks.tallgrass
            || block == Blocks.leaves
            || block == Blocks.leaves2
            || block == Blocks.fire
            || block == Blocks.hay_block
            || block == Blocks.double_plant
            || block == Blocks.web
            || block == Blocks.wheat) && super.canCollideWithBlock(block, metadata);
    }
}
