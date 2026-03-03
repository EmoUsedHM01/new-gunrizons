package com.gtnewhorizon.newgunrizons.registry;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.model.magazine.AK12Magazine;
import com.gtnewhorizon.newgunrizons.model.magazine.AS50Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.ASValMag;
import com.gtnewhorizon.newgunrizons.model.magazine.DeagleMag;
import com.gtnewhorizon.newgunrizons.model.magazine.DragunovMag;
import com.gtnewhorizon.newgunrizons.model.magazine.FNFALMag;
import com.gtnewhorizon.newgunrizons.model.magazine.G18Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.GlockMagazine;
import com.gtnewhorizon.newgunrizons.model.magazine.HK417Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.HKMP5Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.L115Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.LeeEnfieldNo4Magazine;
import com.gtnewhorizon.newgunrizons.model.magazine.LugerMag;
import com.gtnewhorizon.newgunrizons.model.magazine.M107Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.M110Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.M14Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.M1A1mag;
import com.gtnewhorizon.newgunrizons.model.magazine.M249Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.M2CarbineMag;
import com.gtnewhorizon.newgunrizons.model.magazine.M3A1GreaseGunMag;
import com.gtnewhorizon.newgunrizons.model.magazine.M41AMag;
import com.gtnewhorizon.newgunrizons.model.magazine.M8A7Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.M9Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.MP18mag;
import com.gtnewhorizon.newgunrizons.model.magazine.MP40Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.MP7Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.MPXmag;
import com.gtnewhorizon.newgunrizons.model.magazine.MXMag;
import com.gtnewhorizon.newgunrizons.model.magazine.Mag308;
import com.gtnewhorizon.newgunrizons.model.magazine.Mag75rnd;
import com.gtnewhorizon.newgunrizons.model.magazine.Magazine545x39;
import com.gtnewhorizon.newgunrizons.model.magazine.Magazine762x39;
import com.gtnewhorizon.newgunrizons.model.magazine.MakarovMag;
import com.gtnewhorizon.newgunrizons.model.magazine.Mk48MOD1Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.NATO20rnd;
import com.gtnewhorizon.newgunrizons.model.magazine.NATO40rnd;
import com.gtnewhorizon.newgunrizons.model.magazine.NATODrum100;
import com.gtnewhorizon.newgunrizons.model.magazine.NATOFamasMag;
import com.gtnewhorizon.newgunrizons.model.magazine.NATOG36Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.NATOMag1;
import com.gtnewhorizon.newgunrizons.model.magazine.NATOMag2;
import com.gtnewhorizon.newgunrizons.model.magazine.P90Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.PMAG762x39;
import com.gtnewhorizon.newgunrizons.model.magazine.PPBizonMag;
import com.gtnewhorizon.newgunrizons.model.magazine.PPSHDrumMag;
import com.gtnewhorizon.newgunrizons.model.magazine.RPK74MMag;
import com.gtnewhorizon.newgunrizons.model.magazine.STG44Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.SV98Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.SVT40mag;
import com.gtnewhorizon.newgunrizons.model.magazine.Saiga12mag;
import com.gtnewhorizon.newgunrizons.model.magazine.ScarHMag;
import com.gtnewhorizon.newgunrizons.model.magazine.Tec9Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.Type100Mag;
import com.gtnewhorizon.newgunrizons.model.magazine.VEPRMag;
import com.gtnewhorizon.newgunrizons.model.magazine.VSSVintorezMag;
import com.gtnewhorizon.newgunrizons.model.magazine.revolverclip;
import com.gtnewhorizon.newgunrizons.weapon.ItemMagazine;

public class Magazines {

