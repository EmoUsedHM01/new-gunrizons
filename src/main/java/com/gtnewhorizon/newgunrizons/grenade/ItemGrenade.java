package com.gtnewhorizon.newgunrizons.grenade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentContainer;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.crafting.OptionsMetadata;
import com.gtnewhorizon.newgunrizons.util.Updatable;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstanceFactory;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemGrenade extends Item
    implements PlayerItemInstanceFactory<PlayerGrenadeInstance, GrenadeState>, AttachmentContainer, Updatable {

    public static final int DEFAULT_FUSE_TIMEOUT = 3000;
    public static final float DEFAULT_EXPLOSION_STRENTH = 2.0F;
    public static final int EXPLODE_ON_IMPACT = -1;
    public static final float DEFAULT_GRAVITY_VELOCITY = 0.06F;
    public static final float DEFAULT_FAR_VELOCITY = 1.3F;
    public static final float DEFAULT_VELOCITY = 1.0F;
    public static final float DEFAULT_ROTATION_SLOWDOWN_FACTOR = 0.99F;
    public static final float DEFAULT_EFFECTIVE_RADIUS = 20.0F;
    public static final float DEFAULT_FRAGMENT_DAMAGE = 15.0F;
    public static final int DEFAULT_FRAGMENT_COUNT = 100;
    ItemGrenade.Builder builder;
    private final ModContext modContext;
    private String bounceHardSound;
    private String bounceSoftSound;
    private String explosionSound;
    private String safetyPinOffSound;
    private String throwSound;

    public ItemGrenade(ItemGrenade.Builder builder, ModContext modContext) {
        this.builder = builder;
        this.modContext = modContext;
        this.maxStackSize = 16;
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        stack.setTagCompound(new NBTTagCompound());
    }

    public GrenadeRenderer getRenderer() {
        return this.builder.renderer;
    }

    public String getTextureName() {
        return this.builder.textureNames.get(0);
    }

    public boolean hasSafetyPin() {
        return this.builder.explosionTimeout > 0;
    }

    public List<CompatibleAttachment> getActiveAttachments(EntityLivingBase player, ItemStack itemStack) {
        return new ArrayList<>(this.builder.compatibleAttachments.values());
    }

    Map<ItemAttachment, CompatibleAttachment> getCompatibleAttachments() {
        return this.builder.compatibleAttachments;
    }

    public String getName() {
        return this.builder.name;
    }

    public PlayerGrenadeInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
        PlayerGrenadeInstance instance = new PlayerGrenadeInstance(slot, player, itemStack);
        instance.setState(GrenadeState.READY);
        return instance;
    }

    public void attack(EntityPlayer player, boolean throwingFar) {
        this.modContext.getGrenadeAttackAspect()
            .onAttackButtonClick(player, throwingFar);
    }

    public void attackUp(EntityPlayer player, boolean throwingFar) {
        this.modContext.getGrenadeAttackAspect()
            .onAttackButtonUp(player, throwingFar);
    }

    public void update(EntityPlayer player) {
        this.modContext.getGrenadeAttackAspect()
            .onUpdate(player);
    }

    public float getExplosionStrength() {
        return this.builder.explosionStrength;
    }

    public int getExplosionTimeout() {
        return this.builder.explosionTimeout;
    }

    public long getReequipTimeout() {
        return 800L;
    }

    public double getTotalThrowingDuration() {
        return (double) this.builder.renderer.getTotalThrowingDuration();
    }

    public float getVelocity() {
        return this.builder.velocity.get();
    }

    public float getFarVelocity() {
        return this.builder.farVelocity.get();
    }

    public float getGravityVelocity() {
        return this.builder.gravityVelocity.get();
    }

    public float getRotationSlowdownFactor() {
        return this.builder.rotationSlowdownFactor.get();
    }

    public String getBounceHardSound() {
        return this.bounceHardSound;
    }

    public String getBounceSoftSound() {
        return this.bounceSoftSound;
    }

    public String getExplosionSound() {
        return this.explosionSound;
    }

    public String getSafetyPinOffSound() {
        return this.safetyPinOffSound;
    }

    public String getThrowSound() {
        return this.throwSound;
    }

    public float getEffectiveRadius() {
        return this.builder.effectiveRadius;
    }

    public float getFragmentDamage() {
        return this.builder.fragmentDamage;
    }

    public int getFragmentCount() {
        return this.builder.fragmentCount;
    }

    public long getActiveDuration() {
        return this.builder.activeDuration;
    }

    public Collection<CompatibleAttachment> getCompatibleAttachments(AttachmentCategory... categories) {
        Collection<CompatibleAttachment> c = this.builder.compatibleAttachments.values();
        List<AttachmentCategory> inputCategoryList = Arrays.asList(categories);
        return c.stream()
            .filter(inputCategoryList::contains)
            .collect(Collectors.toList());
    }

    public static class Builder {

        protected String name;
        protected String modId;
        protected ModelBase model;
        protected String textureName;
        protected Consumer<ItemStack> entityPositioning;
        protected Consumer<ItemStack> inventoryPositioning;
        protected BiConsumer<EntityLivingBase, ItemStack> thirdPersonPositioning;
        protected BiConsumer<EntityLivingBase, ItemStack> firstPersonPositioning;
        protected BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning;
        protected BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning;
        protected BiConsumer<ModelBase, ItemStack> inventoryModelPositioning;
        protected BiConsumer<ModelBase, ItemStack> entityModelPositioning;
        protected Consumer<RenderContext> firstPersonLeftHandPositioning;
        protected Consumer<RenderContext> firstPersonRightHandPositioning;
        protected Map<ItemAttachment, CompatibleAttachment> compatibleAttachments = new HashMap<>();
        private Supplier<Float> velocity = () -> 1.0F;
        private Supplier<Float> farVelocity = () -> 1.3F;
        private Supplier<Float> gravityVelocity = () -> 0.06F;
        private int maxStackSize = 1;
        private int explosionTimeout = 3000;
        private float explosionStrength = 2.0F;
        protected CreativeTabs tab;
        private CraftingComplexity craftingComplexity;
        private Object[] craftingMaterials;
        private int craftingCount = 1;
        private GrenadeRenderer renderer;
        List<String> textureNames = new ArrayList<>();
        private Supplier<Float> rotationSlowdownFactor = () -> 0.99F;
        private String bounceHardSound;
        private String bounceSoftSound;
        private String explosionSound;
        private String safetyPinOffSound;
        private String throwSound;
        private float effectiveRadius = 20.0F;
        private float fragmentDamage = 15.0F;
        private int fragmentCount = 100;
        private long activeDuration;
        private Object[] craftingRecipe;

        public Builder() {}

        public ItemGrenade.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemGrenade.Builder withCreativeTab(CreativeTabs tab) {
            this.tab = tab;
            return this;
        }

        public ItemGrenade.Builder withModId(String modId) {
            this.modId = modId;
            return this;
        }

        public ItemGrenade.Builder withModel(ModelBase model) {
            this.model = model;
            return this;
        }

        public ItemGrenade.Builder withVelocity(Supplier<Float> velocity) {
            this.velocity = velocity;
            return this;
        }

        public ItemGrenade.Builder withFarVelocity(Supplier<Float> farVelocity) {
            this.farVelocity = farVelocity;
            return this;
        }

        public ItemGrenade.Builder withGravityVelocity(Supplier<Float> gravityVelocity) {
            this.gravityVelocity = gravityVelocity;
            return this;
        }

        public ItemGrenade.Builder withRotationSlowdownFactor(Supplier<Float> rotationSlowdownFactor) {
            this.rotationSlowdownFactor = rotationSlowdownFactor;
            return this;
        }

        public ItemGrenade.Builder withExplosionStrength(float explosionStrength) {
            this.explosionStrength = explosionStrength;
            return this;
        }

        public ItemGrenade.Builder withExplosionTimeout(int explosionTimeout) {
            this.explosionTimeout = explosionTimeout;
            return this;
        }

        public ItemGrenade.Builder withExplosionOnImpact() {
            this.explosionTimeout = -1;
            return this;
        }

        public ItemGrenade.Builder withTextureNames(String... textureNames) {
            if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                for (String textureName : textureNames) {
                    this.textureNames.add(textureName.toLowerCase() + ".png");
                }
                return this;
            }
        }

        public ItemGrenade.Builder withCompatibleAttachment(ItemAttachment attachment,
            BiConsumer<EntityLivingBase, ItemStack> positioning) {
            this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, null, true));
            return this;
        }

        public ItemGrenade.Builder withMaxStackSize(int maxStackSize) {
            this.maxStackSize = maxStackSize;
            return this;
        }

        public ItemGrenade.Builder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
            this.entityPositioning = entityPositioning;
            return this;
        }

        public ItemGrenade.Builder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
            this.inventoryPositioning = inventoryPositioning;
            return this;
        }

        public ItemGrenade.Builder withThirdPersonPositioning(
            BiConsumer<EntityLivingBase, ItemStack> thirdPersonPositioning) {
            this.thirdPersonPositioning = thirdPersonPositioning;
            return this;
        }

        public ItemGrenade.Builder withFirstPersonPositioning(
            BiConsumer<EntityLivingBase, ItemStack> firstPersonPositioning) {
            this.firstPersonPositioning = firstPersonPositioning;
            return this;
        }

        public ItemGrenade.Builder withFirstPersonModelPositioning(
            BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning) {
            this.firstPersonModelPositioning = firstPersonModelPositioning;
            return this;
        }

        public ItemGrenade.Builder withEntityModelPositioning(BiConsumer<ModelBase, ItemStack> entityModelPositioning) {
            this.entityModelPositioning = entityModelPositioning;
            return this;
        }

        public ItemGrenade.Builder withInventoryModelPositioning(
            BiConsumer<ModelBase, ItemStack> inventoryModelPositioning) {
            this.inventoryModelPositioning = inventoryModelPositioning;
            return this;
        }

        public ItemGrenade.Builder withThirdPersonModelPositioning(
            BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning) {
            this.thirdPersonModelPositioning = thirdPersonModelPositioning;
            return this;
        }

        public ItemGrenade.Builder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioning = leftHand;
            this.firstPersonRightHandPositioning = rightHand;
            return this;
        }

        public ItemGrenade.Builder withRenderer(GrenadeRenderer renderer) {
            this.renderer = renderer;
            return this;
        }

        public ItemGrenade.Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
            return this.withCrafting(1, craftingComplexity, craftingMaterials);
        }

        public ItemGrenade.Builder withCrafting(int craftingCount, CraftingComplexity craftingComplexity,
            Object... craftingMaterials) {
            if (craftingComplexity == null) {
                throw new IllegalArgumentException("Crafting complexity not set");
            } else if (craftingMaterials.length < 2) {
                throw new IllegalArgumentException("2 or more materials required for crafting");
            } else if (craftingCount == 0) {
                throw new IllegalArgumentException("Invalid item count");
            } else {
                this.craftingComplexity = craftingComplexity;
                this.craftingMaterials = craftingMaterials;
                this.craftingCount = craftingCount;
                return this;
            }
        }

        public ItemGrenade.Builder withCraftingRecipe(Object... craftingRecipe) {
            this.craftingRecipe = craftingRecipe;
            return this;
        }

        public ItemGrenade.Builder withBounceHardSound(String sound) {
            this.bounceHardSound = sound != null ? sound.toLowerCase() : null;
            return this;
        }

        public ItemGrenade.Builder withBounceSoftSound(String sound) {
            this.bounceSoftSound = sound != null ? sound.toLowerCase() : null;
            return this;
        }

        public ItemGrenade.Builder withExplosionSound(String sound) {
            this.explosionSound = sound != null ? sound.toLowerCase() : null;
            return this;
        }

        public ItemGrenade.Builder withSafetyPinOffSound(String sound) {
            this.safetyPinOffSound = sound != null ? sound.toLowerCase() : null;
            return this;
        }

        public ItemGrenade.Builder withThrowSound(String sound) {
            this.throwSound = sound != null ? sound.toLowerCase() : null;
            return this;
        }

        public ItemGrenade.Builder withEffectiveRadius(float effectiveRadius) {
            this.effectiveRadius = effectiveRadius;
            return this;
        }

        public ItemGrenade.Builder withFragmentDamage(float fragmentDamage) {
            this.fragmentDamage = fragmentDamage;
            return this;
        }

        public ItemGrenade.Builder withFragmentCount(int fragmentCount) {
            this.fragmentCount = fragmentCount;
            return this;
        }

        public ItemGrenade.Builder withActiveDuration(long duration) {
            this.activeDuration = duration;
            return this;
        }

        public ItemGrenade build(ModContext modContext) {
            ItemGrenade grenade = new ItemGrenade(this, modContext);
            grenade.setUnlocalizedName(this.modId + "_" + this.name);
            grenade.setCreativeTab(this.tab);
            grenade.maxStackSize = this.maxStackSize;
            if (this.bounceHardSound != null) {
                grenade.bounceHardSound = modContext.registerSound(this.bounceHardSound);
            }

            if (this.bounceSoftSound != null) {
                grenade.bounceSoftSound = modContext.registerSound(this.bounceSoftSound);
            }

            if (this.explosionSound != null) {
                grenade.explosionSound = modContext.registerSound(this.explosionSound);
            }

            if (this.safetyPinOffSound != null) {
                grenade.safetyPinOffSound = modContext.registerSound(this.safetyPinOffSound);
            }

            if (this.throwSound != null) {
                grenade.throwSound = modContext.registerSound(this.throwSound);
            }

            modContext.registerGrenadeWeapon(this.name, grenade, this.renderer);
            List shape;
            if (this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
                ItemStack itemStack = new ItemStack(grenade);
                shape = modContext.getRecipeManager()
                    .registerShapedRecipe(grenade, this.craftingRecipe);
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
                    .createShapedRecipe(grenade, this.name, optionsMetadata);
                ItemStack itemStack = new ItemStack(grenade);
                itemStack.stackSize = this.craftingCount;
                if (optionsMetadata.hasOres()) {
                    GameRegistry.addRecipe(new ShapedOreRecipe(itemStack, shape.toArray()).setMirrored(false));
                } else {
                    GameRegistry.addShapedRecipe(itemStack, shape.toArray());
                }
            } else {
                System.err.println("!!!No recipe defined for grenade " + this.name);
            }

            return grenade;
        }
    }
}
