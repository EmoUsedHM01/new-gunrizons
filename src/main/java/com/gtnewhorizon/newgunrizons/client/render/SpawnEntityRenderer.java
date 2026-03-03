package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.entity.WeaponSpawnEntity;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class SpawnEntityRenderer extends Render {

    public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
        WeaponSpawnEntity weaponSpawnEntity = (WeaponSpawnEntity) entity;
        Weapon weapon = weaponSpawnEntity.getWeapon();
        if (weapon != null) {
            ModelBase model = weapon.getAmmoModel();
            if (model != null) {
                String ammoModelTextureName = weapon.getAmmoModelTextureName();
                ResourceLocation textureLocation = ammoModelTextureName != null
                    ? new ResourceLocation(ammoModelTextureName)
                    : null;
                GL11.glPushMatrix();
                if (textureLocation != null) {
                    this.bindTexture(textureLocation);
                }
                GL11.glTranslated(x, y, z);
                model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
            }

        }
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        WeaponSpawnEntity weaponSpawnEntity = (WeaponSpawnEntity) entity;
        return new ResourceLocation(
            weaponSpawnEntity.getWeapon()
                .getAmmoModelTextureName());
    }
}
