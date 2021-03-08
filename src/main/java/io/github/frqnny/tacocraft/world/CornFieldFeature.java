package io.github.frqnny.tacocraft.world;

import io.github.frqnny.tacocraft.block.crop.CornBlock;
import io.github.frqnny.tacocraft.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CornFieldFeature extends Feature<DefaultFeatureConfig> {
    public CornFieldFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig featureConfig) {
        BlockPos topPos1 = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, blockPos);
        world.setBlockState(topPos1.down(), Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7), 3);
        world.setBlockState(topPos1, ModBlocks.CORN_BLOCK.getDefaultState().with(CornBlock.AGE, 7).with(CornBlock.HALF, DoubleBlockHalf.LOWER), 3);
        world.setBlockState(topPos1.up(), ModBlocks.CORN_BLOCK.getDefaultState().with(CornBlock.AGE, 7).with(CornBlock.HALF, DoubleBlockHalf.UPPER), 3);
        return true;
    }
}
