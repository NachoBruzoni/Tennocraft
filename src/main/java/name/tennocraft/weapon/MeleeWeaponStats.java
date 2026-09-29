package name.tennocraft.weapon;

import java.util.List;

public class MeleeWeaponStats extends WeaponStats {
    public final int attackSpeedTicks;
    public final float range;
    public final float sweepRadiusDegrees;
    public final float heavyAttackMultiplier;
    public final int heavyWindupTicks;

    public MeleeWeaponStats(List<DamageComponent> damage, float statusChance, float critChance,
                            float critMultiplier, int punchThrough, int attackSpeedTicks, float range,
                            float sweepRadiusDegrees, float heavyAttackMultiplier, int heavyWindupTicks) {
        super(damage, statusChance, critChance, critMultiplier, punchThrough);
        this.attackSpeedTicks = attackSpeedTicks;
        this.range = range;
        this.sweepRadiusDegrees = sweepRadiusDegrees;
        this.heavyAttackMultiplier = heavyAttackMultiplier;
        this.heavyWindupTicks = heavyWindupTicks;
    }
}
