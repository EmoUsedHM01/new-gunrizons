package com.gtnewhorizon.newgunrizons.items.instances;

import java.util.Arrays;
import java.util.Deque;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderEffect;
import com.gtnewhorizon.newgunrizons.client.shaders.ShaderPhase;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.util.MiscUtils;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;
import com.gtnewhorizon.newgunrizons.weapon.WeaponStateTimed;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

public class ItemWeaponInstance extends ItemInstance<WeaponState> {

    private static final int SERIAL_VERSION = 10;
    private static final UUID NIGHT_VISION_SOURCE_UUID = UUID.randomUUID();
    private static final UUID VIGNETTE_SOURCE_UUID = UUID.randomUUID();
    private static final UUID BLUR_SOURCE_UUID = UUID.randomUUID();

    public final ShaderEffect BLUR_SOURCE;
    public final ShaderEffect NIGHT_VISION_SOURCE;
    public final ShaderEffect VIGNETTE_SOURCE;

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

    private final Deque<WeaponStateTimed> filteredStateQueue = new LinkedBlockingDeque<>();

    private int[] activeAttachmentIds = new int[0];
    private byte[] selectedAttachmentIndexes = new byte[0];

    {
        this.BLUR_SOURCE = (new ShaderEffect(
            BLUR_SOURCE_UUID,
            new ResourceLocation(NewGunrizonsMod.MODID, "shaders/post/blur.json")))
                .withUniform("Radius", (context) -> this.hasOpticScope() ? 10.0F : 5.0F)
                .withUniform("Progress", (context) -> this.getAimChangeProgress());
        this.NIGHT_VISION_SOURCE = (new ShaderEffect(
            NIGHT_VISION_SOURCE_UUID,
            new ResourceLocation(NewGunrizonsMod.MODID, "shaders/post/night-vision.json")))
                .withUniform(
                    "IntensityAdjust",
                    (context) -> 40.0F - Minecraft.getMinecraft().gameSettings.gammaSetting * 38.0F)
                .withUniform(
                    "NoiseAmplification",
                    (context) -> 2.0F + 3.0F * Minecraft.getMinecraft().gameSettings.gammaSetting);
        this.VIGNETTE_SOURCE = (new ShaderEffect(
            VIGNETTE_SOURCE_UUID,
            new ResourceLocation(NewGunrizonsMod.MODID, "shaders/post/vignette.json")))
                .withUniform("Radius", (context) -> this.getOpticScopeVignetteRadius(context.getPartialTicks()));
    }

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

    private void addStateToHistory(WeaponState state) {
        WeaponStateTimed t;
        while ((t = this.filteredStateQueue.peekFirst()) != null && t.getState()
            .getPriority() < state.getPriority()) {
            this.filteredStateQueue.pollFirst();
        }

        long expirationTimeout;
        if (state != WeaponState.FIRING && state != WeaponState.RECOILED && state != WeaponState.PAUSED) {
            expirationTimeout = Integer.MAX_VALUE;
        } else {
            if (this.isAutomaticModeEnabled() && !this.getWeapon()
                .hasRecoilPositioning()) {
                expirationTimeout = (long) (50.0F / this.getFireRate());
            } else {
                expirationTimeout = 500L;
            }
        }

        this.filteredStateQueue.addFirst(new WeaponStateTimed(state, this.stateUpdateTimestamp, expirationTimeout));
    }

    public boolean setState(WeaponState state) {
        boolean result = super.setState(state);
        this.addStateToHistory(state);
        return result;
    }

    public WeaponStateTimed nextHistoryState() {
        WeaponStateTimed result = this.filteredStateQueue.pollLast();
        if (result == null) {
            result = new WeaponStateTimed(this.getState(), this.stateUpdateTimestamp);
        }

        return result;
    }

    public void init(ByteBuf buf) {
        super.init(buf);
        this.activeAttachmentIds = initIntArray(buf);
        this.selectedAttachmentIndexes = initByteArray(buf);
        this.ammo = buf.readInt();
        this.maxShots = buf.readInt();
        this.zoom = buf.readFloat();
        this.laserOn = buf.readBoolean();
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        serializeIntArray(buf, this.activeAttachmentIds);
        serializeByteArray(buf, this.selectedAttachmentIndexes);
        buf.writeInt(this.ammo);
        buf.writeInt(this.maxShots);
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

    private boolean hasOpticScope() {
        ItemAttachment scope = this.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
        return scope instanceof ItemScope && ((ItemScope) scope).isOptical();
    }

    private ItemScope getScope() {
        ItemAttachment scope = this.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
        return scope instanceof ItemScope ? (ItemScope) scope : null;
    }

    private float getOpticScopeVignetteRadius(float partialTicks) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        float f2 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
        return -6.5F * f2 + 0.55F;
    }

    private float getAimChangeProgress() {
        float p = MiscUtils
            .clamp((float) (System.currentTimeMillis() - this.aimChangeTimestamp) / AIM_CHANGE_DURATION, 0.0F, 1.0F);
        if (!this.isAimed()) {
            p = 1.0F - p;
        }

        return p;
    }

    public ShaderEffect getShaderSource(ShaderPhase phase) {

        if (this.isAimed() && phase == ShaderPhase.SCOPE_RENDER) {
            ItemScope scope = this.getScope();
            if (scope != null && scope.isOptical()) {
                return scope.hasNightVision() && this.nightVisionOn ? this.NIGHT_VISION_SOURCE : this.VIGNETTE_SOURCE;
            }
        }

        float progress = this.getAimChangeProgress();
        return phase != ShaderPhase.ITEM_RENDER || !this.isAimed() && (!(progress > 0.0F) || !(progress < 1.0F)) ? null
            : this.BLUR_SOURCE;
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
