package com.gtnewhorizon.newgunrizons.registry;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.render.EntityLaserBeamRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.model.grip.AngledGrip;
import com.gtnewhorizon.newgunrizons.model.grip.Bipod;
import com.gtnewhorizon.newgunrizons.model.grip.Grip2;
import com.gtnewhorizon.newgunrizons.model.grip.StubbyGrip;
import com.gtnewhorizon.newgunrizons.model.grip.VGrip;
import com.gtnewhorizon.newgunrizons.model.misc.Laser;
import com.gtnewhorizon.newgunrizons.model.misc.Laser2;
import com.gtnewhorizon.newgunrizons.model.misc.PUmount;
import com.gtnewhorizon.newgunrizons.model.misc.SVTmount;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor300AACBlackout;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor45ACP;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor556x39;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor556x45;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor762x39;
import com.gtnewhorizon.newgunrizons.model.misc.Suppressor762x51;
import com.gtnewhorizon.newgunrizons.model.sight.ACOG;
import com.gtnewhorizon.newgunrizons.model.sight.AK47iron;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron1;
import com.gtnewhorizon.newgunrizons.model.sight.AKMiron2;
import com.gtnewhorizon.newgunrizons.model.sight.AR15Iron;
import com.gtnewhorizon.newgunrizons.model.sight.AUGScope;
import com.gtnewhorizon.newgunrizons.model.sight.Acog2;
import com.gtnewhorizon.newgunrizons.model.sight.AcogReticle;
import com.gtnewhorizon.newgunrizons.model.sight.AcogScope2;
import com.gtnewhorizon.newgunrizons.model.sight.ColtRearSight;
import com.gtnewhorizon.newgunrizons.model.sight.FALIron;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron1;
import com.gtnewhorizon.newgunrizons.model.sight.G36CIron2;
import com.gtnewhorizon.newgunrizons.model.sight.Holo2;
import com.gtnewhorizon.newgunrizons.model.sight.Holographic;
import com.gtnewhorizon.newgunrizons.model.sight.Holographic2;
import com.gtnewhorizon.newgunrizons.model.sight.Kobra;
import com.gtnewhorizon.newgunrizons.model.sight.LP;
import com.gtnewhorizon.newgunrizons.model.sight.LPscope;
import com.gtnewhorizon.newgunrizons.model.sight.Leupold;
import com.gtnewhorizon.newgunrizons.model.sight.LeupoldReticle;
import com.gtnewhorizon.newgunrizons.model.sight.M14Iron;
import com.gtnewhorizon.newgunrizons.model.sight.M1903A1scope;
import com.gtnewhorizon.newgunrizons.model.sight.M1903A1scope2;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron1;
import com.gtnewhorizon.newgunrizons.model.sight.M4Iron2;
import com.gtnewhorizon.newgunrizons.model.sight.MBUSiron;
import com.gtnewhorizon.newgunrizons.model.sight.MP5Iron;
import com.gtnewhorizon.newgunrizons.model.sight.MicroT1;
import com.gtnewhorizon.newgunrizons.model.sight.OKP7;
import com.gtnewhorizon.newgunrizons.model.sight.OKP7reticle;
import com.gtnewhorizon.newgunrizons.model.sight.P90iron;
import com.gtnewhorizon.newgunrizons.model.sight.PSO1;
import com.gtnewhorizon.newgunrizons.model.sight.PSO12;
import com.gtnewhorizon.newgunrizons.model.sight.PSO1reticle;
import com.gtnewhorizon.newgunrizons.model.sight.PUreticle;
import com.gtnewhorizon.newgunrizons.model.sight.PUscope;
import com.gtnewhorizon.newgunrizons.model.sight.PriscopicScope;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex;
import com.gtnewhorizon.newgunrizons.model.sight.Reflex2;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron1;
import com.gtnewhorizon.newgunrizons.model.sight.ScarIron2;
import com.gtnewhorizon.newgunrizons.model.sight.SpecterSight;
import com.gtnewhorizon.newgunrizons.model.sight.UFCG36Scope;
import com.gtnewhorizon.newgunrizons.model.sight.UnertlReticle;
import com.gtnewhorizon.newgunrizons.model.weapon.HP;

