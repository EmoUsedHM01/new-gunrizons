package com.gtnewhorizon.newgunrizons.items.factories.guns.sniper;

import java.util.Arrays;

import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.sight.LeupoldReticle;
import com.gtnewhorizon.newgunrizons.model.weapon.Remington700;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;

public class Remington700Factory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("Remington700")
            .withAmmoCapacity(6)
            .withMaxBulletsPerReload(6)
            .withFireRate(0.5F)
            .withEjectRoundRequired()
            .withIteratedLoad()
            .withEjectSpentRoundSound("r700action")
            .withPumpTimeout(1000L)
            .withFireRate(0.1F)
            .withRecoil(9.0F)

            .withMaxShots(1)
            .withPumpTimeout(850L)
            .withShootSound("remington700")
            .withAllReloadIterationsCompletedSound("r700boltforward")
            .withSilencedShootSound("ShotgunSilenced")
            .withReloadSound("r700boltback")
            .withReloadIterationSound("loadbullet")
            .withReloadingTime(1000L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withShellCasingEjectEnabled(false)
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.5F; })
            .withFlashOffsetX(() -> { return 0.11F; })
            .withFlashOffsetY(() -> { return 0.06F; })
            .withCreativeTab(NewGunrizonsMod.SnipersTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withInformationProvider(
                (stack) -> {
                    return Arrays.asList(
                        "Type: Sniper rifle",
                        "Damage: 27",
                        "Cartridge: .308 Winchester",
                        "Fire Rate: Bolt Action");
                })
            .withCompatibleAttachment(AuxiliaryAttachments.R700action, true, (model) -> {
                GL11.glTranslatef(-0.105F, -0.585F, 0.63F);
                GL11.glScalef(0.62F, 0.62F, 0.5F);
            })
            .withCompatibleAttachment(Attachments.Leupold, (player, stack) -> {
                GL11.glTranslatef(-0.105F, -0.5F, -0.43F);
                GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
            }, (model) -> {
                if (model instanceof LeupoldReticle) {
                    GL11.glTranslatef(0.076F, -0.52F, 4.0251F);
                    GL11.glScaled(0.09000000357627869D, 0.09000000357627869D, 0.09000000357627869D);
                }

            })
            .withCompatibleBullet(Bullets.Bullet308, (model) -> {})
            .withTextureName("Remington700")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new Remington700())
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
                        GL11.glTranslatef(-0.9F, 0.4F, 1.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningLoadIterationCompleted((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.4F, -1.0F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.4F, -1.3F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(12.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.45F, 0.48F, -1.15F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(0.22F, 0.15F, -1.2F);
                        GL11.glRotatef(-1.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Leupold)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.45F);
                        }

                    })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.R700action.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioningLoadIterationCompleted(
                        AuxiliaryAttachments.R700action.getRenderablePart(),
                        (renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        })
                    .withFirstPersonPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -1.1F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -0.9F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 150L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -0.9F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 150L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -1.1F);
                    }, 150L, 0L))
                    .withFirstPersonCustomPositioningEjectSpentRound(
                        AuxiliaryAttachments.R700action.getRenderablePart(),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, -0.01F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 170L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 150L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 150L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, -0.01F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 150L, 50L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.R700action.getRenderablePart(),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, -0.01F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 170L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 150L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L))
                    .withFirstPersonCustomPositioningAllLoadIterationsCompleted(
                        AuxiliaryAttachments.R700action.getRenderablePart(),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 150L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, -0.01F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 150L, 50L))
                    .withFirstPersonCustomPositioningLoadIteration(
                        AuxiliaryAttachments.R700action.getRenderablePart(),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(-0.59F, -0.43F, 0.5F);
                            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glScalef(1.0F, 1.0F, 1.0F);
                        }, 250L, 50L))
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -1.1F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -0.9F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 220L, 40L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.35F, -1.0F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.4F, -1.0F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    }, 200L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.4F, -1.0F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                    }, 220L, 0L))
                    .withFirstPersonPositioningAllLoadIterationsCompleted(new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -0.9F);
                        GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
                    }, 230L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(43.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.3F, 0.35F, -1.1F);
                    }, 150L, 0L))
                    .withFirstPersonPositioningLoadIteration(new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.4F, -1.0F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                    }, 150L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.4F, -1.0F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    }, 170L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.38F, 0.43F, -1.0F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                    }, 290L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.38F, 0.42F, -1.0F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                    }, 180L, 0L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(-0.4F, 0.41F, -1.0F);
                        GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
                    }, 200L, 0L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glTranslatef(0.25F, 0.15F, -1.3F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Leupold)) {
                            GL11.glTranslatef(0.0F, 0.185F, 0.45F);
                        }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, 0.65F, -0.5F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(4.0F, 4.0F, 4.0F);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.475F, -0.025F, -0.625F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.925F, 0.03F);
                    })
                    .withFirstPersonHandPositioningLoadIterationCompleted((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.925F, 0.03F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.18F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.925F, 0.03F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 2.5F);
                        GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.0F, -1.05F, 0.55F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.925F, 0.03F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.5F, -0.225F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.375F, -0.6F, -0.2F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-145.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.575F, -0.325F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.925F, 0.03F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.925F, 0.03F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.7F, -0.925F, 0.03F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningLoadIteration(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningLoadIteration(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.725F, 0.0F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.55F, 0.025F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.475F, 0.025F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.275F, -0.55F, 0.025F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.3F, -0.725F, 0.0F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningAllLoadIterationsCompleted(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningAllLoadIterationsCompleted(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-145.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.575F, -0.325F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.375F, -0.6F, -0.2F);
                    }, 250L, 50L))
                    .withFirstPersonLeftHandPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.1F, -0.5F, -0.225F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 5.5F);
                        GL11.glRotatef(-0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-52.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.125F, -0.475F, -0.275F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningEjectSpentRound(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.375F, -0.6F, -0.2F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-145.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.575F, -0.325F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-145.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.45F, -0.575F, -0.325F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.375F, -0.6F, -0.2F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(27.0F)
            .withSpawnEntityGravityVelocity(0.0F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
