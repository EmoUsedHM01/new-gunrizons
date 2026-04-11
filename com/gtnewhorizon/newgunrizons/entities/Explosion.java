package com.gtnewhorizon.newgunrizons.entities;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.particle.ParticleManager;
import com.gtnewhorizon.newgunrizons.network.ExplosionMessage;
import com.gtnewhorizon.newgunrizons.registry.Sounds;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
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

public class Explosion {
   private static final ResourceLocation SMOKE_TEXTURE = new ResourceLocation("newgunrizons", "textures/effect/large-smoke.png");
   private final boolean isFlaming;
   private final boolean isSmoking;
   private final Random explosionRNG = new Random();
   private final World world;
   private final double explosionX;
   private final double explosionY;
   private final double explosionZ;
   private final Entity exploder;
   private final float explosionSize;
   private final List<BlockPos> affectedBlockPositions = Lists.newArrayList();
   private final Map<EntityPlayer, Vec3> playerKnockbackMap = Maps.newHashMap();
   private final Vec3 position;

   public static void createServerSideExplosion(
      World world, Entity entity, double posX, double posY, double posZ, float explosionStrength, boolean isFlaming, boolean isSmoking
   ) {
      Explosion explosion = new Explosion(world, entity, posX, posY, posZ, explosionStrength, isFlaming, isSmoking);
      explosion.doExplosionA();
      explosion.doExplosionB(false);
      if (!isSmoking) {
         explosion.clearAffectedBlockPositions();
      }

      for (Entity player : world.field_73010_i) {
         if (player.func_70092_e(posX, posY, posZ) < 4096.0) {
            NewGunrizonsMod.CHANNEL
               .sendTo(
                  new ExplosionMessage(
                     posX, posY, posZ, explosionStrength, explosion.getAffectedBlockPositions(), explosion.getPlayerKnockbackMap().get(player)
                  ),
                  (EntityPlayerMP)player
               );
         }
      }
   }

   public Explosion(World worldIn, Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions) {
      this(worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
   }

   public Explosion(
      World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking, List<BlockPos> affectedPositions
   ) {
      this(worldIn, entityIn, x, y, z, size, flaming, smoking);
      this.affectedBlockPositions.addAll(affectedPositions);
   }

   public Explosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking) {
      this.world = worldIn;
      this.exploder = entityIn;
      this.explosionSize = size;
      this.explosionX = x;
      this.explosionY = y;
      this.explosionZ = z;
      this.isFlaming = flaming;
      this.isSmoking = smoking;
      this.position = Vec3.func_72443_a(this.explosionX, this.explosionY, this.explosionZ);
   }

