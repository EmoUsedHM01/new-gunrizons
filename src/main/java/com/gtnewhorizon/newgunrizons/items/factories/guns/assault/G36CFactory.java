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

public class G36CFactory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("G36C")
         .withFireRate(0.7F)
         .withRecoil(3.2F)
         .withMaxShots(Integer.MAX_VALUE, 3, 1)
         .withShootSound("G36")
         .withSilencedShootSound("RifleSilencer")
         .withReloadSound("StandardReload")
         .withUnloadSound("Unload")
         .withReloadingTime(50L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.8F)
         .withFlashOffsetZ(() -> 0.0F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> -0.2F)
         .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Assault rifle/Carbine", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "30rnd 5.56x45mm NATO Magazine (Type 3)", "Fire Rate: Auto"
            )
         )
         .withCompatibleAttachment(Magazines.NATOG36Mag, model -> {
            GL11.glTranslatef(-0.37F, 0.8F, -1.7F);
            GL11.glScaled(1.25, 1.7, 1.7);
         })
         .withCompatibleAttachment(Magazines.NATODrum100, model -> {
            GL11.glTranslatef(-0.37F, 0.8F, -1.7F);
            GL11.glScaled(1.25, 1.6, 1.7);
            GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.G36Rail, true, model -> {
            GL11.glTranslatef(-0.31F, -2.0F, -2.52F);
            GL11.glScaled(0.67, 0.675, 0.665);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.G36Action, true, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.125F, -1.8F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(0.13F, -1.55F, -3.05F);
               GL11.glScaled(0.0, 0.0, 0.0);
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
               GL11.glTranslatef(-0.171F, -1.95F, -0.3F);
               GL11.glScaled(0.25, 0.25, 0.25);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.19F, -1.92F, -3.6F);
               GL11.glScaled(0.45, 0.45, 0.45);
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
               GL11.glTranslatef(0.14F, -1.74F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
            GL11.glTranslatef(-0.31F, -2.0F, -1.0F);
            GL11.glScaled(0.8, 0.8, 0.8);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.237F, -0.26F, 0.46F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
            GL11.glTranslatef(-0.21F, -1.64F, -1.4F);
            GL11.glScaled(0.55, 0.55, 0.55);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.15F, -1.035F, 1.513F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
            GL11.glTranslatef(-0.325F, -2.0F, -1.0F);
            GL11.glScaled(0.85, 0.85, 0.85);
         }, model -> {
            if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glTranslatef(0.237F, -0.272F, 0.67F);
               GL11.glScaled(0.05, 0.05, 0.05);
            }
         })
         .withCompatibleAttachment(Attachments.Reflex, model -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(-0.07F, -1.82F, -1.5F);
               GL11.glScaled(0.45, 0.45, 0.45);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -2.15F, -1.7F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Kobra, model -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(-0.05F, -1.87F, -1.5F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -2.165F, -1.7F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Holo2, model -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(-0.05F, -1.87F, -1.5F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.125F, -2.165F, -1.7F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Holographic2, model -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(-0.05F, -1.87F, -1.5F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.125F, -2.165F, -1.7F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.17F, -2.0F, -1.0F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.125F, -2.1F, -1.0F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.Grip2, model -> {
            GL11.glTranslatef(-0.2F, -0.35F, -4.0F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.StubbyGrip, model -> {
            GL11.glTranslatef(-0.2F, -0.35F, -4.0F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.VGrip, model -> {
            GL11.glTranslatef(-0.2F, -0.4F, -4.0F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
            GL11.glTranslatef(0.1F, -1.3F, -4.3F);
            GL11.glScaled(0.85, 0.85, 0.85);
         })
         .withCompatibleAttachment(Attachments.Laser, (p, s) -> {
            GL11.glTranslatef(0.1F, -1.3F, -4.3F);
            GL11.glScaled(0.85, 0.85, 0.85);
         })
         .withCompatibleAttachment(Attachments.Silencer556x45, model -> {
            GL11.glTranslatef(-0.2F, -1.45F, -6.4F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withTextureName("AK12")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/g36c"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
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
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glTranslatef(-0.3F, 0.8F, -0.3F);
                  GL11.glRotatef(3.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glTranslatef(-0.3F, 0.8F, -0.2F);
                  GL11.glRotatef(3.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 1.375F, 0.1F);
                  GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(-0.002F, 0.275F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.009F, 0.283F, 0.4F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(-0.0F, 0.29F, 0.5F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.225F, 0.2F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.24F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.24F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.24F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.19F, 0.1F);
                  }
               })
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.G36Action.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.G36Action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.5F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.G36Action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.5F)
               )
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.G36Rail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.G36Rail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.G36Rail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.NATOG36Mag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOG36Mag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.NATODrum100.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATODrum100.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(Magazines.NATOG36Mag, renderContext -> {})
               .withFirstPersonCustomPositioning(Magazines.NATODrum100, renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
               }, 250L, 500L), new Transition(renderContext -> {
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
               }, 250L, 20L), new Transition(renderContext -> {
                  GL11.glScalef(1.5F, 1.5F, 1.5F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glTranslatef(-0.3F, 0.85F, 0.3F);
               }, 500L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(1.5F, 1.5F, 1.5F);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glTranslatef(-0.3F, 0.85F, 0.3F);
               }, 70L, 0L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.G36Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.G36Action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.5F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.NATOG36Mag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 1.5F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATOG36Mag,
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 1.5F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.G36Rail.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.G36Rail.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.NATODrum100,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.5F, 0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATODrum100,
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.5F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.35F, 1.375F, 0.025F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(-0.002F, 0.275F, 0.6F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.009F, 0.283F, 0.4F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(-0.0F, 0.29F, 0.5F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.225F, 0.2F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.24F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.24F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.24F, 0.1F);
                  }

                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.19F, 0.1F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.45F, 1.175F, -0.125F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.25F, 0.8F, 0.7F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 5.5F);
                  GL11.glTranslatef(0.5F, 0.01F, -0.05F);
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
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glRotatef(110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(205.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-95.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.775F, -0.025F);
               }, renderContext -> {
                  GL11.glScalef(2.5F, 2.5F, 3.0F);
                  GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.625F, -0.575F, -0.175F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.8F, -0.075F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.175F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.15F, -0.45F, -0.075F);
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
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(1.8F, 1.8F, 2.5F);
                  GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.8F, -0.075F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.625F, -0.575F, -0.175F);
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
         .withSpawnEntityDamage(7.0F)
         .withSpawnEntityGravityVelocity(0.0118F)
         .build();
   }
}
