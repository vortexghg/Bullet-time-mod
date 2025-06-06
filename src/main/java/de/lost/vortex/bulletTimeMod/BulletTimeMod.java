package de.lost.vortex.bulletTimeMod;

import de.lost.vortex.bulletTimeMod.sound.ModSound;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BulletTimeMod implements ModInitializer {
    public static final String MOD_ID = "bullet-time-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        ModSound.initialize();
        TickHandler.register();
    }
}