package com.gtnewhorizon.newgunrizons.items.guns.assault;

import java.util.Arrays;
import java.util.function.Consumer;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.guns.GunFactory;
import com.gtnewhorizon.newgunrizons.model.sight.AK12IronSight;
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
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.MicroT1;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex2;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.AK15;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

public class AK15Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("AK15")
            .withFireRate(0.6F)
            .withRecoil(3.0F)
            .withZoom(0.9F)
            .withMaxShots(Integer.MAX_VALUE, 1)
            .withShootSound("AK15")
            .withSilencedShootSound("AKsilenced")
            .withReloadSound("AKReload")
            .withUnloadSound("akunload")
            .withReloadingTime(45L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.6F; })
            .withFlashOffsetX(() -> { return 0.14F; })
            .withFlashOffsetY(() -> { return 0.12F; })
            .withInaccuracy(1.0F)
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withCrafting(
                CraftingComplexity.MEDIUM,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                CommonProxy.MetalComponents,
                Blocks.planks)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Assault rifle",
                    "Damage: 7.6",
                    "Caliber: 7.62x39mm",
                    "Magazines:",
                    "30rnd 7.62x39mm Magazine",
                    "30rnd 7.62x39mm PMAG Magazine",
                    "75rnd 7.62x39mm Drum Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(AuxiliaryAttachments.AKaction, true, (model) -> {})
            .withCompatibleAttachment(
                Magazines.Magazine762x39,
                (Consumer) ((model) -> { GL11.glTranslatef(0.0F, -0.17F, 0.05F); }))
            .withCompatibleAttachment(
                Magazines.AK15Mag,
                (Consumer) ((model) -> { GL11.glTranslatef(0.0F, -0.17F, 0.05F); }))
            .withCompatibleAttachment(Magazines.Mag75rnd762x39, (Consumer) ((model) -> {
                GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.011F, 0.59F, 0.05F);
                GL11.glScaled(1.100000023841858D, 1.2999999523162842D, 1.0D);
            }))
            .withCompatibleAttachment(Attachments.Silencer762x39, (model) -> {
                GL11.glTranslatef(-0.2F, -1.06F, -7.55F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Magazines.PMAG762x39, (Consumer) ((model) -> {}))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(-0.183F, -1.32F, -5.0F);
                    GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.6800000071525574D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(-0.25F, -1.65F, -3.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron1) {
                    GL11.glTranslatef(0.155F, -1.74F, 1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
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
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(0.165F, -1.65F, 1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.25F, -1.55F, -2.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
                GL11.glTranslatef(-0.277F, -1.35F, -1.0F);
                GL11.glScaled(0.6499999761581421D, 0.6499999761581421D, 0.6499999761581421D);
            }, (model) -> {
                if (model instanceof AcogScope2) {
                    GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof AcogReticle) {
                    GL11.glTranslatef(0.243F, -0.23F, 0.68F);
                    GL11.glScaled(0.029999999329447746D, 0.029999999329447746D, 0.029999999329447746D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.17F, -1.35F, -1.2F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.45F, -1.5F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
                GL11.glTranslatef(-0.285F, -1.34F, -0.7F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.272F, 0.67F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
                GL11.glTranslatef(-0.182F, -1.1F, -1.2F);
                GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
                GL11.glTranslatef(-0.285F, -1.35F, -1.25F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.235F, 1.16F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.065F, -1.2F, -1.8F);
                    GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.49F, -1.8F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.055F, -1.25F, -1.4F);
                    GL11.glScaled(0.5400000214576721D, 0.5400000214576721D, 0.5400000214576721D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.125F, -1.5F, -1.3F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.055F, -1.25F, -1.4F);
                    GL11.glScaled(0.5400000214576721D, 0.5400000214576721D, 0.5400000214576721D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.125F, -1.5F, -1.3F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.055F, -1.25F, -1.4F);
                    GL11.glScaled(0.5400000214576721D, 0.5400000214576721D, 0.5400000214576721D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.47F, -1.85F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Grip2, (model) -> {
                GL11.glTranslatef(-0.185F, -0.23F, -3.5F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
                GL11.glTranslatef(-0.185F, -0.23F, -3.5F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.Grip, (model) -> {
                GL11.glTranslatef(-0.185F, -0.13F, -3.7F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(-0.185F, -0.23F, -4.1F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.VGrip, (model) -> {
                GL11.glTranslatef(-0.185F, -0.23F, -3.5F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.AK12IronSight, true, (model) -> {
                if (model instanceof AK12IronSight) {
                    GL11.glTranslatef(-0.152F, -1.42F, -0.25F);
                    GL11.glScaled(0.41999998688697815D, 0.41999998688697815D, 0.41999998688697815D);
                }

            })
            .withTextureName("AK15")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new AK15())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
                        GL11.glTranslatef(1.0F, 2.0F, -1.2F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-1.8F, -1.1F, 2.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 1.0F, -0.4F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 1.0F, -0.3F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.35F, 0.78F, -0.0F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.17F, 0.95F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.13F, 0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.18F, 0.5F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.19F, 0.65F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.17F, 0.8F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.2F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.18F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.18F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.18F, -0.2F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.Magazine762x39, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.PMAG762x39, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.AK15Mag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.Mag75rnd762x39, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AKaction.getRenderablePart(),
                        (renderContext) -> {
                            if (renderContext.getWeaponInstance()
                                .getAmmo() == 0) {
                                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                            }

                        })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AKaction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AKaction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.0F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AK12IronSight.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AK12IronSight.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.Magazine762x39.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.Magazine762x39.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.Mag75rnd762x39.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.Mag75rnd762x39.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.PMAG762x39.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.PMAG762x39.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.AK15Mag.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.AK15Mag.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                    }, 350L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 130L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.0F, -0.3F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                    }, 400L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.0F, -0.15F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 200L, 70L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.4F, 1.0F, -0.3F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 180L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                    }, 200L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.1F, -0.3F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.AK12IronSight.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AK12IronSight.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.AKaction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AKaction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.Magazine762x39,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.Magazine762x39,
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.AK15Mag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.AK15Mag, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                        GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.Mag75rnd762x39,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.Mag75rnd762x39,
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.PMAG762x39,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.PMAG762x39, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                        GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.35F, 0.78F, -0.1F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.17F, 1.0F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.13F, 0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.18F, 0.5F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.19F, 0.65F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.17F, 0.8F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.2F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.18F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.18F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.18F, -0.2F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.35F, 1.025F, -0.4F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.149999F, 0.45F, 0.525F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glTranslatef(0.5F, 0.12F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glTranslatef(0.49F, 0.14F, -0.1F);
                        GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.525F, -0.925F, 0.25F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.02F, -0.38F, 0.5F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.0F, -0.625F, 0.425F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.05F, -0.6F, 0.225F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glTranslatef(0.5F, 0.15F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glTranslatef(0.5F, 0.15F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glTranslatef(0.5F, 0.15F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    }, 260L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.075F, 0.075F);
                    }, 250L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.025F, -0.425F, 0.15F);
                    }, 280L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.075F, 0.075F);
                    }, 280L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.05F, -0.6F, 0.225F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.0F, -0.625F, 0.425F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.02F, -0.38F, 0.5F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.6F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
