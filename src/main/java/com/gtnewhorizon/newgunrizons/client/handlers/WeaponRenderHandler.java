package com.gtnewhorizon.newgunrizons.client.handlers;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

import com.gtnewhorizon.newgunrizons.client.particle.ParticleManager;
import com.gtnewhorizon.newgunrizons.client.scope.ScopeManager;
import com.gtnewhorizon.newgunrizons.client.scope.ScopePerspective;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderContext;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderManager;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderPhase;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponRenderHandler {

    private final ShaderManager shaderManager;

    public WeaponRenderHandler() {
        this.shaderManager = new ShaderManager();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        ShaderContext shaderContext = new ShaderContext(
            ShaderPhase.WORLD_RENDER,
            minecraft.entityRenderer,
            minecraft.getFramebuffer(),
            event.renderTickTime);
        EntityPlayer player = minecraft.thePlayer;
        if (event.phase == TickEvent.Phase.START) {
            com.gtnewhorizon.newgunrizons.client.animation.CameraRecoilController.INSTANCE.update(player);
            if (player != null) {
                ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
                if (weaponInstance != null) {
                    if (weaponInstance.isAimed()) {
                        ScopePerspective view = ScopeManager.INSTANCE.getPerspective(weaponInstance, true);
                        if (view != null) {
                            view.update(event, weaponInstance);
                        }
                    } else {
                        // Trigger scope deactivation when no longer aiming.
                        // ScopeManager.getPerspective with init=true cleans up
                        // the perspective and frees the FBO.
                        ScopeManager.INSTANCE.getPerspective(weaponInstance, true);
                    }
                }
            }
        } else if (event.phase == TickEvent.Phase.END) {
            ScopePerspective.isRenderingScope = false;
            this.shaderManager.removeStaleShaders(shaderContext);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        // During scope rendering, super.renderWorld() fires RenderHandEvent from
        // vanilla's hand block. Cancel it to prevent shader/framebuffer state
        // corruption — the scope framebuffer must stay bound, not the main one.
        if (ScopePerspective.isRenderingScope) {
            event.setCanceled(true);
            return;
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft.gameSettings.thirdPersonView == 0) {
            ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
            ShaderContext shaderContext = new ShaderContext(
                ShaderPhase.ITEM_RENDER,
                null,
                minecraft.getFramebuffer(),
                event.partialTicks);
            this.shaderManager.applyShader(shaderContext, weaponInstance);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onFovUpdate(FOVUpdateEvent event) {
        ItemWeaponInstance instance = ItemInstanceRegistry.getMainHeldWeapon();
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (instance != null) {
            float fov;
            if (instance.isAttachmentZoomEnabled()) {
                if (ScopePerspective.isRenderingScope) {
                    fov = instance.getZoom();
                } else {
                    fov = player.capabilities.isFlying ? 1.1F : 1.0F;
                }
            } else {
                fov = player.capabilities.isFlying ? 1.1F : 1.0F;
            }
            event.newfov = fov;
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderLiving(Pre event) {
        if (!event.isCanceled() && event.entity instanceof EntityPlayer) {
            ItemStack itemStack = event.entity.getHeldItem();
            if (itemStack != null && itemStack.getItem() instanceof ItemWeapon) {
                RenderPlayer rp = (RenderPlayer) event.renderer;
                ItemInstance<?> instance = ItemInstanceRegistry.INSTANCE.getItemInstance(event.entity, itemStack);
                if (instance instanceof ItemWeaponInstance) {
                    ItemWeaponInstance weaponInstance = (ItemWeaponInstance) instance;
                    rp.modelBipedMain.aimedBow = weaponInstance.isAimed()
                        || weaponInstance.getState() == WeaponState.SHOOTING;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        ParticleManager.renderSmoke(event.partialTicks);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        event.map.registerIcon(new ResourceLocation(NewGunrizonsMod.MODID, "particle/blood").toString());
    }
}
