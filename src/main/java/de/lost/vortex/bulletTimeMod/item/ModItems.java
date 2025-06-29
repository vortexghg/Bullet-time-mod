package de.lost.vortex.bulletTimeMod.item;

import de.lost.vortex.bulletTimeMod.BulletTimeMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static void registerItems() {
        BulletTimeMod.LOGGER.info("Registering Mod Items");
    }

    public static final Item PARAGLIDER = registerItem(Item::new);


    private static Item registerItem(Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(BulletTimeMod.MOD_ID, "paraglider"),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BulletTimeMod.MOD_ID, "paraglider")))));
    }
}
