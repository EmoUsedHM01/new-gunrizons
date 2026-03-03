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
import com.gtnewhorizon.newgunrizons.model.sight.AcogReticle;
import com.gtnewhorizon.newgunrizons.model.sight.AcogScope2;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.Holo2;
import com.gtnewhorizon.newgunrizons.model.sight.Holographic;
import com.gtnewhorizon.newgunrizons.model.sight.Holographic2;
import com.gtnewhorizon.newgunrizons.model.sight.Kobra;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MBUSiron;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.MicroT1;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex2;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.TAR21;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class TAR21Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("TAR21")
            .withFireRate(0.6F)
            .withRecoil(3.5F)
            .withZoom(0.9F)
            .withMaxShots(Integer.MAX_VALUE, 3, 1)
            .withShootSound("TAR21")
            .withSilencedShootSound("MP5Silenced")
            .withReloadSound("m16reload")
            .withUnloadSound("M4unload")
            .withReloadingTime(43L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.2F; })
            .withFlashOffsetY(() -> { return 0.18F; })
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Assault Rifle",
                    "Damage: 7",
                    "Caliber: 5.56x45mm NATO",
                    "Magazines:",
                    "30rnd 5.56x45mm NATO Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(Magazines.NATOMag1, (Consumer) ((model) -> {
                GL11.glTranslatef(-0.32F, 0.35F, 1.45F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }))
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.17F, -1.155F, -0.1F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.16F, -1.15F, -3.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -3.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.092F, -1.91F, -0.9F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.155F, -1.45F, 0.1F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof P90iron) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(-0.155F, -1.45F, 0.14F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.25F, -1.55F, -2.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(-0.155F, -1.45F, -2.64F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.5D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(-0.1F, -1.235F, -0.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
                if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.17F, -1.155F, -0.1F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.16F, -1.15F, -3.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -3.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.092F, -1.91F, -0.9F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.155F, -1.45F, 0.1F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof P90iron) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(-0.155F, -1.44F, 0.14F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.25F, -1.55F, -2.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(-0.155F, -1.45F, -2.64F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(-0.1F, -1.235F, -0.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MBUSiron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.175F, -1.4F, -0.2F);
                    GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.53F, -0.35F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
                GL11.glTranslatef(-0.3F, -1.42F, 0.15F);
                GL11.glScaled(0.75D, 0.75D, 0.75D);
            }, (model) -> {
                if (model instanceof AcogScope2) {
                    GL11.glTranslatef(-0.018F, -0.25F, 0.13F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof AcogReticle) {
                    GL11.glTranslatef(0.243F, -0.23F, 0.68F);
                    GL11.glScaled(0.029999999329447746D, 0.029999999329447746D, 0.029999999329447746D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.073F, -1.25F, -0.6F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.52F, -0.6F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.048F, -1.28F, -0.3F);
                    GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -1.57F, -0.2F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.048F, -1.28F, -0.3F);
                    GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -1.57F, -0.2F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.06F, -1.25F, -0.2F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.46F, -0.65F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
                GL11.glTranslatef(-0.2F, -0.95F, -5.25F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureNames("TAR21")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new TAR21())
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
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.6F, 1.075F, -1.3F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.6F, 1.075F, -1.15F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glTranslatef(-0.3F, 2.8F, -1.7F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.12F, 0.3F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.04F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.0785F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.078F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, -0.01F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.NATOMag1, (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.6F, -0.5F, -0.2F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-2.024999F, 0.35F, -0.775F);
                    }, 350L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.6F, -0.5F, -0.2F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-2.024999F, 0.35F, -0.775F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.6F, -0.5F, -0.2F);
                        GL11.glRotatef(-98.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-2.024999F, 0.35F, -0.775F);
                    }, 100L, 130L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.0F, -0.1F, -0.2F);
                        GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(32.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 1.674999F, 0.15F);
                    }, 400L, 100L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.0F, -0.1F, -0.2F);
                        GL11.glRotatef(-52.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 1.674999F, 0.4F);
                    }, 200L, 70L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.0F, -0.1F, -0.2F);
                        GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 1.674999F, 0.6F);
                    }, 180L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.6F, -0.5F, -0.2F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-2.024999F, 0.35F, -0.775F);
                    }, 300L, 50L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.6F, -0.5F, -0.2F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-2.024999F, 0.35F, -0.775F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.6F, -0.5F, -0.2F);
                        GL11.glRotatef(-98.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-2.024999F, 0.35F, -0.775F);
                    }, 170L, 0L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.NATOMag1,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.3F, 0.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.NATOMag1,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.3F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glTranslatef(-0.5F, 2.8F, -2.0F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.12F, 0.3F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.04F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.0785F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.078F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, -0.01F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.275F, 1.199999F, -1.05F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.825F, 0.825F, 0.0F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.7F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                        GL11.glTranslatef(0.025F, -0.35F, -0.375F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.2F, 2.2F, 2.2F);
                        GL11.glTranslatef(1.0F, 0.3F, -0.3F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.825F, -0.9F, 0.7F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.85F, -0.925F, 0.375F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.85F, -0.925F, 0.375F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.1F, -0.05F, 0.225F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.3F, 0.2F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.1F, -0.05F, 0.225F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.85F, -0.925F, 0.375F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.85F, -0.925F, 0.375F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.825F, -0.9F, 0.7F);
                    }, 170L, 0L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.475F, 0.1F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.0F)
            .withSpawnEntityGravityVelocity(0.028F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
