package com.gtnewhorizon.newgunrizons.items.instances;

import java.util.Arrays;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

public class ItemWeaponInstance extends ItemInstance<WeaponState> {

    private static final int SERIAL_VERSION = 11;
    private static final long AIM_CHANGE_DURATION = 400L;

    @Setter
    @Getter
    private int ammo;

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

    @Getter
    private int maxShots;

    @Getter
    private float zoom = 1.0F;

    @Getter
    private boolean laserOn;

    private long aimChangeTimestamp;

    @Getter
    private boolean nightVisionOn;

    @Getter
    @Setter
    private int loadIterationCount;

    private int[] activeAttachmentIds = new int[0];
    private byte[] selectedAttachmentIndexes = new byte[0];

    public ItemWeaponInstance() {}

    public ItemWeaponInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        super(itemInventoryIndex, player, itemStack);
    }

    public ItemWeaponInstance(int itemInventoryIndex, EntityLivingBase player) {
        super(itemInventoryIndex, player);
    }

    protected int getSerialVersion() {
        return SERIAL_VERSION;
    }

    public void init(ByteBuf buf) {
        super.init(buf);
        this.activeAttachmentIds = initIntArray(buf);
        this.selectedAttachmentIndexes = initByteArray(buf);
        this.ammo = buf.readInt();
        this.maxShots = buf.readInt();
        this.recoil = buf.readFloat();
        this.zoom = buf.readFloat();
        this.laserOn = buf.readBoolean();
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        serializeIntArray(buf, this.activeAttachmentIds);
        serializeByteArray(buf, this.selectedAttachmentIndexes);
        buf.writeInt(this.ammo);
        buf.writeInt(this.maxShots);
        buf.writeFloat(this.recoil);
        buf.writeFloat(this.zoom);
        buf.writeBoolean(this.laserOn);
    }

    private static void serializeIntArray(ByteBuf buf, int[] a) {
        buf.writeByte(a.length);
        for (int b : a) {
            buf.writeInt(b);
        }
    }

    private static void serializeByteArray(ByteBuf buf, byte[] a) {
        buf.writeByte(a.length);
        for (byte b : a) {
            buf.writeByte(b);
        }
    }

    private static int[] initIntArray(ByteBuf buf) {
        int length = buf.readByte();
        int[] a = new int[length];

        for (int i = 0; i < length; ++i) {
            a[i] = buf.readInt();
        }

        return a;
    }

    private static byte[] initByteArray(ByteBuf buf) {
        int length = buf.readByte();
        byte[] a = new byte[length];

        for (int i = 0; i < length; ++i) {
            a[i] = buf.readByte();
        }

        return a;
    }

    protected void updateWith(ItemInstance<WeaponState> otherItemInstance, boolean updateManagedState) {
        super.updateWith(otherItemInstance, updateManagedState);
        ItemWeaponInstance otherWeaponInstance = (ItemWeaponInstance) otherItemInstance;
        this.setAmmo(otherWeaponInstance.ammo);
        this.setZoom(otherWeaponInstance.zoom);
        this.setRecoil(otherWeaponInstance.recoil);
        this.setSelectedAttachmentIndexes(otherWeaponInstance.selectedAttachmentIndexes);
        this.setActiveAttachmentIds(otherWeaponInstance.activeAttachmentIds);
        this.setLaserOn(otherWeaponInstance.laserOn);
        this.setMaxShots(otherWeaponInstance.maxShots);
        this.setLoadIterationCount(otherWeaponInstance.loadIterationCount);
    }

    public ItemWeapon getWeapon() {
        return (ItemWeapon) this.item;
    }

    public void setRecoil(float recoil) {
        if (recoil != this.recoil) {
            this.recoil = recoil;

        }

    }

    public void setMaxShots(int maxShots) {
        if (this.maxShots != maxShots) {
            this.maxShots = maxShots;

        }

    }

    public void resetCurrentSeries() {
        this.seriesShotCount = 0;
    }

    public float getFireRate() {
        return this.getWeapon()
            .getFireRate();
    }

    public boolean isAutomaticModeEnabled() {
        return this.maxShots > 1;
    }

    public void setAimed(boolean aimed) {
        if (aimed != this.aimed) {
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

    public void setActiveAttachmentIds(int[] activeAttachmentIds) {
        if (!Arrays.equals(this.activeAttachmentIds, activeAttachmentIds)) {
            this.activeAttachmentIds = activeAttachmentIds;

        }

    }

    public byte[] getSelectedAttachmentIds() {
        return this.selectedAttachmentIndexes;
    }

    public void setSelectedAttachmentIndexes(byte[] selectedAttachmentIndexes) {
        if (!Arrays.equals(this.selectedAttachmentIndexes, selectedAttachmentIndexes)) {
            this.selectedAttachmentIndexes = selectedAttachmentIndexes;

        }

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

    public void setZoom(float zoom) {
        if (this.zoom != zoom) {
            this.zoom = zoom;

        }

    }

    public void setLaserOn(boolean laserOn) {
        if (this.laserOn != laserOn) {
            this.laserOn = laserOn;

        }

    }

    public void setNightVisionOn(boolean nightVisionOn) {
        if (this.nightVisionOn != nightVisionOn) {
            this.nightVisionOn = nightVisionOn;

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
        return this.getWeapon()
            .getName() + "["
            + this.getUuid()
            + "]";
    }

    static {
        TypeRegistry.getInstance()
            .register(ItemWeaponInstance.class);
    }
}
