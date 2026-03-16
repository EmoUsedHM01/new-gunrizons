package com.gtnewhorizon.newgunrizons.items.factories.guns.shotgun;

import java.util.Arrays;

import com.gtnewhorizon.newgunrizons.model.BedrockModel;
import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.registry.Bullets;
import com.gtnewhorizon.newgunrizons.state.RenderableState;

public class DoomShotgunFactory {

    public Item createGun() {
        return (new ItemWeapon.Builder()).withName("DoomShotgun")
            .withAmmoCapacity(9)
            .withMaxBulletsPerReload(8)
            .withFireRate(0.06F)
            .withRecoil(8.0F)
            .withCameraRecoilDuration(120)
            .withMaxShots(1)
            .withShootSound("handcannon")
            .withReloadSound("loadshell")
            .withReloadIterationSound("loadshell")
            .withAllReloadIterationsCompletedSound("pump")
            .withIteratedLoad()
            .withCrosshair("gun")
            .withCrosshairRunning("Running")
            .withCrosshairZoomed("Sight")
            .withFlashIntensity(0.7F)
            .withFlashScale(0.4F)
            .withInaccuracy(8.0F)
            .withTracerIntensity(5.0f)
            .withPellets(8)
            .withCreativeTab(NewGunrizonsMod.ShotgunsTab)
            .withInformationProvider(
                (stack) -> Arrays.asList(
                    "Type: Pump-Action Shotgun",
                    "Damage: 8x8 pellets",
                    "Ammo: 12 Gauge Shotgun Shells",
                    "Fire Rate: Pump Action"))
            .withCompatibleBullet(Bullets.ShotgunShell)
            .withTextureName("shotgun")
            .withRenderer(
                (new WeaponRenderer.Builder()).withModel(new BedrockModel("weapon/shotgun"))
                    .withBedrockAnimation("weapon/shotgun")
                    .withBedrockAnimationForState(RenderableState.IDLE, "animation.shotgun.idle")
                    .withBedrockAnimationForState(RenderableState.ZOOMING, "animation.shotgun.zoom")
                    .withBedrockAnimationForState(RenderableState.RUNNING, "animation.shotgun.run")
                    .withBedrockAnimationForState(RenderableState.MODIFYING, "animation.shotgun.modify")
                    .withBedrockAnimationForState(RenderableState.SHOOTING, "animation.shotgun.shoot_and_pump")
                    .withBedrockAnimationForState(RenderableState.ZOOMING_SHOOTING, "animation.shotgun.shoot_zoom")
                    .withBedrockAnimationForState(RenderableState.RELOADING_START, "animation.shotgun.reload_start")
                    .withBedrockAnimationForState(RenderableState.RELOADING_ITERATION, "animation.shotgun.reload_insert")
                    .withBedrockAnimationForState(RenderableState.RELOADING_END, "animation.shotgun.reload_end")
                    .build())
            .withSpawnEntityDamage(8.0F)
            .withSpawnEntityGravityVelocity(0.016F)
            .build();
    }
}
