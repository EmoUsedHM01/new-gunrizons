package com.gtnewhorizon.newgunrizons.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizon.newgunrizons.blocks.BlockBauxiteOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockCopperOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockLeadOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockRubyOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockSiliconOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockSulfurOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockTantalumOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockTinOre;
import com.gtnewhorizon.newgunrizons.blocks.BlockTitaniumOre;
import com.gtnewhorizon.newgunrizons.items.material.ItemAluminumIngot;
import com.gtnewhorizon.newgunrizons.items.material.ItemCopperIngot;
import com.gtnewhorizon.newgunrizons.items.material.ItemLeadIngot;
import com.gtnewhorizon.newgunrizons.items.material.ItemRuby;
import com.gtnewhorizon.newgunrizons.items.material.ItemSilicon;
import com.gtnewhorizon.newgunrizons.items.material.ItemSteelDust;
import com.gtnewhorizon.newgunrizons.items.material.ItemSteelIngot;
import com.gtnewhorizon.newgunrizons.items.material.ItemSulfurDust;
import com.gtnewhorizon.newgunrizons.items.material.ItemTantalumIngot;
import com.gtnewhorizon.newgunrizons.items.material.ItemTinIngot;
import com.gtnewhorizon.newgunrizons.items.material.ItemTitaniumIngot;

import cpw.mods.fml.common.registry.GameRegistry;

public class Ores {

    public static final String DUST_SULFUR = "dustSulfur";
    public static final String INGOT_TITANIUM = "ingotTitanium";
    public static final String INGOT_STEEL = "ingotSteel";
    public static final String INGOT_COPPER = "ingotCopper";
    public static final String INGOT_TANTALUM = "ingotTantalum";
    public static final String INGOT_ALUMINIUM = "ingotAluminium";
    public static final String INGOT_TIN = "ingotTin";
    public static final String INGOT_LEAD = "ingotLead";

    public static Block TitaniumOre;
    public static Item TitaniumIngot;
    public static Block LeadOre;
    public static Item LeadIngot;
    public static Block CopperOre;
    public static Item CopperIngot;
    public static Block TinOre;
    public static Item TinIngot;
    public static Block SulfurOre;
    public static Item SulfurDust;
    public static Block BauxiteOre;
    public static Item AluminumIngot;
    public static Block SiliconOre;
    public static Block TantalumOre;
    public static Item TantalumIngot;
    public static Block RubyOre;
    public static Item Ruby;
    public static Item SteelDust;
    public static Item SteelIngot;
    public static Item Silicon;

    public static void init() {
        TitaniumOre = new BlockTitaniumOre();
        TitaniumIngot = new ItemTitaniumIngot();
        LeadOre = new BlockLeadOre();
        LeadIngot = new ItemLeadIngot();
        CopperOre = new BlockCopperOre();
        CopperIngot = new ItemCopperIngot();
        TinOre = new BlockTinOre();
        TinIngot = new ItemTinIngot();
        SulfurOre = new BlockSulfurOre();
        SulfurDust = new ItemSulfurDust();
        BauxiteOre = new BlockBauxiteOre();
        AluminumIngot = new ItemAluminumIngot();
        RubyOre = new BlockRubyOre();
        Ruby = new ItemRuby();
        SiliconOre = new BlockSiliconOre();
        Silicon = new ItemSilicon();
        TantalumOre = new BlockTantalumOre();
        TantalumIngot = new ItemTantalumIngot();
        SteelDust = new ItemSteelDust();
        SteelIngot = new ItemSteelIngot();

        GameRegistry.registerBlock(TitaniumOre, "TitaniumOre");
        GameRegistry.registerItem(TitaniumIngot, "TitaniumIngot");
        GameRegistry.registerBlock(LeadOre, "LeadOre");
        GameRegistry.registerItem(LeadIngot, "LeadIngot");
        GameRegistry.registerBlock(CopperOre, "CopperOre");
        GameRegistry.registerItem(CopperIngot, "CopperIngot");
        GameRegistry.registerBlock(TinOre, "TinOre");
        GameRegistry.registerItem(TinIngot, "TinIngot");
        GameRegistry.registerBlock(SulfurOre, "SulfurOre");
        GameRegistry.registerItem(SulfurDust, "SulfurDust");
        GameRegistry.registerBlock(BauxiteOre, "BauxiteOre");
        GameRegistry.registerItem(AluminumIngot, "AluminumIngot");
        GameRegistry.registerBlock(SiliconOre, "SiliconOre");
        GameRegistry.registerItem(Silicon, "Silicon");
        GameRegistry.registerBlock(TantalumOre, "TantalumOre");
        GameRegistry.registerItem(TantalumIngot, "TantalumIngot");
        GameRegistry.registerBlock(RubyOre, "RubyOre");
        GameRegistry.registerItem(Ruby, "Ruby");
        GameRegistry.registerItem(SteelDust, "SteelDust");
        GameRegistry.registerItem(SteelIngot, "SteelIngot");

        OreDictionary.registerOre("ingotTitanium", TitaniumIngot);
        OreDictionary.registerOre("ingotTantalum", TantalumIngot);
        OreDictionary.registerOre("ingotLead", LeadIngot);
        OreDictionary.registerOre("ingotCopper", CopperIngot);
        OreDictionary.registerOre("ingotTin", TinIngot);
        OreDictionary.registerOre("dustSulfur", SulfurDust);
        OreDictionary.registerOre("ingotAluminium", AluminumIngot);
        OreDictionary.registerOre("Ruby", Ruby);
        OreDictionary.registerOre("ingotSteel", SteelIngot);
    }
}
