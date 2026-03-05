package com.gtnewhorizon.newgunrizons.registry;

import com.gtnewhorizon.newgunrizons.NewGunrizonsMod;

/**
 * Global sound constants and sound name resolution.
 * <p>
 * Sound names are resolved to resource location strings ({@code "newgunrizons:soundname"})
 * for use with {@code player.playSound()}.
 */
public final class Sounds {

    public static final String ZOOM = resolve("opticzoom");
    public static final String FIRE_MODE_SWITCH = resolve("gunfiremodeswitch");
    public static final String DRY_FIRE = resolve("dryfire");
    public static final String EXPLOSION = resolve("grenadeexplosion");

    private Sounds() {}

    /**
     * Resolves a sound name to its full resource location string.
     * Example: {@code "AKReload"} → {@code "newgunrizons:akreload"}
     */
    public static String resolve(String sound) {
        if (sound == null) {
            return null;
        }
        return NewGunrizonsMod.MODID + ":" + sound.toLowerCase();
    }
}
