package name.tennocraft.weapon;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class WeaponDamageHelper {

    public static void resolveHit(Player attacker, LivingEntity target, WeaponStats stats) {
        RandomSource random = attacker.getRandom();

        boolean crit = random.nextFloat() < stats.critChance;
        float damage = stats.totalDamage() * (crit ? stats.critMultiplier : 1.0F);

        target.hurt(attacker.level().damageSources().playerAttack(attacker), damage);

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
