package io.github.franiscoder.tacocraft;

import io.github.franiscoder.tacocraft.block.FurnaceBlock;
import io.github.franiscoder.tacocraft.client.render.ComalBlockEntityRenderer;
import io.github.franiscoder.tacocraft.client.gui.FurnaceGUI;
import io.github.franiscoder.tacocraft.client.gui.FurnaceScreen;
import io.github.franiscoder.tacocraft.init.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.container.BlockContext;

public class TacoCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COMAL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_BLOCK, RenderLayer.getCutout());
        ScreenProviderRegistry.INSTANCE.registerFactory(FurnaceBlock.ID, (syncId, identifier, player, buf) -> new FurnaceScreen(new FurnaceGUI(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        BlockEntityRendererRegistry.INSTANCE.register(ModBlocks.COMAL_BLOCK_ENTITY, ComalBlockEntityRenderer::new);
    }
}
