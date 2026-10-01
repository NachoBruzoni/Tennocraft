package name.tennocraft.weapon;

import net.minecraft.sounds.SoundEvent;

import java.util.List;

public class RangedWeaponStats extends WeaponStats {
    public final int magazineSize;
    public final int reloadTimeTicks;
    public final int multishot;
    public final FireMode fireMode;
    public final boolean projectile; // false = hitscan
    public final int fireRateTicks;  // added — see note above
    public final SoundEvent fireSound;

    public RangedWeaponStats(List<DamageComponent> damage, float statusChance, float critChance,
                             float critMultiplier, int punchThrough, int magazineSize, int reloadTimeTicks,
                             int multishot, FireMode fireMode, boolean projectile,
                             int fireRateTicks, SoundEvent fireSound) {
        super(damage, statusChance, critChance, critMultiplier, punchThrough);
        this.magazineSize = magazineSize;
        this.reloadTimeTicks = reloadTimeTicks;
        this.multishot = multishot;
        this.fireMode = fireMode;
        this.projectile = projectile;
        this.fireRateTicks = fireRateTicks;
        this.fireSound = fireSound;
    }
}
