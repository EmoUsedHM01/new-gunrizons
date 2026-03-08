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
        switch (itemRenderType) {
            case ENTITY: return GROUND;
            case EQUIPPED: return THIRD_PERSON_RIGHT_HAND;
            case EQUIPPED_FIRST_PERSON: return FIRST_PERSON_RIGHT_HAND;
            case INVENTORY: return GUI;
            default: return NONE;
        }
    }
}
