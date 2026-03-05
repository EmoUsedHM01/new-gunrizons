package com.gtnewhorizon.newgunrizons.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.CommonProxy;
import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.items.ItemBullet;
import com.gtnewhorizon.newgunrizons.model.ammo.Bullet44;
import com.gtnewhorizon.newgunrizons.model.ammo.BulletBig;
import com.gtnewhorizon.newgunrizons.model.ammo.SMAWRocket;
import com.gtnewhorizon.newgunrizons.model.ammo.ShotgunShell;

public class Bullets {

    public static ItemBullet ShotgunShell;
    public static ItemBullet ShotgunShell410;
    public static ItemBullet Magnum44Ammo;
    public static ItemBullet Bullet762x39;
    public static ItemBullet Bullet556x39;
    public static ItemBullet Bullet556x45;
    public static ItemBullet Bullet762x54;
    public static ItemBullet Bullet762x51;
    public static ItemBullet Bullet45ACP;
    public static ItemBullet Bullet9mm;
    public static ItemBullet Bullet357;
    public static ItemBullet BMG50;
    public static ItemBullet Bullet57x28;
    public static ItemBullet Bullet46x30;
    public static ItemBullet Carbine30;
    public static ItemBullet Bullet65x39;
    public static ItemBullet Bullet10x24;
    public static ItemBullet Bullet10mm;
    public static ItemBullet Bullet300Blackout;
    public static ItemBullet SMAWRocket;
    public static ItemBullet Bullet50;
    public static ItemBullet Bullet9x39mm;
    public static ItemBullet BulletSpringfield3006;
    public static ItemBullet Bullet545x39;
    public static ItemBullet Bullet762x25;
    public static ItemBullet Bullet8mm;
    public static ItemBullet Bullet308;
    public static ItemBullet Bullet455;
    public static ItemBullet Bullet380200;
    public static ItemBullet Bullet762x21;
    public static ItemBullet Bullet792x33;
    public static ItemBullet Bullet792x57;
    public static ItemBullet Bullet303British;

    public static void init() {
        ShotgunShell = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("ShotgunShell")
            .withModel(new ShotgunShell(), "ShotgunShell.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        ShotgunShell410 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("ShotgunShell410")
            .withModel(new ShotgunShell(), "ShotgunShell.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet9x39mm = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet9x39mm")
            .withModel(new BulletBig(), "gold.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet8mm = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet8mm")
            .withModel(new BulletBig(), "gold.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        SMAWRocket = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("SMAWRocket")
            .withMaxStackSize(1)
            .withModel(new SMAWRocket(), "SMAW.png")
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.0D, 0.0D, 0.0D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.0D, 0.0D, 0.0D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, 0.8F, -2.1F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.6D, 0.6D, 0.6D);
            })
            .withTextureName("Dummy.png")
            .withCraftingRecipe("AXX", 'X', CommonProxy.SteelPlate, 'A', Blocks.tnt)
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet10x24 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.FunGunsTab)
            .withName("Bullet10x24")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet762x21 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet762x21")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet303British = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet303British")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet792x33 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet792x33")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet792x57 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet792x57")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.2F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.8D, 0.8D, 0.8D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.7D, 0.7D, 0.7D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Magnum44Ammo = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Magnum44Ammo")
            .withModel(new Bullet44(), "Bullet44.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.3D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet455 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet455")
            .withModel(new Bullet44(), "Bullet44.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.3D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet380200 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet380200")
            .withModel(new Bullet44(), "Bullet44.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.3D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Carbine30 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Carbine30")
            .withModel(new Bullet44(), "Bullet44.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.6D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.5D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.8D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        BulletSpringfield3006 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("BulletSpringfield3006")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.6D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.5D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.8D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet357 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet357")
            .withModel(new Bullet44(), "Bullet44.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.6D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.5D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.8D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet50 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet50AE")
            .withModel(new Bullet44(), "Bullet44.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.6D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.5D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.8D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet9mm = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet9mm")
            .withModel(new Bullet44(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.3D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet10mm = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet10mm")
            .withModel(new Bullet44(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.3D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet45ACP = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet45ACP")
            .withModel(new Bullet44(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.3D, 0.3D, 0.3D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.6D, 1.6D, 1.6D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet762x39 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet762x39")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.6D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.4D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet46x30 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet46x30")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.6D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.4D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet57x28 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet57x28")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.6D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.4D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet556x39 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet556x39")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.3D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.45D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.3D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet545x39 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet545x39")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.3D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.45D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.3D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet762x25 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet762x25")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.3D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.45D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.3D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet556x45 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet556x45")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.4D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.5D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.4D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet762x54 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet762x54")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.5D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.6D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.5D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet762x51 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet762x51")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.45D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.55D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.45D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet308 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet308")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.45D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.55D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.45D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet300Blackout = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet300Blackout")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.45D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.55D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.45D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        BMG50 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("BMG50")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.MEDIUM, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.4D, 0.7D, 0.4D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.5D, 0.7D, 0.5D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.4D, 1.8D, 1.4D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
        Bullet65x39 = (new ItemBullet.Builder()).withCreativeTab(NewGunrizonsMod.AmmoTab)
            .withName("Bullet65x39")
            .withModel(new BulletBig(), "Bullet.png")
            .withCrafting(8, CraftingComplexity.LOW, "ingotCopper", Items.gunpowder)
            .withFirstPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(0.1F, -1.0F, 0.4F);
                GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScaled(0.45D, 0.45D, 0.45D);
            })
            .withThirdPersonPositioning((player, itemStack) -> {
                GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(0.55D, 0.55D, 0.55D);
            })
            .withInventoryPositioning((itemStack) -> {
                GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
                GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
                GL11.glScaled(1.45D, 1.45D, 1.45D);
            })
            .withTextureName("Dummy.png")
            .build(NewGunrizonsMod.MOD_CONTEXT, ItemBullet.class);
    }
}
