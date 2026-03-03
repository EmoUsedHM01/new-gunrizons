package com.gtnewhorizon.newgunrizons.factory.gun.sniper;

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
import com.gtnewhorizon.newgunrizons.model.sight.AcogReticle;
import com.gtnewhorizon.newgunrizons.model.sight.AcogScope2;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.LPscope;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.AS50;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class AS50Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("AS50")
            .withFireRate(0.16F)
            .withRecoil(4.0F)
            .withZoom(0.9F)
            .withMaxShots(1)
            .withShootSound("AS50")
            .withSilencedShootSound("RifleSilencer")
            .withReloadSound("AKReload")
            .withUnloadSound("Unload")
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
                    "Damage: 30.5",
                    "Caliber: .50 BMG",
                    "Magazines:",
                    "10rnd .50 BMG Magazine (Type 1)",
                    "Fire Rate: Semi");
            })
            .withCompatibleAttachment(Magazines.AS50Mag, (Consumer) ((model) -> {}))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -4.8F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.092F, -1.89F, -1.3F);
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
                    GL11.glTranslatef(0.14F, -1.74F, -2.05F);
                    GL11.glScaled(0.36000001430511475D, 0.36000001430511475D, 0.36000001430511475D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(0.176F, -1.75F, 1.0F);
                    GL11.glScaled(0.1899999976158142D, 0.1899999976158142D, 0.1899999976158142D);
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
            .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
                GL11.glTranslatef(0.055F, -1.8F, 0.0F);
                GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
            }, (model) -> {
                if (model instanceof AcogScope2) {
                    GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof AcogReticle) {
                    GL11.glTranslatef(0.243F, -0.23F, 0.68F);
                    GL11.glScaled(0.029999999329447746D, 0.029999999329447746D, 0.029999999329447746D);
                }

            })
            .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
                GL11.glTranslatef(0.055F, -1.76F, 0.2F);
                GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.272F, 0.67F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
                GL11.glTranslatef(0.055F, -1.76F, 0.2F);
                GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.235F, 1.16F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(0.135F, -0.84F, -2.1F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
                GL11.glTranslatef(0.25F, -1.4F, -2.0F);
                GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
            })
            .withCompatibleAttachment(Attachments.Laser, (p, s) -> {
                GL11.glTranslatef(0.25F, -1.4F, -2.0F);
                GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
            })
            .withCompatibleAttachment(Attachments.Silencer50BMG, (model) -> {
                GL11.glTranslatef(0.107F, -1.52F, -5.8F);
                GL11.glScaled(1.100000023841858D, 1.100000023841858D, 1.2999999523162842D);
            })
            .withTextureNames("AS50", "AS50Blue", "AS50Red", "Electric")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new AS50())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                        GL11.glTranslatef(0.7F, 1.1F, 0.0F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                        GL11.glTranslatef(-1.8F, 0.3F, 1.5F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glTranslatef(0.25F, -0.23F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glTranslatef(0.25F, -0.23F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-0.4F, -0.8F, 1.0F);
                        GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glTranslatef(0.0F, -0.3F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(0.1377F, -1.0F, 1.3F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.75F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.15F, 0.4F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.15F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.2F, 0.7F);
                        } else {
                            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.AS50Mag, (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 250L, 500L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 250L, 20L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.3F, -0.28F, -0.15F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-0.3F, -0.8F, 0.8F);
                    }, 250L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 150L, 50L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.0F, -0.4F, -0.4F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(Magazines.AS50Mag, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.2F, 0.5F, -0.2F);
                        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glTranslatef(1.3F, 0.5F, -0.8F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.AS50Mag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glTranslatef(0.0F, -0.3F, -0.2F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(0.1377F, -1.0F, 1.35F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.75F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.0F, 0.15F, 0.4F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.0F, 0.15F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.2F, 0.7F);
                        } else {
                            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                        GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
                        GL11.glTranslatef(0.5F, -0.25F, -1.0F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
                        GL11.glTranslatef(1.0F, -0.8F, -1.5F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(1.7F, 1.7F, 3.0F);
                        GL11.glTranslatef(0.65F, -0.35F, 0.37F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(1.8F, 1.8F, 2.5F);
                        GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.2F, 2.2F, 2.2F);
                        GL11.glTranslatef(1.0F, 0.2F, 0.2F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(1.8F, 1.8F, 2.5F);
                        GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(1.7F, 1.7F, 3.0F);
                        GL11.glTranslatef(0.65F, -0.35F, 0.37F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(1.8F, 1.8F, 2.5F);
                        GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(1.8F, 1.8F, 2.5F);
                        GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(1.8F, 1.8F, 2.5F);
                        GL11.glTranslatef(-0.4F, -0.2F, 0.6F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
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
                        GL11.glScalef(1.8F, 1.8F, 2.5F);
                        GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(1.8F, 1.8F, 2.5F);
                        GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(30.5F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