public class Attachments {

    public static ItemAttachment Reflex;
    public static ItemAttachment Holo2;
    public static ItemAttachment Holographic2;
    public static ItemAttachment Kobra;
    public static ItemAttachment ACOG;
    public static ItemAttachment Specter;
    public static ItemAttachment G36Scope;
    public static ItemAttachment AUGScope;
    public static ItemAttachment Scope;
    public static ItemAttachment HP;
    public static ItemAttachment Unertl;
    public static ItemAttachment Leupold;
    public static ItemAttachment PSO1;
    public static ItemAttachment Silencer556x45;
    public static ItemAttachment Silencer762x39;
    public static ItemAttachment Silencer556x39;
    public static ItemAttachment Silencer50BMG;
    public static ItemAttachment Silencer9mm;
    public static ItemAttachment Silencer762x54;
    public static ItemAttachment Silencer762x51;
    public static ItemAttachment Silencer45ACP;
    public static ItemAttachment Silencer12Gauge;
    public static ItemAttachment Silencer65x39;
    public static ItemAttachment Silencer57x38;
    public static ItemAttachment Silencer300AACBlackout;
    public static ItemAttachment Silencer357;
    public static ItemAttachment SilencerMP7;
    public static ItemAttachment Laser;
    public static ItemAttachment Laser2;
    public static ItemAttachment Grip2;
    public static ItemAttachment Grip;
    public static ItemAttachment StubbyGrip;
    public static ItemAttachment VGrip;
    public static ItemAttachment Bipod;
    public static ItemAttachment AKMIron;
    public static ItemAttachment MicroT1;
    public static ItemAttachment OKP7;
    public static ItemAttachment PUscope;
    public static ItemAttachment PriscopicScope;
    public static ItemAttachment ColtRearSight;

