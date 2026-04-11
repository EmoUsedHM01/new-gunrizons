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

public class M1A1ThompsonFactory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("M1A1Thompson")
         .withFireRate(0.75F)
         .withRecoil(3.0F)
         .withMaxShots(Integer.MAX_VALUE, 1)
         .withShootSound("M1A1Thompson")
         .withSilencedShootSound("silencer")
         .withReloadSound("m1a1reload")
         .withUnloadSound("type100unload")
         .withReloadingTime(50L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.3F)
         .withFlashOffsetZ(() -> 0.9F)
         .withFlashOffsetX(() -> -0.05F)
         .withFlashOffsetY(() -> 0.2F)
         .withInaccuracy(2.0F)
         .withShellCasingForwardOffset(0.23F)
         .withShellCasingVerticalOffset(-0.02F)
         .withCreativeTab(NewGunrizonsMod.SMGTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Submachine gun", "Damage: 5.8", "Caliber: .45 ACP", "Magazines:", ".45 ACP 30rnd Box Magazine", "Fire Rate: Auto")
         )
         .withCompatibleAttachment(Magazines.M1A1mag, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.04F, -1.216F, -1.65F);
               GL11.glScaled(0.0, 0.0, 0.0);
               GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.13F, -0.73F, -3.45F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.0F, -1.5F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(0.13F, -1.55F, -3.05F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(-0.2F, -1.46F, -1.585F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.155F, -1.74F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(0.26F, -1.55F, -2.35F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/p90iron")) {
               GL11.glTranslatef(0.26F, -1.55F, -2.35F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.05F, -0.8F, -3.4F);
               GL11.glScaled(0.15, 0.35, 0.3);
               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.Silencer45ACP, model -> {
            GL11.glTranslatef(-0.14F, -0.81F, -5.25F);
            GL11.glScaled(1.15, 1.15, 1.15);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.M1A1rearsight, true, model -> {
            if (JsonModel.is(model, "sight/m1a1rearsight")) {
               GL11.glTranslatef(-0.16F, -0.846F, 1.9F);
               GL11.glScaled(0.2, 0.175, 0.2);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.M1A1action, true, model -> GL11.glTranslatef(0.0F, 0.0F, 1.0F))
         .withTextureName("M1A1Thompson")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/m1a1thompson"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.35, 0.35, 0.35);
                  GL11.glTranslatef(1.0F, 0.8F, 0.0F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-1.4F, -0.2F, 1.5F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(-0.5F, 0.85F, -2.3F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(-0.5F, 0.85F, -2.1F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(0.224F, 0.525F, -2.0F);
                  GL11.glRotatef(-0.1F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.M1A1mag, renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.M1A1mag, renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M1A1mag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.M1A1action.getRenderablePart(), renderContext -> {
                  if (renderContext.getWeaponInstance().getAmmo() == 0) {
                     GL11.glTranslatef(0.0F, 0.0F, -1.0F);
                  }
               })
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.M1A1action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.M1A1action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
               }, 280L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(-37.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
               }, 300L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
               }, 100L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(-0.3F, 0.9F, -2.1F);
               }, 350L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(-0.3F, 0.9F, -1.6F);
               }, 200L, 100L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
               }, 150L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.425F, 0.45F, -0.6F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.M1A1mag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 1.0F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.M1A1mag,
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 1.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.M1A1action.getRenderablePart(),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.M1A1action.getRenderablePart(),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, -1.0F), 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(0.224F, 0.525F, -2.075F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(5.0, 5.0, 5.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.525F, 1.2F, -1.675F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(5.0, 5.0, 5.0);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.1F, 0.475F, -1.475F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.1F, -0.525F, 0.26F);
               }, renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               })
               .withFirstPersonHandPositioningRunning(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.1F, -0.525F, 0.26F);
               }, renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.1F, -0.525F, 0.26F);
               }, renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glTranslatef(1.5F, 0.1F, -0.2F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
               }, renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, -0.65F, 0.6F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.8F, 0.475F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.8F, 0.475F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.1F, -0.52F, -0.15F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.1F, -0.52F, -0.15F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.18F, -0.45F, 0.05F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.325F, -0.7F, -0.025F);
               }, 250L, 50L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.8F, 0.475F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.5F);
                  GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, -0.65F, 0.6F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.8F, 3.8F, 3.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.6F, 0.25F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(5.8F)
         .withSpawnEntityGravityVelocity(0.028F)
         .build();
   }
}
