package com.gtnewhorizon.newgunrizons.mechanic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.ItemAttachment;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.network.TypeRegistry;
import com.gtnewhorizon.newgunrizons.state.Aspect;
import com.gtnewhorizon.newgunrizons.state.Permit;
import com.gtnewhorizon.newgunrizons.state.PermitManager;
import com.gtnewhorizon.newgunrizons.state.StateManager;
import com.gtnewhorizon.newgunrizons.util.InventoryUtils;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import io.netty.buffer.ByteBuf;

public final class WeaponAttachmentAspect implements Aspect<WeaponState, PlayerWeaponInstance> {

    private static final Logger logger = LogManager.getLogger(WeaponAttachmentAspect.class);
    private final ModContext modContext;
    private PermitManager permitManager;
    private StateManager<WeaponState, ? super PlayerWeaponInstance> stateManager;
    private final long clickSpammingTimeout = 150L;
    private final Predicate<PlayerWeaponInstance> clickSpammingPreventer = (es) -> System.currentTimeMillis()
        >= es.getStateUpdateTimestamp() + this.clickSpammingTimeout;
    private final Predicate<PlayerWeaponInstance> clickSpammingPreventer2 = (es) -> System.currentTimeMillis()
        >= es.getStateUpdateTimestamp() + this.clickSpammingTimeout * 2L;
    private final Collection<WeaponState> allowedUpdateFromStates;

    public WeaponAttachmentAspect(ModContext modContext) {
        this.allowedUpdateFromStates = Arrays.asList(WeaponState.MODIFYING_REQUESTED);
        this.modContext = modContext;
    }

    public void setStateManager(StateManager<WeaponState, ? super PlayerWeaponInstance> stateManager) {
        if (this.permitManager == null) {
            throw new IllegalStateException("Permit manager not initialized");
        } else {
            this.stateManager = stateManager.in(this)
                .change(WeaponState.READY)
                .to(WeaponState.MODIFYING)
                .when(this.clickSpammingPreventer)
                .withPermit(
                    (s, es) -> new EnterAttachmentModePermit(s),
                    this.modContext.getPlayerItemInstanceRegistry()::update,
                    this.permitManager)
                .manual()
                .in(this)
                .change(WeaponState.MODIFYING)
                .to(WeaponState.READY)
                .when(this.clickSpammingPreventer2)
                .withAction((instance) -> {
                    this.permitManager.request(
                        new WeaponAttachmentAspect.ExitAttachmentModePermit(WeaponState.READY),
                        instance,
                        (p, e) -> {});
                })
                .manual()
                .in(this)
                .change(WeaponState.MODIFYING)
                .to(WeaponState.NEXT_ATTACHMENT)
                .when(this.clickSpammingPreventer)
                .withPermit(null, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager)
                .manual()
                .in(this)
                .change(WeaponState.NEXT_ATTACHMENT)
                .to(WeaponState.MODIFYING)
                .automatic();
        }
    }

    public void setPermitManager(PermitManager permitManager) {
        this.permitManager = permitManager;
        permitManager.registerEvaluator(
            WeaponAttachmentAspect.EnterAttachmentModePermit.class,
            PlayerWeaponInstance.class,
            this::enterAttachmentSelectionMode);
        permitManager.registerEvaluator(
            WeaponAttachmentAspect.ExitAttachmentModePermit.class,
            PlayerWeaponInstance.class,
            this::exitAttachmentSelectionMode);
        permitManager.registerEvaluator(
            WeaponAttachmentAspect.ChangeAttachmentPermit.class,
            PlayerWeaponInstance.class,
            this::changeAttachment);
    }

