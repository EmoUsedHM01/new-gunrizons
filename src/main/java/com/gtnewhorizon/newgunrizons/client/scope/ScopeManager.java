package com.gtnewhorizon.newgunrizons.client.scope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;

import com.gtnewhorizon.newgunrizons.config.ClientModContext;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;

public class ScopeManager {

    private final ClientModContext clientModContext;

    private ScopePerspective currentPerspective;
    private ScopeWorldRenderer entityRenderer;
    private EffectRenderer effectRenderer;

    public ScopeManager(ClientModContext clientModContext) {
        this.clientModContext = clientModContext;
    }

    public ScopePerspective getPerspective(PlayerItemInstance<?> currentInstance, boolean init) {
        if (currentInstance != null && (this.currentPerspective != null || init)) {
            boolean needsPerspective = currentInstance.needsOpticalScopePerspective();
            if (needsPerspective) {
                if (this.currentPerspective == null) {
                    this.currentPerspective = new ScopePerspective();
                    this.currentPerspective.activate(this.clientModContext, this);
                }
            } else if (this.currentPerspective != null && init) {
                this.currentPerspective.deactivate();
                this.currentPerspective = null;
            }

            return this.currentPerspective;
        } else {
            return null;
        }
    }

    public ScopeWorldRenderer getEntityRenderer() {
        if (entityRenderer == null) {
            entityRenderer = new ScopeWorldRenderer(
                Minecraft.getMinecraft(),
                Minecraft.getMinecraft()
                    .getResourceManager());
        }
        return entityRenderer;
    }

    public EffectRenderer getEffectRenderer() {
        if (effectRenderer == null) {
            effectRenderer = new EffectRenderer(
                Minecraft.getMinecraft().thePlayer.worldObj,
                Minecraft.getMinecraft()
                    .getTextureManager());
        }

        return effectRenderer;
    }
}
