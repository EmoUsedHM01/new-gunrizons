package com.gtnewhorizon.newgunrizons.registry;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.model.action.ACRAction;
import com.gtnewhorizon.newgunrizons.model.action.ACRAction2;
import com.gtnewhorizon.newgunrizons.model.action.AK12action;
import com.gtnewhorizon.newgunrizons.model.action.AKaction;
import com.gtnewhorizon.newgunrizons.model.action.AN94action;
import com.gtnewhorizon.newgunrizons.model.action.AR15Action;
import com.gtnewhorizon.newgunrizons.model.action.AUGAction;
import com.gtnewhorizon.newgunrizons.model.action.DeagleTop;
import com.gtnewhorizon.newgunrizons.model.action.DupletBarrels;
import com.gtnewhorizon.newgunrizons.model.action.FamasAction;
import com.gtnewhorizon.newgunrizons.model.action.FelinAction;
import com.gtnewhorizon.newgunrizons.model.action.G36Action;
import com.gtnewhorizon.newgunrizons.model.action.Gewehr98Action;
import com.gtnewhorizon.newgunrizons.model.action.Glock21Slide;
import com.gtnewhorizon.newgunrizons.model.action.Glock32Slide;
import com.gtnewhorizon.newgunrizons.model.action.GlockTop;
import com.gtnewhorizon.newgunrizons.model.action.HecateIIBoltAction;
import com.gtnewhorizon.newgunrizons.model.action.KSG12Pump;
import com.gtnewhorizon.newgunrizons.model.action.Kar98Kaction;
import com.gtnewhorizon.newgunrizons.model.action.L115Bolt2;
import com.gtnewhorizon.newgunrizons.model.action.L96Action;
import com.gtnewhorizon.newgunrizons.model.action.LeeEnfieldSMLEAction;
import com.gtnewhorizon.newgunrizons.model.action.LugerAction1;
import com.gtnewhorizon.newgunrizons.model.action.LugerAction2;
import com.gtnewhorizon.newgunrizons.model.action.M107action;
import com.gtnewhorizon.newgunrizons.model.action.M14Action;
import com.gtnewhorizon.newgunrizons.model.action.M14Action2;
import com.gtnewhorizon.newgunrizons.model.action.M1911Top;
import com.gtnewhorizon.newgunrizons.model.action.M1928action;
import com.gtnewhorizon.newgunrizons.model.action.M1A1action;
import com.gtnewhorizon.newgunrizons.model.action.M1CarbineAction;
import com.gtnewhorizon.newgunrizons.model.action.M1GarandAction;
import com.gtnewhorizon.newgunrizons.model.action.M249Action;
import com.gtnewhorizon.newgunrizons.model.action.M249Cover;
import com.gtnewhorizon.newgunrizons.model.action.M3A1GreaseGunAction;
import com.gtnewhorizon.newgunrizons.model.action.M9Top;
import com.gtnewhorizon.newgunrizons.model.action.MP18action;
import com.gtnewhorizon.newgunrizons.model.action.MP40Action;
import com.gtnewhorizon.newgunrizons.model.action.MakarovTop;
import com.gtnewhorizon.newgunrizons.model.action.MosinBolt;
import com.gtnewhorizon.newgunrizons.model.action.MosinBolt2;
import com.gtnewhorizon.newgunrizons.model.action.P2000Top;
import com.gtnewhorizon.newgunrizons.model.action.P225Top;
import com.gtnewhorizon.newgunrizons.model.action.P99Slide;
import com.gtnewhorizon.newgunrizons.model.action.PPSH41action;
import com.gtnewhorizon.newgunrizons.model.action.R700action;
import com.gtnewhorizon.newgunrizons.model.action.Remington870TacPump;
import com.gtnewhorizon.newgunrizons.model.action.RemingtonPump;
import com.gtnewhorizon.newgunrizons.model.action.SKSaction;
import com.gtnewhorizon.newgunrizons.model.action.STG44Action;
import com.gtnewhorizon.newgunrizons.model.action.SV98Action;
import com.gtnewhorizon.newgunrizons.model.action.SVT40action;
import com.gtnewhorizon.newgunrizons.model.action.Saiga12action;
import com.gtnewhorizon.newgunrizons.model.action.ScarAction;
import com.gtnewhorizon.newgunrizons.model.action.SpringfieldAction;
import com.gtnewhorizon.newgunrizons.model.action.Taurus1911Slide;
import com.gtnewhorizon.newgunrizons.model.action.Tec9Action;
import com.gtnewhorizon.newgunrizons.model.action.Type100action;
import com.gtnewhorizon.newgunrizons.model.action.USP45Top;
import com.gtnewhorizon.newgunrizons.model.action.VSSVintorezAction;
import com.gtnewhorizon.newgunrizons.model.action.WebleyCylinder;
import com.gtnewhorizon.newgunrizons.model.ammo.BulletBig;
import com.gtnewhorizon.newgunrizons.model.ammo.LeeEnfieldClipBullets;
import com.gtnewhorizon.newgunrizons.model.ammo.MagnumCase;
import com.gtnewhorizon.newgunrizons.model.ammo.WebleyBullets;
import com.gtnewhorizon.newgunrizons.model.grip.FamasBipod;
import com.gtnewhorizon.newgunrizons.model.grip.Grip2;
import com.gtnewhorizon.newgunrizons.model.magazine.LeeEnfieldClip;
import com.gtnewhorizon.newgunrizons.model.magazine.M1GarandMag1;
import com.gtnewhorizon.newgunrizons.model.magazine.M1GarandMag2;
import com.gtnewhorizon.newgunrizons.model.magazine.SKSstripper;
import com.gtnewhorizon.newgunrizons.model.magazine.SKSstripper2;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail2;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail3;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail4;
import com.gtnewhorizon.newgunrizons.model.misc.AKRail5;
import com.gtnewhorizon.newgunrizons.model.misc.AKpart;
import com.gtnewhorizon.newgunrizons.model.misc.G36Rail;
import com.gtnewhorizon.newgunrizons.model.misc.M14Rail;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor;
import com.gtnewhorizon.newgunrizons.model.sight.AK12IronSight;
import com.gtnewhorizon.newgunrizons.model.sight.AK47iron;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron1;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron2;
import com.gtnewhorizon.newgunrizons.model.sight.AKS74UIron;
import com.gtnewhorizon.newgunrizons.model.sight.AKiron3;
import com.gtnewhorizon.newgunrizons.model.sight.AR15CarryHandle;
import com.gtnewhorizon.newgunrizons.model.sight.AR15Iron;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.FNP90Sight;
import com.gtnewhorizon.newgunrizons.model.sight.FamasCarryHandle;
import com.gtnewhorizon.newgunrizons.model.sight.FelinCarryHandle;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.G98RearSight;
import com.gtnewhorizon.newgunrizons.model.sight.LeeEnfieldSMLEsight;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M16A1CarryHandle;
import com.gtnewhorizon.newgunrizons.model.sight.M1911frontsight;
import com.gtnewhorizon.newgunrizons.model.sight.M1911rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.M1928rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.M1A1rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.M1CarbineRearSight;
import com.gtnewhorizon.newgunrizons.model.sight.M249RearSight;
import com.gtnewhorizon.newgunrizons.model.sight.M3A1frontsight;
import com.gtnewhorizon.newgunrizons.model.sight.M3A1rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.M9rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.P2000rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.P226frontsight;
import com.gtnewhorizon.newgunrizons.model.sight.P226rearsight;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.PPSHRearSight;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex2;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.sight.SpringfieldRearSight;
import com.gtnewhorizon.newgunrizons.model.sight.m1garandrearsight;
import com.gtnewhorizon.newgunrizons.model.sight.m9frontsight;
import com.gtnewhorizon.newgunrizons.model.sight.makarovfrontsight;
import com.gtnewhorizon.newgunrizons.model.sight.makarovrearsight;

