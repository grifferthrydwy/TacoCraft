package io.github.frqnny.tacocraft.blockentity;

import io.github.frqnny.tacocraft.init.ModBlocks;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class PencaBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
    public int steaks = 0;
    public int porkchops = 0;

    public PencaBlockEntity() {
        super(ModBlocks.PENCA_BLOCK_ENTITY);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        porkchops = tag.getInt("porkchops");
        steaks = tag.getInt("steaks");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("porkchops", porkchops);
        tag.putInt("steaks", steaks);
        return tag;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        porkchops = tag.getInt("porkchops");
        steaks = tag.getInt("steaks");
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        tag.putInt("porkchops", porkchops);
        tag.putInt("steaks", steaks);
        return tag;
    }
}
