package com.gtnewhorizon.newgunrizons.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.network.WeaponActionMessage;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.StateManager;

public final class WeaponAttachmentAspect implements Aspect<WeaponState, ItemWeaponInstance> {

    public static final WeaponAttachmentAspect INSTANCE = new WeaponAttachmentAspect();

    private StateManager<WeaponState, ? super ItemWeaponInstance> stateManager;
    private final long clickSpammingTimeout = 150L;
    private final Predicate<ItemWeaponInstance> clickSpammingPreventer = (es) -> System.currentTimeMillis()
        >= es.getStateUpdateTimestamp() + this.clickSpammingTimeout;
    private final Predicate<ItemWeaponInstance> clickSpammingPreventer2 = (es) -> System.currentTimeMillis()
        >= es.getStateUpdateTimestamp() + this.clickSpammingTimeout * 2L;

    /** Tracks the attachment category for the current changeAttachment operation. */
    private AttachmentCategory pendingAttachmentCategory;

    public WeaponAttachmentAspect() {}

    public void setStateManager(StateManager<WeaponState, ? super ItemWeaponInstance> stateManager) {
        this.stateManager = stateManager.in(this)
            .change(WeaponState.READY)
            .to(WeaponState.MODIFYING)
            .when(this.clickSpammingPreventer)
            .withAction(this::enterAttachmentSelectionMode)
            .manual()
            .in(this)
            .change(WeaponState.MODIFYING)
            .to(WeaponState.READY)
            .when(this.clickSpammingPreventer2)
            .withAction(this::exitAttachmentSelectionMode)
            .manual()
            .in(this)
            .change(WeaponState.MODIFYING)
            .to(WeaponState.NEXT_ATTACHMENT)
            .when(this.clickSpammingPreventer)
            .withAction(this::changeAttachmentAction)
            .manual()
            .in(this)
            .change(WeaponState.NEXT_ATTACHMENT)
            .to(WeaponState.MODIFYING)
            .automatic();
    }

