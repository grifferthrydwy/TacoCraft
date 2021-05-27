package io.github.frqnny.tacocraft.item;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CookedPencaItem extends Item {
    public CookedPencaItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        stackInHand.decrement(1);

        ItemStack stack = new ItemStack(ModItems.BARBACOA);
        stack.setCount(TacoCraft.config.barbacoa_per_penca);
        ItemScatterer.spawn(world, user.getX(), user.getY(), user.getZ(), stack);

        return TypedActionResult.success(stackInHand, world.isClient());
    }
}
