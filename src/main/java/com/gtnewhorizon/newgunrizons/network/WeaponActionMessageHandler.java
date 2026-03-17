package com.gtnewhorizon.newgunrizons.network;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import com.gtnewhorizon.newgunrizons.weapon.WeaponFireAspect;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

/**
 * Server-side handler for {@link WeaponActionMessage}. Performs the authoritative
 * inventory operations (consuming ammo, swapping attachments, etc.).
 */
public class WeaponActionMessageHandler implements IMessageHandler<WeaponActionMessage, IMessage> {

    @Override
    public IMessage onMessage(WeaponActionMessage message, MessageContext ctx) {

        if (ctx.side != Side.SERVER) {
            return null;
        }

        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        int slotIndex = message.getSlotIndex();

        if (slotIndex < 0 || slotIndex >= player.inventory.mainInventory.length) {
            return null;
        }

        ItemStack weaponStack = player.inventory.getStackInSlot(slotIndex);
        if (weaponStack == null || !(weaponStack.getItem() instanceof ItemWeapon)) {
            return null;
        }

        ItemWeapon weapon = (ItemWeapon) weaponStack.getItem();

        if (weaponStack.stackTagCompound == null) {
            weaponStack.stackTagCompound = new NBTTagCompound();
        }

        switch (message.getActionType()) {
            case WeaponActionMessage.WEAPON_LOAD:
                this.processWeaponLoad(player, weaponStack, weapon, slotIndex);
                break;
            case WeaponActionMessage.WEAPON_UNLOAD:
                this.processWeaponUnload(player, weaponStack, weapon, slotIndex);
                break;
            case WeaponActionMessage.CHANGE_ATTACHMENT:
                this.processChangeAttachment(player, weaponStack, weapon, message.getAttachmentCategory(), slotIndex);
                break;
            case WeaponActionMessage.CHANGE_FIRE_MODE:
                this.processChangeFireMode(player, weaponStack, weapon, slotIndex);
                break;
            case WeaponActionMessage.TOGGLE_LASER:
                this.processToggleLaser(player, weaponStack, slotIndex);
                break;
            case WeaponActionMessage.ZOOM_IN:
                this.processZoom(player, weaponStack, true, slotIndex);
                break;
            case WeaponActionMessage.ZOOM_OUT:
                this.processZoom(player, weaponStack, false, slotIndex);
                break;
            case WeaponActionMessage.FIRE:
                WeaponFireAspect.INSTANCE.serverFire(player, weaponStack, slotIndex);
                break;
        }

        return null;
    }

    private ItemWeaponInstance getOrCreateInstance(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
        ItemWeaponInstance instance = ItemInstance.fromStack(weaponStack);
        if (instance == null) {
            instance = ((ItemWeapon) weaponStack.getItem()).createItemInstance(player, weaponStack, slotIndex);
            ItemInstance.toStack(weaponStack, instance);
        }
        instance.setPlayer(player);
        return instance;
    }

    private void processWeaponLoad(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon, int slotIndex) {
        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);

