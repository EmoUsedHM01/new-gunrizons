package com.gtnewhorizon.newgunrizons.client.scope;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;

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
      if (this.worldRenderer == null) {
         this.worldRenderer = new ScopeWorldRenderer(Minecraft.func_71410_x(), Minecraft.func_71410_x().func_110442_L());
      }

      return this.worldRenderer;
   }

   public EffectRenderer getEffectRenderer() {
      if (this.effectRenderer == null) {
         this.effectRenderer = new EffectRenderer(Minecraft.func_71410_x().field_71439_g.field_70170_p, Minecraft.func_71410_x().func_110434_K());
      }

      return this.effectRenderer;
   }
}
