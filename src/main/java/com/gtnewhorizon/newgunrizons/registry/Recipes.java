package com.gtnewhorizon.newgunrizons.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.config.ModContext;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init(ModContext modContext) {
        GameRegistry.addSmelting(Ores.TitaniumOre, new ItemStack(Ores.TitaniumIngot), 5.0F);
        GameRegistry.addSmelting(Ores.CopperOre, new ItemStack(Ores.CopperIngot), 5.0F);
        GameRegistry.addSmelting(Ores.LeadOre, new ItemStack(Ores.LeadIngot), 5.0F);
        GameRegistry.addSmelting(Ores.TinOre, new ItemStack(Ores.TinIngot), 5.0F);
        GameRegistry.addSmelting(Ores.BauxiteOre, new ItemStack(Ores.AluminumIngot), 5.0F);
        GameRegistry.addSmelting(Ores.SiliconOre, new ItemStack(Ores.Silicon), 5.0F);
        GameRegistry.addSmelting(Ores.TantalumOre, new ItemStack(Ores.TantalumIngot), 5.0F);
        GameRegistry.addSmelting(Ores.SulfurDust, new ItemStack(Items.gunpowder, 1, 0), 5.0F);
        GameRegistry.addSmelting(Ores.SteelDust, new ItemStack(Ores.SteelIngot), 5.0F);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Cloth, 3),
                "XAX",
                "AXA",
                "XAX",
                'X',
                Items.string,
                'A',
                Blocks.wool);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.TanCloth, 3),
                "XAX",
                "AXA",
                "XAX",
                'X',
                Blocks.sandstone,
                'A',
                CommonProxy.Cloth);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.GreenCloth, 3),
                "XAX",
                "AXA",
                "XAX",
                'X',
                Blocks.leaves,
                'A',
                CommonProxy.Cloth);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.ElectronicCircuitBoard),
                "TAI",
                "ACA",
                "RAD",
                'I',
                CommonProxy.Inductor,
                'T',
                CommonProxy.Transistor,
                'R',
                CommonProxy.Resistor,
                'D',
                CommonProxy.Diode,
                'C',
                CommonProxy.Capacitor,
                'A',
                CommonProxy.CopperWiring);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.OpticGlass),
                "XAX",
                "AEA",
                "XAX",
                'X',
                "ingotTitanium",
                'E',
                CommonProxy.CopperWiring,
                'A',
                Blocks.glass_pane);
        modContext.getRecipeManager()
            .registerShapedRecipe(new ItemStack(CommonProxy.MiniSteelPlate, 2), "XX", 'X', "ingotSteel");
        modContext.getRecipeManager()
            .registerShapedRecipe(new ItemStack(CommonProxy.SteelPlate, 1), "XXX", "XXX", 'X', "ingotSteel");
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.BigSteelPlate),
                "XAX",
                'X',
                CommonProxy.SteelPlate,
                'A',
                CommonProxy.MiniSteelPlate);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.MetalComponents),
                "XA",
                "AX",
                'X',
                CommonProxy.SteelPlate,
                'A',
                "ingotSteel");
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.LaserPointer),
                "XXX",
                "AAR",
                "XXX",
                'X',
                CommonProxy.MiniSteelPlate,
                'A',
                Ores.Ruby,
                'R',
                CommonProxy.Capacitor);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Resistor, 3),
                "A",
                "X",
                "A",
                'A',
                "ingotSteel",
                'X',
                Items.coal);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Transistor, 3),
                "AA",
                "XX",
                "XX",
                'A',
                "ingotSteel",
                'X',
                Ores.Silicon);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Capacitor, 3),
                "XX",
                "AA",
                "RR",
                'X',
                "ingotAluminium",
                'A',
                CommonProxy.Plastic,
                'R',
                "ingotTantalum");
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Inductor, 3),
                " A ",
                "AXA",
                " A ",
                'X',
                CommonProxy.Plastic,
                'A',
                CommonProxy.CopperWiring);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Diode, 3),
                "X",
                "A",
                "X",
                'A',
                "ingotSteel",
                'X',
                Ores.Silicon);
        modContext.getRecipeManager()
            .registerShapedRecipe(new ItemStack(CommonProxy.CopperWiring, 10), "A  ", "A A", "AAA", 'A', "ingotCopper");
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Plastic, 3),
                "AX",
                'A',
                Items.coal,
                'X',
                Items.water_bucket);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(CommonProxy.Piston),
                "XA ",
                "AXX",
                " XE",
                'A',
                CommonProxy.SteelPlate,
                'X',
                "ingotSteel",
                'E',
                CommonProxy.ElectronicCircuitBoard);
        modContext.getRecipeManager()
            .registerShapedRecipe(
                new ItemStack(Ores.SteelDust, 1),
                " X ",
                "XAX",
                " X ",
                'A',
                Items.iron_ingot,
                'X',
                Items.coal);
        modContext.getRecipeManager()
            .registerShapedRecipe(new ItemStack(CommonProxy.AluminumPlate, 6), "   ", "XXX", 'X', "ingotAluminium");
    }
}
