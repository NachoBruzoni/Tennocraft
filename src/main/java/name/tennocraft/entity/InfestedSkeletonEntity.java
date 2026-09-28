package name.tennocraft.entity;

import name.tennocraft.registry.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class InfestedSkeletonEntity extends Zombie implements InfestedMob {

    public final InfestedSkeletonDispatcher dispatcher;

    public InfestedSkeletonEntity(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        this.dispatcher = new InfestedSkeletonDispatcher(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        InfestedMobBehavior.addAggroGoal(this, this.targetSelector);
    }

    @Override
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, (double)1.0F, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, (double)1.0F, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[]{ZombifiedPiglin.class}));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
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
    public boolean isUnderWaterConverting() {
        return false;
    }

    @Override
    public boolean isBaby() {
        return false;
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
        return ModSounds.INFESTED_SKELETON_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.INFESTED_SKELETON_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return ModSounds.INFESTED_SKELETON_DEATH;
    }
}
