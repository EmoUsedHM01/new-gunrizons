package com.gtnewhorizon.newgunrizons.client.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

import com.gtnewhorizon.newgunrizons.client.debug.MuzzleDebug;
import com.gtnewhorizon.newgunrizons.client.render.HudRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WeaponHudHandler {

    private final HudRenderer hudRenderer;

    public WeaponHudHandler() {
        this.hudRenderer = new HudRenderer();
    }

    @SubscribeEvent
    public void onRenderCrosshair(Pre event) {
        if (event.type != ElementType.CROSSHAIRS) {
            return;
        }

        MuzzleDebug.renderOverlay(event.resolution);

        ItemStack itemStack = Minecraft.getMinecraft().thePlayer.getHeldItem();
        if (itemStack == null) {
            return;
        }

        ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
        if (weaponInstance != null) {
            if (hudRenderer.renderWeaponHud(event.resolution, itemStack, weaponInstance)) {
                event.setCanceled(true);
            }
        } else if (itemStack.getItem() instanceof ItemMagazine) {
            hudRenderer.renderMagazineHud(event.resolution, itemStack);
            event.setCanceled(true);
        } else if (itemStack.getItem() instanceof ItemGrenade) {
            if (hudRenderer.renderGrenadeHud(event.resolution)) {
                event.setCanceled(true);
            }
        }
    }
}
