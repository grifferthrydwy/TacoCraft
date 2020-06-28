package io.github.franiscoder.tacocraft.block.inventory;

import io.github.franiscoder.tacocraft.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/**
 * A simple {@code SidedInventory} implementation with only default methods + an item list getter.
 *
 * <h2>Reading and writing to tags</h2>
 * Use  and
 * on {@linkplain #getItems() the item list}.
 * <p>
 * License: <a href="https://creativecommons.org/publicdomain/zero/1.0/">CC0</a>
 *
 * @author Juuz
 */

/**
 * A simple {@code Inventory} implementation with only default methods + an item list getter.
 * <p>
 * Originally by Juuz
 */
@FunctionalInterface
public interface ComalInventory extends Inventory {
    /**
     * Creates an inventory from the item list.
     */
    static ComalInventory of(DefaultedList<ItemStack> items) {
        return () -> items;
    }
    // Creation

    /**
     * Creates a new inventory with the size.
     */
    static ComalInventory ofSize(int size) {
        return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
    }

    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     */
    DefaultedList<ItemStack> getItems();
    // Inventory

    /**
     * Returns the inventory size.
     */

    @Override
    default int size() {
        return getItems().size();
    }

    /**
     * @return true if this inventory has only empty stacks, false otherwise
     */
    @Override
    default boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the item in the slot.
     */
    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    /**
     * Takes a stack of the size from the slot.
     * <p>(default implementation) If there are less items in the slot than what are requested,
     * takes all items in that slot.
     */
    @Override
    default ItemStack removeStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    /**
     * Removes the current stack in the {@code slot} and returns it.
     */
    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

    /**
     * Replaces the current stack in the {@code slot} with the provided stack.
     * <p>If the stack is too big for this inventory ({@link Inventory#getMaxCountPerStack()}),
     * it gets resized to this inventory's maximum amount.
     */
    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }

    /**
     * Clears {@linkplain #getItems() the item list}}.
     */
    @Override
    default void clear() {
        getItems().clear();
    }

    @Override
    default void markDirty() {
        // Override if you want behavior.
    }

    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    default boolean isValid(int slot, ItemStack stack) {
        return stack.getItem() == ModItems.TORTILLA_DOUGH;
    }
}

