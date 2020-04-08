package io.github.franiscoder.tacocraft.blockentity;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import io.github.franiscoder.tacocraft.block.inventory.FurnaceInventory;
import io.github.franiscoder.tacocraft.init.ModBlocks;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class FurnaceBlockEntity extends BlockEntity implements FurnaceInventory, InventoryProvider, PropertyDelegateHolder, Tickable {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
    public int burnTime = 0;
    public int fuelTime = 0;
    public PropertyDelegate propertyDelegate;

    public FurnaceBlockEntity() {
        super(ModBlocks.FURNACE_BLOCK_ENTITY);
        propertyDelegate = new PropertyDelegate() {

            public int get(int index) {
                switch (index) {
                    case 0:
                        return burnTime;
                    case 1:
                        return fuelTime;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        burnTime = value;
                        break;
                    case 1:
                        fuelTime = value;
                        break;
                }

            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        this.burnTime = tag.getShort("BurnTime");
        this.fuelTime = tag.getShort("FuelTime");
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putShort("BurnTime", (short) this.burnTime);
        tag.putShort("FuelTime", (short) this.burnTime);
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    @Override
    public SidedInventory getInventory(BlockState state, IWorld world, BlockPos pos) {
        return FurnaceInventory.of(getItems());
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return this.propertyDelegate;
    }

    public boolean isBurning() {
        return burnTime > 0;
    }

    @Override
    public void tick() {
        boolean readyToBurn = !isBurning() && AbstractFurnaceBlockEntity.canUseAsFuel(items.get(0));
        boolean isBurning = isBurning();
        boolean doneBurning = !isBurning && items.get(0) == ItemStack.EMPTY;

        if (isBurning) {
            --burnTime;
        } else if (readyToBurn) {
            fuelTime = getFuelTime(items.get(0));
            burnTime = fuelTime;
            ItemStack stack = items.get(0);
            Item item = stack.getItem();
            stack.decrement(1);
            if (stack.isEmpty()) {
                Item item2 = item.getRecipeRemainder();
                this.items.set(0, item2 == null ? ItemStack.EMPTY : new ItemStack(item2));
            }
            world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, true), 3);
        } else if (doneBurning) {
            world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, false), 3);
        }


    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(item, 0);
        }
    }
}
