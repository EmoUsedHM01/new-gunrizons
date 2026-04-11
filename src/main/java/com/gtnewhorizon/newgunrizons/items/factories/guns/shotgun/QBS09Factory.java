package com.gtnewhorizon.newgunrizons.items.factories.guns.shotgun;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.JsonModel;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import java.util.Arrays;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class QBS09Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("QBS09")
         .withAmmoCapacity(6)
         .withMaxBulletsPerReload(5)
         .withIteratedLoad()
         .withFireRate(0.3F)
         .withRecoil(9.0F)
         .withMaxShots(1)
         .withShootSound("QBS09")
         .withSilencedShootSound("ShotgunSilenced")
         .withReloadSound("drawweapon")
         .withReloadIterationSound("loadshell")
         .withReloadingTime(15L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withInaccuracy(10.0F)
         .withPellets(11)
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> 0.4F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.5F)
         .withShellCasingEjectEnabled(false)
         .withCreativeTab(NewGunrizonsMod.ShotgunsTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Combat shotgun", "Damage per Pellet: 5", "Pellets per Shot: 11", "Ammo: 12 Gauge Shotgun Shell", "Fire Rate: Semi")
         )
         .withCompatibleBullet(Bullets.ShotgunShell, model -> {})
         .withTextureName("QBS09")
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.161F, -1.61F, 0.3F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(0.24F, -1.53F, -1.9F);
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
               GL11.glTranslatef(-0.111F, -0.65F, 0.95F);
               GL11.glScaled(0.16, 0.16, 0.16);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.125F, -0.65F, -2.96F);
               GL11.glScaled(0.3, 0.3, 1.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.118F, -0.65F, -2.96F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(-0.145F, -0.92F, 1.0F);
               GL11.glScaled(0.4, 0.7, 0.55);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.Silencer12Gauge, model -> {
            GL11.glTranslatef(-0.19F, -0.6F, -6.5F);
            GL11.glScaled(1.4, 1.4, 1.4);
         })
         .withRenderer(new WeaponRenderer.Builder().withModel(new JsonModel("weapon/qbs09")).withEntityPositioning(itemStack -> {
            GL11.glScaled(0.5, 0.5, 0.5);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
         }).withInventoryPositioning(itemStack -> {
            GL11.glScaled(0.35, 0.35, 0.35);
            GL11.glTranslatef(1.0F, 0.8F, 0.0F);
            GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
         }).withThirdPersonPositioning(renderContext -> {
            GL11.glScaled(0.6, 0.6, 0.6);
            GL11.glTranslatef(-0.9F, 0.4F, 1.0F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
         }).withFirstPersonPositioningLoadIterationCompleted(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }).withFirstPersonPositioning(renderContext -> {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glTranslatef(-0.4F, 0.4F, -1.5F);
         }).withFirstPersonPositioningRecoiled(renderContext -> {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glTranslatef(-0.4F, 0.4F, -1.3F);
            GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
         }).withFirstPersonPositioningZoomingRecoiled(renderContext -> {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glTranslatef(0.252F, 0.21F, -1.0F);
            GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
               GL11.glTranslatef(0.0F, 0.195F, 0.8F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
               GL11.glTranslatef(0.0F, 0.185F, 0.8F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
               GL11.glTranslatef(0.0F, 0.15F, 0.5F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
               GL11.glTranslatef(0.0F, 0.17F, 0.5F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
               GL11.glTranslatef(0.0F, 0.17F, 0.5F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
               GL11.glTranslatef(0.0F, 0.16F, 0.5F);
            }
         }).withFirstPersonPositioningReloading(new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 250L, 50L)).withFirstPersonPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 230L, 50L)).withFirstPersonPositioningLoadIteration(new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 150L, 0L), new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 170L, 0L), new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 140L, 0L), new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 150L, 0L), new Transition(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.85F, -0.075F, -0.475F);
         }, 140L, 0L)).withFirstPersonPositioningZooming(renderContext -> {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glTranslatef(0.252F, 0.21F, -1.3F);
            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
               GL11.glTranslatef(0.0F, 0.195F, 1.0F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
               GL11.glTranslatef(0.0F, 0.185F, 1.0F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
               GL11.glTranslatef(0.0F, 0.15F, 0.5F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
               GL11.glTranslatef(0.0F, 0.17F, 0.5F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
               GL11.glTranslatef(0.0F, 0.17F, 0.5F);
            }

            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
               GL11.glTranslatef(0.0F, 0.16F, 0.5F);
            }
         }).withFirstPersonPositioningRunning(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.05F, 0.3F, -0.675F);
         }).withFirstPersonPositioningModifying(renderContext -> {
            GL11.glScalef(4.0F, 4.0F, 4.0F);
            GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.475F, -0.025F, -0.625F);
         }).withFirstPersonHandPositioning(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 6.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.175F, -0.525F, -0.3F);
         }, renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 2.5F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.575F, -0.825F, 0.275F);
         }).withFirstPersonHandPositioningLoadIterationCompleted(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.7F, -0.925F, 0.03F);
         }).withFirstPersonHandPositioningZooming(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-65.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.18F);
         }, renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 2.5F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.575F, -0.825F, 0.275F);
         }).withFirstPersonHandPositioningModifying(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 2.5F);
            GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.05F, 0.55F);
         }, renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 2.5F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.575F, -0.825F, 0.275F);
         }).withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, 250L, 50L)).withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.7F, -0.925F, 0.03F);
         }, 250L, 1000L), new Transition(renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.7F, -0.925F, 0.03F);
         }, 250L, 50L)).withFirstPersonLeftHandPositioningLoadIteration(new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 5.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.125F, -0.475F, -0.275F);
         }, 250L, 50L)).withFirstPersonRightHandPositioningLoadIteration(new Transition(renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.3F, -0.4F, 0.8F);
         }, 250L, 1000L), new Transition(renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.35F, -0.425F, 0.45F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.475F, -0.35F, 0.3F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.35F, -0.425F, 0.45F);
         }, 250L, 50L), new Transition(renderContext -> {
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.3F, -0.4F, 0.8F);
         }, 250L, 50L)).withFirstPersonLeftHandPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 6.5F);
            GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.175F, -0.525F, -0.3F);
         }, 250L, 50L)).withFirstPersonRightHandPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
            GL11.glScalef(2.5F, 2.5F, 2.5F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.575F, -0.825F, 0.275F);
         }, 250L, 1000L)).build())
         .withSpawnEntityDamage(5.0F)
         .withSpawnEntityGravityVelocity(0.8F)
         .build();
   }
}
