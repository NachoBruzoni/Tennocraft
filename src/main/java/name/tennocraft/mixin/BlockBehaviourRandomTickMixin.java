package name.tennocraft.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import name.tennocraft.entity.InfestationHelper;
import name.tennocraft.entity.InfestedZombieEntity;
import name.tennocraft.registry.ModTags;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourRandomTickMixin {

    @Inject(method = "randomTick", at = @At("HEAD"))
    private void tennocraft$onRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (!state.is(ModTags.INFESTATION_SOURCE)) return;
        if (random.nextInt(8192) != 0) return;

        ChunkPos chunkPos = new ChunkPos(pos);
        // Bounded to a reasonable vertical range around the block, rather than
        // the doc's literal "whole chunk column" (which could reach a zombie
        // 200 blocks away vertically) — easy to widen/narrow later.
        AABB searchBox = new AABB(
                chunkPos.getMinBlockX(), pos.getY() - 32, chunkPos.getMinBlockZ(),
                chunkPos.getMaxBlockX() + 1, pos.getY() + 32, chunkPos.getMaxBlockZ() + 1
        );

        List<Zombie> candidates = level.getEntitiesOfClass(Zombie.class, searchBox,
                z -> !(z instanceof InfestedZombieEntity));
        if (candidates.isEmpty()) return;

        Zombie target = candidates.get(random.nextInt(candidates.size()));
        InfestationHelper.convertToInfested(target);
    }
}