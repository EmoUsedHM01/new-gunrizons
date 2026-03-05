package com.gtnewhorizon.newgunrizons.items.factories.guns.assault;

import java.util.Arrays;

import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.action.ACRAction;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail2;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail3;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail4;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail5;
import com.gtnewhorizon.newgunrizons.model.sight.AK47iron;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron1;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron2;
import com.gtnewhorizon.newgunrizons.model.sight.Acog2;
import com.gtnewhorizon.newgunrizons.model.sight.AcogReticle;
import com.gtnewhorizon.newgunrizons.model.sight.AcogScope2;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.Holo2;
import com.gtnewhorizon.newgunrizons.model.sight.Holographic;
import com.gtnewhorizon.newgunrizons.model.sight.Holographic2;
import com.gtnewhorizon.newgunrizons.model.sight.Kobra;
import com.gtnewhorizon.newgunrizons.model.sight.LPscope;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MBUSiron;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.MicroT1;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex2;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.BushmasterACR;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;

public class ACRFactory {

    public Item createGun() {
        return (new ItemWeapon.Builder()).withName("ACR")
            .withFireRate(0.7F)
            .withRecoil(3.5F)

            .withMaxShots(Integer.MAX_VALUE, 3, 1)
            .withShootSound("acr")
            .withSilencedShootSound("AR15Silenced")
            .withReloadSound("acrreload")
            .withUnloadSound("m4unload")
            .withReloadingTime(30L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.6F; })
            .withFlashOffsetX(() -> { return 0.14F; })
            .withFlashOffsetY(() -> { return 0.12F; })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withInformationProvider(
                (stack) -> Arrays.asList(
                    "Type: Assault rifle",
                    "Damage: 7.2",
                    "Caliber: 5.56x45mm NATO",
                    "Magazines:",
                    "30rnd 5.56x45mm NATO Magazine",
                    "20rnd 5.56x45mm NATO Magazine",
                    "40rnd 5.56x45mm NATO Magazine",
                    "30rnd 5.56x45mm NATO PMAG Magazine",
                    "100rnd 5.56x45mm NATO Beta-C Magazine",
                    "Fire Rate: Auto"))
            .withCompatibleAttachment(Magazines.NATOMag1, ((model) -> {
                GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
                GL11.glScaled(1.15D, 1.2D, 1.2D);
            }))
            .withCompatibleAttachment(Magazines.NATOMag2, ((model) -> {
                GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
                GL11.glScaled(1.15D, 1.2D, 1.2D);
            }))
            .withCompatibleAttachment(Magazines.NATO20rnd, ((model) -> {
                GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
                GL11.glScaled(1.15D, 1.2D, 1.2D);
            }))
            .withCompatibleAttachment(Magazines.NATO40rnd, ((model) -> {
                GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
                GL11.glScaled(1.15D, 1.2D, 1.2D);
            }))
            .withCompatibleAttachment(Magazines.NATODrum100, ((model) -> {
                GL11.glTranslatef(-0.35F, 0.5F, -1.31F);
                GL11.glScaled(1.15D, 1.2D, 1.2D);
            }))
            .withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
                if (model instanceof ScarIron1) {
                    GL11.glTranslatef(0.165F, -1.65F, 1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.24F, -1.56F, -1.7F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.14F, -1.566F, 0.3F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.39F, -2.8F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(-0.22F, -2.08F, -3.7F);
                    GL11.glScaled(0.65D, 1.1D, 0.2D);
                } else if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.162F, -1.665F, -0.3F);
                    GL11.glScaled(0.35D, 0.35D, 0.35D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(0.26F, -1.8F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof P90iron) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(-0.173F, -1.7F, -3.72F);
                    GL11.glScaled(0.5D, 0.5D, 0.85D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MBUSiron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
                GL11.glTranslatef(-0.288F, -1.625F, -0.5F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            }, (model) -> {
                if (model instanceof AcogScope2) {
                    GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof AcogReticle) {
                    GL11.glTranslatef(0.243F, -0.23F, 0.68F);
                    GL11.glScaled(0.03D, 0.03D, 0.03D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.17F, -1.6F, -0.6F);
                    GL11.glScaled(0.3D, 0.3D, 0.3D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.7F, -0.7F);
                    GL11.glScaled(0.07D, 0.07D, 0.07D);
                }

            })
            .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
                GL11.glTranslatef(-0.182F, -1.32F, -0.6F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.1D, 0.1D, 0.1D);
                }

            })
            .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
                GL11.glTranslatef(-0.312F, -1.6F, -0.65F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.272F, 0.67F);
                    GL11.glScaled(0.05D, 0.05D, 0.05D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.07F, -1.45F, -0.8F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.12F, -1.7F, -0.94F);
                    GL11.glScaled(0.1D, 0.1D, 0.1D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.055F, -1.49F, -0.8F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.12F, -1.69F, -1.4F);
                    GL11.glScaled(0.1D, 0.1D, 0.1D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.05F, -1.48F, -0.8F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -1.78F, -0.8F);
                    GL11.glScaled(0.05D, 0.05D, 0.05D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.05F, -1.48F, -0.8F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -1.78F, -0.8F);
                    GL11.glScaled(0.05D, 0.05D, 0.05D);
                }

            })
            .withCompatibleAttachment(Attachments.Grip2, (model) -> {
                GL11.glTranslatef(-0.2F, -0.4F, -2.7F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.4F, -2.7F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Grip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.3F, -2.95F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.VGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.4F, -2.7F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(-0.2F, -0.35F, -3.5F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
                GL11.glTranslatef(0.08F, -1.15F, -3.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withCompatibleAttachment(Attachments.Laser, (p, s) -> {
                GL11.glTranslatef(0.08F, -1.15F, -3.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
                GL11.glTranslatef(-0.19F, -1.205F, -6.8F);
                GL11.glScaled(0.9D, 0.9D, 0.9D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.RemingtonACRRail, true, (model) -> {
                if (model instanceof AKRail) {
                    GL11.glTranslatef(0.19F, -1.15F, -3.9F);
                    GL11.glScaled(0.85D, 0.85D, 0.8D);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof AKRail2) {
                    GL11.glTranslatef(-0.43F, -0.93F, -3.92F);
                    GL11.glScaled(0.85D, 0.85D, 0.8D);
                    GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof AKRail3) {
                    GL11.glTranslatef(-0.03F, -0.77F, -3.94F);
                    GL11.glScaled(0.85D, 0.85D, 0.75D);
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof AKRail4) {
                    GL11.glTranslatef(-0.226F, -1.52F, -3.9F);
                    GL11.glScaled(0.76D, 0.87D, 0.8D);
                } else if (model instanceof AKRail5) {
                    GL11.glTranslatef(-0.226F, -1.52F, -2.0F);
                    GL11.glScaled(0.76D, 0.87D, 0.8D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.RemingtonACRAction, true, (model) -> {
                if (model instanceof ACRAction) {
                    GL11.glTranslatef(-0.06F, -1.4F, -2.9F);
                    GL11.glScaled(0.6D, 0.6D, 0.5D);
                }
            })
            .withTextureName("ACR")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModel(new BushmasterACR())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.35D, 0.35D, 0.35D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.4D, 0.4D, 0.4D);
                        GL11.glTranslatef(1.0F, 2.0F, -1.2F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.6D, 0.6D, 0.6D);
                        GL11.glTranslatef(-1.7F, -0.8F, 1.9F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.55F, 1.45F, -0.7F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.55F, 1.45F, -0.6F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glTranslatef(-0.905F, 2.2F, -2.3F);
                        GL11.glRotatef(-0.2F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.08F, 0.5F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.01F, 0.25F);
                        } else
                            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                                GL11.glTranslatef(0.0F, 0.085F, 0.45F);
                            } else
                                if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                                    GL11.glTranslatef(0.0F, 0.035F, 0.25F);
                                } else if (ItemWeapon
                                    .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                                        GL11.glTranslatef(-0.01F, 0.02F, 0.4F);
                                    } else if (ItemWeapon
                                        .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                                            GL11.glTranslatef(0.0F, 0.06F, 0.2F);
                                        } else if (ItemWeapon.isActiveAttachment(
                                            renderContext.getWeaponInstance(),
                                            Attachments.Holographic2)) {
                                                GL11.glTranslatef(0.0F, 0.059F, 0.2F);
                                            } else if (ItemWeapon.isActiveAttachment(
                                                renderContext.getWeaponInstance(),
                                                Attachments.Kobra)) {
                                                    GL11.glTranslatef(0.0F, 0.01F, 0.4F);
                                                }

                        GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                    })
                    .withFirstPersonCustomPositioning(Magazines.NATOMag1, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.NATOMag1.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.NATOMag1.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.NATO20rnd, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.NATO20rnd.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.NATO20rnd.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.NATO40rnd, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.NATO40rnd.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.NATO40rnd.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.NATOMag2, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.NATOMag2.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.NATOMag2.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.NATODrum100, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.NATODrum100.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.NATODrum100.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.RemingtonACRRail.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.RemingtonACRRail.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.RemingtonACRRail.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.RemingtonACRAction.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.RemingtonACRAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.9F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.RemingtonACRAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.9F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.5F, 1.4F, -0.7F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 300L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.5F, 1.4F, -0.7F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 310L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.5F, 1.3F, -0.7F);
                        GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                    }, 140L, 60L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.5F, 1.4F, -0.7F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 320L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.65F, 1.4F, -0.7F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 50L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glTranslatef(-0.4F, 1.5F, -0.7F);
                    }, 150L, 0L))
                    .withFirstPersonCustomPositioningUnloading(Magazines.NATOMag1, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(Magazines.NATO40rnd, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(Magazines.NATO20rnd, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.NATOMag1, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.NATO40rnd, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.NATO20rnd, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(Magazines.NATOMag2, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.NATOMag2, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.NATODrum100,
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                            GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.NATODrum100,
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.2F, 0.2F);
                            GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.RemingtonACRRail.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.RemingtonACRRail.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.RemingtonACRAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.RemingtonACRAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glTranslatef(-0.905F, 2.2F, -2.5F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.08F, 0.65F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.01F, 0.4F);
                        } else
                            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                                GL11.glTranslatef(0.0F, 0.085F, 0.6F);
                            } else
                                if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                                    GL11.glTranslatef(0.0F, 0.035F, 0.25F);
                                } else if (ItemWeapon
                                    .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                                        GL11.glTranslatef(-0.01F, 0.02F, 0.4F);
                                    } else if (ItemWeapon
                                        .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                                            GL11.glTranslatef(0.0F, 0.06F, 0.2F);
                                        } else if (ItemWeapon.isActiveAttachment(
                                            renderContext.getWeaponInstance(),
                                            Attachments.Holographic2)) {
                                                GL11.glTranslatef(0.0F, 0.059F, 0.2F);
                                            } else if (ItemWeapon.isActiveAttachment(
                                                renderContext.getWeaponInstance(),
                                                Attachments.Kobra)) {
                                                    GL11.glTranslatef(0.0F, 0.01F, 0.4F);
                                                }

                        GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 0.95F, -0.55F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.875F, 0.625F, 0.3F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.324F, 0.135F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.375F, -0.8F, 0.15F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.225F, -0.725F, 0.575F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.75F, 0.5F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.75F, 0.5F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.324F, 0.135F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.324F, 0.135F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.324F, 0.135F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.2F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
