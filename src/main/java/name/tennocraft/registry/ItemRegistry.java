package name.tennocraft.registry;

import name.tennocraft.TennoCraft;
import name.tennocraft.weapon.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

import static name.tennocraft.registry.ModSounds.*;

public class ItemRegistry {

    public static final Item MUTAGEN_SAMPLE = registerItem(
            "mutagen_sample",
            new Item(new Item.Properties()));


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
                    3,          // fireRateTicks
                    BRATON_FIRE
            )
    ));

    public static final Item PARIS = registerItem("paris", new ProjectileWeaponItem(
            new Item.Properties(),
            new RangedWeaponStats(
                    List.of(new DamageComponent(DamageType.PUNCTURE, 60), new DamageComponent(DamageType.SLASH, 20)),
                    0.20F,      // status chance
                    0.25F,      // crit chance
                    2.0F,       // crit multiplier
                    0,          // punch through — reserved
                    1,          // magazine size (bow-style, one arrow "loaded" per shot)
                    20,         // reload ticks (1s) — placeholder, yours to tune
                    1,          // multishot
                    FireMode.CHARGE,
                    true,       // projectile
                    60,          // fireRateTicks → for CHARGE, this is read as "ticks to reach full charge"
                    PARIS_FIRE
            ),
            (level, player) -> new TennoArrowProjectile(level, player, new ResourceLocation(TennoCraft.TENNOCRAFT, "paris"))
    ));

    static {
        WeaponRegistry.register(new ResourceLocation(TennoCraft.TENNOCRAFT, "paris"), ((ProjectileWeaponItem) PARIS).stats);
    }

    private static Item registerItem(String path, Item item) {
        ResourceLocation id = new ResourceLocation(TennoCraft.TENNOCRAFT, path);
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void registerModItems() {
        TennoCraft.LOGGER.info("Registering items for " + TennoCraft.TENNOCRAFT);
    }
}