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
import com.gtnewhorizon.newgunrizons.model.sight.LPscope;
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
import com.gtnewhorizon.newgunrizons.model.weapon.POFUSA;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class POFUSAFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("POF308")
            .withFireRate(0.55F)
            .withRecoil(6.0F)
            .withZoom(0.9F)
            .withMaxShots(Integer.MAX_VALUE, 1)
            .withShootSound("POFUSA")
            .withSilencedShootSound("ar15silenced")
            .withReloadSound("m4reload")
            .withUnloadSound("m4unload")
            .withReloadingTime(50L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.1F; })
            .withFlashOffsetY(() -> { return 0.1F; })
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Carbine",
                    "Damage: 8",
                    "Caliber: .308 Winchester",
                    "Magazines:",
                    "10rnd .308 Winchester Magazine",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(Magazines.Mag308, (Consumer) ((model) -> {
                GL11.glTranslatef(0.0F, 0.0F, 0.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }))
            .withCompatibleAttachment(AuxiliaryAttachments.AR15Action, true, (model) -> {
                GL11.glTranslatef(-0.175F, -1.28F, -0.67F);
                GL11.glScaled(0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(0.162F, -1.75F, 1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(-0.055F, -1.35F, -4.05F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof P90iron) {
                    GL11.glTranslatef(-0.185F, -1.45F, -3.75F);
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
                    GL11.glTranslatef(-0.164F, -1.255F, -4.64F);
                    GL11.glScaled(0.5D, 0.4000000059604645D, 1.100000023841858D);
                    GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(-0.17F, -1.46F, -4.64F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 1.0D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.16F, -1.5F, -0.3F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(0.255F, -1.55F, -2.25F);
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
                    GL11.glTranslatef(0.092F, -1.91F, -0.9F);
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
                    GL11.glTranslatef(-0.044F, -1.1F, -0.3F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                    GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(-0.107F, -1.37F, -0.25F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MBUSiron) {
                    GL11.glTranslatef(-0.161F, -1.47F, -0.17F);
                    GL11.glScaled(0.3499999940395355D, 0.33000001311302185D, 0.3499999940395355D);
                }

            })
            .withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
                GL11.glTranslatef(-0.265F, -1.41F, -0.3F);
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
                GL11.glTranslatef(-0.17F, -1.14F, -0.45F);
                GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
                GL11.glTranslatef(-0.3F, -1.38F, -0.3F);
                GL11.glScaled(0.75D, 0.75D, 0.75D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.272F, 0.67F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.HP, (player, stack) -> {
                GL11.glTranslatef(-0.3F, -1.38F, -0.5F);
                GL11.glScaled(0.75D, 0.75D, 0.75D);
            }, (model) -> {
                if (model instanceof LPscope) {
                    GL11.glTranslatef(0.237F, -0.235F, 1.16F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.072F, -1.25F, -0.9F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.53F, -0.9F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.17F, -1.42F, -0.6F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.125F, -1.53F, -0.7F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
                    GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -1.545F, -0.5F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
                    GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -1.55F, -0.5F);
                    GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
                    GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.12F, -1.53F, -1.05F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Bipod, (model) -> {
                GL11.glTranslatef(-0.2F, -0.41F, -4.3F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureNames("AK12")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new POFUSA())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
                        GL11.glTranslatef(1.0F, 2.0F, -1.2F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                        GL11.glTranslatef(-1.7F, -0.8F, 1.9F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(1.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 1.05F, -0.8F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(1.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 1.05F, -0.7F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.35F, 0.9F, -0.4F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.001F, 0.095F, 0.65F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {}

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(-0.003F, 0.03F, 0.45F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.001F, 0.12F, 0.5F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.001F, 0.12F, 0.25F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.1F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.3F);
                        }

                    })
                    .withFirstPersonCustomPositioning(Magazines.Mag308, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AR15Action.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.65F, 0.95F, -0.275F);
                    }, 350L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.65F, 0.95F, -0.275F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(32.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.65F, 0.92F, -0.275F);
                    }, 100L, 130L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 0.95F, -0.275F);
                    }, 290L, 10L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 0.95F, -0.275F);
                    }, 310L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.5F, 0.85F, -0.3F);
                        GL11.glRotatef(13.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                    }, 300L, 50L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.2F, 1.0F, -0.3F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(-0.24F, 1.0F, -0.3F);
                        GL11.glRotatef(-4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    }, 170L, 0L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.Mag308,
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.01F, 0.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                            GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.AR15Action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AR15Action.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(Magazines.Mag308, new Transition((renderContext) -> {
                        GL11.glTranslatef(0.3F, 1.7F, 0.1F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.01F, 0.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.01F, 0.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.35F, 0.9F, -0.525F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.001F, 0.095F, 0.75F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {}

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(-0.003F, 0.03F, 0.55F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.001F, 0.12F, 0.5F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.001F, 0.12F, 0.25F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.1F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                            GL11.glTranslatef(0.0F, 0.09F, 0.1F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.2F, 1.0F, -0.1F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.75F, 0.675F, 0.25F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glTranslatef(0.48F, 0.08F, -0.05F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.2F, 2.2F, 2.2F);
                        GL11.glTranslatef(1.0F, 0.4F, -0.4F);
                        GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.7F, 0.15F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-135.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.775F, 0.2F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -0.1F, 0.6F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(8.0F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
