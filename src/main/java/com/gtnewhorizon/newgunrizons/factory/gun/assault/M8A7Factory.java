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
import com.gtnewhorizon.newgunrizons.model.weapon.M8A7;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class M8A7Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("M8A7")
            .withFireRate(0.9F)
            .withRecoil(2.4F)
            .withZoom(0.9F)
            .withMaxShots(4)
            .withShootSound("M8A7")
            .withSilencedShootSound("MP5Silenced")
            .withReloadSound("StandardReload")
            .withUnloadSound("Unload")
            .withReloadingTime(43L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.12F; })
            .withFlashOffsetY(() -> { return 0.12F; })
            .withCreativeTab(NewGunrizonsMod.FunGunsTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Assault Rifle",
                    "Damage: 7.9",
                    "Caliber: .300 AAC Blackout",
                    "Magazines:",
                    "32rnd .300 AAC Blackout Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(
                Magazines.M8A7Mag,
                (Consumer) ((model) -> { GL11.glTranslatef(0.0F, -0.05F, 0.0F); }))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.17F, -1.155F, -0.1F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.16F, -1.15F, -3.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(-0.157F, -1.425F, -1.57F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -3.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.092F, -1.91F, -0.9F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.144F, -1.61F, 0.0F);
                    GL11.glScaled(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D);
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
                    GL11.glTranslatef(-0.164F, -1.6F, -3.85F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(-0.1F, -1.235F, -0.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.Grip2, (model) -> {
                GL11.glTranslatef(-0.2F, -0.1F, -2.9F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.VGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.1F, -2.9F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(-0.2F, -0.1F, -2.9F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
                GL11.glTranslatef(-0.2F, -0.1F, -2.9F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleAttachment(Attachments.Silencer300AACBlackout, (model) -> {
                GL11.glTranslatef(-0.2F, -1.11F, -6.8F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureNames("M8A7")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new M8A7())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.25D, 0.25D, 0.25D);
                        GL11.glTranslatef(1.0F, 2.0F, -1.4F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-1.9F, -1.0F, 2.1F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glTranslatef(0.32F, 0.15F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                        GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glTranslatef(0.32F, 0.15F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                        GL11.glTranslatef(-0.4F, -0.7F, 1.2F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glTranslatef(0.07F, -0.39F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-0.2F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(0.315F, -1.17F, 2.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                            GL11.glTranslatef(1.333F, -1.25F, 2.15F);
                        } else {
                            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.M8A7Mag, (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glTranslatef(0.7F, -0.5F, -0.1F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 250L, 500L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.7F, -0.5F, -0.1F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 250L, 20L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.32F, 0.15F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                        GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
                    }, 350L, 60L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.32F, 0.15F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                        GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
                    }, 100L, 0L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.32F, 0.15F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                        GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
                    }, 100L, 0L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.32F, 0.15F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                        GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
                    }, 250L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glTranslatef(0.7F, -0.5F, -0.1F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 150L, 50L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.7F, -0.5F, -0.1F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(Magazines.M8A7Mag, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.0F, 0.5F, -0.2F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glTranslatef(1.3F, 0.5F, -0.8F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.M8A7Mag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glTranslatef(0.07F, -0.39F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(0.315F, -1.17F, 1.9F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                            GL11.glTranslatef(1.333F, -1.25F, 2.15F);
                        } else {
                            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
                        GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
                        GL11.glTranslatef(0.3F, -0.25F, -0.1F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
                        GL11.glTranslatef(1.3F, -1.2F, -0.2F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.2F, 3.2F, 4.6F);
                        GL11.glTranslatef(0.5F, 0.08F, -0.1F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.9F, 0.5F, -0.4F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.3F, 2.3F, 2.5F);
                        GL11.glTranslatef(0.3F, 0.8F, 0.3F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.3F, 2.3F, 2.5F);
                        GL11.glTranslatef(0.3F, 0.6F, 0.3F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.4F, 2.4F, 3.7F);
                        GL11.glTranslatef(0.7F, 0.25F, 0.2F);
                        GL11.glRotatef(160.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(50.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.4F, 2.4F, 3.7F);
                        GL11.glTranslatef(0.73F, 0.25F, 0.2F);
                        GL11.glRotatef(160.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(50.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.4F, 2.4F, 3.7F);
                        GL11.glTranslatef(0.73F, 0.25F, 0.2F);
                        GL11.glRotatef(160.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(50.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.4F, 2.4F, 3.7F);
                        GL11.glTranslatef(0.7F, 0.25F, 0.2F);
                        GL11.glRotatef(160.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(50.0F, 1.0F, 1.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(1.7F, 1.7F, 3.0F);
                        GL11.glTranslatef(0.65F, -0.2F, 0.37F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(1.7F, 1.7F, 3.0F);
                        GL11.glTranslatef(0.7F, 0.0F, 0.37F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.8F, 2.8F, 3.3F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.9F)
            .withSpawnEntityGravityVelocity(0.028F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
