package com.gtnewhorizon.newgunrizons.registry;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.attachment.ItemAttachment;
import com.gtnewhorizon.newgunrizons.factory.grenade.FuseGrenadeFactory;
import com.gtnewhorizon.newgunrizons.factory.grenade.ImpactGrenadeFactory;
import com.gtnewhorizon.newgunrizons.grenade.ItemGrenade;
import com.gtnewhorizon.newgunrizons.model.misc.Pin;

public class Grenades {

    public static ItemGrenade FuseGrenade;
    public static ItemGrenade ImpactGrenade;
    public static ItemAttachment GrenadeSafetyPin;

    public static void init(CommonProxy commonProxy) {
        GrenadeSafetyPin = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Pin(), "AK12.png")
            .withName("GrenadeSafetyPin")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FuseGrenade = (new FuseGrenadeFactory()).createGrenade(commonProxy);
        ImpactGrenade = (new ImpactGrenadeFactory()).createGrenade(commonProxy);
    }
}
