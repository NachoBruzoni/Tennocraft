package name.tennocraft.weapon;

import name.tennocraft.registry.ModSounds;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class HitscanWeaponItem extends RangedWeaponItem {
    private static final double RANGE = 40.0;

    public HitscanWeaponItem(Properties properties, RangedWeaponStats stats) {
        super(properties, stats);
    }

    @Override
    protected void fire(Level level, Player player, ItemStack stack) {
        Vec3 start = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 end = start.add(look.scale(RANGE));
        playFireSound(level, player, stats.fireSound);

        HitResult blockHit = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        double actualRange = blockHit.getType() != HitResult.Type.MISS ? start.distanceTo(blockHit.getLocation()) : RANGE;
        Vec3 actualEnd = start.add(look.scale(actualRange));

        AABB searchBox = player.getBoundingBox().expandTowards(look.scale(actualRange)).inflate(1.0);
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(level, player, start, actualEnd, searchBox,
                target -> target != player && target.isPickable() && !target.isSpectator());

        if (entityHit != null && entityHit.getEntity() instanceof LivingEntity target) {
            for (int i = 0; i < stats.multishot; i++) {
                WeaponDamageHelper.resolveHit(player, target, stats);
            }
        }
    }
}