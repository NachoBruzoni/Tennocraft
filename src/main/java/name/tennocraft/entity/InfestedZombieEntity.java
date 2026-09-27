package name.tennocraft.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

import name.tennocraft.registry.ModSounds; // built below

public class InfestedZombieEntity extends Zombie implements InfestedMob {

    public final InfestedZombieDispatcher dispatcher;

    public InfestedZombieEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        this.dispatcher = new InfestedZombieDispatcher(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        InfestedMobBehavior.addAggroGoal(this, this.targetSelector);
    }

    @Override
    public boolean isSunSensitive() {
        return false;
    }

    @Override
    public boolean killedEntity(ServerLevel level, LivingEntity entity) {
        boolean result = super.killedEntity(level, entity);
        InfestedMobBehavior.onKill(entity);
        return result;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            dispatcher.idle();
        } else {
            InfestedMobBehavior.trySpreadToNearby(this, 2048);
        }
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return ModSounds.INFESTED_ZOMBIE_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.INFESTED_ZOMBIE_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return ModSounds.INFESTED_ZOMBIE_DEATH;
    }
}