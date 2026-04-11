package com.gtnewhorizon.newgunrizons.client;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ADSConfig {

    public static Configuration config;

    public static String adsMode = "toggle";

    public static void init(File configFile) {
        config = new Configuration(configFile);
        config.load();

        adsMode = config.getString(
            "adsMode",
            "client",
            "toggle",
            "Aim Down Sights mode. 'toggle' = press to switch between aiming and hipfiring. "
                + "'hold' = aim while holding the key, hipfire when released.",
            new String[] { "toggle", "hold" }
        );

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static void reload() {
        adsMode = config.getCategory("client").get("adsMode").getString();
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static boolean isHoldMode() {
        return "hold".equalsIgnoreCase(adsMode);
    }
}
