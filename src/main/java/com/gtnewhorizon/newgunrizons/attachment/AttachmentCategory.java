package com.gtnewhorizon.newgunrizons.attachment;

/**
 * Categories for weapon attachment slots.
 * <p>
 * Standard categories ({@link #SCOPE}, {@link #GRIP}, {@link #SILENCER},
 * represent primary attachment types that the player can equip/swap. {@code EXTRA} through {@code EXTRA7}
 * are auxiliary slots used for weapon sub-parts such as bolt actions, carry handles, bipods, rails, and
 * other cosmetic components.
 */
public enum AttachmentCategory {

    SCOPE,
    GRIP,
    SILENCER,
    EXTRA;

    /** Cached values array to avoid allocating a new array on every call to {@link #values()}. */
    public static final AttachmentCategory[] VALUES = values();
}
