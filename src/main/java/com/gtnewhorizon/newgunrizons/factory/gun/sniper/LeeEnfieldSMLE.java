package com.gtnewhorizon.newgunrizons.factory.gun.sniper;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.model.action.LeeEnfieldSMLEAction;
import com.gtnewhorizon.newgunrizons.model.ammo.LeeEnfieldClipBullets;
import com.gtnewhorizon.newgunrizons.model.magazine.LeeEnfieldClip;
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
import com.gtnewhorizon.newgunrizons.model.sight.PriscopicScope;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.sight.UnertlReticle;
import com.gtnewhorizon.newgunrizons.model.weapon.LeeEnfieldSMLEMk3;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class LeeEnfieldSMLE {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("LeeEnfieldSMLEMkIII")
            .withAmmoCapacity(10)
            .withFireRate(0.16F)
            .withEjectRoundRequired()
            .withEjectSpentRoundSound("springfieldboltaction")
            .withRecoil(6.0F)
            .withZoom(0.8F)
            .withMaxShots(1)
            .withShootSound("LeeEnfield")
            .withPumpTimeout(1400L)
            .withReloadSound("leeenfieldsmlereload")
            .withReloadingTime(500L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.1F; })
            .withFlashOffsetY(() -> { return 0.1F; })
            .withCreativeTab(NewGunrizonsMod.SnipersTab)
            .withCrafting(
                CraftingComplexity.MEDIUM,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                CommonProxy.MetalComponents,
                Blocks.planks)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Bolt-action rifle",
                    "Damage: 26",
                    "Cartridge:",
                    ".303 British Bullet",
                    "Fire Rate: Bolt Action");
            })
            .withCompatibleAttachment(AuxiliaryAttachments.LeeEnfieldSMLEaction, true, (model) -> {
                if (model instanceof LeeEnfieldSMLEAction) {}

            })
            .withCompatibleAttachment(AuxiliaryAttachments.LeeEnfieldClip, true, (model) -> {
                if (model instanceof LeeEnfieldClip) {
                    GL11.glTranslatef(-0.2F, -0.7F, -2.1F);
                    GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.LeeEnfieldClipBullets, true, (model) -> {
                if (model instanceof LeeEnfieldClipBullets) {
                    GL11.glTranslatef(-0.2F, -0.7F, -2.1F);
                    GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
                }

            })
            .withCompatibleBullet(Bullets.Bullet303British, (model) -> {})
            .withCompatibleAttachment(AuxiliaryAttachments.LeeEnfieldSMLESight, true, (model) -> {
                GL11.glTranslatef(-0.26F, -1.47F, -3.65F);
                GL11.glScaled(0.20000000298023224D, 0.25D, 0.20000000298023224D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(0.17F, -1.42F, 0.43F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof M4Iron2) {
                    GL11.glTranslatef(-0.11F, -1.29F, -9.7F);
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
                    GL11.glTranslatef(-0.27F, -1.58F, -6.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.22F, -1.94F, -1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.207F, -1.245F, -9.165F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(0.165F, -1.65F, 1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.25F, -1.55F, -2.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(-0.24F, -1.39F, -8.35F);
                    GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 1.0D);
                } else if (model instanceof M14Iron) {
                    GL11.glTranslatef(0.129F, -1.63F, -2.08F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof MP5Iron) {
                    GL11.glTranslatef(0.215F, -1.54F, 1.2F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCompatibleAttachment(Attachments.PriscopicScope, (player, stack) -> {
                GL11.glTranslatef(0.62F, -1.57F, -3.6F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }, (model) -> {
                if (model instanceof PriscopicScope) {
                    GL11.glTranslatef(-0.508F, 0.46F, -0.55F);
                    GL11.glScaled(0.2199999988079071D, 0.2199999988079071D, 0.2199999988079071D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glTranslatef(-0.48F, 0.285F, 2.28F);
                    GL11.glScaled(0.014999999664723873D, 0.014999999664723873D, 0.014999999664723873D);
                }

            })
            .withTextureNames("LeeEnfieldSMLE")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new LeeEnfieldSMLEMk3())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.3199999928474426D, 0.3199999928474426D, 0.3199999928474426D);
                        GL11.glTranslatef(1.0F, 0.8F, 0.0F);
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
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.4F, 1.22F, 0.2F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.4F, 1.22F, 0.6F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.39F, 0.92F, 0.7F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                            GL11.glTranslatef(-0.003F, 0.105F, 1.3F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PriscopicScope)) {
                            GL11.glTranslatef(-0.3F, -0.12F, 0.93F);
                        }

                    })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.LeeEnfieldSMLEaction.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.LeeEnfieldSMLESight.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.LeeEnfieldClip.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.LeeEnfieldClipBullets.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 1.924999F, 1.1F);
                    }, 400L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 1.924999F, 1.1F);
                    }, 300L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.5F);
                    }, 200L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.4F);
                    }, 470L, 130L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 1.924999F, 1.3F);
                    }, 460L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-34.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(29.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 1.924999F, 1.3F);
                    }, 480L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-36.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(28.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.3F);
                    }, 100L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-38.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.1F, 1.3F);
                    }, 100L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.4F);
                    }, 470L, 130L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 1.924999F, 1.3F);
                    }, 460L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-34.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(29.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 1.924999F, 1.3F);
                    }, 480L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-36.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(28.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.3F);
                    }, 100L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-38.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.1F, 1.3F);
                    }, 100L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.3F);
                    }, 400L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-37.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(31.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.76F, 2.0F, 1.1F);
                    }, 200L, 100L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.1F);
                    }, 260L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(-33.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.775F, 2.0F, 1.1F);
                    }, 320L, 0L))
                    .withFirstPersonPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.174999F, 0.425F);
                    }, 200L, 10L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-9.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.174999F, 0.46F);
                    }, 210L, 10L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.174999F, 1.0F);
                    }, 200L, 20L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-9.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.174999F, 0.425F);
                    }, 220L, 10L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.174999F, 0.425F);
                    }, 210L, 10L))
                    .withFirstPersonCustomPositioningEjectSpentRound(
                        AuxiliaryAttachments.LeeEnfieldSMLESight.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 300L),
                        new Transition((renderContext) -> {}, 250L, 0L),
                        new Transition((renderContext) -> {}, 250L, 0L),
                        new Transition((renderContext) -> {}, 250L, 0L))
                    .withFirstPersonCustomPositioningEjectSpentRound(
                        AuxiliaryAttachments.LeeEnfieldSMLEaction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 300L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 0L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 0L),
                        new Transition((renderContext) -> {}, 250L, 0L))
                    .withFirstPersonCustomPositioningEjectSpentRound(
                        AuxiliaryAttachments.LeeEnfieldClip.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 300L),
                        new Transition((renderContext) -> {}, 250L, 0L),
                        new Transition((renderContext) -> {}, 250L, 0L),
                        new Transition((renderContext) -> {}, 250L, 0L))
                    .withFirstPersonCustomPositioningEjectSpentRound(
                        AuxiliaryAttachments.LeeEnfieldClipBullets.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 300L),
                        new Transition((renderContext) -> {}, 250L, 0L),
                        new Transition((renderContext) -> {}, 250L, 0L),
                        new Transition((renderContext) -> {}, 250L, 0L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.LeeEnfieldClip.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.6F, -0.2F, 0.0F);
                            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, -0.6F, 0.0F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, -0.6F, 0.0F); }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, -1.5F, 1.0F);
                            GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.6F, -0.2F, 0.0F);
                            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, -0.6F, 0.0F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, -0.6F, 0.0F); }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, -1.5F, 1.0F);
                            GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, -1.5F, 1.0F);
                            GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, -1.5F, 1.0F);
                            GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.0F, -1.5F, 1.0F);
                            GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.LeeEnfieldClipBullets.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.6F, -0.2F, 0.0F);
                            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, -0.6F, 0.0F); }, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(-0.5F, 1.0F, 0.5F); }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.6F, -0.2F, 0.0F);
                            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, -0.6F, 0.0F); }, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.LeeEnfieldSMLESight.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.LeeEnfieldSMLEaction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 1.1F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-1.38F, -1.05F, 0.0F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glTranslatef(0.39F, 0.89F, 0.4F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                            GL11.glTranslatef(-0.003F, 0.105F, 1.5F);
                        }

                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PriscopicScope)) {
                            GL11.glTranslatef(-0.3F, -0.12F, 1.15F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, 1.4F, 0.625F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.2F, 0.55F, 1.3F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.425F, -0.05F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 5.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.08F, 0.08F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.425F, -0.05F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 3.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.36F, 0.13F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.205F, -0.325F, 0.0F);
                    })
                    .withFirstPersonHandPositioningRunning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.205F, -0.325F, 0.0F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.2F, -0.2F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.2F, -0.125F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.375F, -0.225F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.225F, -0.275F, -0.5F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, 0.2F, -0.075F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.125F, 0.15F, -0.325F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.0F, -0.1F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.0F, -0.1F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.225F, -0.275F, -0.5F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, 0.2F, -0.075F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.125F, 0.15F, -0.325F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.0F, -0.1F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.0F, -0.1F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.375F, -0.225F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.2F, -0.125F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.2F, -0.2F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.425F, -0.05F);
                    }, 250L, 1000L))
                    .withFirstPersonLeftHandPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.325F, 0.15F, 0.05F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.2F, -0.2F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.2F, -0.125F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.375F, -0.225F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.2F, -0.125F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.2F, -0.2F);
                    }, 250L, 1000L))
                    .build())
            .withSpawnEntityDamage(26.0F)
            .withSpawnEntityGravityVelocity(0.0F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
