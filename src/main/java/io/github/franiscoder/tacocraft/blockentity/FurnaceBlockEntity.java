package io.github.franiscoder.tacocraft.blockentity;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import io.github.franiscoder.tacocraft.block.inventory.FurnaceInventory;
import io.github.franiscoder.tacocraft.client.gui.FurnaceGUI;
import io.github.franiscoder.tacocraft.init.ModBlocks;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

import javax.annotation.Nullable;

public class FurnaceBlockEntity extends BlockEntity implements FurnaceInventory, InventoryProvider, SidedInventory, PropertyDelegateHolder, Tickable, ExtendedScreenHandlerFactory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
    public int burnTime = 0;
    public int fuelTime = 0;
    public PropertyDelegate propertyDelegate = new PropertyDelegate() {

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

    public FurnaceBlockEntity() {
        super(ModBlocks.FURNACE_BLOCK_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
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
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return this;
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    public boolean isBurning() {
        return burnTime > 0;
    }

    @Override
    public void tick() {
        assert world != null;
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

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new FurnaceGUI(syncId, inv, ScreenHandlerContext.create(this.world, this.pos));
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] result = new int[items.size()];
        int resultLength = result.length;
        for (int i = 0; i < resultLength; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }
}
