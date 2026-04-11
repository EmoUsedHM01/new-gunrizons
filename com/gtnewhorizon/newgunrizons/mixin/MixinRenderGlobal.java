package com.gtnewhorizon.newgunrizons.mixin;

import com.gtnewhorizon.newgunrizons.client.scope.ScopePerspective;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderGlobal.class, priority = 2000)
public class MixinRenderGlobal {
   @Inject(method = "clipRenderersByFrustum", at = @At("HEAD"), cancellable = true)
   private void mw$skipFrustumCullDuringScopeRender(ICamera camera, float partialTicks, CallbackInfo ci) {
      if (ScopePerspective.isRenderingScope) {
         ci.cancel();
      }
   }
}
