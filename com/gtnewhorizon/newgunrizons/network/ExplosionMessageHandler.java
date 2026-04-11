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
         EntityPlayer player = Minecraft.func_71410_x().field_71439_g;
         Explosion explosion = new Explosion(
            player.field_70170_p, null, message.getPosX(), message.getPosY(), message.getPosZ(), message.getStrength(), message.getAffectedBlockPositions()
         );
         explosion.doExplosionB(true);
         player.field_70159_w = player.field_70159_w + message.getMotionX();
         player.field_70181_x = player.field_70181_x + message.getMotionY();
         player.field_70179_y = player.field_70179_y + message.getMotionZ();
      }

      return null;
   }
}
