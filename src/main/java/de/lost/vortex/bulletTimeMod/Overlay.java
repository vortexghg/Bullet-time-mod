package de.lost.vortex.bulletTimeMod;

public class Overlay {
    if (BulletTimeModConfig.staminaVisible) {
        drawStaminaRad(
                ctx,
                stamina, maxStamina,
                BulletTimeModConfig.staminaX,
                BulletTimeModConfig.staminaY,
                BulletTimeModConfig.staminaRadius,
        )
    }
}
