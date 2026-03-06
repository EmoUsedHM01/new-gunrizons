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
import com.gtnewhorizon.newgunrizons.items.ItemMagazine;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import com.gtnewhorizon.newgunrizons.weapon.WeaponAttachmentAspect;
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

    private final WeaponFireAspect weaponFireAspect;

    public WeaponActionMessageHandler(WeaponFireAspect weaponFireAspect) {
        this.weaponFireAspect = weaponFireAspect;
    }

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

        ItemStack itemStack = player.inventory.getStackInSlot(slotIndex);
        if (itemStack == null) {
            return null;
        }

        switch (message.getActionType()) {
            case WeaponActionMessage.WEAPON_LOAD:
                this.processWeaponLoad(player, itemStack, slotIndex);
                break;
            case WeaponActionMessage.WEAPON_UNLOAD:
                this.processWeaponUnload(player, itemStack, slotIndex);
                break;
            case WeaponActionMessage.MAGAZINE_LOAD:
                this.processMagazineLoad(player, itemStack);
                break;
            case WeaponActionMessage.CHANGE_ATTACHMENT:
                this.processChangeAttachment(player, itemStack, message.getAttachmentCategory(), slotIndex);
                break;
            case WeaponActionMessage.CHANGE_FIRE_MODE:
                this.processChangeFireMode(player, itemStack, slotIndex);
                break;
            case WeaponActionMessage.TOGGLE_LASER:
                this.processToggleLaser(player, itemStack, slotIndex);
                break;
            case WeaponActionMessage.ZOOM_IN:
                this.processZoom(player, itemStack, true, slotIndex);
                break;
            case WeaponActionMessage.ZOOM_OUT:
                this.processZoom(player, itemStack, false, slotIndex);
                break;
            case WeaponActionMessage.FIRE:
                this.weaponFireAspect.serverFire(player, itemStack, slotIndex);
                break;
        }

        return null;
    }

    /**
     * Gets the weapon instance from NBT, or creates and persists a fresh one if absent.
     * Returns null if the item is not an {@link ItemWeapon}.
     */
    private ItemWeaponInstance getOrCreateInstance(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
        if (!(weaponStack.getItem() instanceof ItemWeapon)) {
            return null;
        }
        ItemWeapon weapon = (ItemWeapon) weaponStack.getItem();

        if (weaponStack.stackTagCompound == null) {
            weaponStack.stackTagCompound = new NBTTagCompound();
        }

        ItemWeaponInstance instance = ItemInstance.fromStack(weaponStack, ItemWeaponInstance.class);
        if (instance == null) {
            instance = weapon.createItemInstance(player, weaponStack, slotIndex);
            ItemInstance.toStack(weaponStack, instance);
        }
        instance.setPlayer(player);
        return instance;
    }

    private void processWeaponLoad(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
        if (!(weaponStack.getItem() instanceof ItemWeapon weapon)) {
            return;
        }

        if (weaponStack.stackTagCompound == null) {
            weaponStack.stackTagCompound = new NBTTagCompound();
        }

        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);

        List<ItemMagazine> compatibleMagazines = weapon.getCompatibleMagazines();
        List<ItemAttachment> compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);

        if (!compatibleMagazines.isEmpty()) {
            this.loadWithMagazine(player, weaponStack, weapon, instance, compatibleMagazines);
        } else if (!compatibleBullets.isEmpty()) {
            this.loadWithBullets(player, weaponStack, weapon, instance, compatibleBullets);
        } else if (weapon.getAmmo() != null) {
            this.loadWithGenericAmmo(player, weaponStack, weapon, instance);
        }
    }

    private void loadWithMagazine(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon,
        ItemWeaponInstance instance, List<ItemMagazine> compatibleMagazines) {
        ItemAttachment existingMagazine = WeaponAttachmentAspect
            .getActiveAttachment(AttachmentCategory.MAGAZINE, instance);

        if (existingMagazine != null) {
            // Magazine already attached — ammo already set from existing magazine
            int ammo = ItemInstance.getAmmo(weaponStack);
            instance.setAmmo(ammo);
            instance.setLoadIterationCount(0);
            ItemInstance.toStack(weaponStack, instance);
            return;
        }

        // Consume a compatible magazine from inventory
        ItemStack magazineStack = InventoryUtils.tryConsumingCompatibleItem(
            compatibleMagazines,
            1,
            player,
            (stack) -> ItemInstance.getAmmo(stack) > 0,
            (stack) -> true);

        if (magazineStack != null) {
            int ammo = ItemInstance.getAmmo(magazineStack);
            ItemInstance.setAmmo(weaponStack, ammo);

            WeaponAttachmentAspect.addAttachment((ItemAttachment) magazineStack.getItem(), instance);
            instance.setAmmo(ammo);
            instance.setLoadIterationCount(0);
            ItemInstance.toStack(weaponStack, instance);
            player.worldObj.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
        }
    }

    private void loadWithBullets(EntityPlayerMP player, ItemStack weaponStack, ItemWeapon weapon,
        ItemWeaponInstance instance, List<ItemAttachment> compatibleBullets) {
        int currentAmmo = instance.getAmmo();
        int maxToLoad = Math.min(weapon.getMaxBulletsPerReload(), weapon.getAmmoCapacity() - currentAmmo);

        ItemStack consumedStack = InventoryUtils
            .tryConsumingCompatibleItem(compatibleBullets, maxToLoad, player, (stack) -> true);

        if (consumedStack != null) {
            int ammo = currentAmmo + consumedStack.stackSize;
            ItemInstance.setAmmo(weaponStack, ammo);

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
            ItemInstance.setAmmo(weaponStack, weapon.getAmmoCapacity());

            instance.setAmmo(weapon.getAmmoCapacity());
            ItemInstance.toStack(weaponStack, instance);
            player.worldObj.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
        }
    }

    private void processWeaponUnload(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
        if (!(weaponStack.getItem() instanceof ItemWeapon weapon)) {
            return;
        }

        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);
        if (instance == null) {
            return;
        }

        // Remove magazine attachment
        int[] activeAttachmentIds = instance.getActiveAttachmentIds();
        int magazineAttachmentId = activeAttachmentIds[AttachmentCategory.MAGAZINE.ordinal()];
        ItemAttachment currentMagazine = null;
        if (magazineAttachmentId > 0) {
            currentMagazine = (ItemAttachment) Item.getItemById(magazineAttachmentId);
        }

        if (currentMagazine instanceof ItemMagazine magazine) {
            // Create magazine ItemStack with current ammo and return to inventory
            ItemStack magazineItemStack = magazine.createItemStack();
            ItemInstance.setAmmo(magazineItemStack, instance.getAmmo());
            player.inventory.addItemStackToInventory(magazineItemStack);

            // Clear attachment from instance
            activeAttachmentIds[AttachmentCategory.MAGAZINE.ordinal()] = -1;
            instance.setActiveAttachmentIds(activeAttachmentIds);
        }

        ItemInstance.setAmmo(weaponStack, 0);
        instance.setAmmo(0);
        ItemInstance.toStack(weaponStack, instance);

        player.worldObj.playSoundToNearExcept(player, weapon.getUnloadSound(), 1.0F, 1.0F);
    }

    private void processMagazineLoad(EntityPlayerMP player, ItemStack magazineStack) {
        if (!(magazineStack.getItem() instanceof ItemMagazine magazine)) {
            return;
        }

        List<ItemBullet> compatibleBullets = magazine.getCompatibleBullets();
        int currentAmmo = ItemInstance.getAmmo(magazineStack);
        int maxToLoad = magazine.getAmmo() - currentAmmo;

        ItemStack consumedStack = InventoryUtils
            .tryConsumingCompatibleItem(compatibleBullets, maxToLoad, player, (stack) -> true);

        if (consumedStack != null) {
            ItemInstance.setAmmo(magazineStack, currentAmmo + consumedStack.stackSize);

            if (magazine.getReloadSound() != null) {
                player.playSound(magazine.getReloadSound(), 1.0F, 1.0F);
            }
        }
    }

    private void processChangeAttachment(EntityPlayerMP player, ItemStack weaponStack, byte categoryOrdinal,
        int slotIndex) {
        if (!(weaponStack.getItem() instanceof ItemWeapon weapon)) {
            return;
        }

        if (categoryOrdinal < 0 || categoryOrdinal >= AttachmentCategory.VALUES.length) {
            return;
        }
        AttachmentCategory attachmentCategory = AttachmentCategory.VALUES[categoryOrdinal];

        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);
        if (instance == null) {
            return;
        }

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

        // Search for next compatible attachment in inventory
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

        // Remove current attachment handler
        if (currentAttachment != null && currentAttachment.getRemoveHandler() != null) {
            currentAttachment.getRemoveHandler()
                .apply(currentAttachment, instance);
        }

        // Apply new attachment or clear slot
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

        // Return old attachment to inventory
        if (currentAttachment != null) {
            InventoryUtils.addItemToPlayerInventory(player, currentAttachment, foundSlot);
        }

        instance.setActiveAttachmentIds(activeAttachmentIds);
        ItemInstance.toStack(weaponStack, instance);
    }

    private void processChangeFireMode(EntityPlayerMP player, ItemStack weaponStack, int slotIndex) {
        if (!(weaponStack.getItem() instanceof ItemWeapon weapon)) {
            return;
        }

        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);
        if (instance == null) {
            return;
        }

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
        if (instance == null) {
            return;
        }
        instance.setLaserOn(!instance.isLaserOn());
        ItemInstance.toStack(weaponStack, instance);
    }

    private void processZoom(EntityPlayerMP player, ItemStack weaponStack, boolean zoomIn, int slotIndex) {
        if (!(weaponStack.getItem() instanceof ItemWeapon)) {
            return;
        }

        ItemWeaponInstance instance = getOrCreateInstance(player, weaponStack, slotIndex);
        if (instance == null) {
            return;
        }

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
