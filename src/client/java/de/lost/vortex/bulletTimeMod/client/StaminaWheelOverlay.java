package de.lost.vortex.bulletTimeMod.client;

    import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
    import net.minecraft.client.MinecraftClient;
    import net.minecraft.client.gui.DrawContext;
    import net.minecraft.client.render.RenderLayer;
    import net.minecraft.util.Identifier;
    import com.mojang.blaze3d.systems.RenderSystem;
    import org.lwjgl.opengl.GL11;

    public class StaminaWheelOverlay {
        private static final Identifier WHEEL_TEXTURE = new Identifier("bullet-time-mod:textures/gui/stamina_wheel.png");

        private static float stamina = 0.67f;
        private static float colorR = 0.31f, colorG = 0.87f, colorB = 0.38f, colorA = 1.0f;

        public static void setStamina(float percent) {
            stamina = Math.max(0, Math.min(percent, 1));
        }
        public static void setColor(float r, float g, float b, float a) {
            colorR = r; colorG = g; colorB = b; colorA = a;
        }

        public static void register() {
            HudRenderCallback.EVENT.register((ctx, tickDelta) -> onHudRender(ctx, tickDelta));
        }

        private static void onHudRender(DrawContext ctx, float tickDelta) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.options.hudHidden) return;

            int diameter = 96;
            int texSize = 768;
            int x = client.getWindow().getScaledWidth() - diameter - 10;
            int y = client.getWindow().getScaledHeight() - diameter - 48;
            int cx = x + diameter / 2;
            int cy = y + diameter / 2;

            ctx.drawTexture(
                    () -> RenderLayer.getGui(),
                    WHEEL_TEXTURE,
                    x, y,
                    0.0F, 0.0F,
                    diameter, diameter,
                    diameter, diameter,
                    texSize, texSize
            );

            drawStaminaArc(cx, cy, diameter/2, diameter/2 - 10, stamina, colorR, colorG, colorB, colorA);
        }

        private static void drawStaminaArc(int cx, int cy, int outerR, int innerR, float percent, float r, float g, float b, float a) {
            RenderSystem.enableBlend();
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
            float startAngle = -90f;
            float arcAngle = 360f * percent;
            for (int i = 0; i <= 100; i++) {
                float angle = startAngle + arcAngle * i / 100f;
                double rad = Math.toRadians(angle);
                float ox = (float)Math.cos(rad), oy = (float)Math.sin(rad);
                GL11.glColor4f(r, g, b, a);
                GL11.glVertex2f(cx + ox * outerR, cy + oy * outerR);
                GL11.glVertex2f(cx + ox * innerR, cy + oy * innerR);
            }
            GL11.glEnd();
            RenderSystem.disableBlend();
        }
    }