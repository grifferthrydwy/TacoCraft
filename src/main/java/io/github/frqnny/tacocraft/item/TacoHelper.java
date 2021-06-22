package io.github.frqnny.tacocraft.item;

import io.github.frqnny.tacocraft.TacoCraft;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class TacoHelper {
    public static final FoodComponent BARBACOA = new FoodComponent.Builder().hunger(10).saturationModifier(1.5F).meat().build();

    public static FoodComponent createFoodComponent(FoodComponent baseMeat, int hungerModifier, float satModifier) {
        return new FoodComponent.Builder()
                .hunger(baseMeat.getHunger() + 2 + hungerModifier)
                .saturationModifier(baseMeat.getSaturationModifier() + 0.2F + satModifier)
                .meat()
                .build();
    }

    public static Item createTaco(FoodComponent foodComponent) {
        return new Item(new FabricItemSettings().group(TacoCraft.ITEM_GROUP).food(foodComponent));
    }
}
