package io.github.franiscoder.tacocraft.client.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class FurnaceScreen extends CottonInventoryScreen<FurnaceGUI> {
    public FurnaceScreen(FurnaceGUI gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}
