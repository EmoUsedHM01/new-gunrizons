package com.gtnewhorizon.newgunrizons.factory.gun.assault;

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
import com.gtnewhorizon.newgunrizons.model.sight.MBUSiron;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.m1carbine;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class M2CarbineFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("M2Carbine")
            .withFireRate(0.5F)
            .withRecoil(2.8F)
            .withZoom(0.9F)
            .withMaxShots(1, Integer.MAX_VALUE)
            .withShootSound("M1Carbine")
            .withReloadSound("M1CarbineReload")
            .withUnloadSound("AKUnload")
            .withReloadingTime(60L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.5F; })
            .withFlashOffsetX(() -> { return 0.14F; })
            .withFlashOffsetY(() -> { return 0.08F; })
            .withInaccuracy(1.0F)
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withCrafting(
                CraftingComplexity.MEDIUM,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                "ingotSteel",
                Blocks.planks)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Semi-automatic carbine",
                    "Damage: 7",
                    "Caliber: .30 Carbine",
                    "Magazines:",
                    "15rnd .30 Carbine Magazine",
                    "30rnd .30 Carbine Magazine",
                    "Fire Rate: Semi");
            })
            .withCompatibleAttachment(
                AuxiliaryAttachments.M1CarbineAction,
                true,
                (model) -> { GL11.glTranslatef(0.0F, 0.03F, 0.0F); })
            .withCompatibleAttachment(AuxiliaryAttachments.SpringfieldRearSight, true, (model) -> {
                GL11.glTranslatef(-0.1385F, -1.125F, -0.72F);
                GL11.glScaled(0.10999999940395355D, 0.10999999940395355D, 0.10999999940395355D);
            })
            .withCompatibleAttachment(Magazines.M1CarbineMag, (Consumer) ((model) -> {
                GL11.glTranslatef(-0.3F, 0.5F, -1.3F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }))
            .withCompatibleAttachment(Magazines.M2CarbineMag, (Consumer) ((model) -> {
                GL11.glTranslatef(-0.3F, 0.5F, -1.3F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(-0.2F, -1.4F, -0.7F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(-0.16F, -1.11F, -6.3F);
                    GL11.glScaled(0.33000001311302185D, 0.33000001311302185D, 1.2000000476837158D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.092F, -1.7F, -0.9F);
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
                    GL11.glTranslatef(-0.16F, -1.215F, -0.76F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.16F, -1.16F, -3.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(0.177F, -1.65F, 1.4F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.25F, -1.55F, -2.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MBUSiron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withTextureNames("m2carbine")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new m1carbine())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.25D, 0.25D, 0.25D);
                        GL11.glTranslatef(1.0F, 2.6F, -2.1F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-1.8F, -1.2F, 2.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(6.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.45F, 0.97F, -0.25F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.43F, 1.0F, -0.1F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.295F, 0.719F, -0.0F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {}

                    })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.M1CarbineAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.7F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.M1CarbineAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.7F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(Magazines.M1CarbineMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M1CarbineMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(Magazines.M2CarbineMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M2CarbineMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.M1CarbineMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(Magazines.M2CarbineMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.M1CarbineAction.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-6.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.475F, 1.025F, -0.25F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(37.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-13.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.65F, 1.025F, -0.15F);
                    }, 350L, 200L), new Transition((renderContext) -> {
                        GL11.glRotatef(37.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.65F, 1.1F, -0.15F);
                    }, 100L, 150L), new Transition((renderContext) -> {
                        GL11.glScalef(1.0F, 1.0F, 1.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.625F);
                    }, 260L, 80L), new Transition((renderContext) -> {
                        GL11.glScalef(1.0F, 1.0F, 1.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(32.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.7F);
                    }, 100L, 10L), new Transition((renderContext) -> {
                        GL11.glScalef(1.0F, 1.0F, 1.0F);
                        GL11.glRotatef(-37.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(32.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 0.075F, 0.7F);
                    }, 100L, 150L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.475F, 1.1F, -0.2F);
                    }, 150L, 50L), new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.475F, 1.1F, -0.2F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.M1CarbineAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 350L, 150L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 350L, 150L),
                        new Transition((renderContext) -> {}, 400L, 80L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.7F); }, 100L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 100L, 200L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 350L, 150L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 350L, 150L),
                        new Transition((renderContext) -> {}, 400L, 80L),
                        new Transition((renderContext) -> {}, 100L, 200L),
                        new Transition((renderContext) -> {}, 100L, 200L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.M1CarbineAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 150L, 50L),
                        new Transition((renderContext) -> {}, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.SpringfieldRearSight.getRenderablePart(),
                        new Transition((renderContext) -> {}, 150L, 50L),
                        new Transition((renderContext) -> {}, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.M1CarbineMag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 1.5F, 1.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.M2CarbineMag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 1.5F, 1.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.M1CarbineMag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 1.5F, 1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.1F);
                            GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.M2CarbineMag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 1.5F, 1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, 0.6F, -0.1F);
                            GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.295F, 0.719F, -0.2F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {}

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, 1.149999F, 0.7F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.65F, 0.6F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(2.7F, 2.7F, 4.0F);
                        GL11.glTranslatef(0.5F, 0.08F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.525F, -0.05F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.2F, 2.2F, 2.2F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.475F, -1.05F, -0.1F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.525F, -0.05F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(2.7F, 2.7F, 4.0F);
                        GL11.glTranslatef(0.6F, 0.2F, -0.1F);
                        GL11.glRotatef(125.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.525F, -0.05F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.425F, -0.2F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.6F, -0.225F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.6F, -0.225F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.7F, 2.7F, 4.0F);
                        GL11.glTranslatef(0.5F, 0.08F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.7F, 2.7F, 4.0F);
                        GL11.glTranslatef(0.5F, 0.08F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.7F, 2.7F, 4.0F);
                        GL11.glTranslatef(0.5F, 0.08F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.5F, -0.075F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.5F, -0.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.5F, -0.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.025F, -0.075F, -0.2F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.025F, -0.25F, -0.2F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.025F, -0.25F, -0.2F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.6F, -0.225F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.425F, -0.2F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.5F, -0.075F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.5F, -0.075F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.0F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
