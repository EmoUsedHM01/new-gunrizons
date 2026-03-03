package com.vicmatskiv.weaponlib;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderHandEvent;

import com.vicmatskiv.weaponlib.scope.ScopePerspective;
import com.vicmatskiv.weaponlib.shader.DynamicShaderContext;
import com.vicmatskiv.weaponlib.shader.DynamicShaderGroupManager;
import com.vicmatskiv.weaponlib.shader.DynamicShaderPhase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientEventHandler {

    private static final UUID SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER_UUID = UUID
        .fromString("8efa8469-0256-4f8e-bdd9-3e7b23970663");
    private static final AttributeModifier SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER;
    private final Lock mainLoopLock;
    private final SafeGlobals safeGlobals;
    private final Queue<Runnable> runInClientThreadQueue;
    private final ClientModContext modContext;
    private final DynamicShaderGroupManager shaderGroupManager;

    public ClientEventHandler(ClientModContext modContext, Lock mainLoopLock, SafeGlobals safeGlobals,
        Queue<Runnable> runInClientThreadQueue) {
        this.modContext = modContext;
        this.mainLoopLock = mainLoopLock;
        this.safeGlobals = safeGlobals;
        this.runInClientThreadQueue = runInClientThreadQueue;
        this.shaderGroupManager = new DynamicShaderGroupManager();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            this.mainLoopLock.lock();
        } else if (event.phase == TickEvent.Phase.END) {
            this.update();
            this.modContext.getSyncManager()
                .run();
            this.mainLoopLock.unlock();
            this.processRunInClientThreadQueue();
            this.safeGlobals.objectMouseOver.set(Minecraft.getMinecraft().objectMouseOver);
            if (Minecraft.getMinecraft().thePlayer != null) {
                this.safeGlobals.currentItemIndex.set(Minecraft.getMinecraft().thePlayer.inventory.currentItem);
            }
        }

    }

    private void update() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        this.modContext.getPlayerItemInstanceRegistry()
            .update(player);
        PlayerWeaponInstance mainHandHeldWeaponInstance = this.modContext.getMainHeldWeapon();
        if (mainHandHeldWeaponInstance != null) {
            if (player.isSprinting()) {
                mainHandHeldWeaponInstance.setAimed(false);
            }

            if (mainHandHeldWeaponInstance.isAimed()) {
                this.slowPlayerDown(player);
            } else {
                this.restorePlayerSpeed(player);
            }
        } else if (player != null) {
            this.restorePlayerSpeed(player);
        }

    }

    private void restorePlayerSpeed(EntityPlayer entityPlayer) {
        if (entityPlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .getModifier(ClientEventHandler.SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER.getID()) != null) {
            entityPlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .removeModifier(ClientEventHandler.SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER);
        }

    }

    private void slowPlayerDown(EntityPlayer entityPlayer) {
        if (entityPlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .getModifier(ClientEventHandler.SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER.getID()) == null) {
            entityPlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .applyModifier(ClientEventHandler.SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER);
        }

    }

    private void processRunInClientThreadQueue() {
        Runnable r;
        while ((r = this.runInClientThreadQueue.poll()) != null) {
            r.run();
        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderHandEvent(RenderHandEvent event) {
        // During scope rendering, super.renderWorld() fires RenderHandEvent from
        // vanilla's hand block. Cancel it to prevent shader/framebuffer state
        // corruption — the scope framebuffer must stay bound, not the main one.
        if (this.safeGlobals.renderingPhase.get() == RenderingPhase.RENDER_PERSPECTIVE) {
            event.setCanceled(true);
            return;
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft.gameSettings.thirdPersonView == 0) {
            PlayerWeaponInstance weaponInstance = this.modContext.getMainHeldWeapon();
            DynamicShaderContext shaderContext = new DynamicShaderContext(
                DynamicShaderPhase.PRE_ITEM_RENDER,
                null,
                minecraft.getFramebuffer(),
                event.partialTicks);
            this.shaderGroupManager.applyShader(shaderContext, weaponInstance);
        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderTickEvent(RenderTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        DynamicShaderContext shaderContext = new DynamicShaderContext(
            DynamicShaderPhase.POST_WORLD_RENDER,
            minecraft.entityRenderer,
            minecraft.getFramebuffer(),
            event.renderTickTime);
        EntityPlayer clientPlayer = Minecraft.getMinecraft().thePlayer;
        if (event.phase == TickEvent.Phase.START) {
            this.mainLoopLock.lock();
            if (clientPlayer != null) {
                PlayerWeaponInstance weaponInstance = this.modContext.getMainHeldWeapon();
                if (weaponInstance != null) {
                    if (weaponInstance.isAimed()) {
                        ScopePerspective view = this.modContext.getViewManager()
                            .getPerspective(weaponInstance, true);
                        if (view != null) {
                            view.update(event, weaponInstance);
                        }
                    } else {
                        // Trigger scope deactivation when no longer aiming.
                        // ScopeManager.getPerspective with init=true cleans up
                        // the perspective and frees the FBO.
                        this.modContext.getViewManager()
                            .getPerspective(weaponInstance, true);
                    }
                }
            }
        } else if (event.phase == TickEvent.Phase.END) {
            this.safeGlobals.renderingPhase.set(null);
            this.shaderGroupManager.removeStaleShaders(shaderContext);
            this.mainLoopLock.unlock();
        }

    }

    static {
        SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER = (new AttributeModifier(
            SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER_UUID,
            "Slow Down While Zooming",
            -0.5D,
            2)).setSaved(false);
    }
}
