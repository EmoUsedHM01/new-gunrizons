package com.gtnewhorizon.newgunrizons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.registry.Ores;

public class BlockTitaniumOre extends Block {

    private static final String name = "titaniumore";

    public BlockTitaniumOre() {
        super(Material.rock);
        this.setBlockName("newgunrizons_TitaniumOre");
        this.setBlockTextureName("newgunrizons:titaniumore");
        this.setHardness(6.0F);
        this.setResistance(600000.0F);
        this.setStepSound(soundTypeStone);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }

    public Block getBlockDropped(int meta, Random rand, int fortune) {
        return Ores.TitaniumOre;
    }
}
