package com.gtnewhorizon.newgunrizons.attachment;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface AttachmentContainer {

    List<CompatibleAttachment> getActiveAttachments(EntityLivingBase var1, ItemStack var2);
}
