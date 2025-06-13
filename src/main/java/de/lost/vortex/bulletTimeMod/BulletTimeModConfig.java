package de.lost.vortex.bulletTimeMod;

import me.shedaniel.cloth.api.ConfigScreenFactory;
import me.shedaniel.cloth.api.ConfigBuilder;

public class BulletTimeModConfig {

    public static int staminaX = 20;
    public static int staminaY = 20;
    public static boolean staminaVisible = true;

    public static ConfigScreenFactory<?> createConfigScreen() {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle("Bullet Time Mod Config");

        builder.getOrCreateCategory("General")
                .addIntSlider("Stamina X", staminaX, 0, 1000, value -> staminaX = value)
                .addIntSlider("Stamina Y", staminaY, 0, 1000, value -> staminaY = value)
                .addBooleanToggle("Stamina Visible", staminaVisible, value -> staminaVisible = value);

        return builder.build();
    }
}
