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
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.Ots14Groza;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

public class OTs14GrozaFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("OTs14Groza")
            .withFireRate(0.7F)
            .withRecoil(3.3F)
            .withZoom(0.9F)
            .withMaxShots(Integer.MAX_VALUE, 1)
            .withShootSound("Groza")
            .withSilencedShootSound("AKsilenced")
            .withReloadSound("AKReload")
            .withUnloadSound("akunload")
            .withReloadingTime(45L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.6F; })
            .withFlashOffsetX(() -> { return 0.2F; })
            .withFlashOffsetY(() -> { return 0.16F; })
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
                    "Damage: 7.2",
                    "Caliber: 7.62x39mm",
                    "Magazines:",
                    "30rnd 7.62x39mm Magazine",
                    "30rnd 7.62x39mm PMAG Magazine",
                    "75rnd 7.62x39mm Drum Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(AuxiliaryAttachments.AKaction, true, (model) -> {})
            .withCompatibleAttachment(Magazines.Magazine762x39, (Consumer) ((model) -> {
                GL11.glTranslatef(0.0F, -0.5F, 0.05F);
                GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
            }))
            .withCompatibleAttachment(Magazines.Mag75rnd762x39, (Consumer) ((model) -> {
                GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.011F, 0.59F, 0.05F);
                GL11.glScaled(1.100000023841858D, 1.2999999523162842D, 1.0D);
            }))
            .withCompatibleAttachment(Attachments.Silencer762x39, (model) -> {
                GL11.glTranslatef(-0.22F, -1.06F, -6.5F);
                GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
            })
            .withCompatibleAttachment(Magazines.PMAG762x39, (Consumer) ((model) -> {
                GL11.glTranslatef(0.0F, -0.5F, 0.05F);
                GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
            }))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(-0.183F, -1.32F, -5.95F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
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
                    GL11.glTranslatef(-0.106F, -1.7F, -4.6F);
                    GL11.glScaled(0.10000000149011612D, 0.30000001192092896D, 0.30000001192092896D);
                    GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withTextureName("OTs14Groza")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new Ots14Groza())
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
                        GL11.glTranslatef(-0.525F, 1.249999F, 0.125F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.525F, 1.2F, 0.4F);
                        GL11.glRotatef(-1.7F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.35F, 1.2F, 1.9F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.005F, 0.25F, 0.3F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.Magazine762x39, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.PMAG762x39, (renderContext) -> {})
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
                        AuxiliaryAttachments.AKIron.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AKIron.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
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
                        GL11.glTranslatef(0.35F, 1.2F, 1.8F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.005F, 0.25F, 0.3F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.825F, 1.424999F, 1.149999F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.025F, 0.925F, 0.775F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.175F, 0.55F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.175F, 0.55F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.475F, -1.05F, 0.4F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
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
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
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
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.0F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.2F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
