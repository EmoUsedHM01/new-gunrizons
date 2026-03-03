package com.gtnewhorizon.newgunrizons.grenade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.ItemAttachment;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;

import io.netty.buffer.ByteBuf;

public class PlayerGrenadeInstance extends PlayerItemInstance<GrenadeState> {

    private static final int SERIAL_VERSION = 11;
    private int ammo;
    private long activationTimestamp;
    private final Deque<AsyncGrenadeState> filteredStateQueue = new LinkedBlockingDeque();
    private int[] activeAttachmentIds = new int[0];
    private byte[] selectedAttachmentIndexes = new byte[0];
    private long lastSafetyPinAlertTimestamp;
    private boolean throwingFar;

    public PlayerGrenadeInstance() {}

    public PlayerGrenadeInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        super(itemInventoryIndex, player, itemStack);
    }

    protected int getSerialVersion() {
        return SERIAL_VERSION;
    }

    private void addStateToHistory(GrenadeState state) {
        AsyncGrenadeState t;
        while ((t = this.filteredStateQueue.peekFirst()) != null && t.getState()
            .getPriority() < state.getPriority()) {
            this.filteredStateQueue.pollFirst();
        }

        long expirationTimeout = 500L;
        this.filteredStateQueue.addFirst(new AsyncGrenadeState(state, this.stateUpdateTimestamp, expirationTimeout));
    }

    public boolean setState(GrenadeState state) {
        boolean result = super.setState(state);
        this.addStateToHistory(state);
        return result;
    }

    public AsyncGrenadeState nextHistoryState() {
        AsyncGrenadeState result = this.filteredStateQueue.pollLast();
        if (result == null) {
            result = new AsyncGrenadeState(this.getState(), this.stateUpdateTimestamp);
        }

        return result;
    }

    public int getAmmo() {
        return this.ammo;
    }

    protected void setAmmo(int ammo) {
        if (ammo != this.ammo) {
            this.ammo = ammo;
            ++this.updateId;
        }

    }

    public void init(ByteBuf buf) {
        super.init(buf);
        this.throwingFar = buf.readBoolean();
        this.activeAttachmentIds = initIntArray(buf);
        this.selectedAttachmentIndexes = initByteArray(buf);
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeBoolean(this.throwingFar);
        serializeIntArray(buf, this.activeAttachmentIds);
        serializeByteArray(buf, this.selectedAttachmentIndexes);
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

    protected void updateWith(PlayerItemInstance<GrenadeState> otherItemInstance, boolean updateManagedState) {
        super.updateWith(otherItemInstance, updateManagedState);
        PlayerGrenadeInstance otherWeaponInstance = (PlayerGrenadeInstance) otherItemInstance;
        this.setAmmo(otherWeaponInstance.ammo);
        this.setSelectedAttachmentIndexes(otherWeaponInstance.selectedAttachmentIndexes);
        this.setActiveAttachmentIds(otherWeaponInstance.activeAttachmentIds);
    }

    public ItemGrenade getWeapon() {
        return (ItemGrenade) this.item;
    }

    public long getActivationTimestamp() {
        return this.activationTimestamp;
    }

    void setActivationTimestamp(long activationTimestamp) {
        this.activationTimestamp = activationTimestamp;
    }

    public int[] getActiveAttachmentIds() {
        if (this.activeAttachmentIds == null || this.activeAttachmentIds.length != AttachmentCategory.values.length) {
            this.activeAttachmentIds = new int[AttachmentCategory.values.length];
            for (CompatibleAttachment itemGrenadeCompatibleAttachment : this.getWeapon()
                .getCompatibleAttachments()
                .values()) {
                CompatibleAttachment attachment = itemGrenadeCompatibleAttachment;
                if (attachment.isDefault()) {
                    this.activeAttachmentIds[attachment.getAttachment()
                        .getCategory()
                        .ordinal()] = Item.getIdFromItem(attachment.getAttachment());
                }
            }
        }

        return this.activeAttachmentIds;
    }

    void setActiveAttachmentIds(int[] activeAttachmentIds) {
        if (!Arrays.equals(this.activeAttachmentIds, activeAttachmentIds)) {
            this.activeAttachmentIds = activeAttachmentIds;
            ++this.updateId;
        }

    }

    void setSelectedAttachmentIndexes(byte[] selectedAttachmentIndexes) {
        if (!Arrays.equals(this.selectedAttachmentIndexes, selectedAttachmentIndexes)) {
            this.selectedAttachmentIndexes = selectedAttachmentIndexes;
            ++this.updateId;
        }

    }

    public List<CompatibleAttachment> getActiveAttachments() {
        int[] activeIds = this.getActiveAttachmentIds();
        List<CompatibleAttachment> result = new ArrayList<>();

        for (int activeId : activeIds) {
            if (activeId != 0) {
                Item item = Item.getItemById(activeId);
                if (item instanceof ItemAttachment) {
                    CompatibleAttachment compatibleAttachment = getWeapon().getCompatibleAttachments()
                        .get(item);
                    if (compatibleAttachment != null) {
                        result.add(compatibleAttachment);
                    }
                }
            }
        }

        return result;
    }

    public String toString() {
        return this.getWeapon().builder.name + "[" + this.getUuid() + "]";
    }

    public long getLastSafetyPinAlertTimestamp() {
        return this.lastSafetyPinAlertTimestamp;
    }

    public void setLastSafetyPinAlertTimestamp(long lastSafetyPinAlertTimestamp) {
        this.lastSafetyPinAlertTimestamp = lastSafetyPinAlertTimestamp;
    }

    public void setThrowingFar(boolean throwingFar) {
        this.throwingFar = throwingFar;
    }

    public boolean isThrowingFar() {
        return this.throwingFar;
    }

    static {
        TypeRegistry.getInstance()
            .register(PlayerGrenadeInstance.class);
    }
}
