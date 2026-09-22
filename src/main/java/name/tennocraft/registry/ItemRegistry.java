package name.tennocraft.registry;

import name.tennocraft.TennoCraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static net.minecraft.world.item.Items.registerItem;


public class ItemRegistry {

    public static final Item MUTAGEN_SAMPLE = registerItem("mutagen_sample",
            new Item(new Item.Properties()));

    private static Item RegisterItem(String path, Item item) {
        ResourceLocation id = new ResourceLocation(TennoCraft.TENNOCRAFT, path);
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void registerModItems() {
        TennoCraft.LOGGER.info("Registering items for " + TennoCraft.TENNOCRAFT);
    }
}
