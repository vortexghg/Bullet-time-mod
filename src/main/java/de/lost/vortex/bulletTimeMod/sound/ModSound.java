package de.lost.vortex.bulletTimeMod.sound;

import de.lost.vortex.bulletTimeMod.BulletTimeMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModSound {
    private ModSound() {
        // private empty constructor to avoid accidental instantiation
    }

    // and is called in the mod to use the custom sound
    public static final SoundEvent BULLET_TIME_ENTER = registerSound("bullet_time_enter");
    public static final SoundEvent BULLET_TIME_LEAVE = registerSound("bullet_time_leave");
    public static final SoundEvent ENGINE_LOOP = registerSound("engine");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(BulletTimeMod.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
    public static void initialize() {
        BulletTimeMod.LOGGER.info("Registering " + BulletTimeMod.MOD_ID + " Sound");
    }
}
