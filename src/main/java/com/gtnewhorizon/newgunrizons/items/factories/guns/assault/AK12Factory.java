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

public class AK12Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("AK12")
         .withFireRate(0.75F)
         .withRecoil(3.5F)
         .withMaxShots(Integer.MAX_VALUE, 3, 1)
         .withShootSound("AK12")
         .withSilencedShootSound("AKsilenced")
         .withReloadSound("akreload")
         .withUnloadSound("akunload")
         .withReloadingTime(43L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.6F)
         .withFlashOffsetZ(() -> -0.6F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Assault rifle", "Damage: 7.3", "Caliber: 5.45x39mm", "Magazines:", "31rnd 5.45x39mm Magazine", "Fire Rate: Auto")
         )
         .withCompatibleAttachment(Magazines.AK12Mag, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.21F, -1.94F, 4.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.18F, -1.25F, -4.1F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(-0.162F, -1.25F, -4.72F);
               GL11.glScaled(0.35, 0.35, 0.52);
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
               GL11.glTranslatef(-0.1F, -1.235F, -0.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.AK12IronSight, true, model -> {
            if (JsonModel.is(model, "sight/ak12ironsight")) {
               GL11.glTranslatef(-0.166F, -1.4F, -0.3F);
               GL11.glScaled(0.56, 0.56, 0.56);
            }
         })
         .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
            GL11.glTranslatef(-0.277F, -1.3F, -1.0F);
            GL11.glScaled(0.65, 0.65, 0.65);
         }, model -> {
            if (JsonModel.is(model, "sight/acogscope2")) {
               GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/acogreticle")) {
               GL11.glTranslatef(0.243F, -0.23F, 0.68F);
               GL11.glScaled(0.03, 0.03, 0.03);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.17F, -1.3F, -1.2F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.4F, -1.5F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
            GL11.glTranslatef(-0.31F, -1.3F, -1.0F);
            GL11.glScaled(0.8, 0.8, 0.8);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.272F, 0.67F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
            GL11.glTranslatef(-0.182F, -1.04F, -1.3F);
            GL11.glScaled(0.4, 0.4, 0.4);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.15F, -1.035F, 1.513F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
            GL11.glTranslatef(-0.31F, -1.3F, -1.5F);
            GL11.glScaled(0.8, 0.8, 0.8);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.235F, 1.16F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Reflex, model -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(-0.065F, -1.13F, -1.8F);
               GL11.glScaled(0.45, 0.45, 0.45);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.42F, -1.8F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Holo2, model -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(-0.055F, -1.18F, -1.4F);
               GL11.glScaled(0.54, 0.54, 0.54);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.125F, -1.435F, -1.3F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Holographic2, model -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(-0.055F, -1.18F, -1.4F);
               GL11.glScaled(0.54, 0.54, 0.54);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.125F, -1.435F, -1.3F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Kobra, model -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(-0.055F, -1.18F, -1.4F);
               GL11.glScaled(0.54, 0.54, 0.54);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -1.407F, -1.85F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Grip2, model -> {
            GL11.glTranslatef(-0.2F, -0.23F, -2.6F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.StubbyGrip, model -> {
            GL11.glTranslatef(-0.2F, -0.23F, -2.6F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.Grip, model -> {
            GL11.glTranslatef(-0.2F, -0.18F, -3.0F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.Bipod, model -> {
            GL11.glTranslatef(-0.2F, -0.23F, -3.0F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.VGrip, model -> {
            GL11.glTranslatef(-0.2F, -0.23F, -2.6F);
            GL11.glScaled(0.8, 0.8, 0.8);
         })
         .withCompatibleAttachment(Attachments.Silencer556x39, model -> {
            GL11.glTranslatef(-0.2F, -1.02F, -6.35F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.AK12action, true, model -> {})
         .withTextureName("AK12kal")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/ak12"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.32, 0.32, 0.32);
                  GL11.glTranslatef(1.0F, 1.8F, -1.0F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.6, 0.6, 0.6);
                  GL11.glTranslatef(-1.6F, -0.8F, 1.7F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.25F);
                  GL11.glRotatef(4.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.91F, -0.1F);
                  GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(5.5F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glTranslatef(0.4F, 2.3F, -1.0F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(1.5F, 0.0F, 0.0F, 1.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.185F, 0.85F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.185F, 0.85F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.133F, 1.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.12F, 1.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.14F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.AK12Mag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AK12action.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AK12IronSight.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.AK12action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.4F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.AK12action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.4F)
               )
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.AK12IronSight.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.AK12IronSight.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.AK12Mag, renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.AK12Mag, renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
               }, 350L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
               }, 300L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                  GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
               }, 100L, 130L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
               }, 400L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.2F);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
               }, 200L, 70L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.4F, 0.9F, -0.35F);
                  GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
               }, 180L, 0L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                  GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
               }, 200L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
               }, 100L, 0L), new Transition(renderContext -> {
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                  GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
               }, 100L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.AK12Mag, new Transition(renderContext -> {}, 250L, 1000L), new Transition(renderContext -> {
                     GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                     GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  }, 250L, 1000L), new Transition(renderContext -> {
                     GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                     GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  }, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.AK12Mag,
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
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.AK12action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AK12action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.4F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glTranslatef(0.0F, 2.3F, -1.5F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.0F, 0.185F, 0.95F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.0F, 0.185F, 0.95F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.0F, 0.133F, 1.25F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.14F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.0F, 0.12F, 1.1F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.0F);
                  GL11.glRotatef(12.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.025F, 0.6F, -0.05F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.0F);
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.725F, 0.4F, 0.4F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glTranslatef(0.47F, 0.07F, -0.0F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(2.2F, 2.2F, 2.2F);
                  GL11.glTranslatef(1.0F, 0.4F, -0.5F);
                  GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.075F, -0.35F, 0.5F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.15F, -0.625F, 0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.05F, -0.6F, 0.225F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.55F, 0.35F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.125F, -0.65F, 0.325F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.55F, 0.35F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.05F, -0.6F, 0.225F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.15F, -0.625F, 0.425F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.075F, -0.35F, 0.5F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(7.3F)
         .withSpawnEntityGravityVelocity(0.028F)
         .build();
   }
}
