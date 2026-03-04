package com.gtnewhorizon.newgunrizons.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.util.Pair;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.ItemWeapon;

import lombok.Getter;
import lombok.Setter;

public class ItemAttachment extends Item {

    @Getter
    private final AttachmentCategory category;
    @Getter
    private final String crosshair;
    @Getter
    private final ItemAttachment.ApplyHandler apply;
    @Getter
    private final ItemAttachment.ApplyHandler remove;
    @Getter
    public ItemAttachment.ApplyHandler2 apply2;
    public ItemAttachment.ApplyHandler2 remove2;
    @Getter
    private final List<Pair<ModelBase, String>> texturedModels;
    @Getter
    @Setter
    private CustomRenderer postRenderer;
    @Setter
    @Getter
    private Part renderablePart;
    @Setter
    private String name;
    @Setter
    @Getter
    private Function<ItemStack, String> informationProvider;
    public int maxStackSize;
    private final List<CompatibleAttachment> attachments;
    private final List<ItemWeapon> compatibleWeapons;
    public String textureName;

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        stack.setTagCompound(new NBTTagCompound());
    }

    public ItemAttachment(String modId, AttachmentCategory category, ModelBase model, String textureName,
        String crosshair, ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
        this.texturedModels = new ArrayList<>();
        this.maxStackSize = 1;
        this.attachments = new ArrayList<>();
        this.compatibleWeapons = new ArrayList<>();
        this.category = category;
        this.textureName = textureName.toLowerCase();
        this.crosshair = crosshair != null ? modId + ":" + "textures/crosshairs/" + crosshair + ".png" : null;
        this.apply = apply;
        this.remove = remove;
    }

    public ItemAttachment(String modId, AttachmentCategory category, String crosshair,
        ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
        this.texturedModels = new ArrayList<>();
        this.maxStackSize = 1;
        this.attachments = new ArrayList<>();
        this.compatibleWeapons = new ArrayList<>();
        this.category = category;
        this.crosshair = crosshair != null ? modId + ":" + "textures/crosshairs/" + crosshair + ".png" : null;
        this.apply = apply;
        this.remove = remove;
    }

    public int getItemStackLimit() {
        return this.maxStackSize;
    }

    public Item setTextureName(String name) {
        return this;
    }

    public void addModel(ModelBase model, String textureName) {
        this.texturedModels.add(new Pair<>(model, textureName));
    }

    public ItemAttachment(String modId, AttachmentCategory category, String crosshair) {
        this(modId, category, crosshair, (a, w, p) -> {}, (a, w, p) -> {});
    }

    public ItemAttachment(String modId, AttachmentCategory category, ModelBase attachment, String textureName,
        String crosshair) {
        this(modId, category, attachment, textureName, crosshair, (a, w, p) -> {}, (a, w, p) -> {});
    }

    public void addCompatibleWeapon(ItemWeapon weapon) {
        this.compatibleWeapons.add(weapon);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean flag) {
        if (tooltip != null && this.informationProvider != null) {
            tooltip.add(this.informationProvider.apply(itemStack));
        }
    }

    public void addCompatibleAttachment(CompatibleAttachment attachment) {
        this.attachments.add(attachment);
    }

    public List<CompatibleAttachment> getAttachments() {
        return Collections.unmodifiableList(this.attachments);
    }

    public String toString() {
        return this.name != null ? "Attachment [" + this.name + "]" : super.toString();
    }

    public ItemAttachment.ApplyHandler2 getRemove2() {
        return this.remove2;
    }

    public interface ApplyHandler2 {

        void apply(ItemAttachment attachment, PlayerWeaponInstance weaponInstance);
    }

    public interface ApplyHandler {

        void apply(ItemAttachment attachment, ItemWeapon weapon, EntityLivingBase entity);
    }
}
