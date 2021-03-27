package io.github.frqnny.tacocraft.mixin;

import com.google.common.collect.ImmutableSet;
import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public abstract class MixinVillagerEntity {

    @Inject(method = "canGather", at = @At("TAIL"), cancellable = true)
    public void canGatherCorn(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if (stack.getItem().equals(ModItems.CORN)) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "hasSeedToPlant", at = @At("TAIL"), cancellable = true)
    public void hasCornKernelsToPlant(CallbackInfoReturnable<Boolean> info) {
        if (((VillagerEntity) (Object) this).getInventory().containsAny(ImmutableSet.of(ModItems.CORN_SEED))) {
            info.setReturnValue(true);
        }
    }
}
