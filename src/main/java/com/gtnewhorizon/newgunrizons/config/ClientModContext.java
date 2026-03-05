package com.gtnewhorizon.newgunrizons.config;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizon.newgunrizons.client.animation.PlayerRawPitchAnimationManager;
import com.gtnewhorizon.newgunrizons.client.handlers.ClientTickHandler;
import com.gtnewhorizon.newgunrizons.client.handlers.WeaponHudHandler;
import com.gtnewhorizon.newgunrizons.client.handlers.WeaponInputHandler;
import com.gtnewhorizon.newgunrizons.client.handlers.WeaponRenderHandler;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.client.render.EntityBulletRenderer;
import com.gtnewhorizon.newgunrizons.client.render.EntityGrenadeRenderer;
import com.gtnewhorizon.newgunrizons.client.render.EntityShellRenderer;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.client.scope.ScopeManager;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.entities.EntityGrenade;
import com.gtnewhorizon.newgunrizons.entities.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.StatusMessageCenter;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import lombok.Getter;

public class ClientModContext extends CommonModContext {

    @Getter
    private StatusMessageCenter statusMessageCenter;
    @Getter
    private ScopeManager viewManager;
    @Getter
    private float aspectRatio;
    @Getter
    private Map<Object, Integer> inventoryTextureMap;
    @Getter
    private PlayerRawPitchAnimationManager playerRawPitchAnimationManager;

    public void init(Object mod, String modId, SimpleNetworkWrapper channel) {
        super.init(mod, modId, channel);

        this.aspectRatio = (float) Minecraft.getMinecraft().displayWidth
            / (float) Minecraft.getMinecraft().displayHeight;
        this.statusMessageCenter = new StatusMessageCenter();

        MinecraftForge.EVENT_BUS.register(new WeaponHudHandler(Minecraft.getMinecraft(), this));

        KeyBindings.init();

        WeaponInputHandler inputHandler = new WeaponInputHandler(this);
        FMLCommonHandler.instance()
            .bus()
            .register(inputHandler);
        MinecraftForge.EVENT_BUS.register(inputHandler);

        ClientTickHandler tickHandler = new ClientTickHandler(this);
        FMLCommonHandler.instance()
            .bus()
            .register(tickHandler);

        WeaponRenderHandler renderHandler = new WeaponRenderHandler(this);
        FMLCommonHandler.instance()
            .bus()
            .register(renderHandler);
        MinecraftForge.EVENT_BUS.register(renderHandler);

        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new EntityBulletRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityShellCasing.class, new EntityShellRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new EntityGrenadeRenderer());

        this.viewManager = new ScopeManager(this);
        this.inventoryTextureMap = new HashMap<>();
        this.playerRawPitchAnimationManager = new PlayerRawPitchAnimationManager();
    }

    public boolean isClient() {
        return true;
    }

    public void registerWeapon(String name, ItemWeapon weapon, WeaponRenderer renderer) {
        super.registerWeapon(name, weapon, renderer);
        MinecraftForgeClient.registerItemRenderer(weapon, weapon.getRenderer());
        renderer.setClientModContext(this);
    }

    public void registerRenderableItem(String name, Item item, Object renderer) {
        super.registerRenderableItem(name, item, renderer);
        MinecraftForgeClient.registerItemRenderer(item, (IItemRenderer) renderer);
    }

    protected EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public ItemInstanceRegistry getItemInstanceRegistry() {
        return this.itemInstanceRegistry;
    }

    public ItemWeaponInstance getMainHeldWeapon() {
        return this.getItemInstanceRegistry()
            .getMainHandItemInstance(Minecraft.getMinecraft().thePlayer, ItemWeaponInstance.class);
    }

    public void registerGrenadeWeapon(String name, ItemGrenade itemGrenade, GrenadeRenderer renderer) {
        super.registerGrenadeWeapon(name, itemGrenade, renderer);
        MinecraftForgeClient.registerItemRenderer(itemGrenade, itemGrenade.getRenderer());
        renderer.setClientModContext(this);
    }

    public String getModId() {
        return this.modId;
    }
}
