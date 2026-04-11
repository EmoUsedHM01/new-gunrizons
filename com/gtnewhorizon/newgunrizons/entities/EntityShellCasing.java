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
      this.func_70105_a(0.001F, 0.001F);
      float forwardOffset = this.weapon.getShellCasingForwardOffset();
      float sideOffset = this.weaponInstance.isAimed() ? 0.05F : 0.15F;
      float yOffset = this.weapon.getShellCasingVerticalOffset() + (this.thrower.func_70093_af() ? -0.1F : 0.0F);
      this.func_70012_b(
         this.thrower.field_70165_t,
         this.thrower.field_70163_u + this.thrower.func_70047_e() + yOffset,
         this.thrower.field_70161_v,
         this.thrower.field_70177_z,
         this.thrower.field_70125_A
      );
      this.field_70165_t = this.field_70165_t
         - (
            (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float) Math.PI) * sideOffset)
               + MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float) Math.PI)
                  * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI)
                  * forwardOffset
         );
      this.field_70163_u = this.field_70163_u + -MathHelper.func_76126_a(this.field_70125_A / 180.0F * (float) Math.PI) * forwardOffset;
      this.field_70161_v = this.field_70161_v
         - (
            (double)(MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float) Math.PI) * sideOffset)
               - MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float) Math.PI)
                  * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI)
                  * forwardOffset
         );
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      float f = this.velocity;
      float adjustedRotationYaw = this.field_70177_z + (this.weaponInstance.isAimed() ? -10.0F : -30.0F);
      this.field_70159_w = -(MathHelper.func_76134_b(adjustedRotationYaw / 180.0F * (float) Math.PI) * f);
      this.field_70179_y = -MathHelper.func_76126_a(adjustedRotationYaw / 180.0F * (float) Math.PI) * f;
      this.field_70181_x = 0.0;
      this.initialYaw = this.field_70177_z;
      this.initialPitch = this.field_70125_A;
      this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.velocity, this.inaccuracy);
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
      if (!this.field_70170_p.field_72995_K) {
         this.func_70106_y();
      }
   }

   @Override
   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeInt(Item.func_150891_b(this.weapon));
      buffer.writeFloat(this.initialYaw);
      buffer.writeFloat(this.initialPitch);
   }

   @Override
   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.weapon = (ItemWeapon)Item.func_150899_d(buffer.readInt());
      this.initialYaw = buffer.readFloat();
      this.initialPitch = buffer.readFloat();
   }

   @Override
   public void func_70037_a(NBTTagCompound tagCompound) {
      super.func_70037_a(tagCompound);
      Item item = Item.func_150899_d(tagCompound.func_74762_e("entity_item"));
      if (item instanceof ItemWeapon) {
         this.weapon = (ItemWeapon)item;
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound tagCompound) {
      super.func_70014_b(tagCompound);
      tagCompound.func_74768_a("entity_item", Item.func_150891_b(this.weapon));
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

   public void func_70106_y() {
      super.func_70106_y();
   }
}
