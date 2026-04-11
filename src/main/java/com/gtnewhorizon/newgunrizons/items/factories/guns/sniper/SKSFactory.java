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

public class SKSFactory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("SKS")
         .withFireRate(0.3F)
         .withRecoil(4.0F)
         .withAmmoCapacity(10)
         .withMaxShots(1)
         .withShootSound("sks")
         .withSilencedShootSound("AKsilenced")
         .withReloadSound("sksreload")
         .withUnloadSound("svt40unload")
         .withReloadingTime(45L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.4F)
         .withFlashOffsetZ(() -> -0.2F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.4F)
         .withCreativeTab(NewGunrizonsMod.SnipersTab)
         .withInformationProvider(stack -> Arrays.asList("Type: Semi-automatic Carbine", "Damage: 8", "Cartridge: 7.62x39mm", "Fire Rate: Semi"))
         .withCompatibleAttachment(Attachments.PUscope, (player, stack) -> {
            GL11.glTranslatef(0.4F, -1.15F, 0.55F);
            GL11.glScaled(1.0, 1.0, 1.0);
         }, model -> {
            if (JsonModel.is(model, "sight/puscope")) {
               GL11.glTranslatef(-0.508F, 0.46F, -0.55F);
               GL11.glScaled(0.22, 0.22, 0.22);
            } else if (JsonModel.is(model, "misc/pumount")) {
               GL11.glTranslatef(-0.508F, 0.46F, -0.55F);
               GL11.glScaled(0.22, 0.22, 0.22);
            } else if (JsonModel.is(model, "misc/svtmount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pureticle")) {
               GL11.glTranslatef(-0.5F, 0.23F, 0.82F);
               GL11.glScaled(0.03, 0.03, 0.03);
            }
         })
         .withCompatibleAttachment(Attachments.PSO1, (player, stack) -> {
            GL11.glTranslatef(0.07F, -0.6F, 0.4F);
            GL11.glScaled(0.65, 0.65, 0.65);
         }, model -> {
            if (JsonModel.is(model, "sight/pso1reticle")) {
               GL11.glTranslatef(-0.212F, -0.486F, 1.27F);
               GL11.glScaled(0.017, 0.017, 0.017);
            } else if (JsonModel.is(model, "sight/pso12")) {
               GL11.glTranslatef(-0.27F, -0.6F, 1.21F);
               GL11.glScaled(0.8, 0.8, 0.8);
            }
         })
         .withCompatibleAttachment(Attachments.OKP7, model -> {
            if (JsonModel.is(model, "sight/okp7")) {
               GL11.glTranslatef(-0.13F, -0.43F, 0.22F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/okp7reticle")) {
               GL11.glTranslatef(-0.07F, -0.9F, -0.0F);
               GL11.glScaled(0.04, 0.04, 0.04);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.AKpart, true, model -> {
            GL11.glTranslatef(-0.1F, -0.43F, -5.0F);
            GL11.glScaled(0.7, 0.7, 2.7);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.SKSaction, true, model -> {})
         .withCompatibleAttachment(Attachments.Silencer762x39, model -> {
            GL11.glTranslatef(-0.2F, -0.62F, -6.77F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleBullet(Bullets.Bullet762x39, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(-0.175F, -0.99F, -1.35F);
               GL11.glScaled(0.6, 0.6, 0.75);
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
               GL11.glTranslatef(-0.13F, -0.71F, -5.15F);
               GL11.glScaled(0.3, 0.35, 0.5);
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
         .withCompatibleAttachment(AuxiliaryAttachments.SKSmag1, true, model -> GL11.glTranslatef(-0.1F, 1.1F, 2.0F))
         .withCompatibleAttachment(AuxiliaryAttachments.SKSmag2, true, model -> GL11.glTranslatef(-0.1F, 1.1F, 2.0F))
         .withTextureName("sks")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/sks"))
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
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-1.0F, 0.3F, 1.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.45F, 0.63F, -1.5F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.45F, 0.63F, -1.3F);
                  GL11.glRotatef(-1.3F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(0.25F, 0.34F, -0.9F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                     GL11.glTranslatef(0.005F, 0.158F, -0.25F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                     GL11.glTranslatef(-0.014F, 0.162F, -0.05F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.OKP7)) {
                     GL11.glTranslatef(-0.011F, 0.147F, -0.5F);
                  }
               })
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AKpart.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.SKSaction.getRenderablePart(), renderContext -> {
                  if (renderContext.getWeaponInstance().getAmmo() == 0) {
                  }
               })
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.SKSaction.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.SKSaction.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F)
               )
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.AKpart.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.AKpart.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.SKSmag1.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.SKSmag1.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.SKSmag1.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.SKSmag2.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.SKSmag2.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.SKSmag2.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.3F, 0.5F, -1.0F);
                  GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
               }, 300L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.3F, 0.5F, -1.0F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
               }, 150L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.3F, 0.6F, -1.0F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
               }, 350L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.3F, 0.6F, -1.0F);
                  GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
               }, 330L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(39.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.3F, 0.6F, -1.0F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               }, 340L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.4F, 0.6F, -1.0F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               }, 300L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.4F, 0.67F, -1.0F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
               }, 310L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.4F, 0.67F, -1.0F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               }, 320L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glTranslatef(-0.3F, 0.6F, -1.1F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
               }, 80L, 0L))
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.SKSaction.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 250L, 200L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 250L, 200L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 250L, 200L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 250L, 200L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 250L, 200L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 250L, 200L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.5F), 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AKpart.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L),
                  new Transition(renderContext -> {}, 250L, 200L)
               )
               .withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.SKSmag1.getRenderablePart(), new Transition(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-1.2F, -0.9F, -3.0F);
                  GL11.glRotatef(17.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -1.5F, -3.2F);
                  GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -2.1F, -3.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -1.0F, -3.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -1.0F, -3.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -1.0F, -3.0F);
               }, 250L, 200L))
               .withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.SKSmag2.getRenderablePart(), new Transition(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-1.2F, -0.9F, -3.0F);
                  GL11.glRotatef(17.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -1.5F, -3.2F);
                  GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -2.1F, -3.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -2.1F, -3.07F);
                  GL11.glRotatef(2.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-0.08F, -2.1F, -3.07F);
                  GL11.glRotatef(2.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(0.6F, 0.6F, 0.6F);
                  GL11.glTranslatef(-8.5F, -5.0F, -2.0F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
               }, 250L, 200L))
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(4.0, 4.0, 4.0);
                  GL11.glTranslatef(0.25F, 0.34F, -1.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                     GL11.glTranslatef(0.005F, 0.158F, -0.2F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                     GL11.glTranslatef(-0.014F, 0.162F, 0.0F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.OKP7)) {
                     GL11.glTranslatef(-0.011F, 0.147F, -0.5F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.35F, 0.6F, -0.4F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.575F, 0.075F, -0.725F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.608F, -0.869F, 0.051F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.608F, -0.869F, 0.051F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.3F, -0.675F, 0.175F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.608F, -0.869F, 0.051F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 5.5F);
                  GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.08F, -0.475F, -0.28F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.225F, 0.05F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.225F, -0.35F, 0.05F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.25F, -0.725F, 0.325F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.05F, -0.275F, 0.1F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.3F, -0.1F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.125F, -0.3F, -0.1F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.075F, -0.35F, 0.1F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.225F, -0.35F, 0.05F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 2.5F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.225F, 0.05F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(8.0F)
         .withSpawnEntityGravityVelocity(0.0118F)
         .build();
   }
}
