package com.gtnewhorizon.newgunrizons.util;

public class MiscUtils {

    public static float clamp(float x, float lowerlimit, float upperlimit) {
        if (x < lowerlimit) {
            x = lowerlimit;
        }

        if (x > upperlimit) {
            x = upperlimit;
        }

        return x;
    }
}
