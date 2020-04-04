package io.github.franiscoder.tacocraft.init;

import io.github.franiscoder.tacocraft.TacoCraft;
import io.github.franiscoder.tacocraft.world.CornFieldFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModGen {

    private static final Feature<DefaultFeatureConfig> FEATURE = Registry.register(
            Registry.FEATURE,
            new Identifier(TacoCraft.MODID, "feature"),
            new CornFieldFeature(DefaultFeatureConfig::deserialize)
    );

    public static void register() {
        Biomes.PLAINS.addFeature(
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                new CornFieldFeature(DefaultFeatureConfig::deserialize)
                .configure(new DefaultFeatureConfig())
                .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                .configure(new ChanceDecoratorConfig(100)))
        );
        Biomes.SUNFLOWER_PLAINS.addFeature(
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                new CornFieldFeature(DefaultFeatureConfig::deserialize)
                        .configure(new DefaultFeatureConfig())
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                .configure(new ChanceDecoratorConfig(10)))
        );
        Biomes.SAVANNA.addFeature(
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                new CornFieldFeature(DefaultFeatureConfig::deserialize)
                        .configure(new DefaultFeatureConfig())
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                .configure(new ChanceDecoratorConfig(10)))
        );
    }


}
