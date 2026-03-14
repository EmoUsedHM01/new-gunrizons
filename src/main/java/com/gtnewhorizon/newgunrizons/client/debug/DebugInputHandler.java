package com.gtnewhorizon.newgunrizons.client.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.gtnewhorizon.newgunrizons.client.animation.BedrockAnimationController;
import com.gtnewhorizon.newgunrizons.client.input.KeyBindings;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class DebugInputHandler {

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.debugKey.isPressed()) {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.currentScreen == null) {
				EntityPlayer player = mc.thePlayer;
				if (player == null) return;

				ItemStack heldStack = player.getHeldItem();
				if (heldStack == null || !(heldStack.getItem() instanceof ItemWeapon)) return;

				ItemWeapon weapon = (ItemWeapon) heldStack.getItem();
				WeaponRenderer renderer = weapon.getRenderer();
				if (renderer == null) return;

				BedrockAnimationController controller = renderer.getBedrockAnimController();
				if (controller == null) return;

				PositionDebugger.setCurrentController(controller);
				PositionDebugger.setActive(true);
				PositionDebugger.setScreenOpen(true);
				mc.displayGuiScreen(new PositionDebugScreen());
			} else if (mc.currentScreen instanceof PositionDebugScreen) {
				mc.displayGuiScreen(null);
			}
		}
	}
}
