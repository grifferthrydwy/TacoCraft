package io.github.franiscoder.tacocraft.init;

import io.github.franiscoder.tacocraft.TacoCraft;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item CORN_SEED = new AliasedBlockItem(ModBlocks.CORN_BLOCK, new Item.Settings().group(TacoCraft.ITEM_GROUP));
    public static final Item CORN = new Item(new Item.Settings().group(TacoCraft.ITEM_GROUP).group(TacoCraft.ITEM_GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build()));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(TacoCraft.MODID,"corn_seed"), CORN_SEED);
        Registry.register(Registry.ITEM, new Identifier(TacoCraft.MODID,"corn"), CORN);
    }
}
