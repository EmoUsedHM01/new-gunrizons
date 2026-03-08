package com.gtnewhorizon.newgunrizons;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizon.newgunrizons.client.handlers.ClientTickHandler;
import com.gtnewhorizon.newgunrizons.client.handlers.WeaponHudHandler;
import com.gtnewhorizon.newgunrizons.client.handlers.WeaponInputHandler;
import com.gtnewhorizon.newgunrizons.client.handlers.WeaponRenderHandler;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.client.render.EntityBulletRenderer;
import com.gtnewhorizon.newgunrizons.client.render.EntityGrenadeRenderer;
import com.gtnewhorizon.newgunrizons.client.render.EntityShellRenderer;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.entities.EntityGrenade;
import com.gtnewhorizon.newgunrizons.entities.EntityShellCasing;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(Object mod, FMLPreInitializationEvent event) {
        super.init(mod, event);

        MinecraftForge.EVENT_BUS.register(new WeaponHudHandler());

        KeyBindings.init();

        WeaponInputHandler inputHandler = new WeaponInputHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(inputHandler);
        MinecraftForge.EVENT_BUS.register(inputHandler);

        ClientTickHandler tickHandler = new ClientTickHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(tickHandler);

        WeaponRenderHandler renderHandler = new WeaponRenderHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(renderHandler);
        MinecraftForge.EVENT_BUS.register(renderHandler);

        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new EntityBulletRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityShellCasing.class, new EntityShellRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new EntityGrenadeRenderer());
    }

    @Override
    public void registerItem(String name, Item item, IItemRenderer renderer) {
        super.registerItem(name, item, renderer);
        if (renderer != null) {
            MinecraftForgeClient.registerItemRenderer(item, renderer);
        }
        if (item.getCreativeTab() == null) {
            codechicken.nei.api.API.hideItem(new ItemStack(item));
        }
    }
}
