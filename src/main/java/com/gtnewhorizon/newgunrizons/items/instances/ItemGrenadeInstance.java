package com.gtnewhorizon.newgunrizons.items.instances;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeState;
import com.gtnewhorizon.newgunrizons.grenade.GrenadeStateTimed;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGrenadeInstance extends ItemInstance<GrenadeState> {
   private static final int SERIAL_VERSION = 12;
   private int ammo;
   private long activationTimestamp;
   private final Deque<GrenadeStateTimed> filteredStateQueue = new LinkedBlockingDeque<>();
   private int[] activeAttachmentIds = new int[0];
   private byte[] selectedAttachmentIndexes = new byte[0];
   private long lastSafetyPinAlertTimestamp;
   private boolean throwingFar;

   public ItemGrenadeInstance() {
   }

   public ItemGrenadeInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
      super(itemInventoryIndex, player, itemStack);
   }

   @Override
   protected int getSerialVersion() {
      return 12;
   }

   private void addStateToHistory(GrenadeState state) {
      GrenadeStateTimed t;
      while ((t = this.filteredStateQueue.peekFirst()) != null && t.getState().getPriority() < state.getPriority()) {
         this.filteredStateQueue.pollFirst();
      }

      long expirationTimeout = 500L;
      this.filteredStateQueue.addFirst(new GrenadeStateTimed(state, this.stateUpdateTimestamp, expirationTimeout));
   }

   public boolean setState(GrenadeState state) {
      boolean result = super.setState(state);
      this.addStateToHistory(state);
      return result;
   }

   public GrenadeStateTimed nextHistoryState() {
      GrenadeStateTimed result = this.filteredStateQueue.pollLast();
      if (result == null) {
         result = new GrenadeStateTimed(this.getState(), this.stateUpdateTimestamp);
      }

      return result;
   }

   protected void setAmmo(int ammo) {
      if (ammo != this.ammo) {
         this.ammo = ammo;
      }
   }

   @Override
   public void init(ByteBuf buf) {
      super.init(buf);
      this.activeAttachmentIds = initIntArray(buf);
      this.selectedAttachmentIndexes = initByteArray(buf);
   }

   @Override
   public void serialize(ByteBuf buf) {
      super.serialize(buf);
      serializeIntArray(buf, this.activeAttachmentIds);
      serializeByteArray(buf, this.selectedAttachmentIndexes);
   }

   private static void serializeIntArray(ByteBuf buf, int[] a) {
      buf.writeByte(a.length);

      for (int b : a) {
         buf.writeInt(b);
      }
   }

   private static void serializeByteArray(ByteBuf buf, byte[] a) {
      buf.writeByte(a.length);

      for (byte b : a) {
         buf.writeByte(b);
      }
   }

   private static int[] initIntArray(ByteBuf buf) {
      int length = buf.readByte();
      int[] a = new int[length];

      for (int i = 0; i < length; i++) {
         a[i] = buf.readInt();
      }

      return a;
   }

   private static byte[] initByteArray(ByteBuf buf) {
      int length = buf.readByte();
      byte[] a = new byte[length];

      for (int i = 0; i < length; i++) {
         a[i] = buf.readByte();
      }

      return a;
   }

   @Override
   protected void updateWith(ItemInstance<GrenadeState> otherItemInstance, boolean updateManagedState) {
      super.updateWith(otherItemInstance, updateManagedState);
      ItemGrenadeInstance otherWeaponInstance = (ItemGrenadeInstance)otherItemInstance;
      this.setAmmo(otherWeaponInstance.ammo);
      this.setSelectedAttachmentIndexes(otherWeaponInstance.selectedAttachmentIndexes);
      this.setActiveAttachmentIds(otherWeaponInstance.activeAttachmentIds);
   }

   public ItemGrenade getWeapon() {
      return (ItemGrenade)this.item;
   }

   public int[] getActiveAttachmentIds() {
      if (this.activeAttachmentIds == null || this.activeAttachmentIds.length != AttachmentCategory.VALUES.length) {
         this.activeAttachmentIds = new int[AttachmentCategory.VALUES.length];

         for (CompatibleAttachment attachment : this.getWeapon().getCompatibleAttachments().values()) {
            if (attachment.isDefault()) {
               this.activeAttachmentIds[attachment.getAttachment().getCategory().ordinal()] = Item.getIdFromItem(attachment.getAttachment());
            }
         }
      }

      return this.activeAttachmentIds;
   }

   void setActiveAttachmentIds(int[] activeAttachmentIds) {
      if (!Arrays.equals(this.activeAttachmentIds, activeAttachmentIds)) {
         this.activeAttachmentIds = activeAttachmentIds;
      }
   }

   void setSelectedAttachmentIndexes(byte[] selectedAttachmentIndexes) {
      if (!Arrays.equals(this.selectedAttachmentIndexes, selectedAttachmentIndexes)) {
         this.selectedAttachmentIndexes = selectedAttachmentIndexes;
      }
   }

   public List<CompatibleAttachment> getActiveAttachments() {
      int[] activeIds = this.getActiveAttachmentIds();
      List<CompatibleAttachment> result = new ArrayList<>();

      for (int activeId : activeIds) {
         if (activeId != 0) {
            Item item = Item.getItemById(activeId);
            if (item instanceof ItemAttachment) {
               CompatibleAttachment compatibleAttachment = this.getWeapon().getCompatibleAttachments().get(item);
               if (compatibleAttachment != null) {
                  result.add(compatibleAttachment);
               }
            }
         }
      }

      return result;
   }

   @Override
   public String toString() {
      return this.getWeapon().getName() + "[" + this.getUuid() + "]";
   }

   public int getAmmo() {
      return this.ammo;
   }

   public void setActivationTimestamp(long activationTimestamp) {
      this.activationTimestamp = activationTimestamp;
   }

   public long getActivationTimestamp() {
      return this.activationTimestamp;
   }

   public void setLastSafetyPinAlertTimestamp(long lastSafetyPinAlertTimestamp) {
      this.lastSafetyPinAlertTimestamp = lastSafetyPinAlertTimestamp;
   }

   public long getLastSafetyPinAlertTimestamp() {
      return this.lastSafetyPinAlertTimestamp;
   }

   public boolean isThrowingFar() {
      return this.throwingFar;
   }

   public void setThrowingFar(boolean throwingFar) {
      this.throwingFar = throwingFar;
   }

   static {
      TypeRegistry.getInstance().register(ItemGrenadeInstance.class);
   }
}
