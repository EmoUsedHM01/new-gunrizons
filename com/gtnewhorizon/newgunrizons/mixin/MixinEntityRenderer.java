package com.gtnewhorizon.newgunrizons.mixin;

import com.gtnewhorizon.newgunrizons.client.scope.ScopePerspective;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {
   @Inject(method = "renderHand", at = @At("HEAD"), cancellable = true)
   private void mw$skipHandDuringScopeRender(float partialTicks, int pass, CallbackInfo ci) {
      if (ScopePerspective.isRenderingScope) {
         ci.cancel();
      }
   }
}
