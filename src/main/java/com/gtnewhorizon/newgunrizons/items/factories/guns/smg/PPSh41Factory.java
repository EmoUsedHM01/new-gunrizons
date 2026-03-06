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
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.PPSHRearSight;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.PPSH41;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;

public class PPSh41Factory {

    public Item createGun() {
        return (new ItemWeapon.Builder()).withName("PPSh41")
            .withFireRate(0.8F)
            .withRecoil(3.0F)

            .withShootSound("PPSh41")
            .withSilencedShootSound("PPSh41Silenced")
            .withReloadSound("ppsh41reload")
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
                    "Damage: 7.1",
                    "Caliber: 7.52x25mm Tokarev",
                    "Magazines:",
                    "71rnd 7.62x25mm Tokarev Drum Magazine",
                    "Fire Rate: Auto"))
            .withCompatibleAttachment(Magazines.PPSH41DrumMag, ((model) -> {}))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.04F, -1.216F, -1.65F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.13F, -0.73F, -3.45F);
                    GL11.glScaled(0.3D, 0.3D, 0.3D);
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
                    GL11.glTranslatef(0.129F, -1.63F, -1.55F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.PPSHRearSight, true, (model) -> {
                if (model instanceof PPSHRearSight) {
                    GL11.glTranslatef(-0.1645F, -0.715F, -0.12F);
                    GL11.glScaled(0.2D, 0.21D, 0.2D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.PPSH41action, true, (model) -> {})
            .withTextureName("PPSH41")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModel(new PPSH41())
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
                        GL11.glTranslatef(-1.1F, -0.0F, 1.2F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.35F, 0.6F, -1.1F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.4F, 0.6F, -1.0F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(0.21F, 0.425F, -0.9F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonCustomPositioning(Magazines.PPSH41DrumMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(Magazines.PPSH41DrumMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.PPSH41DrumMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.PPSH41action.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.PPSH41action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.PPSH41action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.6F, 0.7F, -1.1F);
                    }, 300L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.6F, 0.7F, -1.1F);
                    }, 320L, 200L), new Transition((renderContext) -> {
                        GL11.glRotatef(38.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.55F, 0.55F, -1.1F);
                    }, 100L, 250L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 1.275F, -0.575F);
                    }, 350L, 55L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-44.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 1.275F, -0.35F);
                    }, 200L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-42.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 1.275F, -0.45F);
                    }, 210L, 40L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.6F, 0.7F, -1.1F);
                    }, 150L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.6F, 0.7F, -1.1F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.PPSH41DrumMag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.7F, 1.0F, 1.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.PPSH41DrumMag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.7F, 1.0F, 1.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.PPSH41action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.PPSH41action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(0.225F, 0.425F, -1.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(5.0D, 5.0D, 5.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 0.675F, -0.575F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(5.0D, 5.0D, 5.0D);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 0.25F, -0.4F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    })
                    .withFirstPersonHandPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glTranslatef(1.5F, 0.1F, -0.2F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.375F, 0.575F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.625F, -0.175F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.15F, -0.2F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.175F, -0.475F, -0.025F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.15F, -0.2F, 0.05F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 5.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.05F, -0.275F, 0.65F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 5.0F);
                        GL11.glRotatef(-65.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.375F, 0.575F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.1F)
            .withSpawnEntityGravityVelocity(0.028F)
            .build();
    }
}
