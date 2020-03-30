package io.github.franiscoder.tacocraft;


import io.github.franiscoder.tacocraft.block.FurnaceBlock;
import io.github.franiscoder.tacocraft.gui.FurnaceGUI;
import io.github.franiscoder.tacocraft.init.ModBlocks;
import io.github.franiscoder.tacocraft.init.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class TacoCraft implements ModInitializer {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("item_group", "tacocraft"),
            () -> new ItemStack(ModBlocks.FURNACE_BLOCK));
    public static String MODID = "tacocraft";

    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
        ModBlocks.registerBlockItems();
        ModBlocks.registerBlockEntities();
        ModItems.registerItems();
        ContainerProviderRegistry.INSTANCE.registerFactory(FurnaceBlock.ID, (syncId, id, player, buf) -> new FurnaceGUI(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));

    }
}
