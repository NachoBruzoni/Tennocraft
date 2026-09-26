package name.tennocraft.registry;

import name.tennocraft.TennoCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.Registries;

public class ModTags {
    public static final TagKey<Block> INFESTATION_SOURCE =
            TagKey.create(Registries.BLOCK, new ResourceLocation(TennoCraft.TENNOCRAFT, "infestation_source"));
}
