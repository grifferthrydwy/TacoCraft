package io.github.frqnny.tacocraft.init;

import draylar.structurized.api.StructurePoolAddCallback;
import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.world.CornFieldFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModGen {
    public static final Feature<DefaultFeatureConfig> CORN_FEATURE = new CornFieldFeature();
    public static final ConfiguredFeature<?, ?> CONFIGURED_CORN_FEATURE = CORN_FEATURE
            .configure(new DefaultFeatureConfig())
            .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(100)));

    public static void init() {
        Registry.register(Registry.FEATURE, TacoCraft.id("feature"), CORN_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, TacoCraft.id("feature_configured"), CONFIGURED_CORN_FEATURE);

        BiomeModifications
                .create(TacoCraft.id("feature"))
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.SAVANNA),
                        (biomeModificationContext -> biomeModificationContext.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, CONFIGURED_CORN_FEATURE))
                );

        StructurePoolAddCallback.EVENT.register(structurePool -> {
            if (structurePool.getStructurePool().getId().toString().contains("minecraft:village/plains/houses")) {
                structurePool.addStructurePoolElement(StructurePoolElement.ofProcessedLegacySingle("tacocraft:plains_corn_farm", StructureProcessorLists.FARM_PLAINS).apply(StructurePool.Projection.RIGID));
            }
        });
    }
}
