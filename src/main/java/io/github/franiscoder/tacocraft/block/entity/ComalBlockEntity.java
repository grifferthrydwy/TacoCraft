package io.github.franiscoder.tacocraft.block.entity;

import io.github.franiscoder.tacocraft.block.inventory.ImplementedInventory;
import io.github.franiscoder.tacocraft.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Clearable;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ComalBlockEntity extends BlockEntity implements Clearable, Tickable, ImplementedInventory, InventoryProvider {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public ComalBlockEntity() {
        super(ModBlocks.COMAL_BLOCK_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void clear() {

    }

    @Override
    public void tick() {

    }

    @Override
    public SidedInventory getInventory(BlockState state, IWorld world, BlockPos pos) {
        return ImplementedInventory.of(getItems());
    }
}
