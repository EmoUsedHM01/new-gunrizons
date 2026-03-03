package com.gtnewhorizon.newgunrizons.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.entity.Explosion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class ExplosionMessageHandler implements IMessageHandler<ExplosionMessage, IMessage> {

    private final ModContext modContext;

    public ExplosionMessageHandler(ModContext modContext) {
        this.modContext = modContext;
    }

    public IMessage onMessage(ExplosionMessage message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            {
                Explosion explosion = new Explosion(
                    this.modContext,
                    player.worldObj,
                    null,
                    message.getPosX(),
                    message.getPosY(),
                    message.getPosZ(),
                    message.getStrength(),
                    message.getAffectedBlockPositions());
                explosion.doExplosionB(true);
                player.motionX += message.getMotionX();
                player.motionY += message.getMotionY();
                player.motionZ += message.getMotionZ();
            }
        }

        return null;
    }
}
