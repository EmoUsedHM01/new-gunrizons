package com.gtnewhorizon.newgunrizons.items.factories.guns.sniper;

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
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.misc.PUmount;
import com.gtnewhorizon.newgunrizons.model.misc.SVTmount;
import com.gtnewhorizon.newgunrizons.model.sight.AK47iron;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron1;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron2;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.PUreticle;
import com.gtnewhorizon.newgunrizons.model.sight.PUscope;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.SVT40;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;

public class SVT40Factory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("SVT40")
            .withFireRate(0.4F)
            .withRecoil(4.0F)

            .withMaxShots(1)
            .withShootSound("svt40")
            .withSilencedShootSound("AKsilenced")
            .withReloadSound("svt40reload")
            .withUnloadSound("svt40unload")
            .withReloadingTime(45L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.6F; })
            .withFlashOffsetX(() -> { return 0.14F; })
            .withFlashOffsetY(() -> { return 0.08F; })
            .withCreativeTab(NewGunrizonsMod.SnipersTab)
            .withCrafting(
                CraftingComplexity.MEDIUM,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                CommonProxy.MetalComponents,
                Blocks.planks)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Semi-automatic Battle Rifle",
                    "Damage: 13",
                    "Caliber: 7.62x54mm",
                    "Magazines:",
                    "10rnd 7.62x54mm Magazine",
                    "Fire Rate: Semi");
            })
            .withCompatibleAttachment(Attachments.PUscope, (player, stack) -> {
                GL11.glTranslatef(0.4F, -1.2F, 0.2F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }, (model) -> {
                if (model instanceof PUscope) {
                    GL11.glTranslatef(-0.508F, 0.46F, -0.55F);
                    GL11.glScaled(0.2199999988079071D, 0.2199999988079071D, 0.2199999988079071D);
                } else if (model instanceof PUmount) {
                    GL11.glTranslatef(-0.209F, -0.485F, 1.27F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof SVTmount) {
                    GL11.glScalef(1.0F, 1.0F, 1.0F);
                    GL11.glTranslatef(-0.4F, 1.2F, -0.19F);
                } else if (model instanceof PUreticle) {
                    GL11.glTranslatef(-0.5F, 0.23F, 0.82F);
                    GL11.glScaled(0.029999999329447746D, 0.029999999329447746D, 0.029999999329447746D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.AKpart, true, (model) -> {
                GL11.glTranslatef(-0.1F, -0.47F, -6.6F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 2.700000047683716D);
                GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.SVT40action, true, (model) -> {})
            .withCompatibleAttachment(
                Magazines.SVT40mag,
                (Consumer) ((model) -> { GL11.glTranslatef(0.0F, -0.17F, 0.05F); }))
            .withCompatibleAttachment(Attachments.Silencer762x54, (model) -> {
                GL11.glTranslatef(-0.2F, -0.7F, -8.3F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(-0.2F, -1.05F, -1.92F);
                    GL11.glScaled(0.75D, 0.6000000238418579D, 0.75D);
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
                    GL11.glTranslatef(-0.13F, -0.77F, -6.2F);
                    GL11.glScaled(0.30000001192092896D, 0.3499999940395355D, 0.30000001192092896D);
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
            .withTextureName("svt40")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new SVT40())
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
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-1.0F, 0.3F, 1.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.2F);
                        GL11.glRotatef(-1.3F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.31F, 0.24F, -0.8F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                            GL11.glTranslatef(0.003F, 0.1565F, 0.15F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.SVT40mag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.SVT40action.getRenderablePart(),
                        (renderContext) -> {
                            if (renderContext.getWeaponInstance()
                                .getAmmo() == 0) {
                                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                            }

                        })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.SVT40action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.7F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.SVT40action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.7F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.SVT40mag.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.SVT40mag.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 350L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 130L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-3.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                    }, 400L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.25F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 200L, 70L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 180L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 100L, 50L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 120L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.725F, -1.449999F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.SVT40action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.SVT40action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.7F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.SVT40mag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(-13.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, 0.15F, 0.05F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(-13.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, 0.9F, 0.05F);
                            GL11.glRotatef(-13.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.SVT40mag, new Transition((renderContext) -> {
                        GL11.glRotatef(-13.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glTranslatef(0.0F, 0.9F, 0.05F);
                        GL11.glRotatef(-13.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glRotatef(-13.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glTranslatef(0.0F, 0.15F, 0.05F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.31F, 0.24F, -1.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                            GL11.glTranslatef(0.003F, 0.1565F, 0.3F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.35F, 0.6F, -0.4F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.249999F, 0.05F, -0.725F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 7.0F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.425F, -0.3F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 7.0F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.425F, -0.3F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.675F, 0.175F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.325F, 0.625F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.7F, 0.45F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, -0.675F, 0.475F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 7.0F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.425F, -0.3F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 7.0F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.425F, -0.3F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 7.0F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.425F, -0.3F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    }, 260L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.15F, -0.325F, 0.125F);
                    }, 250L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.175F, -0.425F, 0.225F);
                    }, 280L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.15F, -0.325F, 0.125F);
                    }, 280L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, -0.675F, 0.475F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.7F, 0.45F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.325F, 0.625F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(13.0F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
