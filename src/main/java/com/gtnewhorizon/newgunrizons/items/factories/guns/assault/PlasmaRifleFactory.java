package com.gtnewhorizon.newgunrizons.items.factories.guns.assault;

import java.util.Arrays;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.BedrockModel;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

public class PlasmaRifleFactory {

    public Item createGun() {
        return (new ItemWeapon.Builder()).withName("PlasmaRifle")
            .withAmmoCapacity(30)
            .withMaxBulletsPerReload(30)
            .withFireRate(0.5F)
            .withRecoil(2.0F)
            .withCameraRecoilDuration(60)
            .withMaxShots(1, 3, 30)
            .withShootSound("plasmarifle_shot")
            .withReloadSound("drawweapon")
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.6F)
            .withFlashScale(0.3F)
            .withSmokeEnabled(false)
            .withTracerWidth(0.1F)
            .withTracerLength(3.0F)
            .withTracerColor(0.3F, 0.8F, 1.0F)
            .withInaccuracy(2F)
            .withPellets(1)
            .withCreativeTab(NewGunrizonsMod.gunsTab)
            .withInformationProvider(
                (stack) -> Arrays.asList(
                    "Type: Plasma Assault Rifle",
                    "Damage: 6",
                    "Ammo: Energy Cells",
                    "Fire Rate: Semi / Burst / Auto"))
            .withCompatibleBullet(Bullets.ShotgunShell)
            .withTextureName("plasmarifle")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModel(new BedrockModel("weapon/plasmarifle"))
                    .withBedrockAnimation("weapon/plasmarifle")
                    .withBedrockAnimationForState(RenderableState.IDLE, "animation.plasmarifle.idle")
                    .withBedrockAnimationForState(RenderableState.ZOOMING, "animation.plasmarifle.zoom")
                    .withBedrockAnimationForState(RenderableState.RUNNING, "animation.plasmarifle.run")
                    .withBedrockAnimationForState(RenderableState.MODIFYING, "animation.plasmarifle.modify")
                    .withBedrockAnimationForState(RenderableState.SHOOTING, "animation.plasmarifle.shoot")
                    .withBedrockAnimationForState(RenderableState.ZOOMING_SHOOTING, "animation.plasmarifle.shoot_zoom")
                    .withBedrockAnimationForState(RenderableState.RELOADING_START, "animation.plasmarifle.reload_start")
                    .withBedrockAnimationForState(RenderableState.RELOADING_ITERATION, "animation.plasmarifle.reload_insert")
                    .withBedrockAnimationForState(RenderableState.RELOADING_END, "animation.plasmarifle.reload_end")
                    .build())
            .withSpawnEntityDamage(6.0F)
            .withSpawnEntityGravityVelocity(0.008F)
            .withSpawnEntitySpeed(15f)
            .build();
    }
}
