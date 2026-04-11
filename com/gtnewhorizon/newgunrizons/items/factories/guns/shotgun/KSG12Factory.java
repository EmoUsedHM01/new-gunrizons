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

public class KSG12Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("KSG12")
         .withAmmoCapacity(15)
         .withMaxBulletsPerReload(7)
         .withFireRate(0.5F)
         .withEjectRoundRequired()
         .withIteratedLoad()
         .withEjectSpentRoundSound("KSG12Pump")
         .withPumpTimeout(800L)
         .withRecoil(9.0F)
         .withMaxShots(1)
         .withShootSound("KSG12")
         .withSilencedShootSound("ShotgunSilenced")
         .withReloadSound("drawweapon")
         .withReloadIterationSound("loadshell")
         .withReloadingTime(15L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withInaccuracy(10.0F)
         .withPellets(10)
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> 0.4F)
         .withFlashOffsetX(() -> 0.2F)
         .withFlashOffsetY(() -> 0.15F)
         .withShellCasingEjectEnabled(false)
         .withCreativeTab(NewGunrizonsMod.ShotgunsTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Bullpup pump-action shotgun", "Damage per Pellet: 5", "Pellets per Shot: 10", "Ammo: 12 Gauge Shotgun Shell", "Fire Rate: Pump-Action"
            )
         )
         .withCompatibleAttachment(AuxiliaryAttachments.KSGPump, true, model -> {})
         .withCompatibleBullet(Bullets.ShotgunShell, model -> {})
         .withCompatibleAttachment(Attachments.AKMIron, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.17F, -1.54F, 0.32F);
               GL11.glScaled(0.25, 0.27, 0.25);
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
               GL11.glTranslatef(0.12F, -1.75F, -1.85F);
               GL11.glScaled(0.5, 0.6, 0.2);
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
               GL11.glTranslatef(0.16F, -1.55F, -1.9F);
               GL11.glScaled(0.35, 0.35, 0.35);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mbusiron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
            GL11.glTranslatef(0.055F, -1.55F, -0.2F);
            GL11.glScaled(0.6, 0.6, 0.6);
         }, model -> {
            if (JsonModel.is(model, "sight/acogscope2")) {
               GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/acogreticle")) {
               GL11.glTranslatef(0.243F, -0.23F, 0.68F);
               GL11.glScaled(0.03, 0.03, 0.03);
            }
         })
         .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
            GL11.glTranslatef(0.15F, -1.3F, -0.2F);
            GL11.glScaled(0.3, 0.3, 0.3);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.15F, -1.035F, 1.513F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Reflex, model -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(0.25F, -1.38F, -0.6F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(0.2F, -1.62F, -0.6F);
               GL11.glScaled(0.12, 0.12, 0.12);
            }
         })
         .withCompatibleAttachment(Attachments.Holo2, model -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(0.264F, -1.4F, -0.3F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(0.202F, -1.64F, -0.15F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Holographic2, model -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(0.264F, -1.4F, -0.3F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(0.202F, -1.64F, -0.15F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Kobra, model -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(0.27F, -1.4F, -0.2F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(0.202F, -1.6F, -0.6F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(0.155F, -1.53F, -0.2F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(0.2F, -1.63F, -0.3F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.Silencer12Gauge, model -> {
            GL11.glTranslatef(0.107F, -1.32F, -4.0F);
            GL11.glScaled(1.3, 1.3, 1.3);
         })
         .withTextureName("AK12")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/ksg12"))
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
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-1.8F, 0.3F, 1.5F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningLoadIterationCompleted(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.625F, 1.225F, -1.55F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.55F, 1.225F, -1.3F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-3.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(0.05F, 1.175F, -1.1F);
                  GL11.glRotatef(-3.0F, 0.0F, 0.0F, 1.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.108F, 1.2F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.01F, 0.9F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                  }
               })
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.KSGPump.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioningLoadIterationCompleted(AuxiliaryAttachments.KSGPump.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.625F, 1.225F, -1.55F);
               }, 130L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.625F, 1.225F, -1.55F);
               }, 130L, 0L))
               .withFirstPersonCustomPositioningEjectSpentRound(
                  AuxiliaryAttachments.KSGPump.getRenderablePart(),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 170L, 50L),
                  new Transition(renderContext -> {}, 150L, 50L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.KSGPump.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonCustomPositioningAllLoadIterationsCompleted(
                  AuxiliaryAttachments.KSGPump.getRenderablePart(), new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonCustomPositioningLoadIteration(
                  AuxiliaryAttachments.KSGPump.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               }, 250L, 50L))
               .withFirstPersonPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.625F, 1.225F, -1.55F);
               }, 250L, 50L))
               .withFirstPersonPositioningLoadIteration(new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               }, 150L, 0L), new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               }, 160L, 0L), new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               }, 200L, 0L), new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               }, 170L, 0L), new Transition(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.575F, -1.375F);
               }, 170L, 0L))
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(-0.025F, 1.175F, -1.4F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.108F, 1.4F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.01F, 1.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.575F, 1.25F, -1.075F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.7F, 0.8F, -0.925F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.125F, -0.625F, -0.2F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(2.2F, 2.2F, 2.2F);
                  GL11.glTranslatef(1.0F, 1.0F, 0.2F);
                  GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               })
               .withFirstPersonHandPositioningLoadIterationCompleted(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L))
               .withFirstPersonLeftHandPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, -0.75F, -0.1F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.125F, -0.625F, -0.2F);
               }, 250L, 50L))
               .withFirstPersonRightHandPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L))
               .withFirstPersonLeftHandPositioningLoadIteration(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.45F, -1.125F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.65F, -0.9F, -0.225F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.45F, -1.125F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
               }, 250L, 50L))
               .withFirstPersonRightHandPositioningLoadIteration(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 50L))
               .withFirstPersonLeftHandPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.125F, -0.625F, -0.2F);
               }, 250L, 50L))
               .withFirstPersonRightHandPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.55F, -0.775F, 0.0F);
               }, 250L, 1000L))
               .build()
         )
         .withSpawnEntityDamage(5.0F)
         .withSpawnEntityGravityVelocity(0.8F)
         .build();
   }
}
