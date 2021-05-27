package io.github.frqnny.tacocraft.client.render;

import io.github.frqnny.tacocraft.blockentity.ComalBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

import java.util.Objects;

public class ComalBlockEntityRenderer implements BlockEntityRenderer<ComalBlockEntity> {
    public ComalBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(ComalBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack stack = blockEntity.stackToRender();

        matrices.push();

        matrices.translate(0.5, 0, 0.45);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));

        int lightAbove = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(blockEntity.getWorld()), blockEntity.getPos().up());
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers, 0);

        matrices.pop();
    }
}
