package me.constantindev.antiantixray.Etc;

import net.minecraft.util.math.BlockPos;

public class CustomRenderableItem extends RenderableBlock {
    public float xW;
    public float yW;
    public float zW;

    public CustomRenderableItem(BlockPos pos, int r, int g, int b, int a, float xw, float yw, float zw) {
        super(pos, r, g, b, a);
        this.xW = xw;
        this.yW = yw;
        this.zW = zw;
    }
}
