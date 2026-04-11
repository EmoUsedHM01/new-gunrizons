package com.gtnewhorizon.newgunrizons.items.instances;

import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.network.UniversalObject;
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemInstance<S extends ManagedState<S>> extends UniversalObject {
   private static final Logger logger = LogManager.getLogger(ItemInstance.class);
   private static final String AMMO_TAG = "Ammo";
   private static final String INSTANCE_TAG = "Instance";
   protected S state;
   protected long stateUpdateTimestamp = System.currentTimeMillis();
   protected EntityLivingBase player;
   protected Item item;
   protected int itemInventoryIndex;
   private ItemInstance<S> preparedState;

   public ItemInstance() {
   }

   public ItemInstance(int itemInventoryIndex, EntityLivingBase player) {
      this.itemInventoryIndex = itemInventoryIndex;
      this.player = player;
      ItemStack itemStack = player.func_70694_bm();
      if (itemStack != null) {
         this.item = itemStack.func_77973_b();
      }
   }

   public ItemInstance(int itemInventoryIndex, EntityLivingBase player, ItemStack itemStack) {
      this.itemInventoryIndex = itemInventoryIndex;
      this.player = player;
      if (itemStack != null) {
         this.item = itemStack.func_77973_b();
      }
   }

   public ItemStack getItemStack() {
      return this.player instanceof EntityPlayer ? ((EntityPlayer)this.player).field_71071_by.func_70301_a(this.itemInventoryIndex) : null;
   }

   protected void setItemInventoryIndex(int itemInventoryIndex) {
      this.itemInventoryIndex = itemInventoryIndex;
   }

   @Override
   public void init(ByteBuf buf) {
      super.init(buf);
      this.item = Item.func_150899_d(buf.readInt());
      this.itemInventoryIndex = buf.readInt();
      this.state = TypeRegistry.getInstance().fromBytes(buf);
   }

   @Override
   public void serialize(ByteBuf buf) {
      super.serialize(buf);
      buf.writeInt(Item.func_150891_b(this.item));
      buf.writeInt(this.itemInventoryIndex);
      TypeRegistry.getInstance().toBytes(this.state, buf);
   }

   public boolean setState(S state) {
      this.state = state;
      this.stateUpdateTimestamp = System.currentTimeMillis();
      if (this.preparedState != null) {
         if (this.preparedState.getState().getCommitPhase() == state) {
            logger.debug("Committing state {} to {}", new Object[]{this.preparedState.getState(), this.preparedState.getState().getCommitPhase()});
            this.updateWith(this.preparedState, false);
         } else {
            this.rollback();
         }

         this.preparedState = null;
      }

      return false;
   }

   protected void rollback() {
   }

   protected void updateWith(ItemInstance<S> otherState, boolean updateManagedState) {
      if (updateManagedState) {
         this.setState(otherState.getState());
      }
   }

   public boolean needsOpticalScopePerspective() {
      return false;
   }

   public static int getAmmo(ItemStack itemStack) {
      return itemStack != null && itemStack.field_77990_d != null ? itemStack.field_77990_d.func_74762_e("Ammo") : 0;
   }

   public static void setAmmo(ItemStack itemStack, int ammo) {
      if (itemStack != null) {
         if (itemStack.field_77990_d == null) {
            itemStack.field_77990_d = new NBTTagCompound();
         }

         itemStack.field_77990_d.func_74768_a("Ammo", ammo);
      }
   }

   public static ItemInstance<?> fromStack(ItemStack itemStack) {
      return deserializeInstance(itemStack);
   }

   public static <T extends ItemInstance<?>> T fromStack(ItemStack itemStack, Class<T> targetClass) {
      try {
         return targetClass.cast(deserializeInstance(itemStack));
      } catch (RuntimeException var3) {
         return null;
      }
   }

   public static void toStack(ItemStack itemStack, ItemInstance<?> instance) {
      if (itemStack != null) {
         if (itemStack.field_77990_d == null) {
            itemStack.field_77990_d = new NBTTagCompound();
         }

         if (instance != null) {
            ByteBuf buf = Unpooled.buffer();

            try {
               TypeRegistry.getInstance().toBytes(instance, buf);
               byte[] bytes = new byte[buf.readableBytes()];
               buf.readBytes(bytes);
               itemStack.field_77990_d.func_74773_a("Instance", bytes);
            } finally {
               buf.release();
            }
         } else {
            itemStack.field_77990_d.func_82580_o("Instance");
         }
      }
   }

   private static ItemInstance<?> deserializeInstance(ItemStack itemStack) {
      if (itemStack != null && itemStack.field_77990_d != null) {
         byte[] bytes = itemStack.field_77990_d.func_74770_j("Instance");
         if (bytes != null && bytes.length != 0) {
            ByteBuf buf = Unpooled.copiedBuffer(bytes);

            ItemInstance var3;
            try {
               var3 = TypeRegistry.getInstance().fromBytes(buf);
            } finally {
               buf.release();
            }

            return var3;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public S getState() {
      return this.state;
   }

   public long getStateUpdateTimestamp() {
      return this.stateUpdateTimestamp;
   }

   public void setPlayer(EntityLivingBase player) {
      this.player = player;
   }

   public EntityLivingBase getPlayer() {
      return this.player;
   }

   public Item getItem() {
      return this.item;
   }

   public int getItemInventoryIndex() {
      return this.itemInventoryIndex;
   }

   static {
      TypeRegistry.getInstance().register(ItemInstance.class);
      TypeRegistry.getInstance().register(ItemWeaponInstance.class);
   }
}
