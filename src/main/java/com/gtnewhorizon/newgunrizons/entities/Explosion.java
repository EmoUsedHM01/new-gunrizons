package com.gtnewhorizon.newgunrizons.entities;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.gtnewhorizon.newgunrizons.client.particle.ParticleManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.network.ExplosionMessage;
import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import lombok.Getter;

public class Explosion {

    private static final ResourceLocation SMOKE_TEXTURE = new ResourceLocation(
        "newgunrizons:/com/gtnewhorizon/newgunrizons/resources/large-smoke.png");
    private final ModContext modContext;
    private final boolean isFlaming;
    private final boolean isSmoking;
    private final Random explosionRNG;
    @Getter
    private final World world;
    private final double explosionX;
    private final double explosionY;
    private final double explosionZ;
    private final Entity exploder;
    private final float explosionSize;
    @Getter
    private final List<BlockPos> affectedBlockPositions;
    @Getter
    private final Map<EntityPlayer, Vec3> playerKnockbackMap;
    @Getter
    private final Vec3 position;

    public static void createServerSideExplosion(ModContext modContext, World world, Entity entity, double posX,
        double posY, double posZ, float explosionStrength, boolean isFlaming, boolean isSmoking) {
        Explosion explosion = new Explosion(
            modContext,
            world,
            entity,
            posX,
            posY,
            posZ,
            explosionStrength,
            isFlaming,
            isSmoking);
        explosion.doExplosionA();
        explosion.doExplosionB(false);
        if (!isSmoking) {
            explosion.clearAffectedBlockPositions();
        }

        for (Entity player : world.playerEntities) {
            if (player.getDistanceSq(posX, posY, posZ) < 4096.0D) {
                modContext.getChannel()
                    .sendTo(
                        new ExplosionMessage(
                            posX,
                            posY,
                            posZ,
                            explosionStrength,
                            explosion.getAffectedBlockPositions(),
                            explosion.getPlayerKnockbackMap()
                                .get(player)),
                        (EntityPlayerMP) player);
            }
        }

    }

    public Explosion(ModContext modContext, World worldIn, Entity entityIn, double x, double y, double z, float size,
        List<BlockPos> affectedPositions) {
        this(modContext, worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
    }

    public Explosion(ModContext modContext, World worldIn, Entity entityIn, double x, double y, double z, float size,
        boolean flaming, boolean smoking, List<BlockPos> affectedPositions) {
        this(modContext, worldIn, entityIn, x, y, z, size, flaming, smoking);
        this.affectedBlockPositions.addAll(affectedPositions);
    }

    public Explosion(ModContext modContext, World worldIn, Entity entityIn, double x, double y, double z, float size,
        boolean flaming, boolean smoking) {
        this.modContext = modContext;
        this.explosionRNG = new Random();
        this.affectedBlockPositions = Lists.newArrayList();
        this.playerKnockbackMap = Maps.newHashMap();
        this.world = worldIn;
        this.exploder = entityIn;
        this.explosionSize = size;
        this.explosionX = x;
        this.explosionY = y;
        this.explosionZ = z;
        this.isFlaming = flaming;
        this.isSmoking = smoking;
        this.position = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);
    }

