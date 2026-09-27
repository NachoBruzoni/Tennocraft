package name.tennocraft.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class InfestationHelper {

    public static <T extends Mob> T convertToInfested(Mob source, EntityType<T> targetType) {
        Level level = source.level();
        T infested = targetType.create(level);
        if (infested == null) return null;

        infested.moveTo(source.getX(), source.getY(), source.getZ(), source.getYRot(), source.getXRot());
        infested.setHealth(infested.getMaxHealth());

        level.addFreshEntity(infested);
        source.discard();

        return infested;
    }
}