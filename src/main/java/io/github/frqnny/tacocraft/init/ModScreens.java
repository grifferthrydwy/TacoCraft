package io.github.frqnny.tacocraft.init;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.frqnny.tacocraft.block.FurnaceBlock;
import io.github.frqnny.tacocraft.client.gui.FurnaceGUI;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreens {
    public static ScreenHandlerType<FurnaceGUI> FURNACE;

    public static void init() {
        FURNACE = ScreenHandlerRegistry.registerExtended(FurnaceBlock.ID, (syncId, inventory, buf) -> new FurnaceGUI(syncId, inventory, ScreenHandlerContext.create(inventory.player.world, buf.readBlockPos())));
    }

    public static void clientInit() {
        ScreenRegistry.<FurnaceGUI, CottonInventoryScreen<FurnaceGUI>>register(ModScreens.FURNACE, (gui, inventory, title) -> new CottonInventoryScreen<>(gui, inventory.player, title));
    }
}
