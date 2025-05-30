package de.lost.vortex.bulletTimeMod;

import net.fabricmc.api.ModInitializer;



public class BulletTimeMod implements ModInitializer {
    public static final String MOD_ID = "bullettimemod";


    @Override
    public void onInitialize() {

        TickHandler.register();

    }
}