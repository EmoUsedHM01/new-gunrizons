package com.gtnewhorizon.newgunrizons.util;

import net.minecraft.block.Block;

import lombok.Getter;

@Getter
public class BlockState {

    private int blockMetadata;
    private final Block block;

    public BlockState(Block block, int blockMetadata) {
        this.block = block;
        this.blockMetadata = blockMetadata;
    }

    public static BlockState fromBlock(Block block) {
        return block != null ? new BlockState(block) : null;
    }

    private BlockState(Block block) {
        this.block = block;
    }

}
