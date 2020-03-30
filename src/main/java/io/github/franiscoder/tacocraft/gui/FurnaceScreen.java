package io.github.franiscoder.tacocraft.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;

public class FurnaceScreen extends CottonInventoryScreen<FurnaceGUI> {
    public FurnaceScreen(FurnaceGUI container, PlayerEntity player) {
        super(container, player);
    }
}
