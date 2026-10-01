package name.tennocraft.weapon;

import name.tennocraft.registry.DamageTypesRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class WeaponDamageHelper {

    public static void resolveHit(Player attacker, LivingEntity target, WeaponStats stats) {
        RandomSource random = attacker.getRandom();

        boolean crit = random.nextFloat() < stats.critChance;
        float damage = stats.totalDamage() * (crit ? stats.critMultiplier : 1.0F);

        DamageSource source = new DamageSource(
                attacker.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypesRegistry.WEAPON_ATTACK),
                attacker
        );
        target.hurt(source, damage);

        if (random.nextFloat() < stats.statusChance) {
            DamageType proc = pickWeightedDamageType(stats, random);
            if (proc != null) {
                StatusEffectHelper.applyStatus(target, proc.status);
            }
        }
    }

    private static DamageType pickWeightedDamageType(WeaponStats stats, RandomSource random) {
        float roll = random.nextFloat() * stats.totalDamage();
        float cumulative = 0;
        for (DamageComponent component : stats.damage) {
            cumulative += component.amount();
            if (roll < cumulative) return component.type();
        }
        return null;
    }
}
