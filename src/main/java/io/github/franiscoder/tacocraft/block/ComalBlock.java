package io.github.franiscoder.tacocraft.block;

import io.github.franiscoder.tacocraft.TacoCraft;
import io.github.franiscoder.tacocraft.block.entity.ComalBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import javax.annotation.Nullable;

public class ComalBlock extends BlockWithEntity {
    public static final Identifier ID = new Identifier(TacoCraft.MODID, "comal");

    public ComalBlock(Settings settings) {
        super(settings.nonOpaque());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new ComalBlockEntity();
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        return Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D);
    }
}
