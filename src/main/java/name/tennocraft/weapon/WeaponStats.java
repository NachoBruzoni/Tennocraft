package name.tennocraft.weapon;

import java.util.List;

public class WeaponStats {
    public final List<DamageComponent> damage;
    public final float statusChance;
    public final float critChance;
    public final float critMultiplier;
    public final int punchThrough; // reserved — not implemented yet, per your go-ahead to skip it

    public WeaponStats(List<DamageComponent> damage, float statusChance, float critChance,
                       float critMultiplier, int punchThrough) {
        this.damage = damage;
        this.statusChance = statusChance;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.punchThrough = punchThrough;
    }

    public float totalDamage() {
        float sum = 0;
        for (DamageComponent c : damage) sum += c.amount();
        return sum;
    }
}
