package de.lost.vortex.bulletTimeMod;

import de.lost.vortex.bulletTimeMod.sound.ModSound;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BulletTimeMod implements ModInitializer {
    public static final String MOD_ID = "bullettimemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



    @Override
    public void onInitialize() {
        Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "bullet_time_enter"),
        SoundEvent.of(Identifier.of(MOD_ID, "bullet_time_enter")));
        ModSound.initialize();
        TickHandler.register();
    }
}