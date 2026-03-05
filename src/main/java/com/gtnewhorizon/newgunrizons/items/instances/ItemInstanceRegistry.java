package com.gtnewhorizon.newgunrizons.items.instances;

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
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;

public class ItemInstanceRegistry {

    private static final int CACHE_EXPIRATION_TIMEOUT_SECONDS = 5;
    private static final Logger logger = LogManager.getLogger(ItemInstanceRegistry.class);
    private final Map<UUID, Map<Integer, ItemInstance<?>>> registry = new HashMap<>();
    private final Cache<ItemStack, Optional<ItemInstance<?>>> itemStackInstanceCache;

    public ItemInstanceRegistry() {
        this.itemStackInstanceCache = CacheBuilder.newBuilder()
            .weakKeys()
            .maximumSize(1000L)
            .expireAfterAccess(CACHE_EXPIRATION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build();
    }

    public <T extends ItemInstance<S>, S extends ManagedState<S>> T getMainHandItemInstance(EntityLivingBase player,
        Class<T> targetClass) {
        if (player == null) {
            return null;
        } else {
            ItemInstance<?> instance = this
                .getItemInstance((EntityPlayer) player, ((EntityPlayer) player).inventory.currentItem);
            return targetClass.isInstance(instance) ? targetClass.cast(instance) : null;
        }
    }

    public ItemInstance<?> getMainHandItemInstance(EntityLivingBase player) {
        return player == null ? null
            : this.getItemInstance((EntityPlayer) player, ((EntityPlayer) player).inventory.currentItem);
    }

    private void registerInstance(Map<Integer, ItemInstance<?>> slotInstances, int slot, ItemInstance<?> instance) {
        slotInstances.put(slot, instance);
    }

    public ItemInstance<?> getItemInstance(EntityPlayer player, int slot) {
        Map<Integer, ItemInstance<?>> slotInstances = this.registry
            .computeIfAbsent(player.getPersistentID(), p -> new HashMap<>());
        ItemInstance<?> result = slotInstances.get(slot);

        if (result == null) {
            result = this.createItemInstance(player, slot);
            if (result != null) {
                this.registerInstance(slotInstances, slot, result);
            }
            return result;
        }

        ItemStack slotItemStack = player.inventory.getStackInSlot(slot);
        if (slotItemStack != null && slotItemStack.getItem() != result.getItem()) {
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

    private boolean isSameItem(ItemInstance<?> instance1, ItemInstance<?> instance2) {
        return Item.getIdFromItem(instance1.getItem()) == Item.getIdFromItem(instance2.getItem());
    }

    @SuppressWarnings("unchecked")
    public <S extends ManagedState<S>, T extends ItemInstance<S>> boolean update(S newManagedState,
        T extendedStateToMerge) {
        Map<Integer, ItemInstance<?>> slotContexts = this.registry.get(
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

    private ItemInstance<?> createItemInstance(EntityLivingBase entityLivingBase, int slot) {
        if (!(entityLivingBase instanceof EntityPlayer player)) {
            return null;
        } else {
            ItemStack itemStack = player.inventory.getStackInSlot(slot);
            ItemInstance<?> result = null;
            if (itemStack != null && itemStack.getItem() instanceof ItemInstanceFactory) {
                try {
                    logger.debug("Deserializing instance for slot {} from stack {}", slot, itemStack);
                    result = Tags.getInstance(itemStack);
                    logger.debug("Deserialized instance {} for slot {} from stack {}", result, slot, itemStack);
                } catch (RuntimeException e) {
                    logger.debug("Failed to deserialize instance from {}", itemStack);
                }

                if (result == null) {
                    logger.debug("Creating instance for slot {} from stack {}", slot, itemStack);
                    result = ((ItemInstanceFactory) itemStack.getItem()).createItemInstance(player, itemStack, slot);
                }

                result.setItemInventoryIndex(slot);
                result.setPlayer(player);
            }

            return result;
        }
    }

    public ItemInstance<?> getItemInstance(EntityLivingBase player, ItemStack itemStack) {
        Optional<ItemInstance<?>> result = Optional.empty();

        try {
            result = this.itemStackInstanceCache.get(itemStack, () -> {
                logger.debug("ItemStack {} not found in cache, initializing...", itemStack);
                ItemInstance<?> instance = null;
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
                    && itemStack.getItem() instanceof ItemInstanceFactory) {
                    logger.debug("Creating temporary item stack instance {}", instance);
                    instance = ((ItemInstanceFactory) itemStack.getItem()).createItemInstance(player, itemStack, -1);
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

        Map<Integer, ItemInstance<?>> slotContexts = this.registry.get(player.getPersistentID());
        if (slotContexts == null) return;

        Iterator<Entry<Integer, ItemInstance<?>>> it = slotContexts.entrySet()
            .iterator();
        while (it.hasNext()) {
            Entry<Integer, ItemInstance<?>> entry = it.next();
            ItemStack slotStack = player.inventory.getStackInSlot(entry.getKey());
            if (slotStack == null || slotStack.getItem() != entry.getValue()
                .getItem()) {
                logger.debug("Removing {} from slot {}", entry.getValue(), entry.getKey());
                it.remove();
            }
        }
    }
}