public class AuxiliaryAttachments {

    public static ItemAttachment M1CarbineAction;
    public static ItemAttachment FNP90Sight;
    public static ItemAttachment AR15Iron;
    public static ItemAttachment Extra;
    public static ItemAttachment ExtraAR;
    public static ItemAttachment GlockTop;
    public static ItemAttachment Glock21Slide;
    public static ItemAttachment Glock32Slide;
    public static ItemAttachment G18Top;
    public static ItemAttachment M9Top;
    public static ItemAttachment P2000Top;
    public static ItemAttachment DeagleTop;
    public static ItemAttachment Deagle44Top;
    public static ItemAttachment KSGPump;
    public static ItemAttachment L115Bolt1;
    public static ItemAttachment L115Bolt2;
    public static ItemAttachment SV98Action;
    public static ItemAttachment RevolverCase;
    public static ItemAttachment PythonCase;
    public static ItemAttachment R870Pump;
    public static ItemAttachment R870PumpTac;
    public static ItemAttachment M1911Top;
    public static ItemAttachment Taurus1911Slide;
    public static ItemAttachment M9SDsuppressor;
    public static ItemAttachment MosinBolt;
    public static ItemAttachment MosinBolt2;
    public static ItemAttachment USP45Top;
    public static ItemAttachment MakarovTop;
    public static ItemAttachment AK12IronSight;
    public static ItemAttachment M14Rail;
    public static ItemAttachment P225Top;
    public static ItemAttachment P226Top;
    public static ItemAttachment P30Top;
    public static ItemAttachment MP5KGrip;
    public static ItemAttachment HecateIIBoltAction;
    public static ItemAttachment AR15Action;
    public static ItemAttachment BushmasterACRAction;
    public static ItemAttachment RemingtonACRAction;
    public static ItemAttachment AKIron;
    public static ItemAttachment AKpart;
    public static ItemAttachment AKpart2;
    public static ItemAttachment AKaction;
    public static ItemAttachment AN94action;
    public static ItemAttachment VSSVintorezAction;
    public static ItemAttachment AK12action;
    public static ItemAttachment AKS74UIron;
    public static ItemAttachment AKRail;
    public static ItemAttachment AUGRail;
    public static ItemAttachment BushmasterACRRail;
    public static ItemAttachment RemingtonACRRail;
    public static ItemAttachment M4Rail;
    public static ItemAttachment ScarAction;
    public static ItemAttachment G36Rail;
    public static ItemAttachment G36Action;
    public static ItemAttachment FamasCarryHandle;
    public static ItemAttachment FamasAction;
    public static ItemAttachment AUGAction;
    public static ItemAttachment FamasBipod1;
    public static ItemAttachment FamasBipod2;
    public static ItemAttachment FelinAction;
    public static ItemAttachment FelinCarryHandle;
    public static ItemAttachment M14Action;
    public static ItemAttachment M14Action2;
    public static ItemAttachment DupletBarrels;
    public static ItemAttachment M107action;
    public static ItemAttachment MP40action;
    public static ItemAttachment Bullet;
    public static ItemAttachment PPSHRearSight;
    public static ItemAttachment M1A1rearsight;
    public static ItemAttachment PPSH41action;
    public static ItemAttachment Type100action;
    public static ItemAttachment M1A1action;
    public static ItemAttachment MP18action;
    public static ItemAttachment R700action;
    public static ItemAttachment M110action;
    public static ItemAttachment M16A1CarryHandle;
    public static ItemAttachment P99Slide;
    public static ItemAttachment Saiga12action;
    public static ItemAttachment Saiga12sights;
    public static ItemAttachment SVT40action;
    public static ItemAttachment M1Garandaction;
    public static ItemAttachment M1GarandMag1;
    public static ItemAttachment M1GarandMag2;
    public static ItemAttachment SKSaction;
    public static ItemAttachment SKSmag1;
    public static ItemAttachment SKSmag2;
    public static ItemAttachment M1CarbineRearSight;
    public static ItemAttachment SpringfieldAction;
    public static ItemAttachment Kar98Kaction;
    public static ItemAttachment SpringfieldRearSight;
    public static ItemAttachment WebleyBullets;
    public static ItemAttachment WebleyCylinder;
    public static ItemAttachment M1928action;
    public static ItemAttachment M1928rearsight;
    public static ItemAttachment LugerAction1;
    public static ItemAttachment LugerAction2;
    public static ItemAttachment M3A1action;
    public static ItemAttachment M3A1sight;
    public static ItemAttachment STG44Action;
    public static ItemAttachment Gewehr98Action;
    public static ItemAttachment G98RearSight;
    public static ItemAttachment M1GarandRearSight;
    public static ItemAttachment LeeEnfieldSMLEaction;
    public static ItemAttachment LeeEnfieldSMLESight;
    public static ItemAttachment LeeEnfieldClip;
    public static ItemAttachment LeeEnfieldClipBullets;
    public static ItemAttachment Tec9Action;
    public static ItemAttachment M249Action;
    public static ItemAttachment M249Cover;