        List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);

        if (!compatibleBullets.isEmpty()) {
            this.loadWithBullets(player, weaponStack, weapon, instance, compatibleBullets);
        } else if (weapon.getAmmo() != null) {
            this.loadWithGenericAmmo(player, weaponStack, weapon, instance);
        }
    }

    private void loadWithBullets(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon,
        ItemWeaponInstance instance, List<ItemAttachment> compatibleBullets) {
        int currentAmmo = instance.getAmmo();
        int maxToLoad = weapon.getAmmoCapacity() - currentAmmo;

        ItemStack consumedStack = InventoryUtils
            .tryConsumingCompatibleItem(compatibleBullets, maxToLoad, player, (stack) -> true);

        if (consumedStack != null) {
            int ammo = currentAmmo + consumedStack.stackSize;
            ItemWeaponInstance.setAmmo(weaponStack, ammo);

            instance.setAmmo(ammo);
            if (weapon.hasIteratedLoad()) {
                instance.setLoadIterationCount(consumedStack.stackSize);
            }
            ItemInstance.toStack(weaponStack, instance);

            player.worldObj.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
        }
    }

    private void loadWithGenericAmmo(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon,
        ItemWeaponInstance instance) {
        if (player.inventory.consumeInventoryItem(weapon.getAmmo())) {
            ItemWeaponInstance.setAmmo(weaponStack, weapon.getAmmoCapacity());

            instance.setAmmo(weapon.getAmmoCapacity());
            ItemInstance.toStack(weaponStack, instance);
            player.worldObj.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
        }
    }

    private void processWeaponUnload(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon, int slotIndex) {
        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);
        ItemWeaponInstance.setAmmo(weaponStack, 0);
        instance.setAmmo(0);
        ItemInstance.toStack(weaponStack, instance);

        if (weapon.getUnloadSound() != null) {
            player.worldObj.playSoundToNearExcept(player, weapon.getUnloadSound(), 1.0F, 1.0F);
        }
    }

    private void processChangeAttachment(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon,
        byte categoryOrdinal, int slotIndex) {
        if (categoryOrdinal < 0 || categoryOrdinal >= AttachmentCategory.VALUES.length) {
            return;
        }
        AttachmentCategory attachmentCategory = AttachmentCategory.VALUES[categoryOrdinal];

        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);

        int[] originalActiveAttachmentIds = instance.getActiveAttachmentIds();
        int[] activeAttachmentIds = Arrays.copyOf(originalActiveAttachmentIds, originalActiveAttachmentIds.length);
        int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];

        ItemAttachment currentAttachment = null;
        if (activeAttachmentIdForThisCategory > 0) {
            currentAttachment = (ItemAttachment) Item.getItemById(activeAttachmentIdForThisCategory);
        }

        if (currentAttachment != null) {
            CompatibleAttachment currentCompatibleAttachment = weapon.getCompatibleAttachments()
                .get(currentAttachment);
            if (currentCompatibleAttachment != null && currentCompatibleAttachment.isPermanent()) {
                return;
            }
        }

        int foundSlot = -1;
        CompatibleAttachment foundCompatibleAttachment = null;

        for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
            ItemStack slotItemStack = player.inventory.getStackInSlot(i);
            if (slotItemStack != null && slotItemStack.getItem() instanceof ItemAttachment) {
                ItemAttachment attachmentFromInventory = (ItemAttachment) slotItemStack.getItem();
                if (attachmentFromInventory.getCategory() == attachmentCategory) {
                    CompatibleAttachment compatibleAttachment = weapon.getCompatibleAttachments()
                        .get(attachmentFromInventory);
                    if (compatibleAttachment != null && attachmentFromInventory != currentAttachment) {
                        foundSlot = i;
                        foundCompatibleAttachment = compatibleAttachment;
                        break;
                    }
                }
            }
        }

        if (currentAttachment != null && currentAttachment.getRemoveHandler() != null) {
            currentAttachment.getRemoveHandler()
                .apply(currentAttachment, instance);
        }

        if (foundSlot >= 0) {
            ItemStack slotItemStack = player.inventory.getStackInSlot(foundSlot);
            ItemAttachment nextAttachment = (ItemAttachment) slotItemStack.getItem();

            if (nextAttachment != null && nextAttachment.getApplyHandler() != null) {
                nextAttachment.getApplyHandler()
                    .apply(nextAttachment, instance);
            } else if (foundCompatibleAttachment.getApplyHandler() != null) {
                foundCompatibleAttachment.getApplyHandler()
                    .apply(nextAttachment, instance);
            } else {
                ItemAttachment.AttachmentHandler handler = weapon.getEquivalentHandler(attachmentCategory);
                if (handler != null) {
                    handler.apply(null, instance);
                }
            }

            InventoryUtils.consumeInventoryItemFromSlot(player, foundSlot);
            activeAttachmentIds[attachmentCategory.ordinal()] = Item.getIdFromItem(nextAttachment);
        } else {
            activeAttachmentIds[attachmentCategory.ordinal()] = -1;
            ItemAttachment.AttachmentHandler handler = weapon.getEquivalentHandler(attachmentCategory);
            if (handler != null) {
                handler.apply(null, instance);
            }
        }

        if (currentAttachment != null) {
            InventoryUtils.addItemToPlayerInventory(player, currentAttachment, foundSlot);
        }

        instance.setActiveAttachmentIds(activeAttachmentIds);
        ItemInstance.toStack(weaponStack, instance);
    }

    private void processChangeFireMode(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon, int slotIndex) {
        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);

        List<Integer> modes = weapon.getMaxShots();
        if (modes.size() <= 1) {
            return;
        }

        int currentIndex = modes.indexOf(instance.getMaxShots());
        int nextIndex = (currentIndex + 1) % modes.size();
        instance.setMaxShots(modes.get(nextIndex));
        ItemInstance.toStack(weaponStack, instance);
    }

    private void processToggleLaser(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);
        instance.setLaserOn(!instance.isLaserOn());
        ItemInstance.toStack(weaponStack, instance);
    }

    private void processZoom(EntityPlayerMP player, ItemStack weaponStack, boolean zoomIn, int slotIndex) {
        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);

        Item scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
        if (!(scopeItem instanceof ItemScope) || !((ItemScope) scopeItem).isOptical()) {
            return;
        }

        float minZoom = ((ItemScope) scopeItem).getMinZoom();
        float maxZoom = ((ItemScope) scopeItem).getMaxZoom();
        float increment = (minZoom - maxZoom) / 20.0F;
        float zoom = instance.getZoom();

        if (zoomIn) {
            if (zoom > maxZoom) {
                zoom = Math.max(zoom - increment, maxZoom);
            }
        } else {
            if (zoom < minZoom) {
                zoom = Math.min(zoom + increment, minZoom);
            }
        }

        instance.setZoom(zoom);
        ItemInstance.toStack(weaponStack, instance);
    }
}
