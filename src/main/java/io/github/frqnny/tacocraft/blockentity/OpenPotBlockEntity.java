package io.github.frqnny.tacocraft.blockentity;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.init.ModBlocks;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class OpenPotBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
    public int closed_pancas = 0;
    public boolean finished = false;
    public int tick = 0;
    protected boolean ready = false;
    protected boolean cooking = false;

    public OpenPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.OPEN_POT_BLOCK_ENTITY, pos, state);
    }


    public static void tick(World world, BlockPos pos, BlockState state, OpenPotBlockEntity be) {
        if (be.ready) {
            if (be.canStartCooking()) {
                be.tick = TacoCraft.config.penca_cook_time;
                be.ready = false;
                be.cooking = true;

                if (!world.isClient) {
                    be.sync();
                }
            }
        } else if (be.cooking) {
            be.tick--;
            if (be.tick == 0) {
                be.cooking = false;
                be.finished = true;
                world.setBlockState(pos.up(), Blocks.COARSE_DIRT.getDefaultState());
                if (!world.isClient) {
                    be.sync();
                }
            }
        }
    }


    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        closed_pancas = tag.getInt("ClosedPancas");
        tick = tag.getInt("Tick");
        ready = tag.getBoolean("Ready");
        cooking = tag.getBoolean("Cooking");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("ClosedPancas", closed_pancas);
        tag.putInt("Tick", tick);
        tag.putBoolean("Ready", ready);
        tag.putBoolean("Cooking", cooking);
        return tag;
    }


    public void letsgetreadybois() {
        ready = true;
    }

    public boolean canStartCooking() {
        Set<BlockPos> posToScan = new ObjectOpenHashSet<>(9);
        posToScan.add(pos.add(1, 1, 1));
        posToScan.add(pos.add(1, 1, 0));
        posToScan.add(pos.add(1, 1, -1));
        posToScan.add(pos.add(0, 1, 1));
        posToScan.add(pos.add(0, 1, 0));
        posToScan.add(pos.add(0, 1, -1));
        posToScan.add(pos.add(-1, 1, 1));
        posToScan.add(pos.add(-1, 1, 0));
        posToScan.add(pos.add(-1, 1, -1));

        boolean isDirt = true;
        for (BlockPos pos : posToScan) {
            if (!world.getBlockState(pos).isOf(Blocks.DIRT) && !world.getBlockState(pos).isOf(Blocks.GRASS_BLOCK)) {
                isDirt = false;
            }
        }

        return isDirt;
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        closed_pancas = tag.getInt("ClosedPancas");
        tick = tag.getInt("Tick");
        ready = tag.getBoolean("Ready");
        finished = tag.getBoolean("Finished");
        cooking = tag.getBoolean("Cooking");
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        tag.putInt("ClosedPancas", closed_pancas);
        tag.putInt("Tick", tick);
        tag.putBoolean("Ready", ready);
        tag.putBoolean("Finished", finished);
        tag.putBoolean("Cooking", cooking);
        return tag;
    }
}
