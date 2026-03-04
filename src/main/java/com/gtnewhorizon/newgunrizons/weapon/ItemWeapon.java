package com.gtnewhorizon.newgunrizons.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.gtnewhorizon.newgunrizons.items.ItemAmmo;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentContainer;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.crafting.OptionsMetadata;
import com.gtnewhorizon.newgunrizons.entities.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.entities.EntityBullet;
import com.gtnewhorizon.newgunrizons.util.Updatable;

import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;

public class ItemWeapon extends Item implements PlayerItemInstanceFactory<PlayerWeaponInstance, WeaponState>,
    AttachmentContainer, Reloadable, Updatable {

    public ItemWeapon.Builder builder;
    @Getter
    private final ModContext modContext;

    @Getter
    private String shootSound;
    @Getter
    private String endOfShootSound;
    @Getter
    private String silencedShootSound;
    @Getter
    private String reloadSound;
    @Getter
    private String reloadIterationSound;
    @Getter
    private String allReloadIterationsCompletedSound;
    @Getter
    private String unloadSound;
    @Getter
    private String ejectSpentRoundSound;

    public ItemWeapon(ItemWeapon.Builder builder, ModContext modContext) {
        this.builder = builder;
        this.modContext = modContext;
        this.setMaxStackSize(1);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        stack.setTagCompound(new NBTTagCompound());
    }

    public String getName() {
        return this.builder.name;
    }

    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack) {
        return true;
    }

    public void toggleAiming() {
        PlayerWeaponInstance mainHandHeldWeaponInstance = this.modContext.getMainHeldWeapon();
        if (mainHandHeldWeaponInstance != null && (mainHandHeldWeaponInstance.getState() == WeaponState.READY
            || mainHandHeldWeaponInstance.getState() == WeaponState.EJECT_REQUIRED)) {
            mainHandHeldWeaponInstance.setAimed(!mainHandHeldWeaponInstance.isAimed());
        }

    }

    public Map<ItemAttachment, CompatibleAttachment> getCompatibleAttachments() {
        return this.builder.compatibleAttachments;
    }

    public String getCrosshair(PlayerWeaponInstance weaponInstance) {
        if (weaponInstance.isAimed()) {
            String crosshair = null;
            ItemAttachment scopeAttachment = WeaponAttachmentAspect
                .getActiveAttachment(AttachmentCategory.SCOPE, weaponInstance);
            if (scopeAttachment != null) {
                crosshair = scopeAttachment.getCrosshair();
            }

            if (crosshair == null) {
                crosshair = this.builder.crosshairZoomed;
            }

            return crosshair;
        } else {
            return weaponInstance.getPlayer()
                .isSprinting() ? this.builder.crosshairRunning : this.builder.crosshair;
        }
    }

    public static boolean isActiveAttachment(PlayerWeaponInstance weaponInstance, ItemAttachment attachment) {
        return weaponInstance != null && WeaponAttachmentAspect.isActiveAttachment(attachment, weaponInstance);
    }

    public int getCurrentAmmo() {
        PlayerWeaponInstance state = this.modContext.getMainHeldWeapon();
        return state.getAmmo();
    }

    public int getAmmoCapacity() {
        return this.builder.ammoCapacity;
    }

    public int getMaxBulletsPerReload() {
        return this.builder.maxBulletsPerReload;
    }

    public List<CompatibleAttachment> getActiveAttachments(EntityLivingBase player, ItemStack itemStack) {
        return this.modContext.getAttachmentAspect()
            .getActiveAttachments(player, itemStack);
    }

    public boolean ejectSpentRoundRequired() {
        return this.builder.ejectSpentRoundRequired;
    }

    public List<ItemMagazine> getCompatibleMagazines() {
        return this.builder.compatibleAttachments.keySet()
            .stream()
            .filter((a) -> a instanceof ItemMagazine)
            .map((a) -> (ItemMagazine) a)
            .collect(Collectors.toList());
    }

    public WeaponRenderer getRenderer() {
        return this.builder.renderer;
    }

    public List<ItemAttachment> getCompatibleAttachments(Class<? extends ItemAttachment> target) {
        return this.builder.compatibleAttachments.keySet()
            .stream()
            .filter(target::isInstance)
            .collect(Collectors.toList());
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean flag) {
        if (tooltip != null && this.builder.informationProvider != null) {
            tooltip.addAll(this.builder.informationProvider.apply(itemStack));
        }
    }

    public void reloadMainHeldItemForPlayer(EntityPlayer player) {
        this.modContext.getWeaponReloadAspect()
            .reloadMainHeldItem(player);
    }

    public void update(EntityPlayer player) {
        this.modContext.getWeaponReloadAspect()
            .updateMainHeldItem(player);
        this.modContext.getWeaponFireAspect()
            .onUpdate(player);
        this.modContext.getAttachmentAspect()
            .updateMainHeldItem(player);
    }

    public void tryFire(EntityPlayer player) {
        this.modContext.getWeaponFireAspect()
            .onFireButtonClick(player);
    }

    public void tryStopFire(EntityPlayer player) {
        this.modContext.getWeaponFireAspect()
            .onFireButtonRelease(player);
    }

    public PlayerWeaponInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
        PlayerWeaponInstance instance = new PlayerWeaponInstance(slot, player, itemStack);
        instance.setState(WeaponState.READY);
        instance.setRecoil(this.builder.recoil);
        instance.setMaxShots(this.builder.maxShots.get(0));

        for (CompatibleAttachment compatibleAttachment : this.getCompatibleAttachments().values()) {
            ItemAttachment attachment = compatibleAttachment.getAttachment();
            if (compatibleAttachment.isDefault() && attachment.getApply2() != null) {
                attachment.apply2.apply(attachment, instance);
            }
        }

        return instance;
    }

    public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
        this.modContext.getAttachmentAspect()
            .toggleClientAttachmentSelectionMode(player);
    }

    public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player) {
        PlayerWeaponInstance instance = (PlayerWeaponInstance) Tags.getInstance(itemStack);
        return instance == null || instance.getState() == WeaponState.READY;
    }

    public void changeFireMode(PlayerWeaponInstance instance) {
        int currentIndex = this.builder.maxShots.indexOf(instance.getMaxShots());
        int nextIndex = (currentIndex + 1) % this.builder.maxShots.size();
        int result = this.builder.maxShots.get(nextIndex);
        instance.setMaxShots(result);
        String message;
        if (result == 1) {
            message = StatCollector.translateToLocalFormatted("gui.firearmMode.semi");
        } else if (result == Integer.MAX_VALUE) {
            message = StatCollector.translateToLocalFormatted("gui.firearmMode.auto");
        } else {
            message = StatCollector.translateToLocalFormatted("gui.firearmMode.burst");
        }

        this.modContext.getStatusMessageCenter()
            .addMessage(StatCollector.translateToLocalFormatted("gui.firearmMode", message), 1000L);
        if (this.modContext.getChangeFireModeSound() != null) {
            instance.getPlayer()
                .playSound(this.modContext.getChangeFireModeSound(), 1.0F, 1.0F);
        }
    }

    public void spawnBullet(EntityLivingBase player) {
        EntityBullet bullet = new EntityBullet(
            this,
            player.worldObj,
            player,
            builder.spawnEntitySpeed,
            builder.spawnEntityGravityVelocity,
            builder.inaccuracy,
            builder.spawnEntityDamage,
            builder.spawnEntityExplosionRadius);
        bullet.setPositionAndDirection();
        player.worldObj.spawnEntityInWorld(bullet);
    }

    public void spawnShell(PlayerWeaponInstance weaponInstance, EntityLivingBase player) {
        EntityShellCasing shell = new EntityShellCasing(
            weaponInstance,
            player.worldObj,
            player,
            0.1F,
            0.05F,
            20.0F);
        shell.setPositionAndDirection();
        player.worldObj.spawnEntityInWorld(shell);
    }

    public long getTotalReloadingDuration() {
        return this.builder.renderer.getTotalReloadingDuration();
    }

    public long getPrepareFirstLoadIterationAnimationDuration() {
        return this.builder.renderer.getPrepareFirstLoadIterationAnimationDuration();
    }

    public long getAllLoadIterationAnimationsCompletedDuration() {
        return this.builder.renderer.getAllLoadIterationAnimationsCompletedDuration();
    }

    public long getTotalLoadIterationDuration() {
        return this.builder.renderer.getTotalLoadIterationDuration();
    }

    public long getTotalUnloadingDuration() {
        return this.builder.renderer.getTotalUnloadingDuration();
    }

    public boolean hasRecoilPositioning() {
        return this.builder.renderer.hasRecoilPositioning();
    }

    public void incrementZoom(PlayerWeaponInstance instance) {
        Item scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
        if (scopeItem instanceof ItemScope && ((ItemScope) scopeItem).isOptical()) {
            float minZoom = ((ItemScope) scopeItem).getMinZoom();
            float maxZoom = ((ItemScope) scopeItem).getMaxZoom();
            float increment = (minZoom - maxZoom) / 20.0F;
            float zoom = instance.getZoom();
            if (zoom > maxZoom) {
                zoom = Math.max(zoom - increment, maxZoom);
            }

            instance.setZoom(zoom);
            float ratio = (minZoom - zoom) / (minZoom - maxZoom);
            this.modContext.getStatusMessageCenter()
                .addMessage(
                    StatCollector.translateToLocalFormatted("gui.currentZoom", Math.round(ratio * 100.0F)),
                    800L);
            if (this.modContext.getZoomSound() != null) {
                instance.getPlayer()
                    .playSound(this.modContext.getZoomSound(), 1.0F, 1.0F);
            }
        }
    }

    public void decrementZoom(PlayerWeaponInstance instance) {
        Item scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
        if (scopeItem instanceof ItemScope && ((ItemScope) scopeItem).isOptical()) {
            float minZoom = ((ItemScope) scopeItem).getMinZoom();
            float maxZoom = ((ItemScope) scopeItem).getMaxZoom();
            float increment = (minZoom - maxZoom) / 20.0F;
            float zoom = instance.getZoom();
            if (zoom < minZoom) {
                zoom = Math.min(zoom + increment, minZoom);
            }

            instance.setZoom(zoom);
            float ratio = (minZoom - zoom) / (minZoom - maxZoom);
            this.modContext.getStatusMessageCenter()
                .addMessage(
                    StatCollector.translateToLocalFormatted("gui.currentZoom", Math.round(ratio * 100.0F)),
                    800L);
            if (this.modContext.getZoomSound() != null) {
                instance.getPlayer()
                    .playSound(this.modContext.getZoomSound(), 1.0F, 1.0F);
            }
        }
    }

    public ItemAttachment.ApplyHandler2 getEquivalentHandler(AttachmentCategory attachmentCategory) {
        ItemAttachment.ApplyHandler2 handler = (a, i) -> {};
        handler = switch (attachmentCategory) {
            case SCOPE -> (a, i) -> {};
            case GRIP -> (a, i) -> i.setRecoil(this.builder.recoil);
            default -> handler;
        };

        return handler;
    }

    public String getTextureName() { return this.builder.textureName; }

    public float getRecoil() {
        return this.builder.recoil;
    }

    public float getShellCasingVerticalOffset() {
        return this.builder.shellCasingVerticalOffset;
    }

    public float getShellCasingForwardOffset() {
        return this.builder.shellCasingForwardOffset;
    }

    public boolean isShellCasingEjectEnabled() {
        return this.builder.shellCasingEjectEnabled;
    }

    public float getSilencedShootSoundVolume() {
        return this.builder.silencedShootSoundVolume;
    }

    public float getShootSoundVolume() {
        return this.builder.shootSoundVolume;
    }

    public boolean hasIteratedLoad() {
        return this.builder.hasIteratedLoad;
    }

    public static class Builder {

        @Getter public String name;
        @Getter public String textureName;

        private String modId;

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
        List<Integer> maxShots = new ArrayList<>();
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
        public Supplier<Float> smokeOffsetX = () -> 0.0F;
        public Supplier<Float> smokeOffsetY = () -> 0.0F;
        private boolean ejectSpentRoundRequired;
        public int maxBulletsPerReload;
        private Function<ItemStack, List<String>> informationProvider;
        private CraftingComplexity craftingComplexity;
        private Object[] craftingMaterials;
        private float shellCasingForwardOffset = 0.1F;
        private float shellCasingVerticalOffset = 0.0F;

        public boolean shellCasingEjectEnabled = true;
        private boolean hasIteratedLoad;

        private final float silencedShootSoundVolume;
        private final float shootSoundVolume;

        private Object[] craftingRecipe;

        public Builder() {
            this.silencedShootSoundVolume = 0.7F;
            this.shootSoundVolume = 10.0F;
        }

        public ItemWeapon.Builder withModId(String modId) {
            this.modId = modId;
            return this;
        }

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

        /** @deprecated */
        @Deprecated
        public ItemWeapon.Builder withZoom(float zoom) {
            return this;
        }

        public ItemWeapon.Builder withMaxShots(int... maxShots) {
            for (int m : maxShots) {
                this.maxShots.add(m);
            }
            return this;
        }

        public ItemWeapon.Builder withFireRate(float fireRate) {
            if (fireRate >= 1.0F || fireRate <= 0.0F) {
                throw new IllegalArgumentException("Invalid fire rate " + fireRate);
            }
            this.fireRate = fireRate;
            return this;
        }

        public ItemWeapon.Builder withTextureName(String textureName) {
            this.textureName = textureName.toLowerCase() + ".png";
            return this;
        }

        public ItemWeapon.Builder withCrosshair(String crosshair) {
            this.crosshair = this.modId + ":" + "textures/crosshairs/" + crosshair.toLowerCase() + ".png";
            return this;
        }

        public ItemWeapon.Builder withCrosshairRunning(String crosshairRunning) {
            this.crosshairRunning = this.modId + ":"
                + "textures/crosshairs/"
                + crosshairRunning.toLowerCase()
                + ".png";
            return this;
        }

        public ItemWeapon.Builder withCrosshairZoomed(String crosshairZoomed) {
            this.crosshairZoomed = this.modId + ":"
                + "textures/crosshairs/"
                + crosshairZoomed.toLowerCase()
                + ".png";
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

        public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment,
                                                           ItemAttachment.ApplyHandler2 applyHandler, ItemAttachment.ApplyHandler2 removeHandler) {
            this.compatibleAttachments
                .put(attachment, new CompatibleAttachment(attachment, applyHandler, removeHandler));
            return this;
        }

        public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment,
                                                           BiConsumer<EntityLivingBase, ItemStack> positioning, Consumer<ModelBase> modelPositioning) {
            this.compatibleAttachments
                .put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, false));
            return this;
        }

        public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment,
                                                           BiConsumer<EntityLivingBase, ItemStack> positioning) {
            this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, null, false));
            return this;
        }

        public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioner) {
            this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
            return this;
        }

        public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault,
                                                           BiConsumer<EntityLivingBase, ItemStack> positioning, Consumer<ModelBase> modelPositioning) {
            this.compatibleAttachments
                .put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault));
            return this;
        }

        public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault,
                                                           boolean isPermanent, BiConsumer<EntityLivingBase, ItemStack> positioning,
                                                           Consumer<ModelBase> modelPositioning) {
            this.compatibleAttachments.put(
                attachment,
                new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault, isPermanent));
            return this;
        }

        public ItemWeapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault,
                                                           Consumer<ModelBase> positioner) {
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
            }
            this.pellets = pellets;
            return this;
        }

        public ItemWeapon.Builder withFlashIntensity(float flashIntensity) {
            if (flashIntensity < 0.0F || flashIntensity > 1.0F) {
                throw new IllegalArgumentException("Invalid flash intensity");
            }
            this.flashIntensity = flashIntensity;
            return this;
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

        public ItemWeapon.Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
            if (craftingComplexity == null) {
                throw new IllegalArgumentException("Crafting complexity not set");
            }
            if (craftingMaterials.length < 2) {
                throw new IllegalArgumentException("2 or more materials required for crafting");
            }
            this.craftingComplexity = craftingComplexity;
            this.craftingMaterials = craftingMaterials;
            return this;
        }

        public ItemWeapon.Builder withCraftingRecipe(Object... craftingRecipe) {
            this.craftingRecipe = craftingRecipe;
            return this;
        }

        public ItemWeapon build(ModContext modContext) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            }
            if (this.name == null) {
                throw new IllegalStateException("Weapon name not provided");
            }

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

            ItemWeapon weapon = new ItemWeapon(this, modContext);
            weapon.shootSound = modContext.registerSound(this.shootSound);
            if (this.endOfShootSound != null) {
                weapon.endOfShootSound = modContext.registerSound(this.endOfShootSound);
            }

            weapon.reloadSound = modContext.registerSound(this.reloadSound);
            weapon.reloadIterationSound = modContext.registerSound(this.reloadIterationSound);
            weapon.allReloadIterationsCompletedSound = modContext
                .registerSound(this.allReloadIterationsCompletedSound);
            weapon.unloadSound = modContext.registerSound(this.unloadSound);
            weapon.silencedShootSound = modContext.registerSound(this.silencedShootSound);
            if (this.ejectSpentRoundSound != null) {
                weapon.ejectSpentRoundSound = modContext.registerSound(this.ejectSpentRoundSound);
            }

            weapon.setCreativeTab(this.creativeTab);
            weapon.setUnlocalizedName(this.name);
            if (this.ammo != null) {
                this.ammo.addCompatibleWeapon(weapon);
            }

            for (ItemAttachment attachment : this.compatibleAttachments.keySet()) {
                attachment.addCompatibleWeapon(weapon);
            }

            modContext.registerWeapon(this.name, weapon, this.renderer);
            List<Object> shape;
            if (this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
                ItemStack itemStack = new ItemStack(weapon);
                shape = modContext.getRecipeManager()
                    .registerShapedRecipe(weapon, this.craftingRecipe);
                boolean hasOres = Arrays.stream(this.craftingRecipe)
                    .anyMatch((r) -> r instanceof String);
                if (hasOres) {
                    GameRegistry.addRecipe(new ShapedOreRecipe(itemStack, shape.toArray()).setMirrored(false));
                } else {
                    GameRegistry.addShapedRecipe(itemStack, shape.toArray());
                }
            } else if (this.craftingComplexity != null) {
                OptionsMetadata optionsMetadata = (new OptionsMetadata.OptionMetadataBuilder()).withSlotCount(9)
                    .build(
                        this.craftingComplexity,
                        Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));
                shape = modContext.getRecipeManager()
                    .createShapedRecipe(weapon, weapon.getName(), optionsMetadata);
                if (optionsMetadata.hasOres()) {
                    GameRegistry
                        .addRecipe(new ShapedOreRecipe(new ItemStack(weapon), shape.toArray()).setMirrored(false));
                } else {
                    GameRegistry.addShapedRecipe(new ItemStack(weapon), shape.toArray());
                }
            }

            return weapon;
        }
    }
}
