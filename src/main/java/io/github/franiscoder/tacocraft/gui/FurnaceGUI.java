package io.github.franiscoder.tacocraft.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class FurnaceGUI extends CottonCraftingController {

    public FurnaceGUI(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(150, 150);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 2);

        WBar fire = new WBar(new Identifier("tacocraft:textures/gui/fire_background.png"), new Identifier("tacocraft:textures/gui/fire_icon.png"),
                0, 1, WBar.Direction.UP);
        root.add(fire, 4, 0);

        root.add(this.createPlayerInventoryPanel(), 0, 4);
        root.validate(this);
    }

    @Environment(EnvType.CLIENT)
    public int getFuelProgress() {
        int i = this.propertyDelegate.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.propertyDelegate.get(0) * 13 / i;
    }

    @Environment(EnvType.CLIENT)
    public boolean isBurning() {
        return this.propertyDelegate.get(0) > 0;
    }

}
