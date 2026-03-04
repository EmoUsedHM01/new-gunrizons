package com.gtnewhorizon.newgunrizons.util;

import java.util.function.BiPredicate;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RayCast {

    public static MovingObjectPosition rayCastBlocks(World world, Vec3 start, Vec3 end,
                                                     BiPredicate<Block, Integer> isCollidable) {

        if (Double.isNaN(start.xCoord) || Double.isNaN(start.yCoord) || Double.isNaN(start.zCoord)) {
            return null;
        }
        if (Double.isNaN(end.xCoord) || Double.isNaN(end.yCoord) || Double.isNaN(end.zCoord)) {
            return null;
        }

        int endX = MathHelper.floor_double(end.xCoord);
        int endY = MathHelper.floor_double(end.yCoord);
        int endZ = MathHelper.floor_double(end.zCoord);
        int currentX = MathHelper.floor_double(start.xCoord);
        int currentY = MathHelper.floor_double(start.yCoord);
        int currentZ = MathHelper.floor_double(start.zCoord);

        Block startBlock = world.getBlock(currentX, currentY, currentZ);
        int startMeta = world.getBlockMetadata(currentX, currentY, currentZ);
        if (isCollidable.test(startBlock, startMeta)) {
            MovingObjectPosition hit = startBlock.collisionRayTrace(world, currentX, currentY, currentZ, start, end);
            if (hit != null) {
                return hit;
            }
        }

        for (int step = 200; step >= 0; --step) {
            if (Double.isNaN(start.xCoord) || Double.isNaN(start.yCoord) || Double.isNaN(start.zCoord)) {
                return null;
            }

            if (currentX == endX && currentY == endY && currentZ == endZ) {
                return null;
            }

            boolean crossesX = true;
            boolean crossesY = true;
            boolean crossesZ = true;
            double nextBoundaryX = 999.0D;
            double nextBoundaryY = 999.0D;
            double nextBoundaryZ = 999.0D;

            if (endX > currentX) {
                nextBoundaryX = currentX + 1.0D;
            } else if (endX < currentX) {
                nextBoundaryX = currentX;
            } else {
                crossesX = false;
            }

            if (endY > currentY) {
                nextBoundaryY = currentY + 1.0D;
            } else if (endY < currentY) {
                nextBoundaryY = currentY;
            } else {
                crossesY = false;
            }

            if (endZ > currentZ) {
                nextBoundaryZ = currentZ + 1.0D;
            } else if (endZ < currentZ) {
                nextBoundaryZ = currentZ;
            } else {
                crossesZ = false;
            }

            double deltaX = end.xCoord - start.xCoord;
            double deltaY = end.yCoord - start.yCoord;
            double deltaZ = end.zCoord - start.zCoord;
            double tX = 999.0D;
            double tY = 999.0D;
            double tZ = 999.0D;

            if (crossesX) {
                tX = (nextBoundaryX - start.xCoord) / deltaX;
            }
            if (crossesY) {
                tY = (nextBoundaryY - start.yCoord) / deltaY;
            }
            if (crossesZ) {
                tZ = (nextBoundaryZ - start.zCoord) / deltaZ;
            }

            int hitSide;
            if (tX < tY && tX < tZ) {
                hitSide = endX > currentX ? 4 : 5;
                start.xCoord = nextBoundaryX;
                start.yCoord += deltaY * tX;
                start.zCoord += deltaZ * tX;
            } else if (tY < tZ) {
                hitSide = endY > currentY ? 0 : 1;
                start.xCoord += deltaX * tY;
                start.yCoord = nextBoundaryY;
                start.zCoord += deltaZ * tY;
            } else {
                hitSide = endZ > currentZ ? 2 : 3;
                start.xCoord += deltaX * tZ;
                start.yCoord += deltaY * tZ;
                start.zCoord = nextBoundaryZ;
            }

            currentX = MathHelper.floor_double(start.xCoord);
            if (hitSide == 5) --currentX;

            currentY = MathHelper.floor_double(start.yCoord);
            if (hitSide == 1) --currentY;

            currentZ = MathHelper.floor_double(start.zCoord);
            if (hitSide == 3) --currentZ;

            Block currentBlock = world.getBlock(currentX, currentY, currentZ);
            int currentMeta = world.getBlockMetadata(currentX, currentY, currentZ);
            if (isCollidable.test(currentBlock, currentMeta)) {
                MovingObjectPosition hit = currentBlock
                    .collisionRayTrace(world, currentX, currentY, currentZ, start, end);
                if (hit != null) {
                    return hit;
                }
            }
        }

        return null;
    }
}
