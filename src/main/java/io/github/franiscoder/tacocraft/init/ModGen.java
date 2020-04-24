package io.github.franiscoder.tacocraft.init;

import io.github.franiscoder.tacocraft.TacoCraft;
import io.github.franiscoder.tacocraft.world.CornFieldFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class ModGen {

    public static void register() {
        Registry.register(
                Registry.FEATURE,
                TacoCraft.id("feature"),
                new CornFieldFeature()
        );

        Biomes.PLAINS.addFeature(
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                new CornFieldFeature()
                        .configure(new DefaultFeatureConfig())
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                .configure(new ChanceDecoratorConfig(100)))
        );
        Biomes.SUNFLOWER_PLAINS.addFeature(
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                new CornFieldFeature()
                        .configure(new DefaultFeatureConfig())
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                .configure(new ChanceDecoratorConfig(200)))
        );
        Biomes.SAVANNA.addFeature(
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                new CornFieldFeature()
                        .configure(new DefaultFeatureConfig())
                        .createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP
                                .configure(new ChanceDecoratorConfig(200)))
        );
    }


}
