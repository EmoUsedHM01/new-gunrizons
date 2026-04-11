package com.gtnewhorizon.newgunrizons.items.factories.guns.sniper;

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

public class M1903A3Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("M1903A3")
         .withAmmoCapacity(5)
         .withFireRate(0.16F)
         .withIteratedLoad()
         .withEjectRoundRequired()
         .withEjectSpentRoundSound("springfieldboltaction")
         .withRecoil(6.0F)
         .withMaxShots(1)
         .withShootSound("springfield")
         .withPumpTimeout(1400L)
         .withReloadSound("springfieldboltback")
         .withAllReloadIterationsCompletedSound("springfieldboltforward")
         .withReloadIterationSound("loadbullet")
         .withReloadingTime(500L)
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
            stack -> Arrays.asList("Type: Bolt-action rifle", "Damage: 27", "Cartridge:", ".30-06 Springfield Bullet", "Fire Rate: Bolt Action")
         )
         .withCompatibleAttachment(AuxiliaryAttachments.SpringfieldAction, true, model -> {
            if (JsonModel.is(model, "action/springfieldaction")) {
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.SpringfieldRearSight, true, model -> {
            GL11.glTranslatef(-0.18F, -1.46F, -1.38F);
            GL11.glScaled(0.14, 0.14, 0.14);
         })
         .withCompatibleBullet(Bullets.BulletSpringfield3006, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.17F, -1.42F, 0.43F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(-0.11F, -1.29F, -9.7F);
               GL11.glScaled(0.5, 0.5, 1.2);
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
               GL11.glTranslatef(-0.27F, -1.58F, -6.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.22F, -1.94F, -1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.207F, -1.245F, -9.165F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.13F, -1.39F, -9.7F);
               GL11.glScaled(0.15, 0.3, 0.8);
               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withTextureName("M1903A3")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/springfield"))
               .withPrepareFirstLoadIterationAnimationDuration(1100)
               .withAllLoadIterationAnimationsCompletedDuration(1000)
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.32, 0.32, 0.32);
                  GL11.glTranslatef(1.0F, 0.8F, 0.0F);
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
                  GL11.glTranslatef(-0.6F, 1.1F, 0.1F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.6F, 1.15F, 0.5F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.39F, 0.92F, 0.7F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                  }
               })
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.SpringfieldAction.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioningLoadIterationCompleted(AuxiliaryAttachments.SpringfieldAction.getRenderablePart(), renderContext -> {
                  GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonCustomPositioningLoadIterationCompleted(AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningLoadIterationCompleted(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               })
               .withFirstPersonPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.175F, 0.425F);
               }, 200L, 10L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-9.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.175F, 0.46F);
               }, 210L, 10L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.175F, 0.8F);
               }, 200L, 20L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-9.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.175F, 0.425F);
               }, 220L, 10L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, 1.175F, 0.425F);
               }, 210L, 10L))
               .withFirstPersonCustomPositioningEjectSpentRound(
                  AuxiliaryAttachments.SpringfieldAction.getRenderablePart(), new Transition(renderContext -> {}, 250L, 50L), new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 300L), new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 0L), new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 0L), new Transition(renderContext -> {}, 250L, 0L)
               )
               .withFirstPersonCustomPositioningEjectSpentRound(
                  AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 300L),
                  new Transition(renderContext -> {}, 250L, 0L),
                  new Transition(renderContext -> {}, 250L, 0L),
                  new Transition(renderContext -> {}, 250L, 0L)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.6F, 1.2F, 0.1F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
               }, 400L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 400L, 40L))
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.SpringfieldAction.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 50L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 50L),
                  new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 50L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonPositioningLoadIteration(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 200L, 20L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 180L, 20L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.25F, 0.525F);
               }, 210L, 20L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.25F, 0.525F);
               }, 180L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 220L, 20L))
               .withFirstPersonCustomPositioningLoadIteration(AuxiliaryAttachments.SpringfieldAction.getRenderablePart(), new Transition(renderContext -> {
                  GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 50L))
               .withFirstPersonCustomPositioningLoadIteration(
                  AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 300L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(41.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.525F);
               }, 250L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.2F, 0.4F);
               }, 250L, 0L))
               .withFirstPersonCustomPositioningAllLoadIterationsCompleted(
                  AuxiliaryAttachments.SpringfieldAction.getRenderablePart(), new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 50L), new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.5F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 50L), new Transition(renderContext -> {
                     GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                     GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 50L), new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonCustomPositioningAllLoadIterationsCompleted(
                  AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L),
                  new Transition(renderContext -> {}, 250L, 50L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.39F, 0.92F, 0.4F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 1.1F, 0.8F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.2F, 0.55F, 1.3F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.275F, -0.3F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.425F, -0.05F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.425F, -0.05F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.7F, -0.36F, 0.13F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.205F, -0.325F, 0.0F);
               })
               .withFirstPersonHandPositioningRunning(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.205F, -0.325F, 0.0F);
               })
               .withFirstPersonHandPositioningLoadIterationCompleted(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, renderContext -> {})
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L))
               .withFirstPersonLeftHandPositioningAllLoadIterationsCompleted(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 1000L))
               .withFirstPersonRightHandPositioningAllLoadIterationsCompleted(
                  new Transition(renderContext -> {}, 250L, 1000L), new Transition(renderContext -> {
                     GL11.glScalef(3.0F, 3.0F, 3.0F);
                     GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                     GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(0.05F, -0.2F, -0.15F);
                  }, 250L, 1000L), new Transition(renderContext -> {
                     GL11.glScalef(3.0F, 3.0F, 3.0F);
                     GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                     GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(0.125F, -0.125F, -0.2F);
                  }, 250L, 1000L), new Transition(renderContext -> {
                     GL11.glScalef(3.0F, 3.0F, 3.0F);
                     GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                     GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(0.4F, -0.225F, -0.2F);
                  }, 250L, 1000L)
               )
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {}, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.225F, -0.2F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.125F, -0.2F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.2F, -0.15F);
               }, 250L, 1000L), new Transition(renderContext -> {}, 250L, 1000L))
               .withFirstPersonLeftHandPositioningLoadIteration(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningLoadIteration(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.1F, -0.15F, 0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-130.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, -0.125F, 0.225F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-130.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.2F, -0.125F, 0.225F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.1F, -0.15F, 0.425F);
               }, 50L, 200L), new Transition(renderContext -> {}, 50L, 200L))
               .withFirstPersonLeftHandPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 7.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.35F, -0.425F);
               }, 250L, 50L))
               .withFirstPersonRightHandPositioningEjectSpentRound(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.225F, -0.2F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.125F, -0.2F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.2F, -0.15F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.125F, -0.2F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.225F, -0.2F);
               }, 250L, 1000L))
               .build()
         )
         .withSpawnEntityDamage(27.0F)
         .withSpawnEntityGravityVelocity(0.0F)
         .build();
   }
}