    public static ItemMagazine Magazine762x39;
    public static ItemMagazine PMAG762x39;
    public static ItemMagazine Mag75rnd762x39;
    public static ItemMagazine AK12Mag;
    public static ItemMagazine RPK74MMag;
    public static ItemMagazine AK74MMag;
    public static ItemMagazine AKS74UMag;
    public static ItemMagazine NATOMag1;
    public static ItemMagazine NATO20rnd;
    public static ItemMagazine NATO40rnd;
    public static ItemMagazine NATOMag2;
    public static ItemMagazine NATOFamasMag;
    public static ItemMagazine NATOG36Mag;
    public static ItemMagazine DragunovMag;
    public static ItemMagazine FALMag;
    public static ItemMagazine M110Mag;
    public static ItemMagazine M14DMRMag;
    public static ItemMagazine Glock21Mag;
    public static ItemMagazine G18Mag;
    public static ItemMagazine NATODrum100;
    public static ItemMagazine M9BerettaMag;
    public static ItemMagazine MP40Mag;
    public static ItemMagazine MP5KMag;
    public static ItemMagazine DeagleMag;
    public static ItemMagazine AS50Mag;
    public static ItemMagazine FNP90Mag;
    public static ItemMagazine M107BMag;
    public static ItemMagazine HKMP7Mag;
    public static ItemMagazine M1CarbineMag;
    public static ItemMagazine M2CarbineMag;
    public static ItemMagazine M240Mag;
    public static ItemMagazine L115Mag;
    public static ItemMagazine SV98Mag;
    public static ItemMagazine ColtM1911Mag;
    public static ItemMagazine M249Mag;
    public static ItemMagazine Mk48Mag;
    public static ItemMagazine MXMag;
    public static ItemMagazine M41AMag;
    public static ItemMagazine Mag10mm;
    public static ItemMagazine Magazine9mm;
    public static ItemMagazine ScarHMag;
    public static ItemMagazine VectorMag;
    public static ItemMagazine PP19Mag;
    public static ItemMagazine Glock32Mag;
    public static ItemMagazine HecateIIMag;
    public static ItemMagazine Deagle50Mag;
    public static ItemMagazine VSSVintorezMag;
    public static ItemMagazine ASValMag;
    public static ItemMagazine PythonClip;
    public static ItemMagazine M8A7Mag;
    public static ItemMagazine PPSH41DrumMag;
    public static ItemMagazine Type100Mag;
    public static ItemMagazine M1A1mag;
    public static ItemMagazine MP18mag;
    public static ItemMagazine HK417Mag;
    public static ItemMagazine M16A1Mag;
    public static ItemMagazine Mag308;
    public static ItemMagazine Saiga12mag;
    public static ItemMagazine VEPR12Mag;
    public static ItemMagazine SVT40mag;
    public static ItemMagazine LugerP08Mag;
    public static ItemMagazine M3A1Mag;
    public static ItemMagazine STG44Mag;
    public static ItemMagazine LeeEnfieldMag;
    public static ItemMagazine Saiga410Mag;
    public static ItemMagazine AK15Mag;
    public static ItemMagazine Tec9Mag;
    public static ItemMagazine MPXmag;