    public void doExplosionA() {
        Set<BlockPos> affectedBlocks = Sets.newHashSet();

        for (int x = 0; x < 16; ++x) {
            for (int y = 0; y < 16; ++y) {
                for (int z = 0; z < 16; ++z) {
                    if (x == 0 || x == 15 || y == 0 || y == 15 || z == 0 || z == 15) {
                        double dirX = (float) x / 15.0F * 2.0F - 1.0F;
                        double dirY = (float) y / 15.0F * 2.0F - 1.0F;
                        double dirZ = (float) z / 15.0F * 2.0F - 1.0F;
                        double dirLength = Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
                        dirX /= dirLength;
                        dirY /= dirLength;
                        dirZ /= dirLength;
                        float strength = this.explosionSize * (0.7F + this.world.rand.nextFloat() * 0.6F);
                        double rayX = this.explosionX;
                        double rayY = this.explosionY;

                        for (double rayZ = this.explosionZ; strength > 0.0F; strength -= 0.225F) {
                            BlockPos pos = new BlockPos((int) rayX, (int) rayY, (int) rayZ);
                            Block block = this.world.getBlock(pos.getX(), pos.getY(), pos.getZ());
                            if (block.getMaterial() != Material.air) {
                                float resistance = this.exploder != null ? block.getExplosionResistance(
                                    this.exploder,
                                    this.world,
                                    pos.getX(),
                                    pos.getY(),
                                    pos.getZ(),
                                    this.explosionX,
                                    this.explosionY,
                                    this.explosionZ)
                                    : block.getExplosionResistance(null);
                                strength -= (resistance + 0.3F) * 0.3F;
                            }

                            if (strength > 0.0F && (this.exploder == null || this.exploder.func_145774_a(
                                getCompatibleExplosion(),
                                this.world,
                                pos.getX(),
                                pos.getY(),
                                pos.getZ(),
                                block,
                                strength))) {
                                affectedBlocks.add(pos);
                            }

                            rayX += dirX * 0.3D;
                            rayY += dirY * 0.3D;
                            rayZ += dirZ * 0.3D;
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(affectedBlocks);
        float blastDiameter = this.explosionSize * 2.0F;
        int minX = MathHelper.floor_double(this.explosionX - (double) blastDiameter - 1.0D);
        int maxX = MathHelper.floor_double(this.explosionX + (double) blastDiameter + 1.0D);
        int minY = MathHelper.floor_double(this.explosionY - (double) blastDiameter - 1.0D);
        int maxY = MathHelper.floor_double(this.explosionY + (double) blastDiameter + 1.0D);
        int minZ = MathHelper.floor_double(this.explosionZ - (double) blastDiameter - 1.0D);
        int maxZ = MathHelper.floor_double(this.explosionZ + (double) blastDiameter + 1.0D);
        List<Entity> nearbyEntities = this.world.getEntitiesWithinAABBExcludingEntity(
            this.exploder,
            AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ));
        Vec3 explosionCenter = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

        for (Entity entity : nearbyEntities) {
            // isImmuneToExplosions always returns false in 1.7.10
            double relativeDistance = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ)
                / (double) blastDiameter;
            if (relativeDistance <= 1.0D) {
                double deltaX = entity.posX - this.explosionX;
                double deltaY = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
                double deltaZ = entity.posZ - this.explosionZ;
                double distance = MathHelper.sqrt_double(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
                if (distance != 0.0D) {
                    deltaX /= distance;
                    deltaY /= distance;
                    deltaZ /= distance;
                    double blockDensity = this.world.getBlockDensity(explosionCenter, entity.boundingBox);
                    double impact = (1.0D - relativeDistance) * blockDensity;
                    entity.attackEntityFrom(
                        DamageSource.setExplosionSource(getCompatibleExplosion()),
                        (float) ((int) ((impact * impact + impact) / 2.0D * 7.0D * (double) blastDiameter + 1.0D)));
                    double knockbackMultiplier = 1.0D;
                    if (entity instanceof EntityLivingBase) {
                        knockbackMultiplier = EnchantmentProtection.func_92092_a(entity, impact);
                    }

                    entity.motionX += deltaX * knockbackMultiplier;
                    entity.motionY += deltaY * knockbackMultiplier;
                    entity.motionZ += deltaZ * knockbackMultiplier;
                    if (entity instanceof EntityPlayer entityplayer) {
                        if (!entityplayer.capabilities.isCreativeMode || !entityplayer.capabilities.isFlying) {
                            this.playerKnockbackMap.put(
                                entityplayer,
                                Vec3.createVectorHelper(deltaX * impact, deltaY * impact, deltaZ * impact));
                        }
                    }
                }
            }
        }

    }

    public void doExplosionB(boolean spawnParticles) {
        String sound = this.modContext.getExplosionSound();
        if (sound != null) {
            this.world.playSoundEffect(
                this.explosionX,
                this.explosionY,
                this.explosionZ,
                sound,
                4.0F,
                (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
        }

        if (this.isSmoking) {
            for (BlockPos blockpos : this.affectedBlockPositions) {
                Block block = this.world.getBlock(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                if (spawnParticles) {
                    for (int i = 0; i < 3; ++i) {
                        double particleX = (float) blockpos.getX() + this.world.rand.nextFloat();
                        double particleY = (float) blockpos.getY() + this.world.rand.nextFloat();
                        double particleZ = (float) blockpos.getZ() + this.world.rand.nextFloat();
                        double offsetX = particleX - this.explosionX;
                        double offsetY = particleY - this.explosionY;
                        double offsetZ = particleZ - this.explosionZ;
                        double distance = MathHelper
                            .sqrt_double(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
                        offsetX /= distance;
                        offsetY /= distance;
                        offsetZ /= distance;
                        double velocityScale = 4.0D / (distance / (double) this.explosionSize + 0.1D);
                        velocityScale *= this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F;
                        offsetX *= velocityScale;
                        offsetY *= velocityScale;
                        offsetZ *= velocityScale;
                        ParticleManager.spawnExplosionParticle(
                                (particleX + this.explosionX) / 2.0D,
                                (particleY + this.explosionY) / 2.0D,
                                (particleZ + this.explosionZ) / 2.0D,
                                offsetX / 2.0D,
                                offsetY * 2.0D,
                                offsetZ / 2.0D,
                                1.5F * this.world.rand.nextFloat(),
                                15 + (int) (this.world.rand.nextFloat() * 10.0F));
                    }
                }

                if (block.getMaterial() != Material.air) {
                    if (block.canDropFromExplosion(getCompatibleExplosion())) {
                        int meta = this.world.getBlockMetadata(
                            blockpos.getX(),
                            blockpos.getY(),
                            blockpos.getZ());
                        block.dropBlockAsItemWithChance(
                            this.world,
                            blockpos.getX(),
                            blockpos.getY(),
                            blockpos.getZ(),
                            meta,
                            1.0F / this.explosionSize,
                            0);
                    }

                    block.onBlockExploded(
                        this.world,
                        blockpos.getX(),
                        blockpos.getY(),
                        blockpos.getZ(),
                        getCompatibleExplosion());
                }
            }

            if (spawnParticles) {
                for (int i = 0; i < 15; ++i) {
                    double pX = this.explosionX + this.world.rand.nextGaussian() * 0.8D;
                    double pY = this.explosionY + this.world.rand.nextGaussian() * 0.9D;
                    double pZ = this.explosionZ + this.world.rand.nextGaussian() * 0.8D;
                    double motionX = this.world.rand.nextGaussian() * 0.001D;
                    double motionY = this.world.rand.nextGaussian() * 1.0E-4D;
                    double motionZ = this.world.rand.nextGaussian() * 0.001D;
                    ParticleManager.spawnExplosionSmoke(
                            pX,
                            pY,
                            pZ,
                            motionX,
                            motionY,
                            motionZ,
                            1.0F,
                            250 + (int) (this.world.rand.nextFloat() * 30.0F),
                        SMOKE_TEXTURE);
                }
            }
        }

        if (this.isFlaming) {
            for (BlockPos blockpos : this.affectedBlockPositions) {
                if (this.world.getBlock(blockpos.getX(), blockpos.getY(), blockpos.getZ())
                    .getMaterial() == Material.air
                    && this.world.getBlock(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ())
                        .func_149730_j()
                    && this.explosionRNG.nextInt(3) == 0) {
                    this.world.setBlock(
                        blockpos.getX(),
                        blockpos.getY(),
                        blockpos.getZ(),
                        Blocks.fire);
                }
            }
        }

    }

    private net.minecraft.world.Explosion getCompatibleExplosion() {
        return new net.minecraft.world.Explosion(
            this.world,
            this.exploder,
            this.explosionX,
            this.explosionY,
            this.explosionZ,
            this.explosionSize);
    }

    public void clearAffectedBlockPositions() {
        this.affectedBlockPositions.clear();
    }

}
