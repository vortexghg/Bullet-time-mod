package de.lost.vortex.bulletTimeMod;

import de.lost.vortex.bulletTimeMod.sound.ModSound;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


public class BulletTimeMod implements ModInitializer {
    public static final String MOD_ID = "bullettimemod";
    public static final Identifier BULLET_TIME_ENTER = Identifier.of("bullet_time_mod:bullet_time_enter");
    public static SoundEvent BULLET_TIME_ENTER_EVENT = SoundEvent.of(BULLET_TIME_ENTER);

    @Override
    public void onInitialize() {
        Registry.register(Registries.SOUND_EVENT, BULLET_TIME_ENTER, BULLET_TIME_ENTER_EVENT);
        TickHandler.register();
        Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "bullet_time_enter"),
                SoundEvent.of(Identifier.of(MOD_ID, "bullet_time_enter")));
        ModSound.init();

    }
}