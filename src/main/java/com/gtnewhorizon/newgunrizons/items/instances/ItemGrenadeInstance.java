package com.gtnewhorizon.newgunrizons.items.instances;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.grenade.GrenadeState;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.state.Stateful;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

public class ItemGrenadeInstance extends ItemInstance implements Stateful<GrenadeState> {

    @Getter
    private GrenadeState state = GrenadeState.IDLE;
    @Getter
    private long stateUpdateTimestamp = System.currentTimeMillis();
    @Getter
    private int ammo;
    @Setter
    @Getter
    private long activationTimestamp;
    @Setter
    @Getter
    private long lastSafetyPinAlertTimestamp;
    @Getter
    @Setter
    private boolean throwingFar;

    public ItemGrenadeInstance() {}

    public ItemGrenadeInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
        super(itemInventoryIndex, player, itemStack);
    }

    public void setState(GrenadeState state) {
        this.state = state;
        this.stateUpdateTimestamp = System.currentTimeMillis();
    }

    public void readFromBuf(ByteBuf buf) {
        super.readFromBuf(buf);
        this.state = GrenadeState.values()[buf.readInt()];
        this.ammo = buf.readInt();
    }

    public void writeToBuf(ByteBuf buf) {
        super.writeToBuf(buf);
        buf.writeInt(this.state != null ? this.state.ordinal() : 0);
        buf.writeInt(this.ammo);
    }

    protected void setAmmo(int ammo) {
        if (ammo != this.ammo) {
            this.ammo = ammo;
        }
    }

    public ItemGrenade getWeapon() {
        return (ItemGrenade) this.item;
    }

    public String toString() {
        return this.getWeapon().getName() + "[grenade]";
    }
}
