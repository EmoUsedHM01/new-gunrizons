package com.gtnewhorizon.newgunrizons.config;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import com.gtnewhorizon.newgunrizons.client.input.WeaponKeyInputHandler;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.RecipeManager;
import com.gtnewhorizon.newgunrizons.entities.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.grenade.EntityGrenade;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeAttackAspect;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeMessage;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeMessageHandler;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeState;
import com.gtnewhorizon.newgunrizons.grenade.ItemGrenade;
import com.gtnewhorizon.newgunrizons.grenade.PlayerGrenadeInstance;
import com.gtnewhorizon.newgunrizons.weapon.MagazineReloadAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponFireAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponReloadAspect;
import com.gtnewhorizon.newgunrizons.network.BlockHitMessage;
import com.gtnewhorizon.newgunrizons.network.BlockHitMessageHandler;
import com.gtnewhorizon.newgunrizons.network.ExplosionMessage;
import com.gtnewhorizon.newgunrizons.network.ExplosionMessageHandler;
import com.gtnewhorizon.newgunrizons.network.NetworkPermitManager;
import com.gtnewhorizon.newgunrizons.network.PermitMessage;
import com.gtnewhorizon.newgunrizons.network.SpawnParticleMessage;
import com.gtnewhorizon.newgunrizons.network.SpawnParticleMessageHandler;
import com.gtnewhorizon.newgunrizons.network.StatusMessageCenter;
import com.gtnewhorizon.newgunrizons.network.SyncManager;
import com.gtnewhorizon.newgunrizons.network.TryFireMessage;
import com.gtnewhorizon.newgunrizons.network.TryFireMessageHandler;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.server.ServerEventHandler;
import com.gtnewhorizon.newgunrizons.state.Permit;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.weapon.MagazineState;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.weapon.PlayerMagazineInstance;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import lombok.Getter;

public class CommonModContext implements ModContext {

    @Getter
    protected String modId;
    @Getter
    protected Object mod;

    @Getter
    protected SimpleNetworkWrapper channel;
    @Getter
    protected WeaponReloadAspect weaponReloadAspect;
    protected WeaponAttachmentAspect weaponAttachmentAspect;
    @Getter
    protected WeaponFireAspect weaponFireAspect;
    protected SyncManager<?> syncManager;
    @Getter
    protected MagazineReloadAspect magazineReloadAspect;
    protected NetworkPermitManager permitManager;
    @Getter
    protected PlayerItemInstanceRegistry playerItemInstanceRegistry;
    private final Map<ResourceLocation, String> registeredSounds = new HashMap<>();
    @Getter
    private RecipeManager recipeManager;
    private String changeZoomSound;
    @Getter
    private String changeFireModeSound;
    @Getter
    private String noAmmoSound;
    @Getter
    private String explosionSound;
    private int modEntityID = 256;
    @Getter
    private GrenadeAttackAspect grenadeAttackAspect;

    public void init(Object mod, String modId, SimpleNetworkWrapper channel) {
        this.mod = mod;
        this.channel = channel;
        this.modId = modId;
        this.weaponReloadAspect = new WeaponReloadAspect(this);
        this.magazineReloadAspect = new MagazineReloadAspect(this);
        this.weaponFireAspect = new WeaponFireAspect(this);
        this.weaponAttachmentAspect = new WeaponAttachmentAspect(this);
        this.grenadeAttackAspect = new GrenadeAttackAspect(this);
        StateManager<GrenadeState, PlayerGrenadeInstance> grenadeStateManager = new StateManager<>(
            (s1, s2) -> s1 == s2);
        this.grenadeAttackAspect.setStateManager(grenadeStateManager);
        this.permitManager = new NetworkPermitManager(this);
        this.syncManager = new SyncManager<>(this.permitManager);
        this.playerItemInstanceRegistry = new PlayerItemInstanceRegistry(this.syncManager);
        StateManager<WeaponState, PlayerWeaponInstance> weaponStateManager = new StateManager<>((s1, s2) -> s1 == s2);
        this.weaponReloadAspect.setPermitManager(this.permitManager);
        this.weaponReloadAspect.setStateManager(weaponStateManager);
        this.weaponFireAspect.setPermitManager(this.permitManager);
        this.weaponFireAspect.setStateManager(weaponStateManager);
        this.weaponAttachmentAspect.setPermitManager(this.permitManager);
        this.weaponAttachmentAspect.setStateManager(weaponStateManager);
        StateManager<MagazineState, PlayerMagazineInstance> magazineStateManager = new StateManager<>(
            (s1, s2) -> s1 == s2);
        this.magazineReloadAspect.setPermitManager(this.permitManager);
        this.magazineReloadAspect.setStateManager(magazineStateManager);
        this.recipeManager = new RecipeManager();
        channel
            .registerMessage(new TryFireMessageHandler(this.weaponFireAspect), TryFireMessage.class, 11, Side.SERVER);
        channel.registerMessage(this.permitManager, PermitMessage.class, 14, Side.SERVER);
        channel.registerMessage(this.permitManager, PermitMessage.class, 15, Side.CLIENT);
        channel.registerMessage(new SpawnParticleMessageHandler(this), SpawnParticleMessage.class, 18, Side.CLIENT);
        channel.registerMessage(new BlockHitMessageHandler(), BlockHitMessage.class, 19, Side.CLIENT);
        channel.registerMessage(
            new GrenadeMessageHandler(this.grenadeAttackAspect),
            GrenadeMessage.class,
            20,
            Side.SERVER);
        channel.registerMessage(new ExplosionMessageHandler(this), ExplosionMessage.class, 21, Side.CLIENT);
        ServerEventHandler serverHandler = new ServerEventHandler(this, modId);
        FMLCommonHandler.instance()
            .bus()
            .register(serverHandler);
        MinecraftForge.EVENT_BUS.register(serverHandler);
        FMLCommonHandler.instance()
            .bus()
            .register(new WeaponKeyInputHandler(this, (ctx) -> this.getPlayer()));
        EntityRegistry.registerModEntity(
            EntityBullet.class,
            "Ammo" + this.modEntityID,
            this.modEntityID++,
            mod,
            64,
            3,
            true);
        EntityRegistry.registerModEntity(
            EntityShellCasing.class,
            "ShellCasing" + this.modEntityID,
            this.modEntityID++,
            mod,
            64,
            500,
            true);
        EntityRegistry.registerModEntity(
            EntityGrenade.class,
            "Grenade" + this.modEntityID,
            this.modEntityID++,
            mod,
            64,
            10000,
            false);
    }

