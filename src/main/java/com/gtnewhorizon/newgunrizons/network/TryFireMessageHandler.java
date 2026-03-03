package com.gtnewhorizon.newgunrizons.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.mechanic.WeaponFireAspect;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class TryFireMessageHandler implements IMessageHandler<TryFireMessage, IMessage> {

    private final WeaponFireAspect fireManager;

    public TryFireMessageHandler(WeaponFireAspect fireManager) {
        this.fireManager = fireManager;
    }

    public IMessage onMessage(TryFireMessage message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack itemStack = player.getHeldItem();
            if (itemStack != null && itemStack.getItem() instanceof Weapon && message.isOn()) {
                this.fireManager.serverFire(player, itemStack);
            }
        }
        return null;
    }
}
