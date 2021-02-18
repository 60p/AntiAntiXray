package me.constantindev.antiantixray.Etc;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class RenderHelper {
    public static List<RenderableBlock> queue = new ArrayList<>();

    public static void addToQueue(RenderableBlock block) {
        if (queue.contains(block)) return;
        queue.add(block);
    }

    public static void renderBlockOutline(BlockPos bpos, int r, int g, int b, int a, MatrixStack matrices, Camera camera) {
        Vec3d cameraPos = camera.getPos();
        VertexConsumerProvider.Immediate entityVertexConsumers = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer builder = entityVertexConsumers.getBuffer(RenderType.OVERLAY_LINES);

        matrices.push();
        matrices.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

        RenderHelper.renderBlockBounding(matrices, builder, bpos, (float) r / 255, (float) g / 255, (float) b / 255, (float) a / 255);

        RenderSystem.disableDepthTest();
        matrices.pop();
        entityVertexConsumers.draw(RenderType.OVERLAY_LINES);
    }

    public static void renderCustom(float x, float y, float z, float xWidth, float yWidth, float zWidth, float r, float g, float b, float a, MatrixStack matrices, Camera camera) {
        Vec3d cameraPos = camera.getPos();
        VertexConsumerProvider.Immediate entityVertexConsumers = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer builder = entityVertexConsumers.getBuffer(RenderType.OVERLAY_LINES);

        matrices.push();
        matrices.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

        RenderHelper.renderBlockBoundingCustom(matrices, builder, new BlockPos(x, y, z), r, g, b, a, xWidth, yWidth, zWidth);

        RenderSystem.disableDepthTest();
        matrices.pop();
        entityVertexConsumers.draw(RenderType.OVERLAY_LINES);
    }

    private static void renderBlockBounding(MatrixStack matrices, VertexConsumer builder, BlockPos bp, float r, float g, float b, float a) {
        if (bp == null) {
            return;
        }

        final float size = 1.0f;
        final float x = bp.getX(), y = bp.getY(), z = bp.getZ();

        WorldRenderer.drawBox(matrices, builder, x, y, z, x + size, y + size, z + size, r, g, b, a);
    }

    private static void renderBlockBoundingCustom(MatrixStack matrices, VertexConsumer builder, BlockPos bp, float r, float g, float b, float a, float x1, float y1, float z1) {
        if (bp == null) {
            return;
        }

        final float x = bp.getX(), y = bp.getY(), z = bp.getZ();

        WorldRenderer.drawBox(matrices, builder, x, y, z, x + x1, y + y1, z + z1, r, g, b, a);
    }
}
