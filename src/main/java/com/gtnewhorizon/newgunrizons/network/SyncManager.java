package com.gtnewhorizon.newgunrizons.network;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import com.gtnewhorizon.newgunrizons.state.Permit;
import com.gtnewhorizon.newgunrizons.state.PermitManager;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;

public class SyncManager<S extends ManagedState<S>> {

    private static final Logger logger = LogManager.getLogger(SyncManager.class);
    private final PermitManager permitManager;
    private final Map<PlayerItemInstance<?>, Long> watchables = new LinkedHashMap<>();
    private final long syncTimeout = 10000L;

    public SyncManager(PermitManager permitManager) {
        this.permitManager = permitManager;
        this.permitManager.registerEvaluator(Permit.class, PlayerItemInstance.class, this::syncOnServer);
    }

    private void syncOnServer(Permit<S> permit, PlayerItemInstance<S> instance) {
        logger.debug("Syncing {} in state {} on server", instance, instance.getState());
        ItemStack itemStack = instance.getItemStack();
        if (itemStack != null) {
            if (instance.getItem() == itemStack.getItem()) {
                logger.debug("Stored instance {} of {} in stack {}", instance, instance.getItem(), itemStack);
                Tags.setInstance(itemStack, instance);
            } else {
                logger.debug(
                    "Item mismatch, expected: {}, actual: {}",
                    instance.getItem()
                        .getUnlocalizedName(),
                    itemStack.getItem()
                        .getUnlocalizedName());
            }
        }

    }

    public void watch(PlayerItemInstance<?> watchableInstance) {
        this.watchables.put(watchableInstance, watchableInstance.getUpdateId());
    }

    public void unwatch(PlayerItemInstance<?> watchableInstance) {
        this.watchables.remove(watchableInstance);
    }

    public void run() {
        List<PlayerItemInstance<?>> instancesToUpdate = (List) this.watchables.entrySet()
            .stream()
            .filter((e) -> {
                return e.getKey()
                    .getUpdateId() != e.getValue()
                    && e.getKey()
                        .getSyncStartTimestamp() + this.syncTimeout < System.currentTimeMillis();
            })
            .map((e) -> { return (PlayerItemInstance) e.getKey(); })
            .collect(Collectors.toList());
        instancesToUpdate.forEach(this::sync);
    }

    private void sync(PlayerItemInstance<?> watchable) {
        logger.debug(
            "Syncing {} in state {} with update id {} to server",
            watchable,
            watchable.getState(),
            watchable.getUpdateId());
        long updateId = watchable.getUpdateId();
        watchable.setSyncStartTimestamp(System.currentTimeMillis());
        this.permitManager.request(new Permit(watchable.getState()), watchable, (p, e) -> {
            this.watchables.put(watchable, updateId);
            watchable.setSyncStartTimestamp(0L);
            logger.debug("Completed syncing {} with update id {}", watchable, updateId);
        });
    }
}
