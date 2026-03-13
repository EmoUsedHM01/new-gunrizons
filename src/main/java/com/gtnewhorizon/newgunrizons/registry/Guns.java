package com.gtnewhorizon.newgunrizons.registry;

import net.minecraft.item.Item;

import com.gtnewhorizon.newgunrizons.items.factories.guns.shotgun.DoomShotgunFactory;

public class Guns {

    public static Item DoomShotgun;

    public static void init() {
        DoomShotgun = new DoomShotgunFactory().createGun();
    }
}
