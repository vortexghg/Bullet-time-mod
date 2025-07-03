package de.lost.vortex.bulletTimeMod;

import eu.midnightdust.lib.config.MidnightConfig;

public class BulletTimeModConfig extends MidnightConfig {
    @Entry(category = "Stamina Wheel", isSlider = true, min = 8, max = 32)
    public static int wheelRadius = 16;

    @Entry(category = "Stamina Wheel", isSlider = true, min = 10, max = 500)
    public static int maxStamina = 100;

    @Entry(category = "Stamina Wheel", isSlider = true, min = 0, max = 500)
    public static int startStamina = 80;

    @Entry(category = "Stamina Wheel", isSlider = true, min = 0.01f, max = 1.0f, precision = 1000)
    public static float regenRate = 0.05f;

    @Entry(category = "Stamina Wheel", isColor = true)
    public static String staminaColor = "#00FF00";
}