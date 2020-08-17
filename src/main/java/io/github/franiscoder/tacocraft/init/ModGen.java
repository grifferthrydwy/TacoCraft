package io.github.franiscoder.tacocraft.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.github.franiscoder.tacocraft.TacoCraft;
import io.github.franiscoder.tacocraft.world.CornFieldFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.BuiltinBiomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModGen {

    public static void register() {

        CornFieldFeature cornFeature = Registry.register(
                Registry.FEATURE,
                TacoCraft.id("feature"),
                new CornFieldFeature()
        );

        ConfiguredFeature<?, ?> configuredFeature = cornFeature
                                                .configure(new DefaultFeatureConfig())
                                                .decorate(Decorator.HEIGHTMAP
                                                        .configure(new NopeDecoratorConfig()).applyChance(100));

        addCorn(configuredFeature, BuiltinBiomes.PLAINS);
        addCorn(configuredFeature, BuiltinRegistries.BIOME.get(BiomeKeys.SUNFLOWER_PLAINS));
        addCorn(configuredFeature, BuiltinRegistries.BIOME.get(BiomeKeys.SAVANNA));
    }

    private static void addCorn(ConfiguredFeature<?, ?> configuredFeature, Biome biome) {
        addFeature(biome,TacoCraft.id("feature"), configuredFeature);
    }

    private static void addFeature(Biome biome, Identifier identifier, ConfiguredFeature<?, ?> configuredFeature) {
        List<List<Supplier<ConfiguredFeature<?, ?>>>> features = biome.getGenerationSettings().getFeatures();

        int stepIndex = GenerationStep.Feature.TOP_LAYER_MODIFICATION.ordinal();

        int size = features.size();
        while (features.size() <= stepIndex) {
            features.add(Lists.newArrayList());
        }

        List<Supplier<ConfiguredFeature<?, ?>>> stepList = features.get(GenerationStep.Feature.TOP_LAYER_MODIFICATION.ordinal());
        if (stepList instanceof ImmutableList) {
            features.set(GenerationStep.Feature.TOP_LAYER_MODIFICATION.ordinal(), stepList = new ArrayList<>(stepList));
        }

        if (!BuiltinRegistries.CONFIGURED_FEATURE.getKey(configuredFeature).isPresent()) {
            if (BuiltinRegistries.CONFIGURED_FEATURE.getOrEmpty(identifier).isPresent()) {
                ConfiguredFeature<?,?> b = BuiltinRegistries.CONFIGURED_FEATURE.getOrEmpty(identifier).get();
                throw new RuntimeException("Duplicate feature: " + identifier.toString());
            }

            BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, identifier, configuredFeature);
        }

        stepList.add(() -> configuredFeature);
    }
}
