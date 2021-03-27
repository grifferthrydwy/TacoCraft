package io.github.frqnny.tacocraft.mixin;

import com.google.common.collect.ImmutableList;
import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.entity.ai.brain.task.FarmerWorkTask;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(FarmerWorkTask.class)
public abstract class MixinFarmerWorkTask {
    @Mutable
    @Shadow
    @Final
    private static List<Item> COMPOSTABLES;

    static {
        COMPOSTABLES = new ImmutableList.Builder<Item>()
                .addAll(COMPOSTABLES)
                .add(ModItems.CORN, ModItems.CORN_SEED)
                .build();
    }
}
