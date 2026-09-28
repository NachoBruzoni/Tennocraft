package name.tennocraft.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import name.tennocraft.TennoCraft;

public class ModSounds {
    public static final SoundEvent INFESTED_ZOMBIE_AMBIENT = register("infested_zombie_ambient");
    public static final SoundEvent INFESTED_ZOMBIE_HURT = register("infested_zombie_hurt");
    public static final SoundEvent INFESTED_ZOMBIE_DEATH = register("infested_zombie_death");

    public static final SoundEvent INFESTED_SKELETON_AMBIENT = register("infested_skeleton_ambient");
    public static final SoundEvent INFESTED_SKELETON_HURT = register("infested_skeleton_hurt");
    public static final SoundEvent INFESTED_SKELETON_DEATH = register("infested_skeleton_death");

    private static SoundEvent register(String path) {
        ResourceLocation id = new ResourceLocation(TennoCraft.TENNOCRAFT, path);
        return net.minecraft.core.Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerModSounds() {
        TennoCraft.LOGGER.info("Registering sounds for " + TennoCraft.TENNOCRAFT);
    }
}