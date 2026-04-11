package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.enchantments.ModEnchantments;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.StatusMessageManager;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class WeaponReloadAspect implements Aspect<WeaponState, ItemWeaponInstance> {
   private static final long ALERT_TIMEOUT = 500L;
   private static final Set<WeaponState> allowedUpdateFromStates = new HashSet<>(
      Arrays.asList(
         WeaponState.LOAD,
         WeaponState.LOAD_ITERATION,
         WeaponState.LOAD_ITERATION_COMPLETED,
         WeaponState.ALL_LOAD_ITERATIONS_COMPLETED,
         WeaponState.UNLOAD_PREPARING,
         WeaponState.UNLOAD,
         WeaponState.ALERT
      )
   );
   private static final Predicate<ItemWeaponInstance> hasNextLoadIteration = weaponInstance -> weaponInstance.getWeapon().hasIteratedLoad()
      && weaponInstance.getLoadIterationCount() > 0;
   private static final Predicate<ItemWeaponInstance> supportsDirectBulletLoad = weaponInstance -> weaponInstance.getWeapon().getAmmoCapacity() > 0;
   private static final Predicate<ItemWeaponInstance> magazineAttached = weaponInstance -> WeaponAttachmentAspect.getActiveAttachment(
         AttachmentCategory.MAGAZINE, weaponInstance
      )
      != null;
   private static final Predicate<ItemWeaponInstance> loadIterationCompleted = weaponInstance -> {
      double speedMult = getReloadSpeedMultiplier(weaponInstance);
      return System.currentTimeMillis()
         >= weaponInstance.getStateUpdateTimestamp()
            + (long)(Math.max(weaponInstance.getWeapon().getLoadIterationTimeout(), weaponInstance.getWeapon().getTotalLoadIterationDuration() + 250L) * speedMult);
   };
   private static final Predicate<ItemWeaponInstance> allLoadIterationsCompleted = weaponInstance -> {
      double speedMult = getReloadSpeedMultiplier(weaponInstance);
      return System.currentTimeMillis()
         >= weaponInstance.getStateUpdateTimestamp() + (long)(weaponInstance.getWeapon().getAllLoadIterationAnimationsCompletedDuration() * speedMult);
   };
   private static final Predicate<ItemWeaponInstance> reloadAnimationCompleted = weaponInstance -> {
      double speedMult = getReloadSpeedMultiplier(weaponInstance);
      return System.currentTimeMillis()
         >= weaponInstance.getStateUpdateTimestamp()
            + (long)(Math.max((double)weaponInstance.getWeapon().getReloadingTimeout(), weaponInstance.getWeapon().getTotalReloadingDuration() * 1.1) * speedMult);
   };
   private static final Predicate<ItemWeaponInstance> unloadAnimationCompleted = weaponInstance -> {
      double speedMult = getReloadSpeedMultiplier(weaponInstance);
      return System.currentTimeMillis()
         >= weaponInstance.getStateUpdateTimestamp() + (long)(weaponInstance.getWeapon().getTotalUnloadingDuration() * 1.1 * speedMult);
   };
   private static final Predicate<ItemWeaponInstance> prepareFirstLoadIterationAnimationCompleted = weaponInstance -> {
      double speedMult = getReloadSpeedMultiplier(weaponInstance);
      return System.currentTimeMillis()
         >= weaponInstance.getStateUpdateTimestamp() + (long)(weaponInstance.getWeapon().getPrepareFirstLoadIterationAnimationDuration() * 1.1 * speedMult);
   };
   private static final Predicate<ItemWeaponInstance> alertTimeoutExpired = instance -> System.currentTimeMillis() >= 500L + instance.getStateUpdateTimestamp();
   private final Predicate<ItemWeaponInstance> inventoryHasFreeSlots = weaponInstance -> weaponInstance.getPlayer() instanceof EntityPlayer
      && InventoryUtils.inventoryHasFreeSlots((EntityPlayer)weaponInstance.getPlayer());
   private final Predicate<ItemStack> magazineNotEmpty = magazineStack -> ItemInstance.getAmmo(magazineStack) > 0;
   public static final WeaponReloadAspect INSTANCE = new WeaponReloadAspect();
   private StateManager<WeaponState, ? super ItemWeaponInstance> stateManager;
   private final Predicate<ItemWeaponInstance> hasCompatibleAmmo = weaponInstance -> {
      if (!(weaponInstance.getPlayer() instanceof EntityPlayer)) {
         return false;
      } else {
         EntityPlayer player = (EntityPlayer)weaponInstance.getPlayer();
         ItemWeapon weapon = weaponInstance.getWeapon();
         List<ItemMagazine> compatibleMagazines = weapon.getCompatibleMagazines();
         if (!compatibleMagazines.isEmpty()) {
            ItemAttachment existingMagazine = WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
            return existingMagazine != null ? true : InventoryUtils.hasCompatibleItem(compatibleMagazines, player, this.magazineNotEmpty, s -> true);
         } else {
            List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);
            return !compatibleBullets.isEmpty()
               ? InventoryUtils.hasCompatibleItem(compatibleBullets, player, s -> true)
               : weapon.getAmmo() != null && player.inventory.hasItem(weapon.getAmmo());
         }
      }
   };

   private static double getReloadSpeedMultiplier(ItemWeaponInstance weaponInstance) {
      int fastHandsLevel = ModEnchantments.getLevel(ModEnchantments.fastHands, weaponInstance.getItemStack());
      return Math.max(0.2, 1.0 - fastHandsLevel * 0.2);
   }

   private static float getReloadSoundPitch(ItemWeaponInstance weaponInstance) {
      double speedMult = getReloadSpeedMultiplier(weaponInstance);
      float speedup = 1.0F / (float)speedMult;
      // Use half the semitone shift: sqrt(speedup) = 2^(t/2 / 12) where t = 12*log2(speedup)
      return (float)Math.sqrt(speedup);
   }

   @Override
   public void setStateManager(StateManager<WeaponState, ? super ItemWeaponInstance> stateManager) {
      this.stateManager = stateManager.in(this)
         .change(WeaponState.READY)
         .to(WeaponState.LOAD)
         .when(supportsDirectBulletLoad.or(magazineAttached.negate()).and(this.hasCompatibleAmmo))
         .withAction(this::performLoad)
         .manual()
         .in(this)
         .change(WeaponState.LOAD)
         .to(WeaponState.READY)
         .when(reloadAnimationCompleted.and(hasNextLoadIteration.negate()))
         .automatic()
         .in(this)
         .change(WeaponState.LOAD)
         .to(WeaponState.LOAD_ITERATION)
         .when(hasNextLoadIteration.and(prepareFirstLoadIterationAnimationCompleted))
         .withAction(this::startLoadIteration)
         .automatic()
         .in(this)
         .change(WeaponState.LOAD_ITERATION)
         .to(WeaponState.LOAD_ITERATION_COMPLETED)
         .when(loadIterationCompleted)
         .withAction(this::completeLoadIteration)
         .automatic()
         .in(this)
         .change(WeaponState.LOAD_ITERATION_COMPLETED)
         .to(WeaponState.LOAD_ITERATION)
         .when(hasNextLoadIteration)
         .withAction(this::startLoadIteration)
         .automatic()
         .in(this)
         .change(WeaponState.LOAD_ITERATION_COMPLETED)
         .to(WeaponState.ALL_LOAD_ITERATIONS_COMPLETED)
         .when(hasNextLoadIteration.negate())
         .automatic()
         .in(this)
         .change(WeaponState.ALL_LOAD_ITERATIONS_COMPLETED)
         .to(WeaponState.READY)
         .when(allLoadIterationsCompleted)
         .withAction(this::completeAllLoadIterations)
         .automatic()
         .in(this)
         .prepare((c, f, t) -> this.prepareUnload(c), unloadAnimationCompleted)
         .change(WeaponState.READY)
         .to(WeaponState.UNLOAD)
         .when(magazineAttached.and(this.inventoryHasFreeSlots))
         .withAction(this::performUnload)
         .manual()
         .in(this)
         .change(WeaponState.UNLOAD)
         .to(WeaponState.READY)
         .automatic()
         .in(this)
         .change(WeaponState.READY)
         .to(WeaponState.ALERT)
         .when(this.inventoryHasFreeSlots.negate())
         .withAction(this::inventoryFullAlert)
         .manual()
         .in(this)
         .change(WeaponState.ALERT)
         .to(WeaponState.READY)
         .when(alertTimeoutExpired)
         .automatic();
   }

   public void reloadMainHeldItem(EntityPlayer player) {
      ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
      if (instance != null) {
         this.stateManager.changeState(this, instance, WeaponState.LOAD, WeaponState.UNLOAD, WeaponState.ALERT);
      }
   }

   public void updateMainHeldItem(EntityPlayer player) {
      ItemWeaponInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemWeaponInstance.class);
      if (instance != null) {
         this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates);
      }
   }

   private void performLoad(ItemWeaponInstance weaponInstance) {
      if (weaponInstance.getPlayer() instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)weaponInstance.getPlayer();
         ItemWeapon weapon = weaponInstance.getWeapon();
         weaponInstance.setLoadIterationCount(0);
         List<ItemMagazine> compatibleMagazines = weapon.getCompatibleMagazines();
         List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);
         if (!compatibleMagazines.isEmpty()) {
            ItemAttachment existingMagazine = WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
            int ammo = ItemInstance.getAmmo(weaponInstance.getItemStack());
            if (existingMagazine == null) {
               ammo = 0;
               ItemStack foundStack = this.findCompatibleMagazineStack(compatibleMagazines, player);
               if (foundStack != null) {
                  ammo = ItemInstance.getAmmo(foundStack);
                  WeaponAttachmentAspect.addAttachment((ItemMagazine)foundStack.getItem(), weaponInstance);
               }
            }

            weaponInstance.setAmmo(ammo);
         } else if (!compatibleBullets.isEmpty()) {
            int maxToLoad = Math.min(weapon.getMaxBulletsPerReload(), weapon.getAmmoCapacity() - weaponInstance.getAmmo());
            int loaded = this.countAvailableBullets(compatibleBullets, player, maxToLoad);
            if (loaded > 0) {
               weaponInstance.setAmmo(weaponInstance.getAmmo() + loaded);
               if (weapon.hasIteratedLoad()) {
                  weaponInstance.setLoadIterationCount(loaded);
               }
            }
         } else if (weapon.getAmmo() != null && player.inventory.hasItem(weapon.getAmmo())) {
            weaponInstance.setAmmo(weapon.getAmmoCapacity());
         }

         if (weapon.getReloadSound() != null) {
            player.playSound(weapon.getReloadSound(), 1.0F, getReloadSoundPitch(weaponInstance));
         }

         NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)0, weaponInstance.getItemInventoryIndex()));
      }
   }

   private ItemStack findCompatibleMagazineStack(List<ItemMagazine> compatibleMagazines, EntityPlayer player) {
      for (ItemMagazine mag : compatibleMagazines) {
         for (ItemStack stack : player.inventory.mainInventory) {
            if (stack != null && stack.getItem() == mag && ItemInstance.getAmmo(stack) > 0) {
               return stack;
            }
         }
      }

      for (ItemMagazine mag : compatibleMagazines) {
         for (ItemStack stackx : player.inventory.mainInventory) {
            if (stackx != null && stackx.getItem() == mag) {
               return stackx;
            }
         }
      }

      return null;
   }

   private int countAvailableBullets(List<ItemAttachment> compatibleBullets, EntityPlayer player, int maxToLoad) {
      for (ItemAttachment bullet : compatibleBullets) {
         for (ItemStack stack : player.inventory.mainInventory) {
            if (stack != null && stack.getItem() == bullet) {
               return Math.min(maxToLoad, stack.stackSize);
            }
         }
      }

      return 0;
   }

   private void prepareUnload(ItemWeaponInstance weaponInstance) {
      if (weaponInstance.getWeapon().getUnloadSound() != null) {
         weaponInstance.getPlayer().playSound(weaponInstance.getWeapon().getUnloadSound(), 1.0F, getReloadSoundPitch(weaponInstance));
      }
   }

   private void performUnload(ItemWeaponInstance weaponInstance) {
      int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
      activeAttachmentIds[AttachmentCategory.MAGAZINE.ordinal()] = -1;
      weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
      weaponInstance.setAmmo(0);
      NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)1, weaponInstance.getItemInventoryIndex()));
   }

   public void inventoryFullAlert(ItemWeaponInstance weaponInstance) {
      StatusMessageManager.INSTANCE.addAlertMessage(StatCollector.translateToLocalFormatted("gui.inventoryFull", new Object[0]), 3, 250L, 200L);
   }

   public void startLoadIteration(ItemWeaponInstance weaponInstance) {
      if (weaponInstance.getWeapon().getReloadIterationSound() != null) {
         weaponInstance.getPlayer().playSound(weaponInstance.getWeapon().getReloadIterationSound(), 1.0F, getReloadSoundPitch(weaponInstance));
      }
   }

   public void completeLoadIteration(ItemWeaponInstance weaponInstance) {
      weaponInstance.setLoadIterationCount(weaponInstance.getLoadIterationCount() - 1);
   }

   public void completeAllLoadIterations(ItemWeaponInstance weaponInstance) {
      if (weaponInstance.getWeapon().getAllReloadIterationsCompletedSound() != null) {
         weaponInstance.getPlayer().playSound(weaponInstance.getWeapon().getAllReloadIterationsCompletedSound(), 1.0F, getReloadSoundPitch(weaponInstance));
      }
   }
}
