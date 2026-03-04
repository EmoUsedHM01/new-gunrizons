package com.gtnewhorizon.newgunrizons.items.guns.assault;

import java.util.Arrays;
import java.util.function.Consumer;

import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.guns.GunFactory;
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
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.MicroT1;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex2;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.weapon.FamasF1;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

public class FamasF1Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("FamasF1")
            .withFireRate(0.75F)
            .withRecoil(2.0F)
            .withZoom(0.9F)
            .withMaxShots(Integer.MAX_VALUE, 3, 1)
            .withShootSound("Famas")
            .withSilencedShootSound("RifleSilencer")
            .withReloadSound("StandardReload")
            .withUnloadSound("Unload")
            .withReloadingTime(50L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.8F; })
            .withFlashOffsetX(() -> { return 0.11F; })
            .withFlashOffsetY(() -> { return 0.12F; })
            .withShellCasingForwardOffset(-0.1F)
            .withCreativeTab(NewGunrizonsMod.AssaultRiflesTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Bullpup assault rifle",
                    "Damage: 7",
                    "Caliber: 5.56x45mm NATO",
                    "Magazines:",
                    "30rnd 5.56x45mm NATO Magazine (Type 2)",
                    "Fire Rate: Auto");
            })
            .withCompatibleAttachment(Magazines.NATOFamasMag, (Consumer) ((model) -> {
                GL11.glTranslatef(-0.355F, 0.9F, 1.65F);
                GL11.glScaled(1.2000000476837158D, 1.5D, 1.5D);
            }))
            .withCompatibleAttachment(AuxiliaryAttachments.FamasCarryHandle, true, (model) -> {
                GL11.glTranslatef(-0.241F, -1.15F, -1.1F);
                GL11.glScaled(0.5199999809265137D, 0.550000011920929D, 0.8500000238418579D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.FamasBipod1, true, (model) -> {
                GL11.glTranslatef(-0.05F, -1.2F, -2.5F);
                GL11.glScaled(1.0D, 1.0D, 0.8799999952316284D);
                GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(1.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(1.8F, 0.0F, 1.0F, 0.0F);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.FamasBipod2, true, (model) -> {
                GL11.glTranslatef(-0.25F, -1.05F, -2.5F);
                GL11.glScaled(1.0D, 1.0D, 0.8799999952316284D);
                GL11.glRotatef(200.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-0.8F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(1.5F, 0.0F, 1.0F, 0.0F);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.FamasAction, true, (model) -> {})
            .withCompatibleAttachment(AuxiliaryAttachments.AKRail, true, (model) -> {
                GL11.glTranslatef(-0.215F, -1.9F, -3.0F);
                GL11.glScaled(0.699999988079071D, 1.2000000476837158D, 1.25D);
            })
            .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.3F, -1.5F, -0.3F);
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
                    GL11.glTranslatef(-0.155F, -1.925F, -3.0F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.699999988079071D);
                } else if (model instanceof AK47iron) {
                    GL11.glTranslatef(-0.23F, -2.22F, -0.17F);
                    GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.30000001192092896D);
                } else if (model instanceof G36CIron1) {
                    GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof G36CIron2) {
                    GL11.glTranslatef(-0.205F, -2.5F, -3.15F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron1) {
                    GL11.glTranslatef(0.165F, -1.65F, 1.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof ScarIron2) {
                    GL11.glTranslatef(0.25F, -1.55F, -2.0F);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof FALIron) {
                    GL11.glTranslatef(0.127F, -1.77F, -2.22F);
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
                GL11.glTranslatef(-0.297F, -2.0F, -0.7F);
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
            .withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
                GL11.glTranslatef(-0.195F, -1.65F, -0.7F);
                GL11.glScaled(0.5D, 0.5D, 0.5D);
            }, (model) -> {
                if (model instanceof Acog2) {
                    GL11.glTranslatef(0.15F, -1.035F, 1.513F);
                    GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
                }

            })
            .withCompatibleAttachment(Attachments.Reflex, (model) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.07F, -1.825F, -1.1F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Holo2, (model) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.05F, -1.85F, -0.8F);
                    GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Holographic2, (model) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.05F, -1.85F, -0.8F);
                    GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                } else if (model instanceof Holo2) {
                    GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.Kobra, (model) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.05F, -1.85F, -0.8F);
                    GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.12F, -2.11F, -0.9F);
                    GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
                }

            })
            .withCompatibleAttachment(Attachments.MicroT1, (model) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.165F, -2.0F, -0.6F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                } else if (model instanceof Reflex2) {
                    GL11.glTranslatef(-0.12F, -2.1F, -0.7F);
                    GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
                }

            })
            .withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
                GL11.glTranslatef(-0.2F, -1.11F, -5.5F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureName("AK12")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new FamasF1())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                        GL11.glTranslatef(0.0F, 0.8F, 0.0F);
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
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glTranslatef(-0.5F, 0.85F, -1.65F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glTranslatef(-0.5F, 0.85F, -1.5F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(1.5D, 1.5D, 1.5D);
                        GL11.glTranslatef(0.58F, 0.76F, 0.1F);
                        GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.005F, 0.275F, 1.0F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.005F, 0.25F, 0.5F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                        } else if (ItemWeapon
                            .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                                GL11.glTranslatef(0.0F, 0.19F, 0.5F);
                            } else
                            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                                GL11.glTranslatef(0.0F, 0.19F, 0.4F);
                            } else
                                if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                                    GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                                } else if (ItemWeapon
                                    .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                                        GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                                    }

                    })
                    .withFirstPersonCustomPositioning(Magazines.NATOFamasMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.AKRail.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.AKRail.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.AKRail.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.FamasBipod1.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.FamasBipod1.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.FamasBipod1.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.FamasBipod2.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.FamasBipod2.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.FamasBipod2.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.FamasAction.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        Magazines.NATOFamasMag.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        Magazines.NATOFamasMag.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.FamasAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.0F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.FamasAction.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.0F); })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
                    }, 300L, 60L), new Transition((renderContext) -> {
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
                    }, 400L, 200L), new Transition((renderContext) -> {
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, -0.1F, -0.125F);
                    }, 400L, 100L), new Transition((renderContext) -> {
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.175F, -0.1F, -0.125F);
                    }, 150L, 100L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
                    }, 150L, 50L), new Transition((renderContext) -> {
                        GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.NATOFamasMag,
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.2F, 0.5F, -0.2F);
                            GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L),
                        new Transition((renderContext) -> {
                            GL11.glTranslatef(0.2F, 0.9F, -0.2F);
                            GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                        }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.NATOFamasMag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.0F, 0.0F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.AKRail.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.AKRail.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.FamasAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.FamasAction.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 1.0F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.FamasBipod1.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.FamasBipod1.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.FamasBipod2.getRenderablePart(),
                        new Transition((renderContext) -> {}, 500L, 1000L),
                        new Transition((renderContext) -> {}, 500L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.FamasBipod2.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(1.5D, 1.5D, 1.5D);
                        GL11.glTranslatef(0.58F, 0.76F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
                            GL11.glTranslatef(0.005F, 0.275F, 1.05F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
                            GL11.glTranslatef(0.005F, 0.25F, 0.5F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
                            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
                            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
                        } else if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
                            GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                        } else if (ItemWeapon
                            .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
                                GL11.glTranslatef(0.0F, 0.19F, 0.5F);
                            } else
                            if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
                                GL11.glTranslatef(0.0F, 0.19F, 0.4F);
                            } else
                                if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                                    GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                                } else if (ItemWeapon
                                    .isActiveAttachment(renderContext.getWeaponInstance(), Attachments.MicroT1)) {
                                        GL11.glTranslatef(0.0F, 0.2F, 0.28F);
                                    }

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.7F, 1.3F, 0.3F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(2.0D, 2.0D, 2.0D);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.85F, 0.575F, -0.5F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.025F, -0.475F, -0.225F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.35F, -0.5F, 0.12F);
                    })
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.025F, -0.475F, -0.225F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.2F, -0.8F, 0.15F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.35F, -0.5F, 0.12F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.125F, -0.575F, 0.075F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 5.0F);
                        GL11.glRotatef(-130.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, -0.6F, -0.075F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.5F, 0.12F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.5F, 0.12F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.35F, -0.5F, 0.12F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.35F, -0.5F, 0.12F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 1.4F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.5F, 0.12F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.4F, -0.5F, 0.12F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(7.0F)
            .withSpawnEntityGravityVelocity(0.0118F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
