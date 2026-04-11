package com.gtnewhorizon.newgunrizons.registry;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentBuilder;
import com.gtnewhorizon.newgunrizons.attachment.AttachmentCategory;
import com.gtnewhorizon.newgunrizons.client.render.EntityLaserBeamRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemScope;
import com.gtnewhorizon.newgunrizons.model.JsonModel;
import org.lwjgl.opengl.GL11;

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
      Reflex = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withRenderablePart()
         .withModel(new JsonModel("sight/reflex"), "Reflex.png")
         .withModel(new JsonModel("sight/reflex2"), "Reflex2.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.2F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(-0.6F, -0.1F, 1.15F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/reflex")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Reflex")
         .withTextureName("Dummy.png")
         .build();
      ColtRearSight = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/m4iron1"), "AK12.png")
         .withModel(new JsonModel("sight/m4iron2"), "AK12.png")
         .withModel(new JsonModel("sight/faliron"), "AK12.png")
         .withModel(new JsonModel("sight/coltrearsight"), "AK12.png")
         .withInventoryModelPositioning((model, s) -> {
            if (JsonModel.is(model, "sight/coltrearsight")) {
               GL11.glTranslatef(-0.6F, 0.0F, 0.2F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.7, 0.75);
            } else {
               GL11.glScalef(0.0F, 0.0F, 0.0F);
            }
         })
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/coltrearsight")) {
               GL11.glTranslatef(0.1F, 0.0F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.5, 0.7, 0.7);
            } else {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/coltrearsight")) {
               GL11.glTranslatef(-1.6F, -0.5F, 1.2F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.3, 0.5, 0.5);
            } else {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("ColtRearSight")
         .withTextureName("Dummy.png")
         .build();
      OKP7 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/okp7"), "ak12.png")
         .withModel(new JsonModel("sight/okp7reticle"), "green.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/okp7")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.2F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/okp7reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/okp7")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.3, 0.3, 0.3);
            } else if (JsonModel.is(model, "sight/okp7reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/okp7")) {
               GL11.glTranslatef(-0.6F, -0.1F, 1.15F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            } else if (JsonModel.is(model, "sight/okp7reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/okp7")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/okp7reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("okp7")
         .withTextureName("Dummy.png")
         .build();
      ACOG = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.1F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(1.17F, 1.17F, 1.17F);
            GL11.glTranslatef(0.087F, 0.42F, 0.56F);
         })
         .withRenderablePart()
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/acog"), "Acog.png")
         .withModel(new JsonModel("sight/acogscope2"), "AK12.png")
         .withModel(new JsonModel("sight/acogreticle"), "acogreticle.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/acog")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/acogreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/acogscope2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/acog")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/acogreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/acogscope2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/acog")) {
               GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.2, 1.2, 1.2);
            } else if (JsonModel.is(model, "sight/acogreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/acogscope2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/acog")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/acogreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/acogscope2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Acog")
         .withTextureName("Dummy.png")
         .build();
      Specter = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.1F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(2.7F, 2.8F, 2.7F);
            GL11.glTranslatef(-0.06F, 0.28F, 0.56F);
         })
         .withRenderablePart()
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/spectersight"), "SpecterSight.png")
         .withModel(new JsonModel("sight/acog2"), "Acog2.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/spectersight")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.35, 0.35, 0.35);
            } else if (JsonModel.is(model, "sight/acog2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/spectersight")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.25, 0.25, 0.25);
            } else if (JsonModel.is(model, "sight/acog2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/spectersight")) {
               GL11.glTranslatef(-0.6F, 0.0F, 0.85F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/acog2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/spectersight")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/acog2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Specter")
         .withTextureName("Dummy.png")
         .build();
      Holo2 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/holographic"), "Holographic.png")
         .withModel(new JsonModel("sight/holo2"), "Holo3.png")
         .withRenderablePart()
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.2F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Holographic")
         .withTextureName("Dummy.png")
         .build();
      Holographic2 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withRenderablePart()
         .withModel(new JsonModel("sight/holographic2"), "Holographic2.png")
         .withModel(new JsonModel("sight/holo2"), "Holo3.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.2F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(-0.6F, -0.1F, 0.5F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/holographic2")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/holo2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Holographic2")
         .withTextureName("Dummy.png")
         .build();
      MicroT1 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withRenderablePart()
         .withModel(new JsonModel("sight/microt1"), "AK12.png")
         .withModel(new JsonModel("sight/reflex2"), "Reflex2.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.2F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/microt1")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("MicroT1")
         .withTextureName("Dummy.png")
         .build();
      Kobra = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/kobra"), "Kobra.png")
         .withModel(new JsonModel("sight/reflex2"), "Reflex2.png")
         .withRenderablePart()
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(0.4F, -0.8F, 0.5F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/kobra")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Kobra")
         .withTextureName("Dummy.png")
         .build();
      G36Scope = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.1F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            GL11.glTranslatef(-0.095F, 0.6F, 0.85F);
         })
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/ufcg36scope"), "AK12.png")
         .withModel(new JsonModel("sight/reflex2"), "Holo3.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/ufcg36scope")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/ufcg36scope")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/ufcg36scope")) {
               GL11.glTranslatef(-0.6F, -0.7F, 1.2F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.9, 0.9, 0.9);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/ufcg36scope")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/reflex2")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("G36Scope")
         .withTextureName("Dummy.png")
         .build();
      AUGScope = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.1F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(1.65F, 1.65F, 1.6F);
            GL11.glTranslatef(-0.07F, 0.559F, 1.82F);
         })
         .withModel(new JsonModel("sight/augscope"), "AK12.png")
         .withModel(new JsonModel("sight/lpscope"), "HP2.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/augscope")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/augscope")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/augscope")) {
               GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.2, 1.2, 1.2);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/augscope")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("AUGScope")
         .withTextureName("Dummy.png")
         .build();
      Scope = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.06F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(1.1F, 1.1F, 1.1F);
            GL11.glTranslatef(0.1F, 0.395F, 0.6F);
         })
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withCrosshair("LP")
         .withModel(new JsonModel("sight/lp"), "AK12.png")
         .withModel(new JsonModel("sight/lpscope"), "HP2.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/lp")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/lp")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/lp")) {
               GL11.glTranslatef(-0.6F, -0.6F, 0.5F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.8, 0.8, 0.8);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/lp")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("LPScope")
         .withTextureName("Dummy.png")
         .build();
      Leupold = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.04F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(2.3F, 2.3F, 2.3F);
            GL11.glTranslatef(-0.085F, 0.33F, 1.75F);
         })
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withCrosshair("LP")
         .withModel(new JsonModel("sight/leupold"), "Leupold.png")
         .withModel(new JsonModel("sight/leupoldreticle"), "Reticle.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/leupold")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/leupoldreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/leupold")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/leupoldreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/leupold")) {
               GL11.glTranslatef(-0.6F, -0.45F, 0.94F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.35, 0.35, 0.35);
            } else if (JsonModel.is(model, "sight/leupoldreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/leupold")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/leupoldreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Leupold")
         .withTextureName("Dummy.png")
         .build();
      PSO1 = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.06F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(1.05F, 1.05F, 1.05F);
            GL11.glTranslatef(-0.32F, 0.168F, 1.2F);
         })
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withCrosshair("LP")
         .withModel(new JsonModel("sight/pso1"), "AK12.png")
         .withModel(new JsonModel("sight/pso12"), "AK12.png")
         .withModel(new JsonModel("sight/pso1reticle"), "black.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/pso1")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/pso1reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pso12")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/pso1")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/pso1reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pso12")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/pso1")) {
               GL11.glTranslatef(-0.6F, -0.3F, 0.7F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            } else if (JsonModel.is(model, "sight/pso1reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pso12")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/pso1")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/pso1reticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pso12")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("PSO1")
         .withTextureName("Dummy.png")
         .build();
      PUscope = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.06F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(0.64F, 0.64F, 0.64F);
            GL11.glTranslatef(-0.875F, 1.0F, 1.28F);
         })
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withCrosshair("LP")
         .withModel(new JsonModel("sight/puscope"), "ak12.png")
         .withModel(new JsonModel("misc/pumount"), "ak12.png")
         .withModel(new JsonModel("misc/svtmount"), "ak12.png")
         .withModel(new JsonModel("sight/pureticle"), "black.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/puscope")) {
               GL11.glTranslatef(0.1F, -1.2F, -0.0F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.2, 0.2, 0.2);
            } else if (JsonModel.is(model, "misc/pumount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pureticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "misc/svtmount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/puscope")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.2, 0.2, 0.2);
            } else if (JsonModel.is(model, "misc/svtmount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "misc/pumount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pureticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/puscope")) {
               GL11.glTranslatef(-0.6F, -0.2F, 1.7F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.45, 0.45, 0.45);
            } else if (JsonModel.is(model, "misc/svtmount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "misc/pumount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pureticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/puscope")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "misc/svtmount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "misc/pumount")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/pureticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("PUscope")
         .withTextureName("Dummy.png")
         .build();
      PriscopicScope = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.03F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(0.876F, 0.87F, 0.88F);
            GL11.glTranslatef(-0.6695F, 0.915F, 2.59F);
         })
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withCrosshair("LP")
         .withModel(new JsonModel("sight/priscopicscope"), "PriscopicScope.png")
         .withModel(new JsonModel("sight/unertlreticle"), "black.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/priscopicscope")) {
               GL11.glTranslatef(0.1F, -1.2F, -0.0F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.2, 0.2, 0.2);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/priscopicscope")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.2, 0.2, 0.2);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/priscopicscope")) {
               GL11.glTranslatef(-0.6F, -0.2F, 1.7F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.2, 0.2, 0.2);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/priscopicscope")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("PriscopicScope")
         .withTextureName("Dummy.png")
         .build();
      HP = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.02F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(1.65F, 1.65F, 1.65F);
            GL11.glTranslatef(0.0285F, 0.492F, 0.7F);
         })
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withCrosshair("HP")
         .withModel(new JsonModel("weapon/hp"), "AK12.png")
         .withModel(new JsonModel("sight/lpscope"), "HP2.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "weapon/hp")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "weapon/hp")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "weapon/hp")) {
               GL11.glTranslatef(-0.6F, -0.6F, 0.6F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.65, 0.65, 0.65);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "weapon/hp")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/lpscope")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("HPScope")
         .withTextureName("Dummy.png")
         .build();
      Unertl = new ItemScope.Builder()
         .withOpticalZoom()
         .withZoomRange(0.22F, 0.01F)
         .withViewfinderPositioning((p, s) -> {
            GL11.glScalef(0.75F, 0.75F, 0.75F);
            GL11.glTranslatef(-0.327F, -1.54F, -0.76F);
         })
         .withCrosshair("HP")
         .withModel(new JsonModel("sight/m1903a1scope2"), "AK12.png")
         .withModel(new JsonModel("sight/m1903a1scope"), "AK12.png")
         .withModel(new JsonModel("sight/unertlreticle"), "black.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/m1903a1scope2")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/m1903a1scope")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/m1903a1scope2")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/m1903a1scope")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/m1903a1scope2") || JsonModel.is(model, "sight/m1903a1scope")) {
               GL11.glTranslatef(-0.6F, -0.6F, 0.6F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.65, 0.65, 0.65);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/m1903a1scope2")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }

            if (JsonModel.is(model, "sight/m1903a1scope")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/unertlreticle")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("Unertl")
         .withTextureName("Dummy.png")
         .build();
      Silencer556x45 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor556x45"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x45")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x45")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x45")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x45")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer556x45")
         .withTextureName("Dummy.png")
         .build();
      Silencer762x39 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor762x39"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x39")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x39")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x39")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x39")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer762x39")
         .withTextureName("Dummy.png")
         .build();
      Silencer9mm = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor"), "GunmetalTexture.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer9mm")
         .withTextureName("Dummy.png")
         .build();
      Silencer45ACP = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor45acp"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer45ACP")
         .withTextureName("Dummy.png")
         .build();
      Silencer762x54 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer762x54")
         .withTextureName("Dummy.png")
         .build();
      Silencer762x51 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor762x51"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x51")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x51")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x51")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor762x51")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer762x51")
         .withTextureName("Dummy.png")
         .build();
      Silencer50BMG = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor"), "GunmetalTexture.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer50BMG")
         .withTextureName("Dummy.png")
         .build();
      Silencer556x39 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor556x39"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer556x39")
         .withTextureName("Dummy.png")
         .build();
      AKMIron = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SCOPE)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("sight/akmiron1"), "AK12.png")
         .withModel(new JsonModel("sight/akmiron2"), "AK12.png")
         .withModel(new JsonModel("sight/ak47iron"), "AK12.png")
         .withModel(new JsonModel("sight/m4iron1"), "AK12.png")
         .withModel(new JsonModel("sight/m4iron2"), "AK12.png")
         .withModel(new JsonModel("sight/p90iron"), "AK12.png")
         .withModel(new JsonModel("sight/g36ciron1"), "AK12.png")
         .withModel(new JsonModel("sight/g36ciron2"), "AK12.png")
         .withModel(new JsonModel("sight/scariron1"), "AK12.png")
         .withModel(new JsonModel("sight/scariron2"), "AK12.png")
         .withModel(new JsonModel("sight/faliron"), "AK12.png")
         .withModel(new JsonModel("sight/m14iron"), "AK12.png")
         .withModel(new JsonModel("sight/mp5iron"), "AK12.png")
         .withModel(new JsonModel("sight/mbusiron"), "AK12.png")
         .withModel(new JsonModel("sight/mp5iron"), "AK12.png")
         .withInventoryModelPositioning((model, s) -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
               GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.2, 1.2, 1.2);
            } else {
               GL11.glScalef(0.0F, 0.0F, 0.0F);
            }
         })
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.1F, -0.8F, 0.4F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.7, 0.7, 0.7);
            } else {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            } else {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withName("AKMIron")
         .withTextureName("Dummy.png")
         .build();
      SilencerMP7 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("SilencerMP7")
         .withTextureName("Dummy.png")
         .build();
      Silencer357 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor"), "GunmetalTexture.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer357")
         .withTextureName("Dummy.png")
         .build();
      Silencer57x38 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer57x38")
         .withTextureName("Dummy.png")
         .build();
      Silencer12Gauge = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor45acp"), "GunmetalTexture.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor45acp")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer12Gauge")
         .withTextureName("Dummy.png")
         .build();
      Silencer300AACBlackout = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor300aacblackout"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor300aacblackout")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor300aacblackout")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor300aacblackout")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor300aacblackout")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer300AACBlackout")
         .withTextureName("Dummy.png")
         .build();
      Silencer65x39 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.SILENCER)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/suppressor556x39"), "AK12.png")
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(0.6F, 0.1F, 0.3F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/suppressor556x39")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Silencer65x39")
         .withTextureName("Dummy.png")
         .build();
      Laser = new AttachmentBuilder()
         .withCategory(AttachmentCategory.GRIP)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/laser"), "AK12.png")
         .withPostRender(new EntityLaserBeamRenderer((p, s) -> GL11.glTranslatef(-0.2F, 1.4F, 1.8F)))
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (!JsonModel.is(model, "sight/ar15iron")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser")) {
               GL11.glTranslatef(0.6F, -0.3F, 0.65F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.8, 1.8, 1.8);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Laser")
         .withTextureName("Dummy.png")
         .build();
      Laser2 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.GRIP)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("misc/laser2"), "AK12.png")
         .withPostRender(new EntityLaserBeamRenderer((p, s) -> GL11.glTranslatef(-0.2F, 1.3F, 1.8F)))
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser2")) {
               GL11.glTranslatef(0.5F, -1.3F, -0.1F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser2")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser2")) {
               GL11.glTranslatef(0.6F, -0.3F, 0.65F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.8, 1.8, 1.8);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "misc/laser2")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.4, 0.4, 0.4);
            }
         })
         .withName("Laser2")
         .withTextureName("Dummy.png")
         .build();
      Grip2 = new AttachmentBuilder()
         .withCategory(AttachmentCategory.GRIP)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("grip/grip2"), "AK12.png")
         .withApply((a, i) -> i.setRecoil(i.getWeapon().getRecoil() * 0.6F))
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/grip2")) {
               GL11.glTranslatef(0.7F, -1.2F, 0.5F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/grip2")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/grip2")) {
               GL11.glTranslatef(0.6F, 0.3F, -0.5F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.3, 1.3, 1.3);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/grip2")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withName("Grip2")
         .withTextureName("Dummy.png")
         .build();
      Grip = new AttachmentBuilder()
         .withCategory(AttachmentCategory.GRIP)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("grip/angledgrip"), "AK12.png")
         .withApply((a, i) -> i.setRecoil(i.getWeapon().getRecoil() * 0.6F))
         .withApply((a, i) -> i.setRecoil(i.getWeapon().getRecoil()))
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/angledgrip")) {
               GL11.glTranslatef(0.7F, -1.1F, 0.5F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/angledgrip")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/angledgrip")) {
               GL11.glTranslatef(0.6F, 0.8F, -0.45F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.2, 1.2, 1.2);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/angledgrip")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withName("AngledGrip")
         .withTextureName("Dummy.png")
         .build();
      StubbyGrip = new AttachmentBuilder()
         .withCategory(AttachmentCategory.GRIP)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("grip/stubbygrip"), "AK12.png")
         .withApply((a, i) -> i.setRecoil(i.getWeapon().getRecoil() * 0.6F))
         .withRemove((a, i) -> i.setRecoil(i.getWeapon().getRecoil()))
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/stubbygrip")) {
               GL11.glTranslatef(0.7F, -1.2F, 0.5F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/stubbygrip")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/stubbygrip")) {
               GL11.glTranslatef(0.6F, 0.5F, -0.5F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.6, 1.6, 1.6);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/stubbygrip")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withName("StubbyGrip")
         .withTextureName("Dummy.png")
         .build();
      VGrip = new AttachmentBuilder()
         .withCategory(AttachmentCategory.GRIP)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("grip/vgrip"), "AK12.png")
         .withApply((a, i) -> i.setRecoil(i.getWeapon().getRecoil() * 0.6F))
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/vgrip")) {
               GL11.glTranslatef(0.7F, -1.1F, 0.5F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/vgrip")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/vgrip")) {
               GL11.glTranslatef(0.6F, 0.3F, -0.5F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(1.3, 1.3, 1.3);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/vgrip")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withName("VGrip")
         .withTextureName("Dummy.png")
         .build();
      Bipod = new AttachmentBuilder()
         .withCategory(AttachmentCategory.GRIP)
         .withCreativeTab(NewGunrizonsMod.AttachmentsTab)
         .withModel(new JsonModel("grip/bipod"), "AK12.png")
         .withApply((a, i) -> i.setRecoil(i.getWeapon().getRecoil() * 0.4F))
         .withFirstPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/bipod")) {
               GL11.glTranslatef(0.7F, -1.1F, 0.5F);
               GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withThirdPersonModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/bipod")) {
               GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
               GL11.glScaled(0.5, 0.5, 0.5);
            }
         })
         .withInventoryModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/bipod")) {
               GL11.glTranslatef(0.6F, -0.05F, -0.5F);
               GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScaled(0.9, 0.9, 0.9);
            }
         })
         .withEntityModelPositioning((model, itemStack) -> {
            if (JsonModel.is(model, "grip/bipod")) {
               GL11.glTranslatef(0.1F, 0.2F, 0.4F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               GL11.glScaled(0.6, 0.6, 0.6);
            }
         })
         .withName("Bipod")
         .withTextureName("Dummy.png")
         .build();
   }
}
