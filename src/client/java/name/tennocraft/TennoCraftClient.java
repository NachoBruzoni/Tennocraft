package name.tennocraft;

import name.tennocraft.entity.InfestedZombieRenderer;
import name.tennocraft.entity.InfestedSkeletonRenderer;
import name.tennocraft.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TennoCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityRegistry.INFESTED_ZOMBIE, InfestedZombieRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.INFESTED_SKELETON, InfestedSkeletonRenderer::new);
	}
}