package com.vicmatskiv.weaponlib;

import java.util.Arrays;
import java.util.Deque;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingDeque;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vicmatskiv.weaponlib.network.TypeRegistry;

import com.vicmatskiv.weaponlib.shader.DynamicShaderGroupSource;
import com.vicmatskiv.weaponlib.shader.DynamicShaderPhase;

import io.netty.buffer.ByteBuf;

public class PlayerWeaponInstance extends PlayerItemInstance<WeaponState> {

    private static final int SERIAL_VERSION = 9;
    private static final UUID NIGHT_VISION_SOURCE_UUID;
    private static final UUID VIGNETTE_SOURCE_UUID;
    private static final UUID BLUR_SOURCE_UUID;
    public final DynamicShaderGroupSource BLUR_SOURCE;
    public final DynamicShaderGroupSource NIGHT_VISION_SOURCE;
    public final DynamicShaderGroupSource VIGNETTE_SOURCE;
    private static final long AIM_CHANGE_DURATION = 400L;
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
    private float zoom;
    @Getter
    private boolean laserOn;
    private long aimChangeTimestamp;
    @Getter
    private boolean nightVisionOn;
    @Getter
    @Setter
    private int loadIterationCount;
    private final Deque<AsyncWeaponState> filteredStateQueue;
    private int[] activeAttachmentIds;
    private byte[] selectedAttachmentIndexes;

