package com.gtnewhorizon.newgunrizons.client.scope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;

import com.gtnewhorizon.newgunrizons.config.ClientModContext;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;

public class ScopeManager {

    private final ClientModContext clientModContext;

    private ScopePerspective perspective;
    private ScopeWorldRenderer entityRenderer;
    private EffectRenderer effectRenderer;

    public ScopeManager(ClientModContext clientModContext) {
        this.clientModContext = clientModContext;
    }

    public ScopePerspective getPerspective(ItemInstance<?> currentInstance, boolean init) {
        if (currentInstance != null && (this.perspective != null || init)) {
            boolean needsPerspective = currentInstance.needsOpticalScopePerspective();
            if (needsPerspective) {
                if (this.perspective == null) {
                    this.perspective = new ScopePerspective();
                    this.perspective.activate(this.clientModContext, this);
                }
            } else if (this.perspective != null && init) {
                this.perspective.deactivate();
                this.perspective = null;
            }

            return this.perspective;
        } else {
            return null;
        }
    }

    public ScopeWorldRenderer getWorldRenderer() {
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
