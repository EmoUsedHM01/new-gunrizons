package com.gtnewhorizon.newgunrizons.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class WeaponActionMessage implements IMessage {
   public static final byte WEAPON_LOAD = 0;
   public static final byte WEAPON_UNLOAD = 1;
   public static final byte MAGAZINE_LOAD = 2;
   public static final byte CHANGE_ATTACHMENT = 3;
   public static final byte CHANGE_FIRE_MODE = 4;
   public static final byte TOGGLE_LASER = 5;
   public static final byte ZOOM_IN = 6;
   public static final byte ZOOM_OUT = 7;
   public static final byte FIRE = 8;
   private byte actionType;
   private int slotIndex;
   private byte attachmentCategory;

   public WeaponActionMessage() {
   }

   public WeaponActionMessage(byte actionType, int slotIndex) {
      this(actionType, slotIndex, (byte)-1);
   }

   public WeaponActionMessage(byte actionType, int slotIndex, byte attachmentCategory) {
      this.actionType = actionType;
      this.slotIndex = slotIndex;
      this.attachmentCategory = attachmentCategory;
   }

   public void fromBytes(ByteBuf buf) {
      this.actionType = buf.readByte();
      this.slotIndex = buf.readInt();
      this.attachmentCategory = buf.readByte();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeByte(this.actionType);
      buf.writeInt(this.slotIndex);
      buf.writeByte(this.attachmentCategory);
   }

   public byte getActionType() {
      return this.actionType;
   }

   public int getSlotIndex() {
      return this.slotIndex;
   }

   public byte getAttachmentCategory() {
      return this.attachmentCategory;
   }
}
