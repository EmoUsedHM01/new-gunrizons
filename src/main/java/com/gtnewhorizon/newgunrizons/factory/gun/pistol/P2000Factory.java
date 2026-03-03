package com.gtnewhorizon.newgunrizons.factory.gun.pistol;

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
import com.gtnewhorizon.newgunrizons.model.action.P2000Top;
import com.gtnewhorizon.newgunrizons.model.sight.P2000rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.P226frontsight;
import com.gtnewhorizon.newgunrizons.model.weapon.P2000;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class P2000Factory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("HKP2000")
            .withFireRate(0.6F)
            .withRecoil(6.0F)
            .withZoom(0.9F)
            .withMaxShots(1)
            .withShootSound("P2000")
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
            .withCompatibleAttachment(AuxiliaryAttachments.P2000Top, true, (model) -> {
                if (model instanceof P2000Top) {
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof P2000rearsight) {
                    GL11.glTranslatef(-0.167F, -1.14F, -0.0F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof P226frontsight) {
                    GL11.glTranslatef(-0.15F, -1.175F, -1.95F);
                    GL11.glScaled(0.25D, 0.25D, 0.30000001192092896D);
                }

            })
            .withCompatibleAttachment(
                Magazines.M9BerettaMag,
                (Consumer) ((model) -> { GL11.glTranslatef(0.0F, 0.1F, 0.1F); }))
            .withCompatibleAttachment(Attachments.Laser, (p, s) -> {
                GL11.glTranslatef(0.01F, -0.7F, -2.2F);
                GL11.glScaled(1.100000023841858D, 1.100000023841858D, 1.100000023841858D);
                GL11.glRotatef(-90.0F, 0.0F, 0.0F, -4.0F);
            })
            .withCompatibleAttachment(Attachments.Silencer9mm, (model) -> {
                GL11.glTranslatef(-0.25F, -1.15F, -4.61F);
                GL11.glScaled(1.5D, 1.5D, 1.5D);
            })
            .withTextureNames("GunmetalTexture", "Electric")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new P2000())
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
                        AuxiliaryAttachments.P2000Top.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningCustomZoomingRecoiled(
                        AuxiliaryAttachments.P2000Top.getRenderablePart(),
                        (renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); })
                    .withFirstPersonPositioningCustomRecoiled(Magazines.M9BerettaMag, (renderContext) -> {})
                    .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M9BerettaMag, (renderContext) -> {})
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(0.35F, 0.56F, -2.4F);
                        GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {}

                    })
                    .withFirstPersonCustomPositioning(Magazines.M9BerettaMag, (renderContext) -> {})
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.P2000Top.getRenderablePart(),
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
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(-12.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.125F, 1.149999F, -2.950002F);
                    }, 200L, 50L), new Transition((renderContext) -> {
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(4.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.125F, 1.0F, -3.0F);
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
                        AuxiliaryAttachments.P2000Top.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.P2000Top.getRenderablePart(),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.5F); }, 250L, 1000L),
                        new Transition((renderContext) -> { GL11.glTranslatef(0.0F, 0.0F, 0.0F); }, 250L, 1000L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(3.0D, 3.0D, 3.0D);
                        GL11.glTranslatef(0.35F, 0.56F, -2.799999F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {}

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
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.425F, -0.525F, 0.14F);
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
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, -0.825F, 0.25F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.325F, -0.825F, 0.25F);
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
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.425F, -0.525F, 0.14F);
                    }, 250L, 0L), new Transition((renderContext) -> {
                        GL11.glScalef(4.5F, 4.5F, 4.5F);
                        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.425F, -0.525F, 0.14F);
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
            .withSpawnEntityGravityVelocity(0.016F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
