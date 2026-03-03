package com.gtnewhorizon.newgunrizons.server;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.grenade.EntityGrenade;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lombok.Getter;

public class ServerEventHandler {

    private final ModContext modContext;
    @Getter
    private final String modId;

    public ServerEventHandler(ModContext modContext, String modId) {
        this.modContext = modContext;
        this.modId = modId;
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent e) {}

    @SubscribeEvent
    public void onItemToss(ItemTossEvent itemTossEvent) {}

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        if (e.entity instanceof EntityGrenade) {
            ((EntityGrenade) e.entity).setContext(this.modContext);
        }
    }

}
