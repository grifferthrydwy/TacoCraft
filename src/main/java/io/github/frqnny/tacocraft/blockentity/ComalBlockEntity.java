package io.github.frqnny.tacocraft.blockentity;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.init.ModBlocks;
import io.github.frqnny.tacocraft.init.ModItems;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Tickable;

import java.util.Objects;

public class ComalBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable {
    boolean doneCooking = false;
    boolean canRender = false;
    private int cookTime = -1;
    private boolean hasTortilla = false;

    public ComalBlockEntity() {
        super(ModBlocks.COMAL_BLOCK_ENTITY);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putShort("CookTime", (short) this.cookTime);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.cookTime = tag.getShort("CookTime");
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        if (compoundTag.getBoolean("hasTortilla")) {
            hasTortilla = true;
        }
        canRender = compoundTag.getBoolean("canRender");
        doneCooking = compoundTag.getBoolean("doneCooking");
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        compoundTag.putBoolean("hasTortilla", hasTortilla);
        compoundTag.putBoolean("canRender", canRender);
        compoundTag.putBoolean("doneCooking", doneCooking);

        return compoundTag;

    }

    public void startCooking() {
        cookTime = TacoCraft.config.tortilla_cook_time;
        setCanRender();
        sync();
    }

    public boolean isCooking() {
        return cookTime > 0;
    }

    public void spawnTortilla() {
        if (doneCooking = true) {
            if (!Objects.requireNonNull(this.getWorld()).isClient) {
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.TORTILLA).copy());
            }
            this.markDirty();
            this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
            doneCooking = false;
            setCanRender();
        }
    }

    public boolean isFinished() {
        return doneCooking;
    }

    public ItemStack stackToRender() {
        return canRender ? doneCooking ? new ItemStack(ModItems.TORTILLA) : new ItemStack(ModItems.TORTILLA_DOUGH) : ItemStack.EMPTY;
    }

    public void setCanRender() {
        canRender = !canRender;
    }

    @Override
    public void tick() {
        if (!this.world.isClient) {
            if (isCooking()) {
                --cookTime;
            } else if (cookTime == 0) {
                doneCooking = true;
                --cookTime;
                hasTortilla = false;
                sync();
            }
        }
    }
}
