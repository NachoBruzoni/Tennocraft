package name.tennocraft.entity;

import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import name.tennocraft.TennoCraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class InfestedSkeletonRenderer extends AzEntityRenderer<InfestedSkeletonEntity>{

    private static final ResourceLocation GEO =
            new ResourceLocation(TennoCraft.TENNOCRAFT, "geo/entity/infested_skeleton.geo.json");
    private static final ResourceLocation TEX =
            new ResourceLocation(TennoCraft.TENNOCRAFT, "textures/entity/infested_skeleton.png");

    public InfestedSkeletonRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<InfestedSkeletonEntity>builder(GEO, TEX)
                        .setAnimatorProvider(InfestedSkeletonAnimator::new)
                        .build(),
                context
        );
    }
}