    public PlayerWeaponInstance() {
        this.BLUR_SOURCE = (new DynamicShaderGroupSource(
            BLUR_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/blur.json")))
                .withUniform("Radius", (context) -> this.hasOpticScope() ? 10.0F : 5.0F)
                .withUniform("Progress", (context) -> this.getAimChangeProgress());
        this.NIGHT_VISION_SOURCE = (new DynamicShaderGroupSource(
            NIGHT_VISION_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/night-vision.json")))
                .withUniform(
                    "IntensityAdjust",
                    (context) -> 40.0F - Minecraft.getMinecraft().gameSettings.gammaSetting * 38.0F)
                .withUniform(
                    "NoiseAmplification",
                    (context) -> 2.0F + 3.0F * Minecraft.getMinecraft().gameSettings.gammaSetting);
        this.VIGNETTE_SOURCE = (new DynamicShaderGroupSource(
            VIGNETTE_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/vignette.json"))).withUniform(
                "Radius",
                (context) -> this.getOpticScopeVignetteRadius(context.getPartialTicks()));
        this.zoom = 1.0F;
        this.filteredStateQueue = new LinkedBlockingDeque<>();
        this.activeAttachmentIds = new int[0];
        this.selectedAttachmentIndexes = new byte[0];
    }

    public PlayerWeaponInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        super(itemInventoryIndex, player, itemStack);
        this.BLUR_SOURCE = (new DynamicShaderGroupSource(
            BLUR_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/blur.json")))
                .withUniform("Radius", (context) -> this.hasOpticScope() ? 10.0F : 5.0F)
                .withUniform("Progress", (context) -> this.getAimChangeProgress());
        this.NIGHT_VISION_SOURCE = (new DynamicShaderGroupSource(
            NIGHT_VISION_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/night-vision.json")))
                .withUniform(
                    "IntensityAdjust",
                    (context) -> 40.0F - Minecraft.getMinecraft().gameSettings.gammaSetting * 38.0F)
                .withUniform(
                    "NoiseAmplification",
                    (context) -> 2.0F + 3.0F * Minecraft.getMinecraft().gameSettings.gammaSetting);
        this.VIGNETTE_SOURCE = (new DynamicShaderGroupSource(
            VIGNETTE_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/vignette.json"))).withUniform(
                "Radius",
                (context) -> this.getOpticScopeVignetteRadius(context.getPartialTicks()));
        this.zoom = 1.0F;
        this.filteredStateQueue = new LinkedBlockingDeque<>();
        this.activeAttachmentIds = new int[0];
        this.selectedAttachmentIndexes = new byte[0];
    }

    public PlayerWeaponInstance(int itemInventoryIndex, EntityLivingBase player) {
        super(itemInventoryIndex, player);
        this.BLUR_SOURCE = (new DynamicShaderGroupSource(
            BLUR_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/blur.json")))
                .withUniform("Radius", (context) -> this.hasOpticScope() ? 10.0F : 5.0F)
                .withUniform("Progress", (context) -> this.getAimChangeProgress());
        this.NIGHT_VISION_SOURCE = (new DynamicShaderGroupSource(
            NIGHT_VISION_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/night-vision.json")))
                .withUniform(
                    "IntensityAdjust",
                    (context) -> 40.0F - Minecraft.getMinecraft().gameSettings.gammaSetting * 38.0F)
                .withUniform(
                    "NoiseAmplification",
                    (context) -> 2.0F + 3.0F * Minecraft.getMinecraft().gameSettings.gammaSetting);
        this.VIGNETTE_SOURCE = (new DynamicShaderGroupSource(
            VIGNETTE_SOURCE_UUID,
            new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/vignette.json"))).withUniform(
                "Radius",
                (context) -> this.getOpticScopeVignetteRadius(context.getPartialTicks()));
        this.zoom = 1.0F;
        this.filteredStateQueue = new LinkedBlockingDeque<>();
        this.activeAttachmentIds = new int[0];
        this.selectedAttachmentIndexes = new byte[0];
    }

    protected int getSerialVersion() {
        return SERIAL_VERSION;
    }

    private void addStateToHistory(WeaponState state) {
        AsyncWeaponState t;
        while ((t = this.filteredStateQueue.peekFirst()) != null && t.getState()
            .getPriority() < state.getPriority()) {
            this.filteredStateQueue.pollFirst();
        }

        long expirationTimeout;
        if (state != WeaponState.FIRING && state != WeaponState.RECOILED && state != WeaponState.PAUSED) {
            expirationTimeout = 2147483647L;
        } else {
            if (this.isAutomaticModeEnabled() && !this.getWeapon()
                .hasRecoilPositioning()) {
                expirationTimeout = (long) (50.0F / this.getFireRate());
            } else {
                expirationTimeout = 500L;
            }
        }

        this.filteredStateQueue.addFirst(new AsyncWeaponState(state, this.stateUpdateTimestamp, expirationTimeout));
    }

    public boolean setState(WeaponState state) {
        boolean result = super.setState(state);
        this.addStateToHistory(state);
        return result;
    }

    public AsyncWeaponState nextHistoryState() {
        AsyncWeaponState result = this.filteredStateQueue.pollLast();
        if (result == null) {
            result = new AsyncWeaponState(this.getState(), this.stateUpdateTimestamp);
        }

        return result;
    }

    public void setAmmo(int ammo) {
        if (ammo != this.ammo) {
            this.ammo = ammo;
            ++this.updateId;
        }

    }

    public void init(ByteBuf buf) {
        super.init(buf);
        this.activeAttachmentIds = initIntArray(buf);
        this.selectedAttachmentIndexes = initByteArray(buf);
        this.ammo = buf.readInt();
        this.aimed = buf.readBoolean();
        this.recoil = buf.readFloat();
        this.maxShots = buf.readInt();
        this.zoom = buf.readFloat();
        this.laserOn = buf.readBoolean();
        this.nightVisionOn = buf.readBoolean();
        this.loadIterationCount = buf.readInt();
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        serializeIntArray(buf, this.activeAttachmentIds);
        serializeByteArray(buf, this.selectedAttachmentIndexes);
        buf.writeInt(this.ammo);
        buf.writeBoolean(this.aimed);
        buf.writeFloat(this.recoil);
        buf.writeInt(this.maxShots);
        buf.writeFloat(this.zoom);
        buf.writeBoolean(this.laserOn);
        buf.writeBoolean(this.nightVisionOn);
        buf.writeInt(this.loadIterationCount);
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

    protected void updateWith(PlayerItemInstance<WeaponState> otherItemInstance, boolean updateManagedState) {
        super.updateWith(otherItemInstance, updateManagedState);
        PlayerWeaponInstance otherWeaponInstance = (PlayerWeaponInstance) otherItemInstance;
        this.setAmmo(otherWeaponInstance.ammo);
        this.setZoom(otherWeaponInstance.zoom);
        this.setRecoil(otherWeaponInstance.recoil);
        this.setSelectedAttachmentIndexes(otherWeaponInstance.selectedAttachmentIndexes);
        this.setActiveAttachmentIds(otherWeaponInstance.activeAttachmentIds);
        this.setLaserOn(otherWeaponInstance.laserOn);
        this.setMaxShots(otherWeaponInstance.maxShots);
        this.setLoadIterationCount(otherWeaponInstance.loadIterationCount);
    }

    public Weapon getWeapon() {
        return (Weapon) this.item;
    }

    public void setRecoil(float recoil) {
        if (recoil != this.recoil) {
            this.recoil = recoil;
            ++this.updateId;
        }

    }

    void setMaxShots(int maxShots) {
        if (this.maxShots != maxShots) {
            this.maxShots = maxShots;
            ++this.updateId;
        }

    }

    public void resetCurrentSeries() {
        this.seriesShotCount = 0;
    }

    public float getFireRate() {
        return this.getWeapon().builder.fireRate;
    }

    public boolean isAutomaticModeEnabled() {
        return this.maxShots > 1;
    }

    public void setAimed(boolean aimed) {
        if (aimed != this.aimed) {
            this.aimed = aimed;
            ++this.updateId;
            this.aimChangeTimestamp = System.currentTimeMillis();
        }

    }

    public int[] getActiveAttachmentIds() {
        if (this.activeAttachmentIds == null || this.activeAttachmentIds.length != AttachmentCategory.values.length) {
            this.activeAttachmentIds = new int[AttachmentCategory.values.length];

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

    void setActiveAttachmentIds(int[] activeAttachmentIds) {
        if (!Arrays.equals(this.activeAttachmentIds, activeAttachmentIds)) {
            this.activeAttachmentIds = activeAttachmentIds;
            ++this.updateId;
        }

    }

    public byte[] getSelectedAttachmentIds() {
        return this.selectedAttachmentIndexes;
    }

    void setSelectedAttachmentIndexes(byte[] selectedAttachmentIndexes) {
        if (!Arrays.equals(this.selectedAttachmentIndexes, selectedAttachmentIndexes)) {
            this.selectedAttachmentIndexes = selectedAttachmentIndexes;
            ++this.updateId;
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
            ++this.updateId;
        }

    }

    public void setLaserOn(boolean laserOn) {
        if (this.laserOn != laserOn) {
            this.laserOn = laserOn;
            ++this.updateId;
        }

    }

    public void setNightVisionOn(boolean nightVisionOn) {
        if (this.nightVisionOn != nightVisionOn) {
            this.nightVisionOn = nightVisionOn;
            ++this.updateId;
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
        float p = MiscUtils.clamp((float) (System.currentTimeMillis() - this.aimChangeTimestamp) / AIM_CHANGE_DURATION, 0.0F, 1.0F);
        if (!this.isAimed()) {
            p = 1.0F - p;
        }

        return p;
    }

    public DynamicShaderGroupSource getShaderSource(DynamicShaderPhase phase) {

        if (this.isAimed() && phase == DynamicShaderPhase.POST_WORLD_OPTICAL_SCOPE_RENDER) {
            ItemScope scope = this.getScope();
            if (scope != null && scope.isOptical()) {
                return scope.hasNightVision() && this.nightVisionOn ? this.NIGHT_VISION_SOURCE : this.VIGNETTE_SOURCE;
            }
        }
        boolean blurOnAim = true;
        float progress = this.getAimChangeProgress();
        return !blurOnAim || phase != DynamicShaderPhase.PRE_ITEM_RENDER
            || !this.isAimed() && (!(progress > 0.0F) || !(progress < 1.0F)) ? null : this.BLUR_SOURCE;
    }

    public String toString() {
        return this.getWeapon().builder.name + "[" + this.getUuid() + "]";
    }

    static {
        TypeRegistry.getInstance()
            .register(PlayerWeaponInstance.class);
        NIGHT_VISION_SOURCE_UUID = UUID.randomUUID();
        VIGNETTE_SOURCE_UUID = UUID.randomUUID();
        BLUR_SOURCE_UUID = UUID.randomUUID();
    }
}
