package com.gtnewhorizon.newgunrizons.items;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentContainer;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.entities.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceFactory;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.StatusMessageManager;
import com.gtnewhorizon.newgunrizons.enchantments.ModEnchantments;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.registry.Sounds;
import com.gtnewhorizon.newgunrizons.weapon.Reloadable;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponFireAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponReloadAspect;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemWeapon extends Item implements ItemInstanceFactory<ItemWeaponInstance, WeaponState>, AttachmentContainer, Reloadable, Updatable {
   private String shootSound;
   private String endOfShootSound;
   private String silencedShootSound;
   private String reloadSound;
   private String reloadIterationSound;
   private String allReloadIterationsCompletedSound;
   private String unloadSound;
   private String ejectSpentRoundSound;
   private final String name;
   private final String textureName;
   private final int ammoCapacity;
   private final float recoil;
   private final ItemAmmo ammo;
   private final float fireRate;
   private final List<Integer> maxShots;
   private final String crosshair;
   private final String crosshairRunning;
   private final String crosshairZoomed;
   private final float spawnEntitySpeed;
   private final float spawnEntityDamage;
   private final float spawnEntityExplosionRadius;
   private final float spawnEntityGravityVelocity;
   private final float inaccuracy;
   private final int pellets;
   private final float flashIntensity;
   private final Supplier<Float> flashScale;
   private final Supplier<Float> flashOffsetX;
   private final Supplier<Float> flashOffsetY;
   private final Supplier<Float> flashOffsetZ;
   private final Supplier<Float> smokeOffsetX;
   private final Supplier<Float> smokeOffsetY;
   private final boolean ejectSpentRoundRequired;
   private final int maxBulletsPerReload;
   private final Function<ItemStack, List<String>> informationProvider;
   private final Map<ItemAttachment, CompatibleAttachment> compatibleAttachments;
   private final WeaponRenderer renderer;
   private final long reloadingTimeout;
   private final long loadIterationTimeout;
   private final long pumpTimeoutMilliseconds;
   private final float shellCasingForwardOffset;
   private final float shellCasingVerticalOffset;
   private final boolean shellCasingEjectEnabled;
   private final boolean hasIteratedLoad;
   private final float silencedShootSoundVolume;
   private final float shootSoundVolume;

   private ItemWeapon(ItemWeapon.Builder builder) {
      this.name = builder.name;
      this.textureName = builder.textureName;
      this.ammoCapacity = builder.ammoCapacity;
      this.recoil = builder.recoil;
      this.ammo = builder.ammo;
      this.fireRate = builder.fireRate;
      this.maxShots = new ArrayList<>(builder.maxShots);
      this.crosshair = builder.crosshair;
      this.crosshairRunning = builder.crosshairRunning;
      this.crosshairZoomed = builder.crosshairZoomed;
      this.spawnEntitySpeed = builder.spawnEntitySpeed;
      this.spawnEntityDamage = builder.spawnEntityDamage;
      this.spawnEntityExplosionRadius = builder.spawnEntityExplosionRadius;
      this.spawnEntityGravityVelocity = builder.spawnEntityGravityVelocity;
      this.inaccuracy = builder.inaccuracy;
      this.pellets = builder.pellets;
      this.flashIntensity = builder.flashIntensity;
      this.flashScale = builder.flashScale;
      this.flashOffsetX = builder.flashOffsetX;
      this.flashOffsetY = builder.flashOffsetY;
      this.flashOffsetZ = builder.flashOffsetZ;
      this.smokeOffsetX = builder.smokeOffsetX;
      this.smokeOffsetY = builder.smokeOffsetY;
      this.ejectSpentRoundRequired = builder.ejectSpentRoundRequired;
      this.maxBulletsPerReload = builder.maxBulletsPerReload;
      this.informationProvider = builder.informationProvider;
      this.compatibleAttachments = builder.compatibleAttachments;
      this.renderer = builder.renderer;
      this.reloadingTimeout = builder.reloadingTimeout;
      this.loadIterationTimeout = builder.loadIterationTimeout;
      this.pumpTimeoutMilliseconds = builder.pumpTimeoutMilliseconds;
      this.shellCasingForwardOffset = builder.shellCasingForwardOffset;
      this.shellCasingVerticalOffset = builder.shellCasingVerticalOffset;
      this.shellCasingEjectEnabled = builder.shellCasingEjectEnabled;
      this.hasIteratedLoad = builder.hasIteratedLoad;
      this.silencedShootSoundVolume = builder.silencedShootSoundVolume;
      this.shootSoundVolume = builder.shootSoundVolume;
      this.setMaxStackSize(1);
   }

   @Override
   public int getItemEnchantability() {
      return 14;
   }

   @Override
   public int getItemEnchantability(ItemStack stack) {
      return 14;
   }

   @Override
   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
      return true;
   }

   @Override
   public boolean isItemTool(ItemStack stack) {
      return true;
   }

   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.onCreated(stack, worldIn, playerIn);
      stack.setTagCompound(new NBTTagCompound());
   }

   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack) {
      return true;
   }

   public boolean showDurabilityBar(ItemStack stack) {
      return this.getCompatibleMagazines().isEmpty();
   }

   public double getDurabilityForDisplay(ItemStack stack) {
      return this.ammoCapacity <= 0 ? 0.0 : 1.0 - (double)ItemInstance.getAmmo(stack) / this.ammoCapacity;
   }

   public void toggleAiming() {
      ItemWeaponInstance mainHandHeldWeaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
      if (mainHandHeldWeaponInstance != null
         && (mainHandHeldWeaponInstance.getState() == WeaponState.READY || mainHandHeldWeaponInstance.getState() == WeaponState.EJECT_REQUIRED)) {
         mainHandHeldWeaponInstance.setAimed(!mainHandHeldWeaponInstance.isAimed());
      }
   }

   public String getCrosshair(ItemWeaponInstance weaponInstance) {
      if (weaponInstance.isAimed()) {
         String crosshair = null;
         ItemAttachment scopeAttachment = WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.SCOPE, weaponInstance);
         if (scopeAttachment != null) {
            crosshair = scopeAttachment.getCrosshair();
         }

         if (crosshair == null) {
            crosshair = this.crosshairZoomed;
         }

         return crosshair;
      } else {
         return weaponInstance.getPlayer().isSprinting() ? this.crosshairRunning : this.crosshair;
      }
   }

   public static boolean isActiveAttachment(ItemWeaponInstance weaponInstance, ItemAttachment attachment) {
      return weaponInstance != null && WeaponAttachmentAspect.isActiveAttachment(attachment, weaponInstance);
   }

   public int getCurrentAmmo() {
      ItemWeaponInstance state = ItemInstanceRegistry.getMainHeldWeapon();
      return state.getAmmo();
   }

   @Override
   public List<CompatibleAttachment> getActiveAttachments(EntityLivingBase player, ItemStack itemStack) {
      return WeaponAttachmentAspect.INSTANCE.getActiveAttachments(player, itemStack);
   }

   public boolean ejectSpentRoundRequired() {
      return this.ejectSpentRoundRequired;
   }

   public List<ItemMagazine> getCompatibleMagazines() {
      return this.compatibleAttachments.keySet().stream().filter(a -> a instanceof ItemMagazine).map(a -> (ItemMagazine)a).collect(Collectors.toList());
   }

   public List<ItemAttachment> getCompatibleAttachments(Class<? extends ItemAttachment> target) {
      return this.compatibleAttachments.keySet().stream().filter(target::isInstance).collect(Collectors.toList());
   }

   public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean flag) {
      // Add enchantments at the top, like vanilla items
      NBTTagList enchTags = itemStack.getEnchantmentTagList();
      if (enchTags != null && enchTags.tagCount() > 0) {
         for (int i = 0; i < enchTags.tagCount(); i++) {
            short id = enchTags.getCompoundTagAt(i).getShort("id");
            short lvl = enchTags.getCompoundTagAt(i).getShort("lvl");
            if (Enchantment.enchantmentsList[id] != null) {
               tooltip.add(Enchantment.enchantmentsList[id].getTranslatedName(lvl));
            }
         }
         tooltip.add("");
      }

      if (tooltip != null && this.informationProvider != null) {
         List<String> lines = this.informationProvider.apply(itemStack);
         int hollowPointLevel = ModEnchantments.getLevel(ModEnchantments.hollowPoint, itemStack);
         for (String line : lines) {
            if (hollowPointLevel > 0 && line.startsWith("Damage: ")) {
               try {
                  float baseDamage = Float.parseFloat(line.substring("Damage: ".length()));
                  float bonus = 0.5F + 0.5F * hollowPointLevel;
                  float totalDamage = baseDamage + bonus;
                  tooltip.add(String.format("Damage: %.1f \u00a7a(+%.1f)\u00a7r", totalDamage, bonus));
               } catch (NumberFormatException e) {
                  tooltip.add(line);
               }
            } else {
               tooltip.add(line);
            }
         }
      }
   }

   @Override
   public void reloadHeldItem(EntityPlayer player) {
      WeaponReloadAspect.INSTANCE.reloadMainHeldItem(player);
   }

   @Override
   public void update(EntityPlayer player) {
      WeaponReloadAspect.INSTANCE.updateMainHeldItem(player);
      WeaponFireAspect.INSTANCE.onUpdate(player);
      WeaponAttachmentAspect.INSTANCE.updateMainHeldItem(player);
   }

   public void tryFire(EntityPlayer player) {
      WeaponFireAspect.INSTANCE.onFireButtonClick(player);
   }

   public void tryStopFire(EntityPlayer player) {
      WeaponFireAspect.INSTANCE.onFireButtonRelease(player);
   }

   public ItemWeaponInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
      ItemWeaponInstance instance = new ItemWeaponInstance(slot, player, itemStack);
      instance.setState(WeaponState.READY);
      instance.setRecoil(this.recoil);
      instance.setMaxShots(this.maxShots.get(0));

      for (CompatibleAttachment compatibleAttachment : this.getCompatibleAttachments().values()) {
         ItemAttachment attachment = compatibleAttachment.getAttachment();
         if (compatibleAttachment.isDefault() && attachment.getApplyHandler() != null) {
            attachment.getApplyHandler().apply(attachment, instance);
         }
      }

      return instance;
   }

   public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
      WeaponAttachmentAspect.INSTANCE.toggleClientAttachmentSelectionMode(player);
   }

   public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player) {
      ItemWeaponInstance instance = (ItemWeaponInstance)ItemInstance.fromStack(itemStack);
      return instance == null || instance.getState() == WeaponState.READY;
   }

   public void changeFireMode(ItemWeaponInstance instance) {
      int currentIndex = this.maxShots.indexOf(instance.getMaxShots());
      int nextIndex = (currentIndex + 1) % this.maxShots.size();
      int result = this.maxShots.get(nextIndex);
      instance.setMaxShots(result);
      String message;
      if (result == 1) {
         message = StatCollector.translateToLocalFormatted("gui.firearmMode.semi", new Object[0]);
      } else if (result == Integer.MAX_VALUE) {
         message = StatCollector.translateToLocalFormatted("gui.firearmMode.auto", new Object[0]);
      } else {
         message = StatCollector.translateToLocalFormatted("gui.firearmMode.burst", new Object[0]);
      }

      StatusMessageManager.INSTANCE.addMessage(StatCollector.translateToLocalFormatted("gui.firearmMode", new Object[]{message}), 1000L);
      if (Sounds.FIRE_MODE_SWITCH != null) {
         instance.getPlayer().playSound(Sounds.FIRE_MODE_SWITCH, 1.0F, 1.0F);
      }

      NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)4, ((EntityPlayer)instance.getPlayer()).inventory.currentItem));
   }

   public void spawnBullet(EntityLivingBase player) {
      this.spawnBullet(player, null);
   }

   public void spawnBullet(EntityLivingBase player, ItemStack weaponStack) {
      EntityBullet bullet = new EntityBullet(
         this,
         player.worldObj,
         player,
         this.spawnEntitySpeed,
         this.spawnEntityGravityVelocity,
         this.inaccuracy,
         this.spawnEntityDamage,
         this.spawnEntityExplosionRadius
      );
      if (weaponStack != null) {
         bullet.applyEnchantments(weaponStack);
      }
      bullet.setPositionAndDirection();
      player.worldObj.spawnEntityInWorld(bullet);
   }

   public void spawnShell(ItemWeaponInstance weaponInstance, EntityLivingBase player) {
      EntityShellCasing shell = new EntityShellCasing(weaponInstance, player.worldObj, player, 0.1F, 0.05F, 20.0F);
      shell.setPositionAndDirection();
      player.worldObj.spawnEntityInWorld(shell);
   }

   public long getTotalReloadingDuration() {
      return this.renderer.getTotalReloadingDuration();
   }

   public long getPrepareFirstLoadIterationAnimationDuration() {
      return this.renderer.getPrepareFirstLoadIterationAnimationDuration();
   }

   public long getAllLoadIterationAnimationsCompletedDuration() {
      return this.renderer.getAllLoadIterationAnimationsCompletedDuration();
   }

   public long getTotalLoadIterationDuration() {
      return this.renderer.getTotalLoadIterationDuration();
   }

   public long getTotalUnloadingDuration() {
      return this.renderer.getTotalUnloadingDuration();
   }

   public boolean hasRecoilPositioning() {
      return this.renderer.hasRecoilPositioning();
   }

   public void incrementZoom(ItemWeaponInstance instance) {
      Item scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
      if (scopeItem instanceof ItemScope && ((ItemScope)scopeItem).isOptical()) {
         float minZoom = ((ItemScope)scopeItem).getMinZoom();
         float maxZoom = ((ItemScope)scopeItem).getMaxZoom();
         float increment = (minZoom - maxZoom) / 20.0F;
         float zoom = instance.getZoom();
         if (zoom > maxZoom) {
            zoom = Math.max(zoom - increment, maxZoom);
         }

         instance.setZoom(zoom);
         float ratio = (minZoom - zoom) / (minZoom - maxZoom);
         StatusMessageManager.INSTANCE.addMessage(StatCollector.translateToLocalFormatted("gui.currentZoom", new Object[]{Math.round(ratio * 100.0F)}), 800L);
         if (Sounds.ZOOM != null) {
            instance.getPlayer().playSound(Sounds.ZOOM, 1.0F, 1.0F);
         }

         NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)6, ((EntityPlayer)instance.getPlayer()).inventory.currentItem));
      }
   }

   public void decrementZoom(ItemWeaponInstance instance) {
      Item scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
      if (scopeItem instanceof ItemScope && ((ItemScope)scopeItem).isOptical()) {
         float minZoom = ((ItemScope)scopeItem).getMinZoom();
         float maxZoom = ((ItemScope)scopeItem).getMaxZoom();
         float increment = (minZoom - maxZoom) / 20.0F;
         float zoom = instance.getZoom();
         if (zoom < minZoom) {
            zoom = Math.min(zoom + increment, minZoom);
         }

         instance.setZoom(zoom);
         float ratio = (minZoom - zoom) / (minZoom - maxZoom);
         StatusMessageManager.INSTANCE.addMessage(StatCollector.translateToLocalFormatted("gui.currentZoom", new Object[]{Math.round(ratio * 100.0F)}), 800L);
         if (Sounds.ZOOM != null) {
            instance.getPlayer().playSound(Sounds.ZOOM, 1.0F, 1.0F);
         }

         NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)7, ((EntityPlayer)instance.getPlayer()).inventory.currentItem));
      }
   }

   public ItemAttachment.AttachmentHandler getEquivalentHandler(AttachmentCategory attachmentCategory) {
      if (attachmentCategory == AttachmentCategory.SCOPE) {
         return (a, i) -> {};
      } else {
         return attachmentCategory == AttachmentCategory.GRIP ? (a, i) -> i.setRecoil(this.recoil) : (a, i) -> {};
      }
   }

   public boolean hasIteratedLoad() {
      return this.hasIteratedLoad;
   }

   public String getShootSound() {
      return this.shootSound;
   }

   public String getEndOfShootSound() {
      return this.endOfShootSound;
   }

   public String getSilencedShootSound() {
      return this.silencedShootSound;
   }

   public String getReloadSound() {
      return this.reloadSound;
   }

   public String getReloadIterationSound() {
      return this.reloadIterationSound;
   }

   public String getAllReloadIterationsCompletedSound() {
      return this.allReloadIterationsCompletedSound;
   }

   public String getUnloadSound() {
      return this.unloadSound;
   }

   public String getEjectSpentRoundSound() {
      return this.ejectSpentRoundSound;
   }

   public String getName() {
      return this.name;
   }

   public String getTextureName() {
      return this.textureName;
   }

   public int getAmmoCapacity() {
      return this.ammoCapacity;
   }

   public float getRecoil() {
      return this.recoil;
   }

   public ItemAmmo getAmmo() {
      return this.ammo;
   }

   public float getFireRate() {
      return this.fireRate;
   }

   public List<Integer> getMaxShots() {
      return this.maxShots;
   }

   public int getPellets() {
      return this.pellets;
   }

   public float getFlashIntensity() {
      return this.flashIntensity;
   }

   public Supplier<Float> getFlashScale() {
      return this.flashScale;
   }

   public Supplier<Float> getFlashOffsetX() {
      return this.flashOffsetX;
   }

   public Supplier<Float> getFlashOffsetY() {
      return this.flashOffsetY;
   }

   public Supplier<Float> getFlashOffsetZ() {
      return this.flashOffsetZ;
   }

   public Supplier<Float> getSmokeOffsetX() {
      return this.smokeOffsetX;
   }

   public Supplier<Float> getSmokeOffsetY() {
      return this.smokeOffsetY;
   }

   public int getMaxBulletsPerReload() {
      return this.maxBulletsPerReload;
   }

   public Map<ItemAttachment, CompatibleAttachment> getCompatibleAttachments() {
      return this.compatibleAttachments;
   }

   public WeaponRenderer getRenderer() {
      return this.renderer;
   }

   public long getReloadingTimeout() {
      return this.reloadingTimeout;
   }

   public long getLoadIterationTimeout() {
      return this.loadIterationTimeout;
   }

   public long getPumpTimeoutMilliseconds() {
      return this.pumpTimeoutMilliseconds;
   }

   public float getShellCasingForwardOffset() {
      return this.shellCasingForwardOffset;
   }

   public float getShellCasingVerticalOffset() {
      return this.shellCasingVerticalOffset;
   }

   public boolean isShellCasingEjectEnabled() {
      return this.shellCasingEjectEnabled;
   }

   public float getSilencedShootSoundVolume() {
      return this.silencedShootSoundVolume;
   }

   public float getShootSoundVolume() {
      return this.shootSoundVolume;
   }

   public static class Builder {
      public String name;
      public String textureName;
      public int ammoCapacity = 0;
      float recoil = 1.0F;
      private String shootSound;
      private String silencedShootSound;
      private String reloadSound;
      private String reloadIterationSound;
      private String allReloadIterationsCompletedSound;
      private String unloadSound;
      private String ejectSpentRoundSound;
      private String endOfShootSound;
      public ItemAmmo ammo;
      public float fireRate = 0.5F;
      private CreativeTabs creativeTab;
      private WeaponRenderer renderer;
      public List<Integer> maxShots = new ArrayList<>();
      String crosshair;
      String crosshairRunning;
      String crosshairZoomed;
      public float spawnEntitySpeed = 10.0F;
      public float spawnEntityDamage;
      public float spawnEntityExplosionRadius;
      public float spawnEntityGravityVelocity;
      public long reloadingTimeout = 10L;
      public long loadIterationTimeout = 10L;
      Map<ItemAttachment, CompatibleAttachment> compatibleAttachments = new HashMap<>();
      private Class<? extends EntityBullet> spawnEntityClass;
      public long pumpTimeoutMilliseconds;
      private float inaccuracy = 1.0F;
      public int pellets = 1;
      public float flashIntensity = 0.4F;
      public Supplier<Float> flashScale = () -> 1.0F;
      public Supplier<Float> flashOffsetX = () -> 0.0F;
      public Supplier<Float> flashOffsetY = () -> 0.0F;
      public Supplier<Float> flashOffsetZ = () -> 0.0F;
      public Supplier<Float> smokeOffsetX = () -> 0.0F;
      public Supplier<Float> smokeOffsetY = () -> 0.0F;
      private boolean ejectSpentRoundRequired;
      public int maxBulletsPerReload;
      private Function<ItemStack, List<String>> informationProvider;
      private float shellCasingForwardOffset = 0.1F;
      private float shellCasingVerticalOffset = 0.0F;
      public boolean shellCasingEjectEnabled = true;
      private boolean hasIteratedLoad;
      private final float silencedShootSoundVolume = 0.7F;
      private final float shootSoundVolume = 10.0F;

      public ItemWeapon.Builder withEjectRoundRequired() {
         this.ejectSpentRoundRequired = true;
         return this;
      }

      public ItemWeapon.Builder withInformationProvider(Function<ItemStack, List<String>> informationProvider) {
         this.informationProvider = informationProvider;
         return this;
      }

      public ItemWeapon.Builder withReloadingTime(long reloadingTime) {
         this.reloadingTimeout = reloadingTime;
         return this;
      }

      public ItemWeapon.Builder withName(String name) {
         this.name = name;
         return this;
      }

      public ItemWeapon.Builder withAmmoCapacity(int ammoCapacity) {
         this.ammoCapacity = ammoCapacity;
         return this;
      }

      public ItemWeapon.Builder withMaxBulletsPerReload(int maxBulletsPerReload) {
         this.maxBulletsPerReload = maxBulletsPerReload;
         return this;
      }

      public ItemWeapon.Builder withIteratedLoad() {
         this.hasIteratedLoad = true;
         return this;
      }

      public ItemWeapon.Builder withRecoil(float recoil) {
         this.recoil = recoil;
         return this;
      }

      public ItemWeapon.Builder withMaxShots(int... maxShots) {
         for (int m : maxShots) {
            this.maxShots.add(m);
         }

         return this;
      }

      public ItemWeapon.Builder withFireRate(float fireRate) {
         if (!(fireRate > 1.0F) && !(fireRate <= 0.0F)) {
            this.fireRate = fireRate;
            return this;
         } else {
            throw new IllegalArgumentException("Invalid fire rate " + fireRate);
         }
      }

      public ItemWeapon.Builder withTextureName(String textureName) {
         this.textureName = textureName.toLowerCase() + ".png";
         return this;
      }

      public ItemWeapon.Builder withCrosshair(String crosshair) {
         this.crosshair = "newgunrizons:textures/crosshairs/" + crosshair.toLowerCase() + ".png";
         return this;
      }

      public ItemWeapon.Builder withCrosshairRunning(String crosshairRunning) {
         this.crosshairRunning = "newgunrizons:textures/crosshairs/" + crosshairRunning.toLowerCase() + ".png";
         return this;
      }

      public ItemWeapon.Builder withCrosshairZoomed(String crosshairZoomed) {
         this.crosshairZoomed = "newgunrizons:textures/crosshairs/" + crosshairZoomed.toLowerCase() + ".png";
         return this;
      }

      public ItemWeapon.Builder withShootSound(String shootSound) {
         this.shootSound = shootSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withEndOfShootSound(String endOfShootSound) {
         this.endOfShootSound = endOfShootSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withEjectSpentRoundSound(String ejectSpentRoundSound) {
         this.ejectSpentRoundSound = ejectSpentRoundSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withSilencedShootSound(String silencedShootSound) {
         this.silencedShootSound = silencedShootSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withReloadSound(String reloadSound) {
         this.reloadSound = reloadSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withReloadIterationSound(String reloadIterationSound) {
         this.reloadIterationSound = reloadIterationSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withAllReloadIterationsCompletedSound(String allReloadIterationCompletedSound) {
         this.allReloadIterationsCompletedSound = allReloadIterationCompletedSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withUnloadSound(String unloadSound) {
         this.unloadSound = unloadSound.toLowerCase();
         return this;
      }

      public ItemWeapon.Builder withCreativeTab(CreativeTabs creativeTab) {
         this.creativeTab = creativeTab;
         return this;
      }

      public ItemWeapon.Builder withSpawnEntityDamage(float spawnEntityDamage) {
         this.spawnEntityDamage = spawnEntityDamage;
         return this;
      }

      public ItemWeapon.Builder withSpawnEntitySpeed(float spawnEntitySpeed) {
         this.spawnEntitySpeed = spawnEntitySpeed;
         return this;
      }

      public ItemWeapon.Builder withSpawnEntityExplosionRadius(float spawnEntityExplosionRadius) {
         this.spawnEntityExplosionRadius = spawnEntityExplosionRadius;
         return this;
      }

      public ItemWeapon.Builder withSpawnEntityGravityVelocity(float spawnEntityGravityVelocity) {
         this.spawnEntityGravityVelocity = spawnEntityGravityVelocity;
         return this;
      }

      public ItemWeapon.Builder withInaccuracy(float inaccuracy) {
         this.inaccuracy = inaccuracy;
         return this;
      }

      public ItemWeapon.Builder withRenderer(WeaponRenderer renderer) {
         this.renderer = renderer;
         return this;
      }

      public ItemWeapon.Builder withCompatibleBullet(ItemBullet bullet, Consumer<ModelBase> positioner) {
         this.compatibleAttachments.put(bullet, new CompatibleAttachment(bullet, positioner));
         return this;
      }

      public ItemWeapon.Builder withCompatibleAttachment(
         ItemAttachment attachment, ItemAttachment.AttachmentHandler applyHandler, ItemAttachment.AttachmentHandler removeHandler
      ) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, applyHandler, removeHandler));
         return this;
      }

      public ItemWeapon.Builder withCompatibleAttachment(
         ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning, Consumer<ModelBase> modelPositioning
      ) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, false));
         return this;
      }

      public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, null, false));
         return this;
      }

      public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioner) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
         return this;
      }

      public ItemWeapon.Builder withCompatibleAttachment(
         ItemAttachment attachment, boolean isDefault, BiConsumer<EntityLivingBase, ItemStack> positioning, Consumer<ModelBase> modelPositioning
      ) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault));
         return this;
      }

      public ItemWeapon.Builder withCompatibleAttachment(
         ItemAttachment attachment,
         boolean isDefault,
         boolean isPermanent,
         BiConsumer<EntityLivingBase, ItemStack> positioning,
         Consumer<ModelBase> modelPositioning
      ) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault, isPermanent));
         return this;
      }

      public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault, Consumer<ModelBase> positioner) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner, isDefault));
         return this;
      }

      public ItemWeapon.Builder withShellCasingEjectEnabled(boolean shellCasingEjectEnabled) {
         this.shellCasingEjectEnabled = shellCasingEjectEnabled;
         return this;
      }

      public ItemWeapon.Builder withShellCasingForwardOffset(float shellCasingForwardOffset) {
         this.shellCasingForwardOffset = shellCasingForwardOffset;
         return this;
      }

      public ItemWeapon.Builder withShellCasingVerticalOffset(float shellCasingVerticalOffset) {
         this.shellCasingVerticalOffset = shellCasingVerticalOffset;
         return this;
      }

      public ItemWeapon.Builder withPumpTimeout(long pumpTimeoutMilliseconds) {
         this.pumpTimeoutMilliseconds = pumpTimeoutMilliseconds;
         return this;
      }

      public ItemWeapon.Builder withPellets(int pellets) {
         if (pellets < 1) {
            throw new IllegalArgumentException("Pellet count must be >= 1");
         } else {
            this.pellets = pellets;
            return this;
         }
      }

      public ItemWeapon.Builder withFlashIntensity(float flashIntensity) {
         if (!(flashIntensity < 0.0F) && !(flashIntensity > 1.0F)) {
            this.flashIntensity = flashIntensity;
            return this;
         } else {
            throw new IllegalArgumentException("Invalid flash intensity");
         }
      }

      public ItemWeapon.Builder withFlashScale(Supplier<Float> flashScale) {
         this.flashScale = flashScale;
         return this;
      }

      public ItemWeapon.Builder withFlashOffsetX(Supplier<Float> flashOffsetX) {
         this.flashOffsetX = flashOffsetX;
         return this;
      }

      public ItemWeapon.Builder withFlashOffsetY(Supplier<Float> flashOffsetY) {
         this.flashOffsetY = flashOffsetY;
         return this;
      }

      public ItemWeapon.Builder withFlashOffsetZ(Supplier<Float> flashOffsetZ) {
         this.flashOffsetZ = flashOffsetZ;
         return this;
      }

      public ItemWeapon build() {
         if (this.name == null) {
            throw new IllegalStateException("Weapon name not provided");
         } else {
            if (this.shootSound == null) {
               this.shootSound = this.name;
            }

            if (this.silencedShootSound == null) {
               this.silencedShootSound = this.shootSound;
            }

            if (this.reloadSound == null) {
               this.reloadSound = "reload";
            }

            if (this.unloadSound == null) {
               this.unloadSound = "unload";
            }

            if (this.spawnEntityClass == null) {
               this.spawnEntityClass = EntityBullet.class;
            }

            if (this.crosshairRunning == null) {
               this.crosshairRunning = this.crosshair;
            }

            if (this.crosshairZoomed == null) {
               this.crosshairZoomed = this.crosshair;
            }

            if (this.maxBulletsPerReload == 0) {
               this.maxBulletsPerReload = this.ammoCapacity;
            }

            if (this.maxShots.isEmpty()) {
               this.maxShots.add(Integer.MAX_VALUE);
            }

            ItemWeapon weapon = new ItemWeapon(this);
            weapon.shootSound = Sounds.resolve(this.shootSound);
            if (this.endOfShootSound != null) {
               weapon.endOfShootSound = Sounds.resolve(this.endOfShootSound);
            }

            weapon.reloadSound = Sounds.resolve(this.reloadSound);
            weapon.reloadIterationSound = Sounds.resolve(this.reloadIterationSound);
            weapon.allReloadIterationsCompletedSound = Sounds.resolve(this.allReloadIterationsCompletedSound);
            weapon.unloadSound = Sounds.resolve(this.unloadSound);
            weapon.silencedShootSound = Sounds.resolve(this.silencedShootSound);
            if (this.ejectSpentRoundSound != null) {
               weapon.ejectSpentRoundSound = Sounds.resolve(this.ejectSpentRoundSound);
            }

            weapon.setCreativeTab(this.creativeTab);
            weapon.setUnlocalizedName(this.name);
            if (this.ammo != null) {
               this.ammo.addCompatibleWeapon(weapon);
            }

            NewGunrizonsMod.proxy.registerItem(this.name, weapon, this.renderer);
            return weapon;
         }
      }

      public String getName() {
         return this.name;
      }

      public String getTextureName() {
         return this.textureName;
      }
   }
}
