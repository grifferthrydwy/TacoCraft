package io.github.franiscoder.tacocraft.world;

import io.github.franiscoder.tacocraft.block.crop.CornBlock;
import io.github.franiscoder.tacocraft.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CornFieldFeature extends Feature<DefaultFeatureConfig> {
    public CornFieldFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        BlockPos topPos1 = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        world.setBlockState(topPos1.down(), Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7), 3);
        world.setBlockState(topPos1, ModBlocks.CORN_BLOCK.getDefaultState().with(CornBlock.AGE, 7).with(CornBlock.HALF, DoubleBlockHalf.LOWER), 3);
        world.setBlockState(topPos1.up(), ModBlocks.CORN_BLOCK.getDefaultState().with(CornBlock.AGE, 7).with(CornBlock.HALF, DoubleBlockHalf.UPPER), 3);
        return true;
    }
}
