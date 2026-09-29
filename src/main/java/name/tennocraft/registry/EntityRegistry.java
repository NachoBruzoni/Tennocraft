package name.tennocraft.registry;

import name.tennocraft.TennoCraft;
import name.tennocraft.entity.InfestedSkeletonEntity;
import name.tennocraft.entity.InfestedZombieEntity;
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

}
