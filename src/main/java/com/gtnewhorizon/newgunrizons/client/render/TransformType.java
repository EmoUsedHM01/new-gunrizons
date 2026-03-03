package com.gtnewhorizon.newgunrizons.client.render;

import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public enum TransformType {

    NONE,
    THIRD_PERSON_LEFT_HAND,
    THIRD_PERSON_RIGHT_HAND,
    FIRST_PERSON_LEFT_HAND,
    FIRST_PERSON_RIGHT_HAND,
    HEAD,
    GUI,
    GROUND,
    FIXED;

    public static TransformType fromItemRenderType(ItemRenderType itemRenderType) {
        return switch (itemRenderType) {
            case ENTITY -> GROUND;
            case EQUIPPED -> THIRD_PERSON_RIGHT_HAND;
            case EQUIPPED_FIRST_PERSON -> FIRST_PERSON_RIGHT_HAND;
            case INVENTORY -> GUI;
            default -> NONE;
        };
    }
}
