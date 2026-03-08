package com.gtnewhorizon.newgunrizons.registry;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.model.JsonModel;

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
            .withModel(new JsonModel("sight/m4iron1"), "AK12.png")
            .withModel(new JsonModel("sight/m4iron2"), "AK12.png")
            .withModel(new JsonModel("sight/faliron"), "AK12.png")
            .withModel(new JsonModel("sight/ar15carryhandle"), "AK12.png")
            .withInventoryModelPositioning((model, s) -> {
                if (JsonModel.is(model, "sight/ar15carryhandle")) {
                    GL11.glTranslatef(-0.6F, 0.0F, 0.2F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.7D, 0.75D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/ar15carryhandle")) {
                    GL11.glTranslatef(0.1F, 0.0F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.7D, 0.7D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/ar15carryhandle")) {
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
            .withModel(new JsonModel("sight/m4iron1"), "AK12.png")
            .withModel(new JsonModel("sight/m4iron2"), "AK12.png")
            .withModel(new JsonModel("sight/faliron"), "AK12.png")
            .withModel(new JsonModel("sight/m16a1carryhandle"), "AK12.png")
            .withInventoryModelPositioning((model, s) -> {
                if (JsonModel.is(model, "sight/m16a1carryhandle")) {
                    GL11.glTranslatef(-0.6F, 0.0F, 0.2F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.7D, 0.75D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/m16a1carryhandle")) {
                    GL11.glTranslatef(0.1F, 0.0F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.7D, 0.7D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/m16a1carryhandle")) {
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
            .withModel(new JsonModel("sight/akmiron1"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/akmiron2"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/ak47iron"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/m4iron1"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/m4iron2"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/p90iron"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/g36ciron1"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/g36ciron2"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/scariron1"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/scariron2"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/faliron"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/m14iron"), "GunmetalTexture.png")
            .withModel(new JsonModel("sight/mp5iron"), "AK12.png")
            .withName("Extra")
            .withTextureName("Dummy.png")
            .build();
        M3A1sight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6)
            .withModel(new JsonModel("sight/m3a1rearsight"), "M3A1greasegun.png")
            .withModel(new JsonModel("sight/m3a1frontsight"), "M3A1greasegun.png")
            .withName("M3A1sight")
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldSMLESight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new JsonModel("sight/leeenfieldsmlesight"), "AK12.png")
            .withName("LeeEnfieldSMLESight")
            .withTextureName("Dummy.png")
            .build();
        PPSHRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("sight/ppshrearsight"), "PPSH41.png")
            .withName("PPSHRearSight")
            .withTextureName("Dummy.png")
            .build();
        SpringfieldRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("sight/springfieldrearsight"), "AK12.png")
            .withName("SpringfieldRearSight")
            .withTextureName("Dummy.png")
            .build();
        G98RearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new JsonModel("sight/g98rearsight"), "AK12.png")
            .withName("G98RearSight")
            .withTextureName("Dummy.png")
            .build();
        M1A1rearsight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("sight/m1a1rearsight"), "GunmetalTexture.png")
            .withName("M1A1rearsight")
            .withTextureName("Dummy.png")
            .build();
        M1GarandRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("sight/m1garandrearsight"), "GunmetalTexture.png")
            .withName("M1GarandRearSight")
            .withTextureName("Dummy.png")
            .build();
        M1928rearsight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("sight/m1928rearsight"), "GunmetalTexture.png")
            .withName("M1928rearsight")
            .withTextureName("Dummy.png")
            .build();
        M1Garandaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/m1garandaction"), "NATOMag1.png")
            .withName("M1Garandaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Tec9Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/tec9action"), "AK12.png")
            .withName("Tec9Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M249Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/m249action"), "M249.png")
            .withName("M249Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M249Cover = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("action/m249cover"), "M249.png")
            .withModel(new JsonModel("sight/m249rearsight"), "AK12.png")
            .withName("M249Cover")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldSMLEaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/leeenfieldsmleaction"), "LeeEnfieldSMLE.png")
            .withName("LeeEnfieldSMLEaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldClip = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("magazine/leeenfieldclip"), "sksstripper.png")
            .withName("LeeEnfieldClip")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LeeEnfieldClipBullets = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("ammo/leeenfieldclipbullets"), "sksstripper.png")
            .withName("LeeEnfieldClipBullets")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M3A1action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/m3a1greasegunaction"), "M3A1GreaseGun.png")
            .withName("M3A1GreaseGun")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SpringfieldAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/springfieldaction"), "m1903a3.png")
            .withName("SpringfieldAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Kar98Kaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/kar98kaction"), "Kar98K.png")
            .withName("Kar98Kaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Gewehr98Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/gewehr98action"), "Gewehr98.png")
            .withName("Gewehr98Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        STG44Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA7)
            .withModel(new JsonModel("action/stg44action"), "STG44.png")
            .withName("STG44Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1GarandMag1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("magazine/m1garandmag1"), "M1GarandMag.png")
            .withName("M1GarandMag1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1GarandMag2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("magazine/m1garandmag2"), "M1GarandMag.png")
            .withName("M1GarandMag2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LugerAction1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/lugeraction1"), "LugerP08.png")
            .withName("LugerAction1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        LugerAction2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("action/lugeraction2"), "LugerP08.png")
            .withName("LugerAction2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        WebleyCylinder = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/webleycylinder"), "Webley.png")
            .withName("WebleyCylinder")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        WebleyBullets = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("ammo/webleybullets"), "Webley.png")
            .withName("WebleyBullets")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SKSmag1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("magazine/sksstripper"), "sksstripper.png")
            .withName("SKSmag1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SKSmag2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("magazine/sksstripper2"), "sksstripper.png")
            .withName("SKSmag2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SKSaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/sksaction"), "NATOmag1.png")
            .withName("SKSaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Bullet = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6)
            .withModel(new JsonModel("ammo/bulletbig"), "Bullet.png")
            .withName("Bullet")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        ExtraAR = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("sight/ar15iron"), "AK12.png")
            .withModel(new JsonModel("sight/faliron"), "AK12.png")
            .withModel(new JsonModel("sight/m4iron1"), "AK12.png")
            .withModel(new JsonModel("sight/m4iron2"), "AK12.png")
            .withName("ExtraAR")
            .withTextureName("Dummy.png")
            .build();
        DupletBarrels = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/dupletbarrels"), "Duplet.png")
            .withRenderablePart()
            .withName("DupletBarrels")
            .withTextureName("Dummy.png")
            .build();
        SVT40action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/svt40action"), "SVT40.png")
            .withName("SVT40action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        GlockTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/glocktop"), "GlockTop.png")
            .withName("GlockTop")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P99Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/p99slide"), "P99.png")
            .withName("P99Slide")
            .withModel(new JsonModel("sight/p2000rearsight"), "usp45rearsight.png")
            .withModel(new JsonModel("sight/p226frontsight"), "usp45frontsight.png")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        R700action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/r700action"), "R700action.png")
            .withName("R700action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1A1action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/m1a1action"), "M1A1Thompson.png")
            .withName("M1A1action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1928action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/m1928action"), "gunmetaltexture.png")
            .withName("M1928action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Saiga12action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/saiga12action"), "ak12.png")
            .withName("Saiga12action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MP18action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/mp18action"), "MP18.png")
            .withName("MP18action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        PPSH41action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/ppsh41action"), "PPSH41.png")
            .withName("PPSH41action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Type100action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/type100action"), "PPSH41.png")
            .withName("Type100action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1CarbineAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/m1carbineaction"), "M1Carbine.png")
            .withName("M1CarbineAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1CarbineRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("sight/m1carbinerearsight"), "AK12.png")
            .withName("M1CarbineRearSight")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Glock21Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/glock21slide"), "Glock21Slide.png")
            .withName("Glock21Slide")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Glock32Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/glock32slide"), "Glock32Slide.png")
            .withName("Glock32Slide")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MP40action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/mp40action"), "MP40.png")
            .withName("MP40action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        G18Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/glocktop"), "G18Top.png")
            .withName("G18Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M9Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/m9top"), "M9Top.png")
            .withModel(new JsonModel("sight/m9rearsight"), "m9rearsight.png")
            .withModel(new JsonModel("sight/m9frontsight"), "m9frontsight.png")
            .withName("M9Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AK12IronSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("sight/ak12ironsight"), "GunmetalTexture.png")
            .withName("AK12IronSight")
            .withTextureName("Dummy.png")
            .build();
        M9SDsuppressor = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("misc/suppressor"), "GunmetalTexture.png")
            .withName("M9SDsuppressor")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P2000Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/p2000top"), "P2000Top.png")
            .withModel(new JsonModel("sight/p2000rearsight"), "p2000rearsight.png")
            .withModel(new JsonModel("sight/p226frontsight"), "p226frontsight.png")
            .withName("P2000Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        DeagleTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/deagletop"), "Deagle.png")
            .withName("DeagleTop")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Deagle44Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/deagletop"), "Deagle44.png")
            .withName("Deagle44Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        KSGPump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/ksg12pump"), "NATOMag1.png")
            .withName("KSGPump")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        L115Bolt1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/l96action"), "L96Action.png")
            .withName("L96Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        SV98Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/sv98action"), "SV98Action.png")
            .withName("SV98Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        L115Bolt2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("action/l115bolt2"), "AK12.png")
            .withName("LP115Bolt2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MosinBolt = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/mosinbolt"), "mosinbolt.png")
            .withName("MosinBolt")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MosinBolt2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("action/mosinbolt2"), "mosinbolt.png")
            .withName("MosinBolt2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        RevolverCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("ammo/magnumcase"), "MagnumCase.png")
            .withName("RevolverCase")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        PythonCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("ammo/magnumcase"), "PythonCase.png")
            .withName("PythonCase")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        R870Pump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/remingtonpump"), "Remington870.png")
            .withName("R870Pump")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        R870PumpTac = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/remington870tacpump"), "Remington870Tactical.png")
            .withName("R870PumpTac")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M1911Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/m1911top"), "M1911.png")
            .withModel(new JsonModel("sight/m1911frontsight"), "m1911frontsight")
            .withModel(new JsonModel("sight/m1911rearsight"), "m1911rearsight")
            .withName("M1911Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Taurus1911Slide = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/taurus1911slide"), "Taurus1911.png")
            .withModel(new JsonModel("sight/m1911frontsight"), "m1911frontsight")
            .withModel(new JsonModel("sight/m1911rearsight"), "m1911rearsight")
            .withName("Taurus1911Slide")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        USP45Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/usp45top"), "USP45Top.png")
            .withModel(new JsonModel("sight/p2000rearsight"), "usp45rearsight.png")
            .withModel(new JsonModel("sight/p226frontsight"), "usp45frontsight.png")
            .withName("USP45Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MakarovTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/makarovtop"), "MakarovPM.png")
            .withModel(new JsonModel("sight/makarovrearsight"), "makarovrearsight.png")
            .withModel(new JsonModel("sight/makarovfrontsight"), "makarovfrontsight.png")
            .withName("MakarovTop")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        Saiga12sights = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("sight/makarovrearsight"), "makarovrearsight.png")
            .withModel(new JsonModel("sight/makarovfrontsight"), "makarovfrontsight.png")
            .withName("Saiga12sights")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FNP90Sight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("sight/fnp90sight"), "AK12.png")
            .withModel(new JsonModel("sight/reflex2"), "Reflex2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/fnp90sight")) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (JsonModel.is(model, "sight/reflex2")) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/fnp90sight")) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (JsonModel.is(model, "sight/reflex2")) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/fnp90sight")) {
                    GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (JsonModel.is(model, "sight/reflex2")) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (JsonModel.is(model, "sight/fnp90sight")) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (JsonModel.is(model, "sight/reflex2")) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("FNP90Sight")
            .withTextureName("Dummy.png")
            .build();
        M14Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("misc/m14rail"), "GunmetalTexture.png")
            .withName("M14Rail")
            .withTextureName("Dummy.png")
            .build();
        M14Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/m14action"), "AK12.png")
            .withName("M14Action")
            .withTextureName("Dummy.png")
            .withRenderablePart()
            .build();
        M14Action2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/m14action2"), "AK12.png")
            .withName("M14Action2")
            .withTextureName("Dummy.png")
            .withRenderablePart()
            .build();
        FamasCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("sight/famascarryhandle"), "AK12.png")
            .withName("FamasCarryHandle")
            .withTextureName("Dummy.png")
            .build();
        FelinCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("sight/felincarryhandle"), "AK12.png")
            .withName("FelinCarryHandle")
            .withTextureName("Dummy.png")
            .build();
        P30Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/p2000top"), "P30Top.png")
            .withName("P30Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P225Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/p225top"), "P225Top.png")
            .withName("P225Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        P226Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/p225top"), "P226Top.png")
            .withModel(new JsonModel("sight/p226rearsight"), "p226rearsight.png")
            .withModel(new JsonModel("sight/p226frontsight"), "p226frontsight.png")
            .withName("P226Top")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        MP5KGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("grip/grip2"), "GunmetalTexture.png")
            .withName("MP5KGrip")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        HecateIIBoltAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/hecateiiboltaction"), "AK12.png")
            .withName("HecateIIBoltAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AR15Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/ar15action"), "AK12.png")
            .withName("AR15Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M110action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("action/ar15action"), "M110.png")
            .withName("M110action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        BushmasterACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/acraction"), "AK12.png")
            .withModel(new JsonModel("action/acraction2"), "AK12.png")
            .withName("BushmasterACRAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        RemingtonACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA)
            .withModel(new JsonModel("action/acraction"), "ACR.png")
            .withModel(new JsonModel("action/acraction2"), "AK12.png")
            .withName("RemingtonACRAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("sight/akiron3"), "AK12.png")
            .withName("AKIron3")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKpart = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("misc/akpart"), "AK12.png")
            .withName("AKpart")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKpart2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("misc/akpart"), "AK12.png")
            .withName("AKpart2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKS74UIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("sight/aks74uiron"), "AK12.png")
            .withName("AKS74UIron")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new JsonModel("misc/akrail"), "AK12.png")
            .withName("AKRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AUGRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new JsonModel("misc/akrail"), "AK12.png")
            .withModel(new JsonModel("misc/akrail2"), "AK12.png")
            .withModel(new JsonModel("misc/akrail3"), "AK12.png")
            .withModel(new JsonModel("misc/akrail4"), "AK12.png")
            .withName("AUGRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        BushmasterACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new JsonModel("misc/akrail"), "AK12.png")
            .withModel(new JsonModel("misc/akrail2"), "AK12.png")
            .withModel(new JsonModel("misc/akrail3"), "AK12.png")
            .withModel(new JsonModel("misc/akrail4"), "AK12.png")
            .withModel(new JsonModel("misc/akrail5"), "AK12.png")
            .withName("BushmasterACRRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        RemingtonACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new JsonModel("misc/akrail"), "ACR.png")
            .withModel(new JsonModel("misc/akrail2"), "ACR.png")
            .withModel(new JsonModel("misc/akrail3"), "ACR.png")
            .withModel(new JsonModel("misc/akrail4"), "ACR.png")
            .withModel(new JsonModel("misc/akrail5"), "ACR.png")
            .withName("RemingtonACRRail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M4Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5)
            .withModel(new JsonModel("misc/akrail"), "AK12.png")
            .withModel(new JsonModel("misc/akrail2"), "AK12.png")
            .withModel(new JsonModel("misc/akrail3"), "AK12.png")
            .withModel(new JsonModel("misc/akrail4"), "AK12.png")
            .withModel(new JsonModel("misc/akrail5"), "AK12.png")
            .withName("M4Rail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        G36Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("misc/g36rail"), "AK12.png")
            .withName("G36Rail")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AKaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/akaction"), "AK12.png")
            .withName("AKaction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AN94action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/an94action"), "AK12.png")
            .withName("AN94action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        VSSVintorezAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/vssvintorezaction"), "AK12.png")
            .withName("VSSVintorezAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AK12action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/ak12action"), "AK12.png")
            .withName("AK12action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        M107action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/m107action"), "M107.png")
            .withName("M107action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        ScarAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/scaraction"), "AK12.png")
            .withName("ScarAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        G36Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/g36action"), "AK12.png")
            .withName("G36Action")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FamasAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/famasaction"), "AK12.png")
            .withName("FamasAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        AUGAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/augaction"), "AK12.png")
            .withName("AUGAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FelinAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4)
            .withModel(new JsonModel("action/felinaction"), "AK12.png")
            .withName("FelinAction")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FamasBipod1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2)
            .withModel(new JsonModel("grip/famasbipod"), "AK12.png")
            .withName("FamasBipod1")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
        FamasBipod2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3)
            .withModel(new JsonModel("grip/famasbipod"), "AK12.png")
            .withName("FamasBipod2")
            .withRenderablePart()
            .withTextureName("Dummy.png")
            .build();
    }
}
