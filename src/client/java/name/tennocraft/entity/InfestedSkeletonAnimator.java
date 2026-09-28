package name.tennocraft.entity;

import mod.azure.azurelib.animation.controller.AzAnimationController;
import mod.azure.azurelib.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.animation.impl.AzEntityAnimator;
import name.tennocraft.TennoCraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class InfestedSkeletonAnimator extends AzEntityAnimator<InfestedSkeletonEntity> {

    private static final ResourceLocation ANIMATIONS =
            new ResourceLocation(TennoCraft.TENNOCRAFT, "animations/entity/infested_skeleton.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<InfestedSkeletonEntity> controllers) {
        controllers.add(AzAnimationController.builder(this, "base_controller").build());
    }
    @Override
    public @NotNull ResourceLocation getAnimationLocation(InfestedSkeletonEntity animatable) {
        return ANIMATIONS;
    }
}
