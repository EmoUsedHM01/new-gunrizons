package com.gtnewhorizon.newgunrizons.client.scope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;

public class ScopeManager {

    public static final ScopeManager INSTANCE = new ScopeManager();

    private ScopePerspective perspective;
    private ScopeWorldRenderer worldRenderer;
    private EffectRenderer effectRenderer;

    public ScopePerspective getPerspective(ItemInstance<?> currentInstance, boolean init) {
        if (currentInstance != null && (this.perspective != null || init)) {
            boolean needsPerspective = currentInstance.needsOpticalScopePerspective();
            if (needsPerspective) {
                if (this.perspective == null) {
                    this.perspective = new ScopePerspective();
                    this.perspective.activate(this);
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
        if (worldRenderer == null) {
            worldRenderer = new ScopeWorldRenderer(
                Minecraft.getMinecraft(),
                Minecraft.getMinecraft()
                    .getResourceManager());
        }
        return worldRenderer;
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
