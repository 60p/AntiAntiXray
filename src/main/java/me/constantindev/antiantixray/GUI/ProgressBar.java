package me.constantindev.antiantixray.GUI;

import com.mojang.blaze3d.systems.RenderSystem;
import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;

@SuppressWarnings("deprecation")
public class ProgressBar implements Toast {

    public boolean done = false;
    public int progress = 1;
    public double todo = Math.pow(Config.rad * 2 + 1, 3);

    private static double round(double value) {
        int scale = (int) Math.pow(10, 2);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
        manager.getGame().getTextureManager().bindTexture(TEXTURE);
        RenderSystem.color3f(1.0F, 1.0F, 1.0F);
        manager.drawTexture(matrices, 0, 0, 0, 0, this.getWidth(), this.getHeight());
        int width = this.getWidth() / 2 - (manager.getGame().textRenderer.getWidth("Refreshing blocks") / 2);
        int height = this.getHeight() / 2 - manager.getGame().textRenderer.fontHeight / 2;
        MinecraftClient.getInstance().textRenderer.draw(matrices, "Refreshing blocks", width, height - MinecraftClient.getInstance().textRenderer.fontHeight + 2, 0xFFFFFF);
        DrawableHelper.fill(matrices, 8, (int) (height * 1.6), this.getWidth() - 8, (int) (height * 2.15), 0x70FFFFFF);
        DrawableHelper.fill(matrices, 10, (int) (height * 1.7), (int) (round(progress / todo * (this.getWidth() - 20)) + 10), height * 2, 0xBBFFFFFF);
        return done ? Visibility.HIDE : Visibility.SHOW;
    }
}
