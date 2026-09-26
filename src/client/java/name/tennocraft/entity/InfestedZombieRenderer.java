package name.tennocraft.entity;

import mod.azure.azurelib.render.entity.AzEntityRenderer;
import mod.azure.azurelib.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import name.tennocraft.TennoCraft;

public class InfestedZombieRenderer extends AzEntityRenderer<InfestedZombieEntity>{

    private static final ResourceLocation GEO =
            new ResourceLocation(TennoCraft.TENNOCRAFT, "geo/entity/infested_zombie.geo.json");
    private static final ResourceLocation TEX =
            new ResourceLocation(TennoCraft.TENNOCRAFT, "textures/entity/infested_zombie.png");

    public InfestedZombieRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<InfestedZombieEntity>builder(GEO, TEX)
                        .setAnimatorProvider(InfestedZombieAnimator::new)
                        .build(),
                context
        );
    }
}