package name.tennocraft.registry;

import name.tennocraft.TennoCraft;
import name.tennocraft.entity.InfestedSkeletonEntity;
import name.tennocraft.entity.InfestedZombieEntity;
import name.tennocraft.weapon.TennoArrowProjectile;
import name.tennocraft.weapon.TennoThrownProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;

public class EntityRegistry {

    public static final EntityType<InfestedZombieEntity> INFESTED_ZOMBIE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(TennoCraft.TENNOCRAFT, "infested_zombie"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, InfestedZombieEntity::new)
                    .dimensions(EntityDimensions.scalable(1.6F, 1.3F))
                    .build()

    );
    public static final EntityType<InfestedSkeletonEntity> INFESTED_SKELETON = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(TennoCraft.TENNOCRAFT, "infested_skeleton"),
            FabricEntityTypeBuilder.create(MobCategory.MONSTER, InfestedSkeletonEntity::new)
                    .dimensions(EntityDimensions.scalable(1.70F, 0.8F))
                    .build()
    );

    public static void registerModEntityTypes() {
        TennoCraft.LOGGER.info("Registering entity types for " + TennoCraft.TENNOCRAFT);
        FabricDefaultAttributeRegistry.register(INFESTED_ZOMBIE,
                Zombie.createAttributes()
                        .add(Attributes.MOVEMENT_SPEED, 0.28)
                        .add(Attributes.ARMOR, 0.0)
                        .build());
        FabricDefaultAttributeRegistry.register(INFESTED_SKELETON, Zombie.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.18).build());

        InfestationRegistry.register(EntityType.ZOMBIE, INFESTED_ZOMBIE);
        InfestationRegistry.register(EntityType.SKELETON, INFESTED_SKELETON);
    }

    public static final EntityType<TennoThrownProjectile> TENNO_THROWN_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(TennoCraft.TENNOCRAFT, "tenno_thrown_projectile"),
            FabricEntityTypeBuilder.<TennoThrownProjectile>create(MobCategory.MISC, TennoThrownProjectile::new)
                    .dimensions(EntityDimensions.scalable(0.25F, 0.25F)).build());

    public static final EntityType<TennoArrowProjectile> TENNO_ARROW_PROJECTILE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(TennoCraft.TENNOCRAFT, "tenno_arrow_projectile"),
            FabricEntityTypeBuilder.<TennoArrowProjectile>create(MobCategory.MISC, TennoArrowProjectile::new)
                    .dimensions(EntityDimensions.scalable(0.5F, 0.5F)).build());

}
