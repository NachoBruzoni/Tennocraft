package name.tennocraft.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

import java.util.HashMap;
import java.util.Map;

public class InfestationRegistry {

    private static final Map<EntityType<?>, EntityType<? extends Mob>> VARIANTS = new HashMap<>();

    public static void register(EntityType<?> vanillaType, EntityType<? extends Mob> infestedType) {
        VARIANTS.put(vanillaType, infestedType);
    }

    public static EntityType<? extends Mob> getInfestedVariant(EntityType<?> vanillaType) {
        return VARIANTS.get(vanillaType);
    }
}
