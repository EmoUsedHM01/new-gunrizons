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

public class FamasF1Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("FamasF1")
         .withFireRate(0.75F)
         .withRecoil(2.0F)
         .withMaxShots(Integer.MAX_VALUE, 3, 1)
         .withShootSound("Famas")
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
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withShellCasingForwardOffset(-0.1F)
         .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Bullpup assault rifle", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "30rnd 5.56x45mm NATO Magazine (Type 2)", "Fire Rate: Auto"
            )
         )
         .withCompatibleAttachment(Magazines.NATOFamasMag, model -> {
            GL11.glTranslatef(-0.355F, 0.9F, 1.65F);
            GL11.glScaled(1.2, 1.5, 1.5);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.FamasCarryHandle, true, model -> {
            GL11.glTranslatef(-0.241F, -1.15F, -1.1F);
            GL11.glScaled(0.52, 0.55, 0.85);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.FamasBipod1, true, model -> {
            GL11.glTranslatef(-0.05F, -1.2F, -2.5F);
            GL11.glScaled(1.0, 1.0, 0.88);
            GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(1.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(1.8F, 0.0F, 1.0F, 0.0F);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.FamasBipod2, true, model -> {
            GL11.glTranslatef(-0.25F, -1.05F, -2.5F);
            GL11.glScaled(1.0, 1.0, 0.88);
            GL11.glRotatef(200.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-0.8F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(1.5F, 0.0F, 1.0F, 0.0F);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.FamasAction, true, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.AKRail, true, model -> {
            GL11.glTranslatef(-0.215F, -1.9F, -3.0F);
            GL11.glScaled(0.7, 1.2, 1.25);
         })
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(-0.3F, -1.5F, -0.3F);
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
               GL11.glTranslatef(-0.155F, -1.925F, -3.0F);
               GL11.glScaled(0.3, 0.3, 0.7);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(-0.23F, -2.22F, -0.17F);
               GL11.glScaled(0.7, 0.7, 0.3);
            } else if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.205F, -2.5F, -3.15F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(0.127F, -1.77F, -2.22F);
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
            GL11.glTranslatef(-0.297F, -2.0F, -0.7F);
            GL11.glScaled(0.75, 0.75, 0.75);
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
            GL11.glTranslatef(-0.195F, -1.65F, -0.7F);
            GL11.glScaled(0.5, 0.5, 0.5);
         }, model -> {
            if (JsonModel.is(model, "sight/acog2")) {
               GL11.glTranslatef(0.15F, -1.035F, 1.513F);
               GL11.glScaled(0.1, 0.1, 0.1);
            }
         })
         .withCompatibleAttachment(Attachments.Reflex, model -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(-0.07F, -1.825F, -1.1F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Holo2, model -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(-0.05F, -1.85F, -0.8F);
               GL11.glScaled(0.55, 0.55, 0.55);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Holographic2, model -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(-0.05F, -1.85F, -0.8F);
               GL11.glScaled(0.55, 0.55, 0.55);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.Kobra, model -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(-0.05F, -1.85F, -0.8F);
               GL11.glScaled(0.55, 0.55, 0.55);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
               GL11.glScaled(0.06, 0.06, 0.06);
            }
         })
         .withCompatibleAttachment(Attachments.MicroT1, model -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.165F, -2.0F, -0.6F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glTranslatef(-0.12F, -2.1F, -0.7F);
               GL11.glScaled(0.07, 0.07, 0.07);
            }
         })
         .withCompatibleAttachment(Attachments.Silencer556x45, model -> {
            GL11.glTranslatef(-0.2F, -1.11F, -5.5F);
            GL11.glScaled(1.0, 1.0, 1.0);
         })
         .withTextureName("AK12")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/famasf1"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.3, 0.3, 0.3);
                  GL11.glTranslatef(0.0F, 0.8F, 0.0F);
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
                  GL11.glScaled(2.0, 2.0, 2.0);
                  GL11.glTranslatef(-0.5F, 0.85F, -1.65F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(2.0, 2.0, 2.0);
                  GL11.glTranslatef(-0.5F, 0.85F, -1.5F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glTranslatef(0.58F, 0.76F, 0.1F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.005F, 0.275F, 1.0F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.005F, 0.25F, 0.5F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.19F, 0.5F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.19F, 0.4F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.NATOFamasMag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.AKRail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.AKRail.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.AKRail.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasAction.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.NATOFamasMag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOFamasMag.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.FamasAction.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.FamasAction.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.475F, -1.75F);
               }, 300L, 60L), new Transition(renderContext -> {
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.475F, -1.75F);
               }, 400L, 200L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, -0.1F, -0.125F);
               }, 400L, 100L), new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, -0.1F, -0.125F);
               }, 150L, 100L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.475F, -1.75F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.225F, -0.475F, -1.75F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(Magazines.NATOFamasMag, new Transition(renderContext -> {
                  GL11.glTranslatef(0.2F, 0.5F, -0.2F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.2F, 0.9F, -0.2F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L))
               .withFirstPersonCustomPositioningReloading(
                  Magazines.NATOFamasMag,
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.0F, 0.0F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.AKRail.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.AKRail.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.FamasAction.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.FamasAction.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.FamasBipod1.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.FamasBipod1.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.FamasBipod2.getRenderablePart(),
                  new Transition(renderContext -> {}, 500L, 1000L),
                  new Transition(renderContext -> {}, 500L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.FamasBipod2.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(1.5, 1.5, 1.5);
                  GL11.glTranslatef(0.58F, 0.76F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                     GL11.glTranslatef(0.005F, 0.275F, 1.05F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                     GL11.glTranslatef(0.005F, 0.25F, 0.5F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                     GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                     GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                     GL11.glTranslatef(0.0F, 0.19F, 0.5F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                     GL11.glTranslatef(0.0F, 0.19F, 0.4F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                  } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                     GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(2.0, 2.0, 2.0);
                  GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.7F, 1.3F, 0.3F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(2.0, 2.0, 2.0);
                  GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.85F, 0.575F, -0.5F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.475F, -0.225F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.5F, 0.12F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.025F, -0.475F, -0.225F);
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
                  GL11.glTranslatef(0.2F, -0.8F, 0.15F);
               }, renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.5F, 0.12F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.125F, -0.575F, 0.075F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 5.0F);
                  GL11.glRotatef(-130.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.25F, -0.6F, -0.075F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.5F, 0.12F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.5F, 0.12F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.5F, 0.12F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.35F, -0.5F, 0.12F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.0F, 2.0F, 2.5F);
                  GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                  GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.5F, 0.12F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 4.0F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.5F, 0.12F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(7.0F)
         .withSpawnEntityGravityVelocity(0.0118F)
         .build();
   }
}
