package com.gtnewhorizon.newgunrizons.items.guns.sniper;

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
import com.gtnewhorizon.newgunrizons.model.misc.AKRail;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail2;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail3;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail4;
import com.gtnewhorizon.newgunrizons.model.sight.AK47iron;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron1;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron2;
import com.gtnewhorizon.newgunrizons.model.sight.Acog2;
import com.gtnewhorizon.newgunrizons.model.sight.AcogReticle;
import com.gtnewhorizon.newgunrizons.model.sight.AcogScope2;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.LPscope;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MBUSiron;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.MicroT1;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex2;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.M107;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

public class M107Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("M107")
            .withFireRate(0.5F)
            .withRecoil(4.0F)
            .withZoom(0.9F)
            .withMaxShots(1)
            .withShootSound("M107")
            .withSilencedShootSound("RifleSilencer")
            .withReloadSound("m107reload")
            .withUnloadSound("akunload")
            .withReloadingTime(43L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.1F; })
            .withFlashOffsetY(() -> { return 0.1F; })
            .withCreativeTab(NewGunrizonsMod.SnipersTab)
            .withCrafting(
                CraftingComplexity.HIGH,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                CommonProxy.BigSteelPlate)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Anti-materiel sniper rifle",
                    "Damage: 50",
                    "Caliber: .50 BMG",
                    "Magazines:",
                    "10rnd .50 BMG Magazine (Type 2)",
                    "Fire Rate: Semi");
            })
            .withCompatibleAttachment(Magazines.M107BMag, (Consumer) ((model) -> {
                GL11.glTranslatef(-0.42F, 0.8F, -1.4F);
                GL11.glScaled(1.5D, 1.5D, 1.5D);
            }))
            .withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
                if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -4.8F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(-0.2F, -1.8F, -5.0F);
                    GL11.glScaled(0.5D, 0.6000000238418579D, 0.20000000298023224D);
                } else if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.16F, -1.65F, -0.3F);
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
                    GL11.glTranslatef(0.14F, -1.56F, -2.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(-0.165F, -1.62F, -0.4F);
                    GL11.glScaled(0.3799999952316284D, 0.3799999952316284D, 0.3799999952316284D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.25F, -1.52F, -3.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(-0.08F, -1.53F, -5.0F);
                    GL11.glScaled(0.30000001192092896D, 0.699999988079071D, 0.4000000059604645D);
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
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
                GL11.glTranslatef(-0.345F, -1.55F, -0.8F);
                GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
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
                GL11.glTranslatef(-0.2F, -1.2F, -1.2F);
                GL11.glScaled(0.5D, 0.5D, 0.5D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
                GL11.glTranslatef(-0.36F, -1.53F, -0.8F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.272F, 0.67F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
                GL11.glTranslatef(-0.335F, -1.53F, -1.2F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.235F, 1.16F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.18F, -1.53F, -1.0F);
                    GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.66F, -1.0F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(-0.16F, -0.4F, -5.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Silencer50BMG, (model) -> {
                GL11.glTranslatef(-0.22F, -1.35F, -12.5F);
                GL11.glScaled(1.2999999523162842D, 1.2999999523162842D, 1.600000023841858D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.AUGRail, true, (model) -> {
                if (model instanceof AKRail) {
                    GL11.glTranslatef(-0.245F, -1.45F, -4.5F);
                    GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
                } else if (model instanceof AKRail2) {
                    GL11.glTranslatef(-0.245F, -1.45F, -6.25F);
                    GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
                } else if (model instanceof AKRail3) {
                    GL11.glTranslatef(-0.03F, -0.52F, -3.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKRail4) {
                    GL11.glTranslatef(-0.245F, -1.45F, -2.33F);
                    GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.M107action, true, (model) -> {
                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureName("M107")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new M107())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                        GL11.glTranslatef(0.8F, 0.8F, 0.0F);
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
                        GL11.glTranslatef(-0.45F, 1.18F, -0.8F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.45F, 1.18F, -0.4F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.3505F, 1.125F, -0.3F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.125F, 0.7F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.036F, 0.8F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.5F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.12F, 0.55F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.M107BMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.M107BMag,
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.M107BMag,
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AUGRail.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AUGRail.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AUGRail.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.M107action.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.M107action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 2.0F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.M107action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 2.0F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
                    }, 300L, 60L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
                    }, 300L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.274999F, -0.125F);
                    }, 400L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.274999F, -0.125F);
                    }, 120L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.274999F, -0.125F);
                    }, 130L, 150L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
                    }, 150L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 0.725F, 0.0F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.M107BMag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.0F, 0.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.M107BMag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.AUGRail.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AUGRail.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.M107action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.M107action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 2.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.3505F, 1.125F, -0.55F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.125F, 0.8F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.036F, 0.9F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.6F);
                        }

                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.12F, 0.65F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, 1.249999F, 0.0F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.0F, 0.525F, 0.35F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 4.0F);
                        GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.9F, 0.5F, -0.4F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 4.0F);
                        GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.675F, 0.325F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.475F, 0.075F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glTranslatef(0.43F, 0.03F, -0.01F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 4.0F);
                        GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 4.0F);
                        GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 10.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.25F, -0.175F, -0.225F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 10.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.35F, -0.5F, -0.225F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 10.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.25F, -0.175F, -0.225F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.475F, 0.075F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 7.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.675F, 0.325F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 4.0F);
                        GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 4.0F);
                        GL11.glTranslatef(-0.15F, 0.35F, 0.53F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(50.0F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
