package com.gtnewhorizon.newgunrizons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.registry.Ores;

public class BlockCopperOre extends Block {

    private static final String name = "titaniumore";

    public BlockCopperOre() {
        super(Material.rock);
        this.setBlockName("newgunrizons_CopperOre");
        this.setBlockTextureName("newgunrizons:copperore");
        this.setHardness(6.0F);
        this.setResistance(600000.0F);
        this.setStepSound(soundTypeStone);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }

    public Block getBlockDropped(int meta, Random rand, int fortune) {
        return Ores.CopperOre;
    }
}
