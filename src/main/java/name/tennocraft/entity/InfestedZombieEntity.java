package name.tennocraft.entity;

import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class InfestedZombieEntity extends Zombie {

    public final InfestedZombieDispatcher dispatcher;

    public InfestedZombieEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        this.dispatcher = new InfestedZombieDispatcher(this);
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            dispatcher.idle();
        }
    }

}
