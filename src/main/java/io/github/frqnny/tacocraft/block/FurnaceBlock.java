package io.github.frqnny.tacocraft.block;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.blockentity.FurnaceBlockEntity;
import io.github.frqnny.tacocraft.init.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FurnaceBlock extends AbstractFurnaceBlock {

    public static final Identifier ID = TacoCraft.id("furnace");

    public FurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof FurnaceBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory) be);
        }
    }


    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double d = (double) pos.getX() + 0.5D;
            double e = pos.getY();
            double f = (double) pos.getZ() + 0.5D;
            if (random.nextDouble() < 0.1D) {
                world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();

            double h = random.nextDouble() * 0.6D - 0.3D;
            double i = axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.52D : h;
            double j = random.nextDouble() * 6.0D / 16.0D;
            double k = axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.52D : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SMOKE, d, e + 1.0D, f, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d, e + 1.0D, f, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlocks.FURNACE_BLOCK_ENTITY, FurnaceBlockEntity::tick);
    }
}
