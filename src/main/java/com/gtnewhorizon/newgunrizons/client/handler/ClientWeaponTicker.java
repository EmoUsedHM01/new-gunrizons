package com.gtnewhorizon.newgunrizons.client.handler;

import java.util.concurrent.atomic.AtomicBoolean;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Mouse;

import com.gtnewhorizon.newgunrizons.config.ClientModContext;
import com.gtnewhorizon.newgunrizons.config.SafeGlobals;
import com.gtnewhorizon.newgunrizons.grenade.ItemGrenade;
import com.gtnewhorizon.newgunrizons.util.Updatable;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

import cpw.mods.fml.client.FMLClientHandler;

public class ClientWeaponTicker extends Thread {

    boolean[] buttonsPressed = new boolean[2];
    long[] buttonsPressedTimestamps = new long[2];
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final ClientModContext clientModContext;

    public ClientWeaponTicker(ClientModContext clientModContext) {
        this.clientModContext = clientModContext;
    }

    public void shutdown() {
        this.running.set(false);
    }

    public void run() {
        SafeGlobals safeGlobals = this.clientModContext.getSafeGlobals();
        int currentItemIndex = safeGlobals.currentItemIndex.get();

        while (this.running.get()) {
            try {
                if (Mouse.isCreated()) {
                    if (Mouse.isButtonDown(1)) {
                        if (!this.buttonsPressed[1]) {
                            this.buttonsPressed[1] = true;
                            this.buttonsPressedTimestamps[1] = System.currentTimeMillis();
                            if (!safeGlobals.guiOpen.get() && !this.isInteracting()) {
                                this.clientModContext.runSyncTick(this::onRightButtonDown);
                            }
                        }
                    } else if (this.buttonsPressed[1]) {
                        this.buttonsPressed[1] = false;
                        this.clientModContext.runSyncTick(this::onRightButtonUp);
                    }

                    if (Mouse.isButtonDown(0)) {
                        currentItemIndex = safeGlobals.currentItemIndex.get();
                        if (!this.buttonsPressed[0]) {
                            this.buttonsPressed[0] = true;
                        }

                        if (!safeGlobals.guiOpen.get() && !this.isInteracting()) {
                            this.clientModContext.runSyncTick(this::onLeftButtonDown);
                        }
                    } else if (this.buttonsPressed[0] || currentItemIndex != safeGlobals.currentItemIndex.get()) {
                        this.buttonsPressed[0] = false;
                        currentItemIndex = safeGlobals.currentItemIndex.get();
                        this.clientModContext.runSyncTick(this::onLeftButtonUp);
                    }

                    this.clientModContext.runSyncTick(this::onTick);
                    Thread.sleep(10L);
                }
            } catch (InterruptedException var4) {
                break;
            }
        }

    }

    private void onLeftButtonUp() {
        EntityPlayer player = FMLClientHandler.instance()
            .getClientPlayerEntity();
        Item item = this.getHeldItemMainHand(player);
        if (item instanceof ItemWeapon) {
            ((ItemWeapon) item).tryStopFire(player);
        } else if (item instanceof ItemGrenade) {
            ((ItemGrenade) item).attackUp(player, true);
        }

    }

    private void onRightButtonUp() {
        EntityPlayer player = FMLClientHandler.instance()
            .getClientPlayerEntity();
        Item item = this.getHeldItemMainHand(player);
        if (item instanceof ItemGrenade) {
            ((ItemGrenade) item).attackUp(player, false);
        }

    }

    private void onLeftButtonDown() {
        EntityPlayer player = FMLClientHandler.instance()
            .getClientPlayerEntity();
        Item item = this.getHeldItemMainHand(player);
        if (item instanceof ItemWeapon) {
            ((ItemWeapon) item).tryFire(player);
        } else if (item instanceof ItemGrenade) {
            ((ItemGrenade) item).attack(player, true);
        }

    }

    private void onRightButtonDown() {
        EntityPlayer player = FMLClientHandler.instance()
            .getClientPlayerEntity();
        Item item = this.getHeldItemMainHand(player);
        if (item instanceof ItemWeapon) {
            ((ItemWeapon) item).toggleAiming();
        } else if (item instanceof ItemGrenade) {
            ((ItemGrenade) item).attack(player, false);
        }

    }

    private void onTick() {
        EntityPlayer player = FMLClientHandler.instance()
            .getClientPlayerEntity();
        Item item = this.getHeldItemMainHand(player);
        if (item instanceof Updatable) {
            ((Updatable) item).update(player);
        }

    }

    private boolean isInteracting() {
        return false;
    }

    private Item getHeldItemMainHand(EntityPlayer player) {
        if (player == null) {
            return null;
        } else {
            ItemStack itemStack = player.getHeldItem();
            return itemStack != null ? itemStack.getItem() : null;
        }
    }
}
