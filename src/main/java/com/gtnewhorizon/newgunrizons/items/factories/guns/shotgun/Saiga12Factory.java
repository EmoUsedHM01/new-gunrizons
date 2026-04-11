package com.gtnewhorizon.newgunrizons.items.factories.guns.shotgun;

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

public class Saiga12Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("Saiga12")
         .withFireRate(0.4F)
         .withRecoil(8.0F)
         .withMaxShots(1)
         .withShootSound("Saiga12")
         .withSilencedShootSound("ShotgunSilenced")
         .withReloadSound("AKReload")
         .withUnloadSound("akunload")
         .withReloadingTime(45L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.6F)
         .withFlashOffsetZ(() -> -2.1F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> -0.1F)
         .withInaccuracy(7.0F)
         .withPellets(11)
         .withCreativeTab(NewGunrizonsMod.ShotgunsTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Shotgun",
               "Damage per Pellet: 6.3",
               "Pellets per Shot: 11",
               "Cartridge: 12 Gauge Shotgun Shell",
               "Magazines:",
               "5rnd 12 Gauge Shell Magazine",
               "Fire Rate: Semi"
            )
         )
         .withCompatibleAttachment(Attachments.PSO1, (player, stack) -> {
            GL11.glTranslatef(0.14F, -0.9F, -1.1F);
            GL11.glScaled(1.2, 1.2, 1.2);
         }, model -> {
            if (JsonModel.is(model, "sight/pso1reticle")) {
               GL11.glTranslatef(-0.212F, -0.486F, 1.27F);
               GL11.glScaled(0.017, 0.017, 0.017);
            } else if (JsonModel.is(model, "sight/pso12")) {
               GL11.glTranslatef(-0.27F, -0.6F, 1.21F);
               GL11.glScaled(0.8, 0.8, 0.8);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.Saiga12sights, true, model -> {
            if (JsonModel.is(model, "sight/makarovrearsight")) {
               GL11.glTranslatef(-0.1393F, -1.3F, -2.85F);
               GL11.glScaled(0.2, 0.22, 0.2);
            } else if (JsonModel.is(model, "sight/makarovfrontsight")) {
               GL11.glTranslatef(-0.124F, -1.29F, -4.9F);
               GL11.glScaled(0.1, 0.1, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.OKP7, model -> {
            if (JsonModel.is(model, "sight/okp7")) {
               GL11.glTranslatef(-0.18F, -0.65F, -1.15F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/okp7reticle")) {
               GL11.glTranslatef(-0.07F, -1.45F, -1.5F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.Saiga12action, true, model -> {})
         .withCompatibleAttachment(Magazines.Saiga12mag, model -> {})
         .withCompatibleAttachment(Attachments.Silencer12Gauge, model -> {
            GL11.glTranslatef(-0.22F, -1.1F, -8.9F);
            GL11.glScaled(1.2, 1.2, 1.2);
         })
         .withTextureName("Saiga12")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/saiga12"))
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
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glTranslatef(-1.8F, -1.1F, 2.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.45F, 1.0F, -0.4F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.45F, 1.0F, -0.3F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.3F, 0.75F, 0.2F);
                  GL11.glRotatef(1.5F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.OKP7)) {
                     GL11.glTranslatef(-0.05F, 0.16F, -0.3F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                     GL11.glTranslatef(-0.013F, 0.2F, -0.4F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.Saiga12mag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.Saiga12sights.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.Saiga12sights.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.Saiga12sights.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.Saiga12action.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.Saiga12action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.Saiga12action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F)
               )
               .withFirstPersonPositioningCustomRecoiled(Magazines.Saiga12mag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.Saiga12mag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
               }, 350L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
               }, 300L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                  GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
               }, 100L, 130L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.0F, -0.3F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
               }, 400L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.0F, -0.15F);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
               }, 200L, 70L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.4F, 1.0F, -0.3F);
                  GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
               }, 180L, 0L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                  GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
               }, 200L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
               }, 100L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
               }, 100L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.Saiga12action.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.Saiga12action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.5F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.Saiga12sights.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.Saiga12sights.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.Saiga12mag, new Transition(renderContext -> {}, 250L, 1000L), new Transition(renderContext -> {
                     GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                     GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  }, 250L, 1000L), new Transition(renderContext -> {
                     GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                     GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  }, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.Saiga12mag,
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                     GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                     GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 0.75F, -0.15F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.OKP7)) {
                     GL11.glTranslatef(-0.05F, 0.16F, -0.3F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                     GL11.glTranslatef(-0.013F, 0.2F, -0.1F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.35F, 1.025F, -0.4F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.15F, 0.45F, 0.525F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glTranslatef(0.49F, 0.14F, -0.1F);
                  GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 1.0F, 1.0F, 0.0F);
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
                  GL11.glTranslatef(0.375F, -0.8F, 0.15F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.02F, -0.38F, 0.5F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.0F, -0.625F, 0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.6F, 0.225F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glTranslatef(0.5F, 0.15F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glTranslatef(0.5F, 0.15F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glTranslatef(0.5F, 0.15F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 250L, 0L))
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
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               }, 260L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.0F, -0.075F, 0.075F);
               }, 250L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.025F, -0.425F, 0.15F);
               }, 280L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.0F, -0.075F, 0.075F);
               }, 280L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.6F, 0.225F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.0F, -0.625F, 0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.02F, -0.38F, 0.5F);
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
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.44F, -0.5F, 0.08F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(6.3F)
         .withSpawnEntityGravityVelocity(0.0118F)
         .build();
   }
}
