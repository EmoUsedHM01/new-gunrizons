package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.model.JsonModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityBulletRenderer extends Render {
   private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("newgunrizons", "textures/effect/bullet44.png");
   private static final ModelBase DEFAULT_MODEL = new JsonModel("misc/modelbullet");

   public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
   }

   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
      return DEFAULT_TEXTURE;
   }
}
