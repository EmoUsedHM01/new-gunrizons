package com.gtnewhorizon.newgunrizons.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;

public class BlockHitMessageHandler implements IMessageHandler<BlockHitMessage, IMessage> {
   public IMessage onMessage(BlockHitMessage message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT) {
         for (int i = 0; i < 6; i++) {
            Minecraft.getMinecraft().effectRenderer.addBlockHitEffects(message.getPosX(), message.getPosY(), message.getPosZ(), message.getSideHit());
         }
      }

      return null;
   }
}
