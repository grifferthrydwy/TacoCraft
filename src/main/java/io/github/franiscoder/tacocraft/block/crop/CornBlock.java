package io.github.franiscoder.tacocraft.block.crop;

import io.github.franiscoder.tacocraft.TacoCraft;
import io.github.franiscoder.tacocraft.init.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class CornBlock extends TallPlantBlock implements Fertilizable {
    //Thanks To Yog, the CornBlock wouldn't be possible
    public static final Identifier ID = TacoCraft.id("corn_block");

    public static final IntProperty AGE;
    public static final EnumProperty<DoubleBlockHalf> HALF;
    public static int growthDelay;

    static {
        AGE = Properties.AGE_7;
        HALF = Properties.DOUBLE_BLOCK_HALF;
    }

    public CornBlock(int delay, Settings s) {
        super(s);
        growthDelay = delay;
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(HALF, DoubleBlockHalf.LOWER));
    }

    protected static int getAge(BlockState state) {
        return state.get(AGE);
    }

    public static boolean isMature(BlockState state) {
        return state.get(AGE) >= 7;
    }

    protected static int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 2, 5);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView view, BlockPos pos) {
        return floor.getBlock() == Blocks.FARMLAND;
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(AGE, age);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int age = state.get(AGE);
            if (state.get(HALF).equals(DoubleBlockHalf.UPPER)) {
                if (age < 7 && world.random.nextInt(growthDelay) == 0) {
                    world.setBlockState(pos, this.withAge(age + 1).with(HALF, DoubleBlockHalf.UPPER), 2);
                    world.setBlockState(pos.down(), this.withAge(age + 1).with(HALF, DoubleBlockHalf.LOWER), 2);
                }
            } else {
                if (age < 7 && world.random.nextInt(growthDelay) == 0) {
                    world.setBlockState(pos.up(), this.withAge(age + 1).with(HALF, DoubleBlockHalf.UPPER), 2);
                    world.setBlockState(pos, this.withAge(age + 1).with(HALF, DoubleBlockHalf.LOWER), 2);
                }
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(AGE).equals(7)) {
            int count = world.random.nextInt(3) + 1;
            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.CORN, count));
            if (state.get(HALF).equals(DoubleBlockHalf.UPPER)) {
                world.setBlockState(pos, this.withAge(6).with(HALF, DoubleBlockHalf.UPPER));
                world.setBlockState(pos.down(), this.withAge(6).with(HALF, DoubleBlockHalf.LOWER));
            } else {
                world.setBlockState(pos.up(), this.withAge(6).with(HALF, DoubleBlockHalf.UPPER));
                world.setBlockState(pos, this.withAge(6).with(HALF, DoubleBlockHalf.LOWER));
            }
        }
        return ActionResult.PASS;
    }

    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int age = state.get(AGE);
        int i = CornBlock.getAge(state) + CornBlock.getGrowthAmount(world);
        int j = 7;
        if (i > j) {
            i = j;
        }

        if (state.get(HALF).equals(DoubleBlockHalf.UPPER)) {
            if (age < 7 && world.random.nextInt(growthDelay) == 0) {
                world.setBlockState(pos, this.withAge(i).with(HALF, DoubleBlockHalf.UPPER));
                world.setBlockState(pos.down(), this.withAge(i).with(HALF, DoubleBlockHalf.LOWER));
            }
        } else {
            if (age < 7 && world.random.nextInt(growthDelay) == 0) {
                world.setBlockState(pos.up(), this.withAge(i).with(HALF, DoubleBlockHalf.UPPER));
                world.setBlockState(pos, this.withAge(i).with(HALF, DoubleBlockHalf.LOWER));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.CORN_SEED);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return !CornBlock.isMature(state);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        builder.add(AGE);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}