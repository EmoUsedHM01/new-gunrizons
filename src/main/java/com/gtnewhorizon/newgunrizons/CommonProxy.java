package com.gtnewhorizon.newgunrizons;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.items.materials.ItemAluminumPlate;
import com.gtnewhorizon.newgunrizons.items.materials.ItemBigSteelPlate;
import com.gtnewhorizon.newgunrizons.items.materials.ItemCapacitor;
import com.gtnewhorizon.newgunrizons.items.materials.ItemCopperWiring;
import com.gtnewhorizon.newgunrizons.items.materials.ItemDiode;
import com.gtnewhorizon.newgunrizons.items.materials.ItemElectronics;
import com.gtnewhorizon.newgunrizons.items.materials.ItemInductor;
import com.gtnewhorizon.newgunrizons.items.materials.ItemLaserPointer;
import com.gtnewhorizon.newgunrizons.items.materials.ItemMetalComponents;
import com.gtnewhorizon.newgunrizons.items.materials.ItemMiniSteelPlate;
import com.gtnewhorizon.newgunrizons.items.materials.ItemOpticGlass;
import com.gtnewhorizon.newgunrizons.items.materials.ItemPlastic;
import com.gtnewhorizon.newgunrizons.items.materials.ItemResistor;
import com.gtnewhorizon.newgunrizons.items.materials.ItemSteelPlate;
import com.gtnewhorizon.newgunrizons.items.materials.ItemTransistor;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.registry.Grenades;
import com.gtnewhorizon.newgunrizons.registry.Guns;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.registry.Ores;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public static Item ElectronicCircuitBoard;
    public static Item OpticGlass;
    public static Item Inductor;
    public static Item Transistor;
    public static Item Resistor;
    public static Item Diode;
    public static Item Capacitor;
    public static Item CopperWiring;
    public static Item LaserPointer;
    public static Item AluminumPlate;
    public static Item SteelPlate;
    public static Item BigSteelPlate;
    public static Item MiniSteelPlate;
    public static Item MetalComponents;
    public static Item Plastic;

    protected boolean isClient() {
        return false;
    }

    public void init(Object mod, FMLPreInitializationEvent event) {

        NewGunrizonsMod.MOD_CONTEXT.init(mod, "newgunrizons", NewGunrizonsMod.CHANNEL);
        NewGunrizonsMod.MOD_CONTEXT.setChangeZoomSound("OpticZoom");
        NewGunrizonsMod.MOD_CONTEXT.setChangeFireModeSound("GunFireModeSwitch");
        NewGunrizonsMod.MOD_CONTEXT.setNoAmmoSound("dryfire");
        NewGunrizonsMod.MOD_CONTEXT.setExplosionSound("grenadeexplosion");

        ElectronicCircuitBoard = new ItemElectronics();
        OpticGlass = new ItemOpticGlass();
        Inductor = new ItemInductor();
        Resistor = new ItemResistor();
        Transistor = new ItemTransistor();
        Diode = new ItemDiode();
        Capacitor = new ItemCapacitor();
        CopperWiring = new ItemCopperWiring();
        LaserPointer = new ItemLaserPointer();
        Plastic = new ItemPlastic();
        AluminumPlate = new ItemAluminumPlate();
        SteelPlate = new ItemSteelPlate();
        BigSteelPlate = new ItemBigSteelPlate();
        MiniSteelPlate = new ItemMiniSteelPlate();
        MetalComponents = new ItemMetalComponents();

        GameRegistry.registerItem(ElectronicCircuitBoard, "Electronics");
        GameRegistry.registerItem(OpticGlass, "OpticGlass");
        GameRegistry.registerItem(AluminumPlate, "AluminumPlate");
        GameRegistry.registerItem(SteelPlate, "SteelPlate");
        GameRegistry.registerItem(BigSteelPlate, "BigSteelPlate");
        GameRegistry.registerItem(MiniSteelPlate, "MiniSteelPlate");
        GameRegistry.registerItem(MetalComponents, "MetalComponents");
        GameRegistry.registerItem(Transistor, "Transistor");
        GameRegistry.registerItem(Resistor, "Resistor");
        GameRegistry.registerItem(Inductor, "Inductor");
        GameRegistry.registerItem(Diode, "Diode");
        GameRegistry.registerItem(Capacitor, "Capacitor");
        GameRegistry.registerItem(CopperWiring, "CopperWiring");
        GameRegistry.registerItem(LaserPointer, "LaserPointer");
        GameRegistry.registerItem(Plastic, "plastic");

        Ores.init();
        Attachments.init();
        AuxiliaryAttachments.init();
        Bullets.init();
        Magazines.init();
        Guns.init(this);
        Grenades.init(this);
    }
}
