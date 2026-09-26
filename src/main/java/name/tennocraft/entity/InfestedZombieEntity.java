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
        else {
            trySpreadInfestation();
        }
    }
    private void trySpreadInfestation() {
        if (this.random.nextInt(2048) != 0) return;

        var searchBox = this.getBoundingBox().inflate(4.0);
        var nearby = this.level().getEntitiesOfClass(net.minecraft.world.entity.monster.Zombie.class, searchBox,
                z -> !(z instanceof InfestedZombieEntity));
        if (nearby.isEmpty()) return;

        var target = nearby.get(this.random.nextInt(nearby.size()));
        InfestationHelper.convertToInfested(target);
    }

}
