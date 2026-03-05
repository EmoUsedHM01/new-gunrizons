package com.gtnewhorizon.newgunrizons.items.factories.guns.pistol;

import java.util.Arrays;
import java.util.function.Consumer;

import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.factories.guns.GunFactory;
import com.gtnewhorizon.newgunrizons.model.action.M9Top;
import com.gtnewhorizon.newgunrizons.model.sight.M9rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.m9frontsight;
import com.gtnewhorizon.newgunrizons.model.weapon.M9;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;

public class M9Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new ItemWeapon.Builder()).withModId("newgunrizons")
            .withName("M9")
            .withFireRate(0.4F)
            .withRecoil(5.0F)

            .withMaxShots(1)
            .withShootSound("M9")
            .withSilencedShootSound("m9silenced")
            .withReloadSound("PistolReload")
            .withUnloadSound("pistolUnload")
            .withReloadingTime(50L)
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.4F)
            .withFlashScale(() -> { return 0.5F; })
            .withFlashOffsetX(() -> { return 0.2F; })
            .withFlashOffsetY(() -> { return 0.1F; })
            .withInaccuracy(3.0F)
            .withCreativeTab(NewGunrizonsMod.PistolsTab)
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Pistol",
                    "Damage: 5",
                    "Caliber: 9mm",
                    "Magazines:",
                    "15rnd 9mm Magazine",
                    "Fire Rate: Semi");
            })
            .withCompatibleAttachment(AuxiliaryAttachments.M9Top, true, (model) -> {
                if (model instanceof M9Top) {
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof M9rearsight) {
                    GL11.glTranslatef(-0.155F, -1.175F, -0.15F);
                    GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.4000000059604645D);
                } else if (model instanceof m9frontsight) {
                    GL11.glTranslatef(-0.15F, -1.175F, -1.85F);
                    GL11.glScaled(0.25D, 0.25D, 0.30000001192092896D);
                }

            })
            .withCompatibleAttachment(
                Magazines.M9BerettaMag,
                (Consumer) ((model) -> { GL11.glTranslatef(0.0F, 0.1F, 0.1F); }))
            .withCompatibleAttachment(Attachments.Silencer9mm, (model) -> {
                GL11.glTranslatef(-0.22F, -1.22F, -4.2F);
                GL11.glScaled(1.2999999523162842D, 1.2999999523162842D, 1.2999999523162842D);
            })
            .withTextureName("M9")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new M9())
                    .withEntityPositioning((itemStack) -> {
                        GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
                    })
                    .withInventoryPositioning((itemStack) -> {
                        GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
                        GL11.glTranslatef(0.0F, 0.8F, 0.0F);
                        GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
                    })
                    .withThirdPersonPositioning((renderContext) -> {
                        GL11.glScaled(0.5D, 0.5D, 0.5D);
                        GL11.glTranslatef(-1.8F, -1.0F, 2.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(-0.85F, 0.9F, -3.4F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(-0.85F, 0.9F, -3.1F);
                        GL11.glRotatef(-6.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningCustomRecoiled(
                        AuxiliaryAttachments.M9Top.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.M9Top.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningCustomRecoiled(Magazines.M9BerettaMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M9BerettaMag, (renderContext) -> {})
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(0.35F, 0.6F, -2.4F);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {}

                    })
                    .withFirstPersonCustomPositioning(Magazines.M9BerettaMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.M9Top.getRenderablePart(),
                        (renderContext) -> {
                            if (renderContext.getWeaponInstance()
                                .getAmmo() == 0) {
                                GL11.glTranslatef(0.0F, 0.0F, 0.5F);
                            }

                        })
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.299999F, 1.025F, -2.775002F);
                    }, 280L, 0L), new Transition((renderContext) -> {
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.299999F, 1.025F, -2.775002F);
                    }, 300L, 100L), new Transition((renderContext) -> {
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.299999F, 1.025F, -2.775002F);
                    }, 120L, 150L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(-0.75F, 1.5F, -2.7F);
                    }, 200L, 50L), new Transition((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(-0.75F, 1.8F, -2.3F);
                    }, 120L, 0L))
                    .withFirstPersonPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-13.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(-1.2F, 1.45F, -3.2F);
                    }, 150L, 50L))
                    .withFirstPersonCustomPositioningUnloading(
                        Magazines.M9BerettaMag,
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        Magazines.M9BerettaMag,
                        new Transition((renderContext) -> { GL11.glTranslatef(0.05F, 1.8F, 0.4F); }, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L),
                        new Transition((renderContext) -> {}, 250L, 1000L))
                    .withFirstPersonCustomPositioningUnloading(
                        AuxiliaryAttachments.M9Top.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.M9Top.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(0.35F, 0.63F, -2.799999F);
                        if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {}

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.9F, 2.25F, -1.699999F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-2.375F, 0.7F, -0.125F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.05F, -0.75F, 0.2F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.025F, -0.875F, 0.25F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.425F, -1.05F, 0.55F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.375F, -0.95F, 0.375F);
                    }, 50L, 200L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.375F, -0.95F, 0.375F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.375F, -0.675F, 0.075F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-185.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.3F, -0.675F, 0.175F);
                    }, 250L, 0L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    }, 250L, 0L))
                    .withFirstPersonLeftHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, -0.825F, 0.25F);
                    }, 50L, 200L))
                    .withFirstPersonRightHandPositioningUnloading(new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    }, 250L, 1000L))
                    .withFirstPersonHandPositioningZooming((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.15F, -0.775F, 0.275F);
                    }, (renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.348F, -0.54F, 0.144F);
                    })
                    .build())
            .withSpawnEntityDamage(5.0F)
            .withSpawnEntityGravityVelocity(0.02F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
