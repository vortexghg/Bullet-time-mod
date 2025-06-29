package de.lost.vortex.bulletTimeMod.item;

import de.lost.vortex.bulletTimeMod.BulletTimeMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup BULLET_TIME_MOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(BulletTimeMod.MOD_ID, "bullet_mod_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PARAGLIDER))
                    .displayName(Text.translatable("itemgroup.BulletTimeMod.paraglider"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PARAGLIDER);
                    }).build());



    public static void registerItemGroups() {
        BulletTimeMod.LOGGER.info("Registering ModItemGroups");
    }

}
