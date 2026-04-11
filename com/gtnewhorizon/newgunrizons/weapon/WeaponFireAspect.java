package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.StatusMessageManager;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.registry.Sounds;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class WeaponFireAspect implements Aspect<WeaponState, ItemWeaponInstance> {
   private static final long ALERT_TIMEOUT = 500L;
   private static final Predicate<ItemWeaponInstance> readyToShootAccordingToFireRate = instance -> (float)(
         System.currentTimeMillis() - instance.getLastFireTimestamp()
      )
      >= 50.0F / instance.getWeapon().getFireRate();
   private static final Predicate<ItemWeaponInstance> readyToShootAccordingToFireMode = instance -> instance.getSeriesShotCount() < instance.getMaxShots();
   private static final Predicate<ItemWeaponInstance> hasAmmo = instance -> instance.getAmmo() > 0;
   private static final Predicate<ItemWeaponInstance> ejectSpentRoundRequired = instance -> instance.getWeapon().ejectSpentRoundRequired();
   private static final Predicate<ItemWeaponInstance> ejectSpentRoundTimeoutExpired = instance -> System.currentTimeMillis()
      >= instance.getWeapon().getPumpTimeoutMilliseconds() + instance.getStateUpdateTimestamp();
   private static final Predicate<ItemWeaponInstance> alertTimeoutExpired = instance -> System.currentTimeMillis() >= 500L + instance.getStateUpdateTimestamp();
   private static final Predicate<ItemWeaponInstance> sprinting = instance -> instance.getPlayer().func_70051_ag();
   private static final Set<WeaponState> allowedFireOrEjectFromStates = new HashSet<>(
      Arrays.asList(WeaponState.READY, WeaponState.PAUSED, WeaponState.EJECT_REQUIRED)
   );
   private static final Set<WeaponState> allowedUpdateFromStates = new HashSet<>(
      Arrays.asList(WeaponState.EJECTING, WeaponState.PAUSED, WeaponState.FIRING, WeaponState.RECOILED, WeaponState.PAUSED, WeaponState.ALERT)
   );
   public static final WeaponFireAspect INSTANCE = new WeaponFireAspect();
   private StateManager<WeaponState, ? super ItemWeaponInstance> stateManager;

   @Override
   public void setStateManager(StateManager<WeaponState, ? super ItemWeaponInstance> stateManager) {
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
         .when(hasAmmo.and(sprinting.negate()).and(readyToShootAccordingToFireRate))
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
         .when(hasAmmo.and(sprinting.negate()).and(readyToShootAccordingToFireMode).and(readyToShootAccordingToFireRate))
         .withAction(this::fire)
         .manual()
         .in(this)
         .change(WeaponState.PAUSED)
         .to(WeaponState.READY)
         .when(ejectSpentRoundRequired.negate())
         .withAction(ItemWeaponInstance::resetCurrentSeries)
         .manual();
   }

   public void onFireButtonClick(EntityPlayer player) {
      ItemWeaponInstance weaponInstance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
      if (weaponInstance != null) {
         this.stateManager
            .changeStateFromAnyOf(this, weaponInstance, allowedFireOrEjectFromStates, WeaponState.FIRING, WeaponState.EJECTING, WeaponState.ALERT);
      }
   }

   public void onFireButtonRelease(EntityPlayer player) {
      ItemWeaponInstance weaponInstance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
      if (weaponInstance != null) {
         this.stateManager.changeState(this, weaponInstance, WeaponState.EJECT_REQUIRED, WeaponState.READY);
      }
   }

   public void onUpdate(EntityPlayer player) {
      ItemWeaponInstance weaponInstance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
      if (weaponInstance != null) {
         this.stateManager.changeStateFromAnyOf(this, weaponInstance, allowedUpdateFromStates);
      }
   }

   private void cannotFire(ItemWeaponInstance weaponInstance) {
      if (weaponInstance.getAmmo() == 0) {
         String message;
         if (weaponInstance.getWeapon().getAmmoCapacity() == 0
            && WeaponAttachmentAspect.INSTANCE.getActiveAttachment(weaponInstance, AttachmentCategory.MAGAZINE) == null) {
            message = StatCollector.func_74837_a("gui.noMagazine", new Object[0]);
         } else {
            message = StatCollector.func_74837_a("gui.noAmmo", new Object[0]);
         }

         StatusMessageManager.INSTANCE.addAlertMessage(message, 3, 250L, 200L);
         if (weaponInstance.getPlayer() instanceof EntityPlayer && Sounds.DRY_FIRE != null) {
            weaponInstance.getPlayer().func_85030_a(Sounds.DRY_FIRE, 1.0F, 1.0F);
         }
      }
   }

   private void fire(ItemWeaponInstance weaponInstance) {
      EntityLivingBase player = weaponInstance.getPlayer();
      ItemWeapon weapon = (ItemWeapon)weaponInstance.getItem();
      Random random = player.func_70681_au();
      NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)8, ((EntityPlayer)player).field_71071_by.field_70461_c));
      boolean silencerOn = WeaponAttachmentAspect.INSTANCE.isSilencerOn(weaponInstance);
      String snd = silencerOn ? weapon.getSilencedShootSound() : weapon.getShootSound();
      if (snd != null) {
         player.func_85030_a(snd, silencerOn ? weapon.getSilencedShootSoundVolume() : weapon.getShootSoundVolume(), 1.0F);
      }

      if (weaponInstance.getAmmo() == 1 && weapon.getEndOfShootSound() != null) {
         player.func_85030_a(weapon.getEndOfShootSound(), 1.0F, 1.0F);
      }

      player.field_70125_A = player.field_70125_A - weaponInstance.getRecoil();
      float rotationYawFactor = -1.0F + random.nextFloat() * 2.0F;
      player.field_70177_z = player.field_70177_z + weaponInstance.getRecoil() * rotationYawFactor;
      NewGunrizonsMod.proxy.onWeaponFireEffects(player, weapon.getSmokeOffsetX().get(), weapon.getSmokeOffsetY().get(), silencerOn);
      weaponInstance.setSeriesShotCount(weaponInstance.getSeriesShotCount() + 1);
      weaponInstance.setLastFireTimestamp(System.currentTimeMillis());
      weaponInstance.setAmmo(weaponInstance.getAmmo() - 1);
   }

   private void ejectSpentRound(ItemWeaponInstance weaponInstance) {
      EntityLivingBase player = weaponInstance.getPlayer();
      if (weaponInstance.getWeapon().getEjectSpentRoundSound() != null) {
         player.func_85030_a(weaponInstance.getWeapon().getEjectSpentRoundSound(), 1.0F, 1.0F);
      }
   }

   public void serverFire(EntityLivingBase player, ItemStack itemStack, int slotIndex) {
      if (itemStack.func_77973_b() instanceof ItemWeapon) {
         ItemWeapon weapon = (ItemWeapon)itemStack.func_77973_b();
         ItemWeaponInstance instance = ItemInstance.fromStack(itemStack, ItemWeaponInstance.class);
         if (instance == null) {
            instance = weapon.createItemInstance(player, itemStack, slotIndex);
            ItemInstance.toStack(itemStack, instance);
         }

         instance.setPlayer(player);
         int ammo = instance.getAmmo();
         if (ammo > 0) {
            instance.setAmmo(ammo - 1);
            ItemInstance.setAmmo(itemStack, ammo - 1);
            ItemInstance.toStack(itemStack, instance);

            for (int i = 0; i < weapon.getPellets(); i++) {
               weapon.spawnBullet(player);
            }

            if (weapon.isShellCasingEjectEnabled()) {
               weapon.spawnShell(instance, player);
            }

            boolean silencerOn = WeaponAttachmentAspect.INSTANCE.isSilencerOn(instance);
            String snd = silencerOn ? weapon.getSilencedShootSound() : weapon.getShootSound();
            float vol = silencerOn ? weapon.getSilencedShootSoundVolume() : weapon.getShootSoundVolume();
            if (player instanceof EntityPlayer) {
               player.field_70170_p.func_85173_a((EntityPlayer)player, snd, vol, 1.0F);
            } else {
               player.field_70170_p.func_72956_a(player, snd, vol, 1.0F);
            }
         }
      }
   }
}
