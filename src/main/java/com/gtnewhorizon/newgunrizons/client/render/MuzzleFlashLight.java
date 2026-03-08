package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockpos.IBlockPos;
import com.gtnewhorizons.angelica.dynamiclights.DynamicLights;
import com.gtnewhorizons.angelica.dynamiclights.IDynamicLightSource;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer;
import org.jetbrains.annotations.NotNull;

/**
 * A standalone dynamic light source for muzzle flash illumination.
 * Registers directly with Angelica's DynamicLights system.
 */
public class MuzzleFlashLight implements IDynamicLightSource {

    private static final long FLASH_LIGHT_DURATION_MS = 60L;
    private static final int FLASH_LUMINANCE = 14;

    private static final MuzzleFlashLight INSTANCE = new MuzzleFlashLight();

    private double posX, posY, posZ;
    private long flashTimestamp;
    private int luminance;
    private int lastLuminance;
    private double prevX, prevY, prevZ;
    private final LongOpenHashSet trackedChunks = new LongOpenHashSet();
    private final LongOpenHashSet prevTrackedChunks = new LongOpenHashSet();

    public static void flash(double x, double y, double z) {
        if (!DynamicLights.isEnabled()) return;
        INSTANCE.posX = x;
        INSTANCE.posY = y;
        INSTANCE.posZ = z;
        INSTANCE.flashTimestamp = System.currentTimeMillis();
        INSTANCE.luminance = FLASH_LUMINANCE;
        DynamicLights.updateTracking(INSTANCE);
    }

    public static void tick() {
        if (INSTANCE.luminance > 0) {
            long elapsed = System.currentTimeMillis() - INSTANCE.flashTimestamp;
            if (elapsed > FLASH_LIGHT_DURATION_MS) {
                INSTANCE.luminance = 0;
            }
            DynamicLights.updateTracking(INSTANCE);
        }
    }

    @Override
    public double angelica$getDynamicLightX() {
        return posX;
    }

    @Override
    public double angelica$getDynamicLightY() {
        return posY;
    }

    @Override
    public double angelica$getDynamicLightZ() {
        return posZ;
    }

    @Override
    public void angelica$resetDynamicLight() {
        this.lastLuminance = 0;
    }

    @Override
    public int angelica$getLuminance() {
        return this.luminance;
    }

    @Override
    public void angelica$dynamicLightTick() {}

    @Override
    public SodiumWorldRenderer angelica$getDynamicLightWorld() {
        return SodiumWorldRenderer.getInstance();
    }

    @Override
    public boolean angelica$updateDynamicLight(@NotNull SodiumWorldRenderer renderer) {
        double dx = this.posX - this.prevX;
        double dy = this.posY - this.prevY;
        double dz = this.posZ - this.prevZ;

        int lum = this.luminance;

        if (Math.abs(dx) > 0.1 || Math.abs(dy) > 0.1 || Math.abs(dz) > 0.1 || lum != this.lastLuminance) {
            this.prevX = this.posX;
            this.prevY = this.posY;
            this.prevZ = this.posZ;
            this.lastLuminance = lum;

            LongOpenHashSet newPos = this.prevTrackedChunks;
            newPos.clear();

            if (lum > 0) {
                int chunkX = MathHelper.floor_double(posX) >> 4;
                int chunkY = MathHelper.floor_double(posY) >> 4;
                int chunkZ = MathHelper.floor_double(posZ) >> 4;
                IBlockPos chunkPos = new BlockPos(chunkX, chunkY, chunkZ);

                DynamicLights.scheduleChunkRebuild(renderer, chunkPos);
                DynamicLights.updateTrackedChunks(chunkPos, this.trackedChunks, newPos);

                ForgeDirection dirX = ((MathHelper.floor_double(posX) & 15) >= 8) ? ForgeDirection.EAST
                    : ForgeDirection.WEST;
                ForgeDirection dirY = ((MathHelper.floor_double(posY) & 15) >= 8) ? ForgeDirection.UP
                    : ForgeDirection.DOWN;
                ForgeDirection dirZ = ((MathHelper.floor_double(posZ) & 15) >= 8) ? ForgeDirection.SOUTH
                    : ForgeDirection.NORTH;

                for (int i = 0; i < 7; i++) {
                    if (i % 4 == 0) {
                        chunkPos = chunkPos.offset(dirX);
                    } else if (i % 4 == 1) {
                        chunkPos = chunkPos.offset(dirZ);
                    } else if (i % 4 == 2) {
                        chunkPos = chunkPos.offset(dirX.getOpposite());
                    } else {
                        chunkPos = chunkPos.offset(dirZ.getOpposite());
                        chunkPos = chunkPos.offset(dirY);
                    }
                    DynamicLights.scheduleChunkRebuild(renderer, chunkPos);
                    DynamicLights.updateTrackedChunks(chunkPos, this.trackedChunks, newPos);
                }
            }

            this.angelica$scheduleTrackedChunksRebuild(renderer);
            this.prevTrackedChunks.addAll(this.trackedChunks);
            this.trackedChunks.clear();
            this.trackedChunks.addAll(newPos);
            this.prevTrackedChunks.clear();
            return true;
        }
        return false;
    }

    @Override
    public void angelica$scheduleTrackedChunksRebuild(@NotNull SodiumWorldRenderer renderer) {
        if (!this.trackedChunks.isEmpty()) {
            it.unimi.dsi.fastutil.longs.LongIterator iter = this.trackedChunks.iterator();
            while (iter.hasNext()) {
                DynamicLights.scheduleChunkRebuild(renderer, iter.nextLong());
            }
        }
    }
}
