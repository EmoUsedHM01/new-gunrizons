package com.gtnewhorizon.newgunrizons.weapon;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemMagazineInstance;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;

public class MagazineReloadAspect implements Aspect<MagazineState, ItemMagazineInstance> {
   private static final Set<MagazineState> allowedUpdateFromStates = new HashSet<>(Arrays.asList(MagazineState.LOAD));
   private static final long reloadAnimationDuration = 1000L;
   private static final Predicate<ItemMagazineInstance> reloadAnimationCompleted = es -> System.currentTimeMillis()
      >= es.getStateUpdateTimestamp() + reloadAnimationDuration;
   public static final MagazineReloadAspect INSTANCE = new MagazineReloadAspect();
   private StateManager<MagazineState, ? super ItemMagazineInstance> stateManager;
   private final Predicate<ItemMagazineInstance> notFull = instance -> ItemInstance.getAmmo(instance.getItemStack()) < instance.getMagazine().getAmmo();
   private final Predicate<ItemMagazineInstance> hasCompatibleBullets = instance -> {
      if (!(instance.getPlayer() instanceof EntityPlayer)) {
         return false;
      } else {
         EntityPlayer player = (EntityPlayer)instance.getPlayer();
         if (!(instance.getItemStack().func_77973_b() instanceof ItemMagazine)) {
            return false;
         } else {
            ItemMagazine magazine = (ItemMagazine)instance.getItemStack().func_77973_b();
            List<ItemBullet> compatibleBullets = magazine.getCompatibleBullets();
            return InventoryUtils.hasCompatibleItem(compatibleBullets, player, s -> true);
         }
      }
   };

   @Override
   public void setStateManager(StateManager<MagazineState, ? super ItemMagazineInstance> stateManager) {
      this.stateManager = stateManager.in(this)
         .change(MagazineState.READY)
         .to(MagazineState.LOAD)
         .when(this.notFull.and(this.hasCompatibleBullets))
         .withAction(this::performLoad)
         .manual()
         .in(this)
         .change(MagazineState.LOAD)
         .to(MagazineState.READY)
         .when(reloadAnimationCompleted)
         .automatic();
   }

   public void reloadHeldItem(EntityPlayer player) {
      ItemMagazineInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemMagazineInstance.class);
      this.stateManager.changeState(this, instance, MagazineState.LOAD);
   }

   public void updateHeldItem(EntityPlayer player) {
      ItemMagazineInstance instance = ItemInstanceRegistry.INSTANCE.getMainHandItemInstance(player, ItemMagazineInstance.class);
      if (instance != null) {
         this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates);
      }
   }

   private void performLoad(ItemMagazineInstance magazineInstance) {
      NewGunrizonsMod.CHANNEL.sendToServer(new WeaponActionMessage((byte)2, magazineInstance.getItemInventoryIndex()));
   }
}
