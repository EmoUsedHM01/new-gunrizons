package com.gtnewhorizon.newgunrizons.items.factories.guns.assault;

import java.util.Arrays;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.BedrockModel;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

public class LaserRifleFactory {

    public Item createGun() {
        return (new ItemWeapon.Builder()).withName("LaserRifle")
            .withAmmoCapacity(24)
            .withMaxBulletsPerReload(24)
            .withFireRate(0.4F)
            .withRecoil(1.5F)
            .withCameraRecoilDuration(50)
            .withMaxShots(1, 3, 24)
            .withShootSound("laserrifle_shot")
            .withReloadSound("drawweapon")
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.5F)
            .withFlashScale(0.25F)
            .withSmokeEnabled(false)
            .withTracerWidth(0.1F)
            .withTracerLength(200F)
            .withTracerColor(1.0F, 0.0F, 0.0F)
            .withTracerIntensity(5.0F)
            .withInaccuracy(0.2F)
            .withPellets(1)
            .withCreativeTab(NewGunrizonsMod.gunsTab)
            .withInformationProvider(
                (stack) -> Arrays.asList(
                    "Type: Laser Assault Rifle",
                    "Damage: 8",
                    "Ammo: Energy Cells",
                    "Fire Rate: Semi / Burst / Auto"))
            .withCompatibleBullet(Bullets.ShotgunShell)
            .withTextureName("laserrifle")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModel(new BedrockModel("weapon/laserrifle"))
                    .withBedrockAnimation("weapon/laserrifle")
                    .withBedrockAnimationForState(RenderableState.IDLE, "animation.laserrifle.idle")
                    .withBedrockAnimationForState(RenderableState.ZOOMING, "animation.laserrifle.zoom")
                    .withBedrockAnimationForState(RenderableState.RUNNING, "animation.laserrifle.run")
                    .withBedrockAnimationForState(RenderableState.MODIFYING, "animation.laserrifle.modify")
                    .withBedrockAnimationForState(RenderableState.SHOOTING, "animation.laserrifle.shoot")
                    .withBedrockAnimationForState(RenderableState.ZOOMING_SHOOTING, "animation.laserrifle.shoot_zoom")
                    .withBedrockAnimationForState(RenderableState.RELOADING_START, "animation.laserrifle.reload_start")
                    .withBedrockAnimationForState(RenderableState.RELOADING_ITERATION, "animation.laserrifle.reload_insert")
                    .withBedrockAnimationForState(RenderableState.RELOADING_END, "animation.laserrifle.reload_end")
                    .build())
            .withSpawnEntityDamage(8.0F)
            .withSpawnEntityGravityVelocity(0F)
            .withSpawnEntitySpeed(40f)
            .build();
    }
}
