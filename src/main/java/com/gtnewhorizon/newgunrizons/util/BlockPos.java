package com.gtnewhorizon.newgunrizons.util;

import net.minecraft.util.Vec3;

import lombok.Getter;

@Getter
public class BlockPos {

    private final int blockPosX;
    private final int blockPosY;
    private final int blockPosZ;

    public BlockPos(int blockPosX, int blockPosY, int blockPosZ) {
        this.blockPosX = blockPosX;
        this.blockPosY = blockPosY;
        this.blockPosZ = blockPosZ;
    }

    public BlockPos(Vec3 pos) {
        this.blockPosX = (int) pos.xCoord;
        this.blockPosY = (int) pos.yCoord;
        this.blockPosZ = (int) pos.zCoord;
    }

    public int hashCode() {
        int result = 31 + this.blockPosX;
        result = 31 * result + this.blockPosY;
        result = 31 * result + this.blockPosZ;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            BlockPos other = (BlockPos) obj;
            if (this.blockPosX != other.blockPosX) {
                return false;
            } else if (this.blockPosY != other.blockPosY) {
                return false;
            } else {
                return this.blockPosZ == other.blockPosZ;
            }
        }
    }
}
