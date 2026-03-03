package com.gtnewhorizon.newgunrizons.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentContainer;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.ItemAttachment;
import com.gtnewhorizon.newgunrizons.attachment.ItemScope;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.crafting.OptionsMetadata;
import com.gtnewhorizon.newgunrizons.entity.EntityShellCasing;
import com.gtnewhorizon.newgunrizons.entity.WeaponSpawnEntity;
import com.gtnewhorizon.newgunrizons.mechanic.WeaponAttachmentAspect;
import com.gtnewhorizon.newgunrizons.util.Updatable;

import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;

public class Weapon extends Item implements PlayerItemInstanceFactory<PlayerWeaponInstance, WeaponState>,
    AttachmentContainer, Reloadable, Updatable {

    private static final Logger logger = LogManager.getLogger(Weapon.class);
    private static final long DEFAULT_RELOADING_TIMEOUT_TICKS = 10L;
    private static final long DEFAULT_UNLOADING_TIMEOUT_TICKS = 10L;
    private static final long DEFAULT_LOAD_ITERATION_TIMEOUT_TICKS = 10L;
    static final long MAX_RELOAD_TIMEOUT_TICKS = 60L;
    static final long MAX_UNLOAD_TIMEOUT_TICKS = 60L;
    public static final float DEFAULT_SHELL_CASING_FORWARD_OFFSET = 0.1F;
    public static final float DEFAULT_SHELL_CASING_VERTICAL_OFFSET = 0.0F;
    public static final float DEFAULT_SHELL_CASING_SIDE_OFFSET = 0.15F;
    public static final float DEFAULT_SHELL_CASING_SIDE_OFFSET_AIMED = 0.05F;
    private static final float DEFAULT_FIRE_RATE = 0.5F;
    private static final float DEFAULT_SILENCED_SHOOT_SOUND_VOLUME = 0.7F;
    private static final float DEFAULT_SHOOT_SOUND_VOLUME = 10.0F;

    public Weapon.Builder builder;
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

    Weapon(Weapon.Builder builder, ModContext modContext) {
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

    public void changeRecoil(EntityLivingBase player, float factor) {
        PlayerWeaponInstance instance = this.modContext.getMainHeldWeapon();
        if (instance != null) {
            float recoil = instance.getWeapon().builder.recoil * factor;
            logger.debug("Changing recoil to {} for instance {}", recoil, instance);
            instance.setRecoil(recoil);
        }

    }

    public Map<ItemAttachment, CompatibleAttachment> getCompatibleAttachments() {
        return this.builder.compatibleAttachments;
    }

    public Collection<CompatibleAttachment> getCompatibleAttachments(AttachmentCategory... categories) {
        Collection<CompatibleAttachment> c = this.builder.compatibleAttachments.values();
        List<AttachmentCategory> inputCategoryList = Arrays.asList(categories);
        return c.stream()
            .filter(
                (e) -> inputCategoryList.contains(
                    e.getAttachment()
                        .getCategory()))
            .collect(Collectors.toList());
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

    public ModelBase getAmmoModel() {
        return this.builder.ammoModel;
    }

    public String getAmmoModelTextureName() {
        return this.builder.ammoModelTextureName;
    }

    public ModelBase getShellCasingModel() {
        return this.builder.shellCasingModel;
    }

    public String getShellCasingTextureName() {
        return this.builder.shellCasingModelTextureName;
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

        for (CompatibleAttachment weaponCompatibleAttachment : ((Weapon) itemStack.getItem()).getCompatibleAttachments()
            .values()) {
            CompatibleAttachment compatibleAttachment = weaponCompatibleAttachment;
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
        Iterator it = this.builder.maxShots.iterator();
        while (it.hasNext() && instance.getMaxShots() != (Integer) it.next()) {

        }

        int result;
        if (it.hasNext()) {
            result = (Integer) it.next();
        } else {
            result = this.builder.maxShots.get(0);
        }

        instance.setMaxShots(result);
        String message;
        if (result == 1) {
            message = StatCollector.translateToLocalFormatted("gui.firearmMode.semi");
        } else if (result == Integer.MAX_VALUE) {
            message = StatCollector.translateToLocalFormatted("gui.firearmMode.auto");
        } else {
            message = StatCollector.translateToLocalFormatted("gui.firearmMode.burst");
        }

        logger.debug("Changed fire mode of {} to {}", instance, result);
        this.modContext.getStatusMessageCenter()
            .addMessage(StatCollector.translateToLocalFormatted("gui.firearmMode", message), 1000L);
        if (this.modContext.getChangeFireModeSound() != null) {
            instance.getPlayer()
                .playSound(this.modContext.getChangeFireModeSound(), 1.0F, 1.0F);
        }
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
            logger.debug("Changed optical zoom to {}", instance.getZoom());
        } else {
            logger.debug("Cannot change non-optical zoom");
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
            logger.debug("Changed optical zoom to {}", zoom);
        } else {
            logger.debug("Cannot change non-optical zoom");
        }

    }

    public ItemAttachment.ApplyHandler2 getEquivalentHandler(AttachmentCategory attachmentCategory) {
        ItemAttachment.ApplyHandler2 handler = (a, i) -> {};
        handler = switch (attachmentCategory) {
            case SCOPE -> (a, i) -> {};
            case GRIP -> (a, i) -> { i.setRecoil(this.builder.recoil); };
            default -> handler;
        };

        return handler;
    }

    public String getTextureName() {
        return this.builder.textureNames.get(0);
    }

    public float getRecoil() {
        return this.builder.recoil;
    }

    public float getShellCasingVerticalOffset() {
        return this.builder.shellCasingVerticalOffset;
    }

    public float getShellCasingForwardOffset() {
        return this.builder.shellCasingForwardOffset;
    }

    public float getShellCasingSideOffset() {
        return this.builder.shellCasingSideOffset;
    }

    public float getShellCasingSideOffsetAimed() {
        return this.builder.shellCasingSideOffsetAimed;
    }

    public boolean isShellCasingEjectEnabled() {
        return this.builder.shellCasingEjectEnabled;
    }

    public Weapon.ShellCasingEjectDirection getShellCasingEjectDirection() {
        return this.builder.shellCasingEjectDirection;
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

    public float getSpawnEntityVelocity() {
        return this.builder.spawnEntitySpeed;
    }

    public float getSpawnEntityGravityVelocity() {
        return this.builder.spawnEntityGravityVelocity;
    }

    public float getSpawnEntityDamage() {
        return this.builder.spawnEntityDamage;
    }

    public float getSpawnEntityExplosionRadius() {
        return this.builder.spawnEntityExplosionRadius;
    }

    public float getInaccuracy() {
        return this.builder.inaccuracy;
    }

    public static class Builder {

        private static final float DEFAULT_SPAWN_ENTITY_SPEED = 10.0F;
        private static final float DEFAULT_INACCURACY = 1.0F;
        private static final String DEFAULT_SHELL_CASING_TEXTURE_NAME = "newgunrizons:/com/gtnewhorizon/newgunrizons/resources/shell.png";
        private static final float DEFAULT_SHELL_CASING_VELOCITY = 0.1F;
        private static final float DEFAULT_SHELL_CASING_GRAVITY_VELOCITY = 0.05F;
        private static final float DEFAULT_SHELL_CASING_INACCURACY = 20.0F;
        String name;
        List<String> textureNames = new ArrayList<>();
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
        public BiFunction<Weapon, EntityLivingBase, ? extends WeaponSpawnEntity> spawnEntityWith;
        public BiFunction<PlayerWeaponInstance, EntityLivingBase, ? extends EntityShellCasing> spawnShellWith;
        private float spawnEntityDamage;
        private float spawnEntityExplosionRadius;
        private float spawnEntityGravityVelocity;
        public long reloadingTimeout = 10L;
        public long loadIterationTimeout = 10L;
        private String modId;
        boolean crosshairFullScreen = false;
        boolean crosshairZoomedFullScreen = false;
        Map<ItemAttachment, CompatibleAttachment> compatibleAttachments = new HashMap<>();
        ModelBase ammoModel;
        String ammoModelTextureName;
        ModelBase shellCasingModel;
        String shellCasingModelTextureName;
        private float spawnEntitySpeed = 10.0F;
        private Class<? extends WeaponSpawnEntity> spawnEntityClass;
        public long pumpTimeoutMilliseconds;
        private float inaccuracy = 1.0F;
        public int pellets = 1;
        public float flashIntensity = 0.4F;
        public Supplier<Float> flashScale = () -> 1.0F;
        public Supplier<Float> flashOffsetX = () -> 0.0F;
        public Supplier<Float> flashOffsetY = () -> 0.0F;
        public Supplier<Float> smokeOffsetX = () -> 0.0F;
        public Supplier<Float> smokeOffsetY = () -> 0.0F;
        long unloadingTimeout = 10L;
        private boolean ejectSpentRoundRequired;
        public int maxBulletsPerReload;
        private Function<ItemStack, List<String>> informationProvider;
        private CraftingComplexity craftingComplexity;
        private Object[] craftingMaterials;
        private float shellCasingForwardOffset = 0.1F;
        private float shellCasingVerticalOffset = 0.0F;
        private float shellCasingSideOffset = 0.15F;
        private float shellCasingSideOffsetAimed = 0.05F;
        public boolean shellCasingEjectEnabled = true;
        private boolean hasIteratedLoad;
        private Weapon.ShellCasingEjectDirection shellCasingEjectDirection;
        private final float silencedShootSoundVolume;
        private final float shootSoundVolume;
        private Object[] craftingRecipe;

        public Builder() {
            this.shellCasingEjectDirection = Weapon.ShellCasingEjectDirection.RIGHT;
            this.silencedShootSoundVolume = 0.7F;
            this.shootSoundVolume = 10.0F;
        }

        public Weapon.Builder withModId(String modId) {
            this.modId = modId;
            return this;
        }

        public Weapon.Builder withEjectRoundRequired() {
            this.ejectSpentRoundRequired = true;
            return this;
        }

        public Weapon.Builder withInformationProvider(Function<ItemStack, List<String>> informationProvider) {
            this.informationProvider = informationProvider;
            return this;
        }

        public Weapon.Builder withReloadingTime(long reloadingTime) {
            this.reloadingTimeout = reloadingTime;
            return this;
        }

        public Weapon.Builder withUnloadingTime(long unloadingTime) {
            this.unloadingTimeout = unloadingTime;
            return this;
        }

        public Weapon.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Weapon.Builder withAmmoCapacity(int ammoCapacity) {
            this.ammoCapacity = ammoCapacity;
            return this;
        }

        public Weapon.Builder withMaxBulletsPerReload(int maxBulletsPerReload) {
            this.maxBulletsPerReload = maxBulletsPerReload;
            return this;
        }

        public Weapon.Builder withIteratedLoad() {
            this.hasIteratedLoad = true;
            return this;
        }

        public Weapon.Builder withRecoil(float recoil) {
            this.recoil = recoil;
            return this;
        }

        /** @deprecated */
        @Deprecated
        public Weapon.Builder withZoom(float zoom) {
            return this;
        }

        public Weapon.Builder withAmmo(ItemAmmo ammo) {
            this.ammo = ammo;
            return this;
        }

        public Weapon.Builder withMaxShots(int... maxShots) {
            for (int m : maxShots) {
                this.maxShots.add(m);
            }
            return this;
        }

        public Weapon.Builder withFireRate(float fireRate) {
            if (!(fireRate >= 1.0F) && !(fireRate <= 0.0F)) {
                this.fireRate = fireRate;
                return this;
            } else {
                throw new IllegalArgumentException("Invalid fire rate " + fireRate);
            }
        }

        public Weapon.Builder withTextureNames(String... textureNames) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                for (String textureName : textureNames) {
                    this.textureNames.add(textureName.toLowerCase() + ".png");
                }
                return this;
            }
        }

        public Weapon.Builder withCrosshair(String crosshair) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.crosshair = this.modId + ":" + "textures/crosshairs/" + crosshair.toLowerCase() + ".png";
                return this;
            }
        }

        public Weapon.Builder withCrosshair(String crosshair, boolean fullScreen) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.crosshair = this.modId + ":" + "textures/crosshairs/" + crosshair.toLowerCase() + ".png";
                this.crosshairFullScreen = fullScreen;
                return this;
            }
        }

        public Weapon.Builder withCrosshairRunning(String crosshairRunning) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.crosshairRunning = this.modId + ":"
                    + "textures/crosshairs/"
                    + crosshairRunning.toLowerCase()
                    + ".png";
                return this;
            }
        }

        public Weapon.Builder withCrosshairZoomed(String crosshairZoomed) {
            return this.withCrosshairZoomed(crosshairZoomed, true);
        }

        public Weapon.Builder withCrosshairZoomed(String crosshairZoomed, boolean fullScreen) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.crosshairZoomed = this.modId + ":"
                    + "textures/crosshairs/"
                    + crosshairZoomed.toLowerCase()
                    + ".png";
                this.crosshairZoomedFullScreen = fullScreen;
                return this;
            }
        }

        public Weapon.Builder withShootSound(String shootSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.shootSound = shootSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withEndOfShootSound(String endOfShootSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.endOfShootSound = endOfShootSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withEjectSpentRoundSound(String ejectSpentRoundSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.ejectSpentRoundSound = ejectSpentRoundSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withSilencedShootSound(String silencedShootSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.silencedShootSound = silencedShootSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withReloadSound(String reloadSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.reloadSound = reloadSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withReloadIterationSound(String reloadIterationSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.reloadIterationSound = reloadIterationSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withAllReloadIterationsCompletedSound(String allReloadIterationCompletedSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.allReloadIterationsCompletedSound = allReloadIterationCompletedSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withUnloadSound(String unloadSound) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                this.unloadSound = unloadSound.toLowerCase();
                return this;
            }
        }

        public Weapon.Builder withCreativeTab(CreativeTabs creativeTab) {
            this.creativeTab = creativeTab;
            return this;
        }

        public Weapon.Builder withSpawnEntityDamage(float spawnEntityDamage) {
            this.spawnEntityDamage = spawnEntityDamage;
            return this;
        }

        public Weapon.Builder withSpawnEntitySpeed(float spawnEntitySpeed) {
            this.spawnEntitySpeed = spawnEntitySpeed;
            return this;
        }

        public Weapon.Builder withSpawnEntityExplosionRadius(float spawnEntityExplosionRadius) {
            this.spawnEntityExplosionRadius = spawnEntityExplosionRadius;
            return this;
        }

        public Weapon.Builder withSpawnEntityGravityVelocity(float spawnEntityGravityVelocity) {
            this.spawnEntityGravityVelocity = spawnEntityGravityVelocity;
            return this;
        }

        public Weapon.Builder withInaccuracy(float inaccuracy) {
            this.inaccuracy = inaccuracy;
            return this;
        }

        public Weapon.Builder withRenderer(WeaponRenderer renderer) {
            this.renderer = renderer;
            return this;
        }

        public Weapon.Builder withCompatibleBullet(ItemBullet bullet, Consumer<ModelBase> positioner) {
            this.compatibleAttachments.put(bullet, new CompatibleAttachment(bullet, positioner));
            return this;
        }

        public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment,
            ItemAttachment.ApplyHandler2 applyHandler, ItemAttachment.ApplyHandler2 removeHandler) {
            this.compatibleAttachments
                .put(attachment, new CompatibleAttachment(attachment, applyHandler, removeHandler));
            return this;
        }

        public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment,
            BiConsumer<EntityLivingBase, ItemStack> positioning, Consumer<ModelBase> modelPositioning) {
            this.compatibleAttachments
                .put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, false));
            return this;
        }

        public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment,
            BiConsumer<EntityLivingBase, ItemStack> positioning) {
            this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, null, false));
            return this;
        }

        public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioner) {
            this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
            return this;
        }

        public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault,
            BiConsumer<EntityLivingBase, ItemStack> positioning, Consumer<ModelBase> modelPositioning) {
            this.compatibleAttachments
                .put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault));
            return this;
        }

        public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault,
            boolean isPermanent, BiConsumer<EntityLivingBase, ItemStack> positioning,
            Consumer<ModelBase> modelPositioning) {
            this.compatibleAttachments.put(
                attachment,
                new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault, isPermanent));
            return this;
        }

        public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault,
            Consumer<ModelBase> positioner) {
            this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner, isDefault));
            return this;
        }

        public Weapon.Builder withSpawnEntityModel(ModelBase ammoModel) {
            this.ammoModel = ammoModel;
            return this;
        }

        public Weapon.Builder withSpawnEntityModelTexture(String ammoModelTextureName) {
            this.ammoModelTextureName = this.modId + ":"
                + "textures/models/"
                + ammoModelTextureName.toLowerCase()
                + ".png";
            return this;
        }

        public Weapon.Builder withShellCasingEjectEnabled(boolean shellCasingEjectEnabled) {
            this.shellCasingEjectEnabled = shellCasingEjectEnabled;
            return this;
        }

        public Weapon.Builder withShellCasingModel(ModelBase shellCasingModel) {
            this.shellCasingModel = shellCasingModel;
            return this;
        }

        public Weapon.Builder withShellCasingModelTexture(String shellModelTextureName) {
            this.shellCasingModelTextureName = this.modId + ":"
                + "textures/models/"
                + shellModelTextureName.toLowerCase()
                + ".png";
            return this;
        }

        public Weapon.Builder withShellCasingForwardOffset(float shellCasingForwardOffset) {
            this.shellCasingForwardOffset = shellCasingForwardOffset;
            return this;
        }

        public Weapon.Builder withShellCasingVerticalOffset(float shellCasingVerticalOffset) {
            this.shellCasingVerticalOffset = shellCasingVerticalOffset;
            return this;
        }

        public Weapon.Builder withShellCasingSideOffset(float shellCasingSideOffset) {
            this.shellCasingSideOffset = shellCasingSideOffset;
            return this;
        }

        public Weapon.Builder withShellCasingSideOffsetAimed(float shellCasingSideOffsetAimed) {
            this.shellCasingSideOffsetAimed = shellCasingSideOffsetAimed;
            return this;
        }

        public Weapon.Builder withShellCasingEjectDirection(
            Weapon.ShellCasingEjectDirection shellCasingEjectDirection) {
            this.shellCasingEjectDirection = shellCasingEjectDirection;
            return this;
        }

        public Weapon.Builder withPumpTimeout(long pumpTimeoutMilliseconds) {
            this.pumpTimeoutMilliseconds = pumpTimeoutMilliseconds;
            return this;
        }

        public Weapon.Builder withPellets(int pellets) {
            if (pellets < 1) {
                throw new IllegalArgumentException("Pellet count must be >= 1");
            } else {
                this.pellets = pellets;
                return this;
            }
        }

        public Weapon.Builder withFlashIntensity(float flashIntensity) {
            if (!(flashIntensity < 0.0F) && !(flashIntensity > 1.0F)) {
                this.flashIntensity = flashIntensity;
                return this;
            } else {
                throw new IllegalArgumentException("Invalid flash intencity");
            }
        }

        public Weapon.Builder withFlashScale(Supplier<Float> flashScale) {
            this.flashScale = flashScale;
            return this;
        }

        public Weapon.Builder withFlashOffsetX(Supplier<Float> flashOffsetX) {
            this.flashOffsetX = flashOffsetX;
            return this;
        }

        public Weapon.Builder withFlashOffsetY(Supplier<Float> flashOffsetY) {
            this.flashOffsetY = flashOffsetY;
            return this;
        }

        public Weapon.Builder withSmokeOffsetX(Supplier<Float> smokeOffsetX) {
            this.smokeOffsetX = smokeOffsetX;
            return this;
        }

        public Weapon.Builder withSmokeOffsetY(Supplier<Float> smokeOffsetY) {
            this.smokeOffsetY = smokeOffsetY;
            return this;
        }

        public Weapon.Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
            if (craftingComplexity == null) {
                throw new IllegalArgumentException("Crafting complexity not set");
            } else if (craftingMaterials.length < 2) {
                throw new IllegalArgumentException("2 or more materials required for crafting");
            } else {
                this.craftingComplexity = craftingComplexity;
                this.craftingMaterials = craftingMaterials;
                return this;
            }
        }

        public Weapon.Builder withCraftingRecipe(Object... craftingRecipe) {
            this.craftingRecipe = craftingRecipe;
            return this;
        }

        public Weapon build(ModContext modContext) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else if (this.name == null) {
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
                    this.spawnEntityClass = WeaponSpawnEntity.class;
                }

                if (this.spawnEntityWith == null) {
                    this.spawnEntityWith = (weaponx, player) -> {
                        WeaponSpawnEntity bullet = new WeaponSpawnEntity(
                            weaponx,
                            player.worldObj,
                            player,
                            this.spawnEntitySpeed,
                            this.spawnEntityGravityVelocity,
                            this.inaccuracy,
                            this.spawnEntityDamage,
                            this.spawnEntityExplosionRadius);
                        bullet.setPositionAndDirection();
                        return bullet;
                    };
                }

                if (this.shellCasingModel == null) {
                    this.shellCasingModel = new Shell();
                }

                if (this.shellCasingModelTextureName == null) {
                    this.shellCasingModelTextureName = "newgunrizons:/com/gtnewhorizon/newgunrizons/resources/shell.png";
                }

                if (this.spawnShellWith == null) {
                    this.spawnShellWith = (weaponInstance, player) -> {
                        EntityShellCasing shell = new EntityShellCasing(
                            weaponInstance,
                            player.worldObj,
                            player,
                            0.1F,
                            0.05F,
                            20.0F);
                        shell.setPositionAndDirection();
                        return shell;
                    };
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

                Weapon weapon = new Weapon(this, modContext);
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
                List shape;
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
                } else {
                    System.err.println("!!!No recipe defined for weapon " + this.name);
                }

                return weapon;
            }
        }
    }

    public enum ShellCasingEjectDirection {
        LEFT,
        RIGHT
    }
}
