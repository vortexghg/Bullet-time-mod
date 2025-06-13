package de.lost.vortex.bulletTimeMod;


import net.minecraft.client.gui.screen.Screen;

public class Overlay {
    public static void renderOverlay(Screen ctx) {
        if (BulletTimeModConfig.staminaVisible) {
            drawStaminaRad(ctx,
                    BulletTimeModConfig.staminaX
            , BulletTimeModConfig.staminaY);
        }
    }
    private static void drawStaminaRad(Screen ctx, int x, int y) {
        //implementere noch die logik für das rad
    }
}
