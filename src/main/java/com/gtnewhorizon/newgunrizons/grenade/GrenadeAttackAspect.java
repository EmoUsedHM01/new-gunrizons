package com.gtnewhorizon.newgunrizons.grenade;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.entities.EntityGrenade;
import com.gtnewhorizon.newgunrizons.entities.Explosion;
import com.gtnewhorizon.newgunrizons.items.instances.ItemGrenadeInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.network.GrenadeMessage;
import com.gtnewhorizon.newgunrizons.network.StatusMessageManager;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.StateManager;

public class GrenadeAttackAspect implements Aspect<GrenadeState, ItemGrenadeInstance> {

    private static final Logger logger = LogManager.getLogger(GrenadeAttackAspect.class);
    private final Predicate<ItemGrenadeInstance> hasSafetyPin = (instance) -> instance.getWeapon()
        .hasSafetyPin();
    private static final Predicate<ItemGrenadeInstance> reequipTimeoutExpired = (instance) -> System.currentTimeMillis()
        > instance.getStateUpdateTimestamp() + instance.getWeapon()
            .getReequipTimeout();
    private static final Predicate<ItemGrenadeInstance> throwingCompleted = (
        instance) -> (double) System.currentTimeMillis()
            >= (double) instance.getStateUpdateTimestamp() + instance.getWeapon()
                .getTotalThrowingDuration() * 1.1D;
    private static final Predicate<ItemGrenadeInstance> explosionTimeoutExpired = (
        instance) -> System.currentTimeMillis() >= instance.getStateUpdateTimestamp() + (long) instance.getWeapon()
            .getExplosionTimeout();
    private static final Set<GrenadeState> allowedAttackFromStates;
    private static final Set<GrenadeState> allowedPinOffFromStates;
    private static final Set<GrenadeState> allowedUpdateFromStates;
    private static final int SAFETY_IN_ALERT_TIMEOUT = 1000;
    public static final GrenadeAttackAspect INSTANCE = new GrenadeAttackAspect();

    private StateManager<GrenadeState, ? super ItemGrenadeInstance> stateManager;

    public GrenadeAttackAspect() {}

    public void setStateManager(StateManager<GrenadeState, ? super ItemGrenadeInstance> stateManager) {
        this.stateManager = stateManager;
        stateManager.in(this)
            .change(GrenadeState.READY)
            .to(GrenadeState.SAFETY_PIN_OFF)
            .withAction(this::takeSafetyPinOff)
            .when(this.hasSafetyPin)
            .manual()
            .in(this)
            .change(GrenadeState.SAFETY_PIN_OFF)
            .to(GrenadeState.STRIKER_LEVER_RELEASED)
            .withAction(this::releaseStrikerLever)
            .manual()
            .in(this)
            .change(GrenadeState.STRIKER_LEVER_RELEASED)
            .to(GrenadeState.EXPLODED_IN_HANDS)
            .withAction(this::explode)
            .when(explosionTimeoutExpired)
            .automatic()
            .in(this)
            .change(GrenadeState.READY)
            .to(GrenadeState.THROWING)
            .when(this.hasSafetyPin.negate())
            .manual()
            .in(this)
            .change(GrenadeState.THROWING)
            .to(GrenadeState.THROWN)
            .withAction(this::throwIt)
            .when(throwingCompleted)
            .automatic()
            .in(this)
            .change(GrenadeState.STRIKER_LEVER_RELEASED)
            .to(GrenadeState.THROWING)
            .manual()
            .in(this)
            .change(GrenadeState.THROWN)
            .to(GrenadeState.READY)
            .withAction(this::reequip)
            .when(reequipTimeoutExpired)
            .automatic()
            .in(this)
            .change(GrenadeState.EXPLODED_IN_HANDS)
            .to(GrenadeState.READY)
            .withAction(this::reequip)
            .when(reequipTimeoutExpired)
            .automatic();
    }

    private void explode(ItemGrenadeInstance instance) {
        logger.debug("Exploding!");
        NewGunrizonsMod.CHANNEL.sendToServer(new GrenadeMessage(instance, 0L));
    }

    private void throwIt(ItemGrenadeInstance instance) {
        logger.debug("Throwing with state " + instance.getState());
        long activationTimestamp;
        if (instance.getWeapon()
            .getExplosionTimeout() > 0) {
            activationTimestamp = instance.getActivationTimestamp();
        } else {
            activationTimestamp = -1L;
        }

        if (instance.getWeapon()
            .getThrowSound() != null) {
            instance.getPlayer()
                .playSound(
                    instance.getWeapon()
                        .getThrowSound(),
                    1.0F,
                    1.0F);
        }

        NewGunrizonsMod.CHANNEL.sendToServer(new GrenadeMessage(instance, activationTimestamp));
    }

    private void reequip(ItemGrenadeInstance instance) {
        logger.debug("Reequipping");
    }

