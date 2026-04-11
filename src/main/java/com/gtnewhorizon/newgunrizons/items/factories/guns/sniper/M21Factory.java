package com.gtnewhorizon.newgunrizons.items.factories.guns.sniper;

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

public class M21Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("M21")
         .withFireRate(0.4F)
         .withRecoil(2.0F)
         .withMaxShots(1)
         .withShootSound("M21")
         .withSilencedShootSound("RifleSilencer")
         .withReloadSound("m14reload")
         .withUnloadSound("Unload")
         .withReloadingTime(45L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> -4.0F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withCreativeTab(NewGunrizonsMod.SnipersTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Sniper", "Damage: 14", "Caliber: 7.62x51mm NATO", "Magazines:", "21rnd 7.62x51mm NATO Magazine", "Fire Rate: Semi")
         )
         .withCompatibleAttachment(Magazines.M14DMRMag, model -> {
            GL11.glTranslatef(-0.4F, 0.9F, -2.1F);
            GL11.glScaled(1.4, 1.5, 1.5);
            GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(-0.215F, -1.58F, -1.34F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(0.129F, -1.63F, -5.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.163F, -1.36F, -8.512F);
               GL11.glScaled(0.36, 0.36, 0.7);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(0.092F, -1.91F, -0.9F);
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
            } else if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.165F, -1.36F, -1.4F);
               GL11.glScaled(0.23, 0.23, 0.23);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.177F, -1.65F, 1.4F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.Leupold, (player, stack) -> {
            GL11.glTranslatef(-0.157F, -1.22F, -2.85F);
            GL11.glScaled(0.55, 0.55, 0.55);
         }, model -> {
            if (JsonModel.is(model, "sight/leupoldreticle")) {
               GL11.glTranslatef(0.076F, -0.52F, 4.0251F);
               GL11.glScaled(0.09, 0.09, 0.09);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.M14Action, true, model -> {
            if (JsonModel.is(model, "action/m14action")) {
               GL11.glTranslatef(0.0F, 0.01F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.M14Action2, true, model -> {
            if (JsonModel.is(model, "action/m14action2")) {
               GL11.glTranslatef(0.0F, 0.02F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withCompatibleAttachment(Attachments.Silencer762x51, model -> {
            GL11.glTranslatef(-0.2F, -1.3F, -10.63F);
            GL11.glScaled(1.0, 1.0, 1.2);
         })
         .withTextureName("M21")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/m21"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.35, 0.35, 0.35);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.28, 0.28, 0.28);
                  GL11.glTranslatef(1.0F, 2.0F, -1.2F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.47, 0.47, 0.47);
                  GL11.glTranslatef(-2.0F, -1.6F, 2.2F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(2.0F, 2.0F, 2.0F);
                  GL11.glTranslatef(-0.45F, 0.7F, -0.1F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(2.0F, 2.0F, 2.0F);
                  GL11.glTranslatef(-0.45F, 0.7F, 0.15F);
                  GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 0.8F, 0.65F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Leupold)) {
                     GL11.glTranslatef(-0.008F, 0.26F, 0.07F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.M14DMRMag, renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.M14DMRMag, renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M14DMRMag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.M14Action.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.M14Action2.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.M14Action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.2F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.M14Action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.2F)
               )
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.M14Action2.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.34F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.M14Action2.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.34F)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.25F, 0.625F, -0.025F);
               }, 350L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.25F, 0.625F, -0.025F);
               }, 450L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.525F, 1.225F, 0.2F);
               }, 350L, 160L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.525F, 1.225F, 0.2F);
               }, 200L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.525F, 1.225F, 0.2F);
               }, 230L, 50L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.25F, 0.625F, -0.025F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.25F, 0.625F, -0.025F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.M14DMRMag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.M14DMRMag,
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.M14Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.M14Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.2F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.M14Action2.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.M14Action2.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.34F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 0.8F, 0.4F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Leupold)) {
                     GL11.glTranslatef(-0.008F, 0.26F, 0.2F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.0F);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.4F, 0.9F, 0.15F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.0F);
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.05F, 0.575F, 0.875F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 8.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.3F, 0.25F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.5F, 0.075F);
               })
               .withFirstPersonHandPositioningRunning(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 8.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.3F, 0.25F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.325F, -0.425F, 0.025F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 8.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.3F, 0.25F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.5F, 0.075F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.375F, -0.8F, 0.15F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.5F, 0.075F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.525F, -0.7F, 0.1F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.225F, -0.675F, 0.1F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.5F, 0.075F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.5F, 0.075F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.075F, 0.05F, 0.225F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.075F, -0.1F, 0.225F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.075F, 0.05F, 0.225F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.225F, -0.675F, 0.1F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.525F, -0.7F, 0.1F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.5F, 0.075F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.5F, 0.075F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(14.0F)
         .build();
   }
}
