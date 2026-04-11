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

public class KrissVectorFactory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("KrissVector")
         .withFireRate(0.8F)
         .withRecoil(3.0F)
         .withMaxShots(Integer.MAX_VALUE, 2, 1)
         .withShootSound("Vector")
         .withSilencedShootSound("VectorSilenced")
         .withReloadSound("vectorreload")
         .withUnloadSound("m4unload")
         .withReloadingTime(45L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> 0.5F)
         .withFlashOffsetX(() -> 0.05F)
         .withFlashOffsetY(() -> 0.2F)
         .withInaccuracy(2.0F)
         .withCreativeTab(NewGunrizonsMod.SMGTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Submachine gun", "Damage: 6.8", "Caliber: .45 ACP", "Magazines:", "26rnd .45 ACP Magazine", "Fire Rate: Auto")
         )
         .withCompatibleAttachment(Magazines.VectorMag, model -> {
            GL11.glTranslatef(-0.43F, 0.8F, 0.2F);
            GL11.glScaled(1.5, 1.5, 1.5);
            GL11.glRotatef(17.0F, 1.0F, 0.0F, 0.0F);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.6F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(-0.205F, -1.38F, -3.75F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(0.092F, -1.75F, -0.85F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(-0.19F, -1.34F, -0.2F);
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
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.AKMIron, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.6F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(-0.22F, -1.38F, -3.75F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(0.092F, -1.75F, -0.85F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
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
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mbusiron")) {
               GL11.glTranslatef(-0.2F, -1.42F, -0.2F);
               GL11.glScaled(0.7, 0.65, 0.7);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.19F, -1.23F, -0.6F);
               GL11.glScaled(0.45, 0.45, 0.45);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.4F, -0.8F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
            GL11.glTranslatef(-0.36F, -1.22F, -0.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         }, model -> {
            if (JsonModel.is(model, "sight/acogscope2")) {
               GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/acogreticle")) {
               GL11.glTranslatef(0.243F, -0.23F, 0.68F);
               GL11.glScaled(0.03, 0.03, 0.03);
            }
         })
         .withCompatibleAttachment(Attachments.Reflex, model -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(-0.05F, -1.0F, -1.5F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.405F, -1.6F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Holo2, model -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(-0.02F, -1.05F, -1.5F);
               GL11.glScaled(0.8, 0.8, 0.8);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.124F, -1.42F, -1.6F);
               GL11.glScaled(0.08, 0.08, 0.08);
            }
         })
         .withCompatibleAttachment(Attachments.Holographic2, model -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(-0.02F, -1.05F, -1.5F);
               GL11.glScaled(0.8, 0.8, 0.8);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.124F, -1.42F, -1.6F);
               GL11.glScaled(0.08, 0.08, 0.08);
            }
         })
         .withCompatibleAttachment(Attachments.Kobra, model -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(-0.025F, -1.05F, -0.9F);
               GL11.glScaled(0.8, 0.8, 0.8);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.405F, -1.6F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Silencer45ACP, model -> {
            GL11.glTranslatef(-0.245F, -0.47F, -6.7F);
            GL11.glScaled(1.5, 1.5, 1.5);
         })
         .withCompatibleAttachment(Attachments.StubbyGrip, model -> {
            GL11.glTranslatef(-0.245F, 0.83F, -3.5F);
            GL11.glScaled(1.5, 1.5, 1.5);
         })
         .withTextureName("KrissVector")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/krissvector"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.3, 0.3, 0.3);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.24, 0.24, 0.24);
                  GL11.glTranslatef(1.0F, 1.5F, -1.5F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.45, 0.45, 0.45);
                  GL11.glTranslatef(-2.1F, -1.2F, 2.2F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.55F, 0.925F, -1.225F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.55F, 0.95F, -0.95F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(0.287F, 1.04F, -0.6F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.005F, 0.04F, 0.65F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.VectorMag, renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(-0.8F, 1.1F, -1.225F);
               }, 270L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(-0.8F, 1.1F, -1.225F);
               }, 400L, 200L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(-0.8F, 1.1F, -1.1F);
               }, 100L, 130L), new Transition(renderContext -> {
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(-0.6F, 1.2F, -1.0F);
               }, 500L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(-0.6F, 1.2F, -0.7F);
               }, 200L, 70L), new Transition(renderContext -> {
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(-0.6F, 1.2F, -0.9F);
               }, 180L, 0L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glTranslatef(0.28F, -0.22F, -0.2F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.28F, -0.22F, -0.2F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.VectorMag, new Transition(renderContext -> {}, 250L, 1000L), new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.VectorMag,
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.7F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(0.287F, 1.04F, -0.95F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.005F, 0.04F, 0.9F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(1.5F, 1.5F, 1.5F);
                  GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, 0.5F, -0.7F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(1.5F, 1.5F, 1.5F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.925F, 0.275F, 0.125F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.225F, 0.35F);
               }, renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glTranslatef(1.0F, 0.5F, -0.4F);
                  GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
               }, renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.025F, -0.3F, 0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.05F, -0.3F, 0.45F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.475F, 0.25F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.075F, -0.55F, 0.475F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.0F, -0.675F, 0.175F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 5.0F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.075F, -0.55F, 0.475F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.3F, 0.8F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.45F, -0.525F, 0.15F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(6.8F)
         .withSpawnEntityGravityVelocity(0.0118F)
         .build();
   }
}
