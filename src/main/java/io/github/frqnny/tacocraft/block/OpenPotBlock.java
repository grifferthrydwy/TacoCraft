package io.github.frqnny.tacocraft.block;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.blockentity.OpenPotBlockEntity;
import io.github.frqnny.tacocraft.init.ModItems;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class OpenPotBlock extends BlockWithEntity {
    public static final Identifier ID = TacoCraft.id("open_pot");

    public OpenPotBlock(Settings settings) {
        super(settings);
    }

    public static boolean isSetupReady(World world, BlockPos pos) {
        Set<BlockPos> posToScan = new ObjectOpenHashSet<>(18);
        posToScan.add(pos.add(1, 0, 1));
        posToScan.add(pos.add(1, 0, 0));
        posToScan.add(pos.add(1, 0, -1));
        posToScan.add(pos.add(0, 0, 1));
        posToScan.add(pos.add(0, 0, -1));
        posToScan.add(pos.add(-1, 0, 1));
        posToScan.add(pos.add(-1, 0, 0));
        posToScan.add(pos.add(-1, 0, -1));

        posToScan.add(pos.add(1, -1, 1));
        posToScan.add(pos.add(1, -1, 0));
        posToScan.add(pos.add(1, -1, -1));
        posToScan.add(pos.add(0, -1, 1));
        posToScan.add(pos.add(0, -1, -1));
        posToScan.add(pos.add(-1, -1, 1));
        posToScan.add(pos.add(-1, -1, 0));
        posToScan.add(pos.add(-1, -1, -1));


        boolean isCobblestone = true;
        for (BlockPos blockPos : posToScan) {
            if (!world.getBlockState(blockPos).isOf(Blocks.COBBLESTONE)) {
                isCobblestone = false;
            }
        }

        return isCobblestone && world.getBlockState(pos.down()).isIn(BlockTags.CAMPFIRES);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new OpenPotBlockEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient) {
            if (!(world.getBlockEntity(pos) instanceof OpenPotBlockEntity)) {
                return ActionResult.FAIL;
            }
            OpenPotBlockEntity be = ((OpenPotBlockEntity) world.getBlockEntity(pos));
            if (isSetupReady(world, pos) && be.closed_pancas == 2 && player.getStackInHand(hand).isEmpty() && !be.finished) {
                be.letsgetreadybois();
                be.sync();
                return ActionResult.SUCCESS;
            } else if (be.closed_pancas < 2 && !be.finished) {
                ItemStack stack = player.getStackInHand(hand);
                Item item = stack.getItem();
                if (item.equals(ModItems.CLOSED_PENCA)) {
                    stack.decrement(1);
                    be.closed_pancas++;
                    be.sync();
                    return ActionResult.SUCCESS;
                }
            } else if (be.finished) {
                be.closed_pancas = 0;
                be.finished = false;
                be.sync();
                ItemStack stack = new ItemStack(ModItems.COOKED_PENCA);
                stack.setCount(2);
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            }

        }
        return ActionResult.FAIL;
    }
}
