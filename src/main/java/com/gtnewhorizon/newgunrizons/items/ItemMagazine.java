package com.gtnewhorizon.newgunrizons.items;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceFactory;
import com.gtnewhorizon.newgunrizons.items.instances.ItemMagazineInstance;
import com.gtnewhorizon.newgunrizons.registry.Sounds;
import com.gtnewhorizon.newgunrizons.weapon.MagazineReloadAspect;
import com.gtnewhorizon.newgunrizons.weapon.MagazineState;
import com.gtnewhorizon.newgunrizons.weapon.Reloadable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemMagazine extends ItemAttachment implements ItemInstanceFactory<ItemMagazineInstance, MagazineState>, Reloadable, Updatable, Part {
   private final int ammo;
   private List<ItemBullet> compatibleBullets;
   private String reloadSound;

   ItemMagazine(int ammo) {
      super(AttachmentCategory.MAGAZINE, null);
      this.ammo = ammo;
      this.setMaxStackSize(1);
   }

   public ItemStack createItemStack() {
      ItemStack attachmentItemStack = new ItemStack(this);
      this.ensureItemStack(attachmentItemStack, this.ammo);
      return attachmentItemStack;
   }

   private void ensureItemStack(ItemStack itemStack, int initialAmmo) {
      if (itemStack.stackTagCompound == null) {
         itemStack.stackTagCompound = new NBTTagCompound();
         ItemInstance.setAmmo(itemStack, initialAmmo);
      }
   }

   @Override
   public void onCreated(ItemStack stack, World world, EntityPlayer player) {
      this.ensureItemStack(stack, 0);
      super.onCreated(stack, world, player);
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
      this.ensureItemStack(stack, this.ammo);
      super.onUpdate(stack, world, entity, slot, isSelected);
   }

   public boolean showDurabilityBar(ItemStack stack) {
      return true;
   }

   public double getDurabilityForDisplay(ItemStack stack) {
      return this.ammo <= 0 ? 0.0 : 1.0 - (double)ItemInstance.getAmmo(stack) / this.ammo;
   }

   @Override
   public Part getRenderablePart() {
      return this;
   }

   public ItemMagazineInstance createItemInstance(EntityLivingBase player, ItemStack itemStack, int slot) {
      ItemMagazineInstance instance = new ItemMagazineInstance(slot, player, itemStack);
      instance.setState(MagazineState.READY);
      return instance;
   }

   @Override
   public void update(EntityPlayer player) {
      MagazineReloadAspect.INSTANCE.updateHeldItem(player);
   }

   @Override
   public void reloadHeldItem(EntityPlayer player) {
      MagazineReloadAspect.INSTANCE.reloadHeldItem(player);
   }

   public int getAmmo() {
      return this.ammo;
   }

   public List<ItemBullet> getCompatibleBullets() {
      return this.compatibleBullets;
   }

   public String getReloadSound() {
      return this.reloadSound;
   }

   public static final class Builder extends AttachmentBuilder {
      private int ammo;
      private final Set<ItemBullet> compatibleBullets = new HashSet<>();
      private String reloadSound;

      public ItemMagazine.Builder withAmmo(int ammo) {
         this.ammo = ammo;
         return this;
      }

      public ItemMagazine.Builder withReloadSound(String reloadSound) {
         this.reloadSound = reloadSound;
         return this;
      }

      public ItemMagazine.Builder withCompatibleBullet(ItemBullet compatibleBullet) {
         this.compatibleBullets.add(compatibleBullet);
         return this;
      }

      @Override
      protected ItemAttachment createAttachment() {
         ItemMagazine magazine = new ItemMagazine(this.ammo);
         magazine.compatibleBullets = new ArrayList<>(this.compatibleBullets);
         if (this.reloadSound != null) {
            magazine.reloadSound = Sounds.resolve(this.reloadSound);
         }

         this.withInformationProvider(stack -> "Ammo: " + ItemInstance.getAmmo(stack) + "/" + this.ammo);
         return magazine;
      }
   }
}
