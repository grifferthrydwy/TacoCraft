package io.github.frqnny.tacocraft.client.render;

import io.github.frqnny.tacocraft.blockentity.OpenPotBlockEntity;
import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

public class OpenPotBlockEntityRenderer extends BlockEntityRenderer<OpenPotBlockEntity> {
    public static final ItemStack CLOSED_PENCA = new ItemStack(ModItems.CLOSED_PENCA);
    public static final ItemStack COOKED_PENCA = new ItemStack(ModItems.COOKED_PENCA);

    public OpenPotBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(OpenPotBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack stack = entity.finished ? COOKED_PENCA : CLOSED_PENCA;

        matrices.push();

        matrices.translate(0.5, 0.1, 0.3);
        int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
        if (entity.closed_pancas > 0) {
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
        }
        if (entity.closed_pancas > 1) {
            matrices.translate(0, 0, 0.4);
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
        }

        matrices.pop();

    }
}
