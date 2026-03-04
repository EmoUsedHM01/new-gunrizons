package com.gtnewhorizon.newgunrizons.attachment;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import lombok.Getter;

@Getter
public class CompatibleAttachment {

    private final ItemAttachment attachment;
    private Consumer<ModelBase> modelPositioning;
    private BiConsumer<EntityLivingBase, ItemStack> positioning;
    private boolean isDefault;
    private boolean isPermanent;
    private ItemAttachment.ApplyHandler2 applyHandler;
    private ItemAttachment.ApplyHandler2 removeHandler;

    public CompatibleAttachment(ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning,
        Consumer<ModelBase> modelPositioning, boolean isDefault, boolean isPermanent) {
        this.attachment = attachment;
        this.positioning = positioning;
        this.modelPositioning = modelPositioning;
        this.isDefault = isDefault;
        this.isPermanent = isPermanent;
    }

    public CompatibleAttachment(ItemAttachment attachment, BiConsumer<EntityLivingBase, ItemStack> positioning,
        Consumer<ModelBase> modelPositioning, boolean isDefault) {
        this(attachment, positioning, modelPositioning, isDefault, false);
    }

    public CompatibleAttachment(ItemAttachment attachment, ItemAttachment.ApplyHandler2 applyHandler,
        ItemAttachment.ApplyHandler2 removeHandler) {
        this.attachment = attachment;
        this.applyHandler = applyHandler;
        this.removeHandler = removeHandler;
    }

    public CompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioning) {
        this(attachment, null, positioning, false);
    }

    public CompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioning, boolean isDefault) {
        this.attachment = attachment;
        this.modelPositioning = positioning;
        this.isDefault = isDefault;
    }

}
