package name.tennocraft.registry;

import name.tennocraft.TennoCraft;
import name.tennocraft.weapon.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

public class ItemRegistry {

    public static final Item MUTAGEN_SAMPLE = registerItem(
            "mutagen_sample",
            new Item(new Item.Properties()));

    private static Item registerItem(String path, Item item) {
        ResourceLocation id = new ResourceLocation(TennoCraft.TENNOCRAFT, path);
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }
    public static final Item BRATON = registerItem("braton", new HitscanWeaponItem(
            new Item.Properties(),
            new RangedWeaponStats(
                    List.of(new DamageComponent(DamageType.PUNCTURE, 18), new DamageComponent(DamageType.SLASH, 6)),
                    0.15F,      // status chance
                    0.15F,      // crit chance
                    2.0F,       // crit multiplier
                    0,          // punch through — reserved
                    45,         // magazine size
                    60,         // reload ticks (3s)
                    1,          // multishot
                    FireMode.AUTO,
                    false,      // hitscan
                    3          // fireRateTicks
            )
    ));

    public static void registerModItems() {
        TennoCraft.LOGGER.info("Registering items for " + TennoCraft.TENNOCRAFT);
    }
}