    public static void init() {
        AR15Iron = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new M4Iron1(), "AK12.png")
            .withModel(new M4Iron2(), "AK12.png")
            .withModel(new FALIron(), "AK12.png")
            .withModel(new AR15CarryHandle(), "AK12.png")
            .withInventoryModelPositioning((model, s) -> {
                if (model instanceof AR15CarryHandle) {
                    GL11.glTranslatef(-0.6F, 0.0F, 0.2F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.7D, 0.75D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AR15CarryHandle) {
                    GL11.glTranslatef(0.1F, 0.0F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.7D, 0.7D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AR15CarryHandle) {
                    GL11.glTranslatef(-1.6F, -0.5F, 1.2F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.3D, 0.5D, 0.5D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("AR15Iron")
            .withTextureName("Dummy.png")
            .build();
        M16A1CarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M4Iron1(), "AK12.png")
            .withModel(new M4Iron2(), "AK12.png")
            .withModel(new FALIron(), "AK12.png")
            .withModel(new M16A1CarryHandle(), "AK12.png")
            .withInventoryModelPositioning((model, s) -> {
                if (model instanceof M16A1CarryHandle) {
                    GL11.glTranslatef(-0.6F, 0.0F, 0.2F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.7D, 0.75D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M16A1CarryHandle) {
                    GL11.glTranslatef(0.1F, 0.0F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.7D, 0.7D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M16A1CarryHandle) {
                    GL11.glTranslatef(-1.6F, -0.5F, 1.2F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.3D, 0.5D, 0.5D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("M16A1CarryHandle")
            .withTextureName("Dummy.png")
            .build();
        Extra = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6)
            .withModel(new AKMiron1(), "GunmetalTexture.png")
            .withModel(new AKMiron2(), "GunmetalTexture.png")
            .withModel(new AK47iron(), "GunmetalTexture.png")
            .withModel(new M4Iron1(), "GunmetalTexture.png")
            .withModel(new M4Iron2(), "GunmetalTexture.png")
            .withModel(new P90iron(), "GunmetalTexture.png")
            .withModel(new G36CIron1(), "GunmetalTexture.png")
            .withModel(new G36CIron2(), "GunmetalTexture.png")
            .withModel(new ScarIron1(), "GunmetalTexture.png")
            .withModel(new ScarIron2(), "GunmetalTexture.png")
            .withModel(new FALIron(), "GunmetalTexture.png")
            .withModel(new M14Iron(), "GunmetalTexture.png")
            .withModel(new MP5Iron(), "AK12.png")
            .withName("Extra")
            .withTextureName("Dummy.png")
            .build();
        M3A1sight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6)
            .withModel(new M3A1rearsight(), "M3A1greasegun.png")
            .withModel(new M3A1frontsight(), "M3A1greasegun.png")
            .withName("M3A1sight")
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldSMLESight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new LeeEnfieldSMLEsight(), "AK12.png")
            .withName("LeeEnfieldSMLESight")
            .withTextureName("Dummy.png")
            .build();
        PPSHRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new PPSHRearSight(), "PPSH41.png")
            .withName("PPSHRearSight")
            .withTextureName("Dummy.png")
            .build();
        SpringfieldRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new SpringfieldRearSight(), "AK12.png")
            .withName("SpringfieldRearSight")
            .withTextureName("Dummy.png")
            .build();
        G98RearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new G98RearSight(), "AK12.png")
            .withName("G98RearSight")
            .withTextureName("Dummy.png")
            .build();
        M1A1rearsight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M1A1rearsight(), "GunmetalTexture.png")
            .withName("M1A1rearsight")
            .withTextureName("Dummy.png")
            .build();
        M1GarandRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new m1garandrearsight(), "GunmetalTexture.png")
            .withName("M1GarandRearSight")
            .withTextureName("Dummy.png")
            .build();
        M1928rearsight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M1928rearsight(), "GunmetalTexture.png")
            .withName("M1928rearsight")
            .withTextureName("Dummy.png")
            .build();
        M1Garandaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M1GarandAction(), "NATOMag1.png")
            .withName("M1Garandaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Tec9Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new Tec9Action(), "AK12.png")
            .withName("Tec9Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M249Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M249Action(), "M249.png")
            .withName("M249Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M249Cover = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new M249Cover(), "M249.png")
            .withModel(new M249RearSight(), "AK12.png")
            .withName("M249Cover")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldSMLEaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new LeeEnfieldSMLEAction(), "LeeEnfieldSMLE.png")
            .withName("LeeEnfieldSMLEaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldClip = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new LeeEnfieldClip(), "sksstripper.png")
            .withName("LeeEnfieldClip")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldClipBullets = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new LeeEnfieldClipBullets(), "sksstripper.png")
            .withName("LeeEnfieldClipBullets")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M3A1action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M3A1GreaseGunAction(), "M3A1GreaseGun.png")
            .withName("M3A1GreaseGun")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SpringfieldAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new SpringfieldAction(), "m1903a3.png")
            .withName("SpringfieldAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Kar98Kaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new Kar98Kaction(), "Kar98K.png")
            .withName("Kar98Kaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Gewehr98Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new Gewehr98Action(), "Gewehr98.png")
            .withName("Gewehr98Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        STG44Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new STG44Action(), "STG44.png")
            .withName("STG44Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1GarandMag1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1GarandMag1(), "M1GarandMag.png")
            .withName("M1GarandMag1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1GarandMag2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M1GarandMag2(), "M1GarandMag.png")
            .withName("M1GarandMag2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LugerAction1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new LugerAction1(), "LugerP08.png")
            .withName("LugerAction1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LugerAction2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new LugerAction2(), "LugerP08.png")
            .withName("LugerAction2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        WebleyCylinder = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new WebleyCylinder(), "Webley.png")
            .withName("WebleyCylinder")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        WebleyBullets = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new WebleyBullets(), "Webley.png")
            .withName("WebleyBullets")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SKSmag1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new SKSstripper(), "sksstripper.png")
            .withName("SKSmag1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SKSmag2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new SKSstripper2(), "sksstripper.png")
            .withName("SKSmag2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SKSaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new SKSaction(), "NATOmag1.png")
            .withName("SKSaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Bullet = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6)
            .withModel(new BulletBig(), "Bullet.png")
            .withName("Bullet")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        ExtraAR = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new AR15Iron(), "AK12.png")
            .withModel(new FALIron(), "AK12.png")
            .withModel(new M4Iron1(), "AK12.png")
            .withModel(new M4Iron2(), "AK12.png")
            .withName("ExtraAR")
            .withTextureName("Dummy.png")
            .build();
        DupletBarrels = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new DupletBarrels(), "Duplet.png")
            .withRenderablePart()
            .withName("DupletBarrels")
            .withTextureName("Dummy.png")
            .build();
        SVT40action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new SVT40action(), "SVT40.png")
            .withName("SVT40action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        GlockTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new GlockTop(), "GlockTop.png")
            .withName("GlockTop")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P99Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P99Slide(), "P99.png")
            .withName("P99Slide")
            .withModel(new P2000rearsight(), "usp45rearsight.png")
            .withModel(new P226frontsight(), "usp45frontsight.png")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        R700action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new R700action(), "R700action.png")
            .withName("R700action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1A1action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1A1action(), "M1A1Thompson.png")
            .withName("M1A1action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1928action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1928action(), "gunmetaltexture.png")
            .withName("M1928action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Saiga12action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new Saiga12action(), "ak12.png")
            .withName("Saiga12action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MP18action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new MP18action(), "MP18.png")
            .withName("MP18action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        PPSH41action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new PPSH41action(), "PPSH41.png")
            .withName("PPSH41action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Type100action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Type100action(), "PPSH41.png")
            .withName("Type100action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1CarbineAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1CarbineAction(), "M1Carbine.png")
            .withName("M1CarbineAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1CarbineRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M1CarbineRearSight(), "AK12.png")
            .withName("M1CarbineRearSight")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Glock21Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Glock21Slide(), "Glock21Slide.png")
            .withName("Glock21Slide")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Glock32Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Glock32Slide(), "Glock32Slide.png")
            .withName("Glock32Slide")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MP40action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new MP40Action(), "MP40.png")
            .withName("MP40action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        G18Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new GlockTop(), "G18Top.png")
            .withName("G18Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M9Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M9Top(), "M9Top.png")
            .withModel(new M9rearsight(), "m9rearsight.png")
            .withModel(new m9frontsight(), "m9frontsight.png")
            .withName("M9Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AK12IronSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AK12IronSight(), "GunmetalTexture.png")
            .withName("AK12IronSight")
            .withTextureName("Dummy.png")
            .build();
        M9SDsuppressor = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new Suppressor(), "GunmetalTexture.png")
            .withName("M9SDsuppressor")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P2000Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P2000Top(), "P2000Top.png")
            .withModel(new P2000rearsight(), "p2000rearsight.png")
            .withModel(new P226frontsight(), "p226frontsight.png")
            .withName("P2000Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        DeagleTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new DeagleTop(), "Deagle.png")
            .withName("DeagleTop")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Deagle44Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new DeagleTop(), "Deagle44.png")
            .withName("Deagle44Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        KSGPump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new KSG12Pump(), "NATOMag1.png")
            .withName("KSGPump")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        L115Bolt1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new L96Action(), "L96Action.png")
            .withName("L96Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SV98Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new SV98Action(), "SV98Action.png")
            .withName("SV98Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        L115Bolt2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new L115Bolt2(), "AK12.png")
            .withName("LP115Bolt2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MosinBolt = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new MosinBolt(), "mosinbolt.png")
            .withName("MosinBolt")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MosinBolt2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new MosinBolt2(), "mosinbolt.png")
            .withName("MosinBolt2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        RevolverCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new MagnumCase(), "MagnumCase.png")
            .withName("RevolverCase")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        PythonCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new MagnumCase(), "PythonCase.png")
            .withName("PythonCase")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        R870Pump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new RemingtonPump(), "Remington870.png")
            .withName("R870Pump")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        R870PumpTac = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Remington870TacPump(), "Remington870Tactical.png")
            .withName("R870PumpTac")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1911Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1911Top(), "M1911.png")
            .withModel(new M1911frontsight(), "m1911frontsight")
            .withModel(new M1911rearsight(), "m1911rearsight")
            .withName("M1911Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Taurus1911Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Taurus1911Slide(), "Taurus1911.png")
            .withModel(new M1911frontsight(), "m1911frontsight")
            .withModel(new M1911rearsight(), "m1911rearsight")
            .withName("Taurus1911Slide")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        USP45Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new USP45Top(), "USP45Top.png")
            .withModel(new P2000rearsight(), "usp45rearsight.png")
            .withModel(new P226frontsight(), "usp45frontsight.png")
            .withName("USP45Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MakarovTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new MakarovTop(), "MakarovPM.png")
            .withModel(new makarovrearsight(), "makarovrearsight.png")
            .withModel(new makarovfrontsight(), "makarovfrontsight.png")
            .withName("MakarovTop")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Saiga12sights = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new makarovrearsight(), "makarovrearsight.png")
            .withModel(new makarovfrontsight(), "makarovfrontsight.png")
            .withName("Saiga12sights")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FNP90Sight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new FNP90Sight(), "AK12.png")
            .withModel(new Reflex2(), "Reflex2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof FNP90Sight) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof FNP90Sight) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof FNP90Sight) {
                    GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof FNP90Sight) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("FNP90Sight")
            .withTextureName("Dummy.png")
            .build();
        M14Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M14Rail(), "GunmetalTexture.png")
            .withName("M14Rail")
            .withTextureName("Dummy.png")
            .build();
        M14Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M14Action(), "AK12.png")
            .withName("M14Action")
            .withTextureName("Dummy.png")
            .withRenderablePart()
            .build();
        M14Action2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M14Action2(), "AK12.png")
            .withName("M14Action2")
            .withTextureName("Dummy.png")
            .withRenderablePart()
            .build();
        FamasCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new FamasCarryHandle(), "AK12.png")
            .withName("FamasCarryHandle")
            .withTextureName("Dummy.png")
            .build();
        FelinCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new FelinCarryHandle(), "AK12.png")
            .withName("FelinCarryHandle")
            .withTextureName("Dummy.png")
            .build();
        P30Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P2000Top(), "P30Top.png")
            .withName("P30Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P225Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P225Top(), "P225Top.png")
            .withName("P225Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P226Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P225Top(), "P226Top.png")
            .withModel(new P226rearsight(), "p226rearsight.png")
            .withModel(new P226frontsight(), "p226frontsight.png")
            .withName("P226Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MP5KGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new Grip2(), "GunmetalTexture.png")
            .withName("MP5KGrip")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        HecateIIBoltAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new HecateIIBoltAction(), "AK12.png")
            .withName("HecateIIBoltAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AR15Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AR15Action(), "AK12.png")
            .withName("AR15Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M110action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AR15Action(), "M110.png")
            .withName("M110action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        BushmasterACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new ACRAction(), "AK12.png")
            .withModel(new ACRAction2(), "AK12.png")
            .withName("BushmasterACRAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        RemingtonACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new ACRAction(), "ACR.png")
            .withModel(new ACRAction2(), "AK12.png")
            .withName("RemingtonACRAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AKiron3(), "AK12.png")
            .withName("AKIron3")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKpart = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new AKpart(), "AK12.png")
            .withName("AKpart")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKpart2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AKpart(), "AK12.png")
            .withName("AKpart2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKS74UIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new AKS74UIron(), "AK12.png")
            .withName("AKS74UIron")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withName("AKRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AUGRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withModel(new AKRail2(), "AK12.png")
            .withModel(new AKRail3(), "AK12.png")
            .withModel(new AKRail4(), "AK12.png")
            .withName("AUGRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        BushmasterACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withModel(new AKRail2(), "AK12.png")
            .withModel(new AKRail3(), "AK12.png")
            .withModel(new AKRail4(), "AK12.png")
            .withModel(new AKRail5(), "AK12.png")
            .withName("BushmasterACRRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        RemingtonACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "ACR.png")
            .withModel(new AKRail2(), "ACR.png")
            .withModel(new AKRail3(), "ACR.png")
            .withModel(new AKRail4(), "ACR.png")
            .withModel(new AKRail5(), "ACR.png")
            .withName("RemingtonACRRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M4Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withModel(new AKRail2(), "AK12.png")
            .withModel(new AKRail3(), "AK12.png")
            .withModel(new AKRail4(), "AK12.png")
            .withModel(new AKRail5(), "AK12.png")
            .withName("M4Rail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        G36Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new G36Rail(), "AK12.png")
            .withName("G36Rail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AKaction(), "AK12.png")
            .withName("AKaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AN94action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AN94action(), "AK12.png")
            .withName("AN94action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        VSSVintorezAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new VSSVintorezAction(), "AK12.png")
            .withName("VSSVintorezAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AK12action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AK12action(), "AK12.png")
            .withName("AK12action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M107action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new M107action(), "M107.png")
            .withName("M107action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        ScarAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new ScarAction(), "AK12.png")
            .withName("ScarAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        G36Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new G36Action(), "AK12.png")
            .withName("G36Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FamasAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new FamasAction(), "AK12.png")
            .withName("FamasAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AUGAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AUGAction(), "AK12.png")
            .withName("AUGAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FelinAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new FelinAction(), "AK12.png")
            .withName("FelinAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FamasBipod1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new FamasBipod(), "AK12.png")
            .withName("FamasBipod1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FamasBipod2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new FamasBipod(), "AK12.png")
            .withName("FamasBipod2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
    }
}
