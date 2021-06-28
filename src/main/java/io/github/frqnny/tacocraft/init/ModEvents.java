package io.github.frqnny.tacocraft.init;

import io.github.frqnny.tacocraft.TacoCraft;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class ModEvents {

    public static void init() {
        if (TacoCraft.CONFIG.addCornSeedsToGrassLootTable) {
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
                if (TacoCraft.GRASS_LOOT_TABLE_ID.equals(id)) {
                    FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .withEntry(ItemEntry.builder(ModItems.CORN_SEED).build())
                            .withCondition(RandomChanceLootCondition.builder(0.125F).build());

                    supplier.withPool(poolBuilder.build());
                }
            });
        }
    }
}
