package com.gtnewhorizon.newgunrizons.client.handlers;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.items.Updatable;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientTickHandler {

    private static final AttributeModifier SLOW_DOWN_WHILE_ZOOMING = new AttributeModifier(
        UUID.randomUUID(),
        "Slow Down While Zooming",
        -0.5D,
        2).setSaved(false);

    public ClientTickHandler() {}

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player != null) {
                Item item = getHeldItem(player);
                if (item instanceof Updatable) {
                    ((Updatable) item).update(player);
                }
            }
        } else if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            ItemInstanceRegistry.INSTANCE.update(player);
            ItemWeaponInstance weaponInstance = ItemInstanceRegistry.getMainHeldWeapon();
            if (weaponInstance != null) {
                if (player.isSprinting()) {
                    weaponInstance.setAimed(false);
                }
                if (weaponInstance.isAimed()) {
                    this.slowPlayerDown(player);
                } else {
                    this.restorePlayerSpeed(player);
                }
            } else if (player != null) {
                this.restorePlayerSpeed(player);
            }
        }
    }

    private void restorePlayerSpeed(EntityPlayer player) {
        if (player.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .getModifier(SLOW_DOWN_WHILE_ZOOMING.getID()) != null) {
            player.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .removeModifier(SLOW_DOWN_WHILE_ZOOMING);
        }
    }

    private void slowPlayerDown(EntityPlayer player) {
        if (player.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .getModifier(SLOW_DOWN_WHILE_ZOOMING.getID()) == null) {
            player.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .applyModifier(SLOW_DOWN_WHILE_ZOOMING);
        }
    }

    private static Item getHeldItem(EntityPlayer player) {
        ItemStack itemStack = player.getHeldItem();
        return itemStack != null ? itemStack.getItem() : null;
    }
}
