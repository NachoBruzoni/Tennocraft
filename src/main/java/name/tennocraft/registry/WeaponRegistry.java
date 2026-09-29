package name.tennocraft.registry;

import name.tennocraft.weapon.RangedWeaponStats;
import net.minecraft.resources.ResourceLocation;
import java.util.HashMap;
import java.util.Map;

public class WeaponRegistry {
    private static final Map<ResourceLocation, RangedWeaponStats> RANGED_STATS = new HashMap<>();

    public static void register(ResourceLocation weaponId, RangedWeaponStats stats) {
        RANGED_STATS.put(weaponId, stats);
    }

    public static RangedWeaponStats getStats(ResourceLocation weaponId) {
        return RANGED_STATS.get(weaponId);
    }

}