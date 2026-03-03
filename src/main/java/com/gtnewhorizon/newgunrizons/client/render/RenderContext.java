package com.gtnewhorizon.newgunrizons.client.render;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.util.vector.Matrix4f;

import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.animation.MatrixHelper;
import com.gtnewhorizon.newgunrizons.client.animation.PartPositionProvider;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;

import lombok.Getter;
import lombok.Setter;

public class RenderContext implements PartPositionProvider {

    @Getter
    private EntityLivingBase player;
    private ItemStack itemStack;
    @Setter
    @Getter
    private float limbSwing;
    @Setter
    @Getter
    private float flimbSwingAmount;
    @Setter
    @Getter
    private float ageInTicks;
    @Setter
    @Getter
    private float netHeadYaw;
    @Setter
    @Getter
    private float headPitch;
    @Setter
    @Getter
    private float scale;
    @Setter
    @Getter
    private float transitionProgress;
    private TransformType compatibleTransformType;
    @Setter
    @Getter
    private RenderableState fromState;
    @Setter
    @Getter
    private RenderableState toState;
    @Getter
    private final ModContext modContext;
    @Setter
    @Getter
    private PlayerItemInstance<?> playerItemInstance;
    private final Map<Part, Matrix4f> attachablePartPositions;

    public RenderContext(ModContext modContext, EntityLivingBase player, ItemStack itemStack) {
        this.modContext = modContext;
        this.player = player;
        this.itemStack = itemStack;
        this.attachablePartPositions = new HashMap<>();
    }

    public void setPlayer(EntityPlayer player) {
        this.player = player;
    }

    public void setWeapon(ItemStack weapon) {
        this.itemStack = weapon;
    }

    public ItemStack getWeapon() {
        return this.itemStack;
    }

    public TransformType getTransformType() {
        return this.compatibleTransformType;
    }

    public void setTransformType(TransformType compatibleTransformType) {
        this.compatibleTransformType = compatibleTransformType;
    }

    public PlayerWeaponInstance getWeaponInstance() {
        if (this.playerItemInstance instanceof PlayerWeaponInstance) {
            return (PlayerWeaponInstance) this.playerItemInstance;
        } else {
            PlayerItemInstance<?> itemInstance = this.modContext.getPlayerItemInstanceRegistry()
                .getItemInstance(this.player, this.itemStack);
            return itemInstance instanceof PlayerWeaponInstance ? (PlayerWeaponInstance) itemInstance : null;
        }
    }

    public void capturePartPosition(Part part) {
        this.attachablePartPositions.put(part, MatrixHelper.captureMatrix());
    }

    public Matrix4f getPartPosition(Object part) {
        if (part == null) {
            part = Part.MAIN_ITEM;
        }

        return this.attachablePartPositions.get(part);
    }
}
