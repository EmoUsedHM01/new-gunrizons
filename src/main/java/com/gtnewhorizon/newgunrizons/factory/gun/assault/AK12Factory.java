package com.gtnewhorizon.newgunrizons.factory.gun.assault;

import java.util.Arrays;
import java.util.function.Consumer;

import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.factory.gun.GunFactory;
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
import com.gtnewhorizon.newgunrizons.model.weapon.AK12;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class AK12Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("AK12")
            .withFireRate(0.75F)
            .withRecoil(3.5F)
            .withZoom(0.9F)
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
            .withFlashScale(() -> { return 0.6F; })
            .withFlashOffsetX(() -> { return 0.14F; })
            .withFlashOffsetY(() -> { return 0.12F; })
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withCrafting(
                CraftingComplexity.MEDIUM,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                CommonProxy.MetalComponents)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Assault rifle",
                    "Damage: 7.3",
                    "Caliber: 5.45x39mm",
                    "Magazines:",
                    "31rnd 5.45x39mm Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(Magazines.AK12Mag, (Consumer) ((model) -> {}))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.21F, -1.94F, 4.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.18F, -1.25F, -4.1F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(-0.162F, -1.25F, -4.72F);
                    GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.5199999809265137D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.092F, -1.91F, -0.9F);
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
                    GL11.glTranslatef(-0.1F, -1.235F, -0.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.AK12IronSight, true, (model) -> {
                if (model instanceof AK12IronSight) {
                    GL11.glTranslatef(-0.166F, -1.4F, -0.3F);
                    GL11.glScaled(0.5600000023841858D, 0.5600000023841858D, 0.5600000023841858D);
                }

            })
            .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
                GL11.glTranslatef(-0.277F, -1.3F, -1.0F);
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
                    GL11.glTranslatef(-0.17F, -1.3F, -1.2F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.4F, -1.5F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
                GL11.glTranslatef(-0.31F, -1.3F, -1.0F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.272F, 0.67F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
                GL11.glTranslatef(-0.182F, -1.04F, -1.3F);
                GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
                GL11.glTranslatef(-0.31F, -1.3F, -1.5F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.235F, 1.16F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.065F, -1.13F, -1.8F);
                    GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.42F, -1.8F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.055F, -1.18F, -1.4F);
                    GL11.glScaled(0.5400000214576721D, 0.5400000214576721D, 0.5400000214576721D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.125F, -1.435F, -1.3F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.055F, -1.18F, -1.4F);
                    GL11.glScaled(0.5400000214576721D, 0.5400000214576721D, 0.5400000214576721D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.125F, -1.435F, -1.3F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.055F, -1.18F, -1.4F);
                    GL11.glScaled(0.5400000214576721D, 0.5400000214576721D, 0.5400000214576721D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.407F, -1.85F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Grip2, (model) -> {
                GL11.glTranslatef(-0.2F, -0.23F, -2.6F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.23F, -2.6F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.Grip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.18F, -3.0F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(-0.2F, -0.23F, -3.0F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.VGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.23F, -2.6F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.Silencer556x39, (model) -> {
                GL11.glTranslatef(-0.2F, -1.02F, -6.35F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.AK12action, true, (model) -> {})
            .withTextureNames("AK12kal", "Electric")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new AK12())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.3199999928474426D, 0.3199999928474426D, 0.3199999928474426D);
                        GL11.glTranslatef(1.0F, 1.8F, -1.0F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                        GL11.glTranslatef(-1.6F, -0.8F, 1.7F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.25F);
                        GL11.glRotatef(4.0F, 0.0F, 0.0F, 1.0F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.91F, -0.1F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.5F, 0.0F, 0.0F, 1.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glTranslatef(0.4F, 2.3F, -1.0F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(1.5F, 0.0F, 0.0F, 1.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.85F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.85F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.133F, 1.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.12F, 1.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.14F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.AK12Mag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AK12action.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AK12IronSight.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AK12action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.4F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AK12action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.4F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AK12IronSight.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AK12IronSight.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.AK12Mag,
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.AK12Mag,
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                    }, 350L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 130L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                    }, 400L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.2F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 200L, 70L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.4F, 0.9F, -0.35F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 180L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                    }, 200L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 0.9F, -0.35F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.AK12Mag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.3F);
                            GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.05F, 1.5F, -0.5F);
                            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.AK12Mag, new Transition((renderContext) -> {
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
                        AuxiliaryAttachments.AK12action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AK12action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.4F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glTranslatef(0.0F, 2.3F, -1.5F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.95F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.95F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.133F, 1.25F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.14F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.132F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.12F, 1.1F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(12.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.025F, 0.6F, -0.05F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.4F, 0.4F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glTranslatef(0.47F, 0.07F, -0.0F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.2F, 2.2F, 2.2F);
                        GL11.glTranslatef(1.0F, 0.4F, -0.5F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.075F, -0.35F, 0.5F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.625F, 0.425F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.6F, 0.225F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.025F, -0.55F, 0.35F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.125F, -0.65F, 0.325F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.025F, -0.55F, 0.35F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.6F, 0.225F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.625F, 0.425F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.075F, -0.35F, 0.5F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.3F)
            .withSpawnEntityGravityVelocity(0.028F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
