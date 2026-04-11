package com.gtnewhorizon.newgunrizons.items.factories.guns.smg;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.JsonModel;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import java.util.Arrays;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class MAC10Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("MAC-10")
         .withFireRate(0.9F)
         .withRecoil(2.7F)
         .withMaxShots(Integer.MAX_VALUE, 1)
         .withShootSound("MAC10")
         .withSilencedShootSound("MP5Silenced")
         .withReloadSound("mac10reload")
         .withUnloadSound("pistolunload")
         .withReloadingTime(30L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.7F)
         .withFlashOffsetZ(() -> 3.2F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withInaccuracy(2.0F)
         .withCreativeTab(NewGunrizonsMod.PistolsTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Machine pistol", "Damage: 5.5", "Caliber: .45 ACP", "Magazines:", "26rnd .45 ACP Magazine", "Fire Rate: Auto")
         )
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.162F, -1.75F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(-0.055F, -1.35F, -4.05F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/p90iron")) {
               GL11.glTranslatef(0.26F, -1.55F, -2.35F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(0.13F, -1.55F, -3.05F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(0.092F, -1.91F, -0.9F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.1F, -1.05F, -1.4F);
               GL11.glScaled(0.2, 0.45, 0.3);
               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Magazines.VectorMag, model -> GL11.glTranslatef(-0.32F, 0.2F, 1.12F))
         .withCompatibleAttachment(Attachments.Silencer45ACP, model -> {
            GL11.glTranslatef(-0.25F, -1.1F, -3.8F);
            GL11.glScaled(1.5, 1.5, 1.5);
         })
         .withTextureName("AK12")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/mac10"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.4, 0.4, 0.4);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.35, 0.35, 0.35);
                  GL11.glTranslatef(0.0F, 0.8F, 0.0F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glTranslatef(-1.8F, -1.0F, 2.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.85F, 0.875F, -3.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.85F, 0.875F, -2.6F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(0.275F, 0.775F, -2.3F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(1.38F, -4.0F, 3.2F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.VectorMag, renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-2.025F, 0.65F, -0.55F);
               }, 300L, 60L), new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-2.025F, 0.65F, -0.55F);
               }, 300L, 200L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.85F, 0.875F, -2.5F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
               }, 400L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.85F, 0.875F, -2.5F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
               }, 120L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.85F, 0.875F, -2.5F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
               }, 130L, 150L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-2.025F, 0.65F, -0.55F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-2.025F, 0.65F, -0.55F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.VectorMag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 2.0F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.VectorMag,
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 2.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(0.275F, 0.775F, -2.66F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(1.38F, -1.18F, 3.3F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.35F, 1.725F, -1.225F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.675F, 0.5F, -0.525F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.325F, 0.675F);
               }, renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.325F, 0.675F);
               }, renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.55F, -0.925F, 0.725F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.5F, -0.825F, 0.525F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, -0.625F, -0.05F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.275F, -0.625F, -0.05F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, -0.625F, -0.05F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.5F, -0.825F, 0.525F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.55F, -0.925F, 0.725F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               }, 250L, 50L))
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.325F, 0.675F);
               }, renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.525F, 0.12F);
               })
               .build()
         )
         .withSpawnEntityDamage(5.5F)
         .withSpawnEntityGravityVelocity(0.02F)
         .build();
   }
}
