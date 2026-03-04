package com.gtnewhorizon.newgunrizons.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.registry.Ores;

public class BlockSulfurOre extends Block {

    private static final String name = "titaniumore";

    public BlockSulfurOre() {
        super(Material.rock);
        this.setBlockName("newgunrizons_SulfurOre");
        this.setBlockTextureName("newgunrizons:sulfurore");
        this.setHardness(4.0F);
        this.setResistance(600000.0F);
        this.setStepSound(soundTypeStone);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(NewGunrizonsMod.gunsTab);
    }

    public Item getItemDropped(int meta, Random rand, int fortune) {
        return Ores.SulfurDust;
    }
}
