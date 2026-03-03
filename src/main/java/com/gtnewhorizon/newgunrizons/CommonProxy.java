package com.gtnewhorizon.newgunrizons;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.material.ItemAluminumPlate;
import com.gtnewhorizon.newgunrizons.material.ItemBigSteelPlate;
import com.gtnewhorizon.newgunrizons.material.ItemCapacitor;
import com.gtnewhorizon.newgunrizons.material.ItemCloth;
import com.gtnewhorizon.newgunrizons.material.ItemCopperWiring;
import com.gtnewhorizon.newgunrizons.material.ItemDiode;
import com.gtnewhorizon.newgunrizons.material.ItemElectronics;
import com.gtnewhorizon.newgunrizons.material.ItemGreenCloth;
import com.gtnewhorizon.newgunrizons.material.ItemInductor;
import com.gtnewhorizon.newgunrizons.material.ItemLaserPointer;
import com.gtnewhorizon.newgunrizons.material.ItemMetalComponents;
import com.gtnewhorizon.newgunrizons.material.ItemMiniSteelPlate;
import com.gtnewhorizon.newgunrizons.material.ItemOpticGlass;
import com.gtnewhorizon.newgunrizons.material.ItemPiston;
import com.gtnewhorizon.newgunrizons.material.ItemPlastic;
import com.gtnewhorizon.newgunrizons.material.ItemResistor;
import com.gtnewhorizon.newgunrizons.material.ItemSteelPlate;
import com.gtnewhorizon.newgunrizons.material.ItemTanCloth;
import com.gtnewhorizon.newgunrizons.material.ItemTransistor;
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
    public static Item Cloth;
    public static Item TanCloth;
    public static Item GreenCloth;
    public static Item Inductor;
    public static Item Transistor;
    public static Item Resistor;
    public static Item Diode;
    public static Item Capacitor;
    public static Item CopperWiring;
    public static Item Piston;
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
        Cloth = new ItemCloth();
        TanCloth = new ItemTanCloth();
        GreenCloth = new ItemGreenCloth();
        Inductor = new ItemInductor();
        Resistor = new ItemResistor();
        Transistor = new ItemTransistor();
        Diode = new ItemDiode();
        Capacitor = new ItemCapacitor();
        CopperWiring = new ItemCopperWiring();
        Piston = new ItemPiston();
        LaserPointer = new ItemLaserPointer();
        Plastic = new ItemPlastic();
        AluminumPlate = new ItemAluminumPlate();
        SteelPlate = new ItemSteelPlate();
        BigSteelPlate = new ItemBigSteelPlate();
        MiniSteelPlate = new ItemMiniSteelPlate();
        MetalComponents = new ItemMetalComponents();

        GameRegistry.registerItem(ElectronicCircuitBoard, "Electronics");
        GameRegistry.registerItem(OpticGlass, "OpticGlass");
        GameRegistry.registerItem(Cloth, "Cloth");
        GameRegistry.registerItem(TanCloth, "TanCloth");
        GameRegistry.registerItem(GreenCloth, "GreenCloth");
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
        GameRegistry.registerItem(Piston, "Piston");
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
