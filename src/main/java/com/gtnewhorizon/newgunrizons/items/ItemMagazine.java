package com.gtnewhorizon.newgunrizons.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.newgunrizons.weapon.MagazineState;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstanceFactory;
import com.gtnewhorizon.newgunrizons.weapon.PlayerMagazineInstance;
import com.gtnewhorizon.newgunrizons.weapon.Reloadable;
import net.minecraft.client.model.ModelBase;
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
import com.gtnewhorizon.newgunrizons.util.Updatable;

import lombok.Getter;

public class ItemMagazine extends ItemAttachment
    implements PlayerItemInstanceFactory<PlayerMagazineInstance, MagazineState>, Reloadable, Updatable, Part {

    @Getter
    private final int ammo;
    private List<ItemBullet> compatibleBullets;
    @Getter
    private String reloadSound;
    private ModContext modContext;

    ItemMagazine(String modId, ModelBase model, String textureName, int ammo) {
        this(modId, model, textureName, ammo, null, null);
    }

    ItemMagazine(String modId, ModelBase model, String textureName, int ammo, ItemAttachment.ApplyHandler apply,
        ItemAttachment.ApplyHandler remove) {
        super(modId, AttachmentCategory.MAGAZINE, model, textureName, null, apply, remove);
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

    public void onCreated(ItemStack stack, World p_77622_2_, EntityPlayer p_77622_3_) {
        this.ensureItemStack(stack, 0);
        super.onCreated(stack, p_77622_2_, p_77622_3_);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        this.ensureItemStack(stack, this.ammo);
        super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
    }

    public List<ItemBullet> getCompatibleBullets() {
        return this.compatibleBullets;
    }

    public Part getRenderablePart() {
        return this;
    }

    public PlayerMagazineInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
        PlayerMagazineInstance instance = new PlayerMagazineInstance(slot, player, itemStack);
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
            ItemMagazine magazine = new ItemMagazine(
                this.getModId(),
                this.getModel(),
                this.getTextureName(),
                this.ammo);
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
