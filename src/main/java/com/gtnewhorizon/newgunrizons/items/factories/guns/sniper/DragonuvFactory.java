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

public class DragonuvFactory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("Dragunov")
         .withFireRate(0.6F)
         .withRecoil(3.0F)
         .withMaxShots(1)
         .withShootSound("Dragonuv")
         .withSilencedShootSound("RifleSilencer")
         .withReloadSound("AKReload")
         .withUnloadSound("akunload")
         .withReloadingTime(45L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.6F)
         .withFlashOffsetZ(() -> -5.0F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withCreativeTab(NewGunrizonsMod.SnipersTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Sniper rifle/Designated marksmen rifle", "Damage: 15", "Caliber: 7.62x54mm", "Magazines:", "11rnd 7.62x54mm Magazine", "Fire Rate: Auto"
            )
         )
         .withCompatibleAttachment(Attachments.PSO1, (player, stack) -> {
            GL11.glTranslatef(0.14F, -0.95F, -1.2F);
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
         .withCompatibleAttachment(AuxiliaryAttachments.AKIron, true, model -> {
            GL11.glTranslatef(-0.175F, -1.06F, -9.35F);
            GL11.glScaled(0.6, 0.55, 0.5);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.AKaction, true, model -> {
            GL11.glTranslatef(0.0F, 0.0F, -0.3F);
            GL11.glScaled(1.0, 1.0, 1.05);
         })
         .withCompatibleAttachment(Magazines.DragunovMag, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(-0.183F, -1.32F, -9.35F);
               GL11.glScaled(0.55, 0.55, 0.68);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(-0.25F, -1.65F, -3.6F);
               GL11.glScaled(0.8, 0.7, 0.6);
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
         .withCompatibleAttachment(Attachments.Silencer762x54, model -> {
            GL11.glTranslatef(-0.2F, -1.06F, -11.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withTextureName("Dragunov")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/dragunov"))
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
                  GL11.glTranslatef(-0.35F, 0.975F, -0.3F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glTranslatef(0.805F, -0.423F, 0.7F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                     GL11.glTranslatef(-0.007F, 0.222F, 0.4F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.DragunovMag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AKIron.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AKaction.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.AKaction.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.AKaction.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F)
               )
               .withFirstPersonPositioningCustomRecoiled(Magazines.DragunovMag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.DragunovMag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.AKIron.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.AKIron.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.425F, 0.6F);
               }, 300L, 60L), new Transition(renderContext -> {
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.425F, 0.6F);
               }, 300L, 200L), new Transition(renderContext -> {
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.425F, 0.6F);
               }, 400L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.425F, 0.6F);
               }, 120L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.425F, 0.6F);
               }, 130L, 150L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.425F, 0.6F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.425F, 0.6F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.DragunovMag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 2.0F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.DragunovMag,
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 2.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.AKaction.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AKaction.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.AKIron.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AKIron.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glTranslatef(0.805F, -0.423F, 0.625F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                     GL11.glTranslatef(-0.007F, 0.222F, 0.4F);
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
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
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
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.15F, -0.05F);
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
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
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
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.75F, 0.05F, 0.575F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.375F, -0.1F, 0.525F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.275F, 0.175F, 0.15F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.275F, -0.1F, 0.35F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.275F, 0.175F, 0.15F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 7.0F);
                  GL11.glTranslatef(0.5F, 0.1F, -0.1F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.375F, -0.1F, 0.525F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.75F, 0.05F, 0.575F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(15.0F)
         .build();
   }
}