    public boolean isClient() {
        return false;
    }

    public String registerSound(String sound) {
        if (sound == null) {
            return null;
        } else {
            ResourceLocation soundResourceLocation = new ResourceLocation(this.modId, sound);
            return this.registerSound(soundResourceLocation);
        }
    }

    protected String registerSound(ResourceLocation soundResourceLocation) {
        String result = this.registeredSounds.get(soundResourceLocation);
        if (result == null) {
            result = soundResourceLocation.toString();
            this.registeredSounds.put(soundResourceLocation, result);
        }

        return result;
    }

    public void registerWeapon(String name, ItemWeapon weapon, WeaponRenderer renderer) {
        GameRegistry.registerItem(weapon, name);
    }

    protected EntityPlayer getPlayer() {
        return null;
    }

    public void runSyncTick(Runnable runnable) {
        throw new UnsupportedOperationException();
    }

    public void registerRenderableItem(String name, Item item, Object renderer) {
        GameRegistry.registerItem(item, name);
    }

    public WeaponAttachmentAspect getAttachmentAspect() {
        return this.weaponAttachmentAspect;
    }

    public PlayerWeaponInstance getMainHeldWeapon() {
        throw new IllegalStateException();
    }

    public StatusMessageCenter getStatusMessageCenter() {
        throw new IllegalStateException();
    }

    public void setChangeZoomSound(String sound) {
        this.changeZoomSound = this.registerSound(sound.toLowerCase());
    }

    public String getZoomSound() {
        return this.changeZoomSound;
    }

    public void setChangeFireModeSound(String sound) {
        this.changeFireModeSound = this.registerSound(sound.toLowerCase());
    }

    public void setNoAmmoSound(String sound) {
        this.noAmmoSound = this.registerSound(sound.toLowerCase());
    }

    public void setExplosionSound(String sound) {
        this.explosionSound = this.registerSound(sound.toLowerCase());
    }

    public void registerGrenadeWeapon(String name, ItemGrenade itemMelee, GrenadeRenderer renderer) {
        GameRegistry.registerItem(itemMelee, name);
    }

    public ResourceLocation getNamedResource(String name) {
        return new ResourceLocation(this.modId, name);
    }

    static {
        TypeRegistry.getInstance()
            .register(MagazineReloadAspect.LoadPermit.class);
        TypeRegistry.getInstance()
            .register(MagazineState.class);
        TypeRegistry.getInstance()
            .register(PlayerItemInstance.class);
        TypeRegistry.getInstance()
            .register(PlayerWeaponInstance.class);
        TypeRegistry.getInstance()
            .register(PlayerMagazineInstance.class);
        TypeRegistry.getInstance()
            .register(PlayerWeaponInstance.class);
        TypeRegistry.getInstance()
            .register(Permit.class);
        TypeRegistry.getInstance()
            .register(WeaponAttachmentAspect.EnterAttachmentModePermit.class);
        TypeRegistry.getInstance()
            .register(WeaponAttachmentAspect.ExitAttachmentModePermit.class);
        TypeRegistry.getInstance()
            .register(WeaponAttachmentAspect.ChangeAttachmentPermit.class);
        TypeRegistry.getInstance()
            .register(WeaponReloadAspect.UnloadPermit.class);
        TypeRegistry.getInstance()
            .register(MagazineReloadAspect.LoadPermit.class);
        TypeRegistry.getInstance()
            .register(PlayerWeaponInstance.class);
        TypeRegistry.getInstance()
            .register(WeaponState.class);
        TypeRegistry.getInstance()
            .register(PlayerGrenadeInstance.class);
    }
}
