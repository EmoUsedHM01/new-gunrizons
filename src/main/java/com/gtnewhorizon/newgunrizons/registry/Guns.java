package com.gtnewhorizon.newgunrizons.registry;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.items.factories.guns.assault.LaserRifleFactory;
import com.gtnewhorizon.newgunrizons.items.factories.guns.assault.PlasmaRifleFactory;
import com.gtnewhorizon.newgunrizons.items.factories.guns.shotgun.DoomShotgunFactory;

public class Guns {

    public static Item DoomShotgun;
    public static Item PlasmaRifle;
    public static Item LaserRifle;

    public static void init() {
        DoomShotgun = new DoomShotgunFactory().createGun();
        PlasmaRifle = new PlasmaRifleFactory().createGun();
        LaserRifle = new LaserRifleFactory().createGun();
    }
}
