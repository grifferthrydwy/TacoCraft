package io.github.franiscoder.tacocraft.blockentity;

import io.github.franiscoder.tacocraft.block.inventory.ComalInventory;
import io.github.franiscoder.tacocraft.init.ModBlocks;
import io.github.franiscoder.tacocraft.init.ModItems;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Objects;

public class ComalBlockEntity extends BlockEntity implements Tickable, ComalInventory, InventoryProvider, BlockEntityClientSerializable {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
    boolean doneCooking = false;
    boolean canRender = false;
    private int cookTime = -1;

    public ComalBlockEntity() {
        super(ModBlocks.COMAL_BLOCK_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void tick() {

        if (isCooking()) {
            --cookTime;
        } else if (cookTime == 0) {
            doneCooking = true;
            --cookTime;
            this.setInvStack(0, ItemStack.EMPTY);
            sync();
        }
    }

    @Override
    public SidedInventory getInventory(BlockState state, IWorld world, BlockPos pos) {
        return ComalInventory.of(getItems());
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putShort("CookTime", (short) this.cookTime);
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        this.cookTime = tag.getShort("CookTime");
        Inventories.fromTag(tag, items);

    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        if (compoundTag.getBoolean("hasTortilla")) {
            this.setInvStack(0, new ItemStack(ModItems.TORTILLA_DOUGH));
        }
        canRender = compoundTag.getBoolean("canRender");
        doneCooking = compoundTag.getBoolean("doneCooking");
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        ItemStack stack = new ItemStack(ModItems.TORTILLA_DOUGH);
        stack.setCount(1);
        boolean hasTortilla = this.getInvStack(0).getItem().getTranslationKey().equals(stack.getItem().getTranslationKey());
        compoundTag.putBoolean("hasTortilla", hasTortilla);
        compoundTag.putBoolean("canRender", canRender);
        compoundTag.putBoolean("doneCooking", doneCooking);

        return compoundTag;

    }

    public void startCooking() {
        cookTime = 300;
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
        boolean old = canRender;
        canRender = !old;
    }
}
