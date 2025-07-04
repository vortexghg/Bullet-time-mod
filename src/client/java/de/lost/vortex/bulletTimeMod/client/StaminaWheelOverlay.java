package de.lost.vortex.bulletTimeMod.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class StaminaWheelOverlay {
    private static final Identifier WHEEL_TEXTURE = Identifier.of("bullet-time-mod", "textures/gui/stamina_wheel.png");

    public static void register() {
        HudRenderCallback.EVENT.register(StaminaWheelOverlay::onHudRender);
    }

    private static void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
    }

    private static void onHudRender(DrawContext ctx, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) return;

        int diameter = 96;
        int x = client.getWindow().getScaledWidth() - diameter - 10;
        int y = client.getWindow().getScaledHeight() - diameter - 48;

        // Hier ist der KORREKTE drawTexture-Aufruf!
        ctx.drawTexture(WHEEL_TEXTURE, x, y, 0.0F, 0.0F, 768, 768, 768, 768, 768, 768);
    }
}