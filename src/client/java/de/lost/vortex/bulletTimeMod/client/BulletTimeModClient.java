package de.lost.vortex.bulletTimeMod.client;

import de.lost.vortex.bulletTimeMod.sound.ModSound;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class BulletTimeModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModSound.initialize();
        //Overlay.registerOverlay();
        StaminaWheelOverlay.register();

}
}