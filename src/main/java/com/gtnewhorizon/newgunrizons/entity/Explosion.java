package com.gtnewhorizon.newgunrizons.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.gtnewhorizon.newgunrizons.client.particle.ExplosionSmokeFX;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.network.ExplosionMessage;
import com.gtnewhorizon.newgunrizons.util.BlockPos;
import com.gtnewhorizon.newgunrizons.util.BlockState;

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
        float damageCoefficient = 1.0F;
        explosionStrength *= damageCoefficient;
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
        Set<BlockPos> set = Sets.newHashSet();

        int k;
        int l;
        for (int j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                for (l = 0; l < 16; ++l) {
                    if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
                        double d0 = (float) j / 15.0F * 2.0F - 1.0F;
                        double d1 = (float) k / 15.0F * 2.0F - 1.0F;
                        double d2 = (float) l / 15.0F * 2.0F - 1.0F;
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f = this.explosionSize * (0.7F + this.world.rand.nextFloat() * 0.6F);
                        double d4 = this.explosionX;
                        double d6 = this.explosionY;

                        for (double d8 = this.explosionZ; f > 0.0F; f -= 0.22500001F) {
                            BlockPos blockpos = new BlockPos((int) d4, (int) d6, (int) d8);
                            BlockState blockState = BlockState.fromBlock(
                                this.world.getBlock(
                                    blockpos.getBlockPosX(),
                                    blockpos.getBlockPosY(),
                                    blockpos.getBlockPosZ()));
                            if (blockState.getBlock()
                                .getMaterial() != Material.air) {
                                float f2 = this.exploder != null ? blockState.getBlock()
                                    .getExplosionResistance(
                                        this.exploder,
                                        this.world,
                                        blockpos.getBlockPosX(),
                                        blockpos.getBlockPosY(),
                                        blockpos.getBlockPosZ(),
                                        this.explosionX,
                                        this.explosionY,
                                        this.explosionZ)
                                    : blockState.getBlock()
                                        .getExplosionResistance(null);
                                f -= (f2 + 0.3F) * 0.3F;
                            }

                            if (f > 0.0F && (this.exploder == null || this.exploder.func_145774_a(
                                getCompatibleExplosion(),
                                this.world,
                                blockpos.getBlockPosX(),
                                blockpos.getBlockPosY(),
                                blockpos.getBlockPosZ(),
                                blockState.getBlock(),
                                f))) {
                                set.add(blockpos);
                            }

                            d4 += d0 * 0.30000001192092896D;
                            d6 += d1 * 0.30000001192092896D;
                            d8 += d2 * 0.30000001192092896D;
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(set);
        float f3 = this.explosionSize * 2.0F;
        k = MathHelper.floor_double(this.explosionX - (double) f3 - 1.0D);
        l = MathHelper.floor_double(this.explosionX + (double) f3 + 1.0D);
        int i2 = MathHelper.floor_double(this.explosionY - (double) f3 - 1.0D);
        int i1 = MathHelper.floor_double(this.explosionY + (double) f3 + 1.0D);
        int j2 = MathHelper.floor_double(this.explosionZ - (double) f3 - 1.0D);
        int j1 = MathHelper.floor_double(this.explosionZ + (double) f3 + 1.0D);
        List<Entity> list = this.world
            .getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getBoundingBox(k, i2, j2, l, i1, j1));
        Vec3 vec3d = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

        for (Entity entity : list) {
            {
                // isImmuneToExplosions always returns false in 1.7.10
                double d12 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double) f3;
                if (d12 <= 1.0D) {
                    double d5 = entity.posX - this.explosionX;
                    double d7 = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
                    double d9 = entity.posZ - this.explosionZ;
                    double d13 = MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);
                    if (d13 != 0.0D) {
                        d5 /= d13;
                        d7 /= d13;
                        d9 /= d13;
                        double d14 = this.world.getBlockDensity(vec3d, entity.boundingBox);
                        double d10 = (1.0D - d12) * d14;
                        entity.attackEntityFrom(
                            DamageSource.setExplosionSource(getCompatibleExplosion()),
                            (float) ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * (double) f3 + 1.0D)));
                        double d11 = 1.0D;
                        if (entity instanceof EntityLivingBase) {
                            d11 = EnchantmentProtection.func_92092_a(entity, d10);
                        }

                        entity.motionX += d5 * d11;
                        entity.motionY += d7 * d11;
                        entity.motionZ += d9 * d11;
                        if (entity instanceof EntityPlayer entityplayer) {
                            if (!entityplayer.capabilities.isCreativeMode || !entityplayer.capabilities.isFlying) {
                                this.playerKnockbackMap
                                    .put(entityplayer, Vec3.createVectorHelper(d5 * d10, d7 * d10, d9 * d10));
                            }
                        }
                    }
                }
            }
        }

    }

    public void doExplosionB(boolean spawnParticles) {
        {
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
        }
        Iterator var2;
        BlockPos blockpos;
        if (this.isSmoking) {
            var2 = this.affectedBlockPositions.iterator();

            while (var2.hasNext()) {
                blockpos = (BlockPos) var2.next();
                BlockState blockState = BlockState.fromBlock(
                    this.world.getBlock(blockpos.getBlockPosX(), blockpos.getBlockPosY(), blockpos.getBlockPosZ()));
                if (spawnParticles) {
                    for (int i = 0; i < 3; ++i) {
                        double d0 = (float) blockpos.getBlockPosX() + this.world.rand.nextFloat();
                        double d1 = (float) blockpos.getBlockPosY() + this.world.rand.nextFloat();
                        double d2 = (float) blockpos.getBlockPosZ() + this.world.rand.nextFloat();
                        double d3 = d0 - this.explosionX;
                        double d4 = d1 - this.explosionY;
                        double d5 = d2 - this.explosionZ;
                        double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        double d7 = 4.0D / (d6 / (double) this.explosionSize + 0.1D);
                        d7 *= this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F;
                        d3 *= d7;
                        d4 *= d7;
                        d5 *= d7;
                        this.modContext.getEffectManager()
                            .spawnExplosionParticle(
                                (d0 + this.explosionX) / 2.0D,
                                (d1 + this.explosionY) / 2.0D,
                                (d2 + this.explosionZ) / 2.0D,
                                d3 / 2.0D,
                                d4 * 2.0D,
                                d5 / 2.0D,
                                1.5F * this.world.rand.nextFloat(),
                                15 + (int) (this.world.rand.nextFloat() * 10.0F));
                    }
                }

                if (blockState.getBlock()
                    .getMaterial() != Material.air) {
                    if (blockState.getBlock()
                        .canDropFromExplosion(getCompatibleExplosion())) {
                        int meta = this.world.getBlockMetadata(
                            blockpos.getBlockPosX(),
                            blockpos.getBlockPosY(),
                            blockpos.getBlockPosZ());
                        blockState.getBlock()
                            .dropBlockAsItemWithChance(
                                this.world,
                                blockpos.getBlockPosX(),
                                blockpos.getBlockPosY(),
                                blockpos.getBlockPosZ(),
                                meta,
                                1.0F / this.explosionSize,
                                0);
                    }

                    blockState.getBlock()
                        .onBlockExploded(
                            this.world,
                            blockpos.getBlockPosX(),
                            blockpos.getBlockPosY(),
                            blockpos.getBlockPosZ(),
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
                    this.modContext.getEffectManager()
                        .spawnExplosionSmoke(
                            pX,
                            pY,
                            pZ,
                            motionX,
                            motionY,
                            motionZ,
                            1.0F,
                            250 + (int) (this.world.rand.nextFloat() * 30.0F),
                            ExplosionSmokeFX.Behavior.EXPLOSION,
                            SMOKE_TEXTURE);
                }
            }
        }

        if (this.isFlaming) {
            var2 = this.affectedBlockPositions.iterator();

            while (var2.hasNext()) {
                blockpos = (BlockPos) var2.next();
                if (this.world.getBlock(blockpos.getBlockPosX(), blockpos.getBlockPosY(), blockpos.getBlockPosZ())
                    .getMaterial() == Material.air
                    && BlockState.fromBlock(
                        this.world
                            .getBlock(blockpos.getBlockPosX(), blockpos.getBlockPosY() - 1, blockpos.getBlockPosZ()))
                        .getBlock()
                        .func_149730_j()
                    && this.explosionRNG.nextInt(3) == 0) {
                    this.world.setBlock(
                        blockpos.getBlockPosX(),
                        blockpos.getBlockPosY(),
                        blockpos.getBlockPosZ(),
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
