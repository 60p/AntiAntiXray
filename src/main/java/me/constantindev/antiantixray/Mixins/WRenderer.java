package me.constantindev.antiantixray.Mixins;

import me.constantindev.antiantixray.Etc.CustomRenderableItem;
import me.constantindev.antiantixray.Etc.RenderHelper;
import me.constantindev.antiantixray.Etc.RenderableBlock;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WRenderer {
    @Inject(method = "render", at = @At("TAIL"))
    public void r(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci) {
        for (RenderableBlock renderableBlock : RenderHelper.queue.toArray(new RenderableBlock[0])) {
            try {
                if (renderableBlock instanceof CustomRenderableItem) {
                    CustomRenderableItem cr = (CustomRenderableItem) renderableBlock;
                    RenderHelper.renderCustom(cr.bp.getX(), cr.bp.getY(), cr.bp.getZ(), cr.xW, cr.yW, cr.zW, cr.r, cr.g, cr.b, cr.a, matrices, camera);
                } else {
                    RenderHelper.renderBlockOutline(renderableBlock.bp, renderableBlock.r, renderableBlock.g, renderableBlock.b, renderableBlock.a, matrices, camera);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
