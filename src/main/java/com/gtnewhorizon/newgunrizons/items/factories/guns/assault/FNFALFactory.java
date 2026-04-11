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

public class FNFALFactory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("FNFAL")
         .withFireRate(0.5F)
         .withRecoil(2.0F)
         .withMaxShots(1, Integer.MAX_VALUE)
         .withShootSound("FNFAL")
         .withSilencedShootSound("RifleSilencer")
         .withReloadSound("StandardReload")
         .withUnloadSound("Unload")
         .withReloadingTime(50L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> -0.9F)
         .withFlashOffsetX(() -> 0.3F)
         .withFlashOffsetY(() -> 0.0F)
         .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Battle rifle", "Damage: 13", "Caliber: 7.62x51mm NATO", "Magazines:", "20rnd 7.62x51mm NATO Magazine", "Fire Rate: Semi"
            )
         )
         .withCompatibleAttachment(Magazines.FALMag, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(0.129F, -1.62F, -2.08F);
               GL11.glScaled(0.65, 0.65, 0.65);
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
               GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.177F, -1.65F, 1.4F);
               GL11.glScaled(0.2, 0.2, 0.2);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
            GL11.glTranslatef(0.055F, -1.65F, 0.6F);
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
         .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
            GL11.glTranslatef(0.055F, -1.65F, 0.6F);
            GL11.glScaled(0.6, 0.6, 0.6);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.272F, 0.67F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
            GL11.glTranslatef(0.055F, -1.61F, 0.4F);
            GL11.glScaled(0.6, 0.6, 0.6);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.235F, 1.16F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Reflex, model -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(0.27F, -1.48F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(0.202F, -1.8F, 0.0F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Kobra, model -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(0.27F, -1.55F, 0.5F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(0.202F, -1.75F, 0.1F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Holo2, model -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(0.264F, -1.53F, 0.2F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(0.202F, -1.76F, 0.3F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Holographic2, model -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(0.264F, -1.53F, 0.2F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(0.202F, -1.76F, 0.3F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Grip2, model -> {
            GL11.glTranslatef(0.135F, -0.8F, -1.25F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.StubbyGrip, model -> {
            GL11.glTranslatef(0.135F, -0.8F, -1.25F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.Grip, model -> {
            GL11.glTranslatef(0.135F, -0.8F, -1.25F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.VGrip, model -> {
            GL11.glTranslatef(0.135F, -0.8F, -1.25F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.Bipod, model -> {
            GL11.glTranslatef(0.135F, -0.8F, -1.25F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
            GL11.glTranslatef(0.2F, -1.5F, -1.25F);
            GL11.glScaled(0.7, 0.7, 0.7);
         })
         .withCompatibleAttachment(Attachments.Silencer762x51, model -> {
            GL11.glTranslatef(0.107F, -1.45F, -4.8F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withTextureName("AK12")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/fnfal"))
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
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glTranslatef(0.35F, -0.3F, -0.25F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glTranslatef(0.35F, -0.3F, -0.25F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 1.0F);
                  GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glTranslatef(-0.05F, -0.3F, -0.25F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(0.137F, -1.055F, 1.17F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(-0.005F, 0.1315F, 0.75F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.148F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(-0.01F, 0.18F, 0.2F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(-0.01F, 0.125F, 0.8F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.105F, 1.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(1.369F, -1.23F, 3.0F);
                  } else {
                     GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.FALMag, renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
               }, 250L, 500L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
               }, 250L, 20L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.3F, -0.28F, -0.15F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.3F, -0.8F, 0.8F);
               }, 250L, 0L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningReloading(
                  Magazines.FALMag,
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(Magazines.FALMag, new Transition(renderContext -> {
                  GL11.glTranslatef(0.2F, 0.5F, -0.2F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glTranslatef(1.3F, 0.5F, -0.8F);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 1000L))
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glTranslatef(-0.05F, -0.3F, -0.25F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(0.137F, -1.055F, 1.14F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(-0.005F, 0.1315F, 0.75F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.148F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(-0.01F, 0.18F, 0.2F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(-0.01F, 0.125F, 0.8F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.105F, 1.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(1.369F, -1.23F, 3.0F);
                  } else {
                     GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(0.8, 0.8, 0.8);
                  GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
                  GL11.glTranslatef(0.5F, -0.35F, -1.0F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
                  GL11.glTranslatef(1.0F, -0.8F, -1.5F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(1.7F, 1.7F, 3.0F);
                  GL11.glTranslatef(0.65F, -0.35F, 0.37F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
               }, renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(2.2F, 2.2F, 2.2F);
                  GL11.glTranslatef(1.0F, 0.2F, 0.2F);
                  GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
               }, renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(1.7F, 1.7F, 3.0F);
                  GL11.glTranslatef(0.65F, -0.35F, 0.37F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.4F, -0.2F, 0.6F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(1.7F, 1.7F, 3.0F);
                  GL11.glTranslatef(0.65F, -0.2F, 0.37F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(1.7F, 1.7F, 3.0F);
                  GL11.glTranslatef(0.7F, 0.0F, 0.37F);
                  GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(13.0F)
         .withSpawnEntityGravityVelocity(0.0118F)
         .build();
   }
}
