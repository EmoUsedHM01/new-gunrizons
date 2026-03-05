package com.gtnewhorizon.newgunrizons.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.util.Pair;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all weapon attachments (scopes, grips, silencers, magazines, bullets).
 * <p>
 * Each attachment belongs to an {@link AttachmentCategory} and can have rendering models,
 * equip/unequip handlers, and nested sub-attachments. Subclasses add specialized behavior:
 * {@link ItemScope} (zoom), {@link ItemMagazine} (ammo capacity), {@link ItemBullet} (type marker).
 */
public class ItemAttachment extends Item {

    // ==================== Identity & classification ====================

    @Getter
    private final AttachmentCategory category;

    @Getter
    @Setter
    private String name;

    // ==================== Rendering ====================

    /** Model+texture pairs rendered for this attachment. */
    @Getter
    private final List<Pair<ModelBase, String>> texturedModels;

    /** Optional renderer executed after the main render pass. */
    @Getter
    @Setter
    private CustomRenderer postRenderer;

    /** Custom render slot for this attachment; null if it shares the weapon's render pass. */
    @Getter
    @Setter
    private Part renderablePart;

    // ==================== Equip/unequip handlers ====================

    /** Called when this attachment is equipped onto a weapon. */
    @Getter
    @Setter
    private AttachmentHandler applyHandler;

    /** Called when this attachment is removed from a weapon. */
    @Getter
    @Setter
    private AttachmentHandler removeHandler;

    // ==================== Item display ====================

    /** Optional HUD crosshair texture path (fully qualified). */
    @Getter
    private final String crosshair;

    /** Optional tooltip provider shown when hovering over this item in inventory. */
    @Getter
    @Setter
    private Function<ItemStack, String> informationProvider;

    // ==================== Nested attachments ====================

    /** Attachments that can be mounted on this attachment (e.g. sights on a rail). */
    private final List<CompatibleAttachment> attachments;

    // ==================== Constructor ====================

    /**
     * @param modId     mod identifier for resource paths
     * @param category  the attachment slot category
     * @param crosshair crosshair texture name (without path prefix), or null
     */
    public ItemAttachment(String modId, AttachmentCategory category, String crosshair) {
        this.texturedModels = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.category = category;
        this.crosshair = crosshair != null ? modId + ":textures/crosshairs/" + crosshair + ".png" : null;
    }

    // ==================== Item overrides ====================

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        stack.setTagCompound(new NBTTagCompound());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean flag) {
        if (tooltip != null && this.informationProvider != null) {
            tooltip.add(this.informationProvider.apply(itemStack));
        }
    }

    @Override
    public String toString() {
        return this.name != null ? "Attachment [" + this.name + "]" : super.toString();
    }

    // ==================== Model management ====================

    public void addModel(ModelBase model, String textureName) {
        this.texturedModels.add(new Pair<>(model, textureName));
    }

    // ==================== Nested attachment management ====================

    public void addCompatibleAttachment(CompatibleAttachment attachment) {
        this.attachments.add(attachment);
    }

    public List<CompatibleAttachment> getAttachments() {
        return Collections.unmodifiableList(this.attachments);
    }

    // ==================== Handler interface ====================

    /** Callback invoked when an attachment is equipped or removed from a weapon. */
    @FunctionalInterface
    public interface AttachmentHandler {

        void apply(ItemAttachment attachment, ItemWeaponInstance weaponInstance);
    }
}
