package io.github.frqnny.tacocraft;

import draylar.omegaconfig.OmegaConfig;
import io.github.frqnny.tacocraft.block.FurnaceBlock;
import io.github.frqnny.tacocraft.client.gui.FurnaceGUI;
import io.github.frqnny.tacocraft.config.TacoCraftConfig;
import io.github.frqnny.tacocraft.init.*;
import io.github.frqnny.tacocraft.util.TagUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;

public class TacoCraft implements ModInitializer {
    public static final String MODID = "tacocraft";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            id("item_group"),
            () -> new ItemStack(ModBlocks.FURNACE_BLOCK)
    );
    public static final Identifier GRASS_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/grass");

    public static TacoCraftConfig config = OmegaConfig.register(TacoCraftConfig.class);


    public static Identifier id(String string) {
        return new Identifier(MODID, string);
    }

    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModItems.init();
        ModGen.init();
        ModEvents.init();

        TagUtils.init();
    }
}
