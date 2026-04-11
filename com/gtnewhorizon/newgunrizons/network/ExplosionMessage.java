package com.gtnewhorizon.newgunrizons.network;

import com.google.common.collect.Lists;
import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.Vec3;

public class ExplosionMessage implements IMessage {
   private double posX;
   private double posY;
   private double posZ;
   private float strength;
   private List<BlockPos> affectedBlockPositions;
   private float motionX;
   private float motionY;
   private float motionZ;

   public ExplosionMessage() {
   }

   public ExplosionMessage(double xIn, double yIn, double zIn, float strengthIn, List<BlockPos> affectedBlockPositionsIn, Vec3 motion) {
      this.posX = xIn;
      this.posY = yIn;
      this.posZ = zIn;
      this.strength = strengthIn;
      this.affectedBlockPositions = Lists.newArrayList(affectedBlockPositionsIn);
      if (motion != null) {
         this.motionX = (float)motion.field_72450_a;
         this.motionY = (float)motion.field_72448_b;
         this.motionZ = (float)motion.field_72449_c;
      }
   }

   public void fromBytes(ByteBuf buf) {
      this.posX = buf.readFloat();
      this.posY = buf.readFloat();
      this.posZ = buf.readFloat();
      this.strength = buf.readFloat();
      int i = buf.readInt();
      this.affectedBlockPositions = new ArrayList<>(i);
      int j = (int)this.posX;
      int k = (int)this.posY;
      int l = (int)this.posZ;

      for (int i1 = 0; i1 < i; i1++) {
         int j1 = buf.readByte() + j;
         int k1 = buf.readByte() + k;
         int l1 = buf.readByte() + l;
         this.affectedBlockPositions.add(new BlockPos(j1, k1, l1));
      }

      this.motionX = buf.readFloat();
      this.motionY = buf.readFloat();
      this.motionZ = buf.readFloat();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeFloat((float)this.posX);
      buf.writeFloat((float)this.posY);
      buf.writeFloat((float)this.posZ);
      buf.writeFloat(this.strength);
      buf.writeInt(this.affectedBlockPositions.size());
      int i = (int)this.posX;
      int j = (int)this.posY;
      int k = (int)this.posZ;

      for (BlockPos blockpos : this.affectedBlockPositions) {
         int l = blockpos.getX() - i;
         int i1 = blockpos.getY() - j;
         int j1 = blockpos.getZ() - k;
         buf.writeByte(l);
         buf.writeByte(i1);
         buf.writeByte(j1);
      }

      buf.writeFloat(this.motionX);
      buf.writeFloat(this.motionY);
      buf.writeFloat(this.motionZ);
   }

   public double getPosX() {
      return this.posX;
   }

   public double getPosY() {
      return this.posY;
   }

   public double getPosZ() {
      return this.posZ;
   }

   public float getStrength() {
      return this.strength;
   }

   public List<BlockPos> getAffectedBlockPositions() {
      return this.affectedBlockPositions;
   }

   public float getMotionX() {
      return this.motionX;
   }

   public float getMotionY() {
      return this.motionY;
   }

   public float getMotionZ() {
      return this.motionZ;
   }
}
