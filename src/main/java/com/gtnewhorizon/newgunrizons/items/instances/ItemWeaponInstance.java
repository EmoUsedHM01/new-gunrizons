package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.state.Stateful;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.NBTTagCompound;

public class ItemWeaponInstance extends ItemInstance implements Stateful<WeaponState> {

    private static final String AMMO_TAG = "gunrizons_ammo";
    private static final long AIM_CHANGE_DURATION = 400L;

    @Getter
    private WeaponState state = WeaponState.IDLE;
    @Getter
    private long stateUpdateTimestamp = System.currentTimeMillis();

    @Setter
    @Getter
    private int ammo;

    @Setter
    @Getter
    private float recoil;

    @Setter
    @Getter
    private int seriesShotCount;

    @Setter
    @Getter
    private long lastFireTimestamp;

    @Getter
    private boolean aimed;
    private long aimChangeTimestamp;

    @Setter
    @Getter
    private int maxShots;

    @Setter
    @Getter
    private float zoom = 1.0F;

    @Setter
    @Getter
    private boolean laserOn;

    @Setter
    @Getter
    private boolean nightVisionOn;

    @Getter
    @Setter
    private int loadIterationCount;

    @Setter
    private int[] activeAttachmentIds = new int[0];
    @Setter
    @Getter
    private byte[] selectedAttachmentIndexes = new byte[0];

    public ItemWeaponInstance() {}

    public ItemWeaponInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        super(itemInventoryIndex, player, itemStack);
    }

    public ItemWeaponInstance(int itemInventoryIndex, EntityLivingBase player) {
        super(itemInventoryIndex, player);
    }

    @Override
    public void readFromBuf(ByteBuf buf) {
        super.readFromBuf(buf);
        this.state = WeaponState.values()[buf.readInt()];

        int lengthActive = buf.readByte();
        this.activeAttachmentIds = new int[lengthActive];
        for (int i = 0; i < lengthActive; ++i) {
            this.activeAttachmentIds[i] = buf.readInt();
        }

        int lengthSelected = buf.readByte();
        this.selectedAttachmentIndexes = new byte[lengthSelected];
        for (int i = 0; i < lengthSelected; ++i) {
            this.selectedAttachmentIndexes[i] = buf.readByte();
        }

        this.ammo = buf.readInt();
        this.maxShots = buf.readInt();
        this.recoil = buf.readFloat();
        this.zoom = buf.readFloat();
        this.laserOn = buf.readBoolean();
    }

    @Override
    public void writeToBuf(ByteBuf buf) {
        super.writeToBuf(buf);
        buf.writeInt(this.state != null ? this.state.ordinal() : 0);

        buf.writeByte(this.activeAttachmentIds.length);
        for (int b : this.activeAttachmentIds) {
            buf.writeInt(b);
        }

        buf.writeByte(this.selectedAttachmentIndexes.length);
        for (byte b : this.selectedAttachmentIndexes) {
            buf.writeByte(b);
        }

        buf.writeInt(this.ammo);
        buf.writeInt(this.maxShots);
        buf.writeFloat(this.recoil);
        buf.writeFloat(this.zoom);
        buf.writeBoolean(this.laserOn);
    }

    @Override
    public void writeByteType(ByteBuf buf) {
        buf.writeByte(1);
    }

    public void setState(WeaponState state) {
        this.state = state;
        this.stateUpdateTimestamp = System.currentTimeMillis();
    }

    public ItemWeapon getWeapon() {
        return (ItemWeapon) this.item;
    }

    public void resetCurrentSeries() {
        this.seriesShotCount = 0;
    }

    public boolean isSilencerOn() {
        int[] activeAttachmentsIds = getActiveAttachmentIds();
        int activeAttachmentIdForThisCategory = activeAttachmentsIds[AttachmentCategory.SILENCER.ordinal()];
        return activeAttachmentIdForThisCategory > 0;
    }

    public void setAimed(boolean aimed) {
        if (this.aimed != aimed) {
            this.aimed = aimed;
            this.aimChangeTimestamp = System.currentTimeMillis();
        }
    }

    public int[] getActiveAttachmentIds() {
        if (this.activeAttachmentIds == null || this.activeAttachmentIds.length != AttachmentCategory.VALUES.length) {
            this.activeAttachmentIds = new int[AttachmentCategory.VALUES.length];

            for (CompatibleAttachment attachment : this.getWeapon()
                .getCompatibleAttachments()
                .values()) {
                if (attachment.isDefault()) {
                    this.activeAttachmentIds[attachment.getAttachment()
                        .getCategory()
                        .ordinal()] = Item.getIdFromItem(attachment.getAttachment());
                }
            }
        }

        return this.activeAttachmentIds;
    }

    public boolean isAttachmentZoomEnabled() {
        Item scopeItem = this.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
        return scopeItem instanceof ItemScope;
    }

    public ItemAttachment getAttachmentItemWithCategory(AttachmentCategory category) {
        if (this.activeAttachmentIds != null && this.activeAttachmentIds.length > category.ordinal()) {
            Item activeAttachment = Item.getItemById(this.activeAttachmentIds[category.ordinal()]);
            return activeAttachment instanceof ItemAttachment ? (ItemAttachment) activeAttachment : null;
        } else {
            return null;
        }
    }

    public boolean needsOpticalScopePerspective() {
        if (this.isAimed()) {
            ItemAttachment scope = this.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
            return scope instanceof ItemScope && ((ItemScope) scope).isOptical();
        }
        return false;
    }

    public float getAimChangeProgress() {
        float delta = (float) (System.currentTimeMillis() - this.aimChangeTimestamp) / AIM_CHANGE_DURATION;
        float p = Math.min(Math.max(delta, 0.0F), 1.0F);
        if (!this.isAimed()) {
            p = 1.0F - p;
        }
        return p;
    }

    public String toString() {
        return this.getWeapon().getName() + "[weapon]";
    }


    public static int getAmmo(ItemStack itemStack) {
        return itemStack != null && itemStack.stackTagCompound != null ? itemStack.stackTagCompound.getInteger(AMMO_TAG)
            : 0;
    }

    public static void setAmmo(ItemStack itemStack, int ammo) {
        if (itemStack != null) {
            if (itemStack.stackTagCompound == null) {
                itemStack.stackTagCompound = new NBTTagCompound();
            }
            itemStack.stackTagCompound.setInteger(AMMO_TAG, ammo);
        }
    }
}
