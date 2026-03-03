package com.gtnewhorizon.newgunrizons.mechanic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.config.CommonModContext;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.entity.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.entity.WeaponSpawnEntity;
import com.gtnewhorizon.newgunrizons.network.TryFireMessage;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.PermitManager;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

public class WeaponFireAspect implements Aspect<WeaponState, PlayerWeaponInstance> {

    private static final long ALERT_TIMEOUT = 500L;

    private static final Predicate<PlayerWeaponInstance> readyToShootAccordingToFireRate = (
        instance) -> (float) (System.currentTimeMillis() - instance.getLastFireTimestamp())
            >= 50.0F / instance.getWeapon().builder.fireRate;
    private static final Predicate<PlayerWeaponInstance> readyToShootAccordingToFireMode = (
        instance) -> instance.getSeriesShotCount() < instance.getMaxShots();
    private static final Predicate<PlayerWeaponInstance> hasAmmo = (instance) -> instance.getAmmo() > 0;
    private static final Predicate<PlayerWeaponInstance> ejectSpentRoundRequired = (instance) -> instance.getWeapon()
        .ejectSpentRoundRequired();
    private static final Predicate<PlayerWeaponInstance> ejectSpentRoundTimeoutExpired = (
        instance) -> System.currentTimeMillis()
            >= instance.getWeapon().builder.pumpTimeoutMilliseconds + instance.getStateUpdateTimestamp();
    private static final Predicate<PlayerWeaponInstance> alertTimeoutExpired = (instance) -> System.currentTimeMillis()
        >= ALERT_TIMEOUT + instance.getStateUpdateTimestamp();
    private static final Predicate<PlayerWeaponInstance> sprinting = (instance) -> instance.getPlayer()
        .isSprinting();
    private static final Set<WeaponState> allowedFireOrEjectFromStates;
    private static final Set<WeaponState> allowedUpdateFromStates;
    private final ModContext modContext;
    private StateManager<WeaponState, ? super PlayerWeaponInstance> stateManager;

    public WeaponFireAspect(CommonModContext modContext) {
        this.modContext = modContext;
    }

    public void setPermitManager(PermitManager permitManager) {}

    public void setStateManager(StateManager<WeaponState, ? super PlayerWeaponInstance> stateManager) {
        this.stateManager = stateManager;
        stateManager.in(this)
            .change(WeaponState.READY)
            .to(WeaponState.ALERT)
            .when(hasAmmo.negate())
            .withAction(this::cannotFire)
            .manual()
            .in(this)
            .change(WeaponState.ALERT)
            .to(WeaponState.READY)
            .when(alertTimeoutExpired)
            .automatic()
            .in(this)
            .change(WeaponState.READY)
            .to(WeaponState.FIRING)
            .when(
                hasAmmo.and(sprinting.negate())
                    .and(readyToShootAccordingToFireRate))
            .withAction(this::fire)
            .manual()
            .in(this)
            .change(WeaponState.FIRING)
            .to(WeaponState.RECOILED)
            .automatic()
            .in(this)
            .change(WeaponState.RECOILED)
            .to(WeaponState.PAUSED)
            .automatic()
            .in(this)
            .change(WeaponState.PAUSED)
            .to(WeaponState.EJECT_REQUIRED)
            .when(ejectSpentRoundRequired)
            .manual()
            .in(this)
            .change(WeaponState.EJECT_REQUIRED)
            .to(WeaponState.EJECTING)
            .withAction(this::ejectSpentRound)
            .manual()
            .in(this)
            .change(WeaponState.EJECTING)
            .to(WeaponState.READY)
            .when(ejectSpentRoundTimeoutExpired)
            .automatic()
            .in(this)
            .change(WeaponState.PAUSED)
            .to(WeaponState.FIRING)
            .when(
                hasAmmo.and(sprinting.negate())
                    .and(readyToShootAccordingToFireMode)
                    .and(readyToShootAccordingToFireRate))
            .withAction(this::fire)
            .manual()
            .in(this)
            .change(WeaponState.PAUSED)
            .to(WeaponState.READY)
            .when(ejectSpentRoundRequired.negate())
            .withAction(PlayerWeaponInstance::resetCurrentSeries)
            .manual();
    }

