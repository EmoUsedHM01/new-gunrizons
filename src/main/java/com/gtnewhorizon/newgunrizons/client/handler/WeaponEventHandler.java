package com.gtnewhorizon.newgunrizons.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.gtnewhorizon.newgunrizons.client.render.RenderingPhase;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.SafeGlobals;
import com.gtnewhorizon.newgunrizons.grenade.EntityGrenade;
import com.gtnewhorizon.newgunrizons.grenade.PlayerGrenadeInstance;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponEventHandler {

    private final SafeGlobals safeGlobals;
    private final ModContext modContext;

    public WeaponEventHandler(ModContext modContext, SafeGlobals safeGlobals) {
        this.modContext = modContext;
        this.safeGlobals = safeGlobals;
    }

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {
        this.safeGlobals.guiOpen.set(event.gui != null);
    }

    @SubscribeEvent
    public void zoom(FOVUpdateEvent event) {
        PlayerWeaponInstance instance = this.modContext.getMainHeldWeapon();
        EntityPlayer clientPlayer = Minecraft.getMinecraft().thePlayer;
        if (instance != null) {
            float fov;
            if (instance.isAttachmentZoomEnabled()) {
                if (this.safeGlobals.renderingPhase.get() == RenderingPhase.RENDER_SCOPE) {
                    fov = instance.getZoom();
                } else {
                    fov = clientPlayer.capabilities.isFlying ? 1.1F : 1.0F;
                }
            } else {
                fov = Minecraft.getMinecraft().thePlayer.capabilities.isFlying ? 1.1F : 1.0F;
            }

            event.newfov = fov;
        }

    }

    @SubscribeEvent
    public void onMouse(MouseEvent event) {
        if (event.button == 0 || event.button == 1) {
            PlayerItemInstance<?> instance = this.modContext.getPlayerItemInstanceRegistry()
                .getMainHandItemInstance(Minecraft.getMinecraft().thePlayer);
            if (instance instanceof PlayerWeaponInstance || instance instanceof PlayerGrenadeInstance) {
                event.setCanceled(true);
            }
        }

    }

    @SubscribeEvent
    public void handleRenderLivingEvent(Pre event) {
        if (!event.isCanceled() && event.entity instanceof EntityPlayer) {
            ItemStack itemStack = event.entity.getHeldItem();
            if (itemStack != null && itemStack.getItem() instanceof ItemWeapon) {
                RenderPlayer rp = (RenderPlayer) event.renderer;
                PlayerItemInstance<?> instance = this.modContext.getPlayerItemInstanceRegistry()
                    .getItemInstance(event.entity, itemStack);
                if (instance instanceof PlayerWeaponInstance weaponInstance) {
                    rp.modelBipedMain.aimedBow = weaponInstance.isAimed()
                        || weaponInstance.getState() == WeaponState.FIRING
                        || weaponInstance.getState() == WeaponState.RECOILED
                        || weaponInstance.getState() == WeaponState.PAUSED;
                }
            }

        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityGrenade) {
            ((EntityGrenade) event.entity).setContext(this.modContext);
        }

    }

    @SubscribeEvent
    public void onTextureStitchEvent(TextureStitchEvent.Pre event) {
        event.map.registerIcon(
            this.getModContext()
                .getNamedResource("particle/blood")
                .toString());
    }

    protected ModContext getModContext() {
        return this.modContext;
    }
}
