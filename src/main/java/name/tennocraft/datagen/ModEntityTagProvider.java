package name.tennocraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

import name.tennocraft.registry.EntityRegistry;
import name.tennocraft.registry.ModTags;

public class ModEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public ModEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(ModTags.INFESTED)
                .add(EntityRegistry.INFESTED_ZOMBIE);
    }
}