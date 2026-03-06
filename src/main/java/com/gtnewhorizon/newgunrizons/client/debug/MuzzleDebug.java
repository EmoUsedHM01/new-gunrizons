package com.gtnewhorizon.newgunrizons.client.debug;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MuzzleDebug {

    @Getter
    private static boolean enabled = false;
    @Getter
    private static float distanceOffset = 0.0F;
    @Getter
    private static float xOffset = 0.0F;
    @Getter
    private static float yOffset = 0.0F;
    private static final float STEP = 0.01F;

    private static boolean wasKp8 = false;
    private static boolean wasKp2 = false;
    private static boolean wasKp4 = false;
    private static boolean wasKp6 = false;
    private static boolean wasKp7 = false;
    private static boolean wasKp9 = false;
    private static boolean wasKp5 = false;

    public static void toggle() {
        enabled = !enabled;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            if (enabled) {
                mc.thePlayer.addChatMessage(new ChatComponentText(
                    EnumChatFormatting.GOLD + "[MuzzleDebug] " + EnumChatFormatting.GREEN + "ON"
                        + EnumChatFormatting.GRAY + " | KP8/2=Y KP4/6=X KP7/9=Dist KP5=Print"));
            } else {
                mc.thePlayer.addChatMessage(new ChatComponentText(
                    EnumChatFormatting.GOLD + "[MuzzleDebug] " + EnumChatFormatting.RED + "OFF"));
            }
        }
    }

    public static void tick() {
        if (!enabled) return;

        boolean kp8 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8);
        boolean kp2 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2);
        boolean kp4 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4);
        boolean kp6 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6);
        boolean kp7 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7);
        boolean kp9 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9);
        boolean kp5 = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5);
        if (kp8 && !wasKp8) yOffset -= STEP;
        if (kp2 && !wasKp2) yOffset += STEP;
        if (kp4 && !wasKp4) xOffset -= STEP;
        if (kp6 && !wasKp6) xOffset += STEP;
        if (kp7 && !wasKp7) distanceOffset -= STEP;
        if (kp9 && !wasKp9) distanceOffset += STEP;

        if (kp5 && !wasKp5) {
            printToChat();
        }

        wasKp8 = kp8;
        wasKp2 = kp2;
        wasKp4 = kp4;
        wasKp6 = kp6;
        wasKp7 = kp7;
        wasKp9 = kp9;
        wasKp5 = kp5;
    }

    public static void renderOverlay(ScaledResolution resolution) {
        if (!enabled) return;

        FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
        int x = 4;
        int y = 4;
        int lineHeight = 10;

        fr.drawStringWithShadow(EnumChatFormatting.GOLD + "Muzzle Debug", x, y, 0xFFFFFF);
        y += lineHeight;
        fr.drawStringWithShadow(String.format("Distance: %.2f (KP7/9)", distanceOffset), x, y, 0xCCCCCC);
        y += lineHeight;
        fr.drawStringWithShadow(String.format("X Offset: %.2f (KP4/6)", xOffset), x, y, 0xCCCCCC);
        y += lineHeight;
        fr.drawStringWithShadow(String.format("Y Offset: %.2f (KP8/2)", yOffset), x, y, 0xCCCCCC);
        y += lineHeight;
        fr.drawStringWithShadow("KP5 = Print to chat", x, y, 0x888888);
    }

    private static void printToChat() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;

        mc.thePlayer.addChatMessage(new ChatComponentText(
            EnumChatFormatting.GOLD + "[MuzzleDebug] "
                + EnumChatFormatting.WHITE + String.format("dist=%.2f x=%.2f y=%.2f", distanceOffset, xOffset, yOffset)));
    }
}
