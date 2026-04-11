package com.gtnewhorizon.newgunrizons.entities;

import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShellCasing extends EntityProjectile {
   private static final String TAG_ENTITY_ITEM = "entity_item";
   private static final float ROTATION_SLOWDOWN_FACTOR = 0.95F;
   private static final float ROTATION_MAX_CHANGE = 30.0F;
   private static final float SHELL_OFFSET = 0.15F;
   private static final float SHELL_OFFSET_AIMED = 0.05F;
   private ItemWeapon weapon;
   private ItemWeaponInstance weaponInstance;
   private float initialYaw;
   private float initialPitch;
   private float xRotation;
   private float yRotation;
   private float zRotation;
   private float xRotationChange;
   private float yRotationChange;
   private float zRotationChange;

   public EntityShellCasing(World world) {
      super(world);
      Random random = new Random();
      this.xRotationChange = 30.0F * (float)random.nextGaussian();
      this.yRotationChange = 30.0F * (float)random.nextGaussian();
      this.zRotationChange = 30.0F * (float)random.nextGaussian();
   }

   public EntityShellCasing(ItemWeaponInstance weaponInstance, World world, EntityLivingBase player, float velocity, float gravityVelocity, float inaccuracy) {
      super(world, player, velocity, gravityVelocity, inaccuracy);
      this.weapon = weaponInstance.getWeapon();
      this.weaponInstance = weaponInstance;
   }

   @Override
   public void setPositionAndDirection() {
      this.setSize(0.001F, 0.001F);
      float forwardOffset = this.weapon.getShellCasingForwardOffset();
      float sideOffset = this.weaponInstance.isAimed() ? 0.05F : 0.15F;
      float yOffset = this.weapon.getShellCasingVerticalOffset() + (this.thrower.isSneaking() ? -0.1F : 0.0F);
      this.setLocationAndAngles(
         this.thrower.posX,
         this.thrower.posY + this.thrower.getEyeHeight() + yOffset,
         this.thrower.posZ,
         this.thrower.rotationYaw,
         this.thrower.rotationPitch
      );
      this.posX = this.posX
         - (
            (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * sideOffset)
               + MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI)
                  * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)
                  * forwardOffset
         );
      this.posY = this.posY + -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI) * forwardOffset;
      this.posZ = this.posZ
         - (
            (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * sideOffset)
               - MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI)
                  * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)
                  * forwardOffset
         );
      this.setPosition(this.posX, this.posY, this.posZ);
      float f = this.velocity;
      float adjustedRotationYaw = this.rotationYaw + (this.weaponInstance.isAimed() ? -10.0F : -30.0F);
      this.motionX = -(MathHelper.cos(adjustedRotationYaw / 180.0F * (float) Math.PI) * f);
      this.motionZ = -MathHelper.sin(adjustedRotationYaw / 180.0F * (float) Math.PI) * f;
      this.motionY = 0.0;
      this.initialYaw = this.rotationYaw;
      this.initialPitch = this.rotationPitch;
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.velocity, this.inaccuracy);
   }

   @Override
   public void func_70071_h_() {
      super.func_70071_h_();
      this.xRotation = this.xRotation + this.xRotationChange;
      this.yRotation = this.yRotation + this.yRotationChange;
      this.zRotation = this.zRotation + this.zRotationChange;
      this.xRotationChange *= 0.95F;
      this.yRotationChange *= 0.95F;
      this.zRotationChange *= 0.95F;
   }

   @Override
   protected void onImpact(MovingObjectPosition position) {
      if (!this.worldObj.isRemote) {
         this.setDead();
      }
   }

   @Override
   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeInt(Item.getIdFromItem(this.weapon));
      buffer.writeFloat(this.initialYaw);
      buffer.writeFloat(this.initialPitch);
   }

   @Override
   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.weapon = (ItemWeapon)Item.getItemById(buffer.readInt());
      this.initialYaw = buffer.readFloat();
      this.initialPitch = buffer.readFloat();
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      Item item = Item.getItemById(tagCompound.getInteger("entity_item"));
      if (item instanceof ItemWeapon) {
         this.weapon = (ItemWeapon)item;
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("entity_item", Item.getIdFromItem(this.weapon));
   }

   public ItemWeapon getWeapon() {
      return this.weapon;
   }

   public float getXRotation() {
      return this.initialPitch - this.xRotation;
   }

   public float getYRotation() {
      return this.yRotation - this.initialYaw - 90.0F;
   }

   public float getZRotation() {
      return this.zRotation;
   }

   public void setDead() {
      super.setDead();
   }
}
