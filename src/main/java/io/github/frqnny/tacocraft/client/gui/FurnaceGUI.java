package io.github.frqnny.tacocraft.client.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.frqnny.tacocraft.TacoCraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;

public class FurnaceGUI extends SyncedGuiDescription {

    public FurnaceGUI(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(TacoCraft.Furnace, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(150, 150);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 2);

        WBar fire = new WBar(new Identifier("tacocraft:textures/gui/fire_background.png"), new Identifier("tacocraft:textures/gui/fire_icon.png"),
                0, 1, WBar.Direction.UP);
        //fire.setProperties(getBlockPropertyDelegate(context));
        root.add(fire, 4, 0);

        root.add(this.createPlayerInventoryPanel(), 0, 4);
        root.validate(this);
    }


}
