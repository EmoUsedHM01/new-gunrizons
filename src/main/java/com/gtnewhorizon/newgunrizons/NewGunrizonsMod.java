package com.gtnewhorizon.newgunrizons;

import net.minecraft.creativetab.CreativeTabs;

import com.gtnewhorizon.newgunrizons.tabs.AmmoTab;
import com.gtnewhorizon.newgunrizons.tabs.GunsTab;
import com.gtnewhorizon.newgunrizons.tabs.ShotgunsTab;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = NewGunrizonsMod.MODID, version = NewGunrizonsMod.VERSION)
public class NewGunrizonsMod {

    public static final String MODID = "newgunrizons";
    public static final String VERSION = "1.0.1";

    public static final SimpleNetworkWrapper CHANNEL;

    public static CreativeTabs gunsTab;
    public static CreativeTabs ShotgunsTab;
    public static CreativeTabs AmmoTab;

    @SidedProxy(
        serverSide = "com.gtnewhorizon.newgunrizons.CommonProxy",
        clientSide = "com.gtnewhorizon.newgunrizons.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.init(this, event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    static {
        CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        gunsTab = new GunsTab(CreativeTabs.getNextID(), "guns_tab");
        ShotgunsTab = new ShotgunsTab(CreativeTabs.getNextID(), "ShotgunsTab");
        AmmoTab = new AmmoTab(CreativeTabs.getNextID(), "AmmoTab");
    }
}
