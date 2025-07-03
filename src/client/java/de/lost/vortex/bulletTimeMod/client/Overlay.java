package de.lost.vortex.bulletTimeMod.client;

import de.lost.vortex.bulletTimeMod.BulletTimeMod;
import de.lost.vortex.bulletTimeMod.BulletTimeModConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class Overlay {
    private static float currentStamina = BulletTimeModConfig.startStamina;

    public static void registerOverlay() {
        HudRenderCallback.EVENT.register(Overlay::onHudRender);
        BulletTimeMod.LOGGER.info("Registering Overlay");
    }

    public static void setCurrentStamina(float value) {
        currentStamina = Math.max(0, Math.min(value, BulletTimeModConfig.maxStamina));
    }

    private static void onHudRender(DrawContext ctx, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        // Kreis NICHT zeichnen, wenn HUD versteckt ist (F1)
        if (client.player == null || client.options.hudHidden) return;

        currentStamina += BulletTimeModConfig.regenRate;
        if (currentStamina > BulletTimeModConfig.maxStamina) currentStamina = BulletTimeModConfig.maxStamina;

        int diameter = BulletTimeModConfig.wheelRadius; // jetzt "Durchmesser"
        int padding = 8;
    int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        // Rechts über der Hotbar, etwas Abstand zum Rand
        int x = screenWidth - diameter - padding;
        int y = screenHeight - 48 - diameter - padding;

        float percent = currentStamina / BulletTimeModConfig.maxStamina;
        int fillColor = parseColor(BulletTimeModConfig.staminaColor);

        // Hintergrund (leicht transparent)
        drawFilledCircle(ctx, x + diameter/2, y + diameter/2, diameter/2, 0x80000000);
        // Vordergrund (gefüllt je nach Stamina)
        drawFilledCircle(ctx, x + diameter/2, y + diameter/2, (int)((diameter/2) * percent), fillColor);
    }

    // Normaler ausgefüllter Kreis
    private static void drawFilledCircle(DrawContext ctx, int cx, int cy, int radius, int color) {
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                if (x*x + y*y <= radius*radius) {
                    ctx.fill(cx + x, cy + y, cx + x + 1, cy + y + 1, color);
                }
            }
        }
    }

    private static int parseColor(String hex) {
        try {
            if (hex.startsWith("#")) hex = hex.substring(1);
            if (hex.length() == 6) hex = "FF" + hex;
            return (int)Long.parseLong(hex, 16);
        } catch (Exception e) {
            return 0xFF00FF00; // fallback: grün
        }
    }
}