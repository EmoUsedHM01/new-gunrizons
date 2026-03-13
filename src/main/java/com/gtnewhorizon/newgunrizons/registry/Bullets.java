package com.gtnewhorizon.newgunrizons.registry;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.model.JsonModel;

public class Bullets {

    public static ItemBullet ShotgunShell;

    public static void init() {
        ShotgunShell = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("ShotgunShell")
            .withModel(new JsonModel("ammo/shotgunshell"), "ShotgunShell.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.2D, 1.2D, 1.2D);
            })
            .withTextureName("Dummy.png")
            .build(ItemBullet.class);
    }
}
