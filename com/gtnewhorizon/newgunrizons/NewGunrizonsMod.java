package com.gtnewhorizon.newgunrizons;

import com.gtnewhorizon.newgunrizons.registry.GTRecipes;
import com.gtnewhorizon.newgunrizons.tabs.AmmoTab;
import com.gtnewhorizon.newgunrizons.tabs.AssaultRiflesTab;
import com.gtnewhorizon.newgunrizons.tabs.AttachmentsTab;
import com.gtnewhorizon.newgunrizons.tabs.GunsTab;
import com.gtnewhorizon.newgunrizons.tabs.PistolsTab;
import com.gtnewhorizon.newgunrizons.tabs.SMGTab;
import com.gtnewhorizon.newgunrizons.tabs.ShotgunsTab;
import com.gtnewhorizon.newgunrizons.tabs.SnipersTab;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = "newgunrizons", version = "1.0.0")
public class NewGunrizonsMod {
   public static final String MODID = "newgunrizons";
   public static final String VERSION = "1.0.0";
   public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("newgunrizons");
   public static CreativeTabs gunsTab = new GunsTab(CreativeTabs.getNextID(), "guns_tab");
   public static CreativeTabs AssaultRiflesTab = new AssaultRiflesTab(CreativeTabs.getNextID(), "AssaultRifles_tab");
   public static CreativeTabs PistolsTab = new PistolsTab(CreativeTabs.getNextID(), "Pistols_tab");
   public static CreativeTabs SMGTab = new SMGTab(CreativeTabs.getNextID(), "SMG_tab");
   public static CreativeTabs ShotgunsTab = new ShotgunsTab(CreativeTabs.getNextID(), "ShotgunsTab");
   public static CreativeTabs SnipersTab = new SnipersTab(CreativeTabs.getNextID(), "SnipersTab");
   public static CreativeTabs AmmoTab = new AmmoTab(CreativeTabs.getNextID(), "AmmoTab");
   public static CreativeTabs AttachmentsTab = new AttachmentsTab(CreativeTabs.getNextID(), "AttachmentsTab");
   @SidedProxy(serverSide = "com.gtnewhorizon.newgunrizons.CommonProxy", clientSide = "com.gtnewhorizon.newgunrizons.ClientProxy")
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
      GTRecipes.init();
   }
}
