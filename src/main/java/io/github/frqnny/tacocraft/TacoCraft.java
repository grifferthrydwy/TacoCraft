package io.github.frqnny.tacocraft;

import io.github.frqnny.tacocraft.block.FurnaceBlock;
import io.github.frqnny.tacocraft.client.gui.FurnaceGUI;
import io.github.frqnny.tacocraft.init.ModBlocks;
import io.github.frqnny.tacocraft.init.ModGen;
import io.github.frqnny.tacocraft.init.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class TacoCraft implements ModInitializer {
    public static final String MODID = "tacocraft";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            id("item_group"),
            () -> new ItemStack(ModBlocks.FURNACE_BLOCK)
    );
    public static final Identifier GRASS_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/grass");

    public static ScreenHandlerType<FurnaceGUI> Furnace;


    public static Identifier id(String string) {
        return new Identifier(MODID, string);
    }

    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
        ModBlocks.registerBlockItems();
        ModBlocks.registerBlockEntities();
        ModItems.registerItems();
        ModGen.register();


        Furnace = ScreenHandlerRegistry.registerExtended(FurnaceBlock.ID, (syncId, inventory, buf) -> new FurnaceGUI(syncId, inventory, ScreenHandlerContext.create(inventory.player.world, buf.readBlockPos())));
    }
}