    public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
        PlayerWeaponInstance weaponInstance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerWeaponInstance.class);
        if (weaponInstance != null) {
            this.stateManager.changeState(this, weaponInstance, WeaponState.MODIFYING, WeaponState.READY);
        }

    }

    public void updateMainHeldItem(EntityPlayer player) {
        PlayerWeaponInstance instance = this.modContext.getPlayerItemInstanceRegistry()
            .getMainHandItemInstance(player, PlayerWeaponInstance.class);
        if (instance != null) {
            this.stateManager.changeStateFromAnyOf(this, instance, this.allowedUpdateFromStates);
        }

    }

    private void enterAttachmentSelectionMode(WeaponAttachmentAspect.EnterAttachmentModePermit permit,
        PlayerWeaponInstance weaponInstance) {
        logger.debug("Entering attachment mode");
        byte[] selectedAttachmentIndexes = new byte[AttachmentCategory.values.length];
        Arrays.fill(selectedAttachmentIndexes, (byte) -1);
        weaponInstance.setSelectedAttachmentIndexes(selectedAttachmentIndexes);
        permit.setStatus(Permit.Status.GRANTED);
    }

    private void exitAttachmentSelectionMode(WeaponAttachmentAspect.ExitAttachmentModePermit permit,
        PlayerWeaponInstance weaponInstance) {
        logger.debug("Exiting attachment mode");
        weaponInstance.setSelectedAttachmentIndexes(new byte[0]);
        permit.setStatus(Permit.Status.GRANTED);
    }

    public List<CompatibleAttachment> getActiveAttachments(EntityLivingBase player, ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        List<CompatibleAttachment> activeAttachments = new ArrayList<>();
        PlayerItemInstance<?> itemInstance = this.modContext.getPlayerItemInstanceRegistry()
            .getItemInstance(player, itemStack);
        int[] activeAttachmentsIds;
        if (!(itemInstance instanceof PlayerWeaponInstance)) {
            activeAttachmentsIds = new int[AttachmentCategory.values.length];
            for (CompatibleAttachment attachment : ((Weapon) itemStack.getItem()).getCompatibleAttachments()
                .values()) {
                if (attachment.isDefault()) {
                    activeAttachmentsIds[attachment.getAttachment()
                        .getCategory()
                        .ordinal()] = Item.getIdFromItem(attachment.getAttachment());
                }
            }
        } else {
            activeAttachmentsIds = ((PlayerWeaponInstance) itemInstance).getActiveAttachmentIds();
        }

        Weapon weapon = (Weapon) itemStack.getItem();
        int[] var14 = activeAttachmentsIds;
        int var8 = activeAttachmentsIds.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            int activeIndex = var14[var9];
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

    public void changeAttachment(AttachmentCategory attachmentCategory, PlayerWeaponInstance weaponInstance) {
        if (weaponInstance != null) {
            this.stateManager.changeState(
                this,
                weaponInstance,
                new WeaponAttachmentAspect.ChangeAttachmentPermit(attachmentCategory),
                WeaponState.NEXT_ATTACHMENT);
        }

    }

    private void changeAttachment(WeaponAttachmentAspect.ChangeAttachmentPermit permit,
        PlayerWeaponInstance weaponInstance) {
        if (weaponInstance.getPlayer() instanceof EntityPlayer player) {
            AttachmentCategory attachmentCategory = permit.attachmentCategory;
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
            if (currentAttachment != null) {
                if (currentAttachment.getRemove() != null) {
                    currentAttachment.getRemove()
                        .apply(currentAttachment, weaponInstance.getWeapon(), player);
                }

                if (currentAttachment.getRemove2() != null) {
                    currentAttachment.getRemove2()
                        .apply(currentAttachment, weaponInstance);
                }
            }

            if (lookupResult.index >= 0) {
                ItemStack slotItemStack = player.inventory.getStackInSlot(lookupResult.index);
                ItemAttachment nextAttachment = (ItemAttachment) slotItemStack.getItem();
                if (nextAttachment.getApply() != null) {
                    nextAttachment.getApply()
                        .apply(nextAttachment, weaponInstance.getWeapon(), player);
                } else if (nextAttachment.getApply2() != null) {
                    nextAttachment.getApply2()
                        .apply(nextAttachment, weaponInstance);
                } else if (lookupResult.compatibleAttachment.getApplyHandler() != null) {
                    lookupResult.compatibleAttachment.getApplyHandler()
                        .apply(nextAttachment, weaponInstance);
                } else {
                    ItemAttachment.ApplyHandler2 handler = weaponInstance.getWeapon()
                        .getEquivalentHandler(attachmentCategory);
                    if (handler != null) {
                        handler.apply(null, weaponInstance);
                    }
                }

                InventoryUtils.consumeInventoryItemFromSlot(player, lookupResult.index);
                activeAttachmentIds[attachmentCategory.ordinal()] = Item.getIdFromItem(nextAttachment);
            } else {
                activeAttachmentIds[attachmentCategory.ordinal()] = -1;
                ItemAttachment.ApplyHandler2 handler = weaponInstance.getWeapon()
                    .getEquivalentHandler(attachmentCategory);
                if (handler != null) {
                    handler.apply(null, weaponInstance);
                }
            }

            if (currentAttachment != null) {
                InventoryUtils.addItemToPlayerInventory(player, currentAttachment, lookupResult.index);
            }

            weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
        }
    }

    private WeaponAttachmentAspect.AttachmentLookupResult next(AttachmentCategory category, Item currentAttachment,
        PlayerWeaponInstance weaponInstance) {
        WeaponAttachmentAspect.AttachmentLookupResult result = new WeaponAttachmentAspect.AttachmentLookupResult();
        byte[] originallySelectedAttachmentIndexes = weaponInstance.getSelectedAttachmentIds();
        if (originallySelectedAttachmentIndexes != null
            && originallySelectedAttachmentIndexes.length == AttachmentCategory.values.length) {
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

                logger.debug("Searching for an attachment in slot {}", currentIndex);

                if (currentIndex == -1) {
                    break;
                }

                ItemStack slotItemStack = ((EntityPlayer) weaponInstance.getPlayer()).inventory
                    .getStackInSlot(currentIndex);
                if (slotItemStack != null
                    && slotItemStack.getItem() instanceof ItemAttachment attachmentItemFromInventory) {
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

    public static void addAttachment(ItemAttachment attachment, PlayerWeaponInstance weaponInstance) {
        int[] activeAttachmentsIds = weaponInstance.getActiveAttachmentIds();
        int activeAttachmentIdForThisCategory = activeAttachmentsIds[attachment.getCategory()
            .ordinal()];
        ItemAttachment currentAttachment = null;
        if (activeAttachmentIdForThisCategory > 0) {
            currentAttachment = (ItemAttachment) Item.getItemById(activeAttachmentIdForThisCategory);
        }

        if (currentAttachment == null) {
            if (attachment != null) {
                if (attachment.getApply() != null) {
                    attachment.getApply()
                        .apply(attachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
                } else if (attachment.getApply2() != null) {
                    attachment.getApply2()
                        .apply(attachment, weaponInstance);
                }
            }

            activeAttachmentsIds[attachment.getCategory()
                .ordinal()] = Item.getIdFromItem(attachment);
        } else {
            System.err.println("Attachment of category " + attachment.getCategory() + " installed, remove it first");
        }

    }

    ItemAttachment removeAttachment(AttachmentCategory attachmentCategory, PlayerWeaponInstance weaponInstance) {
        int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
        int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];
        ItemAttachment currentAttachment = null;
        if (activeAttachmentIdForThisCategory > 0) {
            currentAttachment = (ItemAttachment) Item.getItemById(activeAttachmentIdForThisCategory);
        }

        if (currentAttachment != null && currentAttachment.getRemove() != null) {
            currentAttachment.getRemove()
                .apply(currentAttachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
        }

        if (currentAttachment != null) {
            activeAttachmentIds[attachmentCategory.ordinal()] = -1;
            weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
        }

        return currentAttachment;
    }

    public static ItemAttachment getActiveAttachment(AttachmentCategory category, PlayerWeaponInstance weaponInstance) {
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

    public static boolean isActiveAttachment(ItemAttachment attachment, PlayerWeaponInstance weaponInstance) {
        int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
        return Arrays.stream(activeAttachmentIds)
            .anyMatch((attachmentId) -> { return attachment == Item.getItemById(attachmentId); });
    }

    public boolean isSilencerOn(PlayerWeaponInstance weaponInstance) {
        int[] activeAttachmentsIds = weaponInstance.getActiveAttachmentIds();
        int activeAttachmentIdForThisCategory = activeAttachmentsIds[AttachmentCategory.SILENCER.ordinal()];
        return activeAttachmentIdForThisCategory > 0;
    }

    public ItemAttachment getActiveAttachment(PlayerWeaponInstance weaponInstance, AttachmentCategory category) {
        return weaponInstance.getAttachmentItemWithCategory(category);
    }

    static {
        TypeRegistry.getInstance()
            .register(WeaponAttachmentAspect.EnterAttachmentModePermit.class);
        TypeRegistry.getInstance()
            .register(WeaponAttachmentAspect.ExitAttachmentModePermit.class);
        TypeRegistry.getInstance()
            .register(WeaponAttachmentAspect.ChangeAttachmentPermit.class);
    }

    public static class ChangeAttachmentPermit extends Permit<WeaponState> {

        AttachmentCategory attachmentCategory;

        public ChangeAttachmentPermit() {}

        public ChangeAttachmentPermit(AttachmentCategory attachmentCategory) {
            super(WeaponState.NEXT_ATTACHMENT);
            this.attachmentCategory = attachmentCategory;
        }

        public void init(ByteBuf buf) {
            super.init(buf);
            this.attachmentCategory = AttachmentCategory.values()[buf.readInt()];
        }

        public void serialize(ByteBuf buf) {
            super.serialize(buf);
            buf.writeInt(this.attachmentCategory.ordinal());
        }
    }

    public static class ExitAttachmentModePermit extends Permit<WeaponState> {

        public ExitAttachmentModePermit() {}

        public ExitAttachmentModePermit(WeaponState state) {
            super(state);
        }
    }

    public static class EnterAttachmentModePermit extends Permit<WeaponState> {

        public EnterAttachmentModePermit() {}

        public EnterAttachmentModePermit(WeaponState state) {
            super(state);
        }
    }

    private static class AttachmentLookupResult {

        CompatibleAttachment compatibleAttachment;
        int index;

        private AttachmentLookupResult() {
            this.index = -1;
        }

    }
}