   public void doExplosionA() {
      Set<BlockPos> affectedBlocks = Sets.newHashSet();

      for (int x = 0; x < 16; x++) {
         for (int y = 0; y < 16; y++) {
            for (int z = 0; z < 16; z++) {
               if (x == 0 || x == 15 || y == 0 || y == 15 || z == 0 || z == 15) {
                  double dirX = x / 15.0F * 2.0F - 1.0F;
                  double dirY = y / 15.0F * 2.0F - 1.0F;
                  double dirZ = z / 15.0F * 2.0F - 1.0F;
                  double dirLength = Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
                  dirX /= dirLength;
                  dirY /= dirLength;
                  dirZ /= dirLength;
                  float strength = this.explosionSize * (0.7F + this.world.field_73012_v.nextFloat() * 0.6F);
                  double rayX = this.explosionX;
                  double rayY = this.explosionY;

                  for (double rayZ = this.explosionZ; strength > 0.0F; strength -= 0.225F) {
                     BlockPos pos = new BlockPos((int)rayX, (int)rayY, (int)rayZ);
                     Block block = this.world.func_147439_a(pos.getX(), pos.getY(), pos.getZ());
                     if (block.func_149688_o() != Material.field_151579_a) {
                        float resistance = this.exploder != null
                           ? block.getExplosionResistance(
                              this.exploder, this.world, pos.getX(), pos.getY(), pos.getZ(), this.explosionX, this.explosionY, this.explosionZ
                           )
                           : block.func_149638_a(null);
                        strength -= (resistance + 0.3F) * 0.3F;
                     }

                     if (strength > 0.0F
                        && (
                           this.exploder == null
                              || this.exploder.func_145774_a(this.getCompatibleExplosion(), this.world, pos.getX(), pos.getY(), pos.getZ(), block, strength)
                        )) {
                        affectedBlocks.add(pos);
                     }

                     rayX += dirX * 0.3;
                     rayY += dirY * 0.3;
                     rayZ += dirZ * 0.3;
                  }
               }
            }
         }
      }

      this.affectedBlockPositions.addAll(affectedBlocks);
      float blastDiameter = this.explosionSize * 2.0F;
      int minX = MathHelper.func_76128_c(this.explosionX - blastDiameter - 1.0);
      int maxX = MathHelper.func_76128_c(this.explosionX + blastDiameter + 1.0);
      int minY = MathHelper.func_76128_c(this.explosionY - blastDiameter - 1.0);
      int maxY = MathHelper.func_76128_c(this.explosionY + blastDiameter + 1.0);
      int minZ = MathHelper.func_76128_c(this.explosionZ - blastDiameter - 1.0);
      int maxZ = MathHelper.func_76128_c(this.explosionZ + blastDiameter + 1.0);
      List<Entity> nearbyEntities = this.world.func_72839_b(this.exploder, AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ));
      Vec3 explosionCenter = Vec3.func_72443_a(this.explosionX, this.explosionY, this.explosionZ);

      for (Entity entity : nearbyEntities) {
         double relativeDistance = entity.func_70011_f(this.explosionX, this.explosionY, this.explosionZ) / blastDiameter;
         if (relativeDistance <= 1.0) {
            double deltaX = entity.field_70165_t - this.explosionX;
            double deltaY = entity.field_70163_u + entity.func_70047_e() - this.explosionY;
            double deltaZ = entity.field_70161_v - this.explosionZ;
            double distance = MathHelper.func_76133_a(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            if (distance != 0.0) {
               deltaX /= distance;
               deltaY /= distance;
               deltaZ /= distance;
               double blockDensity = this.world.func_72842_a(explosionCenter, entity.field_70121_D);
               double impact = (1.0 - relativeDistance) * blockDensity;
               entity.func_70097_a(
                  DamageSource.func_94539_a(this.getCompatibleExplosion()), (int)((impact * impact + impact) / 2.0 * 7.0 * blastDiameter + 1.0)
               );
               double knockbackMultiplier = 1.0;
               if (entity instanceof EntityLivingBase) {
                  knockbackMultiplier = EnchantmentProtection.func_92092_a(entity, impact);
               }

               entity.field_70159_w += deltaX * knockbackMultiplier;
               entity.field_70181_x += deltaY * knockbackMultiplier;
               entity.field_70179_y += deltaZ * knockbackMultiplier;
               if (entity instanceof EntityPlayer) {
                  EntityPlayer entityplayer = (EntityPlayer)entity;
                  if (!entityplayer.field_71075_bZ.field_75098_d || !entityplayer.field_71075_bZ.field_75100_b) {
                     this.playerKnockbackMap.put(entityplayer, Vec3.func_72443_a(deltaX * impact, deltaY * impact, deltaZ * impact));
                  }
               }
            }
         }
      }
   }

