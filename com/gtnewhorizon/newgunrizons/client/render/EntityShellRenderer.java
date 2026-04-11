package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.entities.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.JsonModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class EntityShellRenderer extends Render {
   private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("newgunrizons", "textures/effect/shell.png");
   private static final ModelBase DEFAULT_MODEL = new JsonModel("misc/modelshell");

   public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float tick) {
      EntityShellCasing entityShellCasing = (EntityShellCasing)entity;
      ItemWeapon weapon = entityShellCasing.getWeapon();
      if (weapon != null) {
         GL11.glPushMatrix();
         this.func_110776_a(DEFAULT_TEXTURE);
         GL11.glTranslated(x, y, z);
         float fov = Minecraft.func_71410_x().field_71474_y.field_74334_X;
         float scale = (fov * 0.001F - 0.02F) * 0.3F;
         GL11.glScalef(scale, scale, scale);
         GL11.glRotatef(entityShellCasing.getXRotation(), 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(entityShellCasing.getYRotation(), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entityShellCasing.getZRotation(), 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         DEFAULT_MODEL.func_78088_a(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
         GL11.glPopMatrix();
      }
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return DEFAULT_TEXTURE;
   }
}
