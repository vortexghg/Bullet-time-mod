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

    public static final Identifier BULLET_TIME_LEAVE = Identifier.of("bullet_time_mod:bullet_time_leave");
    public static SoundEvent BULLET_TIME_LEAVE_EVENT = SoundEvent.of(BULLET_TIME_LEAVE);

    @Override
    public void onInitialize() {

        TickHandler.register();
        Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "bullet_time_enter_event"),
                SoundEvent.of(Identifier.of(MOD_ID, "bullet_time_enter")));
        Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "bullet_time_leave_event"),
                SoundEvent.of(Identifier.of(MOD_ID, "bullet_time_leave")));
        ModSound.init();


    }
}