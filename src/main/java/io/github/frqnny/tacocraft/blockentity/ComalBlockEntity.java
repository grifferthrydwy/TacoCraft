package io.github.frqnny.tacocraft.blockentity;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.init.ModBlocks;
import io.github.frqnny.tacocraft.init.ModItems;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class ComalBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
    boolean doneCooking = false;
    boolean canRender = false;
    private int cookTime = -1;
    private boolean hasTortilla = false;

    public ComalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.COMAL_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ComalBlockEntity be) {
        if (!world.isClient) {
            if (be.isCooking()) {
                be.cookTime--;
            } else if (be.cookTime == 0) {
                be.doneCooking = true;
                be.cookTime--;
                be.hasTortilla = false;
                be.sync();
            }
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        tag.putShort("CookTime", (short) this.cookTime);
        return super.writeNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        this.cookTime = tag.getShort("CookTime");
    }

    @Override
    public void fromClientTag(NbtCompound compoundTag) {
        if (compoundTag.getBoolean("hasTortilla")) {
            hasTortilla = true;
        }
        canRender = compoundTag.getBoolean("canRender");
        doneCooking = compoundTag.getBoolean("doneCooking");
    }

    @Override
    public NbtCompound toClientTag(NbtCompound compoundTag) {
        compoundTag.putBoolean("hasTortilla", hasTortilla);
        compoundTag.putBoolean("canRender", canRender);
        compoundTag.putBoolean("doneCooking", doneCooking);

        return compoundTag;

    }

    public void startCooking() {
        cookTime = TacoCraft.CONFIG.tortilla_cook_time;
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

}
