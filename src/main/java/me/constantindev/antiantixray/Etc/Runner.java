package me.constantindev.antiantixray.Etc;

import me.constantindev.antiantixray.GUI.ProgressBar;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class Runner implements Runnable {
    boolean isRunning = true;
    long delay;
    int rad;
    ProgressBar progressBar;

    public Runner(int rad, long delay, ProgressBar progressBar) {
        this.rad = rad;
        this.delay = delay;
        this.progressBar = progressBar;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        ClientPlayNetworkHandler conn = MinecraftClient.getInstance().getNetworkHandler();
        if (conn == null) return;
        assert MinecraftClient.getInstance().player != null;
        BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();


        // Blocks that aren't ores but still needs to be checked
        Block[] blocks2check = Config.checkblocks;

        for (int cx = -rad; cx <= rad; cx++) {
            for (int cy = -rad; cy <= rad; cy++) {
                for (int cz = -rad; cz <= rad; cz++) {
                    if (!isRunning) break;
                    progressBar.progress++;
                    BlockPos current = new BlockPos(pos.getX() + cx, pos.getY() + cy, pos.getZ() + cz);

                    Block block = MinecraftClient.getInstance().player.world.getBlockState(current).getBlock();

                    boolean good = Config.scanAll; // cool for else man

                    // only check if block is a ore or in blocks2check (obsidian for example)
                    for (Block block1 : blocks2check) {
                        if (block.equals(block1)) {
                            good = true;
                            break;
                        }
                    }

                    if (!good) {
                        continue;
                    }

                    PlayerActionC2SPacket packet = new PlayerActionC2SPacket(
                            PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK,
                            current,
                            Direction.UP
                    );
                    conn.sendPacket(packet);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ignored) {
                        Logger.info("Shit broke somehow, this shouldn't happen. (Runner thread scanning for fake blocks got interrupted). Did you manually kill the thread?");
                    }
                }
            }
        }
        progressBar.done = true;
    }
}
