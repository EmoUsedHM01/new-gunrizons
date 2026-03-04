package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;

import io.netty.buffer.ByteBuf;

public class PlayerMagazineInstance extends PlayerItemInstance<MagazineState> {

    public PlayerMagazineInstance() {}

    public PlayerMagazineInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        super(itemInventoryIndex, player, itemStack);
    }

    public PlayerMagazineInstance(int itemInventoryIndex, EntityLivingBase player) {
        super(itemInventoryIndex, player);
    }

    public void init(ByteBuf buf) {
        super.init(buf);
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
    }

    protected void updateWith(PlayerItemInstance<MagazineState> otherItemInstance, boolean updateManagedState) {
        super.updateWith(otherItemInstance, updateManagedState);
    }

    public ItemMagazine getMagazine() {
        return (ItemMagazine) this.item;
    }

    static {
        TypeRegistry.getInstance()
            .register(PlayerMagazineInstance.class);
    }
}
