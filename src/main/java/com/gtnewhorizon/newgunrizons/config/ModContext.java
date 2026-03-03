package com.gtnewhorizon.newgunrizons.config;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.newgunrizons.client.effect.EffectManager;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.RecipeManager;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeAttackAspect;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.grenade.ItemGrenade;
import com.gtnewhorizon.newgunrizons.mechanic.MagazineReloadAspect;
import com.gtnewhorizon.newgunrizons.mechanic.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.mechanic.WeaponFireAspect;
import com.gtnewhorizon.newgunrizons.mechanic.WeaponReloadAspect;
import com.gtnewhorizon.newgunrizons.network.StatusMessageCenter;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public interface ModContext {

    boolean isClient();

    void init(Object var1, String var2, SimpleNetworkWrapper var4);

    void registerWeapon(String var1, Weapon var2, WeaponRenderer var3);

    SimpleNetworkWrapper getChannel();

    void runSyncTick(Runnable var1);

    void registerRenderableItem(String var1, Item var2, Object var3);

    String registerSound(String var1);

    PlayerItemInstanceRegistry getPlayerItemInstanceRegistry();

    WeaponReloadAspect getWeaponReloadAspect();

    WeaponFireAspect getWeaponFireAspect();

    WeaponAttachmentAspect getAttachmentAspect();

    MagazineReloadAspect getMagazineReloadAspect();

    PlayerWeaponInstance getMainHeldWeapon();

    StatusMessageCenter getStatusMessageCenter();

    RecipeManager getRecipeManager();

    String getZoomSound();

    void setChangeZoomSound(String var1);

    String getChangeFireModeSound();

    void setChangeFireModeSound(String var1);

    String getNoAmmoSound();

    void setNoAmmoSound(String var1);

    String getExplosionSound();

    void setExplosionSound(String var1);

    void registerGrenadeWeapon(String var1, ItemGrenade var2, GrenadeRenderer var3);

    ResourceLocation getNamedResource(String var1);

    GrenadeAttackAspect getGrenadeAttackAspect();

    String getModId();

    EffectManager getEffectManager();
}