    public static void init() {
        Reflex = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withRenderablePart()
            .withModel(new Reflex(), "Reflex.png")
            .withModel(new Reflex2(), "Reflex2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.3D, 0.3D, 0.3D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(-0.6F, -0.1F, 1.15F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Reflex) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Reflex")
            .withTextureName("Dummy.png")
            .build();
        ColtRearSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new M4Iron1(), "AK12.png")
            .withModel(new M4Iron2(), "AK12.png")
            .withModel(new FALIron(), "AK12.png")
            .withModel(new ColtRearSight(), "AK12.png")
            .withInventoryModelPositioning((model, s) -> {
                if (model instanceof ColtRearSight) {
                    GL11.glTranslatef(-0.6F, 0.0F, 0.2F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.7D, 0.75D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof ColtRearSight) {
                    GL11.glTranslatef(0.1F, 0.0F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.7D, 0.7D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof ColtRearSight) {
                    GL11.glTranslatef(-1.6F, -0.5F, 1.2F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.3D, 0.5D, 0.5D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("ColtRearSight")
            .withTextureName("Dummy.png")
            .build();
        OKP7 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new OKP7(), "ak12.png")
            .withModel(new OKP7reticle(), "green.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof OKP7) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof OKP7reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof OKP7) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.3D, 0.3D, 0.3D);
                } else if (model instanceof OKP7reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof OKP7) {
                    GL11.glTranslatef(-0.6F, -0.1F, 1.15F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof OKP7reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof OKP7) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof OKP7reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("okp7")
            .withTextureName("Dummy.png")
            .build();
        ACOG = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.1F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(1.17F, 1.17F, 1.17F);
                GL11.glTranslatef(0.087F, 0.42F, 0.56F);
            })
            .withRenderablePart()
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new ACOG(), "Acog.png")
            .withModel(new AcogScope2(), "AK12.png")
            .withModel(new AcogReticle(), "acogreticle.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof ACOG) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof AcogReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AcogScope2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof ACOG) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof AcogReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AcogScope2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof ACOG) {
                    GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.2D, 1.2D, 1.2D);
                } else if (model instanceof AcogReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AcogScope2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof ACOG) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof AcogReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof AcogScope2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Acog")
            .withTextureName("Dummy.png")
            .build();
        Specter = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.1F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(2.7F, 2.8F, 2.7F);
                GL11.glTranslatef(-0.06F, 0.28F, 0.56F);
            })
            .withRenderablePart()
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new SpecterSight(), "SpecterSight.png")
            .withModel(new Acog2(), "Acog2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof SpecterSight) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.35D, 0.35D, 0.35D);
                } else if (model instanceof Acog2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof SpecterSight) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.25D, 0.25D, 0.25D);
                } else if (model instanceof Acog2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof SpecterSight) {
                    GL11.glTranslatef(-0.6F, 0.0F, 0.85F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof Acog2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof SpecterSight) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Acog2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Specter")
            .withTextureName("Dummy.png")
            .build();
        Holo2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Holographic(), "Holographic.png")
            .withModel(new Holo2(), "Holo3.png")
            .withRenderablePart()
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Holographic")
            .withTextureName("Dummy.png")
            .build();
        Holographic2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withRenderablePart()
            .withModel(new Holographic2(), "Holographic2.png")
            .withModel(new Holo2(), "Holo3.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(-0.6F, -0.1F, 0.5F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Holographic2) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Holo2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Holographic2")
            .withTextureName("Dummy.png")
            .build();
        MicroT1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withRenderablePart()
            .withModel(new MicroT1(), "AK12.png")
            .withModel(new Reflex2(), "Reflex2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.2F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof MicroT1) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("MicroT1")
            .withTextureName("Dummy.png")
            .build();
        Kobra = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Kobra(), "Kobra.png")
            .withModel(new Reflex2(), "Reflex2.png")
            .withRenderablePart()
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(0.4F, -0.8F, 0.5F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Kobra) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Kobra")
            .withTextureName("Dummy.png")
            .build();
        G36Scope = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.1F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                GL11.glTranslatef(-0.095F, 0.6F, 0.85F);
            })
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new UFCG36Scope(), "AK12.png")
            .withModel(new Reflex2(), "Holo3.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof UFCG36Scope) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof UFCG36Scope) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof UFCG36Scope) {
                    GL11.glTranslatef(-0.6F, -0.7F, 1.2F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.9D, 0.9D, 0.9D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof UFCG36Scope) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof Reflex2) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("G36Scope")
            .withTextureName("Dummy.png")
            .build();
        AUGScope = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.1F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(1.65F, 1.65F, 1.6F);
                GL11.glTranslatef(-0.07F, 0.559F, 1.82F);
            })
            .withModel(new AUGScope(), "AK12.png")
            .withModel(new LPscope(), "HP2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AUGScope) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AUGScope) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof AUGScope) {
                    GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.2D, 1.2D, 1.2D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof AUGScope) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("AUGScope")
            .withTextureName("Dummy.png")
            .build();
        Scope = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.06F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(1.1F, 1.1F, 1.1F);
                GL11.glTranslatef(0.1F, 0.395F, 0.6F);
            })
            .withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withCrosshair("LP")
            .withModel(new LP(), "AK12.png")
            .withModel(new LPscope(), "HP2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof LP) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof LP) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof LP) {
                    GL11.glTranslatef(-0.6F, -0.6F, 0.5F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.8D, 0.8D, 0.8D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof LP) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("LPScope")
            .withTextureName("Dummy.png")
            .build();
        Leupold = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.04F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(2.3F, 2.3F, 2.3F);
                GL11.glTranslatef(-0.085F, 0.33F, 1.75F);
            })
            .withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withCrosshair("LP")
            .withModel(new Leupold(), "Leupold.png")
            .withModel(new LeupoldReticle(), "Reticle.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Leupold) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof LeupoldReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Leupold) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof LeupoldReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Leupold) {
                    GL11.glTranslatef(-0.6F, -0.45F, 0.94F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.35D, 0.35D, 0.35D);
                } else if (model instanceof LeupoldReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Leupold) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof LeupoldReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Leupold")
            .withTextureName("Dummy.png")
            .build();
        PSO1 = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.06F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(1.05F, 1.05F, 1.05F);
                GL11.glTranslatef(-0.32F, 0.168F, 1.2F);
            })
            .withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withCrosshair("LP")
            .withModel(new PSO1(), "AK12.png")
            .withModel(new PSO12(), "AK12.png")
            .withModel(new PSO1reticle(), "black.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof PSO1) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof PSO1reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PSO12) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof PSO1) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof PSO1reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PSO12) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof PSO1) {
                    GL11.glTranslatef(-0.6F, -0.3F, 0.7F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                } else if (model instanceof PSO1reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PSO12) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof PSO1) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof PSO1reticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PSO12) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("PSO1")
            .withTextureName("Dummy.png")
            .build();
        PUscope = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.06F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(0.64F, 0.64F, 0.64F);
                GL11.glTranslatef(-0.875F, 1.0F, 1.28F);
            })
            .withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withCrosshair("LP")
            .withModel(new PUscope(), "ak12.png")
            .withModel(new PUmount(), "ak12.png")
            .withModel(new SVTmount(), "ak12.png")
            .withModel(new PUreticle(), "black.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof PUscope) {
                    GL11.glTranslatef(0.1F, -1.2F, -0.0F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.2D, 0.2D, 0.2D);
                } else if (model instanceof PUmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUreticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof SVTmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof PUscope) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.2D, 0.2D, 0.2D);
                } else if (model instanceof SVTmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUreticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof PUscope) {
                    GL11.glTranslatef(-0.6F, -0.2F, 1.7F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.45D, 0.45D, 0.45D);
                } else if (model instanceof SVTmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUreticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof PUscope) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof SVTmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUmount) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof PUreticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("PUscope")
            .withTextureName("Dummy.png")
            .build();
        PriscopicScope = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.03F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(0.876F, 0.87F, 0.88F);
                GL11.glTranslatef(-0.6695F, 0.915F, 2.59F);
            })
            .withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withCrosshair("LP")
            .withModel(new PriscopicScope(), "PriscopicScope.png")
            .withModel(new UnertlReticle(), "black.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof PriscopicScope) {
                    GL11.glTranslatef(0.1F, -1.2F, -0.0F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.2D, 0.2D, 0.2D);
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof PriscopicScope) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.2D, 0.2D, 0.2D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof PriscopicScope) {
                    GL11.glTranslatef(-0.6F, -0.2F, 1.7F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.2, 0.2, 0.2);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof PriscopicScope) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("PriscopicScope")
            .withTextureName("Dummy.png")
            .build();
        HP = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.02F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(1.65F, 1.65F, 1.65F);
                GL11.glTranslatef(0.0285F, 0.492F, 0.7F);
            })
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withCrosshair("HP")
            .withModel(new HP(), "AK12.png")
            .withModel(new LPscope(), "HP2.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof HP) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof HP) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof HP) {
                    GL11.glTranslatef(-0.6F, -0.6F, 0.6F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.65D, 0.65D, 0.65D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof HP) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof LPscope) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("HPScope")
            .withTextureName("Dummy.png")
            .build();
        Unertl = (new ItemScope.Builder()).withOpticalZoom()
            .withZoomRange(0.22F, 0.01F)
            .withViewfinderPositioning((p, s) -> {
                GL11.glScalef(0.75F, 0.75F, 0.75F);
                GL11.glTranslatef(-0.327F, -1.54F, -0.76F);
            })
            .withCrosshair("HP")
            .withModel(new M1903A1scope2(), "AK12.png")
            .withModel(new M1903A1scope(), "AK12.png")
            .withModel(new UnertlReticle(), "black.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M1903A1scope2) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof M1903A1scope) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M1903A1scope2) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof M1903A1scope) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof M1903A1scope2 || model instanceof M1903A1scope) {
                    GL11.glTranslatef(-0.6F, -0.6F, 0.6F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.65D, 0.65D, 0.65D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof M1903A1scope2) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

                if (model instanceof M1903A1scope) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                } else if (model instanceof UnertlReticle) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("Unertl")
            .withTextureName("Dummy.png")
            .build();
        Silencer556x45 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor556x45(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x45) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x45) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x45) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x45) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer556x45")
            .withTextureName("Dummy.png")
            .build();
        Silencer762x39 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor762x39(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x39) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x39) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x39) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x39) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer762x39")
            .withTextureName("Dummy.png")
            .build();
        Silencer9mm = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor(), "GunmetalTexture.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer9mm")
            .withTextureName("Dummy.png")
            .build();
        Silencer45ACP = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor45ACP(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer45ACP")
            .withTextureName("Dummy.png")
            .build();
        Silencer762x54 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer762x54")
            .withTextureName("Dummy.png")
            .build();
        Silencer762x51 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor762x51(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x51) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x51) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x51) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor762x51) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer762x51")
            .withTextureName("Dummy.png")
            .build();
        Silencer50BMG = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor(), "GunmetalTexture.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer50BMG")
            .withTextureName("Dummy.png")
            .build();
        Silencer556x39 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor556x39(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer556x39")
            .withTextureName("Dummy.png")
            .build();
        AKMIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new AKMiron1(), "AK12.png")
            .withModel(new AKMiron2(), "AK12.png")
            .withModel(new AK47iron(), "AK12.png")
            .withModel(new M4Iron1(), "AK12.png")
            .withModel(new M4Iron2(), "AK12.png")
            .withModel(new P90iron(), "AK12.png")
            .withModel(new G36CIron1(), "AK12.png")
            .withModel(new G36CIron2(), "AK12.png")
            .withModel(new ScarIron1(), "AK12.png")
            .withModel(new ScarIron2(), "AK12.png")
            .withModel(new FALIron(), "AK12.png")
            .withModel(new M14Iron(), "AK12.png")
            .withModel(new MP5Iron(), "AK12.png")
            .withModel(new MBUSiron(), "AK12.png")
            .withModel(new MP5Iron(), "AK12.png")
            .withInventoryModelPositioning((model, s) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
                    GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.2D, 1.2D, 1.2D);
                } else {
                    GL11.glScalef(0.0F, 0.0F, 0.0F);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(0.1F, -0.8F, 0.4F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.7D, 0.7D, 0.7D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof M4Iron1) {
                    GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                } else {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withName("AKMIron")
            .withTextureName("Dummy.png")
            .build();
        SilencerMP7 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("SilencerMP7")
            .withTextureName("Dummy.png")
            .build();
        Silencer357 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor(), "GunmetalTexture.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer357")
            .withTextureName("Dummy.png")
            .build();
        Silencer57x38 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer57x38")
            .withTextureName("Dummy.png")
            .build();
        Silencer12Gauge = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor45ACP(), "GunmetalTexture.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor45ACP) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer12Gauge")
            .withTextureName("Dummy.png")
            .build();
        Silencer300AACBlackout = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor300AACBlackout(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor300AACBlackout) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor300AACBlackout) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor300AACBlackout) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor300AACBlackout) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer300AACBlackout")
            .withTextureName("Dummy.png")
            .build();
        Silencer65x39 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Suppressor556x39(), "AK12.png")
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(0.6F, 0.1F, 0.3F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.0D, 1.0D, 1.0D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Suppressor556x39) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })

            .withName("Silencer65x39")
            .withTextureName("Dummy.png")
            .build();
        Laser = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Laser(), "AK12.png")
            .withPostRender(new EntityLaserBeamRenderer((p, s) -> GL11.glTranslatef(-0.2F, 1.4F, 1.8F)))
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Laser) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (!(model instanceof AR15Iron)) {
                    GL11.glScaled(0.0D, 0.0D, 0.0D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Laser) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Laser) {
                    GL11.glTranslatef(0.6F, -0.3F, 0.65F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.8D, 1.8D, 1.8D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Laser) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })
            .withName("Laser")
            .withTextureName("Dummy.png")
            .build();
        Laser2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Laser2(), "AK12.png")
            .withPostRender(new EntityLaserBeamRenderer((p, s) -> GL11.glTranslatef(-0.2F, 1.3F, 1.8F)))
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Laser2) {
                    GL11.glTranslatef(0.5F, -1.3F, -0.1F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Laser2) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Laser2) {
                    GL11.glTranslatef(0.6F, -0.3F, 0.65F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.8D, 1.8D, 1.8D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Laser2) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.4D, 0.4D, 0.4D);
                }

            })
            .withName("Laser2")
            .withTextureName("Dummy.png")
            .build();
        Grip2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Grip2(), "AK12.png")
            .withApply(
                (a, i) -> {
                    i.setRecoil(
                        i.getWeapon()
                            .getRecoil() * 0.6F);
                })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Grip2) {
                    GL11.glTranslatef(0.7F, -1.2F, 0.5F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Grip2) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Grip2) {
                    GL11.glTranslatef(0.6F, 0.3F, -0.5F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.3D, 1.3D, 1.3D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Grip2) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withName("Grip2")
            .withTextureName("Dummy.png")
            .build();
        Grip = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new AngledGrip(), "AK12.png")
            .withApply(
                (a, i) -> {
                    i.setRecoil(
                        i.getWeapon()
                            .getRecoil() * 0.6F);
                })
            .withApply(
                (a, i) -> {
                    i.setRecoil(
                        i.getWeapon()
                            .getRecoil());
                })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AngledGrip) {
                    GL11.glTranslatef(0.7F, -1.1F, 0.5F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof AngledGrip) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof AngledGrip) {
                    GL11.glTranslatef(0.6F, 0.8F, -0.45F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.2D, 1.2D, 1.2D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof AngledGrip) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withName("AngledGrip")
            .withTextureName("Dummy.png")
            .build();
        StubbyGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new StubbyGrip(), "AK12.png")
            .withApply(
                (a, i) -> {
                    i.setRecoil(
                        i.getWeapon()
                            .getRecoil() * 0.6F);
                })
            .withRemove(
                (a, i) -> {
                    i.setRecoil(
                        i.getWeapon()
                            .getRecoil());
                })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof StubbyGrip) {
                    GL11.glTranslatef(0.7F, -1.2F, 0.5F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof StubbyGrip) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof StubbyGrip) {
                    GL11.glTranslatef(0.6F, 0.5F, -0.5F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.6D, 1.6D, 1.6D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof StubbyGrip) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withName("StubbyGrip")
            .withTextureName("Dummy.png")
            .build();
        VGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new VGrip(), "AK12.png")
            .withApply(
                (a, i) -> {
                    i.setRecoil(
                        i.getWeapon()
                            .getRecoil() * 0.6F);
                })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof VGrip) {
                    GL11.glTranslatef(0.7F, -1.1F, 0.5F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof VGrip) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof VGrip) {
                    GL11.glTranslatef(0.6F, 0.3F, -0.5F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(1.3D, 1.3D, 1.3D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof VGrip) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withName("VGrip")
            .withTextureName("Dummy.png")
            .build();
        Bipod = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP)
            .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
            .withModel(new Bipod(), "AK12.png")
            .withApply(
                (a, i) -> {
                    i.setRecoil(
                        i.getWeapon()
                            .getRecoil() * 0.4F);
                })
            .withFirstPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Bipod) {
                    GL11.glTranslatef(0.7F, -1.1F, 0.5F);
                    GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withThirdPersonModelPositioning((model, itemStack) -> {
                if (model instanceof Bipod) {
                    GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glScaled(0.5D, 0.5D, 0.5D);
                }

            })
            .withInventoryModelPositioning((model, itemStack) -> {
                if (model instanceof Bipod) {
                    GL11.glTranslatef(0.6F, -0.05F, -0.5F);
                    GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScaled(0.9D, 0.9D, 0.9D);
                }

            })
            .withEntityModelPositioning((model, itemStack) -> {
                if (model instanceof Bipod) {
                    GL11.glTranslatef(0.1F, 0.2F, 0.4F);
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glScaled(0.6D, 0.6D, 0.6D);
                }

            })
            .withName("Bipod")
            .withTextureName("Dummy.png")
            .build();
    }
}
