package com.gtnewhorizon.newgunrizons.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.config.PlayerContext;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.network.UniversalObject;
import com.gtnewhorizon.newgunrizons.state.ExtendedState;
import com.gtnewhorizon.newgunrizons.state.ManagedState;

import io.netty.buffer.ByteBuf;

public class PlayerItemInstance<S extends ManagedState<S>> extends UniversalObject
    implements ExtendedState<S>, PlayerContext {

    private static final Logger logger = LogManager.getLogger(PlayerItemInstance.class);
    protected S state;
    protected long stateUpdateTimestamp = System.currentTimeMillis();
    protected long updateId;
    protected EntityLivingBase player;
    protected Item item;
    protected int itemInventoryIndex;
    private PlayerItemInstance<S> preparedState;
    private long syncStartTimestamp;

    public PlayerItemInstance() {}

    public PlayerItemInstance(int itemInventoryIndex, EntityLivingBase player) {
        this.itemInventoryIndex = itemInventoryIndex;
        this.player = player;
        ItemStack itemStack = player.getHeldItem();
        if (itemStack != null) {
            this.item = itemStack.getItem();
        }

    }

    public PlayerItemInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        this.itemInventoryIndex = itemInventoryIndex;
        this.player = player;
        if (itemStack != null) {
            this.item = itemStack.getItem();
        }

    }

    public EntityLivingBase getPlayer() {
        return this.player;
    }

    public void setPlayer(EntityLivingBase player) {
        this.player = player;
    }

    public Item getItem() {
        return this.item;
    }

    public ItemStack getItemStack() {
        return this.player instanceof EntityPlayer
            ? ((EntityPlayer) this.player).inventory.getStackInSlot(this.itemInventoryIndex)
            : null;
    }

    public int getItemInventoryIndex() {
        return this.itemInventoryIndex;
    }

    protected void setItemInventoryIndex(int itemInventoryIndex) {
        this.itemInventoryIndex = itemInventoryIndex;
    }

    protected <T extends PlayerItemInstance<S>> T getPreparedState() {
        return (T) this.preparedState;
    }

    public void init(ByteBuf buf) {
        super.init(buf);
        this.item = Item.getItemById(buf.readInt());
        this.itemInventoryIndex = buf.readInt();
        this.updateId = buf.readLong();
        this.state = (S) TypeRegistry.getInstance()
            .fromBytes(buf);
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(Item.getIdFromItem(this.item));
        buf.writeInt(this.itemInventoryIndex);
        buf.writeLong(this.updateId);
        TypeRegistry.getInstance()
            .toBytes(this.state, buf);
    }

    public boolean setState(S state) {
        this.state = state;
        this.stateUpdateTimestamp = System.currentTimeMillis();
        ++this.updateId;
        if (this.preparedState != null) {
            if (this.preparedState.getState()
                .commitPhase() == state) {
                logger.debug(
                    "Committing state {} to {}",
                    new Object[] { this.preparedState.getState(), this.preparedState.getState()
                        .commitPhase() });
                this.updateWith(this.preparedState, false);
            } else {
                this.rollback();
            }

            this.preparedState = null;
        }

        return false;
    }

    protected void rollback() {}

    protected void updateWith(PlayerItemInstance<S> otherState, boolean updateManagedState) {
        if (updateManagedState) {
            this.setState(otherState.getState());
        }

    }

    public S getState() {
        return this.state;
    }

    public long getStateUpdateTimestamp() {
        return this.stateUpdateTimestamp;
    }

    public long getUpdateId() {
        return this.updateId;
    }

    void markDirty() {
        ++this.updateId;
    }

    public <E extends ExtendedState<S>> void prepareTransaction(E preparedExtendedState) {
        this.setState(preparedExtendedState.getState());
        this.preparedState = (PlayerItemInstance) preparedExtendedState;
    }

    public long getSyncStartTimestamp() {
        return this.syncStartTimestamp;
    }

    public void setSyncStartTimestamp(long syncStartTimestamp) {
        this.syncStartTimestamp = syncStartTimestamp;
    }

    public boolean needsOpticalScopePerspective() {
        return false;
    }

    static {
        TypeRegistry.getInstance()
            .register(PlayerItemInstance.class);
        TypeRegistry.getInstance()
            .register(PlayerWeaponInstance.class);
    }
}
