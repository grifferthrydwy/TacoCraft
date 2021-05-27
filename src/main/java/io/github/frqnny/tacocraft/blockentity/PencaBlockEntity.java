package io.github.frqnny.tacocraft.blockentity;

import io.github.frqnny.tacocraft.init.ModBlocks;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class PencaBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
    public int steaks = 0;
    public int porkchops = 0;

    public PencaBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.PENCA_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        porkchops = tag.getInt("porkchops");
        steaks = tag.getInt("steaks");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("porkchops", porkchops);
        tag.putInt("steaks", steaks);
        return tag;
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        porkchops = tag.getInt("porkchops");
        steaks = tag.getInt("steaks");
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        tag.putInt("porkchops", porkchops);
        tag.putInt("steaks", steaks);
        return tag;
    }
}
