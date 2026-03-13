package com.gtnewhorizon.newgunrizons;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;

import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.entities.EntityGrenade;
import com.gtnewhorizon.newgunrizons.entities.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeAttackAspect;
import com.gtnewhorizon.newgunrizons.items.instances.ItemGrenadeInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.BlockHitMessage;
import com.gtnewhorizon.newgunrizons.network.ExplosionMessage;
import com.gtnewhorizon.newgunrizons.network.GrenadeMessage;
import com.gtnewhorizon.newgunrizons.network.GrenadeMessageHandler;
import com.gtnewhorizon.newgunrizons.network.SpawnParticleMessage;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessageHandler;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.registry.Grenades;
import com.gtnewhorizon.newgunrizons.registry.Guns;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponFireAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponReloadAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

    private int modEntityID = 256;

    public void init(Object mod, FMLPreInitializationEvent event) {
        SimpleNetworkWrapper channel = NewGunrizonsMod.CHANNEL;

        GrenadeAttackAspect.INSTANCE.setStateManager(new StateManager<>((s1, s2) -> s1 == s2));

        StateManager<WeaponState, ItemWeaponInstance> weaponStateManager = new StateManager<>((s1, s2) -> s1 == s2);
        WeaponReloadAspect.INSTANCE.setStateManager(weaponStateManager);
        WeaponFireAspect.INSTANCE.setStateManager(weaponStateManager);
        WeaponAttachmentAspect.INSTANCE.setStateManager(weaponStateManager);

        channel.registerMessage(
            new WeaponActionMessageHandler(WeaponFireAspect.INSTANCE),
            WeaponActionMessage.class,
            15,
            Side.SERVER);
        channel.registerMessage(
            new GrenadeMessageHandler(GrenadeAttackAspect.INSTANCE),
            GrenadeMessage.class,
            20,
            Side.SERVER);
        registerClientMessageHandlers(channel);

        EntityRegistry
            .registerModEntity(EntityBullet.class, "Ammo" + this.modEntityID, this.modEntityID++, mod, 64, 3, true);
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

        Attachments.init();
        Bullets.init();
        Guns.init();
        Grenades.init();
    }

    protected void registerClientMessageHandlers(SimpleNetworkWrapper channel) {
        channel.registerMessage((msg, ctx) -> null, SpawnParticleMessage.class, 18, Side.CLIENT);
        channel.registerMessage((msg, ctx) -> null, BlockHitMessage.class, 19, Side.CLIENT);
        channel.registerMessage((msg, ctx) -> null, ExplosionMessage.class, 21, Side.CLIENT);
    }

    public void onWeaponFireEffects(EntityLivingBase player, float smokeOffsetX, float smokeOffsetY,
        boolean silencerOn) {
        // No-op on server; overridden in ClientProxy
    }

    public void applyCameraRecoil(float pitchDelta, float yawDelta, int durationMs) {
        // No-op on server; overridden in ClientProxy
    }

    public boolean isLocalPlayer(EntityLivingBase player) {
        return false;
    }

    public void registerItem(String name, Item item, IItemRenderer renderer) {
        GameRegistry.registerItem(item, name);
    }

    static {
        TypeRegistry.getInstance()
            .register(ItemInstance.class);
        TypeRegistry.getInstance()
            .register(ItemWeaponInstance.class);
        TypeRegistry.getInstance()
            .register(WeaponState.class);
        TypeRegistry.getInstance()
            .register(ItemGrenadeInstance.class);
    }
}
