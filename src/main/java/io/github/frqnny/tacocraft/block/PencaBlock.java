package io.github.frqnny.tacocraft.block;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.blockentity.PencaBlockEntity;
import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PencaBlock extends BlockWithEntity {
    public static final Identifier ID = TacoCraft.id("penca");
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 0.3D, 14.0D);


    public PencaBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!(blockEntity instanceof PencaBlockEntity)) {
                return ActionResult.FAIL;
            }
            PencaBlockEntity pencaBe = ((PencaBlockEntity) blockEntity);


            ItemStack stack = player.getStackInHand(hand);
            Item item = stack.getItem();
            if (pencaBe.porkchops + pencaBe.steaks < 4) {
                if (Items.COOKED_BEEF.equals(item)) {
                    stack.decrement(1);
                    pencaBe.steaks++;

                } else if (Items.COOKED_PORKCHOP.equals(item)) {
                    stack.decrement(1);
                    pencaBe.porkchops++;

                }

                pencaBe.sync();
                return ActionResult.SUCCESS;
            } else if (Items.AIR.equals(item) && pencaBe.porkchops + pencaBe.steaks >= 4) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.CLOSED_PENCA).copy());

                return ActionResult.SUCCESS;
            }


            return ActionResult.FAIL;
        }
        return ActionResult.FAIL;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new PencaBlockEntity();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
