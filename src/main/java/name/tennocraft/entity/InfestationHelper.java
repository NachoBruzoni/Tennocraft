package name.tennocraft.entity;

import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

import name.tennocraft.registry.EntityRegistry;

public class InfestationHelper {

    public static InfestedZombieEntity convertToInfested(Zombie zombie) {
        Level level = zombie.level();
        InfestedZombieEntity infested = EntityRegistry.INFESTED_ZOMBIE.create(level);
        if (infested == null) return null;

        infested.moveTo(zombie.getX(), zombie.getY(), zombie.getZ(), zombie.getYRot(), zombie.getXRot());
        infested.setHealth(infested.getMaxHealth());

        level.addFreshEntity(infested);
        zombie.discard();

        return infested;
    }
}