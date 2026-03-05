package com.gtnewhorizon.newgunrizons.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceFactory;
import com.gtnewhorizon.newgunrizons.items.instances.ItemMagazineInstance;
import com.gtnewhorizon.newgunrizons.util.Updatable;
import com.gtnewhorizon.newgunrizons.weapon.MagazineState;
import com.gtnewhorizon.newgunrizons.weapon.Reloadable;

import lombok.Getter;

/**
 * A magazine attachment that holds ammunition.
 * <p>
 * Magazines track their current ammo count via NBT and support reloading with
 * compatible {@link ItemBullet} types.
 */
public class ItemMagazine extends ItemAttachment
    implements ItemInstanceFactory<ItemMagazineInstance, MagazineState>, Reloadable, Updatable, Part {

    @Getter
    private final int ammo;
    List<ItemBullet> compatibleBullets;
    @Getter
    String reloadSound;
    ModContext modContext;

    ItemMagazine(String modId, int ammo) {
        super(modId, AttachmentCategory.MAGAZINE, null);
        this.ammo = ammo;
        this.setMaxStackSize(1);
    }

    public ItemStack createItemStack() {
        ItemStack attachmentItemStack = new ItemStack(this);
        this.ensureItemStack(attachmentItemStack, this.ammo);
        return attachmentItemStack;
    }

    private void ensureItemStack(ItemStack itemStack, int initialAmmo) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
            Tags.setAmmo(itemStack, initialAmmo);
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        this.ensureItemStack(stack, 0);
        super.onCreated(stack, world, player);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        this.ensureItemStack(stack, this.ammo);
        super.onUpdate(stack, world, entity, slot, isSelected);
    }

    public List<ItemBullet> getCompatibleBullets() {
        return this.compatibleBullets;
    }

    @Override
    public Part getRenderablePart() {
        return this;
    }

    public ItemMagazineInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
        ItemMagazineInstance instance = new ItemMagazineInstance(slot, player, itemStack);
        instance.setState(MagazineState.READY);
        return instance;
    }

    public void update(EntityPlayer player) {
        this.modContext.getMagazineReloadAspect()
            .updateMainHeldItem(player);
    }

    public void reloadMainHeldItemForPlayer(EntityPlayer player) {
        this.modContext.getMagazineReloadAspect()
            .reloadMainHeldItem(player);
    }

    public static final class Builder extends AttachmentBuilder {

        private int ammo;
        private final Set<ItemBullet> compatibleBullets = new HashSet<>();
        private String reloadSound;

        public ItemMagazine.Builder withAmmo(int ammo) {
            this.ammo = ammo;
            return this;
        }

        public ItemMagazine.Builder withReloadSound(String reloadSound) {
            this.reloadSound = reloadSound;
            return this;
        }

        public ItemMagazine.Builder withCompatibleBullet(ItemBullet compatibleBullet) {
            this.compatibleBullets.add(compatibleBullet);
            return this;
        }

        public ItemAttachment createAttachment(ModContext modContext) {
            ItemMagazine magazine = new ItemMagazine(this.getModId(), this.ammo);
            magazine.compatibleBullets = new ArrayList<>(this.compatibleBullets);
            if (this.reloadSound != null) {
                magazine.reloadSound = modContext.registerSound(this.reloadSound);
            }

            magazine.modContext = modContext;
            this.withInformationProvider((stack) -> "Ammo: " + Tags.getAmmo(stack) + "/" + this.ammo);
            return magazine;
        }
    }
}
