package io.github.frqnny.tacocraft.config;

import com.google.common.base.CaseFormat;
import de.siphalor.tweed.config.ConfigEnvironment;
import de.siphalor.tweed.config.ConfigScope;
import de.siphalor.tweed.config.annotated.AConfigEntry;
import de.siphalor.tweed.config.annotated.AConfigListener;
import de.siphalor.tweed.config.annotated.ATweedConfig;
import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.init.ModItems;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;

@ATweedConfig(file = "tacocraft", scope = ConfigScope.GAME, environment = ConfigEnvironment.SYNCED, casing = CaseFormat.LOWER_HYPHEN)
public class Config {

    @AConfigEntry(name = "addCornSeedsToGrassLootTable", comment = "Adds Corn Seeds to Grass Loot Table. Turn off if you do not want TacoCraft Seeds to drop \n its seeds when grass breaks")
    public static boolean addCornSeedsToGrassLootTable = true;

    @AConfigListener
    public static void reload() {
        if (addCornSeedsToGrassLootTable) {
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
                if (TacoCraft.GRASS_LOOT_TABLE_ID.equals(id)) {
                    FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootTableRange.create(1))
                            .withEntry(ItemEntry.builder(ModItems.CORN_SEED).build())
                            .withCondition(RandomChanceLootCondition.builder(0.125F).build());

                    supplier.withPool(poolBuilder.build());
                }
            });
        }
    }
}
