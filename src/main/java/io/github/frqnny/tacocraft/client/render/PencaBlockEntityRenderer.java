package io.github.frqnny.tacocraft.client.render;

import io.github.frqnny.tacocraft.blockentity.PencaBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3f;

import java.util.Objects;

public class PencaBlockEntityRenderer implements BlockEntityRenderer<PencaBlockEntity> {
    public static final ItemStack STEAK_STACK = new ItemStack(Items.COOKED_BEEF);
    public static final ItemStack PORKCHOP_STACK = new ItemStack(Items.COOKED_PORKCHOP);

    public PencaBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(PencaBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        int lightAbove = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().up());

        matrices.push();
        matrices.translate(0.5, 0.029, 0.45);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));

        int steaks = entity.steaks;

        for (int i = 0; i < steaks; i++) {
            ItemStack stack = new ItemStack(Items.COOKED_BEEF);
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers, 0);
            matrices.translate(0, 0, -0.034);
        }

        int porkchops = entity.porkchops;
        for (int j = 0; j < porkchops; j++) {
            ItemStack stack = new ItemStack(Items.COOKED_PORKCHOP);
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers, 0);
            matrices.translate(0, 0, -0.034);
        }

        matrices.pop();
    }
}