    public static void init() {
        PythonClip = (new ItemMagazine.Builder()).withAmmo(6)
            .withCompatibleBullet(Bullets.Bullet357)
            .withName("PythonClip")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new revolverclip(), "RevolverClip.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Magazine762x39 = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet762x39)
            .withName("Magazine762x39")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Magazine762x39(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        STG44Mag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet762x39)
            .withName("STG44Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new STG44Mag(), "STG44.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        SVT40mag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet762x54)
            .withName("SVT40Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new SVT40mag(), "SVT40.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.5F, -0.2F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        LeeEnfieldMag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet762x54)
            .withName("LeeEnfieldMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new LeeEnfieldNo4Magazine(), "LeeEnfieldNo4.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 1.8F, -2.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate)
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Saiga12mag = (new ItemMagazine.Builder()).withAmmo(5)
            .withCompatibleBullet(Bullets.ShotgunShell)
            .withName("Saiga12mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Saiga12mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        VEPR12Mag = (new ItemMagazine.Builder()).withAmmo(8)
            .withCompatibleBullet(Bullets.ShotgunShell)
            .withName("VEPR12Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new VEPRMag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Saiga410Mag = (new ItemMagazine.Builder()).withAmmo(7)
            .withCompatibleBullet(Bullets.ShotgunShell410)
            .withName("Saiga410Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Saiga12mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Type100Mag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet8mm)
            .withName("Type100Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Type100Mag(), "Type100Mag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.7F, 2.2F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        MP18mag = (new ItemMagazine.Builder()).withAmmo(32)
            .withCompatibleBullet(Bullets.Bullet762x25)
            .withName("MP18mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new MP18mag(), "MP18.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 1.0F, 1.8F);
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M1A1mag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet45ACP)
            .withName("M1A1mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M1A1mag(), "M1A1Thompson.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.5F, -0.1F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        PPSH41DrumMag = (new ItemMagazine.Builder()).withAmmo(71)
            .withCompatibleBullet(Bullets.Bullet762x25)
            .withName("PPSH41Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new PPSHDrumMag(), "PPSH41.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -1.0F, 0.2F);
                GL11.glRotatef(-200.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Mag75rnd762x39 = (new ItemMagazine.Builder()).withAmmo(75)
            .withCompatibleBullet(Bullets.Bullet762x39)
            .withName("Mag75rnd762x39")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Mag75rnd(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.2F, -0.6F);
                GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M8A7Mag = (new ItemMagazine.Builder()).withAmmo(32)
            .withCompatibleBullet(Bullets.Bullet300Blackout)
            .withName("M8A7Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.FunGunsTab)
            .withModel(new M8A7Mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.8F, -1.0F, 0.9F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.5D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.4F, -0.8F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        PMAG762x39 = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet762x39)
            .withName("PMAG762x39")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new PMAG762x39(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        AK15Mag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet762x39)
            .withName("AK15Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new PMAG762x39(), "AK15.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.5F, -1.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withTextureName("Dummy.png")
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        AK12Mag = (new ItemMagazine.Builder()).withAmmo(31)
            .withCompatibleBullet(Bullets.Bullet556x39)
            .withName("AK12Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new AK12Magazine(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.3F, -0.6F, 1.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -1.3F, 1.0F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.6F, 0.55F, -1.2F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        RPK74MMag = (new ItemMagazine.Builder()).withAmmo(40)
            .withCompatibleBullet(Bullets.Bullet762x39)
            .withName("RPK74MMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new RPK74MMag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.85F, -0.35F, 0.4F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        AK74MMag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet545x39)
            .withName("AK74MMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Magazine545x39(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.3F, -1.0F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        AKS74UMag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet545x39)
            .withName("AKS74UMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Magazine545x39(), "AKS74UMag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.7F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.3F, -1.0F);
                GL11.glRotatef(-120.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        NATOMag1 = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("NATOMag1")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new NATOMag1(), "NATOMag1.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.0F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        MPXmag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("MPXmag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new MPXmag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.7F, -1.5F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Mag308 = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet308)
            .withName("Mag308")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Mag308(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-1.0F, 0.6F, -1.2F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        NATO20rnd = (new ItemMagazine.Builder()).withAmmo(20)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("NATO20rnd")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new NATO20rnd(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.0F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M16A1Mag = (new ItemMagazine.Builder()).withAmmo(20)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("M16A1Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M110Mag(), "NATOMag1.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.0F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        NATO40rnd = (new ItemMagazine.Builder()).withAmmo(40)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("NATO40rnd")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new NATO40rnd(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, -0.1F, 0.2F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        NATOMag2 = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("NATOMag2")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new NATOMag2(), "NATOMag2.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.0F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        NATOFamasMag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("NATOFamasMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new NATOFamasMag(), "NATOMag1.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.3F, 0.1F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        NATOG36Mag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("NATOG36Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new NATOG36Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.2F, 0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        DragunovMag = (new ItemMagazine.Builder()).withAmmo(11)
            .withCompatibleBullet(Bullets.Bullet762x54)
            .withName("DragunovMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new DragunovMag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.3F, 2.1F, -2.8F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        FALMag = (new ItemMagazine.Builder()).withAmmo(20)
            .withCompatibleBullet(Bullets.Bullet762x51)
            .withName("FALMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new FNFALMag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.2F, 0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M110Mag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet762x51)
            .withName("M110Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M110Mag(), "NATOMag1.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.25F, 0.1F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        HK417Mag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet762x51)
            .withName("HK417Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new HK417Mag(), "HK417Mag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-1.0F, 0.6F, -1.2F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M14DMRMag = (new ItemMagazine.Builder()).withAmmo(21)
            .withCompatibleBullet(Bullets.Bullet762x51)
            .withName("M14DMRMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M14Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.3F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Glock21Mag = (new ItemMagazine.Builder()).withAmmo(13)
            .withCompatibleBullet(Bullets.Bullet45ACP)
            .withName("Glock21Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new GlockMagazine(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.6F, -0.3F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        LugerP08Mag = (new ItemMagazine.Builder()).withAmmo(8)
            .withCompatibleBullet(Bullets.Bullet762x21)
            .withName("LugerP08Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new LugerMag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.6F, -0.5F, 0.6F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Tec9Mag = (new ItemMagazine.Builder()).withAmmo(20)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("Tec9Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Tec9Mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-1.0F, -1.2F, 0.0F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.6D, 0.6D, 0.6D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        G18Mag = (new ItemMagazine.Builder()).withAmmo(20)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("G18Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new G18Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.6F, -0.65F, 0.45F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Glock32Mag = (new ItemMagazine.Builder()).withAmmo(14)
            .withCompatibleBullet(Bullets.Bullet357)
            .withName("Glock32Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new GlockMagazine(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.6F, -0.3F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M9BerettaMag = (new ItemMagazine.Builder()).withAmmo(15)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("M9BerettaMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M9Mag(), "M9Mag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.5F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Mag10mm = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet10mm)
            .withName("Mag10mm")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.FunGunsTab)
            .withModel(new M9Mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.5F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Magazine9mm = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("Magazine9mm")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new MakarovMag(), "MakarovMag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.5F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        NATODrum100 = (new ItemMagazine.Builder()).withAmmo(100)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("NATODrum100")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new NATODrum100(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.5F, 0.4F, 0.4F);
                GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        MP40Mag = (new ItemMagazine.Builder()).withAmmo(32)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("MP40Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new MP40Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -1.3F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.2F, 0.0F, -1.3F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M3A1Mag = (new ItemMagazine.Builder()).withAmmo(31)
            .withCompatibleBullet(Bullets.Bullet45ACP)
            .withName("M3A1Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M3A1GreaseGunMag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -1.3F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.2F, -0.2F, -1.8F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        VectorMag = (new ItemMagazine.Builder()).withAmmo(26)
            .withCompatibleBullet(Bullets.Bullet45ACP)
            .withName("VectorMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new MP40Mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -1.3F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.2F, 0.0F, -1.3F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        MP5KMag = (new ItemMagazine.Builder()).withAmmo(25)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("MP5KMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new HKMP5Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 1.0F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -1.6F, 1.0F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.95F, -1.6F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Deagle50Mag = (new ItemMagazine.Builder()).withAmmo(7)
            .withCompatibleBullet(Bullets.Bullet50)
            .withName("Deagle50Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new DeagleMag(), "Deagle44.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.5F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        VSSVintorezMag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet9x39mm)
            .withName("VSSVintorezMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new VSSVintorezMag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.3F, 0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        ASValMag = (new ItemMagazine.Builder()).withAmmo(20)
            .withCompatibleBullet(Bullets.Bullet9x39mm)
            .withName("ASValMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new ASValMag(), "ASValMag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.3F, 0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        AS50Mag = (new ItemMagazine.Builder()).withAmmo(5)
            .withCompatibleBullet(Bullets.BMG50)
            .withName("AS50Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new AS50Mag(), "NATOMag1.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.9F, 0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        HecateIIMag = (new ItemMagazine.Builder()).withAmmo(7)
            .withCompatibleBullet(Bullets.BMG50)
            .withName("HecateIIMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new AS50Mag(), "NATOMag1.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.9F, 0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 0.8999999761581421D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        FNP90Mag = (new ItemMagazine.Builder()).withAmmo(50)
            .withCompatibleBullet(Bullets.Bullet57x28)
            .withName("FNP90Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new P90Mag(), "P90Mag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.3F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.3F, 0.5F, 0.1F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(0.3F, 1.2F, 0.4F);
                GL11.glRotatef(-170.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M107BMag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.BMG50)
            .withName("M107BMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M107Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.1F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        HKMP7Mag = (new ItemMagazine.Builder()).withAmmo(20)
            .withCompatibleBullet(Bullets.Bullet46x30)
            .withName("HKMP7Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new MP7Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(1.0F, -0.0F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M1CarbineMag = (new ItemMagazine.Builder()).withAmmo(15)
            .withCompatibleBullet(Bullets.Carbine30)
            .withName("M1CarbineMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M14Mag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.3F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M2CarbineMag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Carbine30)
            .withName("M2CarbineMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M2CarbineMag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.6F, -0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        L115Mag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet308)
            .withName("L115Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new L115Mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.3F, -0.5F, 0.6F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.2F, -0.5F, 0.9F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-1.0F, 0.3F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        SV98Mag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet762x54)
            .withName("SV98Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new SV98Mag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.3F, -0.5F, 0.6F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.2F, -0.5F, 0.9F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-1.0F, 0.3F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        ColtM1911Mag = (new ItemMagazine.Builder()).withAmmo(10)
            .withCompatibleBullet(Bullets.Bullet45ACP)
            .withName("ColtM1911Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new GlockMagazine(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.6F, -0.3F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.LOW, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M249Mag = (new ItemMagazine.Builder()).withAmmo(200)
            .withCompatibleBullet(Bullets.Bullet556x45)
            .withName("M249Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new M249Mag(), "M249.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.2F, 0.4F, 0.4F);
                GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, CommonProxy.BigSteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        Mk48Mag = (new ItemMagazine.Builder()).withAmmo(100)
            .withCompatibleBullet(Bullets.Bullet762x51)
            .withName("Mk48Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new Mk48MOD1Mag(), "Mk48MOD1.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.2F, 0.4F, 0.4F);
                GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, CommonProxy.BigSteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        MXMag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet65x39)
            .withName("MXMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new MXMag(), "MXMag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.0F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.0D, 1.0D, 1.0D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        M41AMag = (new ItemMagazine.Builder()).withAmmo(99)
            .withCompatibleBullet(Bullets.Bullet65x39)
            .withName("M41AMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.FunGunsTab)
            .withModel(new M41AMag(), "M41AMag.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.1F, -1.0F, 0.2F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.2F, 0.6F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, -0.6F, 0.8F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.2999999523162842D, 1.2999999523162842D, 1.2999999523162842D);
            })
            .withCrafting(CraftingComplexity.HIGH, CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        ScarHMag = (new ItemMagazine.Builder()).withAmmo(30)
            .withCompatibleBullet(Bullets.Bullet300Blackout)
            .withName("ScarHMag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new ScarHMag(), "GunmetalTexture.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.7F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-1.0F, -0.5F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.4F, 0.2F, 0.4F);
                GL11.glRotatef(-130.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(1.100000023841858D, 1.100000023841858D, 1.100000023841858D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
        PP19Mag = (new ItemMagazine.Builder()).withAmmo(65)
            .withCompatibleBullet(Bullets.Bullet9mm)
            .withName("PP19Mag")
            .withModId("newgunrizons")
            .withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withModel(new PPBizonMag(), "AK12.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -0.3F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.3F, 0.5F, 0.1F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(0.3F, 0.7F, -1.2F);
                GL11.glRotatef(-170.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
            })
            .withCrafting(CraftingComplexity.MEDIUM, CommonProxy.SteelPlate, "ingotSteel")
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemMagazine.class);
    }
}
