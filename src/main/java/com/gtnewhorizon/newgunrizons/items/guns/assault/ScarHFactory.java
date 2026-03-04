package com.gtnewhorizon.newgunrizons.items.guns.assault;

import java.util.Arrays;
import java.util.function.Consumer;

import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.guns.GunFactory;
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
import com.gtnewhorizon.newgunrizons.model.weapon.Scar;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

public class ScarHFactory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("ScarH")
            .withFireRate(0.7F)
            .withRecoil(3.6F)
            .withZoom(0.9F)
            .withMaxShots(Integer.MAX_VALUE, 3, 1)
            .withShootSound("ScarH")
            .withSilencedShootSound("AR15Silenced")
            .withReloadSound("m4reload")
            .withUnloadSound("m4unload")
            .withReloadingTime(50L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.12F; })
            .withFlashOffsetY(() -> { return 0.15F; })
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Battle rifle",
                    "Damage: 7.4",
                    "Nickname: Scar-H",
                    "Caliber: .300 AAC Blackout",
                    "Magazines:",
                    "30rnd .300 AAC Blackout Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(Magazines.ScarHMag, (Consumer) ((model) -> {
                GL11.glTranslatef(-0.342F, 0.6F, -1.43F);
                GL11.glScaled(1.100000023841858D, 1.5D, 1.5D);
            }))
            .withCompatibleAttachment(AuxiliaryAttachments.ScarAction, true, (model) -> {})
            .withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.16F, -1.5F, -0.3F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(0.255F, -1.55F, -2.25F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof P90iron) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -3.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.092F, -1.91F, -0.9F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(-0.163F, -1.85F, 0.2F);
                    GL11.glScaled(0.3499999940395355D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(-0.085F, -1.74F, -4.55F);
                    GL11.glScaled(0.550000011920929D, 0.6399999856948853D, 0.6000000238418579D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(0.127F, -1.77F, -2.22F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
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
                GL11.glTranslatef(-0.321F, -1.8F, -0.7F);
                GL11.glScaled(0.8500000238418579D, 0.8500000238418579D, 0.8500000238418579D);
            }, (model) -> {
                if (model instanceof AcogScope2) {
                    GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof AcogReticle) {
                    GL11.glTranslatef(0.243F, -0.23F, 0.68F);
                    GL11.glScaled(0.029999999329447746D, 0.029999999329447746D, 0.029999999329447746D);
                }

            })
            .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
                GL11.glTranslatef(-0.2F, -1.45F, -0.7F);
                GL11.glScaled(0.5D, 0.5D, 0.5D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
                GL11.glTranslatef(-0.386F, -1.79F, -0.6F);
                GL11.glScaled(1.1100000143051147D, 1.1100000143051147D, 1.1100000143051147D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.272F, 0.67F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
                GL11.glTranslatef(-0.386F, -1.79F, -1.0F);
                GL11.glScaled(1.1100000143051147D, 1.1100000143051147D, 1.1100000143051147D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.235F, 1.16F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.06F, -1.6F, -0.9F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.041F, -1.63F, -0.8F);
                    GL11.glScaled(0.6700000166893005D, 0.6700000166893005D, 0.6700000166893005D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.041F, -1.63F, -1.1F);
                    GL11.glScaled(0.6700000166893005D, 0.6700000166893005D, 0.6700000166893005D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.041F, -1.63F, -0.6F);
                    GL11.glScaled(0.6700000166893005D, 0.6700000166893005D, 0.6700000166893005D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.18F, -1.81F, -0.6F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.12F, -1.97F, -0.7F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Grip2, (model) -> {
                GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Grip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.VGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(-0.2F, -0.41F, -4.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
                GL11.glTranslatef(0.0F, -1.25F, -4.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Laser, (p, s) -> {
                GL11.glTranslatef(0.0F, -1.25F, -4.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Silencer300AACBlackout, (model) -> {
                GL11.glTranslatef(-0.2F, -1.32F, -7.2F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureName("Scar")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new Scar())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                        GL11.glTranslatef(1.0F, 2.0F, -1.2F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-2.0F, -1.2F, 2.2F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.625F, 1.499999F, -1.3F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.625F, 1.499999F, -1.2F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.35F, 1.349999F, -1.4F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AKMIron)) {
                            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(-0.005F, 0.115F, 1.8F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(-0.0F, 0.06F, 1.4F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(-0.003F, 0.19F, 1.3F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.19F, 1.1F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.047F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.045F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.08F, -0.21F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.ScarHMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.ScarAction.getRenderablePart(),
                        (renderContext) -> {
                            if (renderContext.getWeaponInstance()
                                .getAmmo() == 0) {
                                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                            }

                        })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.ScarAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.2F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.ScarAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.2F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.ScarHMag.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.ScarHMag.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.8F, 1.75F, -1.2F);
                    }, 350L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.8F, 1.75F, -0.8F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(32.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.8F, 1.75F, -0.5F);
                    }, 100L, 130L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.7F, 1.5F, -1.0F);
                    }, 290L, 10L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.7F, 1.5F, -1.0F);
                    }, 310L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.8F, 1.75F, -1.2F);
                    }, 300L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.8F, 1.75F, -1.2F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.8F, 1.75F, -1.2F);
                    }, 170L, 0L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.ScarHMag,
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.01F, 0.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.01F, 0.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                            GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.ScarAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.ScarAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.ScarHMag, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.01F, 0.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.35F, 1.349999F, -1.5F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AKMIron)) {
                            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(-0.005F, 0.115F, 1.85F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(-0.0F, 0.06F, 1.4F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(-0.003F, 0.19F, 1.3F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.19F, 1.1F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.047F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.045F, -0.2F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.08F, -0.21F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.375F, 1.574999F, -0.725F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.075F, 0.925F, 0.15F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 6.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.05F, -0.3F, 0.125F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.1F, 3.1F, 3.1F);
                        GL11.glTranslatef(0.9F, 0.4F, -0.4F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.7F, 3.7F, 4.2F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.505F, 0.08F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 6.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.05F, -0.3F, 0.125F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.505F, 0.08F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.7F, 0.15F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.775F, 0.2F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.505F, 0.08F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.4F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