    public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
        ItemWeaponInstance weaponInstance = ItemInstanceRegistry.INSTANCE
            .getMainHandItemInstance(player, ItemWeaponInstance.class);
        if (weaponInstance != null) {
            this.stateManager.changeState(this, weaponInstance, WeaponState.MODIFYING, WeaponState.READY);
        }

    }

    public void updateMainHeldItem(EntityPlayer player) {
        // No automatic transitions needed for attachment mode
    }

    private void enterAttachmentSelectionMode(ItemWeaponInstance weaponInstance) {
        byte[] selectedAttachmentIndexes = new byte[AttachmentCategory.VALUES.length];
        Arrays.fill(selectedAttachmentIndexes, (byte) -1);
        weaponInstance.setSelectedAttachmentIndexes(selectedAttachmentIndexes);
    }

    private void exitAttachmentSelectionMode(ItemWeaponInstance weaponInstance) {
        weaponInstance.setSelectedAttachmentIndexes(new byte[0]);
    }

    public List<CompatibleAttachment> getActiveAttachments(EntityLivingBase player, ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        List<CompatibleAttachment> activeAttachments = new ArrayList<>();
        ItemInstance<?> itemInstance = ItemInstanceRegistry.INSTANCE.getItemInstance(player, itemStack);
        int[] activeAttachmentsIds;
        if (!(itemInstance instanceof ItemWeaponInstance)) {
            activeAttachmentsIds = new int[AttachmentCategory.VALUES.length];
            for (CompatibleAttachment attachment : ((ItemWeapon) itemStack.getItem()).getCompatibleAttachments()
                .values()) {
                if (attachment.isDefault()) {
                    activeAttachmentsIds[attachment.getAttachment()
                        .getCategory()
                        .ordinal()] = Item.getIdFromItem(attachment.getAttachment());
                }
            }
        } else {
            activeAttachmentsIds = ((ItemWeaponInstance) itemInstance).getActiveAttachmentIds();
        }

        ItemWeapon weapon = (ItemWeapon) itemStack.getItem();

        for (int activeIndex : activeAttachmentsIds) {
            if (activeIndex != 0) {
                Item item = Item.getItemById(activeIndex);
                if (item instanceof ItemAttachment) {
                    CompatibleAttachment compatibleAttachment = weapon.getCompatibleAttachments()
                        .get(item);
                    if (compatibleAttachment != null) {
                        activeAttachments.add(compatibleAttachment);
                    }
                }
            }
        }

        return activeAttachments;
    }

    public void changeAttachment(AttachmentCategory attachmentCategory, ItemWeaponInstance weaponInstance) {
        if (weaponInstance != null) {
            this.pendingAttachmentCategory = attachmentCategory;
            this.stateManager.changeState(this, weaponInstance, WeaponState.NEXT_ATTACHMENT);
        }

    }

    private void changeAttachmentAction(ItemWeaponInstance weaponInstance) {
        AttachmentCategory attachmentCategory = this.pendingAttachmentCategory;
        if (attachmentCategory == null) {
            return;
        }
        // Send action to server for authoritative inventory operations
        NewGunrizonsMod.CHANNEL.sendToServer(
            new WeaponActionMessage(
                WeaponActionMessage.CHANGE_ATTACHMENT,
                weaponInstance.getItemInventoryIndex(),
                (byte) attachmentCategory.ordinal()));
        // Optimistic client-side visual update
        this.changeAttachmentInternal(attachmentCategory, weaponInstance);
    }

    private void changeAttachmentInternal(AttachmentCategory attachmentCategory, ItemWeaponInstance weaponInstance) {
        if (weaponInstance.getPlayer() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) weaponInstance.getPlayer();
            int[] originalActiveAttachmentIds = weaponInstance.getActiveAttachmentIds();
            int[] activeAttachmentIds = Arrays.copyOf(originalActiveAttachmentIds, originalActiveAttachmentIds.length);
            int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];
            ItemAttachment currentAttachment = null;
            if (activeAttachmentIdForThisCategory > 0) {
                currentAttachment = (ItemAttachment) Item.getItemById(activeAttachmentIdForThisCategory);
            }

            if (currentAttachment != null) {
                CompatibleAttachment currentCompatibleAttachment = weaponInstance.getWeapon()
                    .getCompatibleAttachments()
                    .get(currentAttachment);
                if (currentCompatibleAttachment.isPermanent()) {
                    return;
                }
            }

            WeaponAttachmentAspect.AttachmentLookupResult lookupResult = this
                .next(attachmentCategory, currentAttachment, weaponInstance);
            if (currentAttachment != null && currentAttachment.getRemoveHandler() != null) {
                currentAttachment.getRemoveHandler()
                    .apply(currentAttachment, weaponInstance);
            }

            if (lookupResult.index >= 0) {
                ItemStack slotItemStack = player.inventory.getStackInSlot(lookupResult.index);
                ItemAttachment nextAttachment = (ItemAttachment) slotItemStack.getItem();
                if (nextAttachment.getApplyHandler() != null) {
                    nextAttachment.getApplyHandler()
                        .apply(nextAttachment, weaponInstance);
                } else if (lookupResult.compatibleAttachment.getApplyHandler() != null) {
                    lookupResult.compatibleAttachment.getApplyHandler()
                        .apply(nextAttachment, weaponInstance);
                } else {
                    ItemAttachment.AttachmentHandler handler = weaponInstance.getWeapon()
                        .getEquivalentHandler(attachmentCategory);
                    if (handler != null) {
                        handler.apply(null, weaponInstance);
                    }
                }

                activeAttachmentIds[attachmentCategory.ordinal()] = Item.getIdFromItem(nextAttachment);
            } else {
                activeAttachmentIds[attachmentCategory.ordinal()] = -1;
                ItemAttachment.AttachmentHandler handler = weaponInstance.getWeapon()
                    .getEquivalentHandler(attachmentCategory);
                if (handler != null) {
                    handler.apply(null, weaponInstance);
                }
            }

            weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
        }
    }

    private AttachmentLookupResult next(AttachmentCategory category, Item currentAttachment,
        ItemWeaponInstance weaponInstance) {
        AttachmentLookupResult result = new AttachmentLookupResult();
        byte[] originallySelectedAttachmentIndexes = weaponInstance.getSelectedAttachmentIds();
        if (originallySelectedAttachmentIndexes != null
            && originallySelectedAttachmentIndexes.length == AttachmentCategory.VALUES.length) {
            byte[] selectedAttachmentIndexes = Arrays
                .copyOf(originallySelectedAttachmentIndexes, originallySelectedAttachmentIndexes.length);
            int activeIndex = selectedAttachmentIndexes[category.ordinal()];
            result.index = -1;
            int offset = activeIndex + 1;

            for (int i = 0; i < 37; ++i) {
                int currentIndex = i + offset;
                if (currentIndex >= 36) {
                    currentIndex -= 37;
                }

                if (currentIndex == -1) {
                    break;
                }

                ItemStack slotItemStack = ((EntityPlayer) weaponInstance.getPlayer()).inventory
                    .getStackInSlot(currentIndex);
                if (slotItemStack != null && slotItemStack.getItem() instanceof ItemAttachment) {
                    ItemAttachment attachmentItemFromInventory = (ItemAttachment) slotItemStack.getItem();
                    CompatibleAttachment compatibleAttachment;
                    if (attachmentItemFromInventory.getCategory() == category
                        && (compatibleAttachment = weaponInstance.getWeapon()
                            .getCompatibleAttachments()
                            .get(attachmentItemFromInventory)) != null
                        && attachmentItemFromInventory != currentAttachment) {
                        result.index = currentIndex;
                        result.compatibleAttachment = compatibleAttachment;
                        break;
                    }
                }
            }

            selectedAttachmentIndexes[category.ordinal()] = (byte) result.index;
            weaponInstance.setSelectedAttachmentIndexes(selectedAttachmentIndexes);
            return result;
        } else {
            return result;
        }
    }

    public static void addAttachment(ItemAttachment attachment, ItemWeaponInstance weaponInstance) {
        int[] activeAttachmentsIds = weaponInstance.getActiveAttachmentIds();
        int activeAttachmentIdForThisCategory = activeAttachmentsIds[attachment.getCategory()
            .ordinal()];
        ItemAttachment currentAttachment = null;
        if (activeAttachmentIdForThisCategory > 0) {
            currentAttachment = (ItemAttachment) Item.getItemById(activeAttachmentIdForThisCategory);
        }

        if (currentAttachment == null) {
            if (attachment != null && attachment.getApplyHandler() != null) {
                attachment.getApplyHandler()
                    .apply(attachment, weaponInstance);
            }

            activeAttachmentsIds[attachment.getCategory()
                .ordinal()] = Item.getIdFromItem(attachment);
        } else {
            System.err.println("Attachment of category " + attachment.getCategory() + " installed, remove it first");
        }

    }

    ItemAttachment removeAttachment(AttachmentCategory attachmentCategory, ItemWeaponInstance weaponInstance) {
        int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
        int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];
        ItemAttachment currentAttachment = null;
        if (activeAttachmentIdForThisCategory > 0) {
            currentAttachment = (ItemAttachment) Item.getItemById(activeAttachmentIdForThisCategory);
        }

        if (currentAttachment != null && currentAttachment.getRemoveHandler() != null) {
            currentAttachment.getRemoveHandler()
                .apply(currentAttachment, weaponInstance);
        }

        if (currentAttachment != null) {
            activeAttachmentIds[attachmentCategory.ordinal()] = -1;
            weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
        }

        return currentAttachment;
    }

    public static ItemAttachment getActiveAttachment(AttachmentCategory category, ItemWeaponInstance weaponInstance) {
        ItemAttachment itemAttachment = null;
        int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
        for (int activeIndex : activeAttachmentIds) {
            if (activeIndex != 0) {
                Item item = Item.getItemById(activeIndex);
                if (item instanceof ItemAttachment) {
                    CompatibleAttachment compatibleAttachment = weaponInstance.getWeapon()
                        .getCompatibleAttachments()
                        .get(item);
                    if (compatibleAttachment != null && category == compatibleAttachment.getAttachment()
                        .getCategory()) {
                        itemAttachment = compatibleAttachment.getAttachment();
                        break;
                    }
                }
            }
        }

        return itemAttachment;
    }

    public static boolean isActiveAttachment(ItemAttachment attachment, ItemWeaponInstance weaponInstance) {
        int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
        return Arrays.stream(activeAttachmentIds)
            .anyMatch((attachmentId) -> attachment == Item.getItemById(attachmentId));
    }

    public boolean isSilencerOn(ItemWeaponInstance weaponInstance) {
        int[] activeAttachmentsIds = weaponInstance.getActiveAttachmentIds();
        int activeAttachmentIdForThisCategory = activeAttachmentsIds[AttachmentCategory.SILENCER.ordinal()];
        return activeAttachmentIdForThisCategory > 0;
    }

    public ItemAttachment getActiveAttachment(ItemWeaponInstance weaponInstance, AttachmentCategory category) {
        return weaponInstance.getAttachmentItemWithCategory(category);
    }

    private static class AttachmentLookupResult {

        CompatibleAttachment compatibleAttachment;
        int index;

        private AttachmentLookupResult() {
            this.index = -1;
        }

    }
}
