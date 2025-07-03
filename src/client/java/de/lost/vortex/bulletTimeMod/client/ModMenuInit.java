package de.lost.vortex.bulletTimeMod.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuInit implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // MidnightConfig integration
        return parent -> eu.midnightdust.lib.config.MidnightConfig.getScreen(parent, "bullet-time-mod");
    }
}