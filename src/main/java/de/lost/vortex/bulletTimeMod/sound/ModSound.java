package de.lost.vortex.bulletTimeMod.sound;

import de.lost.vortex.bulletTimeMod.BulletTimeMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;



public class ModSound {
    public static SoundEvent BULLET_TIME_ENTER_EVENT;
    public static SoundEvent BULLET_TIME_LEAVE_EVENT;

    public static void init(){}



    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(BulletTimeMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

}
