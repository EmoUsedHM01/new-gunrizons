package com.gtnewhorizon.newgunrizons.items.factories.guns.sniper;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.misc.PUmount;
import com.gtnewhorizon.newgunrizons.model.misc.SVTmount;
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
import com.gtnewhorizon.newgunrizons.model.sight.OKP7;
import com.gtnewhorizon.newgunrizons.model.sight.OKP7reticle;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.PSO12;
import com.gtnewhorizon.newgunrizons.model.sight.PSO1reticle;
import com.gtnewhorizon.newgunrizons.model.sight.PUreticle;
import com.gtnewhorizon.newgunrizons.model.sight.PUscope;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.SKS;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;

public class SKSFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("SKS")
            .withFireRate(0.3F)
            .withRecoil(4.0F)
            .withAmmoCapacity(10)

            .withMaxShots(1)
            .withShootSound("sks")
            .withSilencedShootSound("AKsilenced")
            .withReloadSound("sksreload")
            .withUnloadSound("svt40unload")
            .withReloadingTime(45L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.4F; })
            .withFlashOffsetX(() -> { return 0.15F; })
            .withFlashOffsetY(() -> { return 0.1F; })
            .withCreativeTab(NewGunrizonsMod.SnipersTab)
            .withCrafting(
                CraftingComplexity.MEDIUM,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                CommonProxy.MetalComponents,
                Blocks.planks)
            .withInformationProvider(
                (stack) -> {
                    return Arrays
                        .asList("Type: Semi-automatic Carbine", "Damage: 8", "Cartridge: 7.62x39mm", "Fire Rate: Semi");
                })
            .withCompatibleAttachment(Attachments.PUscope, (player, stack) -> {
                GL11.glTranslatef(0.4F, -1.15F, 0.55F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            }, (model) -> {
                if (model instanceof PUscope) {
                    GL11.glTranslatef(-0.508F, 0.46F, -0.55F);
                    GL11.glScaled(0.2199999988079071D, 0.2199999988079071D, 0.2199999988079071D);
                } else if (model instanceof PUmount) {
                    GL11.glTranslatef(-0.508F, 0.46F, -0.55F);
                    GL11.glScaled(0.2199999988079071D, 0.2199999988079071D, 0.2199999988079071D);
                } else if (model instanceof SVTmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUreticle) {
                    GL11.glTranslatef(-0.5F, 0.23F, 0.82F);
                    GL11.glScaled(0.029999999329447746D, 0.029999999329447746D, 0.029999999329447746D);
                }

            })
            .withCompatibleAttachment(Attachments.PSO1, (player, stack) -> {
                GL11.glTranslatef(0.07F, -0.6F, 0.4F);
                GL11.glScaled(0.6499999761581421D, 0.6499999761581421D, 0.6499999761581421D);
            }, (model) -> {
                if (model instanceof PSO1reticle) {
                    GL11.glTranslatef(-0.212F, -0.486F, 1.27F);
                    GL11.glScaled(0.017000000923871994D, 0.017000000923871994D, 0.017000000923871994D);
                } else if (model instanceof PSO12) {
                    GL11.glTranslatef(-0.27F, -0.6F, 1.21F);
                    GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
                }

            })
            .withCompatibleAttachment(Attachments.OKP7, (model) -> {
                if (model instanceof OKP7) {
                    GL11.glTranslatef(-0.13F, -0.43F, 0.22F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof OKP7reticle) {
                    GL11.glTranslatef(-0.07F, -0.9F, -0.0F);
                    GL11.glScaled(0.03999999910593033D, 0.03999999910593033D, 0.03999999910593033D);
                }

            })
            .withCompatibleAttachment(AuxiliaryAttachments.AKpart, true, (model) -> {
                GL11.glTranslatef(-0.1F, -0.43F, -5.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 2.700000047683716D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.SKSaction, true, (model) -> {})
            .withCompatibleAttachment(Attachments.Silencer762x39, (model) -> {
                GL11.glTranslatef(-0.2F, -0.62F, -6.77F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCompatibleBullet(Bullets.Bullet762x39, (model) -> {})
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof AKMiron1) {
                    GL11.glTranslatef(0.125F, -1.8F, -0.5F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AKMiron2) {
                    GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(-0.175F, -0.99F, -1.35F);
                    GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.75D);
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
                    GL11.glTranslatef(-0.13F, -0.71F, -5.15F);
                    GL11.glScaled(0.30000001192092896D, 0.3499999940395355D, 0.5D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(0.165F, -1.65F, 1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
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
            .withCompatibleAttachment(
                AuxiliaryAttachments.SKSmag1,
                true,
                (model) -> { GL11.glTranslatef(-0.1F, 1.1F, 2.0F); })
            .withCompatibleAttachment(
                AuxiliaryAttachments.SKSmag2,
                true,
                (model) -> { GL11.glTranslatef(-0.1F, 1.1F, 2.0F); })
            .withTextureName("sks")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new SKS())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
                        GL11.glTranslatef(1.0F, 2.0F, -1.2F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glTranslatef(-1.0F, 0.3F, 1.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.63F, -1.5F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(5.0F, 5.0F, 5.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.45F, 0.63F, -1.3F);
                        GL11.glRotatef(-1.3F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.25F, 0.34F, -0.9F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                            GL11.glTranslatef(0.005F, 0.158F, -0.25F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                            GL11.glTranslatef(-0.014F, 0.162F, -0.05F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.OKP7)) {
                            GL11.glTranslatef(-0.011F, 0.147F, -0.5F);
                        }

                    })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.SKSaction.getRenderablePart(),
                        (renderContext) -> {
                            if (renderContext.getWeaponInstance()
                                .getAmmo() == 0) {}

                        })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.SKSaction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.SKSaction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.SKSmag1.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.SKSmag1.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.SKSmag1.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.SKSmag2.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.SKSmag2.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.SKSmag2.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.5F, -1.0F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 300L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.5F, -1.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 150L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.6F, -1.0F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 350L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.6F, -1.0F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                    }, 330L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(39.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.6F, -1.0F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 340L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.6F, -1.0F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 300L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.67F, -1.0F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    }, 310L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.67F, -1.0F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 320L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.6F, -1.1F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                    }, 80L, 0L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.SKSaction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 200L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AKpart.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L),
                        new Transition((renderContext) -> {}, 250L, 200L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.SKSmag1.getRenderablePart(),
                        new Transition((renderContext) -> {
                            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                            GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                            GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                            GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-1.2F, -0.9F, -3.0F);
                            GL11.glRotatef(17.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -1.5F, -3.2F);
                            GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -2.1F, -3.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -1.0F, -3.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -1.0F, -3.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -1.0F, -3.0F);
                        }, 250L, 200L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.SKSmag2.getRenderablePart(),
                        new Transition((renderContext) -> {
                            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                            GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                            GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
                            GL11.glTranslatef(-0.8F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-1.2F, -0.9F, -3.0F);
                            GL11.glRotatef(17.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -1.5F, -3.2F);
                            GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -2.1F, -3.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -2.1F, -3.07F);
                            GL11.glRotatef(2.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-0.08F, -2.1F, -3.07F);
                            GL11.glRotatef(2.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 200L),
                        new Transition((renderContext) -> {
                            GL11.glScalef(0.6F, 0.6F, 0.6F);
                            GL11.glTranslatef(-8.5F, -5.0F, -2.0F);
                            GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        }, 250L, 200L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(4.0D, 4.0D, 4.0D);
                        GL11.glTranslatef(0.25F, 0.34F, -1.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PUscope)) {
                            GL11.glTranslatef(0.005F, 0.158F, -0.2F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
                            GL11.glTranslatef(-0.014F, 0.162F, 0.0F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.OKP7)) {
                            GL11.glTranslatef(-0.011F, 0.147F, -0.5F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.35F, 0.6F, -0.4F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 0.075F, -0.725F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.675F, 0.175F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.608F, -0.868999F, 0.051F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-53.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.08F, -0.475F, -0.28F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.225F, 0.05F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.225F, -0.35F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.25F, -0.725F, 0.325F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.05F, -0.275F, 0.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.3F, -0.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.3F, -0.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.075F, -0.35F, 0.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.225F, -0.35F, 0.05F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.225F, 0.05F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(8.0F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
