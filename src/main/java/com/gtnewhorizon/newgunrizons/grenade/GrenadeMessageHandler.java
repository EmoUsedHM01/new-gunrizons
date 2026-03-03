package com.gtnewhorizon.newgunrizons.grenade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class GrenadeMessageHandler implements IMessageHandler<GrenadeMessage, IMessage> {

    private final GrenadeAttackAspect attackAspect;

    public GrenadeMessageHandler(GrenadeAttackAspect attackAspect) {
        this.attackAspect = attackAspect;
    }

    public IMessage onMessage(GrenadeMessage message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack itemStack = player.getHeldItem();
            if (itemStack != null && itemStack.getItem() instanceof ItemGrenade) {
                message.getInstance()
                    .setPlayer(player);
                this.attackAspect.serverThrowGrenade(player, message.getInstance(), message.getActivationTimestamp());
            }
        }

        return null;
    }
}
