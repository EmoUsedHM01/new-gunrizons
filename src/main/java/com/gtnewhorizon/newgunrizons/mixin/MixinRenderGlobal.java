package com.gtnewhorizon.newgunrizons.mixin;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnewhorizon.newgunrizons.client.scope.ScopePerspective;

/**
 * Prevents Celeritas from recomputing chunk visibility during scope rendering.
 * <p>
 * Celeritas's {@code @Overwrite} of {@code clipRenderersByFrustum} runs a BFS
 * that builds chunk render lists based on the current frustum. The scope uses
 * a 1:1 aspect ratio with a narrower horizontal FOV than the main 16:9 render,
 * so its BFS excludes chunks at the screen edges. Even with
 * {@code scheduleTerrainUpdate()}, the scope re-narrows the visibility set
 * every frame before the main render can recover.
 * <p>
 * By cancelling {@code clipRenderersByFrustum} during scope rendering, the scope
 * reuses the previous frame's main-render visibility set (which covers the full
 * widescreen). The main render then runs its own BFS undisturbed.
 */
@Mixin(value = RenderGlobal.class, priority = 2000)
public class MixinRenderGlobal {

    @Inject(method = "clipRenderersByFrustum", at = @At("HEAD"), cancellable = true)
    private void mw$skipFrustumCullDuringScopeRender(ICamera camera, float partialTicks, CallbackInfo ci) {
        if (ScopePerspective.isRenderingScope) {
            ci.cancel();
        }
    }
}
