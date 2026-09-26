package name.tennocraft.mixin;

import name.tennocraft.registry.ModTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockRandomTickMixin {

    @Inject(method = "isRandomlyTicking", at = @At("HEAD"), cancellable = true)
    private void tennocraft$forceRandomTick(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(ModTags.INFESTATION_SOURCE)) {
            cir.setReturnValue(true);
        }
    }
}
