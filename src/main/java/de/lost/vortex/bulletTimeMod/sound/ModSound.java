package de.lost.vortex.bulletTimeMod.sound;

import de.lost.vortex.bulletTimeMod.BulletTimeMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSound {
    public static final SoundEvent BULLET_TIME_ENTER = registerSoundEvent("bullet_time_enter");
    public static final SoundEvent BULLET_TIME_LEAVE = registerSoundEvent("bullet_time_leave");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(BulletTimeMod.MOD_ID);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

}
