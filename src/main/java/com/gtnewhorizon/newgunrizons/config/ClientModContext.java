package com.gtnewhorizon.newgunrizons.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizon.newgunrizons.client.animation.PlayerRawPitchAnimationManager;
import com.gtnewhorizon.newgunrizons.client.effect.EffectManager;
import com.gtnewhorizon.newgunrizons.client.handler.ClientEventHandler;
import com.gtnewhorizon.newgunrizons.client.handler.ClientWeaponTicker;
import com.gtnewhorizon.newgunrizons.client.handler.WeaponEventHandler;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.client.render.CustomGui;
import com.gtnewhorizon.newgunrizons.client.render.ShellCasingRenderer;
import com.gtnewhorizon.newgunrizons.client.render.SpawnEntityRenderer;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.client.resource.WeaponResourcePack;
import com.gtnewhorizon.newgunrizons.client.scope.ScopeManager;
import com.gtnewhorizon.newgunrizons.entity.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.entity.WeaponSpawnEntity;
import com.gtnewhorizon.newgunrizons.grenade.EntityGrenade;
import com.gtnewhorizon.newgunrizons.grenade.EntityGrenadeRenderer;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.grenade.ItemGrenade;
import com.gtnewhorizon.newgunrizons.network.StatusMessageCenter;
import com.gtnewhorizon.newgunrizons.network.SyncManager;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import lombok.Getter;

public class ClientModContext extends CommonModContext {

    private ClientEventHandler clientEventHandler;
    private final Lock mainLoopLock = new ReentrantLock();
    private final Queue<Runnable> runInClientThreadQueue = new LinkedBlockingQueue<>();
    @Getter
    private final SafeGlobals safeGlobals = new SafeGlobals();
    @Getter
    private StatusMessageCenter statusMessageCenter;
    @Getter
    private ScopeManager viewManager;
    @Getter
    private float aspectRatio;
    @Getter
    private Map<Object, Integer> inventoryTextureMap;
    @Getter
    private EffectManager effectManager;
    @Getter
    private PlayerRawPitchAnimationManager playerRawPitchAnimationManager;

    public void init(Object mod, String modId, SimpleNetworkWrapper channel) {
        super.init(mod, modId, channel);

        this.aspectRatio = (float) Minecraft.getMinecraft().displayWidth
            / (float) Minecraft.getMinecraft().displayHeight;
        this.statusMessageCenter = new StatusMessageCenter();

        List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper
            .getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "defaultResourcePacks");
        defaultResourcePacks.add(new WeaponResourcePack());

        MinecraftForge.EVENT_BUS.register(new CustomGui(Minecraft.getMinecraft(), this));
        MinecraftForge.EVENT_BUS.register(new WeaponEventHandler(this, this.safeGlobals));

        KeyBindings.init();

        ClientWeaponTicker clientWeaponTicker = new ClientWeaponTicker(this);
        Runtime.getRuntime()
            .addShutdownHook(new Thread(clientWeaponTicker::shutdown));
        clientWeaponTicker.start();

        this.clientEventHandler = new ClientEventHandler(
            this,
            this.mainLoopLock,
            this.safeGlobals,
            this.runInClientThreadQueue);

        FMLCommonHandler.instance()
            .bus()
            .register(this.clientEventHandler);
        MinecraftForge.EVENT_BUS.register(this.clientEventHandler);

        RenderingRegistry.registerEntityRenderingHandler(WeaponSpawnEntity.class, new SpawnEntityRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityShellCasing.class, new ShellCasingRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new EntityGrenadeRenderer());

        this.viewManager = new ScopeManager(this);
        this.inventoryTextureMap = new HashMap<>();
        this.effectManager = new EffectManager();
        this.playerRawPitchAnimationManager = new PlayerRawPitchAnimationManager();
    }

    public boolean isClient() {
        return true;
    }

    public void registerWeapon(String name, Weapon weapon, WeaponRenderer renderer) {
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

    public void runSyncTick(Runnable runnable) {
        this.mainLoopLock.lock();

        try {
            runnable.run();
        } finally {
            this.mainLoopLock.unlock();
        }

    }

    public PlayerItemInstanceRegistry getPlayerItemInstanceRegistry() {
        return this.playerItemInstanceRegistry;
    }

    public SyncManager<?> getSyncManager() {
        return this.syncManager;
    }

    public PlayerWeaponInstance getMainHeldWeapon() {
        return this.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(Minecraft.getMinecraft().thePlayer, PlayerWeaponInstance.class);
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
