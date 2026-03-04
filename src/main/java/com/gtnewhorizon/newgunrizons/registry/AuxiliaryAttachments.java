package com.gtnewhorizon.newgunrizons.registry;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
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
                    GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.6000000238418579D, 0.699999988079071D, 0.75D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AR15CarryHandle) {
                    GL11.glTranslatef(0.1F, 0.0F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.699999988079071D, 0.699999988079071D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AR15CarryHandle) {
                    GL11.glTranslatef(-1.6F, -0.5F, 1.2F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.30000001192092896D, 0.5D, 0.5D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCraftingRecipe(" AA", "F F", 'A', "ingotSteel", 'F', CommonProxy.SteelPlate)
            .withName("AR15Iron")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
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
                    GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.6000000238418579D, 0.699999988079071D, 0.75D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M16A1CarryHandle) {
                    GL11.glTranslatef(0.1F, 0.0F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.699999988079071D, 0.699999988079071D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M16A1CarryHandle) {
                    GL11.glTranslatef(-1.6F, -0.5F, 1.2F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.30000001192092896D, 0.5D, 0.5D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withCraftingRecipe(" AA", "FAF", 'A', "ingotSteel", 'F', CommonProxy.SteelPlate)
            .withName("M16A1CarryHandle")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
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
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M3A1sight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6)
            .withModel(new M3A1rearsight(), "M3A1greasegun.png")
            .withModel(new M3A1frontsight(), "M3A1greasegun.png")
            .withName("M3A1sight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        LeeEnfieldSMLESight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new LeeEnfieldSMLEsight(), "AK12.png")
            .withName("LeeEnfieldSMLESight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        PPSHRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new PPSHRearSight(), "PPSH41.png")
            .withName("PPSHRearSight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        SpringfieldRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new SpringfieldRearSight(), "AK12.png")
            .withName("SpringfieldRearSight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        G98RearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new G98RearSight(), "AK12.png")
            .withName("G98RearSight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1A1rearsight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M1A1rearsight(), "GunmetalTexture.png")
            .withName("M1A1rearsight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1GarandRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new m1garandrearsight(), "GunmetalTexture.png")
            .withName("M1GarandRearSight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1928rearsight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M1928rearsight(), "GunmetalTexture.png")
            .withName("M1928rearsight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1Garandaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M1GarandAction(), "NATOMag1.png")
            .withName("M1Garandaction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Tec9Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new Tec9Action(), "AK12.png")
            .withName("Tec9Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M249Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M249Action(), "M249.png")
            .withName("M249Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M249Cover = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new M249Cover(), "M249.png")
            .withModel(new M249RearSight(), "AK12.png")
            .withName("M249Cover")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        LeeEnfieldSMLEaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new LeeEnfieldSMLEAction(), "LeeEnfieldSMLE.png")
            .withName("LeeEnfieldSMLEaction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        LeeEnfieldClip = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new LeeEnfieldClip(), "sksstripper.png")
            .withName("LeeEnfieldClip")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        LeeEnfieldClipBullets = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new LeeEnfieldClipBullets(), "sksstripper.png")
            .withName("LeeEnfieldClipBullets")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M3A1action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new M3A1GreaseGunAction(), "M3A1GreaseGun.png")
            .withName("M3A1GreaseGun")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        SpringfieldAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new SpringfieldAction(), "m1903a3.png")
            .withName("SpringfieldAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Kar98Kaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new Kar98Kaction(), "Kar98K.png")
            .withName("Kar98Kaction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Gewehr98Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new Gewehr98Action(), "Gewehr98.png")
            .withName("Gewehr98Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        STG44Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new STG44Action(), "STG44.png")
            .withName("STG44Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1GarandMag1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1GarandMag1(), "M1GarandMag.png")
            .withName("M1GarandMag1")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1GarandMag2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M1GarandMag2(), "M1GarandMag.png")
            .withName("M1GarandMag2")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        LugerAction1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new LugerAction1(), "LugerP08.png")
            .withName("LugerAction1")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        LugerAction2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new LugerAction2(), "LugerP08.png")
            .withName("LugerAction2")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        WebleyCylinder = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new WebleyCylinder(), "Webley.png")
            .withName("WebleyCylinder")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        WebleyBullets = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new WebleyBullets(), "Webley.png")
            .withName("WebleyBullets")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        SKSmag1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new SKSstripper(), "sksstripper.png")
            .withName("SKSmag1")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        SKSmag2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new SKSstripper2(), "sksstripper.png")
            .withName("SKSmag2")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        SKSaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new SKSaction(), "NATOmag1.png")
            .withName("SKSaction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Bullet = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6)
            .withModel(new BulletBig(), "Bullet.png")
            .withName("Bullet")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        ExtraAR = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new AR15Iron(), "AK12.png")
            .withModel(new FALIron(), "AK12.png")
            .withModel(new M4Iron1(), "AK12.png")
            .withModel(new M4Iron2(), "AK12.png")
            .withName("ExtraAR")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        DupletBarrels = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new DupletBarrels(), "Duplet.png")
            .withRenderablePart()
            .withName("DupletBarrels")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        SVT40action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new SVT40action(), "SVT40.png")
            .withName("SVT40action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        GlockTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new GlockTop(), "GlockTop.png")
            .withName("GlockTop")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        P99Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P99Slide(), "P99.png")
            .withName("P99Slide")
            .withModel(new P2000rearsight(), "usp45rearsight.png")
            .withModel(new P226frontsight(), "usp45frontsight.png")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        R700action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new R700action(), "R700action.png")
            .withName("R700action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1A1action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1A1action(), "M1A1Thompson.png")
            .withName("M1A1action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1928action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1928action(), "gunmetaltexture.png")
            .withName("M1928action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Saiga12action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new Saiga12action(), "ak12.png")
            .withName("Saiga12action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        MP18action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new MP18action(), "MP18.png")
            .withName("MP18action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        PPSH41action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new PPSH41action(), "PPSH41.png")
            .withName("PPSH41action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Type100action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Type100action(), "PPSH41.png")
            .withName("Type100action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1CarbineAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1CarbineAction(), "M1Carbine.png")
            .withName("M1CarbineAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1CarbineRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M1CarbineRearSight(), "AK12.png")
            .withName("M1CarbineRearSight")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Glock21Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Glock21Slide(), "Glock21Slide.png")
            .withName("Glock21Slide")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Glock32Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Glock32Slide(), "Glock32Slide.png")
            .withName("Glock32Slide")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        MP40action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new MP40Action(), "MP40.png")
            .withName("MP40action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        G18Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new GlockTop(), "G18Top.png")
            .withName("G18Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M9Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M9Top(), "M9Top.png")
            .withModel(new M9rearsight(), "m9rearsight.png")
            .withModel(new m9frontsight(), "m9frontsight.png")
            .withName("M9Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AK12IronSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AK12IronSight(), "GunmetalTexture.png")
            .withName("AK12IronSight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M9SDsuppressor = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new Suppressor(), "GunmetalTexture.png")
            .withName("M9SDsuppressor")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        P2000Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P2000Top(), "P2000Top.png")
            .withModel(new P2000rearsight(), "p2000rearsight.png")
            .withModel(new P226frontsight(), "p226frontsight.png")
            .withName("P2000Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        DeagleTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new DeagleTop(), "Deagle.png")
            .withName("DeagleTop")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Deagle44Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new DeagleTop(), "Deagle44.png")
            .withName("Deagle44Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        KSGPump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new KSG12Pump(), "NATOMag1.png")
            .withName("KSGPump")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        L115Bolt1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new L96Action(), "L96Action.png")
            .withName("L96Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        SV98Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new SV98Action(), "SV98Action.png")
            .withName("SV98Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        L115Bolt2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new L115Bolt2(), "AK12.png")
            .withName("LP115Bolt2")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        MosinBolt = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new MosinBolt(), "mosinbolt.png")
            .withName("MosinBolt")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        MosinBolt2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new MosinBolt2(), "mosinbolt.png")
            .withName("MosinBolt2")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        RevolverCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new MagnumCase(), "MagnumCase.png")
            .withName("RevolverCase")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        PythonCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new MagnumCase(), "PythonCase.png")
            .withName("PythonCase")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        R870Pump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new RemingtonPump(), "Remington870.png")
            .withName("R870Pump")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        R870PumpTac = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Remington870TacPump(), "Remington870Tactical.png")
            .withName("R870PumpTac")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M1911Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M1911Top(), "M1911.png")
            .withModel(new M1911frontsight(), "m1911frontsight")
            .withModel(new M1911rearsight(), "m1911rearsight")
            .withName("M1911Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Taurus1911Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new Taurus1911Slide(), "Taurus1911.png")
            .withModel(new M1911frontsight(), "m1911frontsight")
            .withModel(new M1911rearsight(), "m1911rearsight")
            .withName("Taurus1911Slide")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        USP45Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new USP45Top(), "USP45Top.png")
            .withModel(new P2000rearsight(), "usp45rearsight.png")
            .withModel(new P226frontsight(), "usp45frontsight.png")
            .withName("USP45Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        MakarovTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new MakarovTop(), "MakarovPM.png")
            .withModel(new makarovrearsight(), "makarovrearsight.png")
            .withModel(new makarovfrontsight(), "makarovfrontsight.png")
            .withName("MakarovTop")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        Saiga12sights = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new makarovrearsight(), "makarovrearsight.png")
            .withModel(new makarovfrontsight(), "makarovfrontsight.png")
            .withName("Saiga12sights")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FNP90Sight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new FNP90Sight(), "AK12.png")
            .withModel(new Reflex2(), "Reflex2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof FNP90Sight) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
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
                    GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof FNP90Sight) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("FNP90Sight")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M14Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M14Rail(), "GunmetalTexture.png")
            .withName("M14Rail")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M14Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new M14Action(), "AK12.png")
            .withName("M14Action")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .withRenderablePart()
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M14Action2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new M14Action2(), "AK12.png")
            .withName("M14Action2")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .withRenderablePart()
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FamasCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new FamasCarryHandle(), "AK12.png")
            .withName("FamasCarryHandle")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FelinCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new FelinCarryHandle(), "AK12.png")
            .withName("FelinCarryHandle")
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        P30Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P2000Top(), "P30Top.png")
            .withName("P30Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        P225Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P225Top(), "P225Top.png")
            .withName("P225Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        P226Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new P225Top(), "P226Top.png")
            .withModel(new P226rearsight(), "p226rearsight.png")
            .withModel(new P226frontsight(), "p226frontsight.png")
            .withName("P226Top")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        MP5KGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new Grip2(), "GunmetalTexture.png")
            .withName("MP5KGrip")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        HecateIIBoltAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new HecateIIBoltAction(), "AK12.png")
            .withName("HecateIIBoltAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AR15Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AR15Action(), "AK12.png")
            .withName("AR15Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M110action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AR15Action(), "M110.png")
            .withName("M110action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        BushmasterACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new ACRAction(), "AK12.png")
            .withModel(new ACRAction2(), "AK12.png")
            .withName("BushmasterACRAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        RemingtonACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new ACRAction(), "ACR.png")
            .withModel(new ACRAction2(), "AK12.png")
            .withName("RemingtonACRAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AKIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new AKiron3(), "AK12.png")
            .withName("AKIron3")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AKpart = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new AKpart(), "AK12.png")
            .withName("AKpart")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AKpart2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AKpart(), "AK12.png")
            .withName("AKpart2")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AKS74UIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new AKS74UIron(), "AK12.png")
            .withName("AKS74UIron")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AKRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withName("AKRail")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AUGRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withModel(new AKRail2(), "AK12.png")
            .withModel(new AKRail3(), "AK12.png")
            .withModel(new AKRail4(), "AK12.png")
            .withName("AUGRail")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        BushmasterACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withModel(new AKRail2(), "AK12.png")
            .withModel(new AKRail3(), "AK12.png")
            .withModel(new AKRail4(), "AK12.png")
            .withModel(new AKRail5(), "AK12.png")
            .withName("BushmasterACRRail")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        RemingtonACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "ACR.png")
            .withModel(new AKRail2(), "ACR.png")
            .withModel(new AKRail3(), "ACR.png")
            .withModel(new AKRail4(), "ACR.png")
            .withModel(new AKRail5(), "ACR.png")
            .withName("RemingtonACRRail")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M4Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new AKRail(), "AK12.png")
            .withModel(new AKRail2(), "AK12.png")
            .withModel(new AKRail3(), "AK12.png")
            .withModel(new AKRail4(), "AK12.png")
            .withModel(new AKRail5(), "AK12.png")
            .withName("M4Rail")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        G36Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new G36Rail(), "AK12.png")
            .withName("G36Rail")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AKaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AKaction(), "AK12.png")
            .withName("AKaction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AN94action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AN94action(), "AK12.png")
            .withName("AN94action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        VSSVintorezAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new VSSVintorezAction(), "AK12.png")
            .withName("VSSVintorezAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AK12action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AK12action(), "AK12.png")
            .withName("AK12action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        M107action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new M107action(), "M107.png")
            .withName("M107action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        ScarAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new ScarAction(), "AK12.png")
            .withName("ScarAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        G36Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new G36Action(), "AK12.png")
            .withName("G36Action")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FamasAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new FamasAction(), "AK12.png")
            .withName("FamasAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        AUGAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new AUGAction(), "AK12.png")
            .withName("AUGAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FelinAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new FelinAction(), "AK12.png")
            .withName("FelinAction")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FamasBipod1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new FamasBipod(), "AK12.png")
            .withName("FamasBipod1")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
        FamasBipod2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new FamasBipod(), "AK12.png")
            .withName("FamasBipod2")
            .withRenderablePart()
            .withModId("newgunrizons")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT);
    }
}
