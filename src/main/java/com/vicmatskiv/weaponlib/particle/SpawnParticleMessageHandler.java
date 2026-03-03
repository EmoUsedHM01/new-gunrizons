package com.vicmatskiv.weaponlib.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

import com.vicmatskiv.weaponlib.ModContext;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

import java.util.Objects;

public class SpawnParticleMessageHandler implements IMessageHandler<SpawnParticleMessage, IMessage> {

    private ModContext modContext;
    private double yOffset = 1.0D;

    public SpawnParticleMessageHandler(ModContext modContext) {
        this.modContext = modContext;
    }

    public IMessage onMessage(SpawnParticleMessage message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            for (int i = 0; i < message.getCount(); ++i) {
                if (Objects.requireNonNull(message.getParticleType()) == SpawnParticleMessage.ParticleType.BLOOD) {
                    EntityBreakingFX particle = new EntityBreakingFX(
                        Minecraft.getMinecraft().thePlayer.worldObj,
                        message.getPosX(),
                        message.getPosY() + yOffset,
                        message.getPosZ(),
                        Items.redstone);
                    TextureAtlasSprite sprite = Minecraft.getMinecraft()
                        .getTextureMapBlocks()
                        .getAtlasSprite(
                            modContext.getNamedResource("particle/blood")
                                .toString());
                    particle.setParticleIcon(sprite);
                    Minecraft.getMinecraft().effectRenderer.addEffect(particle);
                }
            }
        }

        return null;
    }
}
