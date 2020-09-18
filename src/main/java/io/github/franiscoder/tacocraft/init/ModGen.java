package io.github.franiscoder.tacocraft.init;

import io.github.franiscoder.tacocraft.TacoCraft;
import io.github.franiscoder.tacocraft.world.CornFieldFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModGen {
    public static final Feature<DefaultFeatureConfig> CORN_FEATURE = new CornFieldFeature();
    public static final ConfiguredFeature<?, ?> CONFIGURED_CORN_FEATURE = CORN_FEATURE
            .configure(new DefaultFeatureConfig())
            .decorate(Decorator.HEIGHTMAP.configure(new NopeDecoratorConfig()).applyChance(100));

    public static void register() {
        Registry.register(Registry.FEATURE, TacoCraft.id("feature"), CORN_FEATURE);
    }

    public static void putFeatures(Biome biome) {
        if (biome.getCategory() == Biome.Category.PLAINS) {
            biome.getGenerationSettings().getFeatures().get(GenerationStep.Feature.TOP_LAYER_MODIFICATION.ordinal())
                    .add(() -> CONFIGURED_CORN_FEATURE);
        }
    }


}
