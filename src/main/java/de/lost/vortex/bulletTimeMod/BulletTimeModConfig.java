package de.lost.vortex.bulletTimeMod;

import eu.midnightdust.lib.config.MidnightConfig;

public class BulletTimeModConfig extends MidnightConfig {

    @Entry(min = 0, max = 1000)
    public static int staminaX = 20;

    @Entry(min = 0, max = 1000)
    public static int staminaY = 20;

    @Entry(min = 10, max = 200)

    @Entry
    public static boolean staminaVisible = true;
}