    private void takeSafetyPinOff(ItemGrenadeInstance instance) {
        if (instance.getWeapon()
            .getSafetyPinOffSound() != null) {
            instance.getPlayer()
                .playSound(
                    instance.getWeapon()
                        .getSafetyPinOffSound(),
                    1.0F,
                    1.0F);
        }
        logger.debug("Taking safety pin off");
    }

    private void releaseStrikerLever(ItemGrenadeInstance instance) {
        logger.debug("Safety pin is off");
        instance.setActivationTimestamp(System.currentTimeMillis());
    }

    public void onAttackButtonClick(EntityPlayer player, boolean throwingFar) {
        ItemGrenadeInstance grenadeInstance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemGrenadeInstance.class);
        if (grenadeInstance != null) {
            grenadeInstance.setThrowingFar(throwingFar);
            this.stateManager.changeStateFromAnyOf(
                this,
                grenadeInstance,
                allowedAttackFromStates,
                GrenadeState.SAFETY_PIN_OFF,
                GrenadeState.THROWING);
        }

    }

    public void onAttackButtonUp(EntityPlayer player, boolean throwingFar) {
        ItemGrenadeInstance grenadeInstance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemGrenadeInstance.class);
        if (grenadeInstance != null) {
            grenadeInstance.setThrowingFar(throwingFar);
            this.stateManager.changeStateFromAnyOf(
                this,
                grenadeInstance,
                allowedPinOffFromStates,
                GrenadeState.STRIKER_LEVER_RELEASED);
        }

    }

    public void onUpdate(EntityPlayer player) {
        ItemGrenadeInstance grenadeInstance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemGrenadeInstance.class);
        if (grenadeInstance != null) {
            if (grenadeInstance.getState() == GrenadeState.STRIKER_LEVER_RELEASED && System.currentTimeMillis()
                > grenadeInstance.getLastSafetyPinAlertTimestamp() + SAFETY_IN_ALERT_TIMEOUT) {
                long remainingTimeUntilExplosion = (long) grenadeInstance.getWeapon()
                    .getExplosionTimeout() - (System.currentTimeMillis() - grenadeInstance.getActivationTimestamp());
                if (remainingTimeUntilExplosion < 0L) {
                    remainingTimeUntilExplosion = 0L;
                }

                String message = StatCollector.translateToLocalFormatted(
                    "gui.grenadeExplodes",
                    Math.round((float) remainingTimeUntilExplosion / 1000.0F));
                StatusMessageManager.INSTANCE.addAlertMessage(message, 1, 1000L, 0L);
                grenadeInstance.setLastSafetyPinAlertTimestamp(System.currentTimeMillis());
            }

            this.stateManager.changeStateFromAnyOf(this, grenadeInstance, allowedUpdateFromStates);
        }

    }

    public void serverThrowGrenade(EntityPlayer player, ItemGrenadeInstance instance, long activationTimestamp) {
        logger.debug("Throwing grenade");
        GrenadeAttackAspect.serverThrowGrenade((EntityLivingBase) player, instance, activationTimestamp);
        {
            int slot = instance.getItemInventoryIndex();
            if (player.inventory.mainInventory[slot] != null) {
                if (--player.inventory.mainInventory[slot].stackSize <= 0) {
                    player.inventory.mainInventory[slot] = null;
                }
            }
        }
    }

    public static void serverThrowGrenade(EntityLivingBase player, ItemGrenadeInstance instance,
        long activationTimestamp) {
        if (activationTimestamp == 0L) {
            Explosion.createServerSideExplosion(
                player.worldObj,
                null,
                player.posX,
                player.posY,
                player.posZ,
                instance.getWeapon()
                    .getExplosionStrength(),
                false,
                true);
        } else {
            float velocity = instance.isThrowingFar() ? instance.getWeapon()
                .getFarVelocity()
                : instance.getWeapon()
                    .getVelocity();
            EntityGrenade entityGrenade = (new EntityGrenade.Builder()).withThrower(player)
                .withActivationTimestamp(activationTimestamp)
                .withGrenade(instance.getWeapon())
                .withExplosionStrength(
                    instance.getWeapon()
                        .getExplosionStrength())
                .withExplosionTimeout(
                    instance.getWeapon()
                        .getExplosionTimeout())
                .withVelocity(velocity)
                .withGravityVelocity(
                    instance.getWeapon()
                        .getGravityVelocity())
                .withRotationSlowdownFactor(
                    instance.getWeapon()
                        .getRotationSlowdownFactor())
                .build();
            logger.debug("Throwing velocity {} ", velocity);
            player.worldObj.spawnEntityInWorld(entityGrenade);
        }

    }

    static {
        allowedAttackFromStates = new HashSet<>(Arrays.asList(GrenadeState.READY, GrenadeState.STRIKER_LEVER_RELEASED));
        allowedPinOffFromStates = new HashSet<>(Arrays.asList(GrenadeState.SAFETY_PIN_OFF));
        allowedUpdateFromStates = new HashSet<>(
            Arrays.asList(
                GrenadeState.STRIKER_LEVER_RELEASED,
                GrenadeState.THROWING,
                GrenadeState.THROWN,
                GrenadeState.EXPLODED_IN_HANDS));
    }
}
