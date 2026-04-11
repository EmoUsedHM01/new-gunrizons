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

public class SV98Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("SV98")
         .withFireRate(0.16F)
         .withEjectRoundRequired()
         .withEjectSpentRoundSound("L96BoltAction")
         .withRecoil(4.0F)
         .withMaxShots(1)
         .withShootSound("sv98")
         .withPumpTimeout(1000L)
         .withSilencedShootSound("RifleSilencer")
         .withReloadSound("BoltActionReload")
         .withUnloadSound("l96unload")
         .withReloadingTime(40L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> -5.1F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withShellCasingEjectEnabled(false)
         .withCreativeTab(NewGunrizonsMod.SnipersTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Sniper rifle", "Damage: 25", "Caliber: 7.62x54mm", "Magazines:", "10rnd 7.62x54mm Magazine", "Fire Rate: Bolt Action")
         )
         .withCompatibleAttachment(Magazines.SV98Mag, model -> {
            GL11.glScaled(1.55, 1.6, 1.6);
            GL11.glTranslatef(-0.28F, 0.51F, -0.97F);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.SV98Action, true, model -> GL11.glTranslatef(-0.08F, 0.01F, 0.02F))
         .withCompatibleAttachment(AuxiliaryAttachments.AKRail, true, model -> {
            GL11.glTranslatef(-0.22F, -1.36F, -2.6F);
            GL11.glScaled(0.7, 0.8, 0.7);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(-0.165F, -1.5F, -1.15F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(0.262F, -0.8F, -2.25F);
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
               GL11.glTranslatef(-0.25F, -1.68F, -2.98F);
               GL11.glScaled(0.82, 0.71, 0.7);
            } else if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.175F, -1.33F, -9.8F);
               GL11.glScaled(0.4, 0.5, 0.7);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.17F, -1.545F, -3.1F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
            GL11.glTranslatef(-0.36F, -1.44F, -1.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.235F, 1.16F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
            GL11.glTranslatef(-0.36F, -1.43F, -1.1F);
            GL11.glScaled(1.0, 1.0, 1.0);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.272F, 0.67F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.17F, -1.45F, -1.7F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.56F, -2.0F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
            GL11.glTranslatef(-0.315F, -1.47F, -1.3F);
            GL11.glScaled(0.8, 0.8, 0.8);
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
            GL11.glTranslatef(-0.19F, -1.15F, -1.4F);
            GL11.glScaled(0.45, 0.45, 0.45);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.15F, -1.035F, 1.513F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Bipod, model -> {
            GL11.glTranslatef(-0.2F, -0.3F, -5.85F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withTextureName("sv98")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/sv98"))
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
                  GL11.glScaled(0.45, 0.45, 0.45);
                  GL11.glTranslatef(-1.8F, -1.0F, 2.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.8F, 1.0F, -0.3F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonCustomPositioning(Magazines.SV98Mag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AKRail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, 0.25F);
                  GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 0.82F, 0.3F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.335F, 0.15F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.325F, 0.3F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.3F, 0.8F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.235F, 0.5F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.75F);
                  }
               })
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.SV98Action.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, -0.25F);
               }, 180L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, 0.1F);
               }, 170L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, 0.1F);
               }, 160L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, -0.25F);
               }, 180L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, -0.25F);
               }, 120L, 0L))
               .withFirstPersonCustomPositioningEjectSpentRound(
                  AuxiliaryAttachments.SV98Action.getRenderablePart(), new Transition(renderContext -> {}, 250L, 50L), new Transition(renderContext -> {
                     GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(-0.85F, 0.5F, 0.0F);
                  }, 250L, 300L), new Transition(renderContext -> {
                     GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(-0.85F, 0.5F, 1.05F);
                  }, 250L, 50L), new Transition(renderContext -> {
                     GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(-0.85F, 0.5F, 0.0F);
                  }, 250L, 50L), new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonCustomPositioningEjectSpentRound(
                  AuxiliaryAttachments.AKRail.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 300L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonCustomPositioningEjectSpentRound(
                  Magazines.SV98Mag.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.7F, 1.0F, -0.1F);
                  GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
               }, 300L, 60L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, 0.1F);
                  GL11.glRotatef(-3.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               }, 300L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.6F, 1.0F, 0.1F);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
               }, 350L, 120L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.75F, 0.725F, 0.025F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.75F, 0.725F, 0.025F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.SV98Mag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.SV98Mag,
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.AKRail.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AKRail.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.SV98Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.SV98Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 0.82F, -0.125F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.335F, 0.35F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.325F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.3F, 1.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.235F, 0.75F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.75F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.425F, 1.025F, -0.375F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.05F, 0.675F, 0.575F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 7.5F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.275F, -0.34F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 7.5F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.275F, -0.35F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.33F, -0.5F, 0.08F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.5F, -0.8F, 0.1F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.675F, 0.7F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.8F, 0.25F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.8F, 0.25F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               }, 250L, 50L))
               .withFirstPersonLeftHandPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 7.5F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.275F, -0.34F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 7.5F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.275F, -0.34F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 7.5F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.275F, -0.34F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 7.5F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.275F, -0.34F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 7.5F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.275F, -0.34F);
               }, 250L, 50L))
               .withFirstPersonRightHandPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.175F, -0.4F, -0.075F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.095F, -0.31F, -0.02F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.15F, -0.515F, -0.125F);
               }, 350L, 1050L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.095F, -0.31F, -0.02F);
               }, 350L, 1050L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.175F, -0.4F, -0.075F);
               }, 350L, 1050L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.8F, 0.25F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.675F, 0.7F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(25.0F)
         .withSpawnEntityGravityVelocity(0.0F)
         .build();
   }
}
