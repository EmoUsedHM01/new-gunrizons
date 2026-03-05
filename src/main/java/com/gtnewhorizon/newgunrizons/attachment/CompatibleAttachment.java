package com.gtnewhorizon.newgunrizons.attachment;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;

import lombok.Getter;

/**
 * Describes how a specific {@link ItemAttachment} is compatible with a weapon or other attachment.
 * <p>
 * Instances are created in two distinct patterns:
 * <ul>
 * <li><b>Rendering mode</b> — defines how the attachment is visually positioned on the weapon,
 * via {@link #modelPositioning} and/or {@link #positioning} callbacks, plus optional
 * {@link #isDefault} and {@link #isPermanent} flags.</li>
 * <li><b>Behavior mode</b> — defines logic to run when the attachment is equipped or unequipped,
 * via {@link #applyHandler} and {@link #removeHandler}.</li>
 * </ul>
 */
@Getter
public class CompatibleAttachment {

    // --- Core reference ---

    private final ItemAttachment attachment;

    // --- Rendering positioning ---

    /** GL transform callback applied to the attachment's model when rendering. */
    private final Consumer<ModelBase> modelPositioning;

    /** GL transform callback applied to the entity holding the attachment. */
    private final BiConsumer<EntityLivingBase, ItemStack> positioning;

    // --- Attachment slot flags ---

    /** Whether this attachment is pre-equipped on the weapon by default. */
    private final boolean isDefault;

    /** Whether this attachment cannot be removed by the player. */
    private final boolean isPermanent;

    // --- Equip/unequip behavior ---

    /** Called when this attachment is equipped onto a weapon. */
    private final ItemAttachment.AttachmentHandler applyHandler;

    /** Called when this attachment is removed from a weapon. */
    private final ItemAttachment.AttachmentHandler removeHandler;

    /**
     * Full rendering-mode constructor.
     *
     * @param attachment       the attachment item
     * @param positioning      entity-level positioning callback (may be null)
     * @param modelPositioning model-level positioning callback (may be null)
     * @param isDefault        true if pre-equipped on the weapon
     * @param isPermanent      true if the player cannot remove this attachment
     */
    public CompatibleAttachment(ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning,
        Consumer<ModelBase> modelPositioning, boolean isDefault, boolean isPermanent) {
        this.attachment = attachment;
        this.positioning = positioning;
        this.modelPositioning = modelPositioning;
        this.isDefault = isDefault;
        this.isPermanent = isPermanent;
        this.applyHandler = null;
        this.removeHandler = null;
    }

    /** Rendering mode with {@code isPermanent = false}. */
    public CompatibleAttachment(ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning,
        Consumer<ModelBase> modelPositioning, boolean isDefault) {
        this(attachment, positioning, modelPositioning, isDefault, false);
    }

    /** Behavior-only mode — no rendering positioning, only equip/unequip handlers. */
    public CompatibleAttachment(ItemAttachment attachment, ItemAttachment.AttachmentHandler applyHandler,
        ItemAttachment.AttachmentHandler removeHandler) {
        this.attachment = attachment;
        this.positioning = null;
        this.modelPositioning = null;
        this.isDefault = false;
        this.isPermanent = false;
        this.applyHandler = applyHandler;
        this.removeHandler = removeHandler;
    }

    /** Model positioning only, non-default. */
    public CompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> modelPositioning) {
        this(attachment, null, modelPositioning, false);
    }

    /** Model positioning with default flag. */
    public CompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> modelPositioning, boolean isDefault) {
        this(attachment, null, modelPositioning, isDefault);
    }
}
