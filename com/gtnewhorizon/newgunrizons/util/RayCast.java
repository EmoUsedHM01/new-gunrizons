package com.gtnewhorizon.newgunrizons.util;

import java.util.function.BiPredicate;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RayCast {
   public static MovingObjectPosition rayCastBlocks(World world, Vec3 start, Vec3 end, BiPredicate<Block, Integer> canCollide) {
      if (Double.isNaN(start.field_72450_a) || Double.isNaN(start.field_72448_b) || Double.isNaN(start.field_72449_c)) {
         return null;
      } else if (!Double.isNaN(end.field_72450_a) && !Double.isNaN(end.field_72448_b) && !Double.isNaN(end.field_72449_c)) {
         int endX = MathHelper.func_76128_c(end.field_72450_a);
         int endY = MathHelper.func_76128_c(end.field_72448_b);
         int endZ = MathHelper.func_76128_c(end.field_72449_c);
         int currentX = MathHelper.func_76128_c(start.field_72450_a);
         int currentY = MathHelper.func_76128_c(start.field_72448_b);
         int currentZ = MathHelper.func_76128_c(start.field_72449_c);
         Block startBlock = world.func_147439_a(currentX, currentY, currentZ);
         int startMeta = world.func_72805_g(currentX, currentY, currentZ);
         if (canCollide.test(startBlock, startMeta)) {
            MovingObjectPosition hit = startBlock.func_149731_a(world, currentX, currentY, currentZ, start, end);
            if (hit != null) {
               return hit;
            }
         }

         for (int step = 200; step >= 0; step--) {
            if (Double.isNaN(start.field_72450_a) || Double.isNaN(start.field_72448_b) || Double.isNaN(start.field_72449_c)) {
               return null;
            }

            if (currentX == endX && currentY == endY && currentZ == endZ) {
               return null;
            }

            boolean crossesX = true;
            boolean crossesY = true;
            boolean crossesZ = true;
            double nextBoundaryX = 999.0;
            double nextBoundaryY = 999.0;
            double nextBoundaryZ = 999.0;
            if (endX > currentX) {
               nextBoundaryX = currentX + 1.0;
            } else if (endX < currentX) {
               nextBoundaryX = currentX;
            } else {
               crossesX = false;
            }

            if (endY > currentY) {
               nextBoundaryY = currentY + 1.0;
            } else if (endY < currentY) {
               nextBoundaryY = currentY;
            } else {
               crossesY = false;
            }

            if (endZ > currentZ) {
               nextBoundaryZ = currentZ + 1.0;
            } else if (endZ < currentZ) {
               nextBoundaryZ = currentZ;
            } else {
               crossesZ = false;
            }

            double deltaX = end.field_72450_a - start.field_72450_a;
            double deltaY = end.field_72448_b - start.field_72448_b;
            double deltaZ = end.field_72449_c - start.field_72449_c;
            double tX = 999.0;
            double tY = 999.0;
            double tZ = 999.0;
            if (crossesX) {
               tX = (nextBoundaryX - start.field_72450_a) / deltaX;
            }

            if (crossesY) {
               tY = (nextBoundaryY - start.field_72448_b) / deltaY;
            }

            if (crossesZ) {
               tZ = (nextBoundaryZ - start.field_72449_c) / deltaZ;
            }

            int hitSide;
            if (tX < tY && tX < tZ) {
               hitSide = endX > currentX ? 4 : 5;
               start.field_72450_a = nextBoundaryX;
               start.field_72448_b += deltaY * tX;
               start.field_72449_c += deltaZ * tX;
            } else if (tY < tZ) {
               hitSide = endY > currentY ? 0 : 1;
               start.field_72450_a += deltaX * tY;
               start.field_72448_b = nextBoundaryY;
               start.field_72449_c += deltaZ * tY;
            } else {
               hitSide = endZ > currentZ ? 2 : 3;
               start.field_72450_a += deltaX * tZ;
               start.field_72448_b += deltaY * tZ;
               start.field_72449_c = nextBoundaryZ;
            }

            currentX = MathHelper.func_76128_c(start.field_72450_a);
            if (hitSide == 5) {
               currentX--;
            }

            currentY = MathHelper.func_76128_c(start.field_72448_b);
            if (hitSide == 1) {
               currentY--;
            }

            currentZ = MathHelper.func_76128_c(start.field_72449_c);
            if (hitSide == 3) {
               currentZ--;
            }

            Block currentBlock = world.func_147439_a(currentX, currentY, currentZ);
            int currentMeta = world.func_72805_g(currentX, currentY, currentZ);
            if (canCollide.test(currentBlock, currentMeta)) {
               MovingObjectPosition hit = currentBlock.func_149731_a(world, currentX, currentY, currentZ, start, end);
               if (hit != null) {
                  return hit;
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }
}
