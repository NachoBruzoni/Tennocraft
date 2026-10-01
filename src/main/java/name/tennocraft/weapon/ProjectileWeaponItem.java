package name.tennocraft.weapon;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.BiFunction;

public class ProjectileWeaponItem extends RangedWeaponItem {
    private final BiFunction<Level, Player, ?> projectileFactory;

    public <T extends net.minecraft.world.entity.projectile.Projectile> ProjectileWeaponItem(
            Properties properties, RangedWeaponStats stats, BiFunction<Level, Player, T> projectileFactory) {
        super(properties, stats);
        this.projectileFactory = projectileFactory;
    }

    @Override
    protected void fire(Level level, Player player, ItemStack stack) {
        for (int i = 0; i < stats.multishot; i++) {
            net.minecraft.world.entity.projectile.Projectile projectile =
                    (net.minecraft.world.entity.projectile.Projectile) projectileFactory.apply(level, player);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
            level.addFreshEntity(projectile);
            playFireSound(level, player, stats.fireSound);
        }
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, player.getSoundSource(), 1.0F, 1.0F);
    }
}
