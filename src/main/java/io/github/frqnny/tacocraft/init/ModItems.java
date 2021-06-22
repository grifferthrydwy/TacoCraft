package io.github.frqnny.tacocraft.init;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.block.*;
import io.github.frqnny.tacocraft.item.CookedPencaItem;
import io.github.frqnny.tacocraft.item.DivineCornBladeItem;
import io.github.frqnny.tacocraft.item.TacoHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item CORN_SEED = new AliasedBlockItem(ModBlocks.CORN_BLOCK, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item CORN = new Item(new FabricItemSettings().group(TacoCraft.ITEM_GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build()));
    public static final Item DIVINE_CORN_BLADE = new DivineCornBladeItem(ToolMaterials.WOOD, 1, -2.4F, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item TORTILLA_DOUGH = new Item(new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item TORTILLA = new Item(new FabricItemSettings().group(TacoCraft.ITEM_GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build()));
    public static final Item EMPTY_SHELL = new Item(new FabricItemSettings().group(TacoCraft.ITEM_GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).build()));

    public static final Item STEAK_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_BEEF, 1, 0.6F));
    public static final Item CARNITAS_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_PORKCHOP, 1, 0.6F));
    public static final Item AL_PASTOR_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_PORKCHOP, 1, 0.1F));
    public static final Item FISH_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_COD, 1, 0.6F));
    public static final Item CHICKEN_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_CHICKEN, 1, 0.6F));
    public static final Item CHEESY_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_BEEF, 2, 0.2F));
    public static final Item CRUNCHY_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_BEEF, 1, 0.6F));
    public static final Item BARBACOA_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(TacoHelper.BARBACOA, 1, 0.6F));
    public static final Item DOUBLE_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.COOKED_BEEF, 2, 1.2F));
    public static final Item CALIFORNIA_TACO = TacoHelper.createTaco(TacoHelper.createFoodComponent(FoodComponents.BAKED_POTATO, 1, 0.6F));
    public static final Item GOLDEN_TACO = TacoHelper.createTaco(FoodComponents.GOLDEN_APPLE);

    public static final Item CLOSED_PENCA = new Item(new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item COOKED_PENCA = new CookedPencaItem(new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item BARBACOA = new Item(new FabricItemSettings().group(TacoCraft.ITEM_GROUP).food(TacoHelper.BARBACOA));

    public static final Item FURNACE_BLOCK = new BlockItem(ModBlocks.FURNACE_BLOCK, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item COMAL_BLOCK = new BlockItem(ModBlocks.COMAL, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item OPEN_POT_BLOCK = new BlockItem(ModBlocks.OPEN_POT, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item PENCA_BLOCK = new BlockItem(ModBlocks.PENCA, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item CORN_BRICK_BLOCK = new BlockItem(ModBlocks.CORN_BRICK, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));
    public static final Item TACO_BOWL = new BlockItem(ModBlocks.TACO_BOWL, new FabricItemSettings().group(TacoCraft.ITEM_GROUP));

    public static void init() {
        Registry.register(Registry.ITEM, TacoCraft.id("corn_seed"), CORN_SEED);
        Registry.register(Registry.ITEM, TacoCraft.id("corn"), CORN);
        Registry.register(Registry.ITEM, TacoCraft.id("divine_corn_blade"), DIVINE_CORN_BLADE);
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
        Registry.register(Registry.ITEM, TacoCraft.id("barbacoa_taco"), BARBACOA_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("double_taco"), DOUBLE_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("california_taco"), CALIFORNIA_TACO);
        Registry.register(Registry.ITEM, TacoCraft.id("golden_taco"), GOLDEN_TACO);

        Registry.register(Registry.ITEM, TacoCraft.id("closed_penca"), CLOSED_PENCA);
        Registry.register(Registry.ITEM, TacoCraft.id("cooked_penca"), COOKED_PENCA);
        Registry.register(Registry.ITEM, TacoCraft.id("barbacoa"), BARBACOA);

        Registry.register(Registry.ITEM, FurnaceBlock.ID, FURNACE_BLOCK);
        Registry.register(Registry.ITEM, ComalBlock.ID, COMAL_BLOCK);
        Registry.register(Registry.ITEM, OpenPotBlock.ID, OPEN_POT_BLOCK);
        Registry.register(Registry.ITEM, PencaBlock.ID, PENCA_BLOCK);
        Registry.register(Registry.ITEM, TacoCraft.id("corn_brick"), CORN_BRICK_BLOCK);
        Registry.register(Registry.ITEM, TacoBowlBlock.ID, TACO_BOWL);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CORN_SEED, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.CORN, 0.65F);
    }
}