    public void onFireButtonClick(EntityPlayer player) {
        PlayerWeaponInstance weaponInstance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerWeaponInstance.class);
        if (weaponInstance != null) {
            this.stateManager.changeStateFromAnyOf(
                this,
                weaponInstance,
                allowedFireOrEjectFromStates,
                WeaponState.FIRING,
                WeaponState.EJECTING,
                WeaponState.ALERT);
        }

    }

    public void onFireButtonRelease(EntityPlayer player) {
        PlayerWeaponInstance weaponInstance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerWeaponInstance.class);
        if (weaponInstance != null) {
            this.stateManager.changeState(this, weaponInstance, WeaponState.EJECT_REQUIRED, WeaponState.READY);
        }

    }

    public void onUpdate(EntityPlayer player) {
        PlayerWeaponInstance weaponInstance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerWeaponInstance.class);
        if (weaponInstance != null) {
            this.stateManager.changeStateFromAnyOf(this, weaponInstance, allowedUpdateFromStates);
        }

    }

    private void cannotFire(PlayerWeaponInstance weaponInstance) {
        if (weaponInstance.getAmmo() == 0) {
            String message;
            if (weaponInstance.getWeapon()
                .getAmmoCapacity() == 0
                && this.modContext.getAttachmentAspect()
                    .getActiveAttachment(weaponInstance, AttachmentCategory.MAGAZINE) == null) {
                message = StatCollector.translateToLocalFormatted("gui.noMagazine");
            } else {
                message = StatCollector.translateToLocalFormatted("gui.noAmmo");
            }

            this.modContext.getStatusMessageCenter()
                .addAlertMessage(message, 3, 250L, 200L);
            if (weaponInstance.getPlayer() instanceof EntityPlayer) {
                if (this.modContext.getNoAmmoSound() != null) {
                    weaponInstance.getPlayer()
                        .playSound(this.modContext.getNoAmmoSound(), 1.0F, 1.0F);
                }
            }
        }

    }

    private void fire(PlayerWeaponInstance weaponInstance) {
        EntityLivingBase player = weaponInstance.getPlayer();
        Weapon weapon = (Weapon) weaponInstance.getItem();
        Random random = player.getRNG();
        this.modContext.getChannel()
            .sendToServer(new TryFireMessage(true));
        boolean silencerOn = this.modContext.getAttachmentAspect()
            .isSilencerOn(weaponInstance);
        {
            String snd = silencerOn ? weapon.getSilencedShootSound() : weapon.getShootSound();
            if (snd != null) {
                player.playSound(
                    snd,
                    silencerOn ? weapon.getSilencedShootSoundVolume() : weapon.getShootSoundVolume(),
                    1.0F);
            }
        }
        if (weaponInstance.getAmmo() == 1 && weapon.getEndOfShootSound() != null) {
            player.playSound(weapon.getEndOfShootSound(), 1.0F, 1.0F);
        }

        player.rotationPitch -= weaponInstance.getRecoil();
        float rotationYawFactor = -1.0F + random.nextFloat() * 2.0F;
        player.rotationYaw += weaponInstance.getRecoil() * rotationYawFactor;
        if (weapon.builder.flashIntensity > 0.0F) {
            this.modContext.getEffectManager()
                .spawnFlashParticle(
                    player,
                    weapon.builder.flashIntensity,
                    weapon.builder.flashScale.get(),
                    weaponInstance.isAimed() ? 0.0F : 0.0F + weapon.builder.flashOffsetX.get(),
                    0.0F + weapon.builder.flashOffsetY.get());
        }

        this.modContext.getEffectManager()
            .spawnSmokeParticle(
                player,
                0.0F + weapon.builder.smokeOffsetX.get(),
                0.0F + weapon.builder.smokeOffsetY.get());

        weaponInstance.setSeriesShotCount(weaponInstance.getSeriesShotCount() + 1);
        weaponInstance.setLastFireTimestamp(System.currentTimeMillis());
        weaponInstance.setAmmo(weaponInstance.getAmmo() - 1);
    }

    private void ejectSpentRound(PlayerWeaponInstance weaponInstance) {
        EntityLivingBase player = weaponInstance.getPlayer();
        if (weaponInstance.getWeapon()
            .getEjectSpentRoundSound() != null) {
            player.playSound(
                weaponInstance.getWeapon()
                    .getEjectSpentRoundSound(),
                1.0F,
                1.0F);
        }
    }

    public void serverFire(EntityLivingBase player, ItemStack itemStack) {
        this.serverFire(player, itemStack, null);
    }

    public void serverFire(EntityLivingBase player, ItemStack itemStack,
        BiFunction<Weapon, EntityLivingBase, ? extends WeaponSpawnEntity> spawnEntityWith) {
        if (itemStack.getItem() instanceof Weapon weapon) {
            if (spawnEntityWith == null) {
                spawnEntityWith = weapon.builder.spawnEntityWith;
            }

            for (int i = 0; i < weapon.builder.pellets; ++i) {
                WeaponSpawnEntity spawnEntity = spawnEntityWith.apply(weapon, player);
                player.worldObj.spawnEntityInWorld(spawnEntity);
            }

            PlayerWeaponInstance playerWeaponInstance = Tags.getInstance(itemStack, PlayerWeaponInstance.class);
            if (weapon.isShellCasingEjectEnabled() && playerWeaponInstance != null) {
                EntityShellCasing entityShellCasing = weapon.builder.spawnShellWith.apply(playerWeaponInstance, player);
                if (entityShellCasing != null) {
                    player.worldObj.spawnEntityInWorld(entityShellCasing);
                }
            }

            boolean silencerOn = playerWeaponInstance != null && this.modContext.getAttachmentAspect()
                .isSilencerOn(playerWeaponInstance);
            {
                String snd = silencerOn ? weapon.getSilencedShootSound() : weapon.getShootSound();
                float vol = silencerOn ? weapon.getSilencedShootSoundVolume() : weapon.getShootSoundVolume();
                if (player instanceof EntityPlayer) {
                    player.worldObj.playSoundToNearExcept((EntityPlayer) player, snd, vol, 1.0F);
                } else {
                    player.worldObj.playSoundAtEntity(player, snd, vol, 1.0F);
                }
            }
        }
    }

    static {
        allowedFireOrEjectFromStates = new HashSet<>(
            Arrays.asList(WeaponState.READY, WeaponState.PAUSED, WeaponState.EJECT_REQUIRED));
        allowedUpdateFromStates = new HashSet<>(
            Arrays.asList(
                WeaponState.EJECTING,
                WeaponState.PAUSED,
                WeaponState.FIRING,
                WeaponState.RECOILED,
                WeaponState.PAUSED,
                WeaponState.ALERT));
    }
}
