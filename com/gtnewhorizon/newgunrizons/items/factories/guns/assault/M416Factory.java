package com.gtnewhorizon.newgunrizons.items.factories.guns.assault;

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

public class M416Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("M416")
         .withFireRate(0.7F)
         .withRecoil(3.5F)
         .withMaxShots(Integer.MAX_VALUE, 3, 1)
         .withShootSound("m4a1")
         .withSilencedShootSound("ar15silenced")
         .withReloadSound("m4reload")
         .withUnloadSound("m4unload")
         .withReloadingTime(50L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> -0.4F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Carbine",
               "Damage: 7",
               "Caliber: 5.56x45mm NATO",
               "Magazines:",
               "30rnd 5.56x45mm NATO Magazine",
               "20rnd 5.56x45mm NATO Magazine",
               "40rnd 5.56x45mm NATO Magazine",
               "30rnd 5.56x45mm NATO PMAG Magazine",
               "100rnd 5.56x45mm NATO Beta-C Magazine",
               "Fire Rate: Auto"
            )
         )
         .withCompatibleAttachment(Magazines.NATOMag1, model -> {
            GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
            GL11.glScaled(1.15, 1.2, 1.15);
         })
         .withCompatibleAttachment(Magazines.NATO40rnd, model -> {
            GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
            GL11.glScaled(1.15, 1.2, 1.15);
         })
         .withCompatibleAttachment(Magazines.NATO20rnd, model -> {
            GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
            GL11.glScaled(1.15, 1.2, 1.15);
         })
         .withCompatibleAttachment(Magazines.NATOMag2, model -> {
            GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
            GL11.glScaled(1.15, 1.2, 1.15);
         })
         .withCompatibleAttachment(Magazines.NATODrum100, model -> {
            GL11.glTranslatef(-0.35F, 0.69F, -1.25F);
            GL11.glScaled(1.15, 1.2, 1.15);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.AR15Action, true, model -> {
            GL11.glTranslatef(-0.175F, -1.28F, -0.67F);
            GL11.glScaled(0.7, 0.4, 0.7);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.162F, -1.75F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(-0.055F, -1.35F, -4.05F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/p90iron")) {
               GL11.glTranslatef(-0.185F, -1.45F, -3.75F);
               GL11.glScaled(0.51, 0.45, 1.0);
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
               GL11.glTranslatef(-0.164F, -1.255F, -3.7F);
               GL11.glScaled(0.5, 0.4, 1.1);
               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.044F, -1.1F, -0.3F);
               GL11.glScaled(0.0, 0.0, 0.0);
               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.AKMIron, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(-0.16F, -1.5F, -0.3F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(0.255F, -1.55F, -2.25F);
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
               GL11.glTranslatef(-0.044F, -1.1F, -0.3F);
               GL11.glScaled(0.63, 1.0, 1.9);
               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(-0.107F, -1.37F, -0.25F);
               GL11.glScaled(0.35, 0.5, 0.37);
            } else if (JsonModel.is(model, "sight/mbusiron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
            GL11.glTranslatef(-0.265F, -1.41F, -0.3F);
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
            GL11.glTranslatef(-0.17F, -1.14F, -0.45F);
            GL11.glScaled(0.35, 0.35, 0.35);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.15F, -1.035F, 1.513F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
            GL11.glTranslatef(-0.3F, -1.38F, -0.3F);
            GL11.glScaled(0.75, 0.75, 0.75);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.272F, 0.67F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
            GL11.glTranslatef(-0.3F, -1.38F, -0.5F);
            GL11.glScaled(0.75, 0.75, 0.75);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.235F, 1.16F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Reflex, model -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(-0.072F, -1.25F, -0.9F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.53F, -0.9F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.Holo2, model -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
               GL11.glScaled(0.55, 0.55, 0.55);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.12F, -1.545F, -0.5F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.Holographic2, model -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
               GL11.glScaled(0.55, 0.55, 0.55);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.12F, -1.55F, -0.5F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.Kobra, model -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
               GL11.glScaled(0.55, 0.55, 0.55);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.12F, -1.53F, -1.05F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.17F, -1.42F, -0.6F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.53F, -0.7F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.Grip2, model -> {
            GL11.glTranslatef(-0.2F, -0.41F, -2.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.StubbyGrip, model -> {
            GL11.glTranslatef(-0.2F, -0.41F, -2.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.Grip, model -> {
            GL11.glTranslatef(-0.2F, -0.35F, -2.9F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.VGrip, model -> {
            GL11.glTranslatef(-0.2F, -0.41F, -2.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.Bipod, model -> {
            GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
            GL11.glTranslatef(0.05F, -1.18F, -3.2F);
            GL11.glScaled(0.7, 0.7, 0.7);
         })
         .withCompatibleAttachment(Attachments.Laser, (p, s) -> {
            GL11.glTranslatef(0.05F, -1.18F, -3.2F);
            GL11.glScaled(0.7, 0.7, 0.7);
         })
         .withCompatibleAttachment(Attachments.Silencer556x45, model -> {
            GL11.glTranslatef(-0.2F, -1.235F, -6.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withTextureName("AK12")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/m416"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.35, 0.35, 0.35);
                  GL11.glTranslatef(1.0F, 2.0F, -1.2F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-1.7F, -0.8F, 1.9F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.4F, 1.05F, -0.4F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.4F, 1.05F, -0.35F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 0.9F, -0.4F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.001F, 0.095F, 0.65F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(-0.003F, 0.03F, 0.45F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.001F, 0.12F, 0.5F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.001F, 0.12F, 0.25F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.NATOMag1, renderContext -> {})
               .withFirstPersonCustomPositioning(Magazines.NATO20rnd, renderContext -> {})
               .withFirstPersonCustomPositioning(Magazines.NATO40rnd, renderContext -> {})
               .withFirstPersonCustomPositioning(Magazines.NATOMag2, renderContext -> {})
               .withFirstPersonCustomPositioning(Magazines.NATODrum100, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AR15Action.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.65F, 0.95F, -0.275F);
               }, 350L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.65F, 0.95F, -0.275F);
               }, 300L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(32.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.65F, 0.92F, -0.275F);
               }, 100L, 130L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 0.95F, -0.275F);
               }, 290L, 10L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 0.95F, -0.275F);
               }, 310L, 0L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.5F, 0.85F, -0.3F);
                  GL11.glRotatef(13.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
               }, 300L, 50L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.2F, 1.0F, -0.3F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
               }, 170L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.24F, 1.0F, -0.3F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
               }, 170L, 0L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.NATOMag1,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.AR15Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AR15Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.NATO40rnd,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.NATO20rnd,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATOMag1,
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATO40rnd,
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATO20rnd,
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.NATOMag2,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATOMag2,
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.NATODrum100,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATODrum100,
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                     GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(-0.01F, 0.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 0.9F, -0.525F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.001F, 0.095F, 0.75F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(-0.003F, 0.03F, 0.55F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.001F, 0.12F, 0.5F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.001F, 0.12F, 0.25F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.5F, 1.6F, -0.1F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.75F, 0.675F, 0.25F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glTranslatef(0.48F, 0.08F, -0.05F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(2.2F, 2.2F, 2.2F);
                  GL11.glTranslatef(1.0F, 0.4F, -0.4F);
                  GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.0F, -0.1F, 0.6F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.15F, -0.7F, 0.15F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.775F, 0.2F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.0F, -0.1F, 0.6F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.0F, -0.1F, 0.6F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.0F, -0.1F, 0.6F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(7.0F)
         .withSpawnEntityGravityVelocity(0.0118F)
         .build();
   }
}
