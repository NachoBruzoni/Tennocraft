package name.tennocraft.weapon;

import name.tennocraft.registry.WeaponRegistry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import name.tennocraft.registry.EntityRegistry;

public class TennoThrownProjectile extends ThrowableItemProjectile {
    private static final EntityDataAccessor<String> WEAPON_ID =
            SynchedEntityData.defineId(TennoThrownProjectile.class, EntityDataSerializers.STRING);

    public TennoThrownProjectile(EntityType<? extends TennoThrownProjectile> type, Level level) {
        super(type, level);
    }

    public TennoThrownProjectile(Level level, Player owner, ResourceLocation weaponId) {
        super(EntityRegistry.TENNO_THROWN_PROJECTILE, owner, level);
        this.entityData.set(WEAPON_ID, weaponId.toString());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WEAPON_ID, "");
    }

    public ResourceLocation getWeaponId() {
        return ResourceLocation.tryParse(this.entityData.get(WEAPON_ID));
    }

    @Override
    protected Item getDefaultItem() {
        return Items.IRON_NUGGET; // placeholder — see rendering section below for the real approach
    }

    @Override
    protected void onHit(HitResult result) {
        if (result instanceof EntityHitResult entityHit
                && entityHit.getEntity() instanceof LivingEntity target
                && getOwner() instanceof Player owner) {
            RangedWeaponStats stats = WeaponRegistry.getStats(getWeaponId());
            if (stats != null) {
                WeaponDamageHelper.resolveHit(owner, target, stats);
            }
        }
        if (!this.level().isClientSide) {
            this.discard();
        }
    }
}