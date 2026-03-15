package com.gtnewhorizon.newgunrizons.attachment;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;

import lombok.Getter;

/**
 * Describes how a specific {@link ItemAttachment} is compatible with a weapon or grenade.
 * <p>
 * The {@code boneName} specifies which bone in the parent model this attachment
 * renders at. If null, the attachment has no visual representation (e.g. bullets).
 */
@Getter
public class CompatibleAttachment {

	private final ItemAttachment attachment;

	/** Bone in the parent model to render this attachment at (null for non-visual attachments). */
	private final String boneName;

	/** Whether this attachment is pre-equipped on the weapon by default. */
	private final boolean isDefault;

	/** Whether this attachment cannot be removed by the player. */
	private final boolean isPermanent;

	/** Called when this attachment is equipped onto a weapon. */
	private final ItemAttachment.AttachmentHandler applyHandler;

	/** Called when this attachment is removed from a weapon. */
	private final ItemAttachment.AttachmentHandler removeHandler;

	public CompatibleAttachment(ItemAttachment attachment, String boneName,
		boolean isDefault, boolean isPermanent,
		ItemAttachment.AttachmentHandler applyHandler, ItemAttachment.AttachmentHandler removeHandler) {
		this.attachment = attachment;
		this.boneName = boneName;
		this.isDefault = isDefault;
		this.isPermanent = isPermanent;
		this.applyHandler = applyHandler;
		this.removeHandler = removeHandler;
	}

	/** Visual attachment at a bone, non-default. */
	public CompatibleAttachment(ItemAttachment attachment, String boneName) {
		this(attachment, boneName, false, false, null, null);
	}

	/** Visual attachment at a bone, with default flag. */
	public CompatibleAttachment(ItemAttachment attachment, String boneName, boolean isDefault) {
		this(attachment, boneName, isDefault, false, null, null);
	}

	/** Non-visual attachment with equip/unequip handlers. */
	public CompatibleAttachment(ItemAttachment attachment,
		ItemAttachment.AttachmentHandler applyHandler, ItemAttachment.AttachmentHandler removeHandler) {
		this(attachment, null, false, false, applyHandler, removeHandler);
	}
}
