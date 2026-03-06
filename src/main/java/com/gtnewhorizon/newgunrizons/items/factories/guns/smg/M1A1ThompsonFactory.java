package com.gtnewhorizon.newgunrizons.items.factories.guns.smg;

import java.util.Arrays;

import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.sight.AK47iron;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron1;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron2;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M1A1rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.M1A1Thompson;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;

public class M1A1ThompsonFactory {

    public Item createGun() {
        return (new ItemWeapon.Builder()).withName("M1A1Thompson")
            .withFireRate(0.75F)
            .withRecoil(3.0F)

            .withMaxShots(Integer.MAX_VALUE, 1)
            .withShootSound("M1A1Thompson")
            .withSilencedShootSound("silencer")
            .withReloadSound("m1a1reload")
            .withUnloadSound("type100unload")
            .withReloadingTime(50L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.3F; })
            .withFlashOffsetX(() -> { return 0.12F; })
            .withFlashOffsetY(() -> { return 0.08F; })
            .withInaccuracy(2.0F)
            .withShellCasingForwardOffset(0.23F)
            .withShellCasingVerticalOffset(-0.02F)
            .withCreativeTab(NewGunrizonsMod.SMGTab)
            .withInformationProvider(
                (stack) -> Arrays.asList(
                    "Type: Submachine gun",
                    "Damage: 5.8",
                    "Caliber: .45 ACP",
                    "Magazines:",
                    ".45 ACP 30rnd Box Magazine",
                    "Fire Rate: Auto"))
            .withCompatibleAttachment(Magazines.M1A1mag, ((model) -> {}))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.04F, -1.216F, -1.65F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.13F, -0.73F, -3.45F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.0F, -1.5F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -3.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(-0.2F, -1.46F, -1.585F);
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
                    GL11.glTranslatef(-0.05F, -0.8F, -3.4F);
                    GL11.glScaled(0.15D, 0.35D, 0.3D);
                    GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.Silencer45ACP, (model) -> {
                GL11.glTranslatef(-0.14F, -0.81F, -5.25F);
                GL11.glScaled(1.15D, 1.15D, 1.15D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.M1A1rearsight, true, (model) -> {
                if (model instanceof M1A1rearsight) {
                    GL11.glTranslatef(-0.16F, -0.846F, 1.9F);
                    GL11.glScaled(0.2D, 0.175D, 0.2D);
                }

            })
            .withCompatibleAttachment(
                AuxiliaryAttachments.M1A1action,
                true,
                (model) -> { GL11.glTranslatef(0.0F, 0.0F, 1.0F); })
            .withTextureName("M1A1Thompson")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModel(new M1A1Thompson())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.35D, 0.35D, 0.35D);
                        GL11.glTranslatef(1.0F, 0.8F, 0.0F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.55D, 0.55D, 0.55D);
                        GL11.glTranslatef(-1.4F, -0.2F, 1.5F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.5F, 0.85F, -2.3F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.5F, 0.85F, -2.1F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(0.224F, 0.525F, -2.0F);
                        GL11.glRotatef(-0.1F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonCustomPositioning(Magazines.M1A1mag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(Magazines.M1A1mag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M1A1mag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.M1A1action.getRenderablePart(),
                        (renderContext) -> {
                            if (renderContext.getWeaponInstance()
                                .getAmmo() == 0) {
                                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
                            }

                        })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.M1A1action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.M1A1action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
                    }, 280L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-37.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
                    }, 300L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
                    }, 100L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.3F, 0.9F, -2.1F);
                    }, 350L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-7.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.3F, 0.9F, -1.6F);
                    }, 200L, 100L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.425F, 0.45F, -0.65F);
                    }, 150L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.425F, 0.45F, -0.6F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.M1A1mag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 1.0F, 0.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.M1A1mag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 1.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.M1A1action.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.M1A1action.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, -1.0F); }, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(0.224F, 0.525F, -2.075F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(5.0D, 5.0D, 5.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.525F, 1.2F, -1.675F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(5.0D, 5.0D, 5.0D);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.1F, 0.475F, -1.475F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.525F, 0.26F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    })
                    .withFirstPersonHandPositioningRunning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.525F, 0.26F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.525F, 0.26F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glTranslatef(1.5F, 0.1F, -0.2F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, -0.65F, 0.6F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.8F, 0.475F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.8F, 0.475F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.52F, -0.15F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.52F, -0.15F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.18F, -0.45F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, -0.7F, -0.025F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.8F, 0.475F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.5F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, -0.65F, 0.6F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.8F, 3.8F, 3.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.6F, 0.25F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(5.8F)
            .withSpawnEntityGravityVelocity(0.028F)
            .build();
    }
}
