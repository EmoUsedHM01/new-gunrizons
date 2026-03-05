package com.gtnewhorizon.newgunrizons.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import lombok.Getter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentContainer;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.crafting.OptionsMetadata;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeState;
import com.gtnewhorizon.newgunrizons.items.instances.ItemGrenadeInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceFactory;
import com.gtnewhorizon.newgunrizons.registry.Sounds;
import com.gtnewhorizon.newgunrizons.util.Updatable;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemGrenade extends Item
    implements ItemInstanceFactory<ItemGrenadeInstance, GrenadeState>, AttachmentContainer, Updatable {

    public static final int EXPLODE_ON_IMPACT = -1;

    private final ModContext modContext;
    @Getter
    private String bounceHardSound;
    @Getter
    private String bounceSoftSound;
    @Getter
    private String explosionSound;
    @Getter
    private String safetyPinOffSound;
    @Getter
    private String throwSound;

    @Getter
    private final String name;
    @Getter
    private final GrenadeRenderer renderer;
    private final List<String> textureNames;
    @Getter
    private final int explosionTimeout;
    @Getter
    private final float explosionStrength;
    @Getter
    private final Map<ItemAttachment, CompatibleAttachment> compatibleAttachments;
    private final Supplier<Float> velocity;
    private final Supplier<Float> farVelocity;
    private final Supplier<Float> gravityVelocity;
    private final Supplier<Float> rotationSlowdownFactor;
    @Getter
    private final float effectiveRadius;
    @Getter
    private final float fragmentDamage;
    @Getter
    private final int fragmentCount;

    private ItemGrenade(Builder builder, ModContext modContext) {
        this.modContext = modContext;
        this.name = builder.name;
        this.renderer = builder.renderer;
        this.textureNames = new ArrayList<>(builder.textureNames);
        this.explosionTimeout = builder.explosionTimeout;
        this.explosionStrength = builder.explosionStrength;
        this.compatibleAttachments = builder.compatibleAttachments;
        this.velocity = builder.velocity;
        this.farVelocity = builder.farVelocity;
        this.gravityVelocity = builder.gravityVelocity;
        this.rotationSlowdownFactor = builder.rotationSlowdownFactor;
        this.effectiveRadius = builder.effectiveRadius;
        this.fragmentDamage = builder.fragmentDamage;
        this.fragmentCount = builder.fragmentCount;
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        stack.setTagCompound(new NBTTagCompound());
    }

    public String getTextureName() {
        return this.textureNames.get(0);
    }

    public boolean hasSafetyPin() {
        return this.explosionTimeout > 0;
    }

    public List<CompatibleAttachment> getActiveAttachments(EntityLivingBase player, ItemStack itemStack) {
        return new ArrayList<>(this.compatibleAttachments.values());
    }

    public ItemGrenadeInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
        ItemGrenadeInstance instance = new ItemGrenadeInstance(slot, player, itemStack);
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

    public long getReequipTimeout() {
        return 800L;
    }

    public long getTotalThrowingDuration() {
        return this.renderer.getTotalThrowingDuration();
    }

    public float getVelocity() {
        return this.velocity.get();
    }

    public float getFarVelocity() {
        return this.farVelocity.get();
    }

    public float getGravityVelocity() {
        return this.gravityVelocity.get();
    }

    public float getRotationSlowdownFactor() {
        return this.rotationSlowdownFactor.get();
    }

    public static class Builder {

        public String name;

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
        private Object[] craftingRecipe;

        public Builder() {}

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCreativeTab(CreativeTabs tab) {
            this.tab = tab;
            return this;
        }

        public Builder withVelocity(Supplier<Float> velocity) {
            this.velocity = velocity;
            return this;
        }

        public Builder withFarVelocity(Supplier<Float> farVelocity) {
            this.farVelocity = farVelocity;
            return this;
        }

        public Builder withGravityVelocity(Supplier<Float> gravityVelocity) {
            this.gravityVelocity = gravityVelocity;
            return this;
        }

        public Builder withRotationSlowdownFactor(Supplier<Float> rotationSlowdownFactor) {
            this.rotationSlowdownFactor = rotationSlowdownFactor;
            return this;
        }

        public Builder withExplosionStrength(float explosionStrength) {
            this.explosionStrength = explosionStrength;
            return this;
        }

        public Builder withExplosionTimeout(int explosionTimeout) {
            this.explosionTimeout = explosionTimeout;
            return this;
        }

        public Builder withExplosionOnImpact() {
            this.explosionTimeout = EXPLODE_ON_IMPACT;
            return this;
        }

        public Builder withTextureNames(String... textureNames) {
            for (String textureName : textureNames) {
                this.textureNames.add(textureName.toLowerCase() + ".png");
            }
            return this;
        }

        public Builder withCompatibleAttachment(ItemAttachment attachment,
            BiConsumer<EntityLivingBase, ItemStack> positioning) {
            this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, null, true));
            return this;
        }

        public Builder withMaxStackSize(int maxStackSize) {
            this.maxStackSize = maxStackSize;
            return this;
        }

        public Builder withRenderer(GrenadeRenderer renderer) {
            this.renderer = renderer;
            return this;
        }

        public Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
            return this.withCrafting(1, craftingComplexity, craftingMaterials);
        }

        public Builder withCrafting(int craftingCount, CraftingComplexity craftingComplexity,
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

        public Builder withCraftingRecipe(Object... craftingRecipe) {
            this.craftingRecipe = craftingRecipe;
            return this;
        }

        public Builder withBounceHardSound(String sound) {
            this.bounceHardSound = sound;
            return this;
        }

        public Builder withBounceSoftSound(String sound) {
            this.bounceSoftSound = sound;
            return this;
        }

        public Builder withExplosionSound(String sound) {
            this.explosionSound = sound;
            return this;
        }

        public Builder withSafetyPinOffSound(String sound) {
            this.safetyPinOffSound = sound;
            return this;
        }

        public Builder withThrowSound(String sound) {
            this.throwSound = sound;
            return this;
        }

        public Builder withEffectiveRadius(float effectiveRadius) {
            this.effectiveRadius = effectiveRadius;
            return this;
        }

        public Builder withFragmentDamage(float fragmentDamage) {
            this.fragmentDamage = fragmentDamage;
            return this;
        }

        public Builder withFragmentCount(int fragmentCount) {
            this.fragmentCount = fragmentCount;
            return this;
        }

        public ItemGrenade build(ModContext modContext) {
            ItemGrenade grenade = new ItemGrenade(this, modContext);
            grenade.setUnlocalizedName(NewGunrizonsMod.MODID + "_" + this.name);
            grenade.setCreativeTab(this.tab);
            grenade.maxStackSize = this.maxStackSize;
            grenade.bounceHardSound = Sounds.resolve(this.bounceHardSound);
            grenade.bounceSoftSound = Sounds.resolve(this.bounceSoftSound);
            grenade.explosionSound = Sounds.resolve(this.explosionSound);
            grenade.safetyPinOffSound = Sounds.resolve(this.safetyPinOffSound);
            grenade.throwSound = Sounds.resolve(this.throwSound);

            modContext.registerGrenadeWeapon(this.name, grenade, this.renderer);
            List<Object> shape;
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
            }

            return grenade;
        }
    }
}
