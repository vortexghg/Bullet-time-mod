package de.lost.vortex.bulletTimeMod.client;

import de.lost.vortex.bulletTimeMod.BulletTimeMod;

public class Overlay {

    public static void registerOverlay() {
        BulletTimeMod.LOGGER.info("Registering Overlay");
    }

    private static float currentStamina = BulletTimeModConfig.startStamina;

    public static void registerOverlay() {
        // Registriere das HUD Rendering
        HudRenderCallback.EVENT.register(Overlay::onHudRender);

        // Stamina regeneration (Client Tick)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                currentStamina += BulletTimeModConfig.regenRate;
                if (currentStamina > BulletTimeModConfig.maxStamina)
                    currentStamina = BulletTimeModConfig.maxStamina;
            }
        });
    }

    public static void setCurrentStamina(float value) {
        currentStamina = Math.max(0, Math.min(value, BulletTimeModConfig.maxStamina));
    }

    private static void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        int centerX = client.getWindow().getScaledWidth() / 2;
        int y = client.getWindow().getScaledHeight() - 40;

        float staminaPercent = currentStamina / BulletTimeModConfig.maxStamina;
        int radius = BulletTimeModConfig.wheelRadius;
        int thickness = BulletTimeModConfig.wheelThickness;
        int color = parseColor(BulletTimeModConfig.staminaColor);

        // Hintergrundkreis (grau)
        drawCircle(drawContext, centerX, y, radius, thickness, 0x80000040, 1f);

        // Vordergrund (Stamina, farbig)
        drawCircle(drawContext, centerX, y, radius, thickness, color, staminaPercent);
    }

    private static void drawCircle(DrawContext ctx, int cx, int cy, int radius, int thickness, int color, float fill) {
        int segments = 60;
        int filled = (int)(segments * fill);
        for (int i = 0; i < filled; i++) {
            double angle1 = 2 * Math.PI * i / segments - Math.PI / 2;
            double angle2 = 2 * Math.PI * (i + 1) / segments - Math.PI / 2;
            int x1o = (int)(Math.cos(angle1) * radius);
            int y1o = (int)(Math.sin(angle1) * radius);
            int x2o = (int)(Math.cos(angle2) * radius);
            int y2o = (int)(Math.sin(angle2) * radius);
            int x1i = (int)(Math.cos(angle1) * (radius - thickness));
            int y1i = (int)(Math.sin(angle1) * (radius - thickness));
            int x2i = (int)(Math.cos(angle2) * (radius - thickness));
            int y2i = (int)(Math.sin(angle2) * (radius - thickness));

            ctx.fill(cx + x1i, cy + y1i, cx + x1o, cy + y1o, color);
            ctx.fill(cx + x2i, cy + y2i, cx + x2o, cy + y2o, color);
        }
    }

    // Hilfsmethode: Hex-Farbe zu ARGB int
    private static int parseColor(String hex) {
        try {
            if (hex.startsWith("#")) hex = hex.substring(1);
            if (hex.length() == 6) hex = "FF" + hex;
            return (int)Long.parseLong(hex, 16);
        } catch (Exception e) {
            return 0xFF00FF00;
        }
    }
}
