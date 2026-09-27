package name.tennocraft;

import name.tennocraft.datagen.ModEntityTagProvider;
import name.tennocraft.datagen.ModLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TennoCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModEntityTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
	}
}