package com.gtnewhorizon.newgunrizons.registry;

import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemGrenade;
import com.gtnewhorizon.newgunrizons.items.factories.grenades.FuseGrenadeFactory;
import com.gtnewhorizon.newgunrizons.items.factories.grenades.ImpactGrenadeFactory;
import com.gtnewhorizon.newgunrizons.model.JsonModel;

public class Grenades {

    public static ItemGrenade FuseGrenade;
    public static ItemGrenade ImpactGrenade;
    public static ItemAttachment GrenadeSafetyPin;

    public static void init() {
        GrenadeSafetyPin = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("misc/pin"), "AK12.png")
            .withName("GrenadeSafetyPin")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FuseGrenade = (new FuseGrenadeFactory()).createGrenade();
        ImpactGrenade = (new ImpactGrenadeFactory()).createGrenade();
    }
}
