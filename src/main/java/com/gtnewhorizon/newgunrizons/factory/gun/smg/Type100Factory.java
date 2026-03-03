package com.gtnewhorizon.newgunrizons.factory.gun.smg;

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
import com.gtnewhorizon.newgunrizons.model.weapon.Type100;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class Type100Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("Type100")
            .withFireRate(0.5F)
            .withRecoil(3.0F)
            .withZoom(0.9F)
            .withShootSound("Type100")
            .withSilencedShootSound("PPSh41Silenced")
            .withReloadSound("type100reload")
            .withUnloadSound("type100unload")
            .withReloadingTime(50L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.3F; })
            .withFlashOffsetX(() -> { return 0.12F; })
            .withFlashOffsetY(() -> { return 0.08F; })
            .withInaccuracy(3.0F)
            .withShellCasingForwardOffset(0.23F)
            .withShellCasingVerticalOffset(-0.02F)
            .withCreativeTab(NewGunrizonsMod.SMGTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Submachine gun",
                    "Damage: 7.6",
                    "Caliber: 8x22mm Nambu",
                    "Magazines:",
                    "30rnd 8x22mm Nambu Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(Magazines.Type100Mag, (Consumer) ((model) -> {}))
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
                    GL11.glTranslatef(-0.13F, -0.892F, 0.5F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
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
                    GL11.glTranslatef(-0.07F, -0.7F, -4.3F);
                    GL11.glScaled(0.10000000149011612D, 0.30000001192092896D, 0.800000011920929D);
                    GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.Type100action, true, (model) -> {})
            .withTextureNames("Type100")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new Type100())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
                        GL11.glTranslatef(1.0F, 0.8F, 0.0F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-1.1F, -0.0F, 1.2F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.45F, 0.7F, -1.1F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(-0.45F, 0.7F, -0.9F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(0.22F, 0.425F, -0.9F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonCustomPositioning(Magazines.Type100Mag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.Type100Mag,
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.Type100Mag,
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.Type100action.getRenderablePart(),
                        (renderContext) -> {
                            if (renderContext.getWeaponInstance()
                                .getAmmo() == 0) {
                                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                            }

                        })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.Type100action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.3F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.Type100action.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.3F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-19.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 0.9F, -0.2F);
                    }, 280L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-21.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(29.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 0.9F, -0.19F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 1.274999F, -0.575F);
                    }, 310L, 55L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-43.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(38.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 1.274999F, -0.3F);
                    }, 200L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-41.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, 1.274999F, -0.4F);
                    }, 210L, 40L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(28.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 0.9F, -0.2F);
                    }, 150L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(27.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 0.9F, -0.1F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.Type100Mag,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.7F, 1.0F, 1.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.Type100Mag, new Transition((renderContext) -> {
                        GL11.glTranslatef(-0.5F, 1.0F, -0.2F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.Type100action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.3F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.Type100action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glTranslatef(0.22F, 0.425F, -1.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(5.0D, 5.0D, 5.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.55F, 1.0F, -0.5F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(5.0D, 5.0D, 5.0D);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 0.25F, -0.4F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.475F, 0.3F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    })
                    .withFirstPersonHandPositioningRunning((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.475F, 0.3F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.475F, 0.3F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
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
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.675F, 0.575F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.0F, -0.45F, 0.375F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.0F, -0.45F, 0.375F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.0F, -0.45F, 0.375F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.0F, -0.45F, 0.375F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.6F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.1F, -0.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.6F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.225F, -0.475F, -0.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.6F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.1F, -0.1F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.0F, -0.45F, 0.375F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.7F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.675F, 0.575F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 2.8F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.575F, -0.7F, 0.15F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.6F)
            .withSpawnEntityGravityVelocity(0.028F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
