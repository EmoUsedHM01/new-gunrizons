package com.gtnewhorizon.newgunrizons.items.factories.guns.pistol;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.WeaponRenderer;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.model.JsonModel;
import com.gtnewhorizon.newgunrizons.registry.AuxiliaryAttachments;
import com.gtnewhorizon.newgunrizons.registry.Magazines;
import java.util.Arrays;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class P08Artillerie {
   public Item createGun() {
      return new ItemWeapon.Builder()
         .withName("LugerArtillerie")
         .withFireRate(0.35F)
         .withRecoil(5.0F)
         .withMaxShots(1)
         .withShootSound("luger")
         .withSilencedShootSound("makarovsilenced")
         .withReloadSound("lugerreload")
         .withUnloadSound("pistolUnload")
         .withReloadingTime(50L)
         .withCrosshair("gun")
         .withCrosshairRunning("Running")
         .withCrosshairZoomed("Sight")
         .withFlashIntensity(0.4F)
         .withFlashScale(() -> 0.45F)
         .withFlashOffsetZ(() -> 2.5F)
         .withFlashOffsetX(() -> 0.0F)
         .withFlashOffsetY(() -> 0.2F)
         .withCreativeTab(NewGunrizonsMod.PistolsTab)
         .withInformationProvider(
            stack -> Arrays.asList("Type: Pistol Carbine", "Damage: 7.5", "Caliber: 7.62x21mm", "Magazines:", "8rnd 7.62x21mm Magazine", "Fire Rate: Semi")
         )
         .withCompatibleAttachment(AuxiliaryAttachments.LugerAction1, true, model -> {
            if (JsonModel.is(model, "action/lugeraction1")) {
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.LugerAction2, true, model -> {
            if (JsonModel.is(model, "action/lugeraction2")) {
               GL11.glScaled(1.0, 1.0, 1.0);
            }
         })
         .withCompatibleAttachment(AuxiliaryAttachments.MakarovTop, true, model -> {
            if (JsonModel.is(model, "action/makarovtop")) {
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/makarovrearsight")) {
               GL11.glTranslatef(-0.149F, -1.07F, -0.13F);
               GL11.glScaled(0.0, 0.0, 0.0);
            } else if (JsonModel.is(model, "sight/makarovfrontsight")) {
               GL11.glTranslatef(-0.1393F, -0.95F, -4.1F);
               GL11.glScaled(0.15, 0.25, 0.2);
            }
         })
         .withCompatibleAttachment(Magazines.LugerP08Mag, model -> {})
         .withTextureName("P08Carbine")
         .withRenderer(
            new WeaponRenderer.Builder()
               .withModel(new JsonModel("weapon/p08carbine"))
               .withEntityPositioning(itemStack -> {
                  GL11.glScaled(0.4, 0.4, 0.4);
                  GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
               })
               .withInventoryPositioning(itemStack -> {
                  GL11.glScaled(0.35, 0.35, 0.35);
                  GL11.glTranslatef(0.0F, 0.8F, 0.0F);
                  GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
               })
               .withThirdPersonPositioning(renderContext -> {
                  GL11.glScaled(0.5, 0.5, 0.5);
                  GL11.glTranslatef(-1.8F, -1.0F, 2.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioning(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glTranslatef(-0.8F, 0.9F, -3.0F);
               })
               .withFirstPersonPositioningRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glTranslatef(-0.8F, 0.9F, -2.5F);
                  GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.LugerAction1.getRenderablePart(), renderContext -> {
                  GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.LugerAction1.getRenderablePart(), renderContext -> {
                  GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.MakarovTop.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, -0.0F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.MakarovTop.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, -0.0F, -0.0F)
               )
               .withFirstPersonPositioningCustomRecoiled(
                  AuxiliaryAttachments.LugerAction2.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.4F)
               )
               .withFirstPersonPositioningCustomZoomingRecoiled(
                  AuxiliaryAttachments.LugerAction2.getRenderablePart(), renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.4F)
               )
               .withFirstPersonPositioningCustomRecoiled(Magazines.LugerP08Mag, renderContext -> {})
               .withFirstPersonPositioningCustomZoomingRecoiled(Magazines.LugerP08Mag, renderContext -> {})
               .withFirstPersonPositioningZoomingRecoiled(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glTranslatef(0.345F, 0.45F, -2.0F);
                  GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
               })
               .withFirstPersonCustomPositioning(Magazines.LugerP08Mag, renderContext -> {})
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.LugerAction1.getRenderablePart(), renderContext -> {
                  if (renderContext.getWeaponInstance().getAmmo() == 0) {
                     GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                     GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
                  }
               })
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.LugerAction2.getRenderablePart(), renderContext -> {
                  if (renderContext.getWeaponInstance().getAmmo() == 0) {
                     GL11.glTranslatef(0.0F, 0.0F, 0.4F);
                  }
               })
               .withFirstPersonCustomPositioning(AuxiliaryAttachments.MakarovTop.getRenderablePart(), renderContext -> {})
               .withFirstPersonPositioningReloading(new Transition(renderContext -> {
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.3F, 1.025F, -2.775F);
               }, 280L, 0L), new Transition(renderContext -> {
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.3F, 1.025F, -2.775F);
               }, 300L, 100L), new Transition(renderContext -> {
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-1.3F, 1.025F, -2.775F);
               }, 120L, 150L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glTranslatef(-0.75F, 1.5F, -2.7F);
               }, 200L, 50L), new Transition(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glTranslatef(-0.75F, 1.8F, -2.3F);
               }, 120L, 0L))
               .withFirstPersonPositioningUnloading(new Transition(renderContext -> {
                  GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-13.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glTranslatef(-1.2F, 1.45F, -2.9F);
               }, 150L, 50L))
               .withFirstPersonCustomPositioningUnloading(Magazines.LugerP08Mag, new Transition(renderContext -> {}, 250L, 1000L))
               .withFirstPersonCustomPositioningReloading(
                  Magazines.LugerP08Mag,
                  new Transition(renderContext -> GL11.glTranslatef(0.05F, 1.8F, 0.4F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.LugerAction1.getRenderablePart(), new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L))
               .withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.LugerAction1.getRenderablePart(), new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glTranslatef(0.0F, -0.5F, -0.6F);
                  GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
               }, 250L, 1000L), new Transition(renderContext -> {}, 250L, 1000L))
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.LugerAction2.getRenderablePart(), new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.4F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.LugerAction2.getRenderablePart(),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.4F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.4F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.4F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, 0.0F, 0.4F), 250L, 1000L),
                  new Transition(renderContext -> {}, 250L, 1000L)
               )
               .withFirstPersonCustomPositioningUnloading(
                  AuxiliaryAttachments.MakarovTop.getRenderablePart(), new Transition(renderContext -> GL11.glTranslatef(0.0F, -0.0F, -0.0F), 250L, 1000L)
               )
               .withFirstPersonCustomPositioningReloading(
                  AuxiliaryAttachments.MakarovTop.getRenderablePart(),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, -0.0F, -0.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, -0.0F, -0.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, -0.0F, -0.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, -0.0F, -0.0F), 250L, 1000L),
                  new Transition(renderContext -> GL11.glTranslatef(0.0F, -0.0F, -0.0F), 250L, 1000L)
               )
               .withFirstPersonPositioningZooming(renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glTranslatef(0.345F, 0.45F, -2.4F);
               })
               .withFirstPersonPositioningRunning(renderContext -> {
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.55F, 1.225F, -0.3F);
               })
               .withFirstPersonPositioningModifying(renderContext -> {
                  GL11.glScaled(3.0, 3.0, 3.0);
                  GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-2.375F, 0.7F, -0.125F);
               })
               .withFirstPersonHandPositioning(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.0F, -0.5F, 0.15F);
               }, renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               })
               .withFirstPersonHandPositioningModifying(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.025F, -0.875F, 0.25F);
               }, renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               })
               .withFirstPersonLeftHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.425F, -1.05F, 0.55F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.375F, -0.95F, 0.375F);
               }, 50L, 200L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.375F, -0.95F, 0.375F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.375F, -0.7F, -0.075F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.4F, -0.7F, -0.175F);
               }, 250L, 0L))
               .withFirstPersonRightHandPositioningReloading(new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               }, 250L, 1000L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               }, 250L, 50L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               }, 250L, 0L), new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               }, 250L, 0L))
               .withFirstPersonLeftHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.0F, -0.5F, 0.15F);
               }, 50L, 200L))
               .withFirstPersonRightHandPositioningUnloading(new Transition(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               }, 250L, 1000L))
               .withFirstPersonHandPositioningZooming(renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(-0.0F, -0.5F, 0.15F);
               }, renderContext -> {
                  GL11.glScalef(4.5F, 4.5F, 4.5F);
                  GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(-55.0F, 0.0F, 0.0F, 1.0F);
                  GL11.glTranslatef(0.4F, -0.55F, 0.175F);
               })
               .build()
         )
         .withSpawnEntityDamage(7.5F)
         .withSpawnEntityGravityVelocity(0.02F)
         .build();
   }
}
