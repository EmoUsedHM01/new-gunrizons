package com.gtnewhorizon.newgunrizons.items;

import net.minecraft.client.model.ModelBase;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.config.ModContext;

public class ItemBullet extends ItemAttachment {

    public ItemBullet(String modId, AttachmentCategory category, ModelBase model, String textureName, String crosshair,
        ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
        super(modId, category, model, textureName, crosshair, apply, remove);
    }

    public static final class Builder extends AttachmentBuilder {

        public Builder() {
            withMaxStackSize(64);
        }

        public ItemAttachment createAttachment(ModContext modContext) {
            return new ItemBullet(
                getModId(),
                AttachmentCategory.BULLET,
                getModel(),
                getTextureName(),
                null,
                null,
                null);
        }
    }
}
