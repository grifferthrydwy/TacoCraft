package io.github.frqnny.tacocraft.blockentity;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.init.ModBlocks;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

import java.util.Set;

public class OpenPotBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable {
    public int closed_pancas = 0;
    public boolean finished = false;
    public int tick = 0;
    protected boolean ready = false;
    protected boolean cooking = false;

    public OpenPotBlockEntity() {
        super(ModBlocks.OPEN_POT_BLOCK_ENTITY);
    }


    @Override
    public void tick() {
        if (ready) {
            if (this.canStartCooking()) {
                tick = TacoCraft.config.penca_cook_time;
                ready = false;
                cooking = true;

                if (!world.isClient) {
                    sync();
                }
            }
        } else if (cooking) {
            tick--;
            if (tick == 0) {
                cooking = false;
                finished = true;
                world.setBlockState(pos.up(), Blocks.COARSE_DIRT.getDefaultState());
                if (!world.isClient) {
                    sync();
                }
            }
        }
    }


    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        closed_pancas = tag.getInt("ClosedPancas");
        tick = tag.getInt("Tick");
        ready = tag.getBoolean("Ready");
        cooking = tag.getBoolean("Cooking");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
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
    public void fromClientTag(CompoundTag tag) {
        closed_pancas = tag.getInt("ClosedPancas");
        tick = tag.getInt("Tick");
        ready = tag.getBoolean("Ready");
        cooking = tag.getBoolean("Cooking");
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        tag.putInt("ClosedPancas", closed_pancas);
        tag.putInt("Tick", tick);
        tag.putBoolean("Ready", ready);
        tag.putBoolean("Cooking", cooking);
        return tag;
    }
}
