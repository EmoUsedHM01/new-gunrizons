package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.weapon.MagazineState;

import io.netty.buffer.ByteBuf;

public class ItemMagazineInstance extends ItemInstance<MagazineState> {

    public ItemMagazineInstance() {}

    public ItemMagazineInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        super(itemInventoryIndex, player, itemStack);
    }

    public ItemMagazineInstance(int itemInventoryIndex, EntityLivingBase player) {
        super(itemInventoryIndex, player);
    }

    public void init(ByteBuf buf) {
        super.init(buf);
    }

    public void serialize(ByteBuf buf) {
        super.serialize(buf);
    }

    protected void updateWith(ItemInstance<MagazineState> otherItemInstance, boolean updateManagedState) {
        super.updateWith(otherItemInstance, updateManagedState);
    }

    public ItemMagazine getMagazine() {
        return (ItemMagazine) this.item;
    }

    static {
        TypeRegistry.getInstance()
            .register(ItemMagazineInstance.class);
    }
}
