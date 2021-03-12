package io.github.frqnny.tacocraft.init;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.item.TacoHelper;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item CORN_SEED = new AliasedBlockItem(ModBlocks.CORN_BLOCK, new Item.Settings().group(TacoCraft.ITEM_GROUP));
    public static final Item CORN = new Item(new Item.Settings().group(TacoCraft.ITEM_GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build()));
    public static final Item TORTILLA_DOUGH = new Item(new Item.Settings().group(TacoCraft.ITEM_GROUP));
    public static final Item TORTILLA = new Item(new Item.Settings().group(TacoCraft.ITEM_GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build()));
    public static final Item EMPTY_SHELL = new Item(new Item.Settings().group(TacoCraft.ITEM_GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build()));

    public static final Item STEAK_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_BEEF));
    public static final Item CARNITAS_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_PORKCHOP));
    public static final Item AL_PASTOR_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_PORKCHOP, 1, 0.1F));
    public static final Item FISH_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_COD));
    public static final Item CHICKEN_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_CHICKEN));
    public static final Item CHEESY_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_BEEF, 2, 0.2F));
    public static final Item CRUNCHY_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_BEEF));
    public static final Item GOLDEN_TACO = TacoHelper.createTaco(FoodComponents.GOLDEN_APPLE);

    public static void registerItems() {
        Registry.register(Registry.ITEM, TacoCraft.id("corn_seed"), CORN_SEED);
        Registry.register(Registry.ITEM, TacoCraft.id("corn"), CORN);
        Registry.register(Registry.ITEM, TacoCraft.id("tortilla_dough"), TORTILLA_DOUGH);
        Registry.register(Registry.ITEM, TacoCraft.id("tortilla"), TORTILLA);
        Registry.register(Registry.ITEM, TacoCraft.id("empty_shell"), EMPTY_SHELL);

        Registry.register(Registry.ITEM, TacoCraft.id("steak_taco"), STEAK_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("carnitas_taco"), CARNITAS_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("al_pastor_taco"), AL_PASTOR_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("fish_taco"), FISH_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("chicken_taco"), CHICKEN_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("cheesy_taco"), CHEESY_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("crunchy_taco"), CRUNCHY_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("golden_taco"), GOLDEN_TACO);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CORN_SEED, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CORN, 0.65F);
    }

}
