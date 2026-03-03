package com.gtnewhorizon.newgunrizons;

import net.minecraft.creativetab.CreativeTabs;

import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.registry.Recipes;
import com.gtnewhorizon.newgunrizons.tab.AmmoTab;
import com.gtnewhorizon.newgunrizons.tab.AssaultRiflesTab;
import com.gtnewhorizon.newgunrizons.tab.AttachmentsTab;
import com.gtnewhorizon.newgunrizons.tab.FunGunsTab;
import com.gtnewhorizon.newgunrizons.tab.GrenadesTab;
import com.gtnewhorizon.newgunrizons.tab.GunsTab;
import com.gtnewhorizon.newgunrizons.tab.PistolsTab;
import com.gtnewhorizon.newgunrizons.tab.SMGTab;
import com.gtnewhorizon.newgunrizons.tab.ShotgunsTab;
import com.gtnewhorizon.newgunrizons.tab.SnipersTab;

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
    public static final String VERSION = "1.0.0";

    @SidedProxy(
        serverSide = "com.gtnewhorizon.newgunrizons.config.CommonModContext",
        clientSide = "com.gtnewhorizon.newgunrizons.config.ClientModContext")
    public static ModContext MOD_CONTEXT;

    public static final SimpleNetworkWrapper CHANNEL;

    public static CreativeTabs gunsTab;
    public static CreativeTabs AssaultRiflesTab;
    public static CreativeTabs PistolsTab;
    public static CreativeTabs SMGTab;
    public static CreativeTabs ShotgunsTab;
    public static CreativeTabs SnipersTab;
    public static CreativeTabs AmmoTab;
    public static CreativeTabs AttachmentsTab;
    public static CreativeTabs GrenadesTab;
    public static CreativeTabs FunGunsTab;

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
        Recipes.init(MOD_CONTEXT);
    }

    static {
        CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        gunsTab = new GunsTab(CreativeTabs.getNextID(), "guns_tab");
        AssaultRiflesTab = new AssaultRiflesTab(CreativeTabs.getNextID(), "AssaultRifles_tab");
        PistolsTab = new PistolsTab(CreativeTabs.getNextID(), "Pistols_tab");
        SMGTab = new SMGTab(CreativeTabs.getNextID(), "SMG_tab");
        ShotgunsTab = new ShotgunsTab(CreativeTabs.getNextID(), "ShotgunsTab");
        SnipersTab = new SnipersTab(CreativeTabs.getNextID(), "SnipersTab");
        AmmoTab = new AmmoTab(CreativeTabs.getNextID(), "AmmoTab");
        AttachmentsTab = new AttachmentsTab(CreativeTabs.getNextID(), "AttachmentsTab");
        GrenadesTab = new GrenadesTab(CreativeTabs.getNextID(), "GrenadesTab");
        FunGunsTab = new FunGunsTab(CreativeTabs.getNextID(), "FunGuns_tab");
    }
}
