package com.gtnewhorizon.newgunrizons.items;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;

public class ItemBullet extends ItemAttachment {
   public ItemBullet(AttachmentCategory category, String crosshair) {
      super(category, crosshair);
   }

   public static final class Builder extends AttachmentBuilder {
      public Builder() {
         this.withMaxStackSize(64);
      }

      @Override
      protected ItemAttachment createAttachment() {
         return new ItemBullet(AttachmentCategory.BULLET, null);
      }
   }
}
