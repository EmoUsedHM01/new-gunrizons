package com.gtnewhorizon.newgunrizons.items.factories.guns.smg;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.JsonModel;
import com.gtnewhorizon.newgunrizons.registry.Attachments;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import java.util.Arrays;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class MP18Factory {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("MP18")
         .withFireRate(0.55F)
         .withRecoil(3.0F)
         .withShootSound("Type100")
         .withSilencedShootSound("PPSh41Silenced")
         .withReloadSound("type100reload")
         .withUnloadSound("type100unload")
         .withReloadingTime(50L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.3F)
         .withFlashOffsetZ(() -> 0.9F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.0F)
         .withInaccuracy(3.0F)
         .withShellCasingForwardOffset(0.23F)
         .withShellCasingVerticalOffset(-0.02F)
         .withCreativeTab(NewGunrizonsMod.SMGTab)
         .withInformationProvider(
            stack -> Arrays.asList(
               "Type: Submachine gun", "Damage: 7.1", "Caliber: 7.62x25mm", "Magazines:", "32rnd 7.62x25mm Drum Magazine", "Fire Rate: Auto"
            )
         )
         .withCompatibleAttachment(Magazines.MP18mag, model -> {})
         .withCompatibleAttachment(AuxiliaryAttachments.Extra, true, model -> {
            if (JsonModel.is(model, "sight/g36ciron1")) {
               GL11.glTranslatef(-0.04F, -1.216F, -1.65F);
               GL11.glScaled(0.0, 0.0, 0.0);
               GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/g36ciron2")) {
               GL11.glTranslatef(-0.13F, -0.73F, -3.45F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron1")) {
               GL11.glTranslatef(0.0F, -1.5F, -0.5F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/akmiron2")) {
               GL11.glTranslatef(0.13F, -1.55F, -3.05F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/ak47iron")) {
               GL11.glTranslatef(-0.155F, -0.93F, -0.5F);
               GL11.glScaled(0.45, 0.4, 0.4);
            } else if (JsonModel.is(model, "sight/m4iron1")) {
               GL11.glTranslatef(0.155F, -1.74F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/m4iron2")) {
               GL11.glTranslatef(0.26F, -1.55F, -2.35F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/p90iron")) {
               GL11.glTranslatef(0.26F, -1.55F, -2.35F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron1")) {
               GL11.glTranslatef(0.165F, -1.65F, 1.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/scariron2")) {
               GL11.glTranslatef(0.25F, -1.55F, -2.0F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/faliron")) {
               GL11.glTranslatef(-0.07F, -0.71F, -3.7F);
               GL11.glScaled(0.1, 0.3, 0.8);
               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
            } else if (JsonModel.is(model, "sight/m14iron")) {
               GL11.glTranslatef(0.129F, -1.63F, -2.08F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/mp5iron")) {
               GL11.glTranslatef(0.215F, -1.54F, 1.2F);
               GL11.glScaled(0.0, 0.0, 0.0);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.MP18action, true, model -> {})
         .withTextureName("mp18")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/mp18"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.35, 0.35, 0.35);
                  GL11.glTranslatef(1.0F, 0.8F, 0.0F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.55, 0.55, 0.55);
                  GL11.glTranslatef(-1.1F, -0.0F, 1.2F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.5F, 0.85F, -1.2F);
                  GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(44.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.52F, 0.85F, -1.0F);
                  GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(0.21F, 0.425F, -0.9F);
                  GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                  }
               })
               .withFirstPersonCustomPositioning(Magazines.MP18mag, renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(Magazines.MP18mag, renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.MP18mag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.MP18action.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.MP18action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.75F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.MP18action.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.75F)
               )
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.775F, 0.9F, -0.2F);
               }, 280L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.775F, 0.9F, -0.2F);
               }, 300L, 100L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, 1.275F, -0.575F);
               }, 310L, 55L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, 1.275F, -0.575F);
               }, 200L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.175F, 1.275F, -0.575F);
               }, 210L, 40L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.775F, 0.9F, -0.2F);
               }, 150L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(4.0F, 4.0F, 4.0F);
                  GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.775F, 0.9F, -0.2F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(
                  Magazines.MP18mag,
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.7F, 1.0F, 1.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  Magazines.MP18mag,
                  new Transition(renderContext -> {
                     GL11.glTranslatef(-0.5F, 1.0F, -0.2F);
                     GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
                     GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
                  }, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.MP18action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 1.3F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.MP18action.getRenderablePart(),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScalef(5.0F, 5.0F, 5.0F);
                  GL11.glTranslatef(0.22F, 0.425F, -1.0F);
                  if (ItemWeapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
                  }
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(5.0, 5.0, 5.0);
                  GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.3F, 0.675F, -0.575F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(5.0, 5.0, 5.0);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.775F, 0.25F, -0.4F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.03F, -0.45F, 0.375F);
               }, renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               })
               .withFirstPersonHandPositioningRunning(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.0F, -0.45F, 0.375F);
               }, renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               })
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.03F, -0.45F, 0.375F);
               }, renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(3.5F, 3.5F, 3.5F);
                  GL11.glTranslatef(1.5F, 0.1F, -0.2F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
               }, renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(0.2F, -0.675F, 0.575F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.03F, -0.45F, 0.375F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.03F, -0.45F, 0.375F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.03F, -0.45F, 0.375F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.03F, -0.45F, 0.375F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.6F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.1F, -0.1F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.6F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.225F, -0.475F, -0.075F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(3.0F, 3.0F, 3.6F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.2F, -0.1F, -0.1F);
               }, 250L, 50L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.03F, -0.45F, 0.375F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 3.7F);
                  GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glTranslatef(0.2F, -0.675F, 0.575F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(2.8F, 2.8F, 2.8F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.575F, -0.7F, 0.15F);
               }, 250L, 50L))
               .build()
         )
         .withSpawnEntityDamage(7.1F)
         .withSpawnEntityGravityVelocity(0.028F)
         .build();
   }
}
