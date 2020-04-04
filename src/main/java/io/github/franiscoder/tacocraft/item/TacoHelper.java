package io.github.franiscoder.tacocraft.item;

import io.github.franiscoder.tacocraft.TacoCraft;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class TacoHelper {
    public static FoodComponent createFoodComponent(FoodComponent baseMeat) {
        return createFoodComponent(baseMeat, 0,0);
    }
    public static FoodComponent createFoodComponent(FoodComponent baseMeat, int hungerModifier, float satModifier) {
        return new FoodComponent.Builder()
                .hunger(baseMeat.getHunger()+2+hungerModifier)
                .saturationModifier(baseMeat.getSaturationModifier() + 0.2F+satModifier)
                .meat()
                .build();
    }

    public static Item createTaco(FoodComponent foodComponent) {
        return new Item(new Item.Settings().group(TacoCraft.ITEM_GROUP).food(foodComponent));
    }
}
