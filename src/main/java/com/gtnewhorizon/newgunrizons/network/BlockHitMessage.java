package com.gtnewhorizon.newgunrizons.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class BlockHitMessage implements IMessage {
   private int posX;
   private int posY;
   private int posZ;
   private int sideHit;

   public BlockHitMessage() {
   }

   public BlockHitMessage(int posX, int posY, int posZ, int sideHit) {
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      this.sideHit = sideHit;
   }

   public void fromBytes(ByteBuf buf) {
      this.posX = buf.readInt();
      this.posY = buf.readInt();
      this.posZ = buf.readInt();
      this.sideHit = buf.readInt();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.posX);
      buf.writeInt(this.posY);
      buf.writeInt(this.posZ);
      buf.writeInt(this.sideHit);
   }

   public int getPosX() {
      return this.posX;
   }

   public int getPosY() {
      return this.posY;
   }

   public int getPosZ() {
      return this.posZ;
   }

   public int getSideHit() {
      return this.sideHit;
   }
}
