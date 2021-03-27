package io.github.frqnny.tacocraft.mixin;

import io.github.frqnny.tacocraft.block.crop.CornBlock;
import io.github.frqnny.tacocraft.init.ModBlocks;
import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.ai.brain.BlockPosLookTarget;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(FarmerVillagerTask.class)
public abstract class MixinFarmerVillagerTask {

    @Shadow
    private BlockPos currentTarget;

    @Shadow
    private long nextResponseTime;

    @Shadow
    @Final
    @Mutable
    private List<BlockPos> targetPositions;

    @Shadow
    abstract BlockPos chooseRandomTarget(ServerWorld world);


    @Inject(method = "keepRunning", at = @At("TAIL"))
    protected void addCornToVillagerTask(ServerWorld world, VillagerEntity villagerEntity, long l, CallbackInfo info) {
        if (this.currentTarget != null && l > this.nextResponseTime) {
            if (this.currentTarget.isWithinDistance(villagerEntity.getPos(), 1.0D)) {
                BlockState state = world.getBlockState(this.currentTarget);
                Block block = state.getBlock();
                if (block instanceof CornBlock && state.get(CornBlock.AGE).equals(7)) {
                    CornBlock corn = (CornBlock) block;
                    int count = world.random.nextInt(3) + 1;
                    ItemScatterer.spawn(world, currentTarget.getX(), currentTarget.getY(), currentTarget.getZ(), new ItemStack(ModItems.CORN, count));
                    if (state.get(CornBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
                        world.setBlockState(currentTarget, corn.withAge(6).with(CornBlock.HALF, DoubleBlockHalf.UPPER));
                        world.setBlockState(currentTarget.down(), corn.withAge(6).with(CornBlock.HALF, DoubleBlockHalf.LOWER));
                    } else {
                        world.setBlockState(currentTarget.up(), corn.withAge(6).with(CornBlock.HALF, DoubleBlockHalf.UPPER));
                        world.setBlockState(currentTarget, corn.withAge(6).with(CornBlock.HALF, DoubleBlockHalf.LOWER));
                    }
                }
                Block block2 = world.getBlockState(this.currentTarget.down()).getBlock();
                if (state.isAir() && block2 instanceof FarmlandBlock && villagerEntity.hasSeedToPlant()) {
                    SimpleInventory simpleInventory = villagerEntity.getInventory();

                    for (int i = 0; i < simpleInventory.size(); ++i) {
                        ItemStack itemStack = simpleInventory.getStack(i);
                        boolean bl = false;
                        if (!itemStack.isEmpty()) {
                            if (itemStack.getItem() == ModItems.CORN_SEED) {

                                world.setBlockState(this.currentTarget, ModBlocks.CORN_BLOCK.getDefaultState().with(CornBlock.HALF, DoubleBlockHalf.LOWER).with(CornBlock.AGE, 1), 3);
                                world.playSound(null, this.currentTarget.getX(), this.currentTarget.getY(), this.currentTarget.getZ(), SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                                itemStack.decrement(1);
                                if (itemStack.isEmpty()) {
                                    simpleInventory.setStack(i, ItemStack.EMPTY);
                                }
                                break;
                            }
                        }
                    }
                }
                if (block instanceof CornBlock && state.get(CornBlock.AGE) != 7) {
                    this.targetPositions.remove(this.currentTarget);
                    this.currentTarget = this.chooseRandomTarget(world);
                    if (this.currentTarget != null) {
                        this.nextResponseTime = l + 20L;
                        villagerEntity.getBrain().remember(MemoryModuleType.WALK_TARGET, (new WalkTarget(new BlockPosLookTarget(this.currentTarget), 0.5F, 1)));
                        villagerEntity.getBrain().remember(MemoryModuleType.LOOK_TARGET, (new BlockPosLookTarget(this.currentTarget)));
                    }
                }
            }
        }
    }


    @Inject(method = "isSuitableTarget", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    protected void isSuitableTarget(BlockPos pos, ServerWorld world, CallbackInfoReturnable<Boolean> info, BlockState blockState, Block block) {
        if (block instanceof CornBlock && blockState.get(CornBlock.AGE) == 7) {
            info.setReturnValue(true);
        }
    }
}