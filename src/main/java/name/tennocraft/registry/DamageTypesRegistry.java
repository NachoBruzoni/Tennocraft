package name.tennocraft.registry;

import name.tennocraft.TennoCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypesRegistry {
    public static final ResourceKey<DamageType> WEAPON_ATTACK =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(TennoCraft.TENNOCRAFT, "weapon_attack"));
}
