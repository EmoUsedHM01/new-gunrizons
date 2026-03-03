package com.gtnewhorizon.newgunrizons.config;

import net.minecraft.entity.EntityLivingBase;

public interface PlayerContext {

    EntityLivingBase getPlayer();

    void setPlayer(EntityLivingBase var1);
}
