package io.github.frqnny.tacocraft.mixin;

import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComposterBlock.class)
public class MixinComposterBlock {

    @Inject(method = "registerDefaultCompostableItems", at = @At(value = "TAIL"))
    private static void registerSeed(CallbackInfo info) {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CORN_SEED, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CORN, 0.65F);
    }
}
