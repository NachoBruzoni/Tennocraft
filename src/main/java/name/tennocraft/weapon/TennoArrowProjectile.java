package name.tennocraft.weapon;

import name.tennocraft.registry.WeaponRegistry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import name.tennocraft.registry.EntityRegistry;

public class TennoArrowProjectile extends AbstractArrow {
    private static final EntityDataAccessor<String> WEAPON_ID =
            SynchedEntityData.defineId(TennoArrowProjectile.class, EntityDataSerializers.STRING);

    public TennoArrowProjectile(EntityType<? extends TennoArrowProjectile> type, Level level) {
        super(type, level);
    }

    public TennoArrowProjectile(Level level, Player owner, ResourceLocation weaponId) {
        super(EntityRegistry.TENNO_ARROW_PROJECTILE, owner, level, ItemStack.EMPTY, null);
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
    protected void onHitEntity(EntityHitResult result) {
        if (result.getEntity() instanceof LivingEntity target && getOwner() instanceof Player owner) {
            RangedWeaponStats stats = WeaponRegistry.getStats(getWeaponId());
            if (stats != null) {
                WeaponDamageHelper.resolveHit(owner, target, stats);
            }
        }
        this.discard();
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}