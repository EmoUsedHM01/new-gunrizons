package com.gtnewhorizon.newgunrizons.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeAttackAspect;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeRenderer;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeState;
import com.gtnewhorizon.newgunrizons.items.instances.ItemGrenadeInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceFactory;
import com.gtnewhorizon.newgunrizons.registry.Sounds;

import lombok.Getter;

public class ItemGrenade extends Item
    implements ItemInstanceFactory<ItemGrenadeInstance>, Updatable {

    public static final int EXPLODE_ON_IMPACT = -1;

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

    private ItemGrenade(Builder builder) {
        this.name = builder.name;
        this.renderer = builder.renderer;
        this.textureNames = new ArrayList<>(builder.textureNames);
        this.explosionTimeout = builder.explosionTimeout;
        this.explosionStrength = builder.explosionStrength;
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

    public ItemGrenadeInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
        ItemGrenadeInstance instance = new ItemGrenadeInstance(slot, player, itemStack);
        instance.setState(GrenadeState.IDLE);
        return instance;
    }

    public void attack(EntityPlayer player, boolean throwingFar) {
        GrenadeAttackAspect.INSTANCE.onAttackButtonClick(player, throwingFar);
    }

    public void attackUp(EntityPlayer player, boolean throwingFar) {
        GrenadeAttackAspect.INSTANCE.onAttackButtonUp(player, throwingFar);
    }

    public void update(EntityPlayer player) {
        GrenadeAttackAspect.INSTANCE.onUpdate(player);
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

        private Supplier<Float> velocity = () -> 1.0F;
        private Supplier<Float> farVelocity = () -> 1.3F;
        private Supplier<Float> gravityVelocity = () -> 0.06F;
        private int maxStackSize = 1;
        private int explosionTimeout = 3000;
        private float explosionStrength = 2.0F;
        protected CreativeTabs tab;
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

        public Builder withMaxStackSize(int maxStackSize) {
            this.maxStackSize = maxStackSize;
            return this;
        }

        public Builder withRenderer(GrenadeRenderer renderer) {
            this.renderer = renderer;
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

        public ItemGrenade build() {
            ItemGrenade grenade = new ItemGrenade(this);
            grenade.setUnlocalizedName(NewGunrizonsMod.MODID + "_" + this.name);
            grenade.setCreativeTab(this.tab);
            grenade.maxStackSize = this.maxStackSize;
            grenade.bounceHardSound = Sounds.resolve(this.bounceHardSound);
            grenade.bounceSoftSound = Sounds.resolve(this.bounceSoftSound);
            grenade.explosionSound = Sounds.resolve(this.explosionSound);
            grenade.safetyPinOffSound = Sounds.resolve(this.safetyPinOffSound);
            grenade.throwSound = Sounds.resolve(this.throwSound);

            NewGunrizonsMod.proxy.registerItem(this.name, grenade, this.renderer);

            return grenade;
        }
    }
}
