package name.tennocraft.weapon;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class MeleeWeaponItem extends Item implements TennoWeaponItem {
    public final MeleeWeaponStats stats;

    public MeleeWeaponItem(Properties properties, MeleeWeaponStats stats) {
        super(properties);
        this.stats = stats;
    }

    /** Called once per windup completion. Finds everything in range within the sweep arc and hits it. */
    public void performAttack(ServerPlayer player) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 lookDir = player.getViewVector(1.0F).normalize();
        double halfArcRadians = Math.toRadians(stats.sweepRadiusDegrees / 2.0); // treating the stat as the FULL arc width — flag below

        AABB searchBox = player.getBoundingBox().inflate(stats.range);
        List<LivingEntity> candidates = player.level().getEntitiesOfClass(LivingEntity.class, searchBox,
                entity -> entity != player && entity.isAlive());

        for (LivingEntity target : candidates) {
            Vec3 toTarget = target.position().subtract(eyePos);
            if (toTarget.length() > stats.range) continue;

            double angle = Math.acos(lookDir.normalize().dot(toTarget.normalize()));
            if (angle > halfArcRadians) continue;

            WeaponDamageHelper.resolveHit(player, target, stats);
        }

        onStrike(player); // hook for the animation trigger — see below
    }

    /** Fires the instant the hit actually resolves — this is where a strike animation should trigger. */
    protected void onStrike(ServerPlayer player) {
        // combo counter increment goes here too, once that system exists
    }
}