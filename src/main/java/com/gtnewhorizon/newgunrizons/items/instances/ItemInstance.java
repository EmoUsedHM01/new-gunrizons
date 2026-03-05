package com.gtnewhorizon.newgunrizons.items.instances;

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
import lombok.Getter;
import lombok.Setter;

public class ItemInstance<S extends ManagedState<S>> extends UniversalObject

    implements ExtendedState<S>, PlayerContext {

    private static final Logger logger = LogManager.getLogger(ItemInstance.class);
    @Getter
    protected S state;
    @Getter
    protected long stateUpdateTimestamp = System.currentTimeMillis();
    @Setter
    @Getter
    protected EntityLivingBase player;
    @Getter
    protected Item item;
    @Getter
    protected int itemInventoryIndex;
    private ItemInstance<S> preparedState;

    public ItemInstance() {}

    public ItemInstance(int itemInventoryIndex, EntityLivingBase player) {
        this.itemInventoryIndex = itemInventoryIndex;
        this.player = player;
        ItemStack itemStack = player.getHeldItem();
        if (itemStack != null) {
            this.item = itemStack.getItem();
        }

    }

    public ItemInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        this.itemInventoryIndex = itemInventoryIndex;
        this.player = player;
        if (itemStack != null) {
            this.item = itemStack.getItem();
        }

    }

    public ItemStack getItemStack() {
        return this.player instanceof EntityPlayer
            ? ((EntityPlayer) this.player).inventory.getStackInSlot(this.itemInventoryIndex)
            : null;
    }

    protected void setItemInventoryIndex(int itemInventoryIndex) {
        this.itemInventoryIndex = itemInventoryIndex;
    }

    public void init(ByteBuf buf) {
        super.init(buf);
        this.item = Item.getItemById(buf.readInt());
        this.itemInventoryIndex = buf.readInt();
        this.state = TypeRegistry.getInstance()
            .fromBytes(buf);
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
        buf.writeInt(Item.getIdFromItem(this.item));
        buf.writeInt(this.itemInventoryIndex);
        TypeRegistry.getInstance()
            .toBytes(this.state, buf);
    }

    public boolean setState(S state) {
        this.state = state;
        this.stateUpdateTimestamp = System.currentTimeMillis();
        if (this.preparedState != null) {
            if (this.preparedState.getState()
                .commitPhase() == state) {
                logger.debug(
                    "Committing state {} to {}",
                    this.preparedState.getState(),
                    this.preparedState.getState()
                        .commitPhase());
                this.updateWith(this.preparedState, false);
            } else {
                this.rollback();
            }

            this.preparedState = null;
        }

        return false;
    }

    protected void rollback() {}

    protected void updateWith(ItemInstance<S> otherState, boolean updateManagedState) {
        if (updateManagedState) {
            this.setState(otherState.getState());
        }

    }

    public <E extends ExtendedState<S>> void prepareTransaction(E preparedExtendedState) {
        this.setState(preparedExtendedState.getState());
        this.preparedState = (ItemInstance) preparedExtendedState;
    }

    public boolean needsOpticalScopePerspective() {
        return false;
    }

    static {
        TypeRegistry.getInstance()
            .register(ItemInstance.class);
        TypeRegistry.getInstance()
            .register(ItemWeaponInstance.class);
    }
}