   public void doExplosionB(boolean spawnParticles) {
      String sound = Sounds.EXPLOSION;
      if (sound != null) {
         this.world
            .func_72908_a(
               this.explosionX,
               this.explosionY,
               this.explosionZ,
               sound,
               4.0F,
               (1.0F + (this.world.field_73012_v.nextFloat() - this.world.field_73012_v.nextFloat()) * 0.2F) * 0.7F
            );
      }

      if (this.isSmoking) {
         for (BlockPos blockpos : this.affectedBlockPositions) {
            Block block = this.world.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            if (spawnParticles) {
               for (int i = 0; i < 3; i++) {
                  double particleX = blockpos.getX() + this.world.field_73012_v.nextFloat();
                  double particleY = blockpos.getY() + this.world.field_73012_v.nextFloat();
                  double particleZ = blockpos.getZ() + this.world.field_73012_v.nextFloat();
                  double offsetX = particleX - this.explosionX;
                  double offsetY = particleY - this.explosionY;
                  double offsetZ = particleZ - this.explosionZ;
                  double distance = MathHelper.func_76133_a(offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ);
                  offsetX /= distance;
                  offsetY /= distance;
                  offsetZ /= distance;
                  double velocityScale = 4.0 / (distance / this.explosionSize + 0.1);
                  velocityScale *= this.world.field_73012_v.nextFloat() * this.world.field_73012_v.nextFloat() + 0.3F;
                  offsetX *= velocityScale;
                  offsetY *= velocityScale;
                  offsetZ *= velocityScale;
                  ParticleManager.spawnExplosionParticle(
                     (particleX + this.explosionX) / 2.0,
                     (particleY + this.explosionY) / 2.0,
                     (particleZ + this.explosionZ) / 2.0,
                     offsetX / 2.0,
                     offsetY * 2.0,
                     offsetZ / 2.0,
                     1.5F * this.world.field_73012_v.nextFloat(),
                     15 + (int)(this.world.field_73012_v.nextFloat() * 10.0F)
                  );
               }
            }

            if (block.func_149688_o() != Material.field_151579_a) {
               if (block.func_149659_a(this.getCompatibleExplosion())) {
                  int meta = this.world.func_72805_g(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                  block.func_149690_a(this.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), meta, 1.0F / this.explosionSize, 0);
               }

               block.onBlockExploded(this.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), this.getCompatibleExplosion());
            }
         }

         if (spawnParticles) {
            for (int i = 0; i < 15; i++) {
               double pX = this.explosionX + this.world.field_73012_v.nextGaussian() * 0.8;
               double pY = this.explosionY + this.world.field_73012_v.nextGaussian() * 0.9;
               double pZ = this.explosionZ + this.world.field_73012_v.nextGaussian() * 0.8;
               double motionX = this.world.field_73012_v.nextGaussian() * 0.001;
               double motionY = this.world.field_73012_v.nextGaussian() * 1.0E-4;
               double motionZ = this.world.field_73012_v.nextGaussian() * 0.001;
               ParticleManager.spawnExplosionSmoke(
                  pX, pY, pZ, motionX, motionY, motionZ, 1.0F, 250 + (int)(this.world.field_73012_v.nextFloat() * 30.0F), SMOKE_TEXTURE
               );
            }
         }
      }

      if (this.isFlaming) {
         for (BlockPos blockpos : this.affectedBlockPositions) {
            if (this.world.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos.getZ()).func_149688_o() == Material.field_151579_a
               && this.world.func_147439_a(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ()).func_149730_j()
               && this.explosionRNG.nextInt(3) == 0) {
               this.world.func_147449_b(blockpos.getX(), blockpos.getY(), blockpos.getZ(), Blocks.field_150480_ab);
            }
         }
      }
   }

   private net.minecraft.world.Explosion getCompatibleExplosion() {
      return new net.minecraft.world.Explosion(this.world, this.exploder, this.explosionX, this.explosionY, this.explosionZ, this.explosionSize);
   }

   public void clearAffectedBlockPositions() {
      this.affectedBlockPositions.clear();
   }

   public World getWorld() {
      return this.world;
   }

   public List<BlockPos> getAffectedBlockPositions() {
      return this.affectedBlockPositions;
   }

   public Map<EntityPlayer, Vec3> getPlayerKnockbackMap() {
      return this.playerKnockbackMap;
   }

   public Vec3 getPosition() {
      return this.position;
   }
}
