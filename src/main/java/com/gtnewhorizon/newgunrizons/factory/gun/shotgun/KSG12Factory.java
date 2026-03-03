package com.gtnewhorizon.newgunrizons.factory.gun.shotgun;

import java.util.Arrays;

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
import com.gtnewhorizon.newgunrizons.model.sight.Acog2;
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
import com.gtnewhorizon.newgunrizons.model.weapon.KSG12;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class KSG12Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("KSG12")
            .withAmmoCapacity(15)
            .withMaxBulletsPerReload(7)
            .withFireRate(0.5F)
            .withEjectRoundRequired()
            .withIteratedLoad()
            .withEjectSpentRoundSound("KSG12Pump")
            .withPumpTimeout(800L)
            .withRecoil(9.0F)
            .withZoom(0.9F)
            .withMaxShots(1)
            .withShootSound("KSG12")
            .withSilencedShootSound("ShotgunSilenced")
            .withReloadSound("drawweapon")
            .withReloadIterationSound("loadshell")
            .withReloadingTime(15L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withInaccuracy(10.0F)
            .withPellets(10)
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.1F; })
            .withFlashOffsetY(() -> { return 0.15F; })
            .withShellCasingEjectEnabled(false)
            .withCreativeTab(NewGunrizonsMod.ShotgunsTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Bullpup pump-action shotgun",
                    "Damage per Pellet: 5",
                    "Pellets per Shot: 10",
                    "Ammo: 12 Gauge Shotgun Shell",
                    "Fire Rate: Pump-Action");
            })
            .withCompatibleAttachment(
                AuxiliaryAttachments.KSGPump,
                true,
                (model) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
            .withCompatibleBullet(Bullets.ShotgunShell, (model) -> {})
            .withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(0.17F, -1.54F, 0.32F);
                    GL11.glScaled(0.25D, 0.27000001072883606D, 0.25D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(0.24F, -1.53F, -1.9F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof P90iron) {
                    GL11.glTranslatef(0.26F, -1.55F, -2.35F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(0.13F, -1.55F, -3.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(0.12F, -1.75F, -1.85F);
                    GL11.glScaled(0.5D, 0.6000000238418579D, 0.20000000298023224D);
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
                    GL11.glTranslatef(0.16F, -1.55F, -1.9F);
                    GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
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
                GL11.glTranslatef(0.055F, -1.55F, -0.2F);
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
            .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
                GL11.glTranslatef(0.15F, -1.3F, -0.2F);
                GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(0.25F, -1.38F, -0.6F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(0.2F, -1.62F, -0.6F);
                    GL11.glScaled(0.11999999731779099D, 0.11999999731779099D, 0.11999999731779099D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(0.264F, -1.4F, -0.3F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(0.202F, -1.64F, -0.15F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(0.264F, -1.4F, -0.3F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(0.202F, -1.64F, -0.15F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(0.27F, -1.4F, -0.2F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(0.202F, -1.6F, -0.6F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(0.155F, -1.53F, -0.2F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(0.2F, -1.63F, -0.3F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Silencer12Gauge, (model) -> {
                GL11.glTranslatef(0.107F, -1.32F, -4.0F);
                GL11.glScaled(1.2999999523162842D, 1.2999999523162842D, 1.2999999523162842D);
            })
            .withTextureNames("AK12", "Electric")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new KSG12())
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
                        GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                        GL11.glTranslatef(-1.8F, 0.3F, 1.5F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningLoadIterationCompleted((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(-0.625F, 1.224999F, -1.549999F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(-0.55F, 1.224999F, -1.3F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-3.0F, 0.0F, 0.0F, 1.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.05F, 1.174999F, -1.1F);
                        GL11.glRotatef(-3.0F, 0.0F, 0.0F, 1.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.108F, 1.2F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.01F, 0.9F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                        }

                    })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.KSGPump.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioningLoadIterationCompleted(
                        AuxiliaryAttachments.KSGPump.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(-0.625F, 1.224999F, -1.549999F);
                    }, 130L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(-0.625F, 1.224999F, -1.549999F);
                    }, 130L, 0L))
                    .withFirstPersonCustomPositioningEjectSpentRound(
                        AuxiliaryAttachments.KSGPump.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 170L, 50L),
                        new Transition((renderContext) -> {}, 150L, 50L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.KSGPump.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L))
                    .withFirstPersonCustomPositioningAllLoadIterationsCompleted(
                        AuxiliaryAttachments.KSGPump.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L))
                    .withFirstPersonCustomPositioningLoadIteration(
                        AuxiliaryAttachments.KSGPump.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L))
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    }, 250L, 50L))
                    .withFirstPersonPositioningAllLoadIterationsCompleted(new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(-0.625F, 1.224999F, -1.549999F);
                    }, 250L, 50L))
                    .withFirstPersonPositioningLoadIteration(new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    }, 150L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    }, 160L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    }, 200L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.725F, 0.575F, -1.374999F);
                    }, 170L, 0L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.025F, 1.174999F, -1.399999F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.0F, 0.108F, 1.4F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.0F, 0.01F, 1.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.1F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.07F, 0.0F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 1.249999F, -1.075F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.7F, 0.8F, -0.925F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.125F, -0.625F, -0.2F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.2F, 2.2F, 2.2F);
                        GL11.glTranslatef(1.0F, 1.0F, 0.2F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    })
                    .withFirstPersonHandPositioningLoadIterationCompleted((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, -0.75F, -0.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.125F, -0.625F, -0.2F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningLoadIteration(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, -1.125F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.65F, -0.9F, -0.225F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, -1.125F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, -1.075F, 0.425F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningLoadIteration(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningAllLoadIterationsCompleted(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.125F, -0.625F, -0.2F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningAllLoadIterationsCompleted(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.55F, -0.775F, 0.0F);
                    }, 250L, 1000L))
                    .build())
            .withSpawnEntityDamage(5.0F)
            .withSpawnEntityGravityVelocity(0.8F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
