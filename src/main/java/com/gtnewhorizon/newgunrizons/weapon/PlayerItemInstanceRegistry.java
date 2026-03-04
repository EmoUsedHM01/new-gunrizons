package com.gtnewhorizon.newgunrizons.weapon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.gtnewhorizon.newgunrizons.config.Tags;
import com.gtnewhorizon.newgunrizons.network.SyncManager;
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;

public class PlayerItemInstanceRegistry {

    private static final int CACHE_EXPIRATION_TIMEOUT_SECONDS = 5;
    private static final Logger logger = LogManager.getLogger(PlayerItemInstanceRegistry.class);
    private final Map<UUID, Map<Integer, PlayerItemInstance<?>>> registry = new HashMap<>();
    private final SyncManager<?> syncManager;
    private final Cache<ItemStack, Optional<PlayerItemInstance<?>>> itemStackInstanceCache;

    public PlayerItemInstanceRegistry(SyncManager<?> syncManager) {
        this.syncManager = syncManager;
        this.itemStackInstanceCache = CacheBuilder.newBuilder()
            .weakKeys()
            .maximumSize(1000L)
            .expireAfterAccess(CACHE_EXPIRATION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build();
    }

    public <T extends PlayerItemInstance<S>, S extends ManagedState<S>> T getMainHandItemInstance(
        EntityLivingBase player, Class<T> targetClass) {
        if (player == null) {
            return null;
        } else {
            PlayerItemInstance<?> instance = this
                .getItemInstance((EntityPlayer) player, ((EntityPlayer) player).inventory.currentItem);
            return targetClass.isInstance(instance) ? targetClass.cast(instance) : null;
        }
    }

    public PlayerItemInstance<?> getMainHandItemInstance(EntityLivingBase player) {
        return player == null ? null
            : this.getItemInstance((EntityPlayer) player, ((EntityPlayer) player).inventory.currentItem);
    }

    private void registerInstance(Map<Integer, PlayerItemInstance<?>> slotInstances, int slot,
        PlayerItemInstance<?> instance) {
        slotInstances.put(slot, instance);
        this.syncManager.watch(instance);
        if (instance.updateId == 0L) {
            instance.markDirty();
        }
    }

    public PlayerItemInstance<?> getItemInstance(EntityPlayer player, int slot) {
        Map<Integer, PlayerItemInstance<?>> slotInstances = this.registry
            .computeIfAbsent(player.getPersistentID(), p -> new HashMap<>());
        PlayerItemInstance<?> result = slotInstances.get(slot);

        if (result == null) {
            result = this.createItemInstance(player, slot);
            if (result != null) {
                this.registerInstance(slotInstances, slot, result);
            }
            return result;
        }

        ItemStack slotItemStack = player.inventory.getStackInSlot(slot);
        if (slotItemStack != null && slotItemStack.getItem() != result.getItem()) {
            this.syncManager.unwatch(result);
            result = this.createItemInstance(player, slot);
            if (result != null) {
                this.registerInstance(slotInstances, slot, result);
            }
        }

        if (result != null && result.getItemInventoryIndex() != slot) {
            logger.warn("Invalid instance slot id, correcting...");
            result.setItemInventoryIndex(slot);
        }

        if (result != null && result.getPlayer() != player) {
            logger.warn(
                "Invalid player " + result.getPlayer() + " associated with instance in slot, changing to {}",
                player);
            result.setPlayer(player);
        }

        return result;
    }

    private boolean isSameItem(PlayerItemInstance<?> instance1, PlayerItemInstance<?> instance2) {
        return Item.getIdFromItem(instance1.getItem()) == Item.getIdFromItem(instance2.getItem());
    }

    @SuppressWarnings("unchecked")
    public <S extends ManagedState<S>, T extends PlayerItemInstance<S>> boolean update(S newManagedState,
        T extendedStateToMerge) {
        Map<Integer, PlayerItemInstance<?>> slotContexts = this.registry.get(
            extendedStateToMerge.getPlayer()
                .getUniqueID());
        boolean result = false;
        if (slotContexts != null) {
            T currentState = (T) slotContexts.get(extendedStateToMerge.getItemInventoryIndex());
            if (currentState != null && this.isSameItem(currentState, extendedStateToMerge)) {
                extendedStateToMerge.setState(newManagedState);
                if (newManagedState.commitPhase() != null) {
                    currentState.prepareTransaction(extendedStateToMerge);
                } else {
                    currentState.updateWith(extendedStateToMerge, true);
                }

                result = true;
            }
        }

        return result;
    }

    private PlayerItemInstance<?> createItemInstance(EntityLivingBase entityLivingBase, int slot) {
        if (!(entityLivingBase instanceof EntityPlayer player)) {
            return null;
        } else {
            ItemStack itemStack = player.inventory.getStackInSlot(slot);
            PlayerItemInstance<?> result = null;
            if (itemStack != null && itemStack.getItem() instanceof PlayerItemInstanceFactory) {
                try {
                    logger.debug("Deserializing instance for slot {} from stack {}", slot, itemStack);
                    result = Tags.getInstance(itemStack);
                    logger.debug("Deserialized instance {} for slot {} from stack {}", result, slot, itemStack);
                } catch (RuntimeException e) {
                    logger.debug("Failed to deserialize instance from {}", itemStack);
                }

                if (result == null) {
                    logger.debug("Creating instance for slot {} from stack {}", slot, itemStack);
                    result = ((PlayerItemInstanceFactory) itemStack.getItem())
                        .createItemInstance(player, itemStack, slot);
                    result.updateId = 0L;
                }

                result.setItemInventoryIndex(slot);
                result.setPlayer(player);
            }

            return result;
        }
    }

    public PlayerItemInstance<?> getItemInstance(EntityLivingBase player, ItemStack itemStack) {
        Optional<PlayerItemInstance<?>> result = Optional.empty();

        try {
            result = this.itemStackInstanceCache.get(itemStack, () -> {
                logger.debug("ItemStack {} not found in cache, initializing...", itemStack);
                PlayerItemInstance<?> instance = null;
                int slot = -1;
                if (Minecraft.getMinecraft().thePlayer == player) {
                    slot = InventoryUtils.getInventorySlot((EntityPlayer) player, itemStack);
                }

                if (slot >= 0) {
                    instance = this.getItemInstance((EntityPlayer) player, slot);
                    logger.debug("Resolved item stack instance {} in slot {}", instance, slot);
                }

                if (instance == null || instance.getItem() != itemStack.getItem()) {
                    try {
                        instance = Tags.getInstance(itemStack);
                    } catch (RuntimeException e) {
                        logger.error("Failed to deserialize instance from stack {}: {}", itemStack, e.toString());
                    }
                }

                if ((instance == null || instance.getItem() != itemStack.getItem())
                    && itemStack.getItem() instanceof PlayerItemInstanceFactory) {
                    logger.debug("Creating temporary item stack instance {}", instance);
                    instance = ((PlayerItemInstanceFactory) itemStack.getItem())
                        .createItemInstance(player, itemStack, -1);
                    instance.setPlayer(player);
                }

                return Optional.ofNullable(instance);
            });
        } catch (ExecutionException | UncheckedExecutionException e) {
            logger.error("Failed to initialize cache instance from {}", itemStack, e.getCause());
        }

        return result.orElse(null);
    }

    public void update(EntityPlayer player) {
        if (player == null) return;

        Map<Integer, PlayerItemInstance<?>> slotContexts = this.registry.get(player.getPersistentID());
        if (slotContexts == null) return;

        Iterator<Entry<Integer, PlayerItemInstance<?>>> it = slotContexts.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Integer, PlayerItemInstance<?>> entry = it.next();
            ItemStack slotStack = player.inventory.getStackInSlot(entry.getKey());
            if (slotStack == null || slotStack.getItem() != entry.getValue().getItem()) {
                logger.debug("Removing {} from slot {}", entry.getValue(), entry.getKey());
                this.syncManager.unwatch(entry.getValue());
                it.remove();
            }
        }
    }
}
