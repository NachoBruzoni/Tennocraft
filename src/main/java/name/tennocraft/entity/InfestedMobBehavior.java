package name.tennocraft.entity;

import name.tennocraft.registry.InfestationRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class InfestedMobBehavior {

    /** Call from registerGoals(), after super.registerGoals(). Makes the mob aggressive toward anything not already infested. */
    public static void addAggroGoal(Mob mob, GoalSelector targetSelector) {
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(mob, Mob.class, true,
                entity -> !(entity instanceof InfestedMob)));
    }

    /** Call every tick, server-side only. The existing "spread to nearby mobs" behavior, generalized. */
    public static void trySpreadToNearby(Mob infested, int chanceDenominator) {
        if (infested.getRandom().nextInt(chanceDenominator) != 0) return;

        AABB searchBox = infested.getBoundingBox().inflate(4.0);
        List<Mob> nearby = infested.level().getEntitiesOfClass(Mob.class, searchBox,
                entity -> !(entity instanceof InfestedMob)
                        && InfestationRegistry.getInfestedVariant(entity.getType()) != null);
        if (nearby.isEmpty()) return;

        Mob target = nearby.get(infested.getRandom().nextInt(nearby.size()));
        EntityType<? extends Mob> variant = InfestationRegistry.getInfestedVariant(target.getType());
        InfestationHelper.convertToInfested(target, variant);
    }

    /** Call from killedEntity(). 100% conversion of anything killed that has a known infested variant. */
    public static void onKill(LivingEntity victim) {
        if (victim instanceof InfestedMob) return;
        if (!(victim instanceof Mob victimMob)) return;

        EntityType<? extends Mob> variant = InfestationRegistry.getInfestedVariant(victimMob.getType());
        if (variant == null) return;

        InfestationHelper.convertToInfested(victimMob, variant);
    }
}