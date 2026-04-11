package com.gtnewhorizon.newgunrizons.network;

import com.gtnewhorizon.newgunrizons.entities.Explosion;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ExplosionMessageHandler implements IMessageHandler<ExplosionMessage, IMessage> {
   public IMessage onMessage(ExplosionMessage message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT) {
         EntityPlayer player = Minecraft.getMinecraft().thePlayer;
         Explosion explosion = new Explosion(
            player.worldObj, null, message.getPosX(), message.getPosY(), message.getPosZ(), message.getStrength(), message.getAffectedBlockPositions()
         );
         explosion.doExplosionB(true);
         player.motionX = player.motionX + message.getMotionX();
         player.motionY = player.motionY + message.getMotionY();
         player.motionZ = player.motionZ + message.getMotionZ();
      }

      return null;
   }
}
