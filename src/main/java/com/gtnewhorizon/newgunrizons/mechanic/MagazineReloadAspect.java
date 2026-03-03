package com.gtnewhorizon.newgunrizons.mechanic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.Permit;
import com.gtnewhorizon.newgunrizons.state.PermitManager;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import com.gtnewhorizon.newgunrizons.weapon.ItemBullet;
import com.gtnewhorizon.newgunrizons.weapon.ItemMagazine;
import com.gtnewhorizon.newgunrizons.weapon.MagazineState;
import com.gtnewhorizon.newgunrizons.weapon.PlayerMagazineInstance;

public class MagazineReloadAspect implements Aspect<MagazineState, PlayerMagazineInstance> {

    private static final Set<MagazineState> allowedUpdateFromStates;
    private static final long reloadAnimationDuration;
    private static final Predicate<PlayerMagazineInstance> reloadAnimationCompleted;
    private final ModContext modContext;
    private PermitManager permitManager;
    private StateManager<MagazineState, ? super PlayerMagazineInstance> stateManager;
    private final Predicate<PlayerMagazineInstance> notFull = (instance) -> Tags.getAmmo(instance.getItemStack())
        < instance.getMagazine()
            .getAmmo();

    public MagazineReloadAspect(ModContext modContext) {
        this.modContext = modContext;
    }

    public void setStateManager(StateManager<MagazineState, ? super PlayerMagazineInstance> stateManager) {
        if (this.permitManager == null) {
            throw new IllegalStateException("Permit manager not initialized");
        } else {
            this.stateManager = stateManager.in(this)
                .change(MagazineState.READY)
                .to(MagazineState.LOAD)
                .when(this.notFull)
                .withPermit(
                    (s, es) -> new LoadPermit(s),
                    this.modContext.getPlayerItemInstanceRegistry()::update,
                    this.permitManager)
                .withAction((c, f, t, p) -> this.doPermittedLoad(c, (LoadPermit) p))
                .manual()
                .in(this)
                .change(MagazineState.LOAD)
                .to(MagazineState.READY)
                .when(reloadAnimationCompleted)
                .automatic();
        }
    }

    public void setPermitManager(PermitManager permitManager) {
        this.permitManager = permitManager;
        permitManager
            .registerEvaluator(MagazineReloadAspect.LoadPermit.class, PlayerMagazineInstance.class, this::evaluateLoad);
    }

    public void reloadMainHeldItem(EntityPlayer player) {
        PlayerMagazineInstance instance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerMagazineInstance.class);
        this.stateManager.changeState(this, instance, MagazineState.LOAD);
    }

    public void updateMainHeldItem(EntityPlayer player) {
        PlayerMagazineInstance instance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerMagazineInstance.class);
        if (instance != null) {
            this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates);
        }

    }

    private void evaluateLoad(MagazineReloadAspect.LoadPermit p, PlayerMagazineInstance magazineInstance) {
        if (magazineInstance.getPlayer() instanceof EntityPlayer) {
            ItemStack magazineStack = magazineInstance.getItemStack();
            Permit.Status status = Permit.Status.DENIED;
            if (magazineStack.getItem() instanceof ItemMagazine magazine) {
                List<ItemBullet> compatibleBullets = magazine.getCompatibleBullets();
                int currentAmmo = Tags.getAmmo(magazineStack);
                ItemStack consumedStack;
                if ((consumedStack = InventoryUtils.tryConsumingCompatibleItem(
                    compatibleBullets,
                    magazine.getAmmo() - currentAmmo,
                    (EntityPlayer) magazineInstance.getPlayer(),
                    (i) -> true)) != null) {
                    Tags.setAmmo(magazineStack, Tags.getAmmo(magazineStack) + consumedStack.stackSize);
                    if (magazine.getReloadSound() != null) {
                        magazineInstance.getPlayer()
                            .playSound(magazine.getReloadSound(), 1.0F, 1.0F);
                    }

                    status = Permit.Status.GRANTED;
                }
            }

            p.setStatus(status);
        }
    }

    private void doPermittedLoad(PlayerMagazineInstance weaponInstance, MagazineReloadAspect.LoadPermit permit) {
        if (permit == null) {
            System.err.println("Permit is null, something went wrong");
        }
    }

    static {
        TypeRegistry.getInstance()
            .register(MagazineReloadAspect.LoadPermit.class);
        allowedUpdateFromStates = new HashSet<>(Arrays.asList(MagazineState.LOAD_REQUESTED, MagazineState.LOAD));
        reloadAnimationDuration = 1000L;
        reloadAnimationCompleted = (es) -> System.currentTimeMillis()
            >= es.getStateUpdateTimestamp() + reloadAnimationDuration;
    }

    public static class LoadPermit extends Permit<MagazineState> {

        public LoadPermit() {}

        public LoadPermit(MagazineState state) {
            super(state);
        }
    }
}
