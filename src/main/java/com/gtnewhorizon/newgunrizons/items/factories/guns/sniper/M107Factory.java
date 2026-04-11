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

public class M107Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("M107")
         .withFireRate(0.5F)
         .withRecoil(4.0F)
         .withMaxShots(1)
         .withShootSound("M107")
         .withSilencedShootSound("RifleSilencer")
         .withReloadSound("m107reload")
         .withUnloadSound("akunload")
         .withReloadingTime(43L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> -5.0F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withCreativeTab(NewGunrizonsMod.SnipersTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Anti-materiel sniper rifle", "Damage: 50", "Caliber: .50 BMG", "Magazines:", "10rnd .50 BMG Magazine (Type 2)", "Fire Rate: Semi"
            )
         )
         .withCompatibleAttachment(Magazines.M107BMag, model -> {
            GL11.glTranslatef(-0.42F, 0.8F, -1.4F);
            GL11.glScaled(1.5, 1.5, 1.5);
         })
         .withCompatibleAttachment(Attachments.AKMIron, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(0.13F, -1.55F, -4.8F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(-0.2F, -1.8F, -5.0F);
               GL11.glScaled(0.5, 0.6, 0.2);
            } else if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(-0.16F, -1.65F, -0.3F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(0.26F, -1.55F, -2.35F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/p90iron")) {
               GL11.glTranslatef(0.26F, -1.55F, -2.35F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(0.14F, -1.56F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(-0.165F, -1.62F, -0.4F);
               GL11.glScaled(0.38, 0.38, 0.38);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.52F, -3.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.08F, -1.53F, -5.0F);
               GL11.glScaled(0.3, 0.7, 0.4);
               GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
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
            GL11.glTranslatef(-0.345F, -1.55F, -0.8F);
            GL11.glScaled(0.95, 0.95, 0.95);
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
            GL11.glTranslatef(-0.2F, -1.2F, -1.2F);
            GL11.glScaled(0.5, 0.5, 0.5);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.15F, -1.035F, 1.513F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
            GL11.glTranslatef(-0.36F, -1.53F, -0.8F);
            GL11.glScaled(1.0, 1.0, 1.0);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.272F, 0.67F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
            GL11.glTranslatef(-0.335F, -1.53F, -1.2F);
            GL11.glScaled(0.9, 0.9, 0.9);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.235F, 1.16F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.18F, -1.53F, -1.0F);
               GL11.glScaled(0.35, 0.35, 0.35);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.66F, -1.0F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.Bipod, model -> {
            GL11.glTranslatef(-0.16F, -0.4F, -5.0F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.Silencer50BMG, model -> {
            GL11.glTranslatef(-0.22F, -1.35F, -12.5F);
            GL11.glScaled(1.3, 1.3, 1.6);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.AUGRail, true, model -> {
            if (JsonModel.is(model, "misc/akrail")) {
               GL11.glTranslatef(-0.245F, -1.45F, -4.5F);
               GL11.glScaled(0.9, 0.9, 0.9);
            } else if (JsonModel.is(model, "misc/akrail2")) {
               GL11.glTranslatef(-0.245F, -1.45F, -6.25F);
               GL11.glScaled(0.9, 0.9, 0.9);
            } else if (JsonModel.is(model, "misc/akrail3")) {
               GL11.glTranslatef(-0.03F, -0.52F, -3.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "misc/akrail4")) {
               GL11.glTranslatef(-0.245F, -1.45F, -2.33F);
               GL11.glScaled(0.9, 0.9, 0.9);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.M107action, true, model -> GL11.glScaled(1.0, 1.0, 1.0))
         .withTextureName("M107")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/m107"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.3, 0.3, 0.3);
                  GL11.glTranslatef(0.8F, 0.8F, 0.0F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glTranslatef(-1.8F, -1.1F, 2.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.18F, -0.8F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.18F, -0.4F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.3505F, 1.125F, -0.3F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.125F, 0.7F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.036F, 0.8F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.5F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.12F, 0.55F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.M107BMag, renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M107BMag, renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.M107BMag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AUGRail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.AUGRail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.AUGRail.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.M107action.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.M107action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 2.0F)
               )
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.M107action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 2.0F)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
               }, 300L, 60L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
               }, 300L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.275F, -0.125F);
               }, 400L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.275F, -0.125F);
               }, 120L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.275F, -0.125F);
               }, 130L, 150L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.M107BMag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.M107BMag,
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.AUGRail.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AUGRail.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.M107action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.M107action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 2.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.3505F, 1.125F, -0.55F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.125F, 0.8F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.036F, 0.9F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.12F, 0.65F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.325F, 1.25F, 0.0F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.0F, 0.525F, 0.35F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 4.0F);
                  GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.9F, 0.5F, -0.4F);
                  GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 4.0F);
                  GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.675F, 0.325F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.1F, -0.475F, 0.075F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 4.0F);
                  GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 4.0F);
                  GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 10.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.175F, -0.225F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 10.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.5F, -0.225F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 10.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.175F, -0.225F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.1F, -0.475F, 0.075F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.675F, 0.325F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 4.0F);
                  GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 4.0F);
                  GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(50.0F)
         .build();
   }
}
