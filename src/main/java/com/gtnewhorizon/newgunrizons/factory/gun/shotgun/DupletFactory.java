package com.gtnewhorizon.newgunrizons.factory.gun.shotgun;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.factory.gun.GunFactory;
import com.gtnewhorizon.newgunrizons.model.weapon.Duplet;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

public class DupletFactory implements GunFactory {

    public Item createGun(CommonProxy commonProxy) {
        return (new Weapon.Builder()).withModId("newgunrizons")
            .withName("Duplet")
            .withAmmoCapacity(7)
            .withMaxBulletsPerReload(2)
            .withFireRate(0.6F)
            .withRecoil(8.0F)
            .withZoom(0.9F)
            .withMaxShots(1)
            .withShootSound("KSG12")
            .withSilencedShootSound("ShotgunSilenced")
            .withReloadSound("ShotgunReload")
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
            .withCreativeTab(NewGunrizonsMod.FunGunsTab)
            .withCrafting(
                CraftingComplexity.MEDIUM,
                CommonProxy.SteelPlate,
                CommonProxy.MiniSteelPlate,
                CommonProxy.MetalComponents,
                Blocks.planks)
            .withInformationProvider((stack) -> {
                return Arrays.asList(
                    "Type: Double Barrel Shotgun",
                    "Damage per Pellet: 10",
                    "Pellets per Shot: 10",
                    "Ammo: 12 Gauge Shotgun Shell",
                    "Fire Rate: Semi");
            })
            .withCompatibleAttachment(AuxiliaryAttachments.DupletBarrels, true, (model) -> {})
            .withCompatibleBullet(Bullets.ShotgunShell, (model) -> {})
            .withTextureNames("Duplet")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModId("newgunrizons")
                    .withModel(new Duplet())
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
                        GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
                        GL11.glTranslatef(-1.8F, -1.0F, 2.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioning((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glTranslatef(-0.5F, 1.1F, -0.475F);
                    })
                    .withFirstPersonPositioningRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glTranslatef(-0.5F, 1.1F, 0.2F);
                        GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glTranslatef(0.2F, 0.95F, -0.1F);
                        GL11.glRotatef(-2.5F, 1.0F, 0.0F, 0.0F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonCustomPositioning(
                        AuxiliaryAttachments.DupletBarrels.getRenderablePart(),
                        (renderContext) -> {})
                    .withFirstPersonPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 1.349999F, 1.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 1.349999F, 1.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 1.349999F, 1.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 1.349999F, 1.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 1.349999F, 1.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.575F, 1.349999F, 1.075F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.1F, -0.2F, -0.3F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 2.0F);
                        GL11.glTranslatef(1.0F, -1.2F, 0.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glTranslatef(0.1F, -0.2F, -0.3F);
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
                        GL11.glRotatef(-45.0F, 1.0F, 0.0F, 2.0F);
                        GL11.glTranslatef(1.0F, -1.2F, 0.0F);
                    }, 250L, 50L))
                    .withFirstPersonCustomPositioningReloading(
                        AuxiliaryAttachments.DupletBarrels.getRenderablePart(),
                        new Transition((renderContext) -> {}, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(50.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, -2.3F, 0.7F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(50.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, -2.3F, 0.7F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(50.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, -2.3F, 0.7F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(50.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, -2.3F, 0.7F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(50.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, -2.3F, 0.7F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {
                            GL11.glRotatef(50.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(0.0F, -2.3F, 0.7F);
                        }, 250L, 50L),
                        new Transition((renderContext) -> {}, 250L, 50L))
                    .withFirstPersonPositioningZooming((renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glTranslatef(0.2F, 0.95F, -0.55F);
                        if (Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {}

                    })
                    .withFirstPersonPositioningRunning((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-0.25F, 1.224999F, 0.2F);
                    })
                    .withFirstPersonPositioningModifying((renderContext) -> {
                        GL11.glScalef(6.0F, 6.0F, 6.0F);
                        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(-1.025F, 0.675F, 0.35F);
                    })
                    .withFirstPersonHandPositioning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 6.0F);
                        GL11.glTranslatef(0.47F, 0.12F, -0.02F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    })
                    .withFirstPersonHandPositioningRunning((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 6.0F);
                        GL11.glTranslatef(0.47F, 0.12F, -0.02F);
                        GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(40.0F, 1.0F, 1.0F, 0.0F);
                    }, (renderContext) -> {
                        GL11.glScalef(2.5F, 2.5F, 3.0F);
                        GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
                        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                    })
                    .withFirstPersonHandPositioningModifying((renderContext) -> {
                        GL11.glScalef(3.0F, 3.0F, 3.0F);
                        GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.375F, -0.8F, 0.15F);
                    }, (renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.44F, -0.5F, 0.08F);
                    })
                    .withFirstPersonLeftHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(2.0F, 2.0F, 2.5F);
                        GL11.glTranslatef(0.4F, 0.5F, 0.8F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    }, 250L, 50L))
                    .withFirstPersonRightHandPositioningReloading(new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 1000L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 50L), new Transition((renderContext) -> {
                        GL11.glScalef(3.5F, 3.5F, 4.0F);
                        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslatef(0.33F, -0.5F, 0.08F);
                    }, 250L, 50L))
                    .build())
            .withSpawnEntityDamage(10.0F)
            .withSpawnEntityGravityVelocity(0.8F)
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
