package de.lost.vortex.bulletTimeMod.client;

import de.lost.vortex.bulletTimeMod.sound.ModSound;
import net.fabricmc.api.ClientModInitializer;

public class BulletTimeModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModSound.initialize();
    }
